
package Login;

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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rashae
 */
public class LoginController implements Initializable {

 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {

    } 
    
    public void showMenu(ActionEvent event ) throws Exception
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
    
}
