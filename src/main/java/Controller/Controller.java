package Controller;

import Model.ActivityList;
import Model.ActivityTableModel;
import View.*;
import View.NewDetailViews.AddDetailPhysicalActivity;
import View.NewDetailViews.AddDetailSocialActivity;
import View.NewDetailViews.PhysicalOrSocial;
import Model.Activity;
import Model.PhysicalActivity;
import Model.SocialActivity;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Quenten Calvano
 * @version 10/22/2021
 */
public class Controller implements ActionListener {
    
    private static ActivityList activities; //Activity list that handles logs.
    private static JFrame masterFrame = new JFrame();//The frame set to visible.
    private static JFrame login; //The login JFrame variable.
    private static ActivityUI activityManager = new ActivityUI();//Main menu.
    //These two JFrames add the two types of activity objects...
    private static JFrame addPhysical = new AddPhysicalActivity();//Add Physical.
    private static JFrame addSocial = new AddSocialActivity();//Add Social.
    private static WeekLogUI weekLog;//The weekLog JFrame to individual entries.
    private static UpdatePhysicalActivity updatePhysical;//Update entry JFrame.
    private static UpdateSocialActivity updateSocial;//Update entry JFrame.
    private static PhysicalActivity tempPhys;//Temporary Physical Activity.
    private static SocialActivity tempSoc;//Temporary Social Activity.
    private static ActivityTableModel activityTable;//Creates list view table.
    private static ActivityListUI activityList;//Shows the list view.
    private static ActivityDetailUI activityDetail;//Shows the detail view.
    private static AddDetailPhysicalActivity detailPhysical 
            = new AddDetailPhysicalActivity();//Adds entry from the detail view.
    private static AddDetailSocialActivity detailSocial 
            = new AddDetailSocialActivity();//Adds entry from the detail view.
    private static PhysicalOrSocial physOrSoc 
            = new PhysicalOrSocial();//Identifier for physical or social logs.
    private static int currentActivity = 0;//Stores the current activity index.
    private static String currentUser;//The username of the current user.
        
    //Constructor...
    public Controller (){
        //Assign the login JFrame to a new LoginUI object 
        //using this controller...
        login = new LoginUI(this);
        //Open the login screen (Required step to access other views)...
        showLogin();
    }

    //Getters and Setters...
    public static String getCurrentUser() {
        return currentUser;
    }
    public static void setCurrentUser(String currentUser) {    
        Controller.currentUser = currentUser;
    }
    public ActivityList getActivities() {
        return activities;
    }
    public void setActivities(ActivityList activities) {
        this.activities = activities;
    }
    public static ActivityTableModel getActivityTable() {
        return activityTable;
    }
    
    //Methods to show the various views...
    public static void showLogin () {
        masterFrame.setVisible(false);
        masterFrame = login;
        masterFrame.setVisible(true);    
    }
    public static void showActivityManager () {
        masterFrame.setVisible(false);
        if (currentUser == null) {
            System.out.println("username is not filled.");
            currentUser = "NULL USER";
        }
        masterFrame = activityManager;
        masterFrame.setVisible(true);
    }
    public static void showAddPhysical () {
        masterFrame.setVisible(false);
        masterFrame = addPhysical;
        masterFrame.setVisible(true);
    }
    public static void showAddSocial () {
        masterFrame.setVisible(false);
        masterFrame = addSocial;
        masterFrame.setVisible(true);
    }
    public static void showWeekLog () {
        masterFrame.setVisible(false);
        masterFrame = weekLog;
        masterFrame.setVisible(true);
    }
    public static void showUpdateSocial(){
        masterFrame.setVisible(false);
        masterFrame = updateSocial;
        masterFrame.setVisible(true);
    }
    public static void showUpdatePhysical(){
        masterFrame.setVisible(false);
        masterFrame = updatePhysical;
        masterFrame.setVisible(true);
    }
    public static void showActivityListUI(){
        masterFrame.setVisible(false);
        activityTable = new ActivityTableModel(activities.getActivityList());
        activityList.table.setModel(new ActivityTableModel(activities.getActivityList()));
        masterFrame = activityList;
        masterFrame.setVisible(true);
    }
    public static void showActivityDetailUI(){
        masterFrame.setVisible(false);
        masterFrame = activityDetail;
        masterFrame.setVisible(true);
    }
    public static void showPhysicalOrSocialUI(){
        masterFrame.setVisible(false);
        masterFrame = physOrSoc;
        masterFrame.setVisible(true);
    }
    public static void showDetailAddPhys(){
        masterFrame.setVisible(false);
        masterFrame = detailPhysical;
        masterFrame.setVisible(true);
    }
    public static void showDetailAddSoc(){
        masterFrame.setVisible(false);
        masterFrame = detailSocial;
        masterFrame.setVisible(true);
    }
  
