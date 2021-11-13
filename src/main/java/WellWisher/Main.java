package WellWisher;

import Controller.Controller;
import Controller.LoginController;
import Model.ActivityList;

/**
 *  The entry point to the Well-Wisher application.
 *
 *
 * @author Quenten Calvano
 * @version 10/17/2021
 */


public class Main {

    public static void main(String[] args) {
        
        
        //Initiate Tests via TestHarness...
        //TestHarness test = new TestHarness();
        
        ActivityList list = new ActivityList("User 1");
        
        System.out.println(list.getActivityList().toString());
        
        Controller controller = new Controller();

    }

}
