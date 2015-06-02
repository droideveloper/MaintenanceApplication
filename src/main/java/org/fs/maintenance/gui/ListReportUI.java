/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilCalendarModel;
import org.fs.maintenance.controllers.IListReportController;
import org.fs.maintenance.views.IListReportView;


/**
 *
 * @author Fatih
 */
public class ListReportUI extends javax.swing.JPanel implements IListReportView,
                                                                ActionListener {

    /**
     * Creates new form ListReportUI
     */
    private ListReportUI() {
        initComponents();
        init();
    }
    
    public static ListReportUI createView() {
        return new ListReportUI();
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
        lblError = new javax.swing.JLabel();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        btnFilter = new javax.swing.JButton();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        listReport = new javax.swing.JList();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        lblResultTitle = new javax.swing.JLabel();
        lblResult = new javax.swing.JLabel();

        btnBack.setText("Back");

        lblError.setForeground(new java.awt.Color(255, 51, 51));
        lblError.setText("jLabel3");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Report Information"));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setText("End Date");

        btnFilter.setText("Filter");

        jScrollPane1.setViewportView(listReport);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel1.setText("Start Date");

        lblResultTitle.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblResultTitle.setText("jLabel3");

        lblResult.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblResult.setText("jLabel3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(btnFilter))
                            .addComponent(jLabel2))
                        .addGap(0, 73, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblResultTitle)
                        .addGap(31, 31, 31)
                        .addComponent(lblResult)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFilter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblResultTitle)
                    .addComponent(lblResult))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBack)
                        .addGap(441, 441, 441))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(lblError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(99, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addGap(33, 33, 33)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblError)
                .addContainerGap(68, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnFilter;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblResult;
    private javax.swing.JLabel lblResultTitle;
    private javax.swing.JList listReport;
    // End of variables declaration//GEN-END:variables

    private final static String BTN_BACK    = "Back";
    private final static String BTN_FILTER  = "Filter";
    
    private IListReportController controller;
    
    private JDatePickerImpl endDatePicker, startDatePicker;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if(BTN_BACK.equalsIgnoreCase(cmd)) {
            back();
        } else if(BTN_FILTER.equalsIgnoreCase(cmd)) {
            filter();
        } else {
            throw new UnsupportedOperationException(String.format(Locale.getDefault(), "%s command is not recognized", cmd));
        }
    }
    
    @Override
    public void init() {
        UtilCalendarModel caledarModel = new UtilCalendarModel();        
        JDatePanelImpl datePanel = new JDatePanelImpl(caledarModel);
        endDatePicker = new JDatePickerImpl(datePanel, new JFormattedTextField.AbstractFormatter() {
            @Override
            public Object stringToValue(String text) throws ParseException {
                return new SimpleDateFormat("dd/MM/yyyy")
                        .parse(text);
            }

            @Override
            public String valueToString(Object value) throws ParseException {
                if(value != null) {
                    return new SimpleDateFormat("dd/MM/yyyy")
                            .format(((Calendar)value).getTime());
                } 
                return null;
            }
        });
        caledarModel = new UtilCalendarModel();
        datePanel = new JDatePanelImpl(caledarModel);
        startDatePicker = new JDatePickerImpl(datePanel, new JFormattedTextField.AbstractFormatter() {
            @Override
            public Object stringToValue(String text) throws ParseException {
                return new SimpleDateFormat("dd/MM/yyyy")
                        .parse(text);
            }

            @Override
            public String valueToString(Object value) throws ParseException {
                if(value != null) {
                    return new SimpleDateFormat("dd/MM/yyyy")
                            .format(((Calendar)value).getTime());
                }
                return null;
            }
        });        

        endDatePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar calendar = (Calendar)endDatePicker.getModel().getValue();
                if(calendar != null) {
                    endDate(calendar.getTime());
                } 
                else {
                    endDate(null);
                }
            }
        });
        startDatePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar calendar = (Calendar)startDatePicker.getModel().getValue();
                if(calendar != null) {
                    startDate(calendar.getTime());
                } 
                else {
                    startDate(null);
                }
            }
        });
        
        removeAll();
        
        btnBack = new javax.swing.JButton();
        lblError = new javax.swing.JLabel();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        btnFilter = new javax.swing.JButton();
        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        listReport = new javax.swing.JList();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        lblResultTitle = new javax.swing.JLabel();
        lblResult = new javax.swing.JLabel();

        btnBack.setText("Back");

        lblError.setForeground(new java.awt.Color(255, 51, 51));
        lblError.setText("jLabel3");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Report Information"));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setText("End Date");

        btnFilter.setText("Filter");

        jScrollPane1.setViewportView(listReport);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel1.setText("Start Date");

        lblResultTitle.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblResultTitle.setText("jLabel3");

        lblResult.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        lblResult.setText("jLabel3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(endDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(btnFilter))
                            .addComponent(jLabel2))
                        .addGap(0, 73, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblResultTitle)
                        .addGap(31, 31, 31)
                        .addComponent(lblResult)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(startDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFilter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblResultTitle)
                    .addComponent(lblResult))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBack)
                        .addGap(441, 441, 441))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(lblError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(99, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addGap(33, 33, 33)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblError)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        
        btnBack.addActionListener(this);
        btnFilter.addActionListener(this);        
        lblError.setVisible(false);
 
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
        if(!lblError.isVisible()) {
            lblError.setVisible(false);
        }    
    }

    @Override
    public void back() {
        if(controller == null) {
            throw new NullPointerException("you should bind view to controller");
        }
        controller.back();
    }

    @Override
    public void filter() {
        if(controller == null) {
            throw new NullPointerException("you should bind view to controller");
        }        
        controller.filter();
    }

    @Override
    public void startDate(Date startDate) {
        if(controller == null) {
            throw new NullPointerException("you should bind view to controller");
        }  
        controller.startDate(startDate);
    }

    @Override
    public void endDate(Date endDate) {
        if(controller == null) {
            throw new NullPointerException("you should bind view to controller");
        }
        controller.endDate(endDate);
    }

    @Override
    public void displayReports(ListModel listModel) {
        if(listModel == null) {
            throw new NullPointerException("list model is null");
        }
        listReport.setModel(listModel);
    }

    @Override
    public void setController(IListReportController controller) {
        this.controller = controller;
    }
    
    @Override
    public void displayMean(String title, String value) {
        lblResultTitle.setText(title);
        lblResult.setText(value);
    }

    @Override
    public void setListRenderer(ListCellRenderer renderer) {
        if(renderer == null) {
            throw new NullPointerException("rendered is null");
        }
        listReport.setCellRenderer(renderer);
    }

    @Override
    public void enabledFilter() {
        btnFilter.setEnabled(true);
    }

    @Override
    public void disableFilter() {
        btnFilter.setEnabled(false);
    }

    @Override
    public String getWindowName() {
        return ListReportUI.class.getSimpleName();
    }

    @Override
    public JPanel createContentView() {
        return this;
    }
}
