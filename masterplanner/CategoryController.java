/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterplanner;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
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
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static masterplanner.DBconection.JDBC_DRIVER;

/**
 * FXML Controller class
 *
 * @author cheick
 */
public class CategoryController implements Initializable {

    @FXML
    private TextField TxtCategoryId;
    @FXML
    private TextField TxtCategoryName;
    @FXML
    private TableView<CategoryDetails> CategoryTableView;
    @FXML
    private TableColumn<CategoryDetails, String> ColumCatId;
    @FXML
    private TableColumn<CategoryDetails, String> ColumCatName;
    
    private ObservableList <CategoryDetails> mydata ;
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
    private Button BtnCategorySave;
    @FXML
    private Button BtnCategoryUpdate;
    @FXML
    private Button BtnCategoryDelete;
    @FXML
    private Button BtnCategoryCancel;
    @FXML
    private Button BtnCategoryEdit;
    @FXML
    private Button BtnLogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Stage stage =MasterPlanner.stage;
            stage.centerOnScreen();
        try {
            RefreshTable();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }    
    
   
private void update()
{ 
  
   CategoryDetails CategoryData = new CategoryDetails(TxtCategoryId.getText(), TxtCategoryName.getText());
   mydata.add(CategoryData);
}
    
    @FXML
    public void BtnHome(ActionEvent event) throws IOException {
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
    public void BtnProfile(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
         Scene scene = new Scene(root);
         
         Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
         thestage.centerOnScreen();
         thestage.setScene(scene);
         thestage.centerOnScreen();
         thestage.show();
                
    }

    @FXML
    public void BtnActivity(ActionEvent event) throws IOException {
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
    public void BtnCategory(ActionEvent event) throws IOException {
       
        Parent root = FXMLLoader.load(getClass().getResource("Category.fxml"));
           Scene scene = new Scene(root);
           Node node= (Node)event.getSource();
           Stage stage=(Stage) node.getScene().getWindow();
          
           stage.setScene(scene);
             stage.centerOnScreen();
           stage.show();
    }

    @FXML
    public void BtnPriority(ActionEvent event) throws IOException {
        
           Parent root = FXMLLoader.load(getClass().getResource("Priority.fxml"));
           Scene scene = new Scene(root);
           Node node= (Node)event.getSource();
           Stage stage=(Stage) node.getScene().getWindow();
           
           stage.setScene(scene);
            stage.centerOnScreen();
           stage.show();
    }

    @FXML
    public void BtnActivityStatus(ActionEvent event) throws IOException {
        
           Parent root = FXMLLoader.load(getClass().getResource("Activity_status.fxml"));
           Scene scene = new Scene(root);
           Node node= (Node)event.getSource();
           Stage stage=(Stage) node.getScene().getWindow();
            
           stage.setScene(scene);
           stage.centerOnScreen();
           stage.show();
    }

    @FXML
    public void BtnStatistiques(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("Statistiques.fxml"));
           Scene scene = new Scene(root);
           Node node= (Node)event.getSource();
           Stage stage=(Stage) node.getScene().getWindow();
   
           stage.setScene(scene);
           stage.centerOnScreen();
           stage.show();
        
        
    }

    @FXML
    public void BtnSettings(ActionEvent event) throws IOException {      
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
    public void BtnCategorySave(ActionEvent event)  throws ClassNotFoundException, SQLException, IOException {
           
       DBconection dbc = new DBconection();
       Connection conn = dbc.connect();
       PreparedStatement preparedStatement = null;
       
      
            
       
        try {
             String sql ="insert into category (cname,username) values (?,?)";
             preparedStatement=conn.prepareStatement(sql);
             preparedStatement.setString(1, TxtCategoryName.getText());
             preparedStatement.setString(2, FXMLDocumentController.username);
             preparedStatement.executeUpdate();
             
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Category Information");
           alert.setHeaderText("");
           alert.setContentText("Category created");
           alert.showAndWait();
           
           TxtCategoryName.clear();
           
           RefreshTable();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //System.out.println("Incorrect Informations..Try again");
        }
       
        
       
       
       
        
       
       
        
       
        
    }

    @FXML
    public void BtnCategoryUpdate(ActionEvent event) throws ClassNotFoundException, SQLException {
        
          DBconection dbcd = new DBconection();
           Connection con = dbcd.connect();
           
           String sql ="update category set cname=? , username=?  where category_id=? ";
           
           
           if (validateText(TxtCategoryName.getText()))
           {
               
      
           
           PreparedStatement statement = con.prepareStatement(sql);
             statement.setString(1,TxtCategoryName.getText());
             statement.setString(2,FXMLDocumentController.username);
             statement.setString(3,TxtCategoryId.getText());
             
             statement.executeUpdate();
          
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Category Information");
           alert.setHeaderText("");
           alert.setContentText("Record Updated");
           alert.showAndWait();
           
          TxtCategoryId.clear();
          TxtCategoryName.clear();
          
          RefreshTable();
        
        
            }
          
    }

    @FXML
    public void BtnCategoryDelete(ActionEvent event) throws ClassNotFoundException, SQLException {
        
           CategoryDetails categories = CategoryTableView.getSelectionModel().getSelectedItem();
           
           DBconection dbcd = new DBconection();
           Connection con = dbcd.connect();
          
       
             
            PreparedStatement pstatement = con.prepareStatement("Delete from category where category_id = ? ");
            pstatement.setString(1, categories.getCategory_id());
            pstatement.executeUpdate();
            
             
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Category Information");
           alert.setHeaderText("");
           alert.setContentText("Record Deleted");
           alert.showAndWait();
           
           RefreshTable();
    }

     
     public void RefreshTable() throws ClassNotFoundException, SQLException
     {
          DBconection dbcd = new DBconection();
        Connection con = dbcd.connect();
        
        try
        {
             mydata = FXCollections.observableArrayList();
        
        Statement statement = con.createStatement();
        String username= FXMLDocumentController.username;
        ResultSet rs = statement.executeQuery("select * from category where username='" + username + "'");
        
        while (rs.next())
        {
             mydata.add(new CategoryDetails(rs.getString(1), rs.getString(2)));
        }
        
     
            ColumCatId.setCellValueFactory(new PropertyValueFactory<CategoryDetails,String>("category_id"));
            ColumCatName.setCellValueFactory(new PropertyValueFactory<CategoryDetails,String>("cname"));
            
            
            
            CategoryTableView.setItems(null);
            CategoryTableView.setItems(mydata);
        }
        
        catch(Exception e)
        {
            System.out.println("");
        }
     }
   

    

    @FXML
    public void BtnCategoryCancel(ActionEvent event) {
        TxtCategoryId.clear();
        TxtCategoryName.clear();
    }

    @FXML
    public void BtnCategoryEdit(ActionEvent event) throws ClassNotFoundException, SQLException {
        DBconection dbcd = new DBconection();
        Connection con = dbcd.connect();
        
        CategoryDetails categories = CategoryTableView.getSelectionModel().getSelectedItem();
                  
            TxtCategoryId.setText(categories.getCategory_id());
            TxtCategoryName.setText(categories.getCname());
    }
     
    @FXML
    public void ClickOnCategoryTableView(MouseEvent click) throws ClassNotFoundException, SQLException {
        
         if (click.getClickCount() ==2) {
             
        DBconection dbcd = new DBconection();
        Connection con = dbcd.connect();
        
        CategoryDetails categories = CategoryTableView.getSelectionModel().getSelectedItem();
                  
            TxtCategoryId.setText(categories.getCategory_id());
            TxtCategoryName.setText(categories.getCname());
         }
         }

    @FXML
    private void BtnLogout(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
         Scene scene = new Scene(root);
                  Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
         thestage.setScene(scene);
          thestage.centerOnScreen();
         thestage.show();
    
    }

   

        
}
