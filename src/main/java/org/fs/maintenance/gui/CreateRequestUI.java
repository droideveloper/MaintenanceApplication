/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JPanel;
import org.fs.maintenance.controllers.ICreateRequestController;
import org.fs.maintenance.views.ICreateRequestView;

/**
 *
 * @author Fatih
 */
public class CreateRequestUI extends javax.swing.JPanel implements ActionListener, FocusListener, ICreateRequestView {

    /**
     * Creates new form CreateRequestUI
     */
    private CreateRequestUI() {
        initComponents();
        init();
    }
    
    public static CreateRequestUI createView() {
        return new CreateRequestUI();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBack = new javax.swing.JButton();
        btnRequestHistory = new javax.swing.JButton();
        lblError = new javax.swing.JLabel();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel lblTitle = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane = new javax.swing.JScrollPane();
        descriptionField = new javax.swing.JTextArea();
        javax.swing.JLabel lblDescription = new javax.swing.JLabel();
        btnApply = new javax.swing.JButton();
        titleField = new javax.swing.JTextField();

        btnBack.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        btnBack.setText("Back");

        btnRequestHistory.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        btnRequestHistory.setText("Request History");

        lblError.setForeground(new java.awt.Color(255, 51, 51));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Request Information"));

        lblTitle.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblTitle.setText("Title");

        descriptionField.setColumns(20);
        descriptionField.setRows(5);
        jScrollPane.setViewportView(descriptionField);

        lblDescription.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblDescription.setText("Description");

        btnApply.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        btnApply.setText("Apply");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnApply)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTitle)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDescription)
                            .addComponent(titleField)
                            .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnApply)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                        .addComponent(btnRequestHistory))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(btnRequestHistory))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblError)
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnRequestHistory;
    private javax.swing.JTextArea descriptionField;
    private javax.swing.JLabel lblError;
    private javax.swing.JTextField titleField;
    // End of variables declaration//GEN-END:variables

    private final static String BTN_APPLY               = "Apply";
    private final static String BTN_BACK                = "Back";
    private final static String BTN_REQUEST_HISTORY     = "Request History";
    
    private ICreateRequestController controller;
    private String title;
    private String description;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if(BTN_BACK.equalsIgnoreCase(cmd)) {
            back();
        } else if(BTN_APPLY.equalsIgnoreCase(cmd)) {
            apply();
        } else if(BTN_REQUEST_HISTORY.equalsIgnoreCase(cmd)) {
            displayRequestsHistory();
        } else {
            throw new UnsupportedOperationException(String.format("%s command not supported", cmd));
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        hideError();
    }

    @Override
    public void focusLost(FocusEvent e) {
        //NO-OP
    }

    @Override
    public void init() {
        btnRequestHistory.addActionListener(this);
        btnBack.addActionListener(this);
        btnApply.addActionListener(this);
        descriptionField.addFocusListener(this);
        titleField.addFocusListener(this);
        lblError.setVisible(false);
    }
    
    @Override
    public void clear() {
        titleField.setText(null);
        descriptionField.setText(null);
        setTitle(null);
        setDescription(null);
    }

    @Override
    public void showError(String msg) {
        lblError.setText(msg);
        if(!lblError.isVisible()) {
            lblError.setVisible(true);
        }
    }

    @Override
    public void hideError() {
        lblError.setText(null);
        if(lblError.isVisible()) {
            lblError.setVisible(false);
        }        
    }

    @Override
    public void apply() {
        if(controller == null) {
            throw new NullPointerException("you should bind view to controller");
        }
        setTitle(titleField.getText());
        setDescription(descriptionField.getText());
        controller.apply();
    }

    @Override
    public void back() {
        if(controller == null) {
            throw new NullPointerException("you should bind view to controller");
        }
        controller.back();
    }

    @Override
    public void displayRequestsHistory() {
        if(controller == null) {
            throw new NullPointerException("you should bind view to controller");
        }
        controller.displayRequestsHistory();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setController(ICreateRequestController controller) {
       this.controller = controller;
    }

    @Override
    public String getWindowName() {
        return CreateRequestUI.class.getSimpleName();
    }
    
    @Override
    public JPanel createContentView() {
        return this;
    }
    
    private void setTitle(String title) {
        this.title = title;
    }
    
    private void setDescription(String description) {
        this.description = description;
    }
}
