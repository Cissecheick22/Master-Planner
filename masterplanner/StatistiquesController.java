/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterplanner;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cheick
 */
public class StatistiquesController implements Initializable {

    @FXML
    private Button BtnHome;
    @FXML
    private Button BtnProfile;
    @FXML
    private Button BtnActivity;
    @FXML
    private Button BtnCategory;
    @FXML
    private Button BtnActivityStatus;
    @FXML
    private Button BtnStatistiques;
    @FXML
    private Button BtnSettings;
    @FXML
    private PieChart PieChartStat;
    @FXML
    private BarChart<String, Number> BarchartStat;
    @FXML
    private Label LActivity;
    @FXML
    private Label LPerform;
    @FXML
    private Label LOngoing;
    @FXML
    private Label LPending;
    @FXML
    private Label LHigh;
    @FXML
    private Label LMedium;
    @FXML
    private Label LLow;
    @FXML
    private Label LPActivity;
    @FXML
    private Label LPPerform;
    @FXML
    private Label LPOngoing;
    @FXML
    private Label LPPending;
    @FXML
    private Label LPHigh;
    @FXML
    private Label LPMedium;
    @FXML
    private Label LPLow;
    @FXML
    private Button BtnLogout;
    @FXML
    private PieChart GeneralPieChart;

    /**
     * Initializes the controller class.
     */
    
    String username = FXMLDocumentController.username;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
            
            
            
            mypiechart();
            mychart();
            GeneralPieChart();
            LActivity.setText(Integer.toString(numberAC()));
           // LPActivity.setText(Integer.toString(100));
            
            LPerform.setText(Integer.toString(numberAPerf()));
            
            LOngoing.setText(Integer.toString(numberAOn()));
            
            LPending.setText(Integer.toString(numberADpen()));
            
            LHigh.setText(Integer.toString(numberAHigh()));
            
            LMedium.setText(Integer.toString(numberAMed()));
            
            LLow.setText(Integer.toString(numberALow()));
           
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StatistiquesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StatistiquesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    
    
     public void  mypiechart() throws ClassNotFoundException, SQLException
    {
        ObservableList<PieChart.Data> piechartlist = FXCollections.observableArrayList(
        new PieChart.Data("ACTIVITY PENDING", numberADpen()),
         new PieChart.Data("ONGOING ACTIVITY", numberAOn()),
          new PieChart.Data("ACTIVITY PERFORMED", numberAPerf())
          
        );
        
        PieChartStat.setData(piechartlist);
        PieChartStat.setClockwise(true);
        PieChartStat.setLegendVisible(true);
          
        
    }
     
     
      public void  GeneralPieChart() throws ClassNotFoundException, SQLException
    {
        ObservableList<PieChart.Data> piechartlist = FXCollections.observableArrayList(
        new PieChart.Data("ACTIVITY PERFORMED", numberAPerf()),
        
         new PieChart.Data("ONGOING ACTIVITY", numberAOn()),
         new PieChart.Data("ACTIVITY PENDING", numberADpen()),
          
         new PieChart.Data("HIGH PRIORITY", numberAHigh()),
         new PieChart.Data("MEDIUM PRIORITY", numberAMed()),
          new PieChart.Data("LOW PRIORITY", numberALow())
          
        );
        
        GeneralPieChart.setData(piechartlist);
        GeneralPieChart.setClockwise(true);
        GeneralPieChart.setLegendVisible(true);
          
        
    }
     
     
      public void mychart() throws ClassNotFoundException, SQLException
    {
        
        
        
       XYChart.Series set1 = new XYChart.Series<>();
       XYChart.Series set2 = new XYChart.Series<>();
       XYChart.Series set3 = new XYChart.Series<>();
       XYChart.Series set4 = new XYChart.Series<>();
       
       
       set1.getData().add(new XYChart.Data("Activity",numberAC()));
       set1.setName("Activity Status");
       
       set2.getData().add(new XYChart.Data("Category",numberAHigh()));
       set2.setName("High");
       
       
       set3.getData().add(new XYChart.Data("Priority",numberAMed()));
       set3.setName("Meduim");
       
       set4.getData().add(new XYChart.Data("Status",numberALow()));
       set4.setName("Low");
      
       
        
       BarchartStat.getData().addAll(set1,set2,set3,set4);
       BarchartStat.setLegendSide(Side.BOTTOM);
       BarchartStat.setLegendVisible(true);
       
    }
    
    
    
     
    @FXML
    private void BtnHome(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
     Scene scene = new Scene(root);
      Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
         thestage.setScene(scene);
         thestage.setFullScreen(true);
          thestage.centerOnScreen();
         thestage.show();
     
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
      Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
         thestage.setScene(scene);
         thestage.setFullScreen(true);
          thestage.centerOnScreen();
         thestage.show();
     
    }

