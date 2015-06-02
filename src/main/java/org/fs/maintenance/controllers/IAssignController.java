/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.controllers;

import java.util.List;
import org.fs.maintenance.entities.Department;
import org.fs.maintenance.entities.Employee;
import org.fs.maintenance.entities.Task;
import org.fs.maintenance.entities.User;

/**
 *
 * @author Fatih
 */
public interface IAssignController {
    
    /**
     * Selects from combo box the index of the object in the list
     * @param index 
     */
    void selectAt(int index);
    
    /**
     * Displays the employee details in the view that is selected for the task assignment
     * @param e 
     */
    void displayEmployeeDetail(Employee e);
    
    /**
     * Displays current working tasks on (PROGRESS) of Task that this employee assigned
     * @param t 
     */
    void displayCurrentTaskDetail(Task t);
    
    /**
     * Displays the task detail about to assign selected employee
     * @param t 
     */
    void displayTaskDetail(Task t);
    
    /**
     * Display Task List that will be assigned to this employee
     * @param tasks 
     */
    void displayTasks(List<Task> tasks);
    
    /**
     * Retrieves tasks from database that has no assignment on it.
     * @return 
     */
    List<Task> retrieveTasks();
    
    /**
     * Gets the number of task this employee assigned and not closed so far.
     * @return 
     */
    int retrieveNumberOfTaskAssigned();
    
    /**
     * 
     * @param usr
     * @return 
     */
    Department retrieveDepartmentByUser(User usr);
    
    /**
     * performs back operation on the ListTask Controller
     */
    void back();
    
    /**
     * performs assignment if all is valid by selected object to the selected employee
     */
    void assign();
    
    /**
     * validates that if assignment can be performed
     * @return 
     */
    boolean validate();
}
