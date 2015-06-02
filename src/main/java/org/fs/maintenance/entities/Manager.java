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
@DatabaseTable(tableName = DbConstants.Manager.TABLE_NAME)
public class Manager {
    
     @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField(columnName = DbConstants.Manager.COLUMN_MANAGER_NAME)
    private String name;
    
    @DatabaseField(columnName = DbConstants.Manager.COLUMN_MANAGER_SURNAME)
    private String surname;
    
    @DatabaseField(columnName = DbConstants.Manager.COLUMN_MANAGER_TITLE)
    private String title;    
    
    @DatabaseField(foreign = true, 
            foreignAutoRefresh = true, 
            columnName = DbConstants.Manager.COLUMN_DEPARTMENT_ID)
    private Department department; 
    
    public Manager() {
    }
    
    private Manager(String name, String surname, String title, Department department) {
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }    
    
    /**
     * New Manager Builder Pattern
     */
    public static class Builder {
        String name;
        String surname;
        String title;
        Department department;
        
        public Builder() { }
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }
        
        public Builder title(String title) {
            this.title = title;
            return this;
        }
        
        public Builder department(Department department)  {
            this.department = department;
            return this;
        }
        
        public Manager build() {
            return new Manager(name, 
                               surname, 
                               title, 
                               department);
        }        
    }
}
