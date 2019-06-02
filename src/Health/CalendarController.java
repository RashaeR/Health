/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Health;

import com.pepperonas.fxiconics.FxIconicsLabel;
import com.pepperonas.fxiconics.MaterialColor;
import com.pepperonas.fxiconics.gmd.FxFontGoogleMaterial;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CalendarController implements Initializable
{
    
    @FXML private Label lblMonthYear;
    @FXML private GridPane gpMain;   
    LocalDateTime ldtControl;
    
    private Connection connection;
    PreparedStatement pst;
     
    public CalendarController() {
        try { 
            Class.forName("com.mysql.cj.jdbc.Driver");
      
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/health_care",
            "root","");
   
        } catch (Exception ex) {
          
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ldtControl = LocalDateTime.now();        
        try{
        loadMonth(ldtControl);
        }
        catch(Exception e)
        {
                   
        }
    }    
 
    int getColumn(LocalDateTime ldt)
    {
        int i = 0;
        while(ldt.getDayOfWeek() != DayOfWeek.SUNDAY)
        {
            i++;
            ldt = ldt.plusDays(1);
            
        }
        
        return i;
    }
    
    @FXML private void handleBTNMonthChange(ActionEvent event) throws Exception
    {
        if(((Button)event.getSource()).getId().equals("leftButtonWithImage"))
        {
            ldtControl = ldtControl.minusMonths(1);
            loadMonth(ldtControl);
        }
        else if(((Button)event.getSource()).getId().equals("rightButtonWithImage"))
        {
            ldtControl = ldtControl.plusMonths(1);
            loadMonth(ldtControl);
        }
    }
    
    private void loadMonth(LocalDateTime ldt) throws Exception
    {
        if(gpMain.getChildren().size() > 0)
        {
            gpMain.getChildren().clear();
        }
       
        loadGridPaneFirstRow();
        lblMonthYear.setText(ldt.getMonth() + " " + ldt.getYear());
        lblMonthYear.setFont(Font.font(24));
        LocalDateTime ldtIterator = ldt.minusDays(ldt.getDayOfMonth() - 1);   
        ldtIterator.format(DateTimeFormatter.ISO_DATE);
        
        int control = getColumn(ldtIterator);
        int control2 = 0;
        int i = 0;
        while(ldtIterator.getMonth() == ldt.getMonth())
        {
            if( i == 0 || i == 1 && control2 <= control)
            {
                i = 1;
                control2++;
            }
            else 
            {
                i = ((control2 - (control + 1)) / 7) + 2;
                control2++;
            }
            
            Button tempLabel = new Button(Integer.toString(ldtIterator.getDayOfMonth()));
            tempLabel.setFont(Font.font(18));
            
            
            gpMain.add(createCell(tempLabel, ldtIterator), ldtIterator.getDayOfWeek().getValue() - 1, i);
           
            ldtIterator = ldtIterator.plusDays(1);
        }        
    }
    
    private void loadGridPaneFirstRow()
    {
        String[] string = {"Monday", "Tuesday", "Wednesday", "Thrusday", "Friday", "Saturday", "Sunday"};
        for(int i = 0; i < string.length; i++)
        {
            Label tempLabel = new Label(string[i]);
            GridPane.setHalignment(tempLabel, HPos.CENTER);
            tempLabel.setFont(Font.font(24));
            gpMain.add(tempLabel, i, 0);
        }       
    }
    
    private BorderPane createCell(Button label, LocalDateTime ldt) throws Exception
    {
        
        BorderPane cell = new BorderPane();
        
        label.setOnMouseClicked(e -> {
                try{
                addNewNote(ldt);
                }
                catch(Exception ex)
                {
                    
                }
        });
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(loadNotesForDate(ldt));
        BorderPane.setAlignment(vbox, Pos.CENTER);
        cell.setCenter(vbox);

        BorderPane.setAlignment(label, Pos.TOP_RIGHT);
        cell.setTop(label);
        cell.getStyleClass().add("cell");
        
        return cell;
    }
    
    //Templete for each appointment when loaded
    private List<HBox> loadNotesForDate(LocalDateTime ldt) throws Exception
    {  
        List<HBox> tempList = new ArrayList();
        
        String sql = "Select * FROM notes WHERE date = ?";
        
       
            pst = connection.prepareStatement(sql);     
            pst.setString(1, ldt.toLocalDate().toString());
            ResultSet rs = pst.executeQuery();  
            while(rs.next())
            {
                FxIconicsLabel labelTextDefault =
                (FxIconicsLabel) new FxIconicsLabel.Builder(FxFontGoogleMaterial.Icons.gmd_cancel)
                        .size(16)
                        .text("")
                        .color(MaterialColor.BLACK)
                        .build()
                        ;
                
                String tempNote = rs.getString("note");
                Label tempLabel = new Label(tempNote);
                tempLabel.setStyle("-fx-font-size: 15");
               
                labelTextDefault.setOnMouseClicked(e -> {
                    try{
                    deleteNote(ldt, tempNote);
                    loadMonth(ldt);
                    }
                    catch(Exception ex)
                    {
                        
                    }
                });
                tempLabel.setOnMouseClicked(e -> {
                    try{
                    editNote(ldt, tempNote); 
                    loadMonth(ldt);
                    }
                    catch(Exception ex)
                    {
                        
                    }
                });
                HBox hbox = new HBox();
                hbox.setPadding(new Insets(0,0,0,3));
                hbox.setSpacing(3);
                hbox.setAlignment(Pos.CENTER_LEFT);
                hbox.getChildren().add(labelTextDefault);
                hbox.getChildren().add(tempLabel);
                tempList.add(hbox);
            }
       
        
        return tempList;
    }
    
    private void addNewNote(LocalDateTime ldt) throws Exception
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("New Note Dialog");
        alert.setHeaderText("Add New Note");
        alert.setContentText("Enter note!");
        
        VBox eventBox = new VBox();
        eventBox.setSpacing(5);
        
        Label eventLabel = new Label("Create Event");
        Label spaceLabel = new Label("");
        eventLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        eventBox.getChildren().addAll(eventLabel,spaceLabel);
        
        TextField textField = new TextField();
        Label spaceLabel2 = new Label("");
        
        VBox doctorBox = new VBox();
        Label appDoc = new Label("Doctor");
        ComboBox AppDoctor = new ComboBox(); 
        Label spaceLabel3 = new Label("");
        doctorBox.getChildren().addAll(appDoc,AppDoctor,spaceLabel3);
        
        VBox patientBox = new VBox();
        Label appPatient = new Label("Patient");
        ComboBox AppPatient = new ComboBox();
        Label spaceLabel4 = new Label("");
        patientBox.getChildren().addAll(appPatient,AppPatient,spaceLabel4);
        
        HBox timeBox = new HBox();
        Label start = new Label("Start Time:  ");
        TextField tfStart = new TextField();
        Label spaceLabel5 = new Label("\t\t\t");
        Label end = new Label("End Time:  ");
        TextField tfEnd = new TextField();
        timeBox.getChildren().addAll(start,tfStart,spaceLabel5,end,tfEnd);
        
        Label appRoom = new Label("Room Number");
        TextField AppRoom = new TextField();
                
        Label tfLabel = new Label("Event Description:");
        
        TextArea textArea = new TextArea();
        textArea.setEditable(true);
        textArea.setWrapText(true);
        
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        
        expContent.add(eventBox, 0, 0);
        expContent.add(textField, 0, 1);
        expContent.add(spaceLabel2, 0, 2);
        expContent.add(doctorBox, 0,3);
        expContent.add(patientBox, 0, 4);
        expContent.add(timeBox, 0, 5);
        expContent.add(appRoom, 0,6);
        expContent.add(AppRoom, 0, 7);
        expContent.add(tfLabel, 0, 19);
        expContent.add(textArea, 0, 20);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setContent(expContent);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            if(textField.getText().length() > 0)
            {
                createNoteForDate(textField.getText(), ldt);
                loadMonth(ldt);
            }            
        } 
    }
    
    private void deleteNote(LocalDateTime ldt, String note) throws Exception
    {
        if(deleteConfirmation(note).get() == ButtonType.OK)
        {
            String sql = "DELETE FROM notes WHERE note = ? and date = ?";
           
            pst = connection.prepareStatement(sql);
            
            pst.setString(1, note);
            pst.setString(2, ldt.toLocalDate().toString());

            pst.executeUpdate();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Delete Note Dialog");
            alert.setHeaderText("Delete Note");
            alert.setContentText("Note was successfully deleted!");
            alert.show();           
            }
            
        }
    
    
    private void editNote(LocalDateTime ldt, String note) throws Exception
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Edit Note Dialog");
        alert.setHeaderText("Edit Note");
        alert.setContentText("Enter note!");

        VBox eventBox = new VBox();
        eventBox.setSpacing(5);
        
        Label eventLabel = new Label("Create Event");
        Label spaceLabel = new Label("");
        eventLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        eventBox.getChildren().addAll(eventLabel,spaceLabel);
        
        TextField textField = new TextField();
        Label spaceLabel2 = new Label("");
        
        VBox doctorBox = new VBox();
        Label appDoc = new Label("Doctor");
        ComboBox AppDoctor = new ComboBox(); 
        Label spaceLabel3 = new Label("");
        doctorBox.getChildren().addAll(appDoc,AppDoctor,spaceLabel3);
        
        VBox patientBox = new VBox();
        Label appPatient = new Label("Patient");
        ComboBox AppPatient = new ComboBox();
        Label spaceLabel4 = new Label("");
        patientBox.getChildren().addAll(appPatient,AppPatient,spaceLabel4);
        
        HBox timeBox = new HBox();
        Label start = new Label("Start Time:  ");
        TextField tfStart = new TextField();
        Label spaceLabel5 = new Label("\t\t\t");
        Label end = new Label("End Time:  ");
        TextField tfEnd = new TextField();
        timeBox.getChildren().addAll(start,tfStart,spaceLabel5,end,tfEnd);
        
        Label appRoom = new Label("Room Number");
        TextField AppRoom = new TextField();
                
        Label tfLabel = new Label("Event Description:");
        
        TextArea textArea = new TextArea();
        textArea.setEditable(true);
        textArea.setWrapText(true);
        
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        
        expContent.add(eventBox, 0, 0);
        expContent.add(textField, 0, 1);
        expContent.add(spaceLabel2, 0, 2);
        expContent.add(doctorBox, 0,3);
        expContent.add(patientBox, 0, 4);
        expContent.add(timeBox, 0, 5);
        expContent.add(appRoom, 0,6);
        expContent.add(AppRoom, 0, 7);
        expContent.add(tfLabel, 0, 19);
        expContent.add(textArea, 0, 20);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setContent(expContent);
        //Change button text to show update 
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Update");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            if(textField.getText().length() > 0)
            {
                editNoteForDate(note, textField.getText(), ldt);
                loadMonth(ldt);
            }            
        } 
    }
    
    private void createNoteForDate(String note, LocalDateTime ldt) throws Exception
    {
        
        String sql = "INSERT INTO notes(date, note) VALUES(?, ?)";
        
        pst = connection.prepareStatement(sql);
        
        pst.setString(1, ldt.toLocalDate().toString());
        pst.setString(2, note);
           
        pst.executeUpdate();
        
       
    }
    
    private void editNoteForDate(String oldNote, String newNote, LocalDateTime ldt) throws Exception
    {
        String sql = "UPDATE Notes SET note = ? WHERE note = ? and  date = ?";
       
               pst = connection.prepareStatement(sql);
        
            
            pst.setString(1, newNote);
            pst.setString(2, oldNote);
            pst.setString(3, ldt.toLocalDate().toString());
           
            pst.executeUpdate();
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Update Note Dialog");
            alert.setHeaderText("Update Note");
            alert.setContentText("Note was successfully updated!");
            alert.show();           
        }
       
    
    
    private void exceptionDialog(String exceptionText)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Error!");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);
        alert.getDialogPane().setExpanded(true);
        alert.showAndWait();
    }
    
    private  Optional<ButtonType> deleteConfirmation(String note)
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Note Dialog");
        alert.setHeaderText("Delete Note");
        alert.setContentText("Are you sure you want to delete this note!");

        TextField textField = new TextField();
        textField.setText(note);

        textField.setMaxWidth(Double.MAX_VALUE);
        textField.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textField, Priority.ALWAYS);
        GridPane.setHgrow(textField, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(textField, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setContent(expContent);
        Optional<ButtonType> result = alert.showAndWait();
        
        ObservableList<String> optionList = FXCollections.observableArrayList();
        return result;
    }
}
 