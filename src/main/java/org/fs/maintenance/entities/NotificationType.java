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
public enum NotificationType {
    INFO("info"),
    WARNING("warning"),
    ERROR("error");
    
    private final String value;
    
    NotificationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }    
}
