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
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Rashae
 */
public class DataBase 
{   
    Connection connect;
    PreparedStatement pst;
    ResultSet resultSet = null;
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
    {  
        try{
        String sql = "select Admin from user where username=? and password=?";
        
        pst = connect.prepareStatement(sql);   
        pst.setString(1, userName.getText());
        pst.setString(2, pass.getText());
        resultSet = pst.executeQuery();
       
        }
    catch(Exception e)
    {
        
    }
    return resultSet; 
    }
    
    public void setUser(TextField user, TextField pass, TextField fName, TextField lName,
            TextField email, TextField cell, TextField address,
            TextField city, TextField state, TextField zip, String bDay, String gender, String admin) throws Exception
    {
        String sql = "insert into user (Username, Password, Fname, LName, Email, Phone, "
                + "Address, City, State, Zip, Birthday, Gender, Admin)"
                + " values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        pst = connect.prepareStatement(sql);
        pst.setString(1, user.getText());
        pst.setString(2,pass.getText());
        pst.setString(3, fName.getText());
        pst.setString(4, lName.getText());
        pst.setString(5, email.getText());
        pst.setString(6, cell.getText());
        pst.setString(7, address.getText());
        pst.setString(8, city.getText());
        pst.setString(9, state.getText());
        pst.setString(10, zip.getText());
        pst.setString(11, bDay);
        pst.setString(12, gender);
        pst.setString(13, admin);
        
        pst.executeUpdate();
    }
    
    public void getUser(String fName, String lName, TableView view, TableColumn col, TableColumn col2,
            TableColumn col3, TableColumn col4, TableColumn col5, TableColumn col6,
            TableColumn col7) throws Exception
    {
        ObservableList<User> info = null;
        List<String> userID = new ArrayList();
        List<String> password = new ArrayList();
        List<String> name = new ArrayList();
        List<String> dob = new ArrayList();
        List<String> email = new ArrayList();
        List<String> gender = new ArrayList();
        List<String> admin = new ArrayList();
        List<User> user = new ArrayList();
        
        String sql = "Select Username, Password, concat(FName, ' ', LName), "
                + " Birthday, Email, "
                + "Gender, Admin From User where FName = ? and LName = ?";
        pst = connect.prepareStatement(sql);
        pst.setString(1,fName);
        pst.setString(2,lName);
        resultSet = pst.executeQuery();
        

        while (resultSet.next()) {
            userID.add(resultSet.getString(1));
            password.add(resultSet.getString(2));
            name.add(resultSet.getString(3));
            dob.add(resultSet.getString(4));
            email.add(resultSet.getString(5));
            gender.add(resultSet.getString(6));
            admin.add(resultSet.getString(7));
            
        }
        for (int i = 0; i < userID.size(); i++) {

            user.add(new User(userID.get(i), password.get(i), name.get(i),
                    dob.get(i), email.get(i), gender.get(i), admin.get(i)));

        }
        info = FXCollections.observableArrayList(user);
        view.setItems(info);
        col.setCellValueFactory(new PropertyValueFactory<User, String>("userID"));
        col2.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        col3.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        col4.setCellValueFactory(new PropertyValueFactory<User, String>("birthday"));
        col5.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        col6.setCellValueFactory(new PropertyValueFactory<User, String>("gender"));
        col7.setCellValueFactory(new PropertyValueFactory<User, String>("admin"));
    }
    
    public void getAllUser(TableView view, TableColumn col, TableColumn col2,
            TableColumn col3, TableColumn col4, TableColumn col5, TableColumn col6,
            TableColumn col7) throws Exception
    {
        ObservableList<User> info = null;
        List<String> userID = new ArrayList();
        List<String> password = new ArrayList();
        List<String> name = new ArrayList();
        List<String> dob = new ArrayList();
        List<String> email = new ArrayList();
        List<String> gender = new ArrayList();
        List<String> admin = new ArrayList();
        List<User> user = new ArrayList();
        
        String sql = "Select Username, Password, concat(FName, ' ', LName), "
                + " Birthday, Email, "
                + "Gender, Admin From User";
        pst = connect.prepareStatement(sql);
        resultSet = pst.executeQuery();
        

        while (resultSet.next()) {
            userID.add(resultSet.getString(1));
            password.add(resultSet.getString(2));
            name.add(resultSet.getString(3));
            dob.add(resultSet.getString(4));
            email.add(resultSet.getString(5));
            gender.add(resultSet.getString(6));
            admin.add(resultSet.getString(7));
            
        }
        for (int i = 0; i < userID.size(); i++) {

            user.add(new User(userID.get(i), password.get(i), name.get(i),
                    dob.get(i), email.get(i), gender.get(i), admin.get(i)));

        }
        info = FXCollections.observableArrayList(user);
        view.setItems(info);
        col.setCellValueFactory(new PropertyValueFactory<User, String>("userID"));
        col2.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        col3.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        col4.setCellValueFactory(new PropertyValueFactory<User, String>("birthday"));
        col5.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        col6.setCellValueFactory(new PropertyValueFactory<User, String>("gender"));
        col7.setCellValueFactory(new PropertyValueFactory<User, String>("admin"));
    }
    
