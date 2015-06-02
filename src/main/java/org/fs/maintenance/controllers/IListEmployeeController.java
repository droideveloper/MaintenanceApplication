/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.controllers;

import java.util.List;
import org.fs.maintenance.entities.Employee;

/**
 *
 * @author Fatih
 */
public interface IListEmployeeController {
    
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
     * @param employees 
     */
    void displayEmployees(List<Employee> employees);
        
    /**
     * 
     * @param employee 
     */
    void displayEmployeeDetail(Employee employee);
    
    /**
     * 
     * @return 
     */
    List<Employee> getEmployees();
    
    /**
     * 
     * @return 
     */
    boolean validate();
}
