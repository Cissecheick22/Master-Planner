/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterplanner;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author cheick
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField TxtUsername;
    @FXML
    private TextField TxtPassword;
    @FXML
    private Hyperlink HUserRegistration;
    @FXML
    private Button BtnUserCancel;
    @FXML
    private Label LblUserIncorrect;
    @FXML
    private Button BtnUserLogin;
    
    public static String username;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
    }    

    @FXML
    private void HUserRegistration(ActionEvent event) throws IOException {
        
     Parent root = FXMLLoader.load(getClass().getResource("NewUserReg.fxml"));
     Scene scene = new Scene(root);
      Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
         thestage.setScene(scene);
         thestage.centerOnScreen();
         thestage.show();
     
    }

    @FXML
    private void BtnUserLogin(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        
       DBconection db = new DBconection();
       Connection con = db.connect();
       if(db.Login(TxtUsername.getText(),TxtPassword.getText()) == true)
       {
            username = TxtUsername.getText();
           
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
     Scene scene = new Scene(root);
      Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
      thestage.setScene(scene);
      thestage.setFullScreen(true);
      thestage.setResizable(true);
         thestage.show();
       }
       else
       {
           Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("Login");
           alert.setHeaderText("");
           alert.setContentText("Username or Password incorrect");
           alert.showAndWait();
        

           LblUserIncorrect.setText("User or Password incorrect");
           TxtUsername.clear();
           TxtPassword.clear();
           
       }
       
      
       
        
    }
  
    @FXML
    private void BtnUserCancel(ActionEvent event) {
        TxtUsername.clear();
        TxtPassword.clear();
    }

    private void NewUserLink(ActionEvent event) throws IOException {
        
         Parent root = FXMLLoader.load(getClass().getResource("NewUserRegistration.fxml"));
        Scene scene = new Scene(root);
      Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
         thestage.setScene(scene);
         thestage.show();
    }

    
}
