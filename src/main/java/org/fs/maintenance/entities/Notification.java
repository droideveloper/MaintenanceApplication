/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.fs.maintenance.common.DbConstants;

/**
 *
 * @author Fatih
 */
@DatabaseTable(tableName = DbConstants.Notification.TABLE_NAME)
public class Notification {
    
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField(columnName = DbConstants.Notification.COLUMN_NOTIFICATION_TITLE)
    private String title;
    
    @DatabaseField(columnName = DbConstants.Notification.COLUMN_NOTIFICATION_STATUS, 
            dataType = DataType.ENUM_STRING)
    private NotificationType type;
    
    @DatabaseField(columnName = DbConstants.Notification.COLUMN_NOTIFICATION_USER,
            foreign = true,
            foreignAutoRefresh = true)
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public User getUser() {
        return user;
    }    
    
    public Notification() {
    }
    
    private Notification(String title, NotificationType type, User user) {
        this.title = title;
        this.type = type;
        this.user = user;
    }   
    
    /**
     * Builder class
     */
    public static class Builder {
        private String title;
        private NotificationType type;
        private User user;
        
        public Builder() { }
        
        public Builder title(String title) {
            this.title = title;
            return this;
        }
        
        public Builder type(NotificationType type) {
            this.type = type;
            return this;
        }
        
        public Builder user(User user) {
            this.user = user;
            return this;
        }
        
        public Notification build() {
            return new Notification(title, type, user);
        }
    }
}
