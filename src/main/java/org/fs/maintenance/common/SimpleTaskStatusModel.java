/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.common;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import org.fs.maintenance.entities.TaskStatus;

/**
 *
 * @author Fatih
 */
public class SimpleTaskStatusModel extends DefaultComboBoxModel<TaskStatus>{
    
    public SimpleTaskStatusModel(List<TaskStatus> status) {
        super();       
        if(status == null) {
            throw new NullPointerException("status are null");
        }
        for(TaskStatus s : status) {
            addElement(s);
        }
    }      
}
