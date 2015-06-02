/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.views;

import javax.swing.ListModel;
import org.fs.maintenance.controllers.IListRequestController;
import org.fs.maintenance.entities.Request;

/**
 *
 * @author Fatih
 */
public interface IListRequestView extends IView {
    
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
     * @param reqModel 
     */
    void displayRequests(ListModel<Request> reqModel);
    
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
    void setController(IListRequestController controller);
    
    /**
     * 
     * @return 
     */
    String getWindowName();
}
