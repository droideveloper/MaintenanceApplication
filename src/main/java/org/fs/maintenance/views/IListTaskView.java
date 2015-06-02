/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.views;

import javax.swing.ListModel;
import org.fs.maintenance.controllers.IListTaskController;
import org.fs.maintenance.entities.Task;

/**
 *
 * @author Fatih
 */
public interface IListTaskView extends IView { 
    
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
     */
    void init();
    
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
     * @param t 
     */
    void displayDetail(Task t);
    
    /**
     * 
     * @param taskModel 
     */
    void displayTasks(ListModel<Task> taskModel);
    
    /**
     * 
     * @param controller 
     */
    void setController(IListTaskController controller);
    
    /**
     * 
     * @return 
     */
    String getWindowName();
}
