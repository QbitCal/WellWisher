package Model;

import WellWisher.Printable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;

/**
 * The class used to create activities to be stored in the WeekLog.
 * 
 * REFERENCE THIS FOR POLYMORPHIC JSON SERIALIZATION AND DESERIALIZATION WITH
 * GSON: 
 * 
 * https://jansipke.nl/serialize-and-deserialize-a-list-of-polymorphic-objects-with-gson/
 *
 * @author Quenten Calvano
 * @version 10/17/2021
 */


public class Activity implements Printable, Serializable {

    //Attributes...
    private int activityID; //The id of the activity added.
    private Double timeLength; //The time length in minutes spent on the activity.
    private LocalDate date; //The date the activity was performed on.
    private String description; //What the activity involved or other notes.
    //private WellWisher.ActivityType activityType; //What type of activity this is.
    private boolean physical;

    //Constructors...
    public Activity (int activityID, Double timeLength, LocalDate date,
                     String description) {
        this.activityID = activityID;
        this.timeLength = timeLength;
        this.date = date;
        this.description = description;
        //this.activityType = activityType;

    }
    public Activity () {

    } //For entering data using setters...
    
    public boolean isPhysical() {
        return physical;
    }
    //Setters and Getters...
    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }
    public void setTimeLength(Double timeLength) {
        this.timeLength = timeLength;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getActivityID() {
        return activityID;
    }
    public Double getTimeLength() {
        return timeLength;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getDescription() {
        return description;
    }

    public static Comparator<Activity> sortByDate (){
        Comparator<Activity> actSorter = (Activity act1, Activity act2) -> act1.getDate().compareTo(act2.getDate());
        return actSorter;
    }
    //This method prints each attribute value for the object into a string.
    @Override
    public String toString() {
        return "Activity{" +
                "activityID=" + activityID +
                ", timeLength=" + timeLength +
                ", date=" + date +
                ", description='" + description
                /* + '\'' +
                ", activityType=" + activityType*/
                +
                '}';
    }
    //This method prints the activity type if it has not been specified...
    public void printActivityType(){
        System.out.println("Unspecified Activity Type");
    }
    //This method prints the string that results from the toString Method of this class...
    @Override
    public void print() {
        System.out.println(this.toString());
    }

    //This method prints the description and notifies the user of the object being described...
    @Override
    public void printDescription() {
        System.out.println("Activity Description: " + this.getDescription());

    }
    public String neatString(){
        return "";
    }
    
    
}
