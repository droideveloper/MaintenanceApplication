/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import org.fs.maintenance.common.Pair;
import org.fs.maintenance.entities.Employee;
import org.fs.maintenance.utils.StringUtility;

/**
 *
 * @author Fatih
 */
public class EmployeeNumbersCell extends javax.swing.JPanel implements ListCellRenderer<Pair<Employee, Integer>>{

    /**
     * Creates new form EmployeeNumbersCell
     */
    private EmployeeNumbersCell(int x) {
        initComponents();
        init();
        this.x = x;
    }
    
    public static EmployeeNumbersCell createView(int x) {
        return new EmployeeNumbersCell(x);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEmployeeName = new javax.swing.JLabel();
        lblNumber = new javax.swing.JLabel();

        lblEmployeeName.setText("jLabel1");

        lblNumber.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEmployeeName, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(lblNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmployeeName)
                    .addComponent(lblNumber))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblEmployeeName;
    private javax.swing.JLabel lblNumber;
    // End of variables declaration//GEN-END:variables

    private Color firstColor;
    private Color secondColor;
    private final int   x;
    
    private void init() {
        firstColor = StringUtility.hex2Rgb(StringUtility.FIRST_COLOR_STR);
        secondColor = StringUtility.hex2Rgb(StringUtility.SECOND_COLOR_STR);
    }
    
    @Override
    public Component getListCellRendererComponent(JList<? extends Pair<Employee, Integer>> list, Pair<Employee, Integer> value, int index, boolean isSelected, boolean cellHasFocus) {
        if(index == 0) {
            if(x == 1) {
                lblEmployeeName.setText("EMPLOYEE NAME");
                lblNumber.setText("NUMBER OF TASKS ASSIGNED");
            } else if(x == 2) {
                lblEmployeeName.setText("EMPLOYEE NAME");
                lblNumber.setText("NUMBER OF TASKS COMPLETED");
            }
            setBackground(StringUtility.hex2Rgb(StringUtility.SELECTED_COLOR_STR));
        } else {       
            lblEmployeeName.setText(value.getKey().getName());
            lblNumber.setText(String.valueOf(value.getValue()));
            boolean mod = index % 2 == 0;
            if(mod) {
                setBackground(firstColor);
            } else {
                setBackground(secondColor);
            }       
        }
        return this;
    }
}
