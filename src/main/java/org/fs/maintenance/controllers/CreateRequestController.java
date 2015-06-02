/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.controllers;

import java.sql.SQLException;
import java.util.Date;
import java.util.Locale;
import org.fs.maintenance.base.AbstractController;
import org.fs.maintenance.common.*;
import org.fs.maintenance.entities.Department;
import org.fs.maintenance.entities.Manager;
import org.fs.maintenance.entities.Notification;
import org.fs.maintenance.entities.NotificationType;
import org.fs.maintenance.entities.Request;
import org.fs.maintenance.entities.RequestStatus;
import org.fs.maintenance.entities.User;
import org.fs.maintenance.gui.ListRequestHistoryUI;
import org.fs.maintenance.utils.StringUtility;
import org.fs.maintenance.views.ICreateRequestView;

/**
 *
 * @author Fatih
 */
public class CreateRequestController extends AbstractController<ICreateRequestView> implements ICreateRequestController {

    private final MainController controller;
    private IDatabaseManager     dbManager;
    private final User           usr;
    
    public static CreateRequestController create(ICreateRequestView view, MainController controller, User usr) {
        return new CreateRequestController(view, controller, usr);        
    }
    
    private CreateRequestController(ICreateRequestView view, MainController controller, User usr) {
        super(view);
        this.controller = controller;
        this.usr = usr;
    }

    @Override
    protected void onCreate() {
        super.onCreate(); 
        view.setController(this);
        dbManager = DatabaseManager.getSharedInstance();
    } 

    @Override
    public boolean validate() {
        String title = view.getTitle();
        if(StringUtility.isNullOrEmpty(title)) {
            view.showError("title of the request should not be empty");
            return false;
        }
        String description = view.getDescription();
        if(StringUtility.isNullOrEmpty(description)) {
            view.showError("descrition of the request should not be empty");
            return false;
        }
        return true;
    }

    @Override
    public void back() {
        disposeView();
        controller.toggleView();
    }

    @Override
    public void apply() {
        if(validate()) {           
            if(usr != null) {
                Request.Builder builder = new Request.Builder()
                        .user(usr)
                        .title(view.getTitle())
                        .description(view.getDescription())
                        .status(RequestStatus.WAITING)
                        .createDate(new Date())
                        .updateDate(new Date());
                try {
                    Request r = dbManager.createNewRequest(builder.build());
                    
                    //get departmetn manager                
                    Department dep = dbManager.findDepartmentByUser(usr);
                    Manager m = dbManager.findManagerByDepartmentCode(dep.getDepartmentCode());
                    User manager = dbManager.findUserByManager(m);
                
                    Notification.Builder notifyBuilder = new Notification.Builder()
                                .title(String.format("<html><center>%s created new Request that it's title is \"%s\".</center></html>", 
                                usr.getEmployee().getName(), r.getTitle()))
                                .type(NotificationType.INFO)
                                .user(manager);
                    
                    dbManager.createNewNotificaiton(notifyBuilder.build());   
                   
                    view.showError(String.format(Locale.ENGLISH, "your request has successfully created,\nyour request id is %d", r.getId()));
                    view.clear();
                } catch(SQLException e) {
                    view.showError("could not save request object.");
                }
            } else {
                view.showError("usr is null, you re not allowed to be here");
            }
        }
    }

    @Override
    public void displayRequestsHistory() {
        toggleView();
        ListRequestHistoryController.create(ListRequestHistoryUI.createView(), this, usr);
    }

    @Override
    public User getUser() {
        return usr;
    }
    
    @Override
    protected String getWindowName() {
        return view.getWindowName();
    }    
}
