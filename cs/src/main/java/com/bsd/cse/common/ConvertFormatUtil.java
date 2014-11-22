/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsd.cse.common;

import com.bsd.cse.model.BilingualText;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author thanasith
 */
public class ConvertFormatUtil {
    
    private final static String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";  
    private final static String DEFAULT_NUMBER_FORMAT = "###0";
    private final static String DEFAULT_NO_DATA = "-";
    
    public static String convertFormat(String str)
    {
        return (str != null && str.trim().length() > 0)?str.trim():DEFAULT_NO_DATA;
    }   
    
    public static String convertFormat(BilingualText bi,Locale locale)
    {
        if(locale.equals(Locale.US))
        {
            String str = null!=bi?bi.getEn():null;
            return (str != null && str.trim().length() > 0)?str.trim():DEFAULT_NO_DATA;
        }
        else
        {
            String str = null!=bi?bi.getLo():null;
            return (str != null && str.trim().length() > 0)?str.trim():DEFAULT_NO_DATA;
        }
    }  
    
    public static String convertFormat(Number number,String format)
    {
        if(number == null)
        {
            return convertFormat((String)null);
        }     
        
        DecimalFormat numberFormat = new DecimalFormat();
        try
        {
            numberFormat.applyPattern(format);
            return numberFormat.format(number);
        }
        catch(Exception e)
        {            
            return convertFormat(number);
        }
        
    }
    
    
    public static String convertFormat(Number number)
    {
        return convertFormat(number, DEFAULT_NUMBER_FORMAT);
    }
    
    public static String convertFormat(Date date,String format)
    {
        if(date == null)
        {
            return convertFormat((String)null);
        } 
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(format,Locale.US);
        try
        {            
            return dateFormat.format(date);
        }
        catch(Exception e)
        {            
            return convertFormat(date);
        }        
    }
    
    public static String convertFormat(Date date)
    {
        return convertFormat(date, DEFAULT_DATE_FORMAT);
    }

    public static BilingualText createNameBilingualText(String name, Locale locale)
    {
        BilingualText bt = new BilingualText();
         if(null == locale)
         {
             bt = createNameBilingualTextAllLocale(name);
         }
         else if (locale.equals(Locale.US))
         {
             bt.setEn(name);
         }
         else
         {
            bt.setLo(name);
         }
        return bt;
    }
    
    public static BilingualText createNameBilingualTextAllLocale(String name)
    {
        BilingualText bt = new BilingualText();
        bt.setEn(name);
        bt.setLo(name);

        return bt;
    }
}
