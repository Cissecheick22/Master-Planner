/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterplanner;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author cheick
 */

class Mytimer extends TimerTask
{
    @Override
    public void run()
    {
       
       HomeController hc = new HomeController();
        
        try {
            if (hc.ActivateAlarm()==true)
            {
                 
                hc.ActivateAlarm();
                
            }} catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Mytimer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
}


public class MasterPlanner extends Application {
    public static Stage stage ;
    @Override
    public void start(Stage stage) throws Exception {
        
        MasterPlanner.stage=stage;
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false); 
        stage.show();
        
        
        stage.setOnCloseRequest(e ->MasterPlanner.stage.close());
    }

    /** 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
