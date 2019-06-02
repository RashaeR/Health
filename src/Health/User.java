/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Health;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Rashae
 */
public class User
{
    private SimpleStringProperty userID;
    private SimpleStringProperty password;
    private SimpleStringProperty name;
    private SimpleStringProperty dob;
    private SimpleStringProperty email;
    private SimpleStringProperty gender;
    private SimpleStringProperty admin;
    
    User(String userID, String password, String name, String dob, String email,
            String gender, String admin)
    {
        this.userID = new SimpleStringProperty(userID);
        this.password = new SimpleStringProperty(password);
        this.name = new SimpleStringProperty(name);
        this.dob = new SimpleStringProperty(dob);
        this.email = new SimpleStringProperty(email);
        this.gender = new SimpleStringProperty(gender);
        this.admin = new SimpleStringProperty(admin);
    }
    
    public String getUserID(){ return userID.get();}
    public String getPassword(){ return password.get();}
    public String getName(){ return name.get();}
    public String getBirthday(){ return dob.get();}
    public String getEmail(){ return email.get();}
    public String getGender(){ return gender.get();}
    public String getAdmin(){ return admin.get();}

    
    
}
