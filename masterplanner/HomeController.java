/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterplanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import sun.security.rsa.RSACore;

/**
 * FXML Controller class
 *
 * @author cheick
 */
public class HomeController implements Initializable {

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
    private Button BtnLinkActivity;
    @FXML
    private Button BtnLinkProfle;
    @FXML
    private Button BtnLinkCategory;
    @FXML
    private Button BtnLinkPriority;
    @FXML
    private Button BtnLinkActivityStatus;
    @FXML
    private TableView<ActivityDetails> HomeActivtiyTableView;
    
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
    private TableColumn<ActivityDetails,String> ActivityEndTimeColum;
    
    @FXML
    private TableColumn<ActivityDetails,String> ActivityEndDateColum;
    @FXML
    private TableColumn<ActivityDetails,String> ActivityPriorityColum;
    @FXML
    private TableColumn<ActivityDetails,String> ActivityCategoryColum;
    @FXML
    private TableColumn<ActivityDetails,String> ActivityStatusColum;
    
    ObservableList<ActivityDetails> mydataLoadActivity = FXCollections.observableArrayList();
    @FXML
    private ComboBox<?> CmbActivityAlarm;
    @FXML
    private DatePicker AlarmDate;
    @FXML
    private TextField TxtAlarmTIme;
    
    
    public LineChart<String,Number> theLinechart;
    @FXML
    private BarChart<String, Number> mybartchart;
    @FXML
    private NumberAxis activity;
    @FXML
    private CategoryAxis priority;
    
    
    @FXML
    private TableView<?> AlarmTableView;
    @FXML
    private TableColumn<?, ?> ColumAlramAcName;
    @FXML
    private TableColumn<?, ?> ColumAlramAcDate;
    @FXML
    private TableColumn<?, ?> ColumAlramAcTime;
    @FXML
    
    private PieChart mypiechart;
    @FXML
    private Button SetupAlarm;
    
    ObservableList mydataLoadAcitivityAlarm = FXCollections.observableArrayList();
    ObservableList LoadAlarmTableView = FXCollections.observableArrayList();
    @FXML
    private Button PlaySong;
    @FXML
    private Button PauseSong;
    @FXML
    private Button StopSong;
    
  
     MediaPlayer mediaPlayer;
    @FXML
    private Button BtnAlarmDelete;

   
   
    InputStream file;
    @FXML
    private ImageView GoProfile;
    @FXML
    private Button BtnLinkStatistique;
    @FXML
    private Button BtnLinkSettings;
    @FXML
    private Button BtnLogout;
    
    String username = FXMLDocumentController.username;

    /**


* Initializes the controller class.
     */
    
    
    
     Timer tm = new Timer();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            Stage stage= MasterPlanner.stage;
         //stage.setOnCloseRequest(e -> Platform.exit());
         stage.setOnCloseRequest(e ->MasterPlanner.stage.close());
         
        
        try {
            
                   
            Timer tm = new Timer();
            tm.scheduleAtFixedRate(new Mytimer(), 0, 10000);
            
            
            mypiechart();
           
            LoadActivityTable();
          LoadComboAlarm();
            mychart();
            LoadAlarmTableView();
            
            AlarmDate.setValue(LocalDate.now());
          
           
            // TODO
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
            stage.setFullScreen(true);
    }   
    
    
    
   public void LoadAlarmTableView() throws ClassNotFoundException, SQLException
    {
           DBconection dbpt = new DBconection();
           Connection conn =dbpt.connect();
           
           LoadAlarmTableView = FXCollections.observableArrayList();
                   
           
           Statement statement = conn.createStatement();
           ResultSet rs = statement.executeQuery("select aname,alarm_date,alarm_time from activity where alarm_date != '' AND alarm_time !='' and  username='"+username+"' ");
        
        while (rs.next())
        {
          LoadAlarmTableView.add(new AlarmDetails(rs.getString("aname"), rs.getString("alarm_date"), rs.getString("alarm_time")));
        }
        
          
          
          ColumAlramAcName.setCellValueFactory(new PropertyValueFactory<>("aname"));
          ColumAlramAcDate.setCellValueFactory(new PropertyValueFactory<>("alarm_date"));
          ColumAlramAcTime.setCellValueFactory(new PropertyValueFactory<>("alarm_time"));
          
          
          
          
          AlarmTableView.setItems(null);
          AlarmTableView.setItems(LoadAlarmTableView);
    }
    
     
     
