/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.controllers;

import java.util.List;
import org.fs.maintenance.entities.Request;
import org.fs.maintenance.entities.User;

/**
 *
 * @author Fatih
 */
public interface IListRequestHistoryController {
    
    /**
     * 
     */
    void back();
    
    /**
     * 
     * @param index 
     */
    void selectAt(int index);
    
    /**
     * 
     * @param requests 
     */
    void displayRequests(List<Request> requests);
    
    /**
     * 
     * @param usr
     * @return 
     */
    List<Request> retrieveRequests(User usr);
}
