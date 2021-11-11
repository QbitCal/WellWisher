package WellWisher;

import java.util.ArrayList;

import static java.lang.String.join;

/**
 *  The class used to record activities within the week log.
 *
 *
 * @author Quenten Calvano
 * @version 9/10/2021
 */

public class WeekLog {

    //Attributes...
    private ArrayList<Model.Activity> monday = new ArrayList<>();
    private ArrayList<Model.Activity> tuesday = new ArrayList<>();
    private ArrayList<Model.Activity> wednesday = new ArrayList<>();
    private ArrayList<Model.Activity> thursday = new ArrayList<>();
    private ArrayList<Model.Activity> friday = new ArrayList<>();
    private ArrayList<Model.Activity> saturday = new ArrayList<>();
    private ArrayList<Model.Activity> sunday = new ArrayList<>();

    //Constructors...
    public WeekLog(ArrayList<Model.Activity> monday, ArrayList<Model.Activity> tuesday, ArrayList<Model.Activity> wednesday, ArrayList<Model.Activity> thursday, ArrayList<Model.Activity> friday, ArrayList<Model.Activity> saturday, ArrayList<Model.Activity> sunday) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }
    public WeekLog() {
    }//Empty constructor used to add activity entries one at a time.


    //Setters and Getters...
    public ArrayList<Model.Activity> getMonday() {
        return monday;
    }
    public void setMonday(ArrayList<Model.Activity> monday) {
        this.monday = monday;
    }
    public ArrayList<Model.Activity> getTuesday() {
        return tuesday;
    }
    public void setTuesday(ArrayList<Model.Activity> tuesday) {
        this.tuesday = tuesday;
    }
    public ArrayList<Model.Activity> getWednesday() {
        return wednesday;
    }
    public void setWednesday(ArrayList<Model.Activity> wednesday) {
        this.wednesday = wednesday;
    }
    public ArrayList<Model.Activity> getThursday() {
        return thursday;
    }
    public void setThursday(ArrayList<Model.Activity> thursday) {
        this.thursday = thursday;
    }
    public ArrayList<Model.Activity> getFriday() {
        return friday;
    }
    public void setFriday(ArrayList<Model.Activity> friday) {
        this.friday = friday;
    }
    public ArrayList<Model.Activity> getSaturday() {
        return saturday;
    }
    public void setSaturday(ArrayList<Model.Activity> saturday) {
        this.saturday = saturday;
    }
    public ArrayList<Model.Activity> getSunday() {
        return sunday;
    }
    public void setSunday(ArrayList<Model.Activity> sunday) {
        this.sunday = sunday;
    }

    //This method prints each attribute value for the object into a string.
    @Override
    public String toString() {
        return "WeekLog{" +
                "monday=\n" + dayToString(monday) +
                ", tuesday=\n" + dayToString(tuesday) +
                ", wednesday=\n" + dayToString(wednesday) +
                ", thursday=\n" + dayToString(thursday) +
                ", friday=\n" + dayToString(friday) +
                ", saturday=\n" + dayToString(saturday) +
                ", sunday=\n" + dayToString(sunday) +
                '}';
    }

    //This method is used to take an array list of Activity objects and return them as a
    // single string of their individual toString results.
    public String dayToString(ArrayList<Model.Activity> activity) {
        //Create a new StringBuilder to add the date entries together...
        StringBuilder builder = new StringBuilder();
        for (Model.Activity instance : activity) {
            join(builder, instance.toString() + "\n");
        }
        //Save the collection of strings together
        String day = builder.toString();
        //return the list of activities for the day as a String...
        return day;
    }
}

