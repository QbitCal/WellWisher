package WellWisher;

/**
 * The class used to access weekly logs that exist in storage.
 *
 * @author Quenten Calvano
 * @version 9/10/2021
 */

import java.util.Date;

public class Logbook {

    //Attributes...
    private WeekLog week;//A temporary WeekLog to access the log to be retrieved from storage.
    private Date date; //The date used to specify the range of dates for the week log currently being retrieved.

    //Constructor...
    public Logbook(WeekLog week, Date date) {
        this.week = week;
        this.date = date;
    }

    //Setters and Getters...
    public WeekLog getWeek() {
        return week;
    }
    public void setWeek(WeekLog week) {
        this.week = week;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    //This method prints each attribute value for the object into a string.
    @Override
    public String toString() {
        return "Logbook{" +
                "week=" + week.toString() +
                ", date=" + date.toString() +
                '}';
    }
}

