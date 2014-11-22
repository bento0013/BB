/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.struts2.interceptor;

import com.bsd.cse.app.security.TransactionLogs;
import com.bsd.cse.app.security.UserCore;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author bento
 */
public class MenuLogsInterceptor extends AbstractInterceptor{
    private static final long serialVersionUID = 6931056415569243416L;
    private static Log LOG = LogFactory.getLog(MenuLogsInterceptor.class);
    /**
     * DOCUMENT ME!
     */
    public static final String ACCESS_FORBIDDEN = "no-permission";

    //~ Instance fields ························································

    private List<String> requiredPermissions = Collections.EMPTY_LIST;

    public MenuLogsInterceptor()
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug(String.format("%1$s created.",
                                    MenuLogsInterceptor.class.getSimpleName()));
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
                                    MenuLogsInterceptor.class.getSimpleName()));
        }
    }

    /**
     * DOCUMENT ME!
     */
    public void destroy()
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug(String.format("%1$s destroyed.",
                                    MenuLogsInterceptor.class.getSimpleName()));
        }
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        if (LOG.isDebugEnabled())
        {
            LOG.debug(String.format("Executing %1$s::intercept()",
                                    MenuLogsInterceptor.class.getSimpleName()));
        }

        HttpServletRequest request = ServletActionContext.getRequest();
        String login_id = request.getUserPrincipal().getName();
       

        for(String functionid : requiredPermissions)
        {
            HttpSession session = request.getSession();
            Long userId = null;
            if(session.getAttribute(login_id) == null)
            {
                userId = UserCore.getUser(login_id).getId();
                session.setAttribute(login_id,userId);
            }
            else
            {
                userId = (Long)session.getAttribute(login_id);
            }
            
            TransactionLogs.logs(new Long(functionid), userId);
            
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
