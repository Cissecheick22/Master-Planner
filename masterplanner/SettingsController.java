
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cheick
 */
public class SettingsController implements Initializable {

    @FXML
    private Button RPriority;
    @FXML
    private Button RCategory;
    @FXML
    private Button RActivity;
    @FXML
    private Button RStatus;
    @FXML
    private Button goStatistiques;
    @FXML
    private Button goActivity;
    @FXML
    private Button gohome;
    @FXML
    private Button RAlarms;
    @FXML
    private Button goprofile;
    
    String username = FXMLDocumentController.username;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void RCategory(ActionEvent event) throws SQLException, ClassNotFoundException {
        
        // Create statement object
        
        DBconection dBconection = new DBconection();
        Connection conn = dBconection.connect();
              
        try {
            Statement stmt = conn.createStatement();

// Set auto-commit to false
conn.setAutoCommit(false);

stmt.addBatch("Delete  from category where username='"+ username + "' ");
//stmt.addBatch("Truncate table category");
stmt.addBatch( "INSERT INTO category (cname,username) VALUES('WORK','"+ username+"')" );
stmt.addBatch( "INSERT INTO category (cname,username) VALUES('MEETINGS','"+ username+"')" );
stmt.addBatch( "INSERT INTO category (cname,username) VALUES('SPORTS','"+ username+"')" );
 



// Create an int[] to hold returned values
int[] count = stmt.executeBatch();

//Explicitly commit statements to apply changes
conn.commit();
Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Settings");
           alert.setHeaderText("");
           alert.setContentText("Settings have been reset !!!");
           alert.showAndWait();

        } catch (Exception e) {
            
            System.out.println(e.getMessage());
        }
        
        
        
conn.close();
        
    }

    @FXML
    private void RActivity(ActionEvent event) throws SQLException, ClassNotFoundException {
        
         // Create statement object
        
        DBconection dBconection = new DBconection();
        Connection conn = dBconection.connect();
              
        try {
            Statement stmt = conn.createStatement();

// Set auto-commit to false
conn.setAutoCommit(false);

stmt.addBatch("Delete  from activity where username='"+ username + "'");
//tmt.addBatch("Truncate table activity_status");





// Create an int[] to hold returned values
int[] count = stmt.executeBatch();

//Explicitly commit statements to apply changes
conn.commit();
Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Settings");
           alert.setHeaderText("");
           alert.setContentText("Settings have been reset !!!");
           alert.showAndWait();

        } catch (Exception e) {
            
            System.out.println(e.getMessage());
        }
        
        
        
conn.close();
    }

    @FXML
    private void RStatus(ActionEvent event) throws SQLException, ClassNotFoundException {
        
         // Create statement object
        
        DBconection dBconection = new DBconection();
        Connection conn = dBconection.connect();
              
        try {
            Statement stmt = conn.createStatement();

// Set auto-commit to false
conn.setAutoCommit(false);

stmt.addBatch("Delete  from activity_status where username='"+ username + "'");
//tmt.addBatch("Truncate table activity_status");
stmt.addBatch( "INSERT INTO activity_status (as_name,username) VALUES('BEFORE','"+ username+"')" );
stmt.addBatch( "INSERT INTO activity_status (as_name,username) VALUES('ONGOING','"+ username+"')" );
stmt.addBatch( "INSERT INTO activity_status (as_name,username) VALUES('DONE','"+ username+"')" );




// Create an int[] to hold returned values
int[] count = stmt.executeBatch();

//Explicitly commit statements to apply changes
conn.commit();
Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Settings");
           alert.setHeaderText("");
           alert.setContentText("Settings have been reset !!!");
           alert.showAndWait();

        } catch (Exception e) {
            
            System.out.println(e.getMessage());
        }
        
        
        
conn.close();
        
    }

    @FXML
    private void RPriority(ActionEvent event) throws ClassNotFoundException, SQLException {
        // Create statement object
        
        DBconection dBconection = new DBconection();
        Connection conn = dBconection.connect();
              
        try {
            Statement stmt = conn.createStatement();

// Set auto-commit to false
conn.setAutoCommit(false);

stmt.addBatch("Delete  from priority where username='"+ username + "'");
//stmt.addBatch("Truncate table priority");
stmt.addBatch( "INSERT INTO priority (pname,username) VALUES('HIGH','"+ username+"')" );
stmt.addBatch( "INSERT INTO priority (pname,username) VALUES('MEDUIM','"+ username+"')" );
stmt.addBatch( "INSERT INTO priority (pname,username) VALUES('LOW','"+ username+"')" );




// Create an int[] to hold returned values
int[] count = stmt.executeBatch();

//Explicitly commit statements to apply changes
conn.commit();
Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Settings");
           alert.setHeaderText("");
           alert.setContentText(" Settings have been reset !!!");
           alert.showAndWait();

        } catch (Exception e) {
            
            System.out.println(e.getMessage());
        }
        
        
        
conn.close();
        
    }

    @FXML
    private void goStatistiques(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Statistiques.fxml"));
           Scene scene = new Scene(root);
           Node node= (Node)event.getSource();
           Stage stage=(Stage) node.getScene().getWindow();
           stage.setScene(scene);
            stage.centerOnScreen();
           stage.show();
    
    }

    @FXML
    private void goActivity(ActionEvent event) throws IOException {
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
    private void gohome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
           Scene scene = new Scene(root);
           Node node= (Node)event.getSource();
           Stage stage=(Stage) node.getScene().getWindow();
           stage.setScene(scene);
           stage.setFullScreen(true);
            stage.centerOnScreen();
           stage.show();
    
    }

    @FXML
    private void RAlarms(ActionEvent event) {
    }

    @FXML
    private void goprofile(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
           Scene scene = new Scene(root);
           Node node= (Node)event.getSource();
           Stage stage=(Stage) node.getScene().getWindow();
           stage.setScene(scene);
            stage.centerOnScreen();
           stage.show();
    
    }
    
}
