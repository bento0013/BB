/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.zkoss.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.lang.Library;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;

/**
 *
 * @author bento
 */
public class ResourceUtil {
    private static Log LOG = LogFactory.getLog(ResourceUtil.class);
    private final static String bundle =Library.getProperty("org.zkoss.util.property.resources","Resources");
    public static String getLabel(String key)
    {
        ResourceBundle resources = null;
        String s = null;
        try
        {
            Session session = Executions.getCurrent().getSession();            
            resources = ResourceBundle.getBundle(bundle,(Locale)session.getAttribute(Attributes.PREFERRED_LOCALE));
            s =  resources.getString(key);            
        }
        catch(Exception e)
        {
            resources = ResourceBundle.getBundle(bundle,Locale.getDefault());
            try
            {
                s =  resources.getString( key);
            }
            catch(Exception e1)
            {
                s =key;
            }
        }
        
        return s!= null?s:key;
    }

    public static String getLabel(String key,Locale locale)
    {
        ResourceBundle resources = null;
        String s = null;
        try
        {
            if(locale == null)
            {                
                resources = ResourceBundle.getBundle(bundle,locale);
                s =  resources.getString(key);
            }
            else
            {
                s = getLabel(key);
            }
        }
        catch(Exception e)
        {
            resources = ResourceBundle.getBundle(bundle,Locale.getDefault());
            try
            {
                s =  resources.getString(key);
            }
            catch(Exception e1)
            {
                s =key;
            }
        }

        return s!= null?s:key;
    }

    public static String getLabel(String key,String defaultValue)
    {        
        String s = null;
        try
        {
            s = getLabel(key);
        }
        catch(Exception e)
        {            
        }
        return (s!= null && !s.equals(key))?s:defaultValue;
    }

    public static String getLabel(String key,Object[] obj)
    {
        ResourceBundle resources = null;
        String s = null;
        try
        {
            Session session = Executions.getCurrent().getSession();
            resources = ResourceBundle.getBundle(bundle,(Locale)session.getAttribute(Attributes.PREFERRED_LOCALE));
            s =  resources.getString(key);
        }
        catch(Exception e)
        {
            resources = ResourceBundle.getBundle(bundle,Locale.getDefault());
            try
            {
                s =  resources.getString( key);
            }
            catch(Exception e1)
            {
                s =key;
            }
        }        
        
        return (s!=null && !s.equals(key))?MessageFormat.format(s, obj):key;
    }
}
