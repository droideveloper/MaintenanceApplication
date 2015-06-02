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
public enum TaskStatus {
    IDLE("idle"),
    PROGRESS("progress"),
    COMPLETE("complete");
    
    private final String value;
    
    TaskStatus(String value) {
        this.value = value;
    }        
}
