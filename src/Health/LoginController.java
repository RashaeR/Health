
package Health;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rashae
 */
public class LoginController implements Initializable {

      @FXML
      private TextField userText;
      @FXML
      private PasswordField passwordText;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {

    } 
    public void test(ActionEvent event) throws Exception
    {
        BorderPane mainMenu = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Parent mainInnerMenu = FXMLLoader.load(getClass().getResource("MainInnerMenu.fxml"));
        
        mainMenu.setCenter(mainInnerMenu);
            
            Scene scene = new Scene(mainMenu);
        
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.show();
    }
    
    public void showMenu(ActionEvent event ) throws Exception
    {          
        DataBase data = new DataBase();
//        BorderPane mainMenu = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
//        Parent mainInnerMenu = FXMLLoader.load(getClass().getResource("MainInnerMenu.fxml"));
        
        
        if (data.validate(userText, passwordText).next())
        {
            test(event);
//            mainMenu.setCenter(mainInnerMenu);
//            
//            Scene scene = new Scene(mainMenu);
//        
//            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            stage.hide();
//            stage.setScene(scene);
//            stage.show();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Information");
            alert.setHeaderText(null);
            alert.setContentText("Does not recognize user information");
           
            Optional<ButtonType> option = alert.showAndWait();
 
            if (option.get() == ButtonType.OK)
                {
                        userText.setText("");
                        passwordText.setText("");
                }
        }
        
        
    }
    
}
