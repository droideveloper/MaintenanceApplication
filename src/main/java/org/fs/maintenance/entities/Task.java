/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;
import org.fs.maintenance.common.DbConstants;

/**
 *
 * @author Fatih
 */
@DatabaseTable(tableName = DbConstants.Task.TABLE_NAME)
public class Task {
    
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField(columnName = DbConstants.Task.COLUMN_TASK_TITLE)
    private String title;
    
    @DatabaseField(columnName = DbConstants.Task.COLUMN_TASK_DESCRIPTION, 
            dataType = DataType.LONG_STRING)
    private String description;
    
    @DatabaseField(columnName = DbConstants.Task.COLUMN_TASK_STATUS, 
            dataType = DataType.ENUM_STRING)
    private TaskStatus status = TaskStatus.IDLE;    
    
    @DatabaseField(columnName = DbConstants.Task.COLUMN_TASK_CREATE_DATE, 
            dataType = DataType.DATE_STRING)
    private Date createDate;
    
    @DatabaseField(columnName = DbConstants.Task.COLUMN_TASK_UPDATE_DATE, 
            dataType = DataType.DATE_STRING)    
    private Date updateDate;    
    
    @DatabaseField(columnName = DbConstants.Task.COLUMN_TASK_START_DATE,
            dataType = DataType.DATE_STRING, 
            canBeNull = true)    
    private Date startDate;

    @DatabaseField(columnName = DbConstants.Task.COLUMN_TASK_COMPLETE_DATE, 
            dataType = DataType.DATE_STRING, 
            canBeNull = true)    
    private Date completeDate;
    
    @DatabaseField(columnName = DbConstants.Task.COLUMN_REQUEST_ID,
            foreign = true, 
            foreignAutoRefresh = true) 
    private Request request;   
    
    @DatabaseField(foreign = true,
            foreignAutoRefresh = true, 
            columnName = DbConstants.Task.COLUMN_EMPLOYEE_ID, 
            canBeNull = true)
    private Employee employee;
    
    public Task() { }
    
    private Task(String title, String description, TaskStatus status, Date createDate, Date updateDate, Request request) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.request = request;
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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    /**
     * Builder of Task Entity (Lovely Pattern to create new instance of object that is easy to read in code )
     */
    public static class Builder {
        String title;
        String description;
        TaskStatus status;
        Date createDate;
        Date updateDate;
        Request request;
        
        public Builder() { }
        
        public Builder title(String title) {
            this.title = title;
            return this;
        }
        
        public Builder description(String description) {
            this.description = description;
            return this;
        }
        
        public Builder status(TaskStatus status) {
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
        
        public Builder request(Request request) {
            this.request = request;
            return this;
        }
        
        public Task build() {
            return new Task(title, 
                            description, 
                            status, 
                            createDate, 
                            updateDate, 
                            request);
        }
    }
}
