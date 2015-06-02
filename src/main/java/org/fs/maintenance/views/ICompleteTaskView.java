/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.views;

import javax.swing.ComboBoxModel;
import org.fs.maintenance.controllers.ICompleteTaskController;
import org.fs.maintenance.entities.Task;
import org.fs.maintenance.entities.TaskStatus;

/**
 *
 * @author Fatih
 */
public interface ICompleteTaskView extends IView {
    
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
     * @param index 
     */
    void selectAt(int index);
    
    /**
     * 
     * @param taskStatus 
     */
    void displayTaskStatus(ComboBoxModel<TaskStatus> taskStatus);
    
    /**
     * 
     * @param t 
     */
    void displayDetail(Task t);
    
    /**
     * 
     */
    void save();
    
    /**
     * 
     */
    void back();
    
    /**
     * 
     * @param controller 
     */
    void setController(ICompleteTaskController controller);
    
    /**
     * 
     * @return 
     */
    String getWindowName();
}
