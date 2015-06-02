/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.views;

import javax.swing.ListModel;
import org.fs.maintenance.controllers.IListEmployeeController;
import org.fs.maintenance.entities.Employee;
import org.fs.maintenance.entities.Task;

/**
 *
 * @author Fatih
 */
public interface IListEmployeeView extends IView {
    
    /**
     * 
     */
    void init();
    
    /**
     * 
     * @param message 
     */
    void showError(String message);
    
    /**
     * 
     */
    void hideError();
    
    /**
     * 
     * @param intex 
     */
    void selectAt(int intex);
    
    /**
     * 
     * @param empModel 
     */
    void displayEmployees(ListModel<Employee> empModel);
    
    /**
     * 
     * @param task 
     */
    void displayEmployeeDetail(Task task);
    
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
     * @param controller 
     */
    void setController(IListEmployeeController controller);
    
    /**
     * 
     * @return 
     */
    String getWindowName();
}
