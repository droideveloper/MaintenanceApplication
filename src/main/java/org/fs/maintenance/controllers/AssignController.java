/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.controllers;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.fs.maintenance.base.AbstractController;
import org.fs.maintenance.common.DatabaseManager;
import org.fs.maintenance.common.IDatabaseManager;
import org.fs.maintenance.common.SimpleTaskModel;
import org.fs.maintenance.entities.Department;
import org.fs.maintenance.entities.Employee;
import org.fs.maintenance.entities.Notification;
import org.fs.maintenance.entities.NotificationType;
import org.fs.maintenance.entities.Task;
import org.fs.maintenance.entities.User;
import org.fs.maintenance.utils.StringUtility;
import org.fs.maintenance.views.IAssignView;

/**
 *
 * @author Fatih
 */
public class AssignController extends AbstractController<IAssignView> implements IAssignController {

    private final ListEmployeeController controller;
    
    private final Task       currentTask;
    private Task             selectedTask;    
    private List<Task>       tasks;    
    private IDatabaseManager dbManager;    
    private final Employee   employee;
    
    private AssignController(IAssignView view, ListEmployeeController controller, Employee employee, Task currentTask) {
        super(view);
        this.controller = controller;
        this.employee = employee;
        this.currentTask = currentTask;
    }
    
    public static AssignController create(IAssignView view, ListEmployeeController controller, Employee employee, Task currentTask) {
        return new AssignController(view, controller, employee, currentTask);
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
        displayCurrentTaskDetail(currentTask);    
        displayTaskDetail(selectedTask);
        displayEmployeeDetail(employee);  
        //each time we get here we retrieve task all over again since they might be approved
        tasks = retrieveTasks();
        //displat them in the list
        displayTasks(tasks);
    }       

    @Override
    protected String getWindowName() {
        return view.getWindowName();
    }

    @Override
    public void selectAt(int index) {
        selectedTask = tasks.get(index);
        displayTaskDetail(selectedTask);
    }

    @Override
    public void displayEmployeeDetail(Employee e) {
        if(e != null) {
            view.displayEmployeeDetail(e);
        }
    }
    
    @Override
    public void displayCurrentTaskDetail(Task t) {
        if(t != null) {
            view.displayCurrentTask(t);
        }
    }

    @Override
    public void displayTaskDetail(Task t) {
        if(t != null) {
            view.displayTaskDetail(t);
        }
    }

    @Override
    public void displayTasks(List<Task> tasks) {
        if(tasks != null) {           
            view.displayTasks(new SimpleTaskModel(tasks));
        }
    }

    @Override
    public List<Task> retrieveTasks() {
        try {
            return dbManager.findTasksNotAssignedToAnyItEmployees();
        } catch(SQLException e) {            
        }
        return null;
    }
    
    @Override
    public int retrieveNumberOfTaskAssigned() {
        try {
            return dbManager.calculateNumberOfTasksAssignedToItEmployee(employee);
        } catch(SQLException e) {            
        }
        return 0;
    }

    @Override
    public void back() {
        disposeView();
        controller.toggleView();
    }

    @Override
    public void assign() {
        if(validate()) {
            try {
                selectedTask.setEmployee(employee);
                selectedTask.setUpdateDate(new Date());//used as assign date
                dbManager.updateExisitingTask(selectedTask);

                
                Notification.Builder notifyBuilder = new Notification.Builder()
                        .title(String.format("<html><center>Your manager assigned you new Task that it's title is \"%s\".</center></html>", selectedTask.getTitle()))
                        .type(NotificationType.INFO)
                        .user(dbManager.findUserByEmployee(employee));
                
                dbManager.createNewNotificaiton(notifyBuilder.build());                
                
                view.showError("you have successfully assigned the task");
                //go over again
                onStart();
                
            } catch(SQLException e) {
                view.showError("unknown error occured");
            }
        } else {
            view.showError("you should select task to assign");
        }
    }

    @Override
    public boolean validate() {
        return !StringUtility.isNullOrEmpty(selectedTask)
                && !StringUtility.isNullOrEmpty(employee);
    }    
    
    @Override
    public Department retrieveDepartmentByUser(User usr) {
        try {
            return dbManager.findDepartmentByUser(usr);
        } catch(SQLException e) {
        }
        return null;
    }
}
