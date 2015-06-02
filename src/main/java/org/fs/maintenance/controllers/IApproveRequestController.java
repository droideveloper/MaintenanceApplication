/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.controllers;

import org.fs.maintenance.entities.Department;
import org.fs.maintenance.entities.Request;
import org.fs.maintenance.entities.User;

/**
 *
 * @author Fatih
 */
public interface IApproveRequestController {
    
    /**
     * performs back operation on the JButton btnBack instance 
     */
    void back();
    
    /**
     * performs reject operation on the selected Request
     */
    void reject();
    
    /**
     * performs approve operation on the selected Request
     */
    void approve(); 
    
    /**
     * displays relative fields on the view with passed request object instance
     * @param r Request object instance
     */
    void displayRequestDetail(Request r);
    
    /**
     * validates before operations such as reject() and approve() 
     * if request object passed is null then will returns false
     * @return 
     */
    boolean validate();
    
    /**
     * Returns department object from DatabaseManager if found else null
     * @param id to look for in the database
     * @return null or department object instance
     */
    Department retrieveDepartmentByID(int id);
    
    /**
     * 
     * @param usr
     * @return 
     */
    Department retrieveDepartmentByUser(User usr);
    
}
