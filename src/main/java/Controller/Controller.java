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
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 *
 * @author Quenten Calvano
 * @version 10/22/2021
 */
public class Controller implements ActionListener {
    
    //FIXME: This needs a username assignment to be given to it in the Constructor...
    private static ActivityList activities = new ActivityList("User 1");
    private static JFrame masterFrame = new JFrame();
    private static JFrame login = new LoginUI();
    private static ActivityUI activityManager = new ActivityUI();
    private static JFrame addPhysical = new AddPhysicalActivity();
    private static JFrame addSocial = new AddSocialActivity();
    private static WeekLogUI weekLog;
    private static UpdatePhysicalActivity updatePhysical;
    private static UpdateSocialActivity updateSocial;
    private static PhysicalActivity tempPhys;
    private static SocialActivity tempSoc;
    private static ActivityTableModel activityTable;
    private static ActivityListUI activityList;
    private static ActivityDetailUI activityDetail;
    private static AddDetailPhysicalActivity detailPhysical = new AddDetailPhysicalActivity();
    private static AddDetailSocialActivity detailSocial = new AddDetailSocialActivity();
    private static PhysicalOrSocial physOrSoc = new PhysicalOrSocial();
    private static int currentActivity = 0;
    private static String currentUser;
        
    //Constructor...
    public Controller (){
        
        
        
        //createTestActivities();//Create the activities for initial testing.
        sortActivityLog(); //Sort the activity log entries.
        weekLog = new WeekLogUI(activities);//Add the test entries.
        activityTable = new ActivityTableModel(activities.getActivityList());
        activityList = new ActivityListUI(activityTable);
        //Initialize detail view with the first entry...
        activityDetail = new ActivityDetailUI(activities.getActivityList(), 0);
        addALButtons(); //Initialize button action listeners.
        
        //showLogin(); //Open the login screen.
        
        //Skip login and show activity manager for testing...
        showActivityManager();
    }
    
    //Getters and Setters...
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
    
    public static void addActivity(Activity act) {
        activities.getActivityList().add(act);
    }
    
    //Get the latest WeekLog from the list...
    public static Activity getLatestWeekLog(ActivityList activities){
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
    
    //This method simply uses the collected physical activity data to create and
    //return a physical activity object...
    //
    //TODO: Validate input to make sure that the values entered are logical...
    public static PhysicalActivity validatePhysicalActivity(int id, double timeLength,
            int day, int month, int year, String description, String intensity,
            int weight) throws Exception {
        PhysicalActivity activity = new PhysicalActivity();

        LocalDate date = LocalDate.of(year, month, day);
        
        activity = new PhysicalActivity(id, timeLength, date, description, intensity, weight);
        
        return activity;
    }
    
    //This method simply uses the collected social activity data to create and
    //return a social activity object...
    //
    //TODO: Validate input to make sure that the values entered are logical...
    public static SocialActivity validateSocialActivity(int id, double timeLength,
            int day, int month, int year, String description, String actType, 
            String company) throws Exception {
        SocialActivity activity = new SocialActivity();
        
        LocalDate date = LocalDate.of(year, month, day);
        
        activity = new SocialActivity(id, timeLength, date, description, actType, company);
        
        return activity;
    }
    
    
    //This method brings fortht the first activity log in the weeklog view...
    public static void setFirstActivityLog(JTextArea bodyText, JLabel dateText){
        sortActivityLog();
        Activity act = activities.getActivityList().get(activities.getActivityList().size() - 1);
        currentActivity = activities.getActivityList().size() - 1;
        bodyText.setText(act.neatString());
        dateText.setText(
            act.getDate().getMonth() + " " +
            act.getDate().getDayOfMonth() + ", " +
            act.getDate().getYear() + " - " + act.getDate().getDayOfWeek());
        setPageLocation();//Set the page location.

    }
    public static void parseActivityLog(Activity act) {
        weekLog.getBodyText().setText(act.neatString());
        weekLog.getDateText().setText(
            act.getDate().getMonth() + " " +
            act.getDate().getDayOfMonth() + ", " +
            act.getDate().getYear() + " - " + act.getDate().getDayOfWeek());
    }
    
    
    //Create activities and add them to the activity list for testing...
    public static void createTestActivities(){
        
        activities.getActivityList().add(new PhysicalActivity(11, 30.0
                , LocalDate.of(2021, 9, 21)
                , "Jogged outside.", "Moderate", 0 ));
        activities.getActivityList().add(new PhysicalActivity(12, 25.0
                , LocalDate.of(2021, 9, 13)
                , "Lifted Weights.", "High", 50 ));
        System.out.println("Created Default Physical activities.");
        
        activities.getActivityList().add(new SocialActivity(13, 60.0
                , LocalDate.of(2021, 9, 30)
                , "I went out for my lunch break", "Lunch"
                , "Mike and Aaron"));
        activities.getActivityList().add(new SocialActivity(14, 120.0
                , LocalDate.of(2021, 9, 10)
                , "I went out on Friday night", "Party"
                , "Tom and Melissa"));
        System.out.println("Created Default Social Activities.");
    }

    //This method is used to sort the activities arraylist to make sure that
    //each time it is entered it is in order by date.
    public static void sortActivityLog(){
        Collections.sort(activities.getActivityList(), Activity.sortByDate());
    }
    
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
    
    //Use this method to update the page location identifier...
    public static void setPageLocation () {   
        weekLog.getPageLocation().setText(
                (currentActivity + 1) + " / " + activities.getActivityList().size());
    }
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
                    activities.getActivityList().remove(activities.getActivityList().get(currentActivity));
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
