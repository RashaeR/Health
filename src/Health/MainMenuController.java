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
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
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
    private Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {

    }   
    
    public void showRegisterDoctor(ActionEvent event) throws Exception
    {
      BorderPane mainMenu = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
      Parent registerDocMenu = FXMLLoader.load(getClass().getResource("RegisterDoctor.fxml"));
      
       mainMenu.setCenter(registerDocMenu);
       Scene scene = new Scene(mainMenu,screenSize.getWidth(), screenSize.getHeight());
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
       public void showRegisterPatient(ActionEvent event) throws Exception
    {
      BorderPane mainMenu = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
      Parent registerPatMenu = FXMLLoader.load(getClass().getResource("ReigsterPatient.fxml"));
      
       mainMenu.setCenter(registerPatMenu);
       Scene scene = new Scene(mainMenu,screenSize.getWidth(), screenSize.getHeight());
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public void showView(ActionEvent event) throws Exception
    {
        BorderPane mainMenu = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Parent viewMenu = FXMLLoader.load(getClass().getResource("ViewDoc.fxml"));
      
        mainMenu.setCenter(viewMenu);
        Scene scene = new Scene(mainMenu, screenSize.getWidth(), screenSize.getHeight());
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public void showAppointments(ActionEvent event) throws Exception
    {
        BorderPane mainMenu = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Parent viewMenu = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
      
        mainMenu.setCenter(viewMenu);
        Scene scene = new Scene(mainMenu, screenSize.getWidth(), screenSize.getHeight());
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public void showUser(ActionEvent event) throws Exception
    {
        BorderPane mainMenu = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Parent userMenu = FXMLLoader.load(getClass().getResource("CreateUser.fxml"));
      
        mainMenu.setCenter(userMenu);
        Scene scene = new Scene(mainMenu,screenSize.getWidth(), screenSize.getHeight());
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
}
