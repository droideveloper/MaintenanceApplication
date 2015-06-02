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
public interface IListTaskController {
    
    /**
     * 
     */
    void back();
    
    /**
     * 
     */
    void select();
    
    /**
     * 
     * @param index 
     */
    void selectAt(int index);
    
    /**
     * 
     * @param tasks 
     */
    void displayTasks(List<Task> tasks);
    
    /**
     * 
     * @param task 
     */
    void displayTaskDetial(Task task);
    
    /**
     * 
     * @param e
     * @return 
     */
    List<Task> retreieveTasks(Employee e);
    
    /**
     * 
     * @param usr
     * @return 
     */
    Department retrieveDepartmentByUser(User usr);
    
    /**
     * 
     * @return 
     */
    boolean validate();
}
