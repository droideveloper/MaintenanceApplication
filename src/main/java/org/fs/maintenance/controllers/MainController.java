/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.controllers;

import java.sql.SQLException;
import org.fs.maintenance.base.AbstractController;
import org.fs.maintenance.common.*;
import org.fs.maintenance.common.INotificationManager;
import org.fs.maintenance.entities.Department;
import org.fs.maintenance.entities.Employee;
import org.fs.maintenance.entities.Manager;
import org.fs.maintenance.entities.User;
import org.fs.maintenance.gui.CreateRequestUI;
import org.fs.maintenance.gui.FindReportUI;
import org.fs.maintenance.gui.ListEmployeeUI;
import org.fs.maintenance.gui.ListRequestUI;
import org.fs.maintenance.gui.ListTaskUI;
import org.fs.maintenance.views.IMainView;

/**
 *
 * @author Fatih
 */
public class MainController extends AbstractController<IMainView> implements IMainController {
   
    private final User          usr;
    private IDatabaseManager    dbManager;
    private INotificationManager notifyManager;
    
    public static MainController create(IMainView view, User usr) {
        return new MainController(view, usr);
    }
    
    private MainController(IMainView view, User usr) {
        super(view);
        this.usr = usr;
        if(usr == null) {
            throw new NullPointerException("user should not be null");
        }
    }

    @Override
    protected void onCreate() {
        super.onCreate(); 
        view.setController(this);
        dbManager = DatabaseManager.getSharedInstance();
        notifyManager = NotificationManager.getSharedInstance();
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        try {
            notifyManager.setUser(usr);
            notifyManager.start();
            
            Department dept = dbManager.findDepartmentByDepartmentCode("IT");
            Employee e = usr.getEmployee();
            if(e != null) {
                if(e.getDepartment().equals(dept)) {
                    view.switchEmployeeContent();
                } else {
                    view.switchDepartmentEmployeeContent();
                }
            }
            Manager m = usr.getManager();
            if(m != null) {
                if(m.getDepartment().equals(dept)) {
                    view.switchManagerContent();
                } else {
                    view.switchDepartmentManagerContent();
                }
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }        
   
    @Override
    protected String getWindowName() {
        return view.getWindowName();
    }

    @Override
    public void selectCreateRequest() {
        toggleView();
        CreateRequestController.create(CreateRequestUI.createView(), this, usr);
    }

    @Override
    public void selectApproveRequest() {
        toggleView();
        ListRequestController.create(ListRequestUI.createView(), this, usr);
    }

    @Override
    public void selectAssignTask() {
        toggleView();
        ListEmployeeController.create(ListEmployeeUI.createView(), this);
    }

    @Override
    public void selectCompleteTask() {
        toggleView();
        ListTaskController.create(ListTaskUI.createView(), this, usr);
    }

    @Override
    public void selectReports() {
        toggleView();
        FindReportController.create(FindReportUI.createView(), this);
    }

    @Override
    public void signout() {        
        disposeView();
        try {
            notifyManager.stop();//stop notifications
            dbManager.dispose();//stop dbManager connection
        } catch(SQLException e) { /*keep it silent*/ }
        System.exit(0);
    }
}
