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
public class ActivityDetails {
    
    private final StringProperty activity_id;
    private final StringProperty aname;
    private final StringProperty description;
    private final StringProperty currentdate;
    private final StringProperty adate;
    private final StringProperty enddate;
    private final StringProperty ac_startingtime;
    private final StringProperty ac_endingtime;
    
    private final StringProperty priority_id;
    private final StringProperty category_id;
    private final StringProperty as_id;

    public ActivityDetails(String activity_id, String aname, String description, String currentdate, String adate, String enddate,String ac_startingtime , String ac_endingtime ,  String priority_id, String category_id, String as_id) {
        this.activity_id = new SimpleStringProperty(activity_id);
        this.aname = new SimpleStringProperty(aname);
        this.description = new SimpleStringProperty(description);
        this.currentdate = new SimpleStringProperty(currentdate);
        this.adate = new SimpleStringProperty(adate);
        this.enddate = new SimpleStringProperty(enddate);
        this.ac_startingtime = new SimpleStringProperty(ac_startingtime);
        this.ac_endingtime = new SimpleStringProperty(ac_endingtime);
        this.priority_id = new SimpleStringProperty(priority_id);
        this.category_id = new SimpleStringProperty(category_id);
        this.as_id = new SimpleStringProperty(as_id);
    }
    
       
   
    
    public String getActivity_id() {
        return activity_id.get();
    }

    public String getAname() {
        return aname.get();
    }

    public String getDescription() {
        return description.get();
    }

    public String getCurrentdate() {
        return currentdate.get();
    }

    public String getAdate() {
        return adate.get();
    }


    public String getEnddate() {
        return enddate.get();
    }

    public StringProperty getAc_startingtime() {
        return ac_startingtime;
    }

    public StringProperty getAc_endingtime() {
        return ac_endingtime;
    }
    
    

    public String getPriority_id() {
        return priority_id.get();
    }

    public String getCategory_id() {
        return category_id.get();
    }

    public String getAs_id() {
        return as_id.get();
    }
    
    
    public void setActivity_id(String value)
    {
        activity_id.set(value);
    }
    
     public void setAname(String value)
    {
        aname.set(value);
    }
     
      public void setDescription(String value)
    {
        description.set(value);
    }
      
       public void setCurrentdate(String value)
    {
        currentdate.set(value);
    }
       
        public void setAdate(String value)
    {
        adate.set(value);
    }
        
         public void setEndate(String value)
    {
        enddate.set(value);
    }
         
         public void setAc_startingtime(String value)
         {
             ac_startingtime.set(value);
         }
         
         
          public void setAc_endingtime(String value)
         {
             ac_endingtime.set(value);
         }
         
          public void setPriority_id(String value)
    {
        priority_id.set(value);
    }
          
           public void setCategory_id(String value)
    {
        category_id.set(value);
    }
           
            public void setAs_id(String value)
    {
        as_id.set(value);
    }
            
            
            //property values 
            
            
             public  StringProperty activity_idProperty() { return activity_id;}
             public  StringProperty anameProperty() { return aname;}
             public  StringProperty descriptionProperty() { return description;}
             public  StringProperty currentdateProperty() { return currentdate;}
             public  StringProperty adateProperty() { return adate;}
             public  StringProperty endateProperty() { return enddate;}
             public  StringProperty ac_startingtimeProperty() { return ac_startingtime;}
             public  StringProperty ac_endingtimeProperty() { return ac_endingtime;}
             
             public  StringProperty priority_idProperty() { return priority_id;}
             public  StringProperty category_idProperty() { return category_id;}
             public  StringProperty as_idProperty() { return as_id;}
             
             
    
    
    
    
    
    
    
    
}
