/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.views;

import javax.swing.ComboBoxModel;
import org.fs.maintenance.controllers.*;
import org.fs.maintenance.entities.*;

/**
 *
 * @author Fatih
 */
public interface IAssignView extends IView {
    
    /**
     * 
     */
    void init();
    
    /**
     * 
     * @param msg 
     */
    void showError(String msg);
    
    /**
     * 
     */
    void hideError();
    
    /**
     * 
     * @param e 
     */
    void displayEmployeeDetail(Employee e);
    
    /**
     * 
     * @param index 
     */
    void selectAt(int index);
    
    /**
     * 
     * @param t 
     */
    void displayCurrentTask(Task t);
    
    /**
     * 
     * @param t 
     */
    void displayTaskDetail(Task t);
    
    /**
     * 
     * @param tasks 
     */
    void displayTasks(ComboBoxModel<Task> tasks);
    
    /**
     * 
     * @param controller 
     */
    void setController(IAssignController controller);
    
    /**
     * 
     */
    void assign();
    
    /**
     * 
     */
    void back();
    
    /**
     * 
     * @return 
     */
    String getWindowName();
}
