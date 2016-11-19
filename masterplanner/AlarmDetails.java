/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterplanner;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author cheick
 */
public class AlarmDetails {
    
    final StringProperty aname;
    final StringProperty alarm_date;
    final StringProperty alarm_time;

    public AlarmDetails(String aname, String alarm_date, String alarm_time) {
        this.aname = new SimpleStringProperty(aname);
        this.alarm_date = new SimpleStringProperty(alarm_date);
        this.alarm_time = new SimpleStringProperty(alarm_time);
    }

    public String getAname() {
        return aname.get();
    }

    public String getAlarm_date() {
        return alarm_date.get();
    }

    public String getAlarm_time() {
        return alarm_time.get();
    }
    
    public void setAname(String value)
    {
        aname.set(value);
    }
    
     public void setAlarm_date(String value)
    {
        alarm_date.set(value);
    }
     
      public void setAlarm_time(String value)
    {
        alarm_time.set(value);
    }
    
    
             public  StringProperty anameProperty() { return aname;}
             public  StringProperty alarm_dateProperty() { return alarm_date;}
             public  StringProperty alarm_timeProperty() { return alarm_time;}
    
    
}
