/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.common;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import org.fs.maintenance.entities.Task;

/**
 *
 * @author Fatih
 */
public class SimpleTaskModel extends DefaultComboBoxModel<Task> {

    private static final String NOT_SELECTABLE_OPTION = " - Select an Option - ";
    boolean selectionAllowed = true;
    
    public SimpleTaskModel(List<Task> tasks) {
        super();
        if(tasks == null) {
            throw new NullPointerException("tasks are null");
        }
        Task.Builder taskBuilder = new Task.Builder()
                .title(NOT_SELECTABLE_OPTION);                
        tasks.add(0, taskBuilder.build());
        for(Task t : tasks) {
            addElement(t);
        }
    }    

    /**
     * first item allowed to be selected only once
     * @param anObject 
     */
    @Override
    public void setSelectedItem(Object anObject) {
        Task aTask = (Task)anObject;
        if(!NOT_SELECTABLE_OPTION.equalsIgnoreCase(aTask.getTitle())) {
            super.setSelectedItem(anObject);
        } else if(selectionAllowed) {
            selectionAllowed = false;
            super.setSelectedItem(anObject);
        }
    }    
}
