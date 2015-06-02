/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.common;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import org.fs.maintenance.entities.Employee;

/**
 *
 * @author Fatih
 */
public class SimpleEmployeeModel extends DefaultComboBoxModel<Employee>{
    
    private final static String NOT_SELECTABLE = " - Select an Employee - ";
    private boolean selectionAllowed = true;
    
    public SimpleEmployeeModel(List<Employee> employees) {
        super();
        if(employees == null) {
            throw new NullPointerException("employees are null");
        }
        Employee.Builder employeeBuilder = new Employee.Builder()
                .name(NOT_SELECTABLE);
        employees.add(0, employeeBuilder.build());
        for(Employee e : employees) {
            addElement(e);
        }
    }

    @Override
    public void setSelectedItem(Object anObject) {
        Employee e = (Employee)anObject;
        if(!NOT_SELECTABLE.equalsIgnoreCase(e.getName())) {
            super.setSelectedItem(anObject);
        } else if(selectionAllowed) {
            selectionAllowed = false;
            super.setSelectedItem(anObject);
        }
    }    
}
