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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cheick
 */
public class ProfileController implements Initializable {

    @FXML
    private Button BtnHome;
    @FXML
    private Button BtnProfile;
    @FXML
    private Button BtnActivity;
    @FXML
    private Button BtnCategory;
    @FXML
    private Button BtnPriority;
    @FXML
    private Button BtnActivitystatus;
    @FXML
    private Button BtnStatistiques;
    @FXML
    private Button BtnSettings;
    @FXML
    private TextField TxtUserId;
    @FXML
    private TextField TxtUserName;
    @FXML
    private TextField TxtPassword;
    @FXML
    private TextField TxtAge;
    @FXML
    private TextField TxtEmail;
    @FXML
    private TextField TxtContactNumber;
    @FXML
    private Button BtnPUpdate;
    @FXML
    private Button BtnPCancel;
    @FXML
    private Button BtnPLoad;
    @FXML
    private Button BtnLogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void BtnHome(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
           Scene scene = new Scene(root);
           Node node= (Node)event.getSource();
           Stage stage=(Stage) node.getScene().getWindow();
           stage.setFullScreen(true);
           stage.centerOnScreen();
           stage.setScene(scene);
           stage.show();
    
    }

    @FXML
    private void BtnProfile(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
           Scene scene = new Scene(root);
           Node node= (Node)event.getSource();
           Stage stage=(Stage) node.getScene().getWindow();
           stage.setScene(scene);
            
           stage.centerOnScreen();
           stage.show();
    
    }

    @FXML
    private void BtnActivity(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("Activity.fxml"));
           Scene scene = new Scene(root);
           Node node= (Node)event.getSource();
           Stage stage=(Stage) node.getScene().getWindow();
           stage.setScene(scene);
           stage.setFullScreen(true);
           stage.centerOnScreen();
           stage.show();
    
    }

    @FXML
    private void BtnCategory(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("Category.fxml"));
           Scene scene = new Scene(root);
           Node node= (Node)event.getSource();
           Stage stage=(Stage) node.getScene().getWindow();
           stage.setScene(scene);
            
           stage.centerOnScreen();
           stage.show();
    
    }

    @FXML
    private void BtnPriority(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("Priority.fxml"));
           Scene scene = new Scene(root);
           Node node= (Node)event.getSource();
           Stage stage=(Stage) node.getScene().getWindow();
           stage.setScene(scene);
           stage.centerOnScreen();
           stage.show();
    
    }

    @FXML
    private void BtnActivitystatus(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("Activity_status.fxml"));
           Scene scene = new Scene(root);
           Node node= (Node)event.getSource();
           Stage stage=(Stage) node.getScene().getWindow();
           stage.setScene(scene);
           stage.centerOnScreen();
           stage.show();
    
    }

    @FXML
    private void BtnStatistiques(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("Statistiques.fxml"));
           Scene scene = new Scene(root);
           Node node= (Node)event.getSource();
           Stage stage=(Stage) node.getScene().getWindow();
           stage.setScene(scene);
           stage.centerOnScreen();
           stage.show();
    
    }

    @FXML
    private void BtnSettings(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
           Scene scene = new Scene(root);
           Node node= (Node)event.getSource();
           Stage stage=(Stage) node.getScene().getWindow();
           stage.setScene(scene);
           stage.centerOnScreen();
           stage.show();
    
    }

    @FXML
    private void BtnPUpdate(ActionEvent event) throws ClassNotFoundException, SQLException {
        
         DBconection dbcd = new DBconection();
           Connection con = dbcd.connect();
           
           String sql ="update user set name=? , password=? ,age=? ,email=?,mobile=? where user_id=? ";
           
           try {
            
           PreparedStatement statement = con.prepareStatement(sql);
           
           
                   
                   
                   
             statement.setString(1,TxtUserName.getText());
             statement.setString(2,TxtPassword.getText());
              statement.setInt(3, Integer.parseInt(TxtAge.getText()));
            statement.setString(4, TxtEmail.getText());
            statement.setString(5, TxtContactNumber.getText());
            statement.setString(6,TxtUserId.getText());
             statement.executeUpdate();
             
             
          
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("User Information");
           alert.setHeaderText("");
           alert.setContentText("Record Updated");
           alert.showAndWait();
           
            TxtUserName.clear();
                TxtPassword.clear();
     
                 TxtAge.clear();
                  TxtEmail.clear();
                   TxtContactNumber.clear();
           
        
          
          
        } catch (Exception e) {
               System.out.println(e.getMessage());
               e.printStackTrace();
        }
               
       
    }

    @FXML
    private void BtnPCancel(ActionEvent event) {
        
        TxtUserName.clear();
                TxtPassword.clear();
     
                 TxtAge.clear();
                  TxtEmail.clear();
                   TxtContactNumber.clear();
    }

    @FXML
    private void BtnPLoad(ActionEvent event) throws ClassNotFoundException, SQLException {
        
         DBconection dbc = new DBconection();
         Connection con = dbc.connect();
         String username =FXMLDocumentController.username;
         
         String sql="select * from user where name='" + username + "' ";
 
         
         try {
               Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        
           while(rs.next())
           {
               TxtUserId.setText(rs.getString("user_id"));
               TxtUserName.setText(rs.getString("name"));
                TxtPassword.setText(rs.getString("password"));
                 TxtAge.setText(rs.getString("age"));
                  TxtEmail.setText(rs.getString("email"));
                   TxtContactNumber.setText(rs.getString("mobile"));
                  
           }
        } catch (Exception e) {
            
             System.out.println(e.getMessage());
        }
    }

    @FXML
    private void BtnLogout(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
         Scene scene = new Scene(root);
         
         Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
         thestage.setScene(scene);
         thestage.show();
    
    }
    
}
