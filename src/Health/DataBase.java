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
    //Checks if input matches database
    public ResultSet validate(TextField userName, PasswordField pass)
    {   ResultSet resultSet = null;
        try{
        String sql = "select * from user where username=? and password=?";
        
        pst = connect.prepareStatement(sql);
        pst.setString(1, userName.getText());
        
        pst.setString(1, userName.getText());
        pst.setString(2, pass.getText());
        resultSet = pst.executeQuery();
       
        }
    catch(Exception e)
    {
        
    }
    return resultSet; 
    }
    
    public void setDoctor(TextField fName, TextField mName, TextField lName,
            TextField email, TextField cell, TextField home, TextField address,
            TextField city, TextField state, TextField zip) throws Exception
    {
        String sql = "insert into doctor (Fname, MName, LName, Email, Cellphone, "
                + "Homephone, Address, City, State, Zip) values ('"+fName.getText()+"','"
                + mName.getText()+"','"+lName.getText()+"','"+email.getText()+"','"
                + cell.getText()+"','"+home.getText()+"','"+address.getText()+"','"
                + city.getText()+"','"+state.getText()+"','"+zip.getText()+"')";
        pst = connect.prepareStatement(sql);
        pst.executeUpdate(sql);
       
    }
    
  
    
}
