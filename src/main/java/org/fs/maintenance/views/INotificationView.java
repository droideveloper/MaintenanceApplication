/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.views;

import org.fs.maintenance.controllers.INotificationController;
import org.fs.maintenance.entities.Notification;

/**
 *
 * @author Fatih
 */
public interface INotificationView extends IView {    
    
    /**
     * 
     */
    void init();
    
    /**
     * 
     * @param notification 
     */
    void display(Notification notification);
    
    /**
     * 
     */
    void close();
    
    /**
     * 
     * @param controller 
     */
    void setController(INotificationController controller);
    
    /**
     * 
     * @return 
     */
    String getWindowName();
}
