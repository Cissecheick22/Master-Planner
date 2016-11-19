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
public class CategoryDetails {
    
    private final StringProperty category_id;
    private final StringProperty cname;

    public CategoryDetails(String category_id, String cname) {
        this.category_id = new SimpleStringProperty(category_id);
        this.cname = new SimpleStringProperty(cname);
    }

    public String getCategory_id() {
        return category_id.get();
    }

    public String getCname() {
        return cname.get();
    }
    
    public void setCategory_id(String value)
    {
        category_id.set(value);
    }
    
    public void setCname(String value)
    {
        cname.set(value);
    }
    
    
    //Property values
    
    public  StringProperty category_idProperty() { return category_id;}
    public  StringProperty cnameProperty() { return cname;}

    
    
    
    
    
    
}
