package WellWisher;

import Controller.Controller;
import Model.Task;
import Model.ToDoList;
import java.time.LocalDate;

/**
 *  The entry point to the Well-Wisher application.
 *
 *
 * @author Quenten Calvano
 * @version 11/13/2021
 */


public class Main {

    public static void main(String[] args) {
        //Initiate Tests via TestHarness...
        //TestHarness test = new TestHarness();
        //Controller controller = new Controller();
        
        testToDo();
    }
        
    public static void testToDo () {
        
        //Print initial toDo List...
        System.out.println("Initial test toDo list:");
        ToDoList toDo = new ToDoList();
        
        //Testing the ToDoList Only...
        System.out.println("\nTask Retrieved:");
        System.out.println(toDo.getTask(4).toString());
        
        System.out.println("Removing task 4: ");
        toDo.removeTask(toDo.getTask(4));
        
        System.out.println("\nTasks after removal:");
        toDo.printToDoList();
        
        Task newTask = new Task(4, "UpdatedTask", LocalDate.now());
        toDo.addTask(newTask);
        
        System.out.println("\nTasks after addition:");
        toDo.printToDoList();
    }
    
}
