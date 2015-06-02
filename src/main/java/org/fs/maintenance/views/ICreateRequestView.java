/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.views;

import org.fs.maintenance.controllers.ICreateRequestController;

/**
 *
 * @author Fatih
 */
public interface ICreateRequestView extends IView {
    
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
     */
    void apply();
    
    /**
     * 
     */
    void back();
    
    /**
     * 
     */
    void displayRequestsHistory();
    
    /**
     * 
     * @return 
     */
    String getTitle();
    
    /**
     * 
     * @return 
     */
    String getDescription();
    
    /**
     * 
     * @return 
     */
    String getWindowName();
    
    /**
     * 
     */
    void clear();
    
    /**
     * 
     * @param controller 
     */
    void setController(ICreateRequestController controller);
}
