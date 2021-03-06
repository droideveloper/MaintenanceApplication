/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.gui;

import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import org.fs.maintenance.common.*;
import org.fs.maintenance.entities.Department;
import org.fs.maintenance.entities.Employee;
import org.fs.maintenance.entities.Manager;
import org.fs.maintenance.entities.Request;
import org.fs.maintenance.entities.User;
import org.fs.maintenance.utils.StringUtility;

/**
 *
 * @author Fatih
 */
public class ListRequestCell extends javax.swing.JPanel implements ListCellRenderer<Request>{

    /**
     * Creates new form ListRequestCell
     */
    private ListRequestCell() {
        initComponents();
        init();
    }
    
    public static ListRequestCell createView() {
        return new ListRequestCell();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblRequestID = new javax.swing.JLabel();
        lblRequestTitle = new javax.swing.JLabel();
        lblDepartmentName = new javax.swing.JLabel();
        lblEmployeeID = new javax.swing.JLabel();
        lblCreateDate = new javax.swing.JLabel();

        lblRequestID.setText("jLabel1");
        lblRequestID.setMaximumSize(new java.awt.Dimension(32, 16));
        lblRequestID.setMinimumSize(new java.awt.Dimension(32, 16));
        lblRequestID.setPreferredSize(new java.awt.Dimension(32, 16));

        lblRequestTitle.setText("jLabel2");
        lblRequestTitle.setMaximumSize(new java.awt.Dimension(120, 16));
        lblRequestTitle.setMinimumSize(new java.awt.Dimension(120, 16));
        lblRequestTitle.setPreferredSize(new java.awt.Dimension(120, 16));

        lblDepartmentName.setText("jLabel3");
        lblDepartmentName.setMaximumSize(new java.awt.Dimension(120, 16));
        lblDepartmentName.setMinimumSize(new java.awt.Dimension(120, 16));
        lblDepartmentName.setPreferredSize(new java.awt.Dimension(120, 16));

        lblEmployeeID.setText("jLabel4");
        lblEmployeeID.setMaximumSize(new java.awt.Dimension(32, 16));
        lblEmployeeID.setMinimumSize(new java.awt.Dimension(32, 16));
        lblEmployeeID.setPreferredSize(new java.awt.Dimension(32, 16));

        lblCreateDate.setText("jLabel5");
        lblCreateDate.setMaximumSize(new java.awt.Dimension(85, 16));
        lblCreateDate.setMinimumSize(new java.awt.Dimension(85, 16));
        lblCreateDate.setPreferredSize(new java.awt.Dimension(85, 16));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRequestID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblRequestTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDepartmentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblCreateDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRequestID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRequestTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDepartmentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCreateDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCreateDate;
    private javax.swing.JLabel lblDepartmentName;
    private javax.swing.JLabel lblEmployeeID;
    private javax.swing.JLabel lblRequestID;
    private javax.swing.JLabel lblRequestTitle;
    // End of variables declaration//GEN-END:variables

    private Color firstColor;
    private Color secondColor;
    private Color selectedColor;
    
    private SimpleDateFormat dateFormat;
    
    private final IDatabaseManager dbManager = DatabaseManager.getSharedInstance();
    
    private void init() {
        firstColor = StringUtility.hex2Rgb(StringUtility.FIRST_COLOR_STR);
        secondColor = StringUtility.hex2Rgb(StringUtility.SECOND_COLOR_STR);
        selectedColor = StringUtility.hex2Rgb(StringUtility.SELECTED_COLOR_STR); 
        dateFormat = new SimpleDateFormat(StringUtility.FIRST_DATE_FORMAT, Locale.getDefault());
    }
    
    private Department findDepartment(int id) {
        try {
            return dbManager.findDepartmentByDepartmentId(id);
        } catch(SQLException e) {            
        }
        return null;
    }
    
    @Override
    public Component getListCellRendererComponent(JList<? extends Request> list, Request value, int index, boolean isSelected, boolean cellHasFocus) {
        lblRequestID.setText(String.valueOf(value.getId()));
        lblRequestTitle.setText(value.getTitle());
        lblCreateDate.setText(dateFormat.format(value.getCreateDate()));
        User usr = value.getUser();
        Employee e = usr.getEmployee();
        if(e != null) {
            lblEmployeeID.setText(String.valueOf(e.getId()));
            lblDepartmentName.setText(findDepartment(e.getDepartment().getId()).getDepartmentName());
        }
        Manager m = usr.getManager();
        if(m != null) {
            lblEmployeeID.setText(String.valueOf(m.getId()));
            lblDepartmentName.setText(findDepartment(m.getDepartment().getId()).getDepartmentName());
        }
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
