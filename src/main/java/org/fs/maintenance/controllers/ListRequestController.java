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
import org.fs.maintenance.entities.Department;
import org.fs.maintenance.entities.Request;
import org.fs.maintenance.entities.User;
import org.fs.maintenance.gui.ApproveRequestUI;
import org.fs.maintenance.utils.StringUtility;
import org.fs.maintenance.views.IListRequestView;

/**
 *
 * @author Fatih
 */
public class ListRequestController extends AbstractController<IListRequestView> implements IListRequestController {

    private final MainController controller;
    
    private List<Request> requests;
    private Request selectedRequest = null;
    
    private final User usr;
    
    private IDatabaseManager dbManager;
    
    private ListRequestController(IListRequestView view, MainController controller, User usr) {
        super(view);
        this.controller = controller;
        this.usr = usr;
    }
    
    public static ListRequestController create(IListRequestView view, MainController controller, User usr) {
        return new ListRequestController(view, controller, usr);
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
        requests = retrieveRequests();
        displayRequests(requests);        
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
           ApproveRequestController.create(ApproveRequestUI.createView(), this, selectedRequest, usr);
       } else {
           view.showError("you should select a request from the list");
       }
    }

    @Override
    public void selectAt(int index) {
        selectedRequest = requests.get(index);
    }

    @Override
    public void displayRequests(List<Request> requests) {
        if(requests != null) {
            DefaultListModel<Request> reqModel = new DefaultListModel<>();
            for(Request r : requests) {
                reqModel.addElement(r);
            }
            view.displayRequests(reqModel);
        }
    }
    
    @Override
    public User getUser() {
        return usr;
    }

    @Override
    public boolean validate() {
       return !StringUtility.isNullOrEmpty(selectedRequest);
    }

    @Override
    public List<Request> retrieveRequests() {
        try {
            Department dept = dbManager.findDepartmentByUser(usr);
            if("IT".equalsIgnoreCase(dept.getDepartmentCode())) {
                return dbManager.findRequestsWaitingItManagerApproval();
            }
            else {
                return dbManager.findRequestsCreatedByDepartmentWaitingManagerApproval(dept);
            }
        } catch(SQLException e) {                    
        }       
        return null;
    }    
}
