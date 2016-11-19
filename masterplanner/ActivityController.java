/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterplanner;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cheick
 */
public class ActivityController implements Initializable {

    @FXML
    private TextField TxtActivityId;
    @FXML
    private TextField TxtActivityName;
    @FXML
    private TextArea TxtAcitivityDescription;
    @FXML
    private ComboBox<String> CmbPriorityName;
    @FXML
    private ComboBox<String> CmbCategoryName;
    @FXML
    private ComboBox<String> CmbActivityStatus;
    @FXML
    private Button BtnActivitySave;
    @FXML
    private Button BtnActivityUpdate;
    @FXML
    private Button BtnActivityDelete;
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
    private TableView<ActivityDetails> ActivityTableView;
    @FXML
    private TableColumn<ActivityDetails,String> ActivityIdColum;
    @FXML
    private TableColumn<ActivityDetails,String> ActivityNameColum;
    @FXML
    private TableColumn<ActivityDetails,String> DescriptionColum;
    @FXML
    private TableColumn<ActivityDetails,String> CurrentDateColum;
    @FXML
    private TableColumn<ActivityDetails,String> ActivityDateColum;
    @FXML
    private TableColumn<ActivityDetails,String> ActivityTimeColum;
    @FXML
    private TableColumn<ActivityDetails,String> ActivityEndDateColum;
    @FXML
    private TableColumn<ActivityDetails,String> ActivityPriorityColum;
    @FXML
    private TableColumn<ActivityDetails,String> ActivityCategoryColum;
    @FXML
    private TableColumn<ActivityDetails,String> ActivityStatusColum;
    
    ObservableList Hour= FXCollections.observableArrayList();
    ObservableList Minutes= FXCollections.observableArrayList();
   
    
    ObservableList mydata= FXCollections.observableArrayList();
    ObservableList mydata1= FXCollections.observableArrayList();
    ObservableList mydata2=FXCollections.observableArrayList(); 
    
    ObservableList mydataLoadActivity= FXCollections.observableArrayList();
    @FXML
    private DatePicker TxtActivityDate;
    @FXML
    private DatePicker TxtActivityEndDate;
    @FXML
    private ComboBox<?> cmbASHour;
    @FXML
    private ComboBox<?> cmbASMn;
    @FXML
    private ComboBox<?> cmbAEHour;
    @FXML
    private ComboBox<?> cmbAEMn;
    @FXML
    private TableColumn<?, ?> ActivityEndTimeColum;
    @FXML
    private Button BtnActivityCancel;
    private TextField TxtSearchActivityID;
    
    final ObservableList<ActivityDetails> DataSearch = FXCollections.observableArrayList();
    
    FilteredList<ActivityDetails> filtereddata = new FilteredList<>(DataSearch , e->true);
    @FXML
    private Button BtnLoadActivityData;
    @FXML
    private Button BtnLogout;
    
    String username = FXMLDocumentController.username;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            LoadActivityTable();
             TxtActivityDate.setValue(LocalDate.now());
            TxtActivityEndDate.setValue(LocalDate.now());
            LoadcomboPriorityName();
           LoadcomboCategoryName();
           LoadcomboActivityStatusName();
            
