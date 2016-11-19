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
public class ActivityStatusDetails {
    
    private final StringProperty as_id;
    private final StringProperty as_name;

    public ActivityStatusDetails(String as_id, String as_name) {
        this.as_id = new SimpleStringProperty(as_id) ;
        this.as_name = new SimpleStringProperty(as_name);
    }

    public String getAs_id() {
        return as_id.get();
    }

    public String getAs_name() {
        return as_name.get();
    }
    
    public void setAs_id(String value)
    {
        as_id.set(value);
    }
    
    public void setAs_name ( String value)
    {
        as_name.set(value);
    }
    
     public  StringProperty as_idProperty() { return as_id;}
    public  StringProperty as_nameProperty() { return as_name;}
    
    
    
    
    
}
