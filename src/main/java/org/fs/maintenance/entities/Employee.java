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
@DatabaseTable(tableName = DbConstants.Employee.TABLE_NAME)
public class Employee {
    
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField(columnName = DbConstants.Employee.COLUMN_EMPLOYEE_NAME)
    private String name;
    
    @DatabaseField(columnName = DbConstants.Employee.COLUMN_EMPLOYEE_SURNAME)
    private String surname;
    
    @DatabaseField(columnName = DbConstants.Employee.COLUMN_EMPLOYEE_TITLE)
    private String title;    
    
    @DatabaseField(foreign = true, 
            foreignAutoRefresh = true, 
            columnName = DbConstants.Employee.COLUMN_DEPARTMENT_ID)
    private Department department; 
    
    public Employee() { }
    
    private Employee(String name, String surname, String title, Department department) {
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
     * Builder Pattern
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
        
        public Builder department(Department department) {
            this.department = department;
            return this;
        }
        
        public Employee build() {
            return new Employee(name, 
                                surname, 
                                title, 
                                department);
        }
    }
}
