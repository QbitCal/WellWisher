
package Model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *  ToDoList used to create a list of Tasks (using a LinkedList)
 *  that allows the user to add and remove tasks to the list.
 * 
 * @author Quenten Calvano
 */
public class ToDoList {
    
        private static LinkedList<Task> toDos = new LinkedList<Task>();
        private LocalDate listDate = LocalDate.now();
        private int taskNum = 0;
        
    public ToDoList() {
        //Date of the todo list is decided upon object creation.
        this.createTestList();
        this.printToDoList();
    }
    
    public void addTask(Task newTask) {
        boolean taskAdded = false;
        boolean insertDecision = false;
        ListIterator<Task> taskIterator = toDos.listIterator();
        //Decide where to insert the task based on insertDecision...
        while(taskIterator.hasNext()){
            insertDecision = taskIterator.next().decideToInsert(newTask);
            if(insertDecision){
                toDos.add(taskIterator.previousIndex(), newTask);
                taskAdded = true;
                break;
            }
        }
        //Add Task to the end of the list...
        if(taskAdded == false){
            //Add to end of list...
            toDos.add(newTask);
        }
    }
    
    public void removeTask(Task taskToRemove) {
        //Remove a task from the toDo list...
        toDos.remove(taskToRemove);
    }
    //getTask method for 
    public Task getTask(int id) {
        Task task = new Task();
        ListIterator<Task> taskIterator = toDos.listIterator();
        while (taskIterator.hasNext()) {
            task = taskIterator.next();
            if (task.getTaskID() == id) {
                return task;
            }
        }
        return null;
    }
    
    public void createTestList() {
        //Insert the head value into the toDos LinkedList...
        this.toDos.add(new Task(
                taskNum, "Task number " + taskNum++, this.listDate));
        //Create 9 more values to add to the LinkedList...
        for (int i = 0; i < 9; i++) {
            this.addTask(new Task(
                taskNum, "Task number " + taskNum++, this.listDate));
        }
    }
    
    public void printToDoList(){
        //Iterate through each task in the list...
        ListIterator<Task> orderIterator = toDos.listIterator();
        //Print each object value to the console as a string...
        while(orderIterator.hasNext()){
            System.out.println(orderIterator.next().toString());
        }
    }
}


    
