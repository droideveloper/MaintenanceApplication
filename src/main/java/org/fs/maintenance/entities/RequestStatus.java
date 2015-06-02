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
public enum RequestStatus {
    WAITING("waiting"),
    DEPARTMENT_APPROVED("department_approved"),
    IT_APPROVED("it_approved"),
    DEPARTMENT_REJECTED("department_rejected"),
    IT_REJECTED("it_rejected");
    
    private final String value;
    
    RequestStatus(String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return value;
    }
}
