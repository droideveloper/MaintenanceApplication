/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.controllers;

import java.util.List;
import org.fs.maintenance.entities.Department;
import org.fs.maintenance.entities.Task;
import org.fs.maintenance.entities.TaskStatus;
import org.fs.maintenance.entities.User;

/**
 *
 * @author Fatih
 */
public interface ICompleteTaskController {
    
    /**
     * performs save operations if the selected task is valid to complete and state is selected as 
     * COMPLETE and also need to be PROGRESS previously
     */
    void save();
    
    /**
     * perform back operations 
     */
    void back();
    
    /**
     * Selects index of the tasks selected from the List
     * @param index 
     */
    void selectAt(int index);
    
    /**
     * Displays task related information in the screen
     * @param t 
     */
    void dispalyTask(Task t);
    
    /**
     * Displays number of tasks status from the taskStatuses enumeration values
     * @param tasksStatuses 
     */
    void displayTaskStatus(List<TaskStatus> tasksStatuses);
    
    /**
     * Collects the task for next level of the use in the future. 
     * @return 
     */
    List<TaskStatus> retrieveTaskStatus();
    
    /**
     * 
     * @param usr
     * @return 
     */
    Department retrieveDepartmentByUser(User usr);
    
    /**
     * Validates if the tasks status can be approved for future
     * @return 
     */
    boolean validate();
}
