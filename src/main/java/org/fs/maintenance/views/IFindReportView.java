/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.views;

import javax.swing.ComboBoxModel;
import org.fs.maintenance.controllers.IFindReportController;
import org.fs.maintenance.entities.ReportType;

/**
 *
 * @author Fatih
 */
public interface IFindReportView extends IView {
    
    /**
     * 
     */
    void init();
    
    /**
     * 
     * @param index 
     */
    void selectReportTypeAt(int index);
       
    /**
     * 
     * @param reportTypeModel 
     */
    void displayReportTypes(ComboBoxModel<ReportType> reportTypeModel);
        
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
    void back();
    
    /**
     * 
     */
    void find();    
    
    /**
     * 
     * @param controller 
     */
    void setController(IFindReportController controller);
    
    /**
     * 
     * @return 
     */
    String getWindowName();
}
