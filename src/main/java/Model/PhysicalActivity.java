package Model;

import Model.Activity;
import WellWisher.Printable;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * An extension of the Activity Class that handles physical activities.
 *
 * @author Quenten Calvano
 * @version 10/17/2021
 */

public class PhysicalActivity extends Activity implements Printable, Serializable{

    //Attributes...
    private String intensity = null;//The intensity description such as Low, Moderate, High.
    private int weight;//The approximate amount of weight added in lbs.

    //For determining the WeekLog to present...
    private final boolean physical = true;
    
    //Constructor...
    public PhysicalActivity(int activityID, Double timeLength, LocalDate date,
                            String description, String intensity, int weight) {
        super(activityID, timeLength, date, description);
        this.intensity = intensity;
        this.weight = weight;
    }
    public PhysicalActivity () {
        
    }
    
    //Setters and Getters...
    public boolean isPhysical() {
        return physical;
    }
    public String getIntensity() {
        return intensity;
    }
    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    //This method prints each attribute value for the object into a string...
    @Override
    public String toString() {
        return "PhysicalActivity{" +
                "activityID=" + super.getActivityID() +
                ", timeLength=" + super.getTimeLength() +
                ", date=" + super.getDate() +
                ", description='" + super.getDescription() +
                ", intensity='" + intensity + '\'' +
                ", weight=" + weight +
                '}';
    }

    //This method prints that this is a physical activity...
    @Override
    public void printActivityType() {
        System.out.println("Physical Activity");
    }
    //This method prints the description and notifies the user of the object being described...
    @Override
    public void printDescription() {
        System.out.println("Physical Activity Description: " + this.getDescription());

    }
    @Override
    public String neatString(){
        String outputText = String.format("%s%10s\n%s%10s\n%s%10s\n"
                + "%s%10s\n%s%10s\n", "ActivityID:",
                this.getActivityID(), "Time:", this.getTimeLength(),
                "Description:", this.getDescription(), "Intensity: ",
                this.getIntensity(), "Weight:", this.getWeight());
        return outputText;
    }
}
