/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * This class builds the columns for the tables included in the full week log
 * list view.
 * 
 *
 * @author Quenten Calvano
 */
public class ActivityTableModel extends AbstractTableModel {
    
    private String[] columnNames = {"Activity ID", "Description", "Date"};
    private ArrayList<Activity> activities;

    public String[] getColumnNames() {
        return columnNames;
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }
    
    public ActivityTableModel(ArrayList<Activity> newActivities){
        this.activities = newActivities;
        
    }
    
    public int getRowCount() {
    return activities.size();
    }
    
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    public Object getValueAt(int row, int col) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy");
        switch(col){
            case 0: return (Object) activities.get(row).getActivityID();
            case 1: return (Object) activities.get(row).getDescription();
            case 2: return (Object) activities.get(row).getDate().format(formatter);
            default: return null;
        }
    }

    
}
