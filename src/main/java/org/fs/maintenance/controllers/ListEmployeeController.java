/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.controllers;

import java.sql.SQLException;
import java.util.List;
import javax.swing.DefaultListModel;
import org.fs.maintenance.base.AbstractController;
import org.fs.maintenance.common.*;
import org.fs.maintenance.entities.Employee;
import org.fs.maintenance.entities.Task;
import org.fs.maintenance.gui.AssignUI;
import org.fs.maintenance.utils.StringUtility;
import org.fs.maintenance.views.IListEmployeeView;

/**
 *
 * @author Fatih
 */
public class ListEmployeeController extends AbstractController<IListEmployeeView> implements IListEmployeeController {

    private final MainController controller;
    private List<Employee> employees;
    private Employee selectedEmployee;
    
    private IDatabaseManager dbManager;
    
    public static ListEmployeeController create(IListEmployeeView view, MainController controller) {
        return new ListEmployeeController(view, controller);
    }
    
    private ListEmployeeController(IListEmployeeView view, MainController controller) {
        super(view);
        this.controller = controller;
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
        if(employees == null) {
            employees = getEmployees();           
        }
        displayEmployees(employees);
    }

    @Override
    public List<Employee> getEmployees() {
        try {
            return dbManager.findEmployeesWorkAtItDepartment();
        } catch(SQLException e) {
        }
        return null;
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
    public void select() {
        if(validate()) {
            try {
                Task task = dbManager.findTaskItEmployeeCurrentlyWorkingOn(selectedEmployee);
                toggleView();
                AssignController.create(AssignUI.createView(), this, selectedEmployee, task);
            } catch(SQLException e) { }
        } else {
            view.showError("you should select an employee from list");
        }
    }
    
    @Override
    public boolean validate() {            
        return !StringUtility.isNullOrEmpty(selectedEmployee);
    }

    @Override
    public void selectAt(int index) {
        selectedEmployee = employees.get(index);
        displayEmployeeDetail(selectedEmployee);
    }

    @Override
    public void displayEmployees(List<Employee> employees) {
        if(employees != null) {
            DefaultListModel<Employee> empModel = new DefaultListModel<>();
            for(Employee e : employees) {
                empModel.addElement(e);
            }
            view.displayEmployees(empModel);
        }
    }

    @Override
    public void displayEmployeeDetail(Employee employee) {
        try {
            Task task = dbManager.findTaskItEmployeeCurrentlyWorkingOn(employee);
            if(task != null) {
                view.displayEmployeeDetail(task);
            }
        } catch(SQLException e) {
        }
    }    
}
