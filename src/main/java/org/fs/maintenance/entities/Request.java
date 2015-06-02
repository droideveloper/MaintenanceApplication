/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DataType;
import java.util.Date;
import org.fs.maintenance.common.DbConstants;

/**
 *
 * @author Fatih
 */
@DatabaseTable(tableName = DbConstants.Request.TABLE_NAME)
public class Request {
    
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField(columnName = DbConstants.Request.COLUMN_REQUEST_TITLE)
    private String title;
    
    @DatabaseField(columnName = DbConstants.Request.COLUMN_REQUEST_DESCRIPTION, 
            dataType = DataType.LONG_STRING)
    private String description;
    
    @DatabaseField(columnName = DbConstants.Request.COLUMN_REQUEST_STATUS, 
            dataType = DataType.ENUM_STRING)
    private RequestStatus status = RequestStatus.WAITING;
    
    @DatabaseField(columnName = DbConstants.Request.COLUMN_REQUEST_CREATE_DATE, 
            dataType = DataType.DATE_STRING)
    private Date createDate;
    
    @DatabaseField(columnName = DbConstants.Request.COLUMN_REQUEST_UPDATE_DATE, 
            dataType = DataType.DATE_STRING)
    private Date updateDate;
    
    @DatabaseField(foreign = true, 
            foreignAutoRefresh = true, 
            columnName = DbConstants.Request.COLUMN_USER_ID)
    private User user;
    
    public Request() { }
    
    private Request(String title, String description, Date createDate, Date updateDate, RequestStatus status, User user) {
        this.title = title;
        this.description = description;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.status = status;
        this.user = user;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * New Object Creator Pattern
     */
    public static class Builder {
        String title;
        String description;
        RequestStatus status;
        Date   createDate;
        Date   updateDate;
        User   user;
        
        public Builder() { }
        
        public Builder title(String title) {
            this.title = title;
            return this;
        }
        
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        
        public Builder status(RequestStatus status) {
            this.status = status;
            return this;
        }
        
        public Builder createDate(Date createDate) {
            this.createDate = createDate;
            return this;
        }
        
        public Builder updateDate(Date updateDate) {
            this.updateDate = updateDate;
            return this;
        }
        
        public Builder user(User user) {
            this.user = user;
            return this;
        }
        
        public Request build() {
            return new Request(title, 
                               description, 
                               createDate, 
                               updateDate, 
                               status, 
                               user);
        }
    }
 }
