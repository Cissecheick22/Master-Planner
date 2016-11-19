/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterplanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javax.xml.soap.Node;

/**
 *
 * @author cheick
 */
public class DBconection {
    
     static final String JDBC_DRIVER ="com.mysql.jdbc.Driver" ;
     static final String DB_URL = "jdbc:mysql://localhost/masterplanner";
     static final String USER = "root";
     static final String PASS = "";
    static Connection con;
    
  

    public Connection connect() throws ClassNotFoundException, SQLException {
     
         try {
                  Class.forName(JDBC_DRIVER);
               con=DriverManager.getConnection(DB_URL,USER,PASS); 
            if ( con==null)
            {
                System.out.println("Not Connected");
                
            }
           
            
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
         
         return con;
    }
    
    public Boolean Login(String username, String password) throws SQLException
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from user where name=? and password= ? ";
        try
        {
        preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        
        resultSet = preparedStatement.executeQuery();
        
        if(!resultSet.next())
        {
            return false; 
        }
        
        if(resultSet.next())
        {
            if(resultSet.getString("name").equals(username))
            {
                
             Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("ALARM");
           alert.setHeaderText("");
           alert.setContentText("Username already exist.");
           alert.showAndWait();
           return false;
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
        
      return true;
      
    }
    
    
     
       
    
}
