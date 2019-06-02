package Health;

import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.TextFields;

public class ViewDocController {

    ObservableList<String> title = FXCollections.observableArrayList("Doctor", "Patient");
    
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField middleText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField date;
    
    @FXML
    private TextField addressText;
    @FXML
    private TextField cityText;
    @FXML
    private TextField stateText;
    @FXML
    private TextField zipText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField cellText;
    @FXML
    private ToggleGroup genderTG;
    @FXML
    private RadioButton maleRadio;
    @FXML
    private RadioButton femaleRadio;
    @FXML
    private TextField positionText;
    @FXML
    private TextField searchText;
    @FXML
    private Button deleteButton;
    @FXML
    private Button updateButton;
    @FXML
    private ComboBox title_ComboBox;
    @FXML
    private HBox position_hBox;
    @FXML
    private VBox view_vBox;
    
    private DataBase data = new DataBase();
    private String [] name;
    private boolean isClicked = false;
    @FXML
    public void initialize() throws Exception
    {
        title_ComboBox.setValue("Doctor");
        title_ComboBox.setItems(title);
        
         TextFields.bindAutoCompletion(searchText, data.getName("Doctor", ",MName, ' ',"));
      
      
    }  
    
    public void buttonClicked()
    {
        isClicked = true;
    }
    
    public void changeMenu() throws Exception
    {
        if(title_ComboBox.getValue().toString() == "Patient" )
        {
            
            view_vBox.getChildren().remove(position_hBox);
            TextFields.bindAutoCompletion(searchText, data.getName("Patient", ",MName, ' ',"));
        }
        else
        {
            view_vBox.getChildren().add(position_hBox);
            TextFields.bindAutoCompletion(searchText, data.getName("Doctor",",MName, ' ',"));
        }
        
    }
    
    public void submit() throws Exception
    {    name = searchText.getText().split(" ");
      data.getDoctor(name[0], name[1], name[2], firstNameText, middleText,
                lastNameText, emailText, cellText, addressText, cityText, stateText,
                zipText, date, maleRadio, femaleRadio, positionText);
    }
    
    public void delete() throws Exception
    {
      name = searchText.getText().split(" ");
      if(isClicked)
      {              
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
      
      alert.setTitle("Delete");
      alert.setHeaderText("Are you sure want to delete this information?");
      alert2.setTitle("Confirmation");
      alert2.setHeaderText(null);
      alert2.setContentText("This information has been successfully deleted");
      
      Optional<ButtonType> option = alert.showAndWait();
      
      if (option.get() == ButtonType.OK)
      {
       
        data.delete(title_ComboBox.getValue().toString(), name[0], name[1], name[2]);
        
        searchText.setText("");
        firstNameText.setText("");
        middleText.setText("");
        lastNameText.setText("");
        emailText.setText("");
        cellText.setText("");
        addressText.setText("");
        cityText.setText("");
        stateText.setText("");
        zipText.setText("");
        date.setText("");
        genderTG.selectToggle(null);
        positionText.setText("");
      }
     }
    }

}
