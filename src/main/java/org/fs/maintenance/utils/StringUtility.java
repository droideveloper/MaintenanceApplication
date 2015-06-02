/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.utils;

import java.awt.Color;

/**
 *
 * @author Fatih
 */
public class StringUtility {
    
    public final static String FIRST_DATE_FORMAT  = "dd MMM yy";
    public final static String SECOND_DATE_FORMAT = "dd MMM yy HH:mm";
    
    /**
     * Color hex used for list background as stated in the name
     * firstCellColor       ->  FIRST_COLOR_STR
     * secondCellColor      ->  SECOND_COLOR_STR
     * selectedCellColor    ->  SELCTED_COLOR_STR
     */
    public final static String FIRST_COLOR_STR      = "#EDF3EE";
    public final static String SECOND_COLOR_STR     = "#FFFFFF";
    public final static String SELECTED_COLOR_STR   = "#E1E1E1";
    
    /**
     * Check for object of the 
     * @param <T> Generic Type of Object
     * @param value 
     * @return true or false depending on the instance type
     */
    public static <T> boolean isNullOrEmpty(T value) {
        if(value == null) return true;
        if(value instanceof String) {
            String v = (String)value;
            return "".equalsIgnoreCase(v);
        }
        return false;
    }    
    
    /**
     * Parse seven char of hex code to Color object instance example colorStr : #FFFFFF(white), #F5F5F5 (white-smoke) 
     * @param colorStr
     * @return 
     */
    public static Color hex2Rgb(String colorStr) {
    return new Color(
            Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
            Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
            Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
    }
}
