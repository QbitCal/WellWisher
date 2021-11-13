package Model;

import Model.Activity;
import WellWisher.Printable;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * An extension of the Activity Class that handles social activities.
 *
 * @author Quenten Calvano
 * @version 10/17/2021
 */

public class SocialActivity extends Activity implements Printable, Serializable{

    //Attributes...
    private String eventType = null;
    private String friendsInvolved = null;
    
    //For determining the WeekLog to present...
    private final boolean physical = false;

    //Constructor...
    public SocialActivity(int activityID, Double timeLength, LocalDate date
            , String description, String eventType, String friendsInvolved) {
        super(activityID, timeLength, date, description);
        this.eventType = eventType;
        this.friendsInvolved = friendsInvolved;
    }
    public SocialActivity () {
        
    }
    //Setters and Getters...

    public boolean isPhysical() {
        return physical;
    }
    public String getEventType() {
        return eventType;
    }
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    public String getFriendsInvolved() {
        return friendsInvolved;
    }
    public void setFriendsInvolved(String friendsInvolved) {
        this.friendsInvolved = friendsInvolved;
    }

    //This method returns the contents of the Social Activity object as a string...
    @Override
    public String toString() {
        return "SocialActivity{" +
                "activityID=" + super.getActivityID() +
                ", timeLength=" + super.getTimeLength() +
                ", date=" + super.getDate() +
                ", description='" + super.getDescription() +
                ", eventType='" + eventType + '\'' +
                ", friendsInvolved='" + friendsInvolved + '\'' +
                '}';
    }

    //This method prints that this is a social activity...
    @Override
    public void printActivityType() {
        System.out.println("Social Activity");
    }
    //This method prints the description and notifies the user of the object being described...
    @Override
    public void printDescription() {
        System.out.println("Social Activity Description: " + this.getDescription());
    }
    @Override
    public String neatString(){
        String outputText = String.format("%s%10s\n%s%10s\n%s%10s"
                + "\n%s%10s\n%s%10s\n", "ActivityID:",
                this.getActivityID(), "Time:", this.getTimeLength(),
                "Description:", this.getDescription(), "Event Type: ",
                this.getEventType(), "Participants:", this.getFriendsInvolved());
        return outputText;
    }
}
