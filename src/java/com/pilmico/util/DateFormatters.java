/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pilmico.util;

import java.text.SimpleDateFormat;

/**
 *
 * @author CRacaza
 */
public class DateFormatters {
    public static SimpleDateFormat dateFormater = new SimpleDateFormat("MM/dd/yyyy");
    public static SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat dateTimeFormater = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
    public static SimpleDateFormat yearFormater = new SimpleDateFormat("yyyy");
    public static SimpleDateFormat timestampFormatTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    
    public static String timestampFormatTimeStr = "yyyy-MM-dd hh:mm:ss";
    
    //dd-Mon-yyyy
    public static SimpleDateFormat timestampFormatOracle = new SimpleDateFormat("dd-MMM-yyyy");
}
