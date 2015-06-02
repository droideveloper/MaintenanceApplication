/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.controllers;

import org.fs.maintenance.entities.User;

/**
 *
 * @author Fatih
 */
public interface ICreateRequestController {
    
    /**
     * validates form for title and description fields not required to be null or empty
     * @return 
     */
    boolean validate();
    
    /**
     * returns back to main view
     */
    void back();
    
    /**
     * apply the request you filled forms
     */
    void apply();
    
    /**
     * we show you previous request and their statuses around the application
     */
    void displayRequestsHistory();    
    
    /**
     * gets the active user to create request by its half.
     * @return 
     */
    User getUser();
}
