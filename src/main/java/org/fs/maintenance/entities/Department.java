/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Objects;
import org.fs.maintenance.common.DbConstants;

/**
 *
 * @author Fatih
 */
@DatabaseTable(tableName = DbConstants.Department.TABLE_NAME)
public class Department {
    
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField(columnName = DbConstants.Department.COLUMN_DEPARTMENT_NAME, 
            canBeNull = false)
    private String departmentName;
    
    @DatabaseField(columnName = DbConstants.Department.COLUMN_DEPARTMENT_CODE, 
            canBeNull = false)
    private String departmentCode;
    
    public Department() { 
    }

    private Department(String departmentName, String departmentCode) {
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Department other = (Department) obj;
        if (!Objects.equals(this.departmentCode, other.departmentCode)) {
            return false;
        }
        return true;
    }
    
    
    
    /**
     * The way to create new Department entity. 
     */
    public static class Builder {
        String departmentName;
        String departmentCode;
        
        public Builder() { }
        
        public Builder departmentName(String departmentName) {
            this.departmentName = departmentName;
            return this;
        }
        
        public Builder departmentCode(String departmentCode) {
            this.departmentCode = departmentCode;
            return this;
        }
        
        public Department build() {
            return new Department(departmentName, 
                                  departmentCode);
        }
    }
}
