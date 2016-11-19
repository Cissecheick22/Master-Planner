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
public class PriorityDetails {
    
    public final StringProperty priority_id;
    public final StringProperty pname;

    public PriorityDetails(String priority_id, String pname) {
        this.priority_id = new SimpleStringProperty(priority_id)  ;
        this.pname = new SimpleStringProperty(pname);
    }

    public String getPriority_id() {
        return priority_id.get();
    }

    public String getPname() {
        return pname.get();
    }
    
    public void setPriority_id(String value)
    {
        priority_id.set(value);
    }
    public  void setPname(String Value)
    {
        pname.set(Value);
    }
    
    //define property values
    
    public StringProperty priority_idProperty(){return priority_id;}
    public StringProperty pnameProperty() { return pname;}
    
    
    
    
     
    
}
