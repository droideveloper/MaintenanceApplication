/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.views;

import java.util.Date;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import org.fs.maintenance.controllers.IListReportController;

/**
 *
 * @author Fatih
 */
public interface IListReportView extends IView {
    
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
    void back();
    
    /**
     * 
     */
    void filter();
    
    /**
     * 
     * @param startDate 
     */
    void startDate(Date startDate);
    
    /**
     * 
     * @param endDate 
     */
    void endDate(Date endDate);    
        
    /**
     * 
     * @param title
     * @param value 
     */
    void displayMean(String title, String value);
    
    /**
     * 
     * @param listModel 
     */
    void displayReports(ListModel listModel);
    
    /**
     * 
     * @param controller 
     */
    void setController(IListReportController controller);
    
    /**
     * 
     * @param renderer 
     */
    void setListRenderer(ListCellRenderer renderer);
    
    /**
     * 
     */
    void enabledFilter();
    
    /**
     * 
     */
    void disableFilter();
    
    /**
     * 
     * @return 
     */
    String getWindowName();
}