  /**
    * This method is used to initialize the data structures required for the 
    * application's functionality upon login.
    * 
    * NOTE: Login is necessary to continue here. Activity logs require the 
    * current user's username to progress in the application.
    * 
    */
    public static void initStructs(){
        activities = new ActivityList(currentUser);
        sortActivityLog(); //Sort the activity log entries.
        weekLog = new WeekLogUI(activities);//Add the test entries.
        activityTable = new ActivityTableModel(activities.getActivityList());
        activityList = new ActivityListUI(activityTable);
        //Initialize detail view with the first entry...
        activityDetail = new ActivityDetailUI(activities.getActivityList(), 0); 
    }
    
  /**
    * This method is used to add action listeners to each of the necessary 
    * buttons throughout the application's various views.
    * 
    */
    public void addALButtons(){
        weekLog.btnPrevious.addActionListener(this);//Listen for btnPrevious.
        weekLog.btnNext.addActionListener(this);//Listen for btnNext.
        weekLog.btnDelete.addActionListener(this);//Listen for btnDelete.
        weekLog.btnUpdate.addActionListener(this);//Listen for btnUpdate.
        activityManager.btnLogList.addActionListener(this);//Listen for List View.
        activityList.btnNew.addActionListener(this);//Listen for btnNew.
        activityList.btnDetails.addActionListener(this);//Listen for btnDetails.
        activityList.btnDone.addActionListener(this);//Listen for btnDone.
    }
    
  /**
    * Accepts an Activity object and adds it to the controller's activity list.
    * The newly updated list is then written back to the serialized file for
    * the current user.
    * 
    *
    * @param  act  an activity object to be added to the activity list.
    */
    public static void addActivity(Activity act) {
        activities.getActivityList().add(act);
        activities.writeActivityListFile();
    }
    
  /**
    * Accepts an ActivityList object and retrieves the latest week log entry
    * from the list so that it can be presented to the user upon moving to the
    * weekLogUI view.
    * 
    *
    * @param  activities  an ActivityList to pull the most recent entry from.
    * @return act         an Activity object with the most recent LocalDate value.
    */
    public static Activity getLatestWeekLog(ActivityList activities){
        //First, make sure the list has been sorted.
        sortActivityLog();
        Activity act = null;
        try {
            act = activities.getActivityList()
                .get(activities.getActivityList().size() - 1);
        }catch(IndexOutOfBoundsException e){
            System.out.println("There are no current entries in the Week Log.");
        }
        return act;
    }
    
  /**
    * This method uses the PhysicalActivity object collected from an entry form
    * to create and return a PhysicalActivity object for the list.
    * 
    *
    * @param  id            an int value for the activity's id.
    * @param  timeLength    a double value for time spent doing the activity.
    * @param  day           an int value for the day of the month (d or dd).
    * @param  month         an int value for the month of the year (mm).
    * @param  year          an int value for the year (yyyy).
    * @param  description   a String for the activity description.
    * @param  intensity     a String value for intensity taken from the list.
    * @param  weight        an int value for the approx. weight lifted/used.
    * 
    * @throws Exception   an exception is thrown if values cannot be added.
    * @return activity    an Activity object with the most recent LocalDate value.
    */
    public static PhysicalActivity validatePhysicalActivity(int id, double timeLength,
            int day, int month, int year, String description, String intensity,
            int weight) throws Exception {
        PhysicalActivity activity = new PhysicalActivity();
        LocalDate date = LocalDate.of(year, month, day);
        activity = new PhysicalActivity(id, timeLength, date, description
                , intensity, weight);
        return activity;
    }
    
  /**
    * This method uses the SocialActivity object collected from an entry form
    * to create and return a SocialActivity object for the list.
    * 
    *
    * @param  id            an int value for the activity's id.
    * @param  timeLength    a double value for time spent doing the activity.
    * @param  day           an int value for the day of the month (d or dd).
    * @param  month         an int value for the month of the year (mm).
    * @param  year          an int value for the year (yyyy).
    * @param  description   a String for the activity description.
    * @param  actType       a String for the type of activity (Party, Lunch, etc.)
    * @param  company       a String for the people there (Jack, Jill, etc.)
    * 
    * @throws Exception   an exception is thrown if values cannot be added.
    * @return activity    an Activity object with the most recent LocalDate value.
    */
    public static SocialActivity validateSocialActivity(int id, double timeLength,
            int day, int month, int year, String description, String actType, 
            String company) throws Exception {
        SocialActivity activity = new SocialActivity();
        LocalDate date = LocalDate.of(year, month, day);
        activity = new SocialActivity(id, timeLength, date, description
                , actType, company);
        return activity;
    }
    
  /**
    * Accesses the first activity log to be shown and populates the JLabel
    * showing the date of the log and the JTextArea showing the description
    * of the log entry.
    * 
    *
    * @param  bodyText  the JTextArea to display the details of the entry.
    * @param  dateText  the JLabel to display the date of the Activity log.
    */
    public static void setFirstActivityLog(JTextArea bodyText, JLabel dateText){
        sortActivityLog();//Start by sorting the log.
        Activity act = activities.getActivityList().get(activities.getActivityList().size() - 1);
        currentActivity = activities.getActivityList().size() - 1;
        bodyText.setText(act.neatString());
        dateText.setText(
            act.getDate().getMonth() + " " +
            act.getDate().getDayOfMonth() + ", " +
            act.getDate().getYear() + " - " + act.getDate().getDayOfWeek());
        setPageLocation();//Set the page location.

    }
    
