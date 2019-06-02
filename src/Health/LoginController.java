
package Health;

import java.net.URL;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
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
      private DataBase data = new DataBase();
      private ResultSet validate = null;
      private Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
      
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {   
        
    } 
    
    public void showMenu(ActionEvent event) throws Exception
    {          
      
        validate = data.validate(userText, passwordText);
        BorderPane mainMenu = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Parent mainInnerMenu = FXMLLoader.load(getClass().getResource("Calendar.fxml"));
        
        //If both user and password match continues to next screen
        if (validate.next())
        {
            if(validate.getString(1).equals("No"))
            {
                System.out.println("No Admin access");
            }
            else{
            mainMenu.setCenter(mainInnerMenu);
            
            Scene scene = new Scene(mainMenu,screenSize.getWidth(), screenSize.getHeight());
        
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();
            }
        }
        //Gives warning that input information is incorrect
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
