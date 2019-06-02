/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Health;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.RadioButton;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Rashae
 */
public class ReigsterPatientController extends RegisterDoctorController {

  
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
         TextFields.bindAutoCompletion(stateText, possibleStates);
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
       
        //Get birthday's date value
        try{
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
        if(middleText.getText().length() > 2 || !middleText.getText().matches("[A-Z][a-zA-Z]*"))
        {
           middleText.setStyle("-fx-border-color: red;");
          
        }
        else
        {
            middleText.setStyle(null);
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
        
        
        if( v1 && v2 && v3 && v4 && v5 && v6 && v7 && v8 && v9 && v10 && v11
                && v12 )
        {
            RadioButton temp = (RadioButton)genderTG.getSelectedToggle();
            String genderValue = temp.getText();
            String birthday = mDate.getText() + "/" + dDate.getText() + "/" 
                + yDate.getText();
      
            data.setPatient(firstNameText, middleText, lastNameText, emailText,
                cellText, addressText, cityText, stateText, zipText,
                birthday, genderValue);
        }

    }
}
