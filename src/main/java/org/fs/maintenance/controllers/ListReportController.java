/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.ListCellRenderer;
import org.fs.maintenance.base.AbstractController;
import org.fs.maintenance.common.*;
import org.fs.maintenance.entities.Department;
import org.fs.maintenance.entities.Employee;
import org.fs.maintenance.entities.ReportType;
import org.fs.maintenance.entities.Task;
import org.fs.maintenance.gui.DepartmentTaskCell;
import org.fs.maintenance.gui.EmployeeNumbersCell;
import org.fs.maintenance.gui.EmployeeProgressCell;
import org.fs.maintenance.views.IListReportView;

/**
 *
 * @author Fatih
 */
public class ListReportController extends AbstractController<IListReportView> implements IListReportController {   
    
    private final FindReportController controller;
    
    private final ReportType reportType;
    
    private IDatabaseManager dbManager;
    
    private DefaultListModel listModel  = null;
    private ListCellRenderer renderer   = null;
    
    private Date startDate = null;
    private Date endDate   = null;
    
    private ListReportController(IListReportView view, FindReportController controller, ReportType reportType) {
        super(view);
        this.controller = controller;
        this.reportType = reportType;
    }
    
    public static ListReportController create(IListReportView view, FindReportController controller, ReportType reportType) {
        return new ListReportController(view, controller, reportType);
    }

    @Override
    protected void onCreate() {
        super.onCreate(); 
        view.setController(this);
        dbManager = DatabaseManager.getSharedInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();       
        switch(reportType.getValue()) {
            case "employee_task_report": {
                listModel = new DefaultListModel<>();
                List<Pair<Employee, Integer>> data = retreieveTasksAssignedToEmployees();
                listModel.addElement(new Pair<Department, Integer>(null, null));
                for(Pair<Employee, Integer> item : data) {
                    listModel.addElement(item);
                }
                renderer = EmployeeNumbersCell.createView(1);
                displayReports();
                //calcualte mean
                double sum = 0.0;
                for(Pair<Employee, Integer> item : data) {
                    sum += item.getValue();
                }
                String title = "Mean Task(s) Assigned to Employees:";
                String value = String.format("%.2f task(s)", sum / (1.0 * data.size()));
                view.displayMean(title, value);
                break;
            }
            case "completed_task_report": {
                listModel = new DefaultListModel<>();
                List<Pair<Employee, Integer>> data = retreieveTasksCompletedByEmployees();
                listModel.addElement(new Pair<Department, Integer>(null, null));
                for(Pair<Employee, Integer> item : data) {
                    listModel.addElement(item);
                }
                renderer = EmployeeNumbersCell.createView(2);
                displayReports();
                //calculate mean
                double sum = 0.0;
                for(Pair<Employee, Integer> item : data) {
                    sum += item.getValue();
                }
                String title = "Mean Task(s) Completed by Employees:";
                String value = String.format("%.2f task(s)", sum / (1.0 * data.size()));
                view.displayMean(title, value);
                break;
            }
            case "progress_task_report": {
                listModel = new DefaultListModel<>();
                List<Pair<Employee, Task>> data = retreieveCurrentTaskOnEmployees();
                listModel.addElement(new Pair<Employee, Task>(null, null));
                for(Pair<Employee, Task> item : data) {
                    listModel.addElement(item);
                }
                renderer = EmployeeProgressCell.createView();
                displayReports();
                //calcualte mean
                List<Employee> employees = new ArrayList();
                for(Pair<Employee, Task> item : data) {
                    employees.add(item.getKey());
                }
                Set<Employee> set = new HashSet<>(employees);//best way to sort employees in distinct values :) bit hack but does the thing
                double sum = 0.0;
                for(Pair<Employee, Task> item : data) {
                    sum += 1.0;
                }
                String title = "Mean Task(s) Working On by Employees:";
                String value = String.format("%.2f task(s)", sum / (1.0 * set.size()));
                view.displayMean(title, value);
                break;
            }
            case "created_task_report": {
                listModel = new DefaultListModel<>();
                List<Pair<Department, Integer>> data = retrieveDepartmentTasksCreated();
                listModel.addElement(new Pair<Department, Integer>(null, null));
                for(Pair<Department, Integer> item : data) {
                    listModel.addElement(item);
                }
                renderer = DepartmentTaskCell.createView();
                displayReports();
                //calculate mean
                double sum = 0.0;
                for(Pair<Department, Integer> item : data) {
                    sum += item.getValue();
                }
                String title = "Mean Request(s) Created by Departments:";
                String value = String.format("%.2f task(s)", sum / (1.0 * data.size()));
                view.displayMean(title, value);
                break;
            }
        }      
    }    
    
    @Override
    protected String getWindowName() {
        return view.getWindowName();
    }

    @Override
    public void back() {
        disposeView();
        controller.toggleView();
    }

    @Override
    public void filter() {
        if(validate()) {
            //simply change data like we starting new
            onStart();
        }
    }

    @Override
    public void startDate(Date startDate) {
        this.startDate = startDate;
        if(this.endDate != null && this.startDate != null) {
            view.enabledFilter();
        } else {
            view.disableFilter();
        }        
    }

    @Override
    public void endDate(Date endDate) {
        this.endDate = endDate;
        if(this.endDate != null && this.startDate != null) {
            view.enabledFilter();
        } else {
            view.disableFilter();
        }
    }

    @Override
    public void displayReports() {
        if(listModel != null) {
            view.displayReports(listModel);
        }
        if(renderer != null) {
            view.setListRenderer(renderer);
        }
    }
    
    @Override
    public boolean validate() {
        if(endDate != null) {
            if(startDate != null) {
                return true;
            } else {
                view.showError("start date is null");
            }
        } else {
            view.showError("end date is null");            
        }
        return false;
    }
    
    @Override
    public List<Pair<Department, Integer>> retrieveDepartmentTasksCreated() {
        try {
            return dbManager.calculateNumberOfRequestsCreatedByEachDepartment(startDate, endDate);
        } catch(SQLException e) {            
        }
        return null;
    }
    
    @Override
    public List<Pair<Employee, Task>> retreieveCurrentTaskOnEmployees() {
        try {
            return dbManager.findTasksEachEmployeeCurrentlyWorkingOn(startDate, endDate);
        } catch(SQLException e) { 
        }
        return null;
    }
    
    @Override
    public List<Pair<Employee, Integer>> retreieveTasksAssignedToEmployees() {
        try {
            return dbManager.calculateNumberOfTasksAssignedToEachItEmployee(startDate, endDate);
        } catch(SQLException e) {
        }
        return null;
    }
    
    @Override
    public List<Pair<Employee, Integer>> retreieveTasksCompletedByEmployees() {
        try {
            return dbManager.calculateNumberOfTasksCompletedByEachItEmployee(startDate, endDate);
        } catch(SQLException e) {
        }
        return null;
    }
}
