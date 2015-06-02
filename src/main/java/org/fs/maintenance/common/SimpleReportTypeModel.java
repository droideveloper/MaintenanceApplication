/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.common;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import org.fs.maintenance.entities.ReportType;

/**
 *
 * @author Fatih
 */
public class SimpleReportTypeModel extends DefaultComboBoxModel<ReportType>{
    
    public SimpleReportTypeModel(List<ReportType> taskReports) {
        super();
        if(taskReports == null) {
            throw new NullPointerException("report types are null");
        }
        for(ReportType rt : taskReports) {
            addElement(rt);
        }
    }
}