  /**
    * Displays the Activity log entry in the weekLogUI as a title showing the
    * Activity's date and the Activity's details in the body text.
    * 
    * @param  act  the activity object to be parsed.
    */
    public static void parseActivityLog(Activity act) {
        weekLog.getBodyText().setText(act.neatString());
        weekLog.getDateText().setText(
            act.getDate().getMonth() + " " +
            act.getDate().getDayOfMonth() + ", " +
            act.getDate().getYear() + " - " + act.getDate().getDayOfWeek());
    }

  /**
    * This method is used to sort the activities ArrayList to order it by date.
    * 
    */
    public static void sortActivityLog(){
        Collections.sort(activities.getActivityList(), Activity.sortByDate());
    }
    
    
  /**
    * This method is called by the LoginUI to give this controller access to
    * the current user's username.
    * 
    * @param username A string value for the current user's username.
    * 
    */
    public void assignCurrentUser(String username){
        //Use this to assign the current user to 
        this.currentUser = username;
    }
    
  /**
    * Updates the page location identifier based on the current activity and
    * overall size of the Activity log list.
    * 
    */
    public static void setPageLocation () {   
        weekLog.getPageLocation().setText(
                (currentActivity + 1) + " / " + activities.getActivityList().size());
    }
    
  /**
    * Overrides the actionPerformed method of the ActionLister class to accept
    * an ActionEvent object, get the source, and perform an action based on the
    * source.
    * 
    * 
    * @param e some action event of the WellWisher application.
    * 
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Get the source of the action event...
        Object obj = e.getSource();
        try {
            //Handle the next button...
            if(obj == weekLog.btnNext){
                if(currentActivity == activities.getActivityList().size()-1){
                    currentActivity = 0;
                    //System.out.println("Going to beginning!");
                }else {
                    currentActivity++;
                    //System.out.println("Going to next!");
                }
                parseActivityLog(activities.getActivityList().get(currentActivity));
                setPageLocation();//Finally, set the page location.
            } 
            //Handle the previous button...
            else if(obj == weekLog.btnPrevious){
                if(currentActivity == 0){
                    currentActivity = activities.getActivityList().size()-1;
                    //System.out.println("Going to end!");
                }else {
                    currentActivity--;
                    //System.out.println("Going to previous!"); 
                }
                parseActivityLog(activities.getActivityList().get(currentActivity));
                setPageLocation();//Finally, set the page location.
            }  
            //The delete button removes the current entry and returns to 
            //the previous entry...
            else if (obj == weekLog.btnDelete){
                    //Remove the activity...
                    activities.getActivityList().remove(activities.getActivityList().get(currentActivity));
                    //Write the new activity list to the serialized file...
                    activities.writeActivityListFile();
                    
                    if (currentActivity != 0) {
                        currentActivity--;
                    } else {
                        currentActivity = activities.getActivityList().size() - 1;
                    }
                    weekLog.btnPrevious.doClick();
            }
            //Handle update button...
            else if (obj == weekLog.btnUpdate) {
                //For physical activities...
                if (activities.getActivityList().get(currentActivity).isPhysical()) {
                    System.out.println("Attempting to edit a physical activity.");
                    tempPhys = (PhysicalActivity)activities.getActivityList().get(currentActivity);
                    updatePhysical = new UpdatePhysicalActivity((PhysicalActivity)activities.getActivityList().get(currentActivity), this);
                    showUpdatePhysical();
                //For social activities...   
                } else {
                    System.out.println("Attempting to edit a social activity.");
                    tempSoc = (SocialActivity)activities.getActivityList().get(currentActivity);
                    updateSocial = new UpdateSocialActivity((SocialActivity)activities.getActivityList().get(currentActivity), this);
                    showUpdateSocial();
                }
            }
            //Handle the Activity List UI button...
            else if (obj == activityManager.btnLogList) {
                showActivityListUI();
            }
            else if (obj == activityList.btnNew) {
                showPhysicalOrSocialUI();
            }
            else if (obj == activityList.btnDetails) {
                
                int selectedTableRow = activityList.table.getSelectedRow();
                int selectedModelRow = activityList.table.convertRowIndexToModel(selectedTableRow);
                if(selectedModelRow < 0) { //if the user does not highlight a row, this will be -1
                    selectedModelRow = 0; //set the model row to the first element in the list
                }
                //Create the new ActivityDetailUI...
                activityDetail = new ActivityDetailUI(activities.getActivityList(), selectedModelRow);
                
                activityDetail.btnBack.addActionListener(this);
                //Show the detail view...
                showActivityDetailUI(); 
            }
            else if (obj == activityList.btnDone) {
                showActivityManager();//Go back to activity manager.
            }
            else if (obj == activityDetail.btnBack) {
                showActivityListUI();
            }  
        }catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("ERROR: An exception has occurred in " +
                            "handling the action of a button.");
                }
    }  
}
