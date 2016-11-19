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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cheick
 */
public class Activity_statusController implements Initializable {

    @FXML
    private Button BtnActivityStatus;
    @FXML
    private TableColumn<PriorityDetails, String> ActivityStatusIdColum;
    @FXML
    private TableColumn<PriorityDetails, String> ActivityStatusNameColum;
    @FXML
    private TextField TxtActivityStatusId;
    @FXML
    private TextField TxtActivityStatusName;
    @FXML
    private Button BtnActivityStatusSave;
    @FXML
    private Button BtnActivityStatusUpdate;
    @FXML
    private Button BtnAcitvityStatusDelete;
    @FXML
    private Button BtnActivityStatusCancel;
    @FXML
    private Button BtnActivityStatusEdit;
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
    private Button BtnStatistiques;
    @FXML
    private Button BtnSettings;
    
    ObservableList<ActivityStatusDetails> mydata;
    @FXML
    
    private TableView<ActivityStatusDetails> ActivityStatusTableView;
    @FXML
    private Button BtnLogout;

    
     String username =  FXMLDocumentController.username;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            RefreshActivityStatusTable();
            // TODO
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Activity_statusController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Activity_statusController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private Boolean validateText(String text)
    {
        Pattern p = Pattern.compile("[a-zA-Z]+");
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
           alert.setContentText("Name is incorrect.Please Enter a valid text");
           alert.showAndWait();
        }
        
        
        return false;
    }


    public void RefreshActivityStatusTable() throws ClassNotFoundException, SQLException
    {
        
         DBconection dbpt = new DBconection();
           Connection conn =dbpt.connect();
           
           mydata = FXCollections.observableArrayList();
                   
           
           Statement statement = conn.createStatement();
           String username=FXMLDocumentController.username;
            ResultSet rs = statement.executeQuery("select * from activity_status where username= '"+username+"'");
        
        while (rs.next())
        {
             mydata.add(new ActivityStatusDetails(rs.getString(1), rs.getString(2)));
        }
        
          
          
          ActivityStatusIdColum.setCellValueFactory(new PropertyValueFactory<PriorityDetails,String>("as_id"));
          ActivityStatusNameColum.setCellValueFactory(new PropertyValueFactory<PriorityDetails,String>("as_name"));
          
          ActivityStatusTableView.setItems(null);
          ActivityStatusTableView.setItems(mydata);
        
    }
    
       @FXML
    private void BtnActivityStatusSave(ActionEvent event) throws ClassNotFoundException, SQLException {
        
         
         DBconection dbp = new DBconection();
       Connection conn = dbp.connect();
       
       PreparedStatement preparedStatement = null;
     
          if (validateText(TxtActivityStatusName.getText()))
              {     
        try {
             String sql ="insert into activity_status (as_name,username) values (?,?)";
         
             preparedStatement=conn.prepareStatement(sql);
             preparedStatement.setString(1, TxtActivityStatusName.getText());
             preparedStatement.setString(2,FXMLDocumentController.username);
             preparedStatement.executeUpdate();
             
             
            
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Activitystatus Information");
           alert.setHeaderText("");
           alert.setContentText("Record Inserted");
           alert.showAndWait();
           
           
          
           TxtActivityStatusName.clear();
          
        
          
        } catch (Exception ourException) {
            
            System.out.println(ourException.getMessage());
            System.out.println(ourException.getMessage());
            ourException.printStackTrace();
        }
        
        finally
        {
           preparedStatement.close();
           conn.close();
        }
         RefreshActivityStatusTable();
              }
    }


    @FXML
    private void BtnActivityStatus(ActionEvent event) {
    }


    @FXML
    private void BtnActivityStatusUpdate(ActionEvent event) throws ClassNotFoundException, SQLException {
        
        
         DBconection dbcd = new DBconection();
           Connection con = dbcd.connect();
           if (validateText(TxtActivityStatusName.getText()))
              {     
           String sql ="update activity_status set as_name=?,username=? where as_id=? ";
           
           PreparedStatement statement = con.prepareStatement(sql);
             statement.setString(1,TxtActivityStatusName.getText());
             statement.setString(2,FXMLDocumentController.username);
             statement.setString(3,TxtActivityStatusId.getText());
             
             statement.executeUpdate();
          
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Activity status Information");
           alert.setHeaderText("");
           alert.setContentText("Record Updated");
           alert.showAndWait();
           
          TxtActivityStatusId.clear();
          TxtActivityStatusName.clear();
          
         RefreshActivityStatusTable();
        
              }
    }

    @FXML
    private void BtnAcitvityStatusDelete(ActionEvent event) throws ClassNotFoundException, SQLException {
        
          ActivityStatusDetails activityStatusDetails = ActivityStatusTableView.getSelectionModel().getSelectedItem();
           
           DBconection dbcd = new DBconection();
           Connection con = dbcd.connect();
          
       
             
            PreparedStatement pstatement = con.prepareStatement("Delete from activity_status where as_id = ? ");
            pstatement.setString(1, activityStatusDetails.getAs_id());
            pstatement.executeUpdate();
            
             
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Activity status Information");
           alert.setHeaderText("");
           alert.setContentText("Record Deleted");
           alert.showAndWait();
           
           RefreshActivityStatusTable();
    }

    @FXML
    private void BtnActivityStatusCancel(ActionEvent event) {
        
        TxtActivityStatusId.clear();
        TxtActivityStatusName.clear();
    }

    @FXML
    private void BtnActivityStatusEdit(ActionEvent event) throws ClassNotFoundException, SQLException {
        
         DBconection dbcd = new DBconection();
        Connection con = dbcd.connect();
        
      ActivityStatusDetails activityStatusDetails = ActivityStatusTableView.getSelectionModel().getSelectedItem();
                  
            TxtActivityStatusId.setText(activityStatusDetails.getAs_id());
            TxtActivityStatusName.setText(activityStatusDetails.getAs_name());
    }

    @FXML
    private void BtnHome(ActionEvent event) throws IOException {
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
    private void BtnProfile(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
         Scene scene = new Scene(root);
         
         Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
         thestage.setScene(scene);
          thestage.centerOnScreen();
         thestage.show();

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
    private void ClickActivityStatusTableView(MouseEvent click) throws ClassNotFoundException, SQLException {
        
        if (click.getClickCount()==2)
        {
              DBconection dbcd = new DBconection();
        Connection con = dbcd.connect();
        
      ActivityStatusDetails activityStatusDetails = ActivityStatusTableView.getSelectionModel().getSelectedItem();
                  
            TxtActivityStatusId.setText(activityStatusDetails.getAs_id());
            TxtActivityStatusName.setText(activityStatusDetails.getAs_name());
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
