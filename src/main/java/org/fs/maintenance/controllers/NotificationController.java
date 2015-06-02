/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.controllers;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.sql.SQLException;
import javax.swing.JFrame;
import org.fs.maintenance.base.AbstractController;
import org.fs.maintenance.common.*;
import org.fs.maintenance.entities.Notification;
import org.fs.maintenance.views.INotificationView;

/**
 *
 * @author Fatih
 */
public class NotificationController extends AbstractController<INotificationView> implements INotificationController {

    private final Notification notification;
    private IDatabaseManager   dbManager;
    
    private NotificationController(INotificationView view, Notification notification) {
        super(view);
        this.notification = notification;
    }
    
    public static INotificationController create(INotificationView view, Notification notification) {
        return new NotificationController(view, notification);
    }

    @Override
    protected void onCreate() {
        setUp();
        view.setController(this);
        dbManager = DatabaseManager.getSharedInstance();
    }
    
    private void setUp() {
        jFrame = new JFrame(getWindowName());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(view.createContentView());
        jFrame.addWindowListener(this);        
        //make frame for notification        
        jFrame.setUndecorated(true);
        //jFrame.setOpacity(0.85f);
        jFrame.setBackground(new Color(0f, 0f, 0f, 1f / 1.2f)); 
        jFrame.pack();
        //set frame on top right location

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - jFrame.getWidth();
        int y = 50;
        jFrame.setLocation(x, y);
    }
    
    @Override
    protected String getWindowName() {
        return view.getWindowName();
    }

    @Override
    public void display() {
        jFrame.setVisible(true);
        view.display(notification);
    }

    @Override
    public void close() {
        try {
            dbManager.deleteExistingNotificaiton(notification);
        } catch(SQLException e) {
        }
        jFrame.dispose();
    }    
}
