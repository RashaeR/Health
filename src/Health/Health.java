/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Health;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Rashae
 */
public class Health extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
       showLogin(primaryStage);
    }

    public void showLogin(Stage primaryStage) throws Exception
    {
        Parent login = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(login);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
