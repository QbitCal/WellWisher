package WellWisher;

/**
 * The class that is used to create and handle user-created reminders.
 *
 * @author Quenten Calvano
 * @version 9/4/2021
 */


public class Reminder {

    //Attributes...
    private String description; //What the user wants to remind themselves of.
    private int repeat; //Integer corresponding to the day of the
    // week (0-monday, 6-Sunday) to repeat the weekly reminder.
    private boolean active; //Boolean determining if the reminder is active or not.

    //Constructors...
    public Reminder(String description, int repeat, boolean active) {
        this.description = description;
        //Make sure number fits in range. Default to Monday if invalid.
        this.repeat = validateRepeat(repeat);
        this.active = active;
    }
    public Reminder(String description, int repeat) {
        this.description = description;
        //Make sure number fits in range. Default to Monday if invalid.
        this.repeat = validateRepeat(repeat);
        this.active = true; //default to true if not entered.
    }
    public Reminder() {
    }

    //Setters and Getters...
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getRepeat() {
        return repeat;
    }
    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    //This method makes sure that the repeat value the user enters corresponds to a day
    //of the week. If an invalid number is entered, returns -1.
    public static int validateRepeat (int repeat) {
        if (repeat < 7 && repeat >= 0) {
            //If valid day of week return entered value...
            return repeat;
        } else {
            //If invalid day of week return -1...
            return -1;
        }
    }

    //This method prints each attribute value for the object into a string.
    @Override
    public String toString() {
        return "Reminder{" +
                "description='" + description + '\'' +
                ", repeat=" + repeat +
                ", active=" + active +
                '}';
    }
}
