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
import org.fs.maintenance.entities.Task;
import org.fs.maintenance.utils.StringUtility;

/**
 *
 * @author Fatih
 */
public class TaskBox extends javax.swing.JPanel implements ListCellRenderer<Task> {

    /**
     * Creates new form TaskBox
     */
    private TaskBox() {
        initComponents();
        init();
    }
    
    public static TaskBox createView() {
        return new TaskBox();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTaskID = new javax.swing.JLabel();
        lblTaskTitle = new javax.swing.JLabel();

        lblTaskID.setText("jLabel1");

        lblTaskTitle.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTaskID)
                .addGap(18, 18, 18)
                .addComponent(lblTaskTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTaskID)
                    .addComponent(lblTaskTitle))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTaskID;
    private javax.swing.JLabel lblTaskTitle;
    // End of variables declaration//GEN-END:variables

    private Color firstColor;
    private Color secondColor;
    private Color selectedColor;
    
    private void init() {
        firstColor = StringUtility.hex2Rgb(StringUtility.FIRST_COLOR_STR);
        secondColor = StringUtility.hex2Rgb(StringUtility.SECOND_COLOR_STR);
        selectedColor = StringUtility.hex2Rgb(StringUtility.SELECTED_COLOR_STR);         
    }
    
    @Override
    public Component getListCellRendererComponent(JList<? extends Task> list, Task value, int index, boolean isSelected, boolean cellHasFocus) {
        lblTaskID.setText(String.valueOf(value.getId()));
        lblTaskTitle.setText(value.getTitle());
        if(isSelected) {
            setBackground(selectedColor);
        } else {
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