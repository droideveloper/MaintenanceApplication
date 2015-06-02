/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.controllers;

import java.sql.SQLException;
import java.util.Date;
import org.fs.maintenance.base.AbstractController;
import org.fs.maintenance.common.DatabaseManager;
import org.fs.maintenance.common.IDatabaseManager;
import org.fs.maintenance.entities.Department;
import org.fs.maintenance.entities.Notification;
import org.fs.maintenance.entities.NotificationType;
import org.fs.maintenance.entities.Request;
import org.fs.maintenance.entities.RequestStatus;
import org.fs.maintenance.entities.Task;
import org.fs.maintenance.entities.TaskStatus;
import org.fs.maintenance.entities.User;
import org.fs.maintenance.views.IApproveRequestView;

/**
 *
 * @author Fatih
 */
public class ApproveRequestController extends AbstractController<IApproveRequestView> implements IApproveRequestController {

    private final ListRequestController controller;
    private final Request               request;
    private final User                  usr;
    
    private IDatabaseManager dbManager;
    
    private ApproveRequestController(IApproveRequestView view, ListRequestController controller, Request request, User usr) {
        super(view);
        this.controller = controller;
        this.request = request;
        this.usr = usr;
    }
    
    public static ApproveRequestController create(IApproveRequestView view, ListRequestController controller, Request request, User usr) {
        return new ApproveRequestController(view, controller, request, usr);
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
        view.displayRequestDetail(request);
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
    public void reject() {   
        try {
            if(validate()) {
                request.setStatus(RequestStatus.IT_REJECTED);
                request.setUpdateDate(new Date());
                dbManager.updateExistingRequest(request);
                
                Notification.Builder notifyBuilder = new Notification.Builder()
                        .title(String.format("<html><center>IT manager rejected your Request that it's title is \"%s\".</center></html>", request.getTitle()))
                        .type(NotificationType.ERROR)
                        .user(request.getUser());
                
                dbManager.createNewNotificaiton(notifyBuilder.build());                
                
            } else {
                request.setStatus(RequestStatus.DEPARTMENT_REJECTED);
                request.setUpdateDate(new Date());
                dbManager.updateExistingRequest(request);
                
                Notification.Builder notifyBuilder = new Notification.Builder()
                        .title(String.format("<html><center>Your manager rejected your Request that it's title is \"%s\".</center></html>", request.getTitle()))
                        .type(NotificationType.WARNING)
                        .user(request.getUser());
                
                dbManager.createNewNotificaiton(notifyBuilder.build());                
            }
            view.showError("you have rejected the request, successfully");
        } catch(SQLException e) {
            view.showError("an unexpected error occured");
        }
    }

    @Override
    public void approve() {  
        try {
            if(validate()) {                
                request.setStatus(RequestStatus.IT_APPROVED);
                request.setUpdateDate(new Date());
                dbManager.updateExistingRequest(request);
                
                Task.Builder taskBuilder = new Task.Builder()
                        .createDate(new Date())
                        .updateDate(new Date())
                        .description(request.getDescription())
                        .title(request.getTitle())
                        .request(request)
                        .status(TaskStatus.IDLE);
                dbManager.createNewTask(taskBuilder.build());
                
                 Notification.Builder notifyBuilder = new Notification.Builder()
                        .title(String.format("<html><center>IT Manager approved your Request that it's title is \"%s\".</center></html>", request.getTitle()))
                        .type(NotificationType.INFO)
                        .user(request.getUser());
                
                dbManager.createNewNotificaiton(notifyBuilder.build());
                
                view.showError("you have aprroved request and created new task, successfully");
            } else {
                request.setStatus(RequestStatus.DEPARTMENT_APPROVED);
                request.setUpdateDate(new Date());
                dbManager.updateExistingRequest(request);
                
                Notification.Builder notifyBuilder = new Notification.Builder()
                        .title(String.format("<html><center>Your manager approved your Request that it's title is \"%s\".</center></html>", request.getTitle()))
                        .type(NotificationType.INFO)
                        .user(request.getUser());
                
                dbManager.createNewNotificaiton(notifyBuilder.build());
                
                view.showError("you have approved the request, successfully");
            }            
        } catch(SQLException e) {
            view.showError("an unexpected error occured");
        }               
    }

    @Override
    public void displayRequestDetail(Request r) {
        view.displayRequestDetail(r);
    }

    @Override
    public boolean validate() {        
        if(usr != null) {
            Department dept = retrieveDepartmentByUser(usr);
            if(dept != null) {
                return "IT".equalsIgnoreCase(dept.getDepartmentCode());
            }            
        }
        return false;
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
    public Department retrieveDepartmentByID(int id) {
        try {
            return dbManager.findDepartmentByDepartmentId(id);
        } catch(SQLException e) {            
        }
        return null;
    }
}
