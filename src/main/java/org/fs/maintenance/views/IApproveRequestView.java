/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.views;

import org.fs.maintenance.controllers.IApproveRequestController;
import org.fs.maintenance.entities.Request;

/**
 *
 * @author Fatih
 */
public interface IApproveRequestView extends IView {
    
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
    void reject();
    
    /**
     * 
     */
    void approve();
    
    /**
     * 
     * @param r 
     */
    void displayRequestDetail(Request r);
    
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
     * @param controller 
     */
    void setController(IApproveRequestController controller);
    
    /**
     * 
     * @return 
     */
    String getWindowName();
}