    @FXML
    private void BtnCategory(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Category.fxml"));
     Scene scene = new Scene(root);
      Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
         thestage.setScene(scene);
          thestage.centerOnScreen();
         thestage.show();
     
    }

    @FXML
    private void BtnActivityStatus(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Activity_status.fxml"));
     Scene scene = new Scene(root);
      Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
         thestage.setScene(scene);
         thestage.centerOnScreen();
         thestage.show();
     
    }

    @FXML
    private void BtnStatistiques(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Statistiques.fxml"));
     Scene scene = new Scene(root);
      Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
         thestage.setScene(scene);
         thestage.centerOnScreen();
         thestage.show();
     
    }

    @FXML
    private void BtnSettings(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
     Scene scene = new Scene(root);
      Stage thestage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
         thestage.setScene(scene);
           thestage.centerOnScreen();
         thestage.show();
     
    }
    
    public int numberAC() throws ClassNotFoundException, SQLException
    {
         DBconection db = new DBconection();
        Connection con = db.connect();
        String username = FXMLDocumentController.username;
        String sql="select COUNT(*) from activity where username='" + username + "' ";
        
        
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
    
    
     public int numberAPerf() throws ClassNotFoundException, SQLException
    {
         DBconection db = new DBconection();
        Connection con = db.connect();
        
       
        String sql="select COUNT(*) from activity where as_id='3' and username='"+username+"' ";
        
        
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int activityperf=0;
            
            while(resultSet.next())
            {
                activityperf= resultSet.getInt(1);
                return activityperf;
            }
           
        return activityperf;
     
    }
    
     
      public int numberAOn() throws ClassNotFoundException, SQLException
    {
         DBconection db = new DBconection();
        Connection con = db.connect();
        
        String sql="select COUNT(*) from activity where as_id='2' and username='"+username+"' ";
        
        
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int activityon=0;
            
            while(resultSet.next())
            {
                activityon= resultSet.getInt(1);
                return activityon;
            }
           
        return activityon;
     
    }
    
      public int numberADpen() throws ClassNotFoundException, SQLException
    {
         DBconection db = new DBconection();
        Connection con = db.connect();
        
        String sql="select COUNT(*) from activity where as_id='1' and username='"+username+"' ";
        
        
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int activitydon=0;
            
            while(resultSet.next())
            {
                activitydon= resultSet.getInt(1);
                return activitydon;
            }
           
        return activitydon;
     
    }
    
      public int numberAHigh() throws ClassNotFoundException, SQLException
    {
         DBconection db = new DBconection();
        Connection con = db.connect();
        
        String sql="select COUNT(*) from activity where priority_id='1' and username='"+username+"' ";
        
        
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int activityhigh=0;
            
            while(resultSet.next())
            {
                activityhigh= resultSet.getInt(1);
                return activityhigh;
            }
           
        return activityhigh;
     
    }
    
      public int numberAMed() throws ClassNotFoundException, SQLException
    {
         DBconection db = new DBconection();
        Connection con = db.connect();
        
        String sql="select COUNT(*) from activity where priority_id='2' and username='"+username+"' ";
        
        
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int activitymed=0;
            
            while(resultSet.next())
            {
                activitymed= resultSet.getInt(1);
                return activitymed;
            }
           
        return activitymed;
     
    }
    
      
      public int numberALow() throws ClassNotFoundException, SQLException
    {
         DBconection db = new DBconection();
        Connection con = db.connect();
        
        String sql="select COUNT(*) from activity where priority_id='3' and username='"+username+"' ";
        
        
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            int activitylow=0;
            
            while(resultSet.next())
            {
                activitylow= resultSet.getInt(1);
                return activitylow;
            }
           
        return activitylow;
     
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
