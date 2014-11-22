/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.struts2.interceptor;

import com.bsd.cse.app.security.UserCore;
import com.bsd.cse.common.catalog.LanguageCatalog;
import com.bsd.cse.model.security.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.zkoss.web.Attributes;

/**
 *
 * @author bento
 */
public class LocaleInterceptor extends AbstractInterceptor{    
    private static Log LOG = LogFactory.getLog(LocaleInterceptor.class);
    /**
     * DOCUMENT ME!
     */
    public static final String ACCESS_FORBIDDEN = "no-permission";

    //~ Instance fields ························································

    private List<String> requiredPermissions = Collections.EMPTY_LIST;

    public LocaleInterceptor()
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug(String.format("%1$s created.",
                                    LocaleInterceptor.class.getSimpleName()));
        }
    }

    //~ Methods ································································

    /**
     * DOCUMENT ME!
     */
    public void init()
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug(String.format("%1$s initialized.",
                                    LocaleInterceptor.class.getSimpleName()));
        }
    }

    /**
     * DOCUMENT ME!
     */
    public void destroy()
    {
        if (LOG.isDebugEnabled())
        {
            LOG.info(String.format("%1$s destroyed.",
                                    LocaleInterceptor.class.getSimpleName()));
        }
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        if (LOG.isDebugEnabled())
        {
            LOG.debug(String.format("Executing %1$s::intercept()",
                                    LocaleInterceptor.class.getSimpleName()));
        }

        HttpServletRequest request = ServletActionContext.getRequest();
        String login_id = request.getUserPrincipal().getName();             
        User user = UserCore.getUser(login_id);
        SessionMap session = (SessionMap) ActionContext.getContext().getSession();
        String locale = user.getLanguage();
        Locale locale_session = (Locale)session.get(Attributes.PREFERRED_LOCALE);

        System.out.println("Class="+LOG.getClass());
        if(locale_session == null )
        {
             if (LOG.isDebugEnabled())
             {
                LOG.debug("User have private locale. have locale = "+locale_session);
             }
             
            if(locale != null)
            {                
                locale_session = (Locale)session.put(Attributes.PREFERRED_LOCALE, LanguageCatalog.getLocale(locale));
            }
            else
            {
                locale_session = (Locale)session.put(Attributes.PREFERRED_LOCALE, Locale.US);
            }
         

        }
        else
        {
            session.put(Attributes.PREFERRED_LOCALE, locale_session);
        }
        return invocation.invoke();
    }
    

    public void setRequiredPermissions(final String perms)
    {
        if (LOG.isInfoEnabled())
        {
            LOG.info(String.format("Setting required permissions '%1$s' for the action",
                                    perms));
        }

        String[] temp = perms.split(",");
        if (perms != null && perms.length() > 0 && temp != null && temp.length > 0)
        {
            requiredPermissions = new ArrayList<String>();

            for (final String s : temp)
            {
                requiredPermissions.add(s);

                if (LOG.isDebugEnabled())
                {
                    LOG.debug(" o " + s);
                }
            }
        }
    }

}
