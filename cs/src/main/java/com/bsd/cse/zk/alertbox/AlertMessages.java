/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.alertbox;

import java.util.Locale;
import java.util.regex.Pattern;
import org.zkoss.util.ResourceUtil;

/**
 *
 * @author bento
 */
public class AlertMessages {
    public static String DEFAULT_ERROR_CODE = "ER-00001";
    public static int YES = CsMessagebox.YES;
    public static int NO = CsMessagebox.NO;
    public static int CANCEL = CsMessagebox.CANCEL;
    public static int OK = CsMessagebox.OK;
    public static int ABORT = CsMessagebox.ABORT;
    public static int IGNORE = CsMessagebox.IGNORE;
    public static int RETRY = CsMessagebox.RETRY;

    public static int alertMessage(String messageCode)
    {
       return alertMessage(messageCode,null);
    } 

    public static int alertMessage(String messageCode,String messageHeader)
    {
       return alertMessage(messageCode,messageHeader,null);
    }
    
    public static int alertMessage(String messageCode,String messageHeader,String icon)
    {
       return showMessage(messageCode,((messageHeader==null || messageHeader.trim().isEmpty())?"default.alert.message.title":messageHeader),CsMessagebox.OK,(icon==null || icon.trim().isEmpty())?CsMessagebox.INFORMATION:icon);
    }

    public static int confirmMessage(String messageCode)
    {
       return confirmMessage(messageCode,"default.alert.message.title",CsMessagebox.QUESTION);
    }

    public static int successMessage(String messageCode)
    {
       return alertMessage(messageCode,"default.alert.message.title",CsMessagebox.SUCCESS);
    }

    public static int failMessage(String messageCode)
    {
       return alertMessage(messageCode,"default.alert.message.title",CsMessagebox.FAIL);
    }

    public static int confirmMessage(String messageCode,String messageHeader)
    {
       return confirmMessage(messageCode,messageHeader,CsMessagebox.QUESTION);
    }

    public static int confirmMessage(String messageCode,String messageHeader,String icon)
    {
       return showMessage(messageCode,((messageHeader==null || messageHeader.trim().isEmpty())?"default.alert.message.title":messageHeader),CsMessagebox.YES|CsMessagebox.NO,(icon==null || icon.trim().isEmpty())?CsMessagebox.QUESTION:icon);
    }

  
    private static int showMessage(String messageCode,String messageHeader, int buttons,String icon)
    {
        try {
               return CsMessagebox.show(ResourceUtil.getLabel(messageCode),ResourceUtil.getLabel(messageHeader) , buttons, icon);
        } catch (InterruptedException ex) {

        }
        return 0;
    }

    private static Boolean checkMessage(String errorCode)
    {
        return Pattern.matches("[\\w]{2}-[\\d]{5}", errorCode);
    }

}
