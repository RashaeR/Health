/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Health;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rashae
 */
public class RegisterDoctorController implements Initializable {
    
    @FXML
    private Button backButton;
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField middleText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField cellText;
    @FXML
    private TextField homeText;
    @FXML
    private TextField addressText;
    @FXML
    private TextField cityText;
    @FXML
    private TextField stateText;
    @FXML
    private TextField zipText;
    
    private DataBase data = new DataBase();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void showMainMenu(ActionEvent event ) throws Exception
    {          
        
        BorderPane mainMenu = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Parent mainInnerMenu = FXMLLoader.load(getClass().getResource("MainInnerMenu.fxml"));
        
        mainMenu.setCenter(mainInnerMenu);
            
        Scene scene = new Scene(mainMenu);
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
    }
    
    public void submitInfo(ActionEvent even) throws Exception
    {
        data.setDoctor(firstNameText,middleText,lastNameText,emailText,cellText,
                homeText,addressText,cityText,stateText,zipText);
       
    }
    
}
