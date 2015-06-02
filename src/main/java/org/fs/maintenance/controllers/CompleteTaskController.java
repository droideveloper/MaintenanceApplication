/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.controllers;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.fs.maintenance.base.AbstractController;
import org.fs.maintenance.common.DatabaseManager;
import org.fs.maintenance.common.IDatabaseManager;
import org.fs.maintenance.common.SimpleTaskStatusModel;
import org.fs.maintenance.entities.Department;
import org.fs.maintenance.entities.Manager;
import org.fs.maintenance.entities.Notification;
import org.fs.maintenance.entities.NotificationType;
import org.fs.maintenance.entities.Task;
import org.fs.maintenance.entities.TaskStatus;
import org.fs.maintenance.entities.User;
import org.fs.maintenance.views.ICompleteTaskView;

/**
 *
 * @author Fatih
 */
public class CompleteTaskController extends AbstractController<ICompleteTaskView> implements ICompleteTaskController {

    private final ListTaskController controller;
    private final Task task;
    
    private IDatabaseManager dbManager;
    
    private TaskStatus status;
    private List<TaskStatus> statuses;
    
    private CompleteTaskController(ICompleteTaskView view, ListTaskController controller, Task task) {
        super(view);
        this.controller = controller;
        this.task = task;
    }
    
    public static CompleteTaskController create(ICompleteTaskView view, ListTaskController controller, Task task) {
        return new CompleteTaskController(view, controller, task);
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
        if(statuses == null) {
            statuses = retrieveTaskStatus();
        }
        displayTaskStatus(statuses);
        dispalyTask(task);
    }    

    @Override
    protected String getWindowName() {
        return view.getWindowName();
    }

    @Override
    public void save() {
        if(validate()) {
            try {
                task.setStatus(status);
                task.setUpdateDate(new Date());
                //if its comlete
                if(status.equals(TaskStatus.COMPLETE)) {
                    task.setCompleteDate(new Date());
                }
                //if its progress
                else if(status.equals(TaskStatus.PROGRESS)) {
                    task.setStartDate(new Date());
                }
                dbManager.updateExisitingTask(task);               
                //get it manager                
                Manager m = dbManager.findManagerByDepartmentCode("IT");
                User usr = dbManager.findUserByManager(m);
                
                Notification.Builder notifyBuilder = new Notification.Builder()
                        .title(String.format("<html><center>%s completed the Task that it's title is \"%s\".</center></html>", 
                                task.getEmployee().getName(), task.getTitle()))
                        .type(NotificationType.INFO)
                        .user(usr);
                
                dbManager.createNewNotificaiton(notifyBuilder.build());        
                
                view.showError("you have updated task's status, successfully.");
            } catch(SQLException e) {
                view.showError("encounterd an error, try again");
            }
        } else {
            view.showError("you should change the task's status");
        }
    }

    @Override
    public void back() {
        disposeView();
        controller.toggleView();
    }

    @Override
    public boolean validate() {
        return !task.getStatus().equals(status);
    }
    
    @Override
    public void selectAt(int index) {
        status = statuses.get(index);
    }

    @Override
    public void dispalyTask(Task t) {
        if(t != null) {
            view.displayDetail(t);
        }
    }

    @Override
    public void displayTaskStatus(List<TaskStatus> tasksStatuses) {
        if(tasksStatuses != null) {
            view.displayTaskStatus(new SimpleTaskStatusModel(tasksStatuses));
        }
    }

    @Override
    public List<TaskStatus> retrieveTaskStatus() {
        return Arrays.asList(TaskStatus.values());
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
