/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.views;

import org.fs.maintenance.controllers.IMainController;

/**
 *
 * @author Fatih
 */
public interface IMainView extends IView {
    
    /**
     * 
     */
    void init();
    
    /**
     * 
     */
    void switchDepartmentManagerContent();
    
    /**
     * 
     */
    void switchDepartmentEmployeeContent();
    
    /**
     * 
     */
    void switchEmployeeContent();
    
    /**
     * 
     */
    void switchManagerContent();
    
    /**
     * 
     */
    void selectCreateRequest();
    
    /**
     * 
     */
    void selectApproveRequest();
    
    /**
     * 
     */
    void selectReports();
    
    /**
     * 
     */
    void selectCompleteTask();
    
    /**
     * 
     */
    void selectAssignTask();
    
    /**
     * 
     */
    void signout();
    
    /**
     * 
     * @return 
     */
    String getWindowName();
    
    /**
     * 
     * @param iMainController 
     */
    void setController(IMainController iMainController);   
}
