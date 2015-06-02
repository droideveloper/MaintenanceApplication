/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.controllers;

import java.util.Date;
import java.util.List;
import org.fs.maintenance.common.Pair;
import org.fs.maintenance.entities.Department;
import org.fs.maintenance.entities.Employee;
import org.fs.maintenance.entities.Task;

/**
 *
 * @author Fatih
 */
public interface IListReportController {
    
    /**
     * 
     */
    void back();
    
    /**
     * 
     */
    void filter();
    
    /**
     * 
     * @param startDate 
     */
    void startDate(Date startDate);
    
    /**
     * 
     * @param endDate 
     */
    void endDate(Date endDate);
    
    /**
     * 
     */
    void displayReports();
    
    /**
     * 
     * @return 
     */
    boolean validate();
    
    /**
     * 
     * @return 
     */
    List<Pair<Department, Integer>> retrieveDepartmentTasksCreated();
    
    /**
     * 
     * @return 
     */
    List<Pair<Employee, Task>> retreieveCurrentTaskOnEmployees();
    
    /**
     * 
     * @return 
     */
    List<Pair<Employee, Integer>> retreieveTasksAssignedToEmployees();
    
    /**
     * 
     * @return 
     */
    List<Pair<Employee, Integer>> retreieveTasksCompletedByEmployees();
}
