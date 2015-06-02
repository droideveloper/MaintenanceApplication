/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.common;

import org.fs.maintenance.entities.Notification;
import org.fs.maintenance.entities.User;

/**
 *
 * @author Fatih
 */
public interface INotificationManager {
    
    /**
     * 
     */
    void start();
    
    /**
     * 
     */
    void stop();
    
    /**
     * 
     * @param usr 
     */
    void setUser(User usr);
    
    /**
     * 
     * @param notify 
     */
    void notify(Notification notify);
}
