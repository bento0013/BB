/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * DOCUMENT ME!
 *
 * @author <a href="mailto:chalermpongc@ar.co.th">Chalermpong Chaiyawan</a>
 * @version $Revision: 1.3 $
 */
public class JNDIUtils
{
    private static final Log log = LogFactory.getLog(JNDIUtils.class);

    private JNDIUtils()
    {
    }

    /**
     * DOCUMENT ME!
     *
     * @param <T> DOCUMENT ME!
     * @param name DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws NamingException DOCUMENT ME!
     * @throws NameNotFoundException DOCUMENT ME!
     */
    @SuppressWarnings("unchecked")
    public static <T> T locate(final String name)
        throws NamingException
    {
        Context context = new InitialContext();

        try
        {            
            return (T) context.lookup(name);
        }
        catch (final NameNotFoundException e)
        {
            String n = (name.startsWith("/") ? name
                                             : ("/" + name));

            try
            {
                return (T) ((Context) context.lookup("java:/comp/env")).lookup(n);
            }
            catch (final NameNotFoundException x)
            {
                try
                {
                    return (T) ((Context) context.lookup("java:/comp")).lookup(n);
                }
                catch (final NameNotFoundException y)
                {
                    return (T) ((Context) context.lookup("java:")).lookup(n);
                }
            }
        }
        finally
        {
            context.close();
        }
    }
}


// vim:nu:ts=4:sts=4:sw=4:ft=java:et:ai:sm:sta

