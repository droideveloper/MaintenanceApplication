/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.views;

import org.fs.maintenance.controllers.ILoginController;

/**
 *
 * @author Fatih
 */
public interface ILoginView extends IView {
    
    /**
     * 
     */
    void init();
    
    /**
     * 
     * @param msg 
     */
    void showError(String msg);
    
    /**
     * 
     */
    void hideError();
    
    /**
     * 
     */
    void login();
    
    /**
     * 
     * @return 
     */
    String getUserName();
    
    /**
     * 
     * @return 
     */
    String getPassword();
    
    /**
     * 
     * @return 
     */
    String getWindowName();
    
    /**
     * 
     * @param iLoginController 
     */
    void setController(ILoginController iLoginController);
    
}
