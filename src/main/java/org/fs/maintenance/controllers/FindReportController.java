/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.controllers;

import java.util.Arrays;
import java.util.List;
import org.fs.maintenance.base.AbstractController;
import org.fs.maintenance.common.SimpleReportTypeModel;
import org.fs.maintenance.entities.ReportType;
import org.fs.maintenance.gui.ListReportUI;
import org.fs.maintenance.views.IFindReportView;

/**
 *
 * @author Fatih
 */
public class FindReportController extends AbstractController<IFindReportView> implements IFindReportController {

    private final MainController controller;
    
    private List<ReportType> reportTypes;
    private ReportType       reportType = null;
    
    private FindReportController(IFindReportView view, MainController controller) {
        super(view);
        this.controller = controller;
    }
    
    public static FindReportController create(IFindReportView view, MainController controller) {
        return new FindReportController(view, controller);
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        view.setController(this);
    }

    @Override
    protected void onStart() {
        super.onStart();    
        if(reportTypes == null) {
            reportTypes = retrieveReportTypes();
        }
        displayReportTypes(reportTypes);
    }    
    
    @Override
    protected String getWindowName() {
        return view.getWindowName();
    }

    @Override
    public void selectReportTypeAt(int index) {
        reportType = reportTypes.get(index);
    }

    @Override
    public void displayReportTypes(List<ReportType> reportTypes) {
        if(reportTypes != null) {
            view.displayReportTypes(new SimpleReportTypeModel(reportTypes));
        }
    }

    @Override
    public void back() {
        disposeView();
        controller.toggleView();
    }

    @Override
    public void find() {
        if(validate()) {
            toggleView();
            ListReportController.create(ListReportUI.createView(), this, reportType);
        }
    }
    
    @Override
    public boolean validate() {
        if(reportType != null  && !reportType.equals(ReportType.SELECT_TASK_REPORT)) {
            return true;            
        }
        view.showError("you should selected a report type");
        return false;
    }   
    
    @Override
    public List<ReportType> retrieveReportTypes() {
        return Arrays.asList(ReportType.values());
    }
}
