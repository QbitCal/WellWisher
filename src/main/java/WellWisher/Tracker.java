package WellWisher;

/**
 * The class used to create and handle trackers created by the user.
 *
 * @author Quenten Calvano
 * @version 9/4/2021
 */


import java.time.LocalDate;
import java.time.ZoneId;

public class Tracker implements Printable{

    //Attributes...
    private String name; //Name of the tracker;
    private String description; //Description/notes about the tracker.
    private int count; //The current count of the Tracker.
    private LocalDate dateStarted; //The date the counter was started.

    //Constructors...
    public Tracker (String name, String description) {
        this.name = name;
        this.description = description;
        this.count = 0; //Starts counter at 0.
        this.dateStarted = LocalDate.now(ZoneId.systemDefault()); //Sets start date to current date at creation.
    }

    //Setters and Getters...
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDateStarted(LocalDate dateStarted) {
        this.dateStarted = dateStarted;
    }//This method exists just in case start date needs to be changed.
    public String getDescription() {
        return description;
    }
    public int getCount() {
        return count;
    }
    public String getName() {
        return name;
    }
    public LocalDate getDateStarted() {
        return dateStarted;
    }

    //This method prints each attribute value for the object into a string.
    @Override
    public String toString() {
        return "Tracker{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", count=" + count + '\'' +
                ", dateStarted=" + dateStarted.toString() +
                '}';
    }

    //This method prints the string that results from the toString Method of this class...
    @Override
    public void print() {
        System.out.println(this.toString());
    }
    //This method prints the description and notifies the user of the object being described...
    @Override
    public void printDescription() {
        System.out.println("Tracker Description: " + this.getDescription());
    }
}

