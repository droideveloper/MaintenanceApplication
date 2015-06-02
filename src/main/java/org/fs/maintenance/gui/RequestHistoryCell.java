/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.gui;

import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import org.fs.maintenance.entities.Request;
import org.fs.maintenance.utils.StringUtility;

/**
 *
 * @author Fatih
 */
public class RequestHistoryCell extends javax.swing.JPanel implements ListCellRenderer<Request> {

    /**
     * Creates new form RequestHistoryCell
     */
    private RequestHistoryCell() {
        initComponents();
        init();
    }

    public static RequestHistoryCell createView() {
        return new RequestHistoryCell();
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
        lblTitle = new javax.swing.JLabel();
        lblRequestStatus = new javax.swing.JLabel();
        lblCreateDate = new javax.swing.JLabel();
        lblUpdateDate = new javax.swing.JLabel();

        lblRequestID.setText("requestID");
        lblRequestID.setMaximumSize(new java.awt.Dimension(32, 16));
        lblRequestID.setMinimumSize(new java.awt.Dimension(32, 16));
        lblRequestID.setPreferredSize(new java.awt.Dimension(32, 16));

        lblTitle.setText("requestTitle");
        lblTitle.setMaximumSize(new java.awt.Dimension(120, 16));
        lblTitle.setMinimumSize(new java.awt.Dimension(120, 16));
        lblTitle.setPreferredSize(new java.awt.Dimension(120, 16));

        lblRequestStatus.setText("requestStatus");
        lblRequestStatus.setMaximumSize(new java.awt.Dimension(120, 16));
        lblRequestStatus.setMinimumSize(new java.awt.Dimension(120, 16));
        lblRequestStatus.setPreferredSize(new java.awt.Dimension(120, 16));

        lblCreateDate.setText("createDate");
        lblCreateDate.setMaximumSize(new java.awt.Dimension(80, 16));
        lblCreateDate.setMinimumSize(new java.awt.Dimension(80, 16));
        lblCreateDate.setPreferredSize(new java.awt.Dimension(80, 16));

        lblUpdateDate.setText("updateDate");
        lblUpdateDate.setMaximumSize(new java.awt.Dimension(80, 16));
        lblUpdateDate.setMinimumSize(new java.awt.Dimension(80, 16));
        lblUpdateDate.setPreferredSize(new java.awt.Dimension(80, 16));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRequestID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblRequestStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCreateDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblUpdateDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRequestID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRequestStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCreateDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUpdateDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCreateDate;
    private javax.swing.JLabel lblRequestID;
    private javax.swing.JLabel lblRequestStatus;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUpdateDate;
    // End of variables declaration//GEN-END:variables
    
    private Color firstColor;
    private Color secondColor;
    private Color selectedColor;
    
    private SimpleDateFormat dateFormat; 
    
    private void init() {
        dateFormat = new SimpleDateFormat(StringUtility.FIRST_DATE_FORMAT, Locale.getDefault());
        firstColor = StringUtility.hex2Rgb(StringUtility.FIRST_COLOR_STR);
        secondColor = StringUtility.hex2Rgb(StringUtility.SECOND_COLOR_STR);
        selectedColor = StringUtility.hex2Rgb(StringUtility.SELECTED_COLOR_STR);
    }
    
    @Override
    public Component getListCellRendererComponent(JList<? extends Request> list, Request r, int index, boolean isSelected, boolean cellHasFocus) {
        lblRequestID.setText(String.valueOf(r.getId()));
        lblTitle.setText(r.getTitle());
        lblRequestStatus.setText(r.getStatus().toString());
        lblCreateDate.setText(dateFormat.format(r.getCreateDate()));
        if(r.getUpdateDate() != null) {
            lblUpdateDate.setText(dateFormat.format(r.getUpdateDate()));
        } else {
            lblUpdateDate.setText("NaN");
        }
        if(isSelected) {
            setBackground(selectedColor);
        } else {
            boolean mod = (index % 2 == 0);
            if(mod) {
                setBackground(firstColor);
            } else {
                setBackground(secondColor);
            }
        }
        return this;
    }
}