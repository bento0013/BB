/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.struts2.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author bento
 */
public class AuthorizationInterceptor extends AbstractInterceptor{
    private static final long serialVersionUID = 6931056415569243416L;
    private static Log LOG = LogFactory.getLog(AuthorizationInterceptor.class);
    /**
     * DOCUMENT ME!
     */
    public static final String ACCESS_FORBIDDEN = "no-permission";

    //~ Instance fields ························································

    private List<String> requiredPermissions = Collections.EMPTY_LIST;

    public AuthorizationInterceptor()
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug(String.format("%1$s created.",
                                    AuthorizationInterceptor.class.getSimpleName()));
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
                                    AuthorizationInterceptor.class.getSimpleName()));
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
                                    AuthorizationInterceptor.class.getSimpleName()));
        }
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        if (LOG.isDebugEnabled())
        {
            LOG.debug(String.format("Executing %1$s::intercept()",
                                    AuthorizationInterceptor.class.getSimpleName()));
        }

//        HttpUserSession us = new HttpUserSession();
        HttpServletRequest request = ServletActionContext.getRequest();
        String namespace = invocation.getProxy().getNamespace();
        String action = invocation.getProxy().getActionName();
        String login_id = request.getUserPrincipal().getName();
        String result = null;

        //if (us.isValid())
        if (login_id != null && !login_id.isEmpty())
        {
            if (LOG.isDebugEnabled())
            {
                LOG.debug(String.format("Checking required permissions for user '%1$s'...",
                                        login_id));
            }

            if (isAllowed(invocation))
            {
                LOG.debug(String.format("User '%1$s' is allowed to perform action %2$s/%3$s.html.",
                                        login_id, namespace, action));

                result = invocation.invoke();
            }
            else
            {
                LOG.warn(String.format("User '%1$s' has no sufficient permissions to perform action %2$s/%3$s, redirecting to target '%4$s'.",
                                       login_id, namespace, action,
                                       (result = handleRejection(invocation))));
            }
        }
        else
        {
            LOG.debug(String.format("Guest access is denied, return result is %1$s.",
                                    (result = handleRejection(invocation))));
        }

        return result;
    }

    protected boolean isAllowed(final ActionInvocation invocation)
    {
        //UserSession us = new HttpUserSession();
        String namespace = invocation.getProxy().getNamespace();
        String action = invocation.getProxy().getActionName();
        if (requiredPermissions == null || requiredPermissions.isEmpty())
        {            
            if (LOG.isDebugEnabled())
            {
                LOG.debug(String.format("No permissions are required to perform action %1$s/%2$s.html",
                                        namespace, action));
            }
        }
        else
        {
            HttpServletRequest request = ServletActionContext.getRequest();

            for (final String perm : requiredPermissions)
            {
                LOG.info("request.isUserInRole("+perm+") = "+(request.isUserInRole(perm)));

                if (request.isUserInRole(perm))
                {
                    return true;
                }
            }

            return false;
        }

        return true;
    }

    protected String handleRejection(final ActionInvocation invocation)
        throws Exception
    {
        return ACCESS_FORBIDDEN;
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
