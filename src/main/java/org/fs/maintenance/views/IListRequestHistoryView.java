/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.views;

import javax.swing.ListModel;
import org.fs.maintenance.controllers.IListRequestHistoryController;
import org.fs.maintenance.entities.Request;

/**
 *
 * @author Fatih
 */
public interface IListRequestHistoryView extends IView {
    
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
     * @param request 
     */
    void displayRequestDetail(Request request);
    
    /**
     * 
     * @param index 
     */
    void selectAt(int index);
    
    /**
     * 
     * @param model 
     */
    void displayRequests(ListModel<Request> model);
    
    /**
     * 
     * @param controller 
     */
    void setController(IListRequestHistoryController controller);
    
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
     * @return 
     */
    String getWindowName();
}
