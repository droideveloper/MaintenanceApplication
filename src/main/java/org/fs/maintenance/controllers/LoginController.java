/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.controllers;

import org.fs.maintenance.base.AbstractController;
import org.fs.maintenance.common.*;
import org.fs.maintenance.entities.User;
import org.fs.maintenance.gui.MainUI;
import org.fs.maintenance.utils.StringUtility;
import org.fs.maintenance.views.ILoginView;

/**
 *
 * @author Fatih
 */
public class LoginController extends AbstractController<ILoginView> implements ILoginController {

    private IDatabaseManager dbManager;
    
    private LoginController(ILoginView view) {
        super(view);
    }
    
    public static LoginController create(ILoginView view) {
        return new LoginController(view);
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
        //JOptionPane.showMessageDialog(jFrame, "this is the simple JDialog", "Sample JDialog", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    protected String getWindowName() {
        return view.getWindowName();
    }

    @Override
    public void login() {
        if(validate()) {
            try {
                User usr = dbManager.findUserByUserName(view.getUserName());
                boolean success = usr.validate(view.getUserName(), view.getPassword());
                if(success) {
                    toggleView();
                    MainController.create(MainUI.createView(), usr);
                } else {
                    view.showError("please check your password");
                }
            } catch(Exception e) {                             
                view.showError("user not found please check username");
            }
        }
    }

    @Override
    public boolean validate() {
        String userName = view.getUserName();
        if(StringUtility.isNullOrEmpty(userName)) {
            view.showError("user name should not be empty");
            return false;
        }
        String password = view.getPassword();
        if(StringUtility.isNullOrEmpty(password)) {
            view.showError("password should not be empty");
            return false;
        }        
        return true;
    }    
}
