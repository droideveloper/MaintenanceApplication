/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.base;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import org.fs.maintenance.views.IView;

/**
 *
 * @author Fatih
 * @param <T>
 * 
 */
public abstract class AbstractController<T extends IView> implements WindowListener {
    
    protected T             view;
    protected JFrame        jFrame;
    private WindowListener  listener;
    
    /**
     * 
     * @param view 
     */
    public AbstractController(T view) {
        this.view = view;
        onCreate();
    }

    /**
     * 
     * @param e 
     */
    @Override
    public final void windowDeactivated(WindowEvent e) {
        if(listener != null) {
           listener.windowDeactivated(e);
        }
    }

    /**
     * 
     * @param e 
     */
    @Override
    public final void windowActivated(WindowEvent e) {
        if(listener != null) {
           listener.windowActivated(e);
        }
    }

    /**
     * 
     * @param e 
     */
    @Override
    public final void windowDeiconified(WindowEvent e){
        if(listener != null) {
           listener.windowDeiconified(e);
        }
    }

    /**
     * 
     * @param e 
     */
    @Override
    public final void windowIconified(WindowEvent e) {
        if(listener != null) {
           listener.windowIconified(e);
        }
    }

    /**
     * 
     * @param e 
     */
    @Override
    public final void windowClosed(WindowEvent e) {
        onDestroy();
        if(listener != null) {
           listener.windowClosed(e);
        }
    }

    /**
     * 
     * @param e 
     */
    @Override
    public final void windowClosing(WindowEvent e) {
        onStop();
        if(listener != null) {
           listener.windowClosing(e);
        }
    }

    /**
     * 
     * @param e 
     */
    @Override
    public final void windowOpened(WindowEvent e) {
        onStart();
        if(listener != null) {
            listener.windowOpened(e);
        }
    }    
    
    /**
     * 
     */
    protected void onCreate() {
        jFrame = new JFrame(getWindowName());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setContentPane(view.createContentView());
        jFrame.addWindowListener(this);
        jFrame.setUndecorated(true);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);//makes all windows stay center of the PC's display center
        jFrame.setVisible(true);
    }
    
    /**
     * 
     */
    protected void onStart() {
    }
    
    /**
     * 
     */
    protected void onStop() {
    }
    
    /**
     * 
     */
    protected void onDestroy() {
    }
    
    /**
     * 
     */
    public final void disposeView() {
        if(jFrame != null) {
            jFrame.dispose();
        }
    }
    
    public final void toggleView() {
        boolean visible = jFrame.isVisible();
        if(visible) {
            jFrame.setVisible(!visible);
        } else {
            jFrame.setVisible(!visible);
            onStart();//this is required refresh of data retriaval on parts
        }
    }
    
    /**
     * 
     * @param listener 
     */
    public final void setWindowListener(WindowListener listener) {
        this.listener = listener;
    }
    
    /**
     * 
     * @return 
     */
    protected abstract String getWindowName();
}