            LoadHour();
            LoadMinute();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, ex);
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
      
      private Boolean validateDate(String stdate , String enddate)
      {
          if (true)
              return true;
          else
          {
              Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Activity ");
           alert.setHeaderText("");
           alert.setContentText("Starting date Should be less than Ending date...");
           alert.showAndWait();
          }
          return false;
      }
      
    
    public void LoadcomboPriorityName() throws ClassNotFoundException, SQLException
    {
        DBconection dbcombo = new DBconection();
        Connection conn = dbcombo.connect();
        
        Statement statement = null;
        ResultSet resultSet = null;
        
           
        try {
             
            //String sql = "SELECT DISTINCT priority.pname from activity,priority where activity.priority_id=priority.priority_id order by priority.pname";
            String sql = "SELECT DISTINCT pname FROM priority  where username='"+ username +"' ";      
            
              statement = conn.createStatement();
               resultSet = statement.executeQuery(sql);
             
              while(resultSet.next())
              {
                 mydata.add(resultSet.getString("pname"));
              }
              
              CmbPriorityName.setItems(mydata);
              
             
        } catch (Exception e) {
        }
        finally
        {
            statement.close();
            resultSet.close();
            conn.close();
            
        }
    }
    
    
    public void LoadcomboCategoryName() throws ClassNotFoundException, SQLException
    {
        DBconection dbcombo = new DBconection();
        Connection conn = dbcombo.connect();
        
        Statement statement = null;
        ResultSet resultSet = null;
        
        
        try {
             
                //String sql = "SELECT DISTINCT category.cname from activity,category where activity.category_id=category.category_id order by category.cname";
                  String sql="Select Distinct cname from category where username= '"+username+"'";  
            
              statement = conn.createStatement();
               resultSet = statement.executeQuery(sql);
             
              while(resultSet.next())
              {
                 mydata1.add(resultSet.getString("cname"));
              }
              
              CmbCategoryName.setItems(mydata1);
              
             
        } catch (Exception e) {
        }
        finally
        {
            conn.close();
            
        }
    }
    
    private void LoadHour()
    {
        for ( int i =1 ;i<=24;i++)
        {
            Hour.add(i);
        }
        cmbASHour.setItems(null);
        cmbAEHour.setItems(null);
        cmbASHour.setItems(Hour);
        cmbAEHour.setItems(Hour);
    }
    private void LoadMinute()
    {
         for ( int i =00 ;i<=59;i++)
        {
            Minutes.add(i);
        }
        cmbASMn.setItems(null);
        cmbAEMn.setItems(null);
        cmbASMn.setItems(Minutes);
        cmbAEMn.setItems(Minutes);
        
    }
    
    
    public void LoadcomboActivityStatusName() throws ClassNotFoundException, SQLException
    {
        DBconection dbcombo = new DBconection();
        Connection conn = dbcombo.connect();
        
        Statement statement = null;
        ResultSet resultSet = null;
        
        
        try {
             
           // String sql = "select DISTINCT  activity_status.as_name from activity,activity_status WHERE\n" +
               //"activity.as_id=activity_status.as_id order by activity_status.as_name";
                 String sql="Select Distinct as_name from activity_status where username= '"+username+"'";   
            
              statement = conn.createStatement();
               resultSet = statement.executeQuery(sql);
             
              while(resultSet.next())
              {
                 mydata2.add(resultSet.getString("as_name"));
              }
              
              CmbActivityStatus.setItems(mydata2);
              
             
        } catch (Exception e) {
        }
        finally
        {
            statement.close();
            resultSet.close();
            conn.close();
            
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

    @FXML
    private void BtnStatistiques(TouchEvent event) {
    }

    @FXML
    private void ActivityTableView(MouseEvent click) throws ClassNotFoundException, SQLException {
        
        if (click.getClickCount()==2)
        {
            DBconection dbcd = new DBconection();
        Connection con = dbcd.connect();
        
      ActivityDetails activityDetails = ActivityTableView.getSelectionModel().getSelectedItem();
                  
            TxtActivityId.setText(activityDetails.getActivity_id());
            TxtActivityName.setText(activityDetails.getAname());
            TxtActivityDate.setValue(LocalDate.now());
            TxtActivityEndDate.setValue(LocalDate.now());
            TxtAcitivityDescription.setText(activityDetails.getDescription());
            
             
   }
    }
    
    
    private void ClearFields()
    {
        TxtActivityId.clear();
        TxtActivityName.clear();
        TxtAcitivityDescription.clear();
        TxtActivityDate.getEditor().clear();
        TxtActivityEndDate.getEditor().clear();
       cmbASHour.getSelectionModel().clearSelection();
       cmbAEHour.getSelectionModel().clearSelection();
       cmbAEMn.getSelectionModel().clearSelection();
       cmbASMn.getSelectionModel().clearSelection();
        
    }
    
    private String getnameFromid( String idtoName) throws ClassNotFoundException, SQLException
    {
        DBconection dbpt = new DBconection();
           Connection conn =dbpt.connect();
           String NameFromId ="";
          String sql = "select pname from priority where priority_id='" + idtoName + "'  and username= '"+username+"' ";
          try {
             Statement statement = conn.createStatement();
          ResultSet resultSet = statement.executeQuery(sql);
          
          
          while (resultSet.next())
          {
              NameFromId = resultSet.getString(1);
          }
          
          return NameFromId ;
        } catch (Exception e) {
              System.out.println(e.getMessage());
        }
         return NameFromId;
    }
    
    
    private String getnameFromCategoryid( String catidtoName) throws ClassNotFoundException, SQLException
    {
        DBconection dbpt = new DBconection();
           Connection conn =dbpt.connect();
           String CatNameFromId ="";
          String sql = "select cname from category where category_id='" + catidtoName + "' and username= '"+username+"'  ";
          try {
             Statement statement = conn.createStatement();
          ResultSet resultSet = statement.executeQuery(sql);
          
          
          while (resultSet.next())
          {
              CatNameFromId = resultSet.getString(1);
          }
          
          return CatNameFromId ;
        } catch (Exception e) {
              System.out.println(e.getMessage());
        }
         return CatNameFromId;
    }
    
      private String getnameFromAsid( String AsidtoName) throws ClassNotFoundException, SQLException
    {
        DBconection dbpt = new DBconection();
           Connection conn =dbpt.connect();
           String AsNameFromId ="";
          String sql = "select as_name from activity_status where as_id='" + AsidtoName + "' and username= '"+username+"'  ";
          try {
             Statement statement = conn.createStatement();
          ResultSet resultSet = statement.executeQuery(sql);
          
          
          while (resultSet.next())
          {
              AsNameFromId = resultSet.getString(1);
          }
          
          return AsNameFromId ;
        } catch (Exception e) {
              System.out.println(e.getMessage());
        }
         return AsNameFromId;
    }
    
    
    public void LoadActivityTable() throws ClassNotFoundException, SQLException
    {
        DBconection dbpt = new DBconection();
           Connection conn =dbpt.connect();
           
           mydataLoadActivity = FXCollections.observableArrayList();
                   
           
           Statement statement = conn.createStatement();
           ResultSet rs = statement.executeQuery("select * from activity where username= '"+username+"'");
        
        while (rs.next())
        {
             //mydataLoadActivity.add( new ActivityDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)  ) );
             mydataLoadActivity.add(new ActivityDetails(rs.getString(1), rs.getString(2),rs.getString(3) , rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7), rs.getString(8),getnameFromid(rs.getString(9)),getnameFromCategoryid(rs.getString(10)), getnameFromAsid(rs.getString(11))) );
             
        }
        
          
          
          ActivityIdColum.setCellValueFactory(new PropertyValueFactory<ActivityDetails,String>("activity_id"));
          ActivityNameColum.setCellValueFactory(new PropertyValueFactory<ActivityDetails,String>("aname"));
          DescriptionColum.setCellValueFactory(new PropertyValueFactory<ActivityDetails,String>("description"));
          CurrentDateColum.setCellValueFactory(new PropertyValueFactory<ActivityDetails,String>("currentdate"));
          ActivityDateColum.setCellValueFactory(new PropertyValueFactory<ActivityDetails,String>("adate"));
          ActivityEndDateColum.setCellValueFactory(new PropertyValueFactory<ActivityDetails,String>("enddate"));
          ActivityTimeColum.setCellValueFactory(new PropertyValueFactory<ActivityDetails,String>("ac_startingtime"));
          ActivityEndTimeColum.setCellValueFactory(new PropertyValueFactory<>("ac_endingtime"));
          
          
          ActivityPriorityColum.setCellValueFactory(new PropertyValueFactory<ActivityDetails,String>("priority_id"));
          ActivityCategoryColum.setCellValueFactory(new PropertyValueFactory<ActivityDetails,String>("category_id"));
          ActivityStatusColum.setCellValueFactory(new PropertyValueFactory<ActivityDetails,String>("as_id"));
          
          
          
          ActivityTableView.setItems(null);
          ActivityTableView.setItems(mydataLoadActivity);
          
           TxtActivityDate.setValue(LocalDate.now());
            TxtActivityEndDate.setValue(LocalDate.now());
    }
    
    private int GetPriorityIdFromCombo() throws ClassNotFoundException, SQLException
    {
         String CmbPriorityid = CmbPriorityName.getValue();
         
         DBconection dbconn = new DBconection();
         Connection conn =  dbconn.connect();
         Statement statement = null;
         ResultSet rs = null;
         String sql = "SELECT priority.priority_id FROM priority WHERE priority.pname= '" + CmbPriorityid +"' and username= '"+username+"' ";
      
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            int Pid = 0;
           while(rs.next())
             Pid = rs.getInt(1);
           
           return Pid;
           }
    
     private int GetCategoryIDFromCombo() throws ClassNotFoundException, SQLException
    {
         String Cmbcategoryid =CmbCategoryName.getValue();
         
         DBconection dbconn = new DBconection();
         Connection conn =  dbconn.connect();
         Statement statement = null;
         ResultSet rs = null;
         String sql = "SELECT category.category_id FROM category WHERE category.cname='" + Cmbcategoryid + "' and username= '"+username+"' ";
      
            statement = conn.createStatement();
            
            rs = statement.executeQuery(sql);
            int CatId= 0;
           while(rs.next())
             CatId = rs.getInt(1);
           
           return CatId;
    }

     
     
     private int GetActivityStatusIDFromName() throws ClassNotFoundException, SQLException
    {
         String CmbAsid =CmbActivityStatus.getValue();
         
         DBconection dbconn = new DBconection();
         Connection conn =  dbconn.connect();
         Statement statement = null;
         ResultSet rs = null;
         String sql = "SELECT as_id FROM activity_status WHERE as_name='" + CmbAsid + "' and username= '"+username+"' ";
      
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            int As_id = 0;
           while(rs.next())
             As_id = rs.getInt(1);
           
           return As_id;
    }


    @FXML
    private void BtnActivitySave(ActionEvent event) throws ClassNotFoundException, SQLException {
        
  
         
       // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Calendar cal = Calendar.getInstance();
        
        String FirstStartingHour =cmbASHour.getValue().toString();
        String FirstStartingMn = cmbASMn.getValue().toString();
        
        
        String ActivityStartingtime = String.join(":", FirstStartingHour,FirstStartingMn);
        
             
        String SecondStartingHour =cmbAEHour.getValue().toString();
        String SecondStartingMn = cmbAEMn.getValue().toString();
        
        String ActivityEndingtime = String.join(":", SecondStartingHour,SecondStartingMn);
        
        
        
        //System.out.println(time);
    
         DBconection dbconn = new DBconection();
         Connection conn =  dbconn.connect();
         PreparedStatement preparedStatement = null;
         
         String sql = " insert into activity (aname,description,adate,enddate,ac_startingtime,ac_endingtime,priority_id,category_id,as_id,username)"
                 + " values (?,?,?,?,?,?,?,?,?,?)";
         
        if(validateText(TxtActivityName.getText()))
        {
         
         try {
             
             preparedStatement = conn.prepareStatement(sql);
             preparedStatement.setString(1,TxtActivityName.getText());
             preparedStatement.setString(2,TxtAcitivityDescription.getText());
             preparedStatement.setString(3,TxtActivityDate.getEditor().getText());
             preparedStatement.setString(4,TxtActivityEndDate.getEditor().getText());
             preparedStatement.setString(5,ActivityStartingtime);
             preparedStatement.setString(6,ActivityEndingtime);
             preparedStatement.setInt(7,GetPriorityIdFromCombo());
             preparedStatement.setInt(8,GetCategoryIDFromCombo());
             preparedStatement.setInt(9,GetActivityStatusIDFromName());
             preparedStatement.setString(10,username);
             
             preparedStatement.executeUpdate();
             
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Activity Informations");
           alert.setHeaderText("");
           alert.setContentText("Record inserted");
           alert.showAndWait();
             
           LoadActivityTable();
           
           ClearFields();
           
           
            
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
     
        }
         
             
        
        
    }

    @FXML
    private void BtnActivityUpdate(ActionEvent event) throws ClassNotFoundException, SQLException {
        
         DBconection dbconn = new DBconection();
         Connection conn =  dbconn.connect();
         PreparedStatement preparedStatement = null;
         
         String sql = " update activity set aname =? , description = ? , adate =? , enddate = ? ,priority_id = ?,category_id =? , as_id=? where activity_id=? and username='"+username+"' " ;
         
        
         
         
         try {
             
             preparedStatement = conn.prepareStatement(sql);
             preparedStatement.setString(1,TxtActivityName.getText());
             preparedStatement.setString(2,TxtAcitivityDescription.getText());
             preparedStatement.setString(3,TxtActivityDate.getEditor().getText());
             preparedStatement.setString(4,TxtActivityEndDate.getEditor().getText());
             preparedStatement.setInt(5,GetPriorityIdFromCombo() );
             preparedStatement.setInt(6,GetCategoryIDFromCombo());
             preparedStatement.setInt(7,GetActivityStatusIDFromName());
             preparedStatement.setString(8,TxtActivityId.getText());
             
             preparedStatement.executeUpdate();
             
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Activity Informations");
           alert.setHeaderText("");
           alert.setContentText("Record updated");
           alert.showAndWait();
             
           LoadActivityTable();
                        ClearFields();
        
         }
         catch(Exception e)
         {
             System.out.println(e.getMessage());
         }
        
    }

    @FXML
    private void BtnActivityDelete(ActionEvent event) throws ClassNotFoundException, SQLException {
        
         ActivityDetails activityDetails = ActivityTableView.getSelectionModel().getSelectedItem();
           
           DBconection dbcd = new DBconection();
           Connection con = dbcd.connect();
          
       
             
            PreparedStatement pstatement = con.prepareStatement("Delete from activity where activity_id = ?  ");
            pstatement.setString(1, activityDetails.getActivity_id());
            pstatement.executeUpdate();
            
             
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Activity status Information");
           alert.setHeaderText("");
           alert.setContentText("Record Deleted");
           alert.showAndWait();
           
           LoadActivityTable();
           
           ClearFields();
    }

    @FXML
    private void BtnActivityCancel(ActionEvent event) {
        
        ClearFields();
    }
    
    public void search()
    {
        TxtSearchActivityID.textProperty().addListener( (observableValue,oldValue,newValue)->{
               
                filtereddata.setPredicate((Predicate<? super ActivityDetails>) activityDetails->{
                    
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    if (activityDetails.getActivity_id().toLowerCase().contains(lowerCaseFilter))
                    {
                        return true;
                    }
                    else if(activityDetails.getAname().toLowerCase().contains(lowerCaseFilter))
                {
                    return true;
                }
                                                                        
                    return false;
                    
                    
                });
                });
            
            SortedList<ActivityDetails> sortedData = new SortedList<>(filtereddata);
            sortedData.comparatorProperty().bind(ActivityTableView.comparatorProperty());
            ActivityTableView.setItems(sortedData);
                
         
            }
    

   

    @FXML
    private void BtnLoadActivityData(ActionEvent event) throws ClassNotFoundException, SQLException {
        
         DBconection dbpt = new DBconection();
           Connection conn =dbpt.connect();
           
           mydataLoadActivity = FXCollections.observableArrayList();
                   
           
           Statement statement = conn.createStatement();
           ResultSet rs = statement.executeQuery("select * from activity where username='"+username+"'");
        
        while (rs.next())
        {
             //mydataLoadActivity.add( new ActivityDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)  ) );
             mydataLoadActivity.add(new ActivityDetails(rs.getString(1), rs.getString(2),rs.getString(3) , rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7), rs.getString(8),rs.getString(9),rs.getString(10), rs.getString(11)) );
             
        }
        
          
          
          ActivityIdColum.setCellValueFactory(new PropertyValueFactory<ActivityDetails,String>("activity_id"));
          ActivityNameColum.setCellValueFactory(new PropertyValueFactory<ActivityDetails,String>("aname"));
          DescriptionColum.setCellValueFactory(new PropertyValueFactory<ActivityDetails,String>("description"));
          CurrentDateColum.setCellValueFactory(new PropertyValueFactory<ActivityDetails,String>("currentdate"));
          ActivityDateColum.setCellValueFactory(new PropertyValueFactory<ActivityDetails,String>("adate"));
          ActivityEndDateColum.setCellValueFactory(new PropertyValueFactory<ActivityDetails,String>("enddate"));
          ActivityTimeColum.setCellValueFactory(new PropertyValueFactory<ActivityDetails,String>("ac_startingtime"));
          ActivityEndTimeColum.setCellValueFactory(new PropertyValueFactory<>("ac_endingtime"));
          
          
          ActivityPriorityColum.setCellValueFactory(new PropertyValueFactory<ActivityDetails,String>("priority_id"));
          ActivityCategoryColum.setCellValueFactory(new PropertyValueFactory<ActivityDetails,String>("category_id"));
          ActivityStatusColum.setCellValueFactory(new PropertyValueFactory<ActivityDetails,String>("as_id"));
          
          
          
          ActivityTableView.setItems(null);
          ActivityTableView.setItems(mydataLoadActivity);
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
