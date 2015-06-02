/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.ComboBoxModel;
import javax.swing.JPanel;
import org.fs.maintenance.controllers.IAssignController;
import org.fs.maintenance.entities.Department;
import org.fs.maintenance.entities.Employee;
import org.fs.maintenance.entities.Request;
import org.fs.maintenance.entities.Task;
import org.fs.maintenance.entities.User;
import org.fs.maintenance.utils.StringUtility;
import org.fs.maintenance.views.IAssignView;

/**
 *
 * @author Fatih
 */
public class AssignUI extends javax.swing.JPanel implements IAssignView, 
                                                            ActionListener {

    /**
     * Creates new form AssignUI
     */
    private AssignUI() {
        initComponents();
        init();
    }
    
    public static AssignUI createView() {
        return new AssignUI();
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
        comboBoxTasks = new javax.swing.JComboBox();
        btnAssign = new javax.swing.JButton();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        lblCurrentTaskID = new javax.swing.JLabel();
        lblCurrentTaskStartDate = new javax.swing.JLabel();
        lblEmployeeID = new javax.swing.JLabel();
        javax.swing.JLabel jLabel9 = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        lblCurrentTaskDescription = new javax.swing.JTextArea();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        lblCurrentTaskTitle = new javax.swing.JLabel();
        lblNumberOfTask = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel11 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel10 = new javax.swing.JLabel();
        lblDepartmentName = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        lblTaskDescription = new javax.swing.JTextArea();
        javax.swing.JLabel jLabel13 = new javax.swing.JLabel();
        lblCreateDate = new javax.swing.JLabel();
        lblTaskTitle = new javax.swing.JLabel();
        javax.swing.JLabel jLabel15 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        lblTaskID = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();

        btnBack.setText("Back");

        btnAssign.setText("Assign");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Employee Working Task Information"));

        lblCurrentTaskID.setText("jLabel6");

        lblCurrentTaskStartDate.setText("jLabel8");

        lblEmployeeID.setText("jLabel2");

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel9.setText("Task Title");

        lblCurrentTaskDescription.setEditable(false);
        lblCurrentTaskDescription.setColumns(20);
        lblCurrentTaskDescription.setRows(5);
        jScrollPane1.setViewportView(lblCurrentTaskDescription);

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel3.setText("Number Of Tasks");

        lblCurrentTaskTitle.setText("jLabel10");

        lblNumberOfTask.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNumberOfTask.setText("jLabel4");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel5.setText("Current Task ID");

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel11.setText("Task Description");

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel7.setText("Task Start Time");

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel1.setText("Employee ID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel11))
                            .addGap(225, 225, 225))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(232, 232, 232))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(233, 233, 233))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(131, 131, 131))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(97, 97, 97)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblNumberOfTask, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addGap(5, 5, 5)))
                    .addComponent(lblCurrentTaskID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCurrentTaskStartDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCurrentTaskTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmployeeID)
                    .addComponent(lblNumberOfTask))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCurrentTaskID)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCurrentTaskStartDate)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCurrentTaskTitle)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Selected Task Information"));

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel6.setText("Department Created");

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel10.setText("Task Create Date");

        lblDepartmentName.setText("jLabel8");

        lblTaskDescription.setEditable(false);
        lblTaskDescription.setColumns(20);
        lblTaskDescription.setRows(5);
        jScrollPane2.setViewportView(lblTaskDescription);

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel13.setText("Task Title");
        jLabel13.setToolTipText("");

        lblCreateDate.setText("jLabel12");

        lblTaskTitle.setText("jLabel14");

        jLabel15.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel15.setText("Task Description");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setText("Task ID");

        lblTaskID.setText("jLabel4");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTaskTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblCreateDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDepartmentName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2)
                    .addComponent(lblTaskID, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTaskID)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDepartmentName)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCreateDate)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTaskTitle)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblError.setForeground(new java.awt.Color(255, 51, 51));
        lblError.setText("jLabel16");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBack)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(comboBoxTasks, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAssign))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(20, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(comboBoxTasks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAssign))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblError)
                .addContainerGap(157, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAssign;
    private javax.swing.JButton btnBack;
    private javax.swing.JComboBox comboBoxTasks;
    private javax.swing.JLabel lblCreateDate;
    private javax.swing.JTextArea lblCurrentTaskDescription;
    private javax.swing.JLabel lblCurrentTaskID;
    private javax.swing.JLabel lblCurrentTaskStartDate;
    private javax.swing.JLabel lblCurrentTaskTitle;
    private javax.swing.JLabel lblDepartmentName;
    private javax.swing.JLabel lblEmployeeID;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblNumberOfTask;
    private javax.swing.JTextArea lblTaskDescription;
    private javax.swing.JLabel lblTaskID;
    private javax.swing.JLabel lblTaskTitle;
    // End of variables declaration//GEN-END:variables

    private final static String BTN_BACK    = "Back";
    private final static String BTN_ASSIGN  = "Assign";
        
    private IAssignController controller;
    private SimpleDateFormat dateFormat;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if(BTN_BACK.equalsIgnoreCase(cmd)) {
            back();
        } else if(BTN_ASSIGN.equalsIgnoreCase(cmd)) {
            assign();
        } else {
            if(comboBoxTasks.getSelectedIndex() >= 0) {
                selectAt(comboBoxTasks.getSelectedIndex());
            }
            hideError();
        }
    }
    
    @Override
    public void init() {
        btnAssign.addActionListener(this);
        btnBack.addActionListener(this);
        comboBoxTasks.addActionListener(this);
        comboBoxTasks.setMaximumRowCount(5);
        dateFormat = new SimpleDateFormat(StringUtility.SECOND_DATE_FORMAT, Locale.getDefault());
        lblError.setVisible(false);        
        
        //clear
        lblTaskID.setText(null);
        lblCreateDate.setText(null);
        lblTaskTitle.setText(null);
        lblTaskDescription.setText(null);
        lblEmployeeID.setText(null);
        lblNumberOfTask.setText(null);
        lblCurrentTaskID.setText(null);
        lblCurrentTaskStartDate.setText(null);
        lblCurrentTaskTitle.setText(null);
        lblCurrentTaskDescription.setText(null);
        lblDepartmentName.setText(null);
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
    public void displayEmployeeDetail(Employee e) {
        if(e == null) {
            throw new NullPointerException("e is null");
        }
        lblEmployeeID.setText(String.valueOf(e.getId()));
        lblNumberOfTask.setText(String.valueOf(controller.retrieveNumberOfTaskAssigned()));
    }

    @Override
    public void selectAt(int index) {
        if(controller == null) {
            throw new NullPointerException("you should bind view to controller");
        }
        controller.selectAt(index);
    }

    @Override
    public void displayCurrentTask(Task t) {
        if(t == null) {
            throw new NullPointerException("current task is null");
        }
        //if employee working on current task
        lblCurrentTaskID.setText(String.valueOf(t.getId()));
        lblCurrentTaskStartDate.setText(dateFormat.format(t.getStartDate()));
        lblCurrentTaskTitle.setText(t.getTitle());
        lblCurrentTaskDescription.setText(t.getDescription());
    }

    @Override
    public void displayTaskDetail(Task t) {
        if(t == null) {
            throw new NullPointerException("task is null");
        }
        lblTaskID.setText(String.valueOf(t.getId()));
        lblCreateDate.setText(dateFormat.format(t.getCreateDate()));
        lblTaskTitle.setText(t.getTitle());
        
        Request request = t.getRequest();
        if(request != null) {
            User usr = request.getUser();
            if(usr != null) {
                Department dept = controller.retrieveDepartmentByUser(usr);
                if(dept != null) {
                    lblDepartmentName.setText(dept.getDepartmentName());
                }
            }
        }
        lblTaskDescription.setText(t.getDescription());
    }

    @Override
    public void displayTasks(ComboBoxModel<Task> tasks) {
        if(tasks == null) {
            throw new NullPointerException("tasks model is null");
        }
        comboBoxTasks.setModel(tasks);
        comboBoxTasks.setRenderer(TaskBox.createView());
    }

    @Override
    public void setController(IAssignController controller) {
        this.controller = controller;
    }

    @Override
    public void assign() {
        if(controller == null) {
            throw new NullPointerException("you should bind view to controller");
        }
        controller.assign();
    }

    @Override
    public void back() {
        if(controller == null) {
            throw new NullPointerException("you should bind view to controller");
        }   
        controller.back();
    }

    @Override
    public String getWindowName() {
        return AssignUI.class.getSimpleName();
    }

    @Override
    public JPanel createContentView() {
        return this;
    }
}