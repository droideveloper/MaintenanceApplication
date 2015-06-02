/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.fs.maintenance.common.DbConstants;

/**
 *
 * @author Fatih
 */
@DatabaseTable(tableName = DbConstants.User.TABLE_NAME)
public class User {
    
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = DbConstants.User.COLUMN_USER_NAME)
    private String userName;
    
    @DatabaseField(columnName = DbConstants.User.COLUMN_PASSWORD)
    private String password;
    
    @DatabaseField(foreign = true, 
            foreignAutoRefresh = true, 
            columnName = DbConstants.User.COLUMN_EMPLOYEE_ID, 
            canBeNull = true)
    private Employee employee;
    
    @DatabaseField(foreign = true, 
            foreignAutoRefresh = true, 
            columnName = DbConstants.User.COLUMN_MANAGER_ID, 
            canBeNull = true)
    private Manager manager;

    public User() {
    }

    private User(String userName, String password, Employee employee) {
        this.userName = userName;
        this.password = password;
        this.employee = employee;
    }
    
    private User(String userName, String password, Manager manager) {
        this.userName = userName;
        this.password = password;
        this.manager = manager;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }    
    
    public boolean validate(String userName, String password) {
        return this.userName.equalsIgnoreCase(userName) 
             && this.password.equalsIgnoreCase(password);
    }
    
    /**
     * this is the only way we can create user object.
     */
    public static class Builder {
        String   userName;
        String   password;
        Employee employee;
        Manager  manager;
        
        public Builder() { }
        
        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }
        
        public Builder password(String password) {
            this.password = password;
            return this;
        }
        
        public Builder employee(Employee employee) {
            this.employee = employee;
            return this;
        }
        
        public Builder manager(Manager manager) {
            this.manager = manager;
            return this;
        }
        
        public User build() {
            if(manager != null) {
                return new User(userName, 
                                password, 
                                manager);
            } else if(employee != null) {
                return new User(userName, 
                                password, 
                                employee);
            } else {
                throw new NullPointerException("you should either set manager or employee");
            }
        }
    }    
}