     private int GetActivityAlarmIDFromName() throws ClassNotFoundException, SQLException
    {
         String CmbAcAlarmName =CmbActivityAlarm.getValue().toString();
         
         DBconection dbconn = new DBconection();
         Connection conn =  dbconn.connect();
         Statement statement = null;
         ResultSet rs = null;
         String sql = "SELECT activity_id FROM activity WHERE aname='" + CmbAcAlarmName + "' and  username='"+username+"' ";
      
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            int AsAlaram_id = 0;
           while(rs.next())
             AsAlaram_id = rs.getInt(1);
           
           return AsAlaram_id;
    }
    
    
    @FXML
    private void SetupAlarm(ActionEvent event) throws ClassNotFoundException, SQLException {
        DBconection dbcombo = new DBconection();
        Connection conn = dbcombo.connect();
        PreparedStatement preparedStatement= null;
        
        String sql = "update  activity set alarm_date=? ,alarm_time=? where activity_id = ? and  username='"+username+"' ";
        
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,AlarmDate.getEditor().getText());
            preparedStatement.setString(2,TxtAlarmTIme.getText());
            preparedStatement.setInt(3,GetActivityAlarmIDFromName());
            
            preparedStatement.execute();
            
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("ALARM");
           alert.setHeaderText("");
           alert.setContentText("RAlarm is set up");
           alert.showAndWait();
           
            LoadAlarmTableView();
            
