/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultListModel;
import org.fs.maintenance.base.AbstractController;
import org.fs.maintenance.common.*;
import org.fs.maintenance.entities.Department;
import org.fs.maintenance.entities.Employee;
import org.fs.maintenance.entities.Task;
import org.fs.maintenance.entities.User;
import org.fs.maintenance.gui.CompleteTaskUI;
import org.fs.maintenance.utils.StringUtility;
import org.fs.maintenance.views.IListTaskView;

/**
 *
 * @author Fatih
 */
public class ListTaskController extends AbstractController<IListTaskView> implements IListTaskController {

    private final MainController controller;
    
    private List<Task> tasks;
    private IDatabaseManager dbManager;
    
    private Task selectedTask;
    private final User usr;
    
    private ListTaskController(IListTaskView view, MainController controller, User usr) {
        super(view);
        this.controller = controller;
        this.usr = usr;
    }
    
    public static ListTaskController create(IListTaskView view, MainController controller, User usr) {
        return new ListTaskController(view, controller, usr);
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
        //get these task on the assignment not necessary to be completed.
        tasks = retreieveTasks(usr.getEmployee());
        displayTasks(tasks);
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
           toggleView();
           CompleteTaskController.create(CompleteTaskUI.createView(), this, selectedTask);
        } else {
            view.showError("you should select a task from list.");
        }
    }

    @Override
    public void selectAt(int index) {
        if(index < tasks.size() && index >= 0) {
            selectedTask = tasks.get(index);
            displayTaskDetial(selectedTask);
        } else {
            throw new ArrayIndexOutOfBoundsException(
                    String.format(Locale.getDefault(),
                            "%d is not smaller than %d and not greater or equal then 0",
                            index, tasks.size()));
        }
    }

    @Override
    public void displayTasks(List<Task> tasks) {
        if(tasks != null) {
            DefaultListModel<Task> taskModel = new DefaultListModel<>();
            for(Task t : tasks) {
                taskModel.addElement(t);
            }
            view.displayTasks(taskModel);
        }
    }

    @Override
    public void displayTaskDetial(Task task) {
        if(task != null) {
            view.displayDetail(task);
        }
    }

    @Override
    public List<Task> retreieveTasks(Employee emp) {
        try {
            return dbManager.findTasksNotCompletedAssignedToItEmployee(emp);
        } catch(SQLException e) { 
        }
        return null;
    }
    
    @Override
    public Department retrieveDepartmentByUser(User usr) {
        try {
            return dbManager.findDepartmentByUser(usr);
        } catch(SQLException e) {            
        }
        return null;
    }

    @Override
    public boolean validate() {
        return !StringUtility.isNullOrEmpty(selectedTask);
    } 
}
