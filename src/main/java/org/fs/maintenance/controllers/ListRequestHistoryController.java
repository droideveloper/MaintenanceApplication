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
import org.fs.maintenance.entities.Request;
import org.fs.maintenance.entities.User;
import org.fs.maintenance.views.IListRequestHistoryView;

/**
 *
 * @author Fatih
 */
public class ListRequestHistoryController extends AbstractController<IListRequestHistoryView> implements IListRequestHistoryController {

    private final CreateRequestController controller;
    private final User                    usr;
    
    private List<Request>    requests;    
    private IDatabaseManager dbManager;
    
    public static ListRequestHistoryController create(IListRequestHistoryView view, CreateRequestController controller, User usr) {
        return new ListRequestHistoryController(view, controller, usr);
    }
    
    private ListRequestHistoryController(IListRequestHistoryView view, CreateRequestController controller, User usr) {
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
    protected void onStart() {
        super.onStart();
        requests = retrieveRequests(usr);
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
    public void selectAt(int index) {
        Request request = requests.get(index);
        if(request != null) {
            view.displayRequestDetail(request);
        } 
    }

    @Override
    public void displayRequests(List<Request> requests) {
        if(requests == null) {
            throw new NullPointerException("usr requests null");
        }    
        if(requests.isEmpty()) {
            view.showError("you don't have any requests that are made.");
        } else {
            DefaultListModel<Request> reqModel = new DefaultListModel<>();
            for(Request r : requests) {
                reqModel.addElement(r);
            }
            view.displayRequests(reqModel);
        }
    }

    @Override
    public List<Request> retrieveRequests(User usr) {
        try {
            return dbManager.findRequestsCreatedByUser(usr);
        } catch(SQLException e) {
            return null;
        }
    }    
}
