/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.controllers;

import java.util.List;
import org.fs.maintenance.entities.ReportType;

/**
 *
 * @author Fatih
 */
public interface IFindReportController {
    
    /**
     * 
     * @param index 
     */
    void selectReportTypeAt(int index);
       
    /**
     * 
     * @param reportTypes 
     */
    void displayReportTypes(List<ReportType> reportTypes);
    
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
     * @return 
     */
    boolean validate();
    
    /**
     * 
     * @return 
     */
    List<ReportType> retrieveReportTypes();
    
}
