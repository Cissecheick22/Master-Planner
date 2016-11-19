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
public class PriorityController implements Initializable {

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
    private Button BtnActivityStatus;
    @FXML
    private Button BtnStatistiques;
    @FXML
    private Button BtnSettings;
    @FXML
    private TextField TxtPriorityId;
    @FXML
    private TextField TxtPriorityName;
    @FXML
    private Button BtnPrioritySave;
    @FXML
    private Button BtnPriorityUpdate;
    @FXML
    private Button BtnPriorityDelete;
    @FXML
    private TableView<PriorityDetails> PriorityTableView;
    @FXML
    private TableColumn<PriorityDetails,String> Priority_idColum;
    @FXML
    private TableColumn<PriorityDetails,String> PriorityNameColum;
    @FXML
    private Button BtnPriorityCancel;
    @FXML
    private Button BtnPriorityEdit;
    
    ObservableList<PriorityDetails> mydata;
    @FXML
    private Button BtnLogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        
        try {
            RefreshPriorityTable();
            // TODO
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PriorityController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PriorityController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void BtnActivityStatus(ActionEvent event) throws IOException {
        
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


    @FXML
    private void BtnPrioritySave(ActionEvent event) throws ClassNotFoundException, SQLException {
        
         DBconection dbp = new DBconection();
       Connection conn = dbp.connect();
       
       PreparedStatement preparedStatement = null;
     
              if (validateText(TxtPriorityName.getText()))
              {       
        try {
             String sql ="insert into priority (pname,username) values (?,?)";
         
             preparedStatement=conn.prepareStatement(sql);
             preparedStatement.setString(1, TxtPriorityName.getText());
             preparedStatement.setString(2,FXMLDocumentController.username);
             preparedStatement.executeUpdate();
             
             
            
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Priority Information");
           alert.setHeaderText("");
           alert.setContentText("Record Inserted");
           alert.showAndWait();
           
           
          
           TxtPriorityName.clear();
          
        
          
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
         RefreshPriorityTable();
       }
    }
    
    public void RefreshPriorityTable() throws ClassNotFoundException, SQLException
    {
           DBconection dbpt = new DBconection();
           Connection conn =dbpt.connect();
           
           mydata = FXCollections.observableArrayList();
           
           Statement statement = conn.createStatement();
           String username= FXMLDocumentController.username;
            ResultSet rs = statement.executeQuery("select * from priority where username = '" +username+"' ");
        
        while (rs.next())
        {
             mydata.add(new PriorityDetails(rs.getString(1), rs.getString(2)));
        }
        
          
          
          Priority_idColum.setCellValueFactory(new PropertyValueFactory<PriorityDetails,String>("priority_id"));
          PriorityNameColum.setCellValueFactory(new PropertyValueFactory<PriorityDetails,String>("pname"));
          
          PriorityTableView.setItems(null);
          PriorityTableView.setItems(mydata);
    }

    @FXML
    private void BtnPriorityUpdate(ActionEvent event) throws ClassNotFoundException, SQLException {
        
        DBconection dbcd = new DBconection();
           Connection con = dbcd.connect();
            if (validateText(TxtPriorityName.getText()))
               {
           try {
             
           String sql ="update priority set pname=?, username=? where priority_id=?";
           
           
           
           PreparedStatement statement = con.prepareStatement(sql);
             statement.setString(1,TxtPriorityName.getText());
             statement.setString(2, FXMLDocumentController.username);
             statement.setString(3,TxtPriorityId.getText());
             
             statement.executeUpdate();
             
             
             
             
             
          
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Priority Information");
           alert.setHeaderText("");
           alert.setContentText("Record Updated");
           alert.showAndWait();
           
          TxtPriorityId.clear();
          TxtPriorityName.clear();
        } catch (Exception e) {
            
               System.out.println(e.getMessage());
               
        }
          
          
       RefreshPriorityTable();
       }
     }

    @FXML
    private void BtnPriorityDelete(ActionEvent event) throws ClassNotFoundException, SQLException {
        
         DBconection dbcd = new DBconection();
           Connection con = dbcd.connect();
           
          PriorityDetails priorityDetails =   PriorityTableView.getSelectionModel().getSelectedItem();
           
           String sql ="delete from priority where priority_id=? ";
           
           PreparedStatement statement = con.prepareStatement(sql);
             statement.setString(1,priorityDetails.getPriority_id());
             
             
             statement.executeUpdate();
          
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Priority Information");
           alert.setHeaderText("");
           alert.setContentText("Record Deleted");
           alert.showAndWait();
           
          TxtPriorityId.clear();
          TxtPriorityName.clear();
          
          RefreshPriorityTable();
    }

    @FXML
    private void BtnPriorityCancel(ActionEvent event) {
        
        TxtPriorityId.clear();
        TxtPriorityName.clear();
    }

    @FXML
    private void BtnPriorityEdit(ActionEvent event) throws ClassNotFoundException, SQLException {
        
        DBconection dbp = new DBconection();
        Connection con =dbp.connect();
        
        try {
              
               
               PriorityDetails priorityDetails = PriorityTableView.getSelectionModel().getSelectedItem();
               
               TxtPriorityId.setText(priorityDetails.getPriority_id());
               TxtPriorityName.setText(priorityDetails.getPname());
        } catch (Exception e) {
            
            System.out.println("");
        }
        
        
    }

    @FXML
    private void ClickPriorityTable(MouseEvent click) throws ClassNotFoundException, SQLException {
        
        if (click.getClickCount()==2)
        {
             
        DBconection dbp = new DBconection();
        Connection con =dbp.connect();
        
        try {
              
               
               PriorityDetails priorityDetails = PriorityTableView.getSelectionModel().getSelectedItem();
               
               TxtPriorityId.setText(priorityDetails.getPriority_id());
               TxtPriorityName.setText(priorityDetails.getPname());
        } catch (Exception e) {
            
            System.out.println("");
        }
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
