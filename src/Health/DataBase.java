/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Health;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Rashae
 */
public class DataBase 
{   
    Connection connect;
    PreparedStatement pst;
    public DataBase()
    {   try{
        Class.forName("com.mysql.cj.jdbc.Driver");
      
        connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/health_care",
        "root","");
      
    
        }
        catch(Exception e)
        {
            e.printStackTrace();   
        }
    }
    
    public ResultSet validate(TextField userName, PasswordField pass)
    {   ResultSet resultSet = null;
        try{
        String sql = "select * from user where username=? and password=?";
        
        pst = connect.prepareStatement(sql);
        pst.setString(1, userName.getText());
        
        pst.setString(1, userName.getText());
        pst.setString(2, pass.getText());
       resultSet = pst.executeQuery();
       
//        if(resultSet.next())
//        {
//            System.out.println("Matched");
//          
//        }
//        else{
//            System.out.println("Not Matched");
//            userName.setText("");
//            pass.setText("");
//        }
//        connect.close();
        }
    catch(Exception e)
    {
        
    }
    return resultSet; 
    }
    
  
    
}
