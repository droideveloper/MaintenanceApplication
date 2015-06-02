/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import org.fs.maintenance.controllers.NotificationController;
import org.fs.maintenance.entities.Notification;
import org.fs.maintenance.entities.User;
import org.fs.maintenance.gui.NotificationUI;

/**
 *
 * @author Fatih
 */
public class NotificationManager implements INotificationManager {

    private static NotificationManager sharedInstance;
    
    private final IDatabaseManager dbManager;
    private final Timer timer;
    private User usr;
    
    private Notification currentlyVisibleNotification;
    
    private boolean isRunning;
    
    private List<Notification> queue = new ArrayList();
    
    public static INotificationManager getSharedInstance() {
        if(sharedInstance == null) {
            sharedInstance = new NotificationManager();
        }
        return sharedInstance;
    }
    
    private NotificationManager() {
        dbManager = DatabaseManager.getSharedInstance();
        timer = new Timer(5 * 1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(usr == null) {
                    throw new NullPointerException("user is null");
                }
                queue = retrieveNotification();
                if(queue != null && !queue.isEmpty()) {
                    Notification notify = queue.get(0);
                    NotificationManager.this.notify(notify);
                }
            }        
        });
    }    
    
    @Override
    public void start() {
        if(isRunning) return;
        if(usr == null) {
            throw new NullPointerException("you should set user");
        }
        queue = retrieveNotification();
        timer.start();
        isRunning = true;
    }

    @Override
    public void stop() {
        if(!isRunning) return;
        timer.stop();
        isRunning = false;
    }

    @Override
    public void setUser(User usr) {
        this.usr = usr;        
    }

    @Override
    public void notify(Notification notify) {
        if(notify == null) {
            throw new NullPointerException("notification is null");
        }
        //if inorder not to show it over and over again
        if(currentlyVisibleNotification != null) {
            if(notify.getId() == currentlyVisibleNotification.getId()) return;
        }
        NotificationController.create(NotificationUI.createView(), notify)
                              .display();
        currentlyVisibleNotification = notify;
    }   
    
    private List<Notification> retrieveNotification() {
        try {
            return dbManager.findNotificationsByUser(usr);
        } catch(SQLException e) {
        }
        return null;
    }
}
