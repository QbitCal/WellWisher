/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import WellWisher.*;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Quenten Calvano
 * @version 10/17/2021
 */
public class ActivityList {
    
    private ArrayList<Model.Activity> activityList = new ArrayList<>();
    private String activityListFilename;
    
    public ActivityList(String username) {
        activityListFilename = username.concat(".ser");
        this.readActivityFile();
        if (activityList.isEmpty() || activityList == null) {
            this.createTestActivities();
            this.writeActivityListFile();
            this.readActivityFile();
        }
    }
    

    public ArrayList<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(ArrayList<Activity> activityList) {
        this.activityList = activityList;
    }

    @Override
    public String toString() {
        
        for (Activity act : activityList){
            System.out.println(act.toString());
        }
        
        return "ActivityList{" + "activityList=" + activityList + '}';
    }

    private void readActivityFile() {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(activityListFilename);
            in = new ObjectInputStream(fis);
            activityList = (ArrayList) in.readObject();
            in.close();
            if (!activityList.isEmpty()) {
                System.out.println("There are activities in this list");
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: FileNotFoundException has occurred.");
        } catch (IOException ex) {
            System.out.println("ERROR: IOException has occurred.");
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR: ClassNotFoundException has occurred.");
        }
    }

    private void createTestActivities() {
        activityList.add(new PhysicalActivity(11, 30.0
                , LocalDate.of(2021, 9, 21)
                , "Jogged outside.", "Moderate", 0 ));
        activityList.add(new PhysicalActivity(12, 25.0
                , LocalDate.of(2021, 9, 13)
                , "Lifted Weights.", "High", 50 ));
        System.out.println("Created Default Physical activities.");
        
        activityList.add(new SocialActivity(13, 60.0
                , LocalDate.of(2021, 9, 30)
                , "I went out for my lunch break", "Lunch"
                , "Mike and Aaron"));
        activityList.add(new SocialActivity(14, 120.0
                , LocalDate.of(2021, 9, 10)
                , "I went out on Friday night", "Party"
                , "Tom and Melissa"));
        System.out.println("Created Default Social Activities.");
    }

    private void writeActivityListFile() {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(activityListFilename);
            out = new ObjectOutputStream(fos);
            out.writeObject(activityList);
            out.close();
        } catch (IOException ex) {
            System.out.println("ERROR: IOException has occurred.");
        }
    }   
}
