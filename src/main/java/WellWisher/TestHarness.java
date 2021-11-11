package WellWisher;

import Model.PhysicalActivity;
import Model.SocialActivity;
import Model.Activity;
import Model.UserAccount;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This class is used to test the Tracker and UserAccount classes
 * as well as test for Inhertance and Polymorphism within the
 * Activity superclass and SocialActivity and PhysicalActivity
 * subclasses.
 *
 * @author Quenten Calvano
 * @version 9/17/2021
 */

public class TestHarness {

    public TestHarness() {

        //Initiate the Tracker Test...
        //testTracker();
        //Initiate the UserAccount Test...
        //testUserAccount();
        //Test PolyMorphism...
        //testPolyMorphism();
        //Test Interface...
        testInterface();


    }
    public static void testTracker(){
        System.out.println("\nTesting the Tracker class:");
        //Test the instrument constructor...
        Tracker tracker = new Tracker("track1", "This is a test tracker");
        if (tracker != null) {
            System.out.println("Test Tracker successfully created.");

            //Test the Get Methods...
            System.out.println("\nTest Tracker information:");
            System.out.printf("Name: %s\n", tracker.getName());
            System.out.printf("Description: %s\n",tracker.getDescription());
            System.out.printf("Count: %d\n",tracker.getCount());
            System.out.printf("Date Started: %s\n",tracker.getDateStarted().toString());

            //Test the Set methods...
            System.out.println("\nTesting mutator methods:");
            tracker.setName("tracker2");
            System.out.println("Name changed to: " + tracker.getName());
            tracker.setDescription("Another description of the test tracker.");
            System.out.println("Description changed to: " + tracker.getDescription());
            tracker.setCount(1);
            System.out.println("Count set to: " + tracker.getCount());

            //Declare a new LocalDate variable to a different date...
            LocalDate date = null;
            date = date.of(2021, 9, 8);
            tracker.setDateStarted(date);
            System.out.println("Date set to: " + tracker.getDateStarted());

            //Test toString method...
            System.out.println("\nTesting toString() method:");
            String results = tracker.toString();
            //If the string is not null, print it. Otherwise inform the user of the error...
            if (results != null) {
                System.out.println("Results of the toString() method call:\n" + results);
            }else {
                System.out.println("Error: toString method returned null.");
            }
        }
        else {
            //Inform the user that there was an error creating the Tracker...
            System.out.println("Error creating Tracker object.");
        }

    }
    public static void testUserAccount() {
        //Test the creation of UserAccount...
        System.out.println("\nTesting UserAccount class:");
        //Create a UserAccount from each Constructor...
        UserAccount user1 = new UserAccount("Username 1","Password 1");
        UserAccount user2 = new UserAccount("Username 2", "Password 2", 230.5);

        //If both test accounts were created, print their data...
        if (user1 != null && user2 != null) {
            //Test the Get methods...
            System.out.println("Test UserAccounts successfully created from both constructors.");
            System.out.println("Test UserAccount information for test account 1:");
            System.out.println("Username: " + user1.getUsername());
            System.out.println("Password: " + user1.getPassword());
            System.out.println("Weight (-1.0 if left blank): " + user1.getWeight() + "lbs.");
            System.out.println("\nTest UserAccount information for test account 2:");
            System.out.println("Username: " + user2.getUsername());
            System.out.println("Password: " + user2.getPassword());
            System.out.println("Weight (-1.0 if left blank): " + user2.getWeight() + "lbs.");

            //Test the Set methods...
            System.out.println("\nTesting mutator methods on test account 1:");
            user1.setUsername("Other_Username");
            System.out.println("Username set to: " + user1.getUsername());
            user1.setPassword("DifferentPassword!");
            System.out.println("Password set to: " + user1.getPassword());
            user1.setWeight(200.0);
            System.out.println("Weight set to: " + user1.getWeight());
            System.out.println();

            //Test the toString method...
            System.out.println("\nTesting toString() method on test account 1:");
            String results = user1.toString();
            //If the string returns not null, print it. Otherwise, inform the user of the error.
            if (results != null) {
                System.out.println("Results of the toString() method call:\n" + results);
            }else {
                System.out.println("Error: toString method returned null.");
            }
        }else {
            //Inform the user of an error creating the UserAccount object...
            System.out.println("Error creating UserAccount object in one or both constructors.");
        }
    }
    public static void testPolyMorphism() {
        //Describe to the user the test to be performed...
        System.out.println("\nTesting Inheritance & PolyMorphism:");

        System.out.println("Activity ArrayList created.");
        //Create a new ArrayList of activities...
        ArrayList<Activity> activities = new ArrayList<>();

        System.out.println("Adding two PhysicalActivity Objects...");
        //Add physical activities to test...
        activities.add(new PhysicalActivity(11, 30.0
                , LocalDate.of(2021, 9, 17)
                , "Jogged outside.", "Moderate", 0 ));
        activities.add(new PhysicalActivity(12, 25.0
                , LocalDate.of(2021, 9, 18)
                , "Lifted Weights.", "High", 50 ));

        System.out.println("Adding two Social Activity Objects...");
        //Add Social activities to test...
        activities.add(new SocialActivity(13, 60.0
                , LocalDate.of(2021, 9, 20)
                , "I went out for my lunch break", "Lunch"
                , "Mike and Aaron"));
        activities.add(new SocialActivity(14, 120.0
                , LocalDate.of(2021, 9, 20)
                , "I went out on Friday night", "Party"
                , "Tom and Melissa"));

        //Set an index to inform the user what entry they see...
        int i = 0;
        System.out.println("Testing the toString() & printActivityType() methods...");

        //Test for PolyMorphism...
        for (Activity activity : activities) {
            System.out.println("\nActivity at index " + i++ + ":");
            //Test the toString method...
            System.out.println(activity.toString());
            System.out.print("Type: ");
            //Test the printActivityType method...
            activity.printActivityType();

        }
    }
    public static void testInterface() {
        System.out.println();

        //Create an ArrayList of Printable objects...
        ArrayList<Printable> thingsToPrint = new ArrayList<>();

        //Add a new activity...
        thingsToPrint.add(new Activity(1,
                32.5,
                LocalDate.of(2021, 9, 4),
                "Went on a walk in the park"));

        //Add a new Tracker...
        thingsToPrint.add(new Tracker("track1", "This is a test tracker"));

        //Add a new Social Activity...
        thingsToPrint.add(new SocialActivity(13, 60.0
                , LocalDate.of(2021, 9, 20)
                , "I went out for my lunch break", "Lunch"
                , "Mike and Aaron"));

        //Add a new Physical Activity...
        thingsToPrint.add(new PhysicalActivity(12, 25.0
                , LocalDate.of(2021, 9, 18)
                , "Lifted Weights.", "High", 50 ));


        //Test for Polymorphism through the printable interface...
        for (Printable item: thingsToPrint) {
            item.print();
            item.printDescription();
            System.out.println();
        }
    }
}
