/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.struts2.interceptor;

import com.bsd.cse.app.security.UserCore;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author bento
 */
public class DisabledUserInterceptor extends AbstractInterceptor{
    private static final long serialVersionUID = 6931056415569243416L;
    private static Log LOG = LogFactory.getLog(DisabledUserInterceptor.class);
    /**
     * DOCUMENT ME!
     */
    public static final String ACCESS_DISABLED = "disabled";

    //~ Instance fields ························································

    public DisabledUserInterceptor()
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug(String.format("%1$s created.",
                                    DisabledUserInterceptor.class.getSimpleName()));
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
                                    DisabledUserInterceptor.class.getSimpleName()));
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
                                    DisabledUserInterceptor.class.getSimpleName()));
        }
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        String result = null;        
        if (LOG.isDebugEnabled())
        {
            LOG.debug(String.format("Executing %1$s::intercept()",
                                    DisabledUserInterceptor.class.getSimpleName()));
        }

        HttpServletRequest request = ServletActionContext.getRequest();
        String login_id = request.getUserPrincipal().getName();
            
        HttpSession session = request.getSession();
        if(login_id != null)
        {
            if(session.getAttribute("disabled") == null)
            {
                session.setAttribute("disabled", UserCore.getUser(login_id).getDisabled());
            }
            
            result = ((Boolean)session.getAttribute("disabled"))?ACCESS_DISABLED:invocation.invoke();
        }
        else
        {
            result = invocation.invoke();
        }


        return result;
    }
    



}