            CmbActivityAlarm.getSelectionModel().clearSelection();
            AlarmDate.getEditor().clear();
            TxtAlarmTIme.clear();
            
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        
    }
    
   
    
    
    public void LoadComboAlarm() throws ClassNotFoundException, SQLException
    {
        DBconection dbcombo = new DBconection();
        Connection conn = dbcombo.connect();
        
        Statement statement = null;
        ResultSet resultSet = null;
        
        
        try {
             
                String sql = "SELECT * from activity where  username='"+username+"' order by currentdate desc";
                    
            
              statement = conn.createStatement();
               resultSet = statement.executeQuery(sql);
             
              while(resultSet.next())
              {
                 mydataLoadAcitivityAlarm.add(resultSet.getString("aname"));
              }
              
              CmbActivityAlarm.setItems(mydataLoadAcitivityAlarm);
              
             
        } catch (Exception e) {
        }
        finally
        {
            statement.close();
            resultSet.close();
            conn.close();
            
        }
    }
    
    public int getACtivitydone() throws ClassNotFoundException, SQLException
    {
        DBconection db = new DBconection();
        Connection con = db.connect();
        
        String sql="select COUNT(*) from activity WHERE as_id=3 and  username='"+username+"'";
        
        
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int activitydone=0;
            
            while(resultSet.next())
            {
                activitydone= resultSet.getInt(1);
                return activitydone;
            }
           
            
            
       
        return activitydone;
       
    }
    
        public int getACtivityOngoing() throws ClassNotFoundException, SQLException
    {
        DBconection db = new DBconection();
        Connection con = db.connect();
        
        String sql="select COUNT(*) from activity WHERE as_id=2 and  username='"+username+"'";
        
        
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int activitydone=0;
            
            while(resultSet.next())
            {
                activitydone= resultSet.getInt(1);
                return activitydone;
            }
           
            
            
       
        return activitydone;
       
    }
        
        
          public int getACtivitybefore() throws ClassNotFoundException, SQLException
    {
        DBconection db = new DBconection();
        Connection con = db.connect();
        
        String sql="select COUNT(*) from activity WHERE as_id=1 and  username='"+username+"'";
        
        
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int activitydone=0;
            
            while(resultSet.next())
            {
                activitydone= resultSet.getInt(1);
                return activitydone;
            }
           
            
            
       
        return activitydone;
       
    }
    
          
          
    
   
    public void  mypiechart() throws ClassNotFoundException, SQLException
    {
        ObservableList<Data> piechartlist = FXCollections.observableArrayList(
        new PieChart.Data("ACTIVITY PENDING", getACtivitybefore()),
         new PieChart.Data("ONGOING ACTIVITY", getACtivityOngoing()),
          new PieChart.Data("ACTIVITY PERFORMED", getACtivitydone())
          
        );
        
        mypiechart.setData(piechartlist);
        mypiechart.setClockwise(true);
        mypiechart.setLegendVisible(true);
          
        
    }
    
    
     public int getACtivityCategory() throws ClassNotFoundException, SQLException
    {
        DBconection db = new DBconection();
        Connection con = db.connect();
        
        String sql="select COUNT(*) from category where  username='"+username+"'";
        
        
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int activitydone=0;
            
            while(resultSet.next())
            {
                activitydone= resultSet.getInt(1);
                return activitydone;
            }
           
            
            
       
        return activitydone;
       
    }
     
     
      public int getACtivityStatus() throws ClassNotFoundException, SQLException
    {
        DBconection db = new DBconection();
        Connection con = db.connect();
        
        String sql="select COUNT(*) from activity_status where  username='"+username+"'";
        
        
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int activitydone=0;
            
            while(resultSet.next())
            {
                activitydone= resultSet.getInt(1);
                return activitydone;
            }
           
            
            
       
        return activitydone;
       
    }
      
      
       public int getACtivityPriority() throws ClassNotFoundException, SQLException
    {
        DBconection db = new DBconection();
        Connection con = db.connect();
        
        String sql="select COUNT(*) from priority where username='"+username+"'";
        
        
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int activitydone=0;
            
            while(resultSet.next())
            {
                activitydone= resultSet.getInt(1);
                return activitydone;
            }
           
            
            
       
        return activitydone;
       
    } 
    
       
        
       public int getACtivityCount() throws ClassNotFoundException, SQLException
    {
        DBconection db = new DBconection();
        Connection con = db.connect();
        
        String sql="select COUNT(*) from activity where  username='"+username+"'";
        
        
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int activitydone=0;
            
            while(resultSet.next())
            {
                activitydone= resultSet.getInt(1);
                return activitydone;
            }
           
            
            
       
        return activitydone;
       
    } 
    
    
    
    
    public void mychart() throws ClassNotFoundException, SQLException
    {
        
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        
       XYChart.Series set1 = new XYChart.Series<>();
       XYChart.Series set2 = new XYChart.Series<>();
       XYChart.Series set3 = new XYChart.Series<>();
       XYChart.Series set4 = new XYChart.Series<>();
       
       
       set1.getData().add(new XYChart.Data("Activity",getACtivityCount()));
       set1.setName("Activity");
       
       set2.getData().add(new XYChart.Data("Category",getACtivityCategory()));
       set2.setName("Category");
       
       
       set3.getData().add(new XYChart.Data("Priority",getACtivityPriority()));
       set3.setName("Priority");
       
       set4.getData().add(new XYChart.Data("Status",getACtivityStatus()));
       set4.setName("Status");
      
       
        
       mybartchart.getData().addAll(set1,set2,set3,set4);
       mybartchart.setLegendSide(Side.BOTTOM);
       mybartchart.setLegendVisible(true);
       
    }
    
   
    
   
    
      private String getnameFromid( String idtoName) throws ClassNotFoundException, SQLException
    {
        DBconection dbpt = new DBconection();
           Connection conn =dbpt.connect();
           String NameFromId ="";
          String sql = "select pname from priority where priority_id='" + idtoName + "' and  username='"+username+"'  ";
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
          String sql = "select cname from category where category_id='" + catidtoName + "' and  username='"+username+"'   ";
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
          String sql = "select as_name from activity_status where as_id='" + AsidtoName + "' and  username='"+username+"'  ";
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
           ResultSet rs = statement.executeQuery("select * from activity where  username='"+username+"' order by currentdate desc");
        
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
          
          
          
          HomeActivtiyTableView.setItems(null);
          HomeActivtiyTableView.setItems(mydataLoadActivity);
    }

  @FXML
    private void BtnHome(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
           Scene scene = new Scene(root);
           Node node= (Node)event.getSource();
           Stage stage=(Stage) node.getScene().getWindow();
           stage.setMaximized(true); 
            stage.centerOnScreen();
           stage.setScene(scene);
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
    private void BtnLinkActivity(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Activity.fxml"));
         Scene scene = new Scene(root);
         
         Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
         thestage.setScene(scene);
         thestage.setFullScreen(true);
         thestage.centerOnScreen();
         thestage.show();
          
       
    }

    @FXML
    private void BtnLinkProfle(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
         Scene scene = new Scene(root);
         
         Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
         thestage.setScene(scene);
         thestage.centerOnScreen();
         thestage.show();
          
    }

    @FXML
    private void BtnLinkCategory(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Category.fxml"));
         Scene scene = new Scene(root);
         
         Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
         thestage.setScene(scene);
         thestage.centerOnScreen();
         thestage.show();
          
    }

    @FXML
    private void BtnLinkPriority(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Priority.fxml"));
         Scene scene = new Scene(root);
         
         Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
         thestage.setScene(scene);
         thestage.centerOnScreen();
         thestage.show();
          
    }

    @FXML
    private void BtnLinkActivityStatus(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Activity_status.fxml"));
         Scene scene = new Scene(root);
         
         Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
         thestage.setScene(scene);
         thestage.centerOnScreen();
         thestage.show();
          
    }


    @FXML
    private void CmbActivityAlarm(ActionEvent event) {
    }
    
    public Boolean  ActivateAlarm() throws ClassNotFoundException, SQLException
    {
        Boolean flag = false;
        DateFormat dateFormat = new SimpleDateFormat("MM/d/yyyy");
        DateFormat hourformat = new SimpleDateFormat("HH:mm");
        Calendar cal = Calendar.getInstance();
       
       String LocaleDate = dateFormat.format(cal.getTime());
       String LocaleHour = hourformat.format(cal.getTime());
       
        
       
       DBconection dBconection = new DBconection();
       Connection conn =  dBconection.connect();
       
       String sql= "select alarm_date , alarm_time from activity where  alarm_date != '' AND alarm_time !='' ";
        Statement statement= null;
        ResultSet resultSet=null;
        try {
             statement = conn.createStatement();
             resultSet= statement.executeQuery(sql);
           //  ObservableList alarminfo = FXCollections.observableArrayList();
             while(resultSet.next())
             {
                 
                 if(resultSet.getString(1).equals(LocaleDate) && resultSet.getString(2).equals(LocaleHour) )
                 {
                      
             
                    Media Alarmfile = new Media("file:/C:/Users/cheick/Documents/NetBeansProjects/masterplanner/src/alarmsong/alarm.mp3");
         mediaPlayer = new MediaPlayer(Alarmfile);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();
       
        mediaPlayer.setVolume(0.10);
        Alert alert = new Alert(Alert.AlertType.WARNING);
                       alert.setTitle("Activity status Information");
                       alert.setHeaderText("");
           
                       alert.setContentText("TIme to Do acctivity");
                      alert.show();
        
           
             flag = true;
        
                 }   
          
         
              
             }
             
            
        } catch (Exception e) {
            flag =false;
        }
       
       return flag;
    }

    @FXML
    private void PlaySong(ActionEvent event) {
        
        try
        {
         Media Alarmfile = new Media("file:/C:/Users/cheick/Documents/NetBeansProjects/masterplanner/src/alarmsong/alarm.mp3");
         mediaPlayer = new MediaPlayer(Alarmfile);
        mediaPlayer.setAutoPlay(true);
       
        mediaPlayer.setVolume(0.10);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void PauseSong(ActionEvent event) {
        mediaPlayer.pause();
        System.out.println(MediaPlayer.Status.PAUSED);
    }

    @FXML
    private void StopSong(ActionEvent event) {
        mediaPlayer.stop();
        
    }

    private void BtnActivate(ActionEvent event) throws IOException {
        
        FileChooser fc = new FileChooser();
       // fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("*.mp3"));
        File SelectedFile = fc.showOpenDialog(null);
        
        if (SelectedFile != null)
        {
         
        }
        
        
        
    }

    @FXML
    private void AlarmTableClick(MouseEvent click) throws ClassNotFoundException, SQLException {
        
          if (click.getClickCount()==2)
        {
            DBconection dbcd = new DBconection();
        Connection con = dbcd.connect();
        
      AlarmDetails alarmDetails = (AlarmDetails) AlarmTableView.getSelectionModel().getSelectedItem();
                  
           // CmbActivityAlarm.set (alarmDetails.aname);
           // AlarmDate(alarmDetails.alarm_date);
    
           
          AlarmDate.setValue(LocalDate.now());
            TxtAlarmTIme.setText(alarmDetails.getAlarm_time());
    }

    
    }
    
    
    public int getIDFromActivitynameAlarm(String acname) throws ClassNotFoundException, SQLException
    {
         DBconection dbcd = new DBconection();
           Connection con = dbcd.connect();
           Statement statement;
           ResultSet rs;
           
           String sql ="select activity_id from activity where aname='"+ acname + "' and  username='"+username+"' ";
           
            statement = con.createStatement();
            rs = statement.executeQuery(sql);
            int AcAlarm_id = 0;
           while(rs.next())
             AcAlarm_id = rs.getInt(1);
           
           return AcAlarm_id;
           
        
    }

    @FXML
    private void BtnAlarmDelete(ActionEvent event) throws ClassNotFoundException, SQLException {
        
         DBconection dbcd = new DBconection();
           Connection con = dbcd.connect();
           
         AlarmDetails alarmDetails = (AlarmDetails) AlarmTableView.getSelectionModel().getSelectedItem();
         
          try {
             
            PreparedStatement pstatement = con.prepareStatement("update activity set alarm_date=? , alarm_time=?  where activity_id = ? and  username='"+username+"' ");
            pstatement.setString(1,"");
            pstatement.setString(2,"");
            pstatement.setInt(3, getIDFromActivitynameAlarm(alarmDetails.getAname()));
            pstatement.executeUpdate();
            
             
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Activity status Information");
           alert.setHeaderText("");
           alert.setContentText("Alarm deleted Deleted");
           alert.showAndWait();
           
           
           
        } catch (Exception e) {
            
              System.out.println(e.getMessage());
        }
              LoadAlarmTableView();
    }

    @FXML
    private void BtnLinkSettings(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
         Scene scene = new Scene(root);
         
         Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
         thestage.setScene(scene);
         thestage.centerOnScreen();
         thestage.show();
        
    }

    @FXML
    private void BtnLinkStatistique(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Statistiques.fxml"));
         Scene scene = new Scene(root);
         
         Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
         thestage.setScene(scene);
         thestage.centerOnScreen();
         thestage.show();
    }

    @FXML
    private void BtnLogout(ActionEvent event) throws IOException, SQLException {
        
        
        DBconection.con.close();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
         Scene scene = new Scene(root);
         
         Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
         thestage.setScene(scene);
         thestage.centerOnScreen();
         thestage.show();
    }
}

  

