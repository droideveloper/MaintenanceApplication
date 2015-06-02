/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.entities;

/**
 *
 * @author Fatih
 */
public enum ReportType {
    SELECT_TASK_REPORT(" - Select a Task Type - "),
    EMPLOYEE_TASK_REPORT("employee_task_report"),
    COMPLETED_TASK_REPORT("completed_task_report"),
    PROGRESS_TASK_REPORT("progress_task_report"),
    CREATED_TASK_REPORT("created_task_report");
    
    private final String value;
    
    ReportType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }   
}