    public void setDoctor(TextField fName, TextField mName, TextField lName,
            TextField email, TextField cell, TextField address,
            TextField city, TextField state, TextField zip, String bDay, String gender, TextField pos) throws Exception
    {
        String sql = "insert into doctor (Fname, MName, LName, Email, Phone, "
                + "Address, City, State, Zip, Birthday, Gender, Position)"
                + " values (?,?,?,?,?,?,?,?,?,?,?,?)";
        
        pst = connect.prepareStatement(sql);
        pst.setString(1, fName.getText());
        pst.setString(2, mName.getText());
        pst.setString(3, lName.getText());
        pst.setString(4, email.getText());
        pst.setString(5, cell.getText());
        pst.setString(6, address.getText());
        pst.setString(7, city.getText());
        pst.setString(8, state.getText());
        pst.setString(9, zip.getText());
        pst.setString(10, bDay);
        pst.setString(11, gender);
        pst.setString(12, pos.getText());
        
        pst.executeUpdate();
       
    }
    
    public void getDoctor(String fName, String mName, String lName, TextField t1,
            TextField t2, TextField t3, TextField t4, TextField t5, TextField t6,
            TextField t7, TextField t8, TextField t9, TextField t10 ,RadioButton t11, 
            RadioButton t11_2, TextField t12) throws Exception
    {
        String sql = "Select * from doctor where FName = ? and MName = ? and LName = ?";  
        pst = connect.prepareStatement(sql);   
        pst.setString(1, fName);
        pst.setString(2, mName);
        pst.setString(3, lName);
        resultSet = pst.executeQuery();
        
        while(resultSet.next())
        {
            t1.setText(resultSet.getString(2));
            t2.setText(resultSet.getString(3));
            t3.setText(resultSet.getString(4));
            t4.setText(resultSet.getString(5));
            t5.setText(resultSet.getString(6));
            t6.setText(resultSet.getString(7));
            t7.setText(resultSet.getString(8));
            t8.setText(resultSet.getString(9));
            t9.setText(resultSet.getString(10));
            t10.setText(resultSet.getString(11));
            if(resultSet.getString(12).equals("Male"))
            {
                t11.setSelected(true);
            }
            else
            {
                t11_2.setSelected(true);
            }      
            t12.setText(resultSet.getString(13));
           
            
        }
    }
    
    public void setPatient(TextField fName, TextField mName, TextField lName,
            TextField email, TextField cell, TextField address,
            TextField city, TextField state, TextField zip, String bDay, String gender) throws Exception
    {
        String sql = "insert into patient (Fname, MName, LName, Email, Phone, "
                + "Address, City, State, Zip, Birthday, Gender)"
                + " values (?,?,?,?,?,?,?,?,?,?,?)";
        
        pst = connect.prepareStatement(sql);
        pst.setString(1, fName.getText());
        pst.setString(2, mName.getText());
        pst.setString(3, lName.getText());
        pst.setString(4, email.getText());
        pst.setString(5, cell.getText());
        pst.setString(6, address.getText());
        pst.setString(7, city.getText());
        pst.setString(8, state.getText());
        pst.setString(9, zip.getText());
        pst.setString(10, bDay);
        pst.setString(11, gender);
       
        
        pst.executeUpdate();
    }
    
    public void delete(String table, String fName, String mName, String lName) throws Exception
    {
        String sql = "DELETE FROM " + table + " WHERE FName = ? and MName = ? and LName = ?";
        pst = connect.prepareStatement(sql);
        pst.setString(1, fName);
        pst.setString(2, mName);
        pst.setString(3, lName);
        pst.executeUpdate();
        
    }
    
    public void delete2(String fName, String lName) throws Exception
    {
        String sql = "DELETE FROM User WHERE FName = ? and LName = ?";
        pst = connect.prepareStatement(sql);
        pst.setString(1, fName);
        pst.setString(2, lName);
        pst.executeUpdate();
        
    }
    
    
    public ArrayList<String> getName(String table, String MName) throws Exception
    {
        String sql = "SELECT CONCAT(FName,' ' "+ MName+" LName) FROM "
                + table;
        ArrayList<String> list  = new ArrayList<>();
        pst = connect.prepareStatement(sql);
   
        resultSet = pst.executeQuery();
        
        while(resultSet.next())
        {
            list.add(resultSet.getString(1));
        }
        
        return list;
    }
    
    
  
    
}
