
package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Tasks used in the ToDoList function of the WellWisher
 * application.
 *
 * @author Quenten Calvano
 */
public class Task {
    
    private int taskID;
    private String taskDescription;
    private LocalDate taskDate;
    private boolean complete = false;
    
    public Task (int taskID, String taskDescription, LocalDate taskDate) {
        this.taskID = taskID;
        this.taskDescription = taskDescription;
        this.taskDate = taskDate;
    }
    public Task () {
        
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public LocalDate getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(LocalDate taskDate) {
        this.taskDate = taskDate;
    }
    
    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
    
    public boolean decideToInsert(Task task){
        boolean decision = false;
        
        // TODO: fix this logic...
        if (task.taskID < this.taskID) {
            decision = true;
        }
        
        return decision;
    }   
    
    
    @Override
    public String toString() {
        return String.valueOf("Task " 
                + taskID 
                +  ": \"" + taskDescription + "\", " 
                + taskDate.format(
                    DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                
    }
    
}
