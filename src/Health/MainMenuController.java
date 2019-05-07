/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Health;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rashae
 */
public class MainMenuController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {

    }   
    
    public void showRegisterDoctor(ActionEvent event) throws Exception
    {
      BorderPane mainMenu = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
      Parent registerDocMenu = FXMLLoader.load(getClass().getResource("RegisterDoctor.fxml"));
      
       mainMenu.setCenter(registerDocMenu);
       Scene scene = new Scene(mainMenu);
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
