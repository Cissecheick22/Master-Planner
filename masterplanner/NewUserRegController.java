/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterplanner;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static masterplanner.DBconection.con;

/**
 * FXML Controller class
 *
 * @author cheick
 */
public class NewUserRegController implements Initializable {

    @FXML
    private TextField TxtNewUserName;
    @FXML
    private TextField TxtNewUserPassword;
    @FXML
    private TextField TxtNewUserRePassword;
    @FXML
    private TextField TxtNewUserAge;
    @FXML
    private TextField TxtNewUserEmail;
    @FXML
    private TextField TxtNewUserContact;
    @FXML
    private Button BtnNewUserSave;
    @FXML
    private Button BtnNewUserCancel;
    @FXML
    private Hyperlink HNewUserLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    
    private Boolean validateText(String text)
    {
        Pattern p = Pattern.compile("[a-zA-Z0-9]+");
        Matcher m = p.matcher(text);
        if (m.find() && m.group().equals(text))
        {
            return true ;
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("User Registration");
           alert.setHeaderText("");
           alert.setContentText("Incorrect informations");
           alert.showAndWait();
        }
        
        
        return false;
    }
    
     private Boolean validateAge(String text)
    {
        Pattern p = Pattern.compile("[0-9]{2}");
        Matcher m = p.matcher(text);
        if (m.find() && m.group().equals(text))
        {
            return true ;
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("User Registration");
           alert.setHeaderText("");
           alert.setContentText("Age is incorrect!!!");
           alert.showAndWait();
        }
        
        
        return false;
    }
     
     public Boolean CheckPassword(String p1 , String p2)
     {
         if(p1.equals(p2))
         {
             return true;
         }
         else
         {
              Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("User Registration");
           alert.setHeaderText("");
           alert.setContentText("Both Password are not matching");
           alert.showAndWait();
         }
         return false;
     }
     
     
       public Boolean checkUniqueUser(String username) throws ClassNotFoundException, SQLException
    {
        DBconection dBconection =new DBconection();
        Connection con =dBconection.connect();
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "select * from user";
        try
        {
        statement = con.createStatement();
        resultSet = statement.executeQuery(query);
        
      
        
        if(resultSet.next())
        {
            if(resultSet.getString("name").equals(username))
            {
               
           return true;
            }
           
        }
       
            
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
        }
       
        return false;
    }
     

    

    @FXML
    private void BtnNewUserSave(ActionEvent event) throws ClassNotFoundException, SQLException {
        
        if (validateText(TxtNewUserName.getText())&&validateAge(TxtNewUserAge.getText()))
        {
            if(CheckPassword(TxtNewUserPassword.getText(),TxtNewUserRePassword.getText()))
            {
                
              if(checkUniqueUser(TxtNewUserName.getText())==true)
              {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("ALARM");
           alert.setHeaderText("");
           alert.setContentText("Username already exist.Enter Another username");
           alert.showAndWait();
             
              }
              else
            {
         DBconection dbc = new DBconection();
       Connection conn = dbc.connect();
       
        PreparedStatement preparedStatement;
        ResulSet resulSet;
        
        String sql="insert into user (name,password,age,email,mobile) values (?,?,?,?,?)";
        
        try {
            
            
            
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, TxtNewUserName.getText());
            preparedStatement.setString(2, TxtNewUserPassword.getText());
            preparedStatement.setInt(3, Integer.parseInt(TxtNewUserAge.getText()));
            preparedStatement.setString(4, TxtNewUserEmail.getText());
            preparedStatement.setString(5, TxtNewUserContact.getText());
            
             preparedStatement.executeUpdate();
             
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("User Information");
           alert.setHeaderText("");
           alert.setContentText("Your Account has been created");
           alert.showAndWait();
           
           TxtNewUserName.clear();
           TxtNewUserPassword.clear();
           TxtNewUserRePassword.clear();
           TxtNewUserAge.clear();
           TxtNewUserEmail.clear();
           TxtNewUserContact.clear();
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
              }
        }
        }
    }

    @FXML
    private void BtnNewUserCancel(ActionEvent event) {
        
          TxtNewUserName.clear();
           TxtNewUserPassword.clear();
           TxtNewUserRePassword.clear();
           TxtNewUserAge.clear();
           TxtNewUserEmail.clear();
           TxtNewUserContact.clear();
    }

    @FXML
    private void HNewUserLogin(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
     Scene scene = new Scene(root);
      Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
         thestage.setScene(scene);
         thestage.show();
    }
    
}
