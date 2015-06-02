/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.common;

import java.io.File;
import java.sql.SQLException;
import java.util.Locale;
import javax.swing.SwingUtilities;
import org.fs.maintenance.controllers.LoginController;
import org.fs.maintenance.gui.LoginUI;


/**
 *
 * @author Fatih
 */
public class MaintenanceApplication {    
    
    static {
        System.setProperty("user.images", "src/main/resources");
        System.setProperty("mock.sql", "sample_data.sql");
        System.setProperty("user.db", "maintenance.sqlite");
        System.setProperty("user.connection.str",
                String.format(Locale.getDefault(), 
                        "jdbc:sqlite:%s/%s", System.getProperty("user.dir"), 
                                             System.getProperty("user.db")));//jdbc:sqlite:/user/Fatih/NetbeansProjects/MaintenanceApplication/maintenance.sqlite, for instance.
    }
    
    public static void main(String[] args) {
        final File dbFile = new File(String.format(Locale.getDefault(), 
                                                   "%s/%s",
                                                   System.getProperty("user.dir"), System.getProperty("user.db")));
                
       
        if(!dbFile.exists()) {
            try { 
                IDatabaseManager dbManager = DatabaseManager.getSharedInstance(); 
                dbManager.initWithMockData();
            } catch(SQLException e) {
            }
        } 
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginController.create(LoginUI.createView());
            }
        });
    }
}
