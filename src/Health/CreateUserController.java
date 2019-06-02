/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Health;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.controlsfx.control.textfield.TextFields;


public class CreateUserController extends RegisterDoctorController {
    
    @FXML
    private TextField deleteText;
    @FXML
    private ToggleGroup adminTG;
    @FXML
    private TextField idText;
    @FXML
    private TextField passwordText;
    @FXML
    private TableView userView;
    @FXML
    private TableColumn userCol1;
    @FXML
    private TableColumn userCol2;
    @FXML
    private TableColumn userCol3;
    @FXML
    private TableColumn userCol4;
    @FXML
    private TableColumn userCol5;
    @FXML
    private TableColumn userCol6;
    @FXML
    private TableColumn userCol7;
    
    private String [] name;
    private boolean isClicked = false;
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {   //Add possible states to textfield
        TextFields.bindAutoCompletion(stateText, possibleStates);
        
        try
        {
        //Add list of user to textfield
        TextFields.bindAutoCompletion(deleteText,data.getName("User", ","));
        //Get all users and fills the table
        data.getAllUser(userView, userCol1, userCol2, userCol3,
                userCol4, userCol5, userCol6, userCol7);
        }
        catch(Exception e)
        {
            
        }
        
    }   
    
    public void buttonClicked()
    {   //Used to check of a button is clicked 
        isClicked = true;
    }
    
    @Override
    public void submitInfo() throws Exception
    {
        boolean v1 = false; //v stands for valid
        boolean v2 = false;
        boolean v3 = false;
        boolean v4 = false;
        boolean v5 = false;
        boolean v6 = false;
        boolean v7 = false;
        boolean v8 = false;
        boolean v9 = false;
        boolean v10 = false;
        boolean v11 = false;
        boolean v12 = false;
        boolean v13 = false;
        //Get birthday's date value
        try{
        //Getting the number value for the dates entered
        mDateValue = Integer.parseInt(mDate.getText());
        dDateValue = Integer.parseInt(dDate.getText());
        yDateValue = Integer.parseInt(yDate.getText());
       
        }catch(Exception e)
        {
            
        }
        
        if(firstNameText.getText().equals(" ") || firstNameText.getText().length() < 2 || !firstNameText.getText().matches("[A-Z][a-zA-Z]*") )
        {
           
            firstNameText.setStyle("-fx-border-color: red;");
            
        }
        else
        {
           firstNameText.setStyle(null); 
           v1 = true;
        }
        if(lastNameText.getText().equals("") || lastNameText.getText().length() < 2 || !lastNameText.getText().matches("[A-Z][a-zA-Z]*") )
        {
            lastNameText.setStyle("-fx-border-color: red;");
          
        }
        else
        {
            lastNameText.setStyle(null);
            v2 = true;
        }
        if(idText.getText().length() < 2 || idText.getText().length() > 32)
        {
            idText.setStyle("-fx-border-color: red;");
        }
        else
        {
         idText.setStyle(null);
         v3 = true;
        }
        if(mDate.getText().length() > 2 || mDateValue > 12 || mDateValue <= 0
                || mDate.getText().length() <= 0 || mDate.getText().matches("[A-Z][a-z]*"))
        {
          mDate.setStyle("-fx-border-color: red;");
          
        }
        else
        {
          mDate.setStyle(null);
          v4 = true;
        }
        if(dDate.getText().length() > 3 && dDateValue > 32  || dDate.getText().length() <= 0)
        {
           dDate.setStyle("-fx-border-color: red;");
          
        }
        else
        {
            dDate.setStyle(null);
            v5 = true;
        }
        if(yDate.getText().length() > 5 && yDateValue > 1900  || yDate.getText().length() <= 0)
        {
           yDate.setStyle("-fx-border-color: red;");
         
        }
        else
        {
            yDate.setStyle(null);
            v6 = true;
        }
        if(emailText.getText().equals("") || !emailText.getText().matches("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")
            || emailText.getText().length() >64)
        {
           emailText.setStyle("-fx-border-color: red;");
          
        }
        else
        {
            emailText.setStyle(null);
            v7 = true;
        }
        if(cellText.getText().equals("") || cellText.getText().length() <= 0)
        {
            cellText.setStyle("-fx-border-color: red;");
           
        }
        else
        {
            cellText.setStyle(null);
            v8 = true;
        }
        if(addressText.getText().equals(""))
        {
            addressText.setStyle("-fx-border-color: red;");
         
        }
        else
        {
            addressText.setStyle(null);
            v9 = true;
        }
        if(cityText.getText().equals(""))
        {
           cityText.setStyle("-fx-border-color: red;");
           
        }
        else
        {
            cityText.setStyle(null);
            v10 = true;
        }
        if(stateText.getText().equals(""))
        {
           stateText.setStyle("-fx-border-color: red;"); 
          
        }
        else
        {
            stateText.setStyle(null);
            v11 = true;
        }
        if(zipText.getText().equals(""))
        {
           zipText.setStyle("-fx-border-color: red;");
          
        }
        else
        {
            zipText.setStyle(null);
            v12 = true;
        }
        if(passwordText.getText().length() < 2 || passwordText.getText().length() > 32)
        {
            passwordText.setStyle("-fx-border-color: red;");
        }
        else
        {
            passwordText.setStyle(null);
            v13 = true;
        }
       
        if( v1 && v2 && v3 && v4 && v5 && v6 && v7 && v8 && v9 && v10 && v11
                && v12 && v13)
        {
        
            RadioButton temp = (RadioButton)genderTG.getSelectedToggle();
            RadioButton temp2 = (RadioButton)adminTG.getSelectedToggle();
            String genderValue = temp.getText();
            String adminValue = temp2.getText();
            String birthday = mDate.getText() + "/" + dDate.getText() + "/" 
            + yDate.getText();
      
            data.setUser(idText, passwordText, firstNameText, lastNameText, emailText,
            cellText, addressText, cityText, stateText, zipText,
            birthday, genderValue, adminValue);  
    
        }
        
    }
   
    public void search() throws Exception
    {
        name = deleteText.getText().split(" ");
        data.getUser(name[0], name[1], userView, userCol1, userCol2, userCol3,
                userCol4, userCol5, userCol6, userCol7);
    }
    
   
    public void delete() throws Exception
    {
        name = deleteText.getText().split(" ");
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
       
            data.delete2( name[0], name[1]);
            deleteText.setText("");
            data.getUser(name[0], name[1], userView, userCol1, userCol2, userCol3,
                userCol4, userCol5, userCol6, userCol7);
        }
        }
    }
    
}
