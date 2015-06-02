/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.common;


/**
 *
 * @author Fatih
 */
public class DbConstants {
    

    
    /**
     * User Table constants for entity and H2Database
     */
    public static class User {
        public final static String TABLE_NAME           = "users";
        
        public final static String COLUMN_ID            = "id";
        public final static String COLUMN_USER_NAME     = "user_name";
        public final static String COLUMN_PASSWORD      = "password";
        public final static String COLUMN_EMPLOYEE_ID   = "employee_id";
        public final static String COLUMN_MANAGER_ID    = "manager_id";
    }   
    
    /**
     * Employee Table constants for entity and H2Database
     */
    public static class Employee {
        public final static String TABLE_NAME               = "employees";
        
        public final static String COLUMN_ID                = "id";        
        public final static String COLUMN_EMPLOYEE_NAME     = "employee_name";
        public final static String COLUMN_EMPLOYEE_SURNAME  = "employee_surname";
        public final static String COLUMN_EMPLOYEE_TITLE    = "employee_title";
        public final static String COLUMN_DEPARTMENT_ID     = "department_id";   
    } 
    
    /**
     * Manager Table constants for entity and H2Database
     */
    public static class Manager {
        public final static String TABLE_NAME              = "managers";
        
        public final static String COLUMN_ID               = "id";        
        public final static String COLUMN_MANAGER_NAME     = "manager_name";
        public final static String COLUMN_MANAGER_SURNAME  = "manager_surname";
        public final static String COLUMN_MANAGER_TITLE    = "manager_title";
        public final static String COLUMN_DEPARTMENT_ID    = "department_id";         
    }
    
    /**
     * Department Table constants for entity and H2Database
     */
    public static class Department {
        public final static String TABLE_NAME              = "departments";
        
        public final static String COLUMN_ID               = "id"; 
        public final static String COLUMN_DEPARTMENT_NAME  = "department_name";
        public final static String COLUMN_DEPARTMENT_CODE  = "department_code";     
    }
    
    /**
     * Request Table constants for entity and H2Database
     */
    public static class Request {
        public final static String TABLE_NAME                   = "requests";
        
        public final static String COLUMN_ID                    = "id";
        public final static String COLUMN_REQUEST_TITLE         = "request_title";
        public final static String COLUMN_REQUEST_DESCRIPTION   = "request_description";
        public final static String COLUMN_REQUEST_STATUS        = "request_status";
        public final static String COLUMN_REQUEST_CREATE_DATE   = "create_date";    
        public final static String COLUMN_REQUEST_UPDATE_DATE   = "update_date";
        public final static String COLUMN_USER_ID               = "user_id";
    }
    
    /**
     * Task Table constants for entity and H2Database
     */
    public static class Task {
        public final static String TABLE_NAME                   = "tasks";
        
        public final static String COLUMN_ID                    = "id";
        public final static String COLUMN_TASK_TITLE            = "task_title";
        public final static String COLUMN_TASK_DESCRIPTION      = "task_description";
        public final static String COLUMN_TASK_STATUS           = "status";
        public final static String COLUMN_TASK_CREATE_DATE      = "create_date";    
        public final static String COLUMN_TASK_UPDATE_DATE      = "update_date";
        public final static String COLUMN_TASK_START_DATE       = "start_date";    
        public final static String COLUMN_TASK_COMPLETE_DATE    = "complete_date";        
        public final static String COLUMN_REQUEST_ID            = "request_id";
        public final static String COLUMN_EMPLOYEE_ID           = "employee_id";        
    }  
    
    public static class Notification {
        public final static String TABLE_NAME                           = "notifications";
        
        public final static String COLUMN_ID                            = "id";
        public final static String COLUMN_NOTIFICATION_TITLE            = "notification_title";
        public final static String COLUMN_NOTIFICATION_STATUS           = "notificaton_status";
        public final static String COLUMN_NOTIFICATION_USER             = "user_id";
    }
}
