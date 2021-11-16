package Model;

import WellWisher.Printable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

/**
 * The class used to create activities to be stored in the WeekLog.
 *
 * @author Quenten Calvano
 * @version 11/13/2021
 */


public class Activity implements Printable, Serializable {

    //Attributes...
    private int activityID; //The id of the activity added.
    private Double timeLength; //The time length in minutes spent on the activity.
    private LocalDate date; //The date the activity was performed on.
    private String description; //What the activity involved or other notes.
    private boolean physical; //Returns whether or not the object is Physical.

    //Constructors...
    public Activity (int activityID, Double timeLength, LocalDate date,
                     String description) {
        this.activityID = activityID;
        this.timeLength = timeLength;
        this.date = date;
        this.description = description;
    }
    
    //Constructor for entering data using setters...
    public Activity () {
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
    public boolean isPhysical() {
        return physical;
    }

  /**
    * Creates a comparator for the Activity objects in which activities are 
    * sorted by checking one activity's LocalDate value to that of another.
    * 
    *
    * @return actSorter  an Activity object comparator for sorting by date.
    */
    public static Comparator<Activity> sortByDate (){
        Comparator<Activity> actSorter = (Activity act1, Activity act2) -> act1.getDate().compareTo(act2.getDate());
        return actSorter;
    }
    
  /**
    * Overrides the toString method allowing for better formatting of the
    * Activity class.
    * 
    * @return a string value of the Activity object's contents.
    */
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
    
  /**
    * Prints descriptive output to the console if the activity type has not
    * been specified...
    * 
    */
    public void printActivityType(){
        System.out.println("Unspecified Activity Type");
    }
    
  /**
    * Prints the contents of the Activity class's toString() method to the 
    * console.
    * 
    */
    @Override
    public void print() {
        System.out.println(this.toString());
    }
  /**
    * This method prints the Activity's description and notifies the user of 
    * the object being described.
    * 
    */
    @Override
    public void printDescription() {
        System.out.println("Activity Description: " + this.getDescription());

    }
  /**
    * This method returns an empty string when called. 
    * 
    * (The neatString() method returns a string of an object's details if the 
    * object is a Physical or Social Activity.)
    * 
    */
    public String neatString(){
        return "";
    }
    
    
}
