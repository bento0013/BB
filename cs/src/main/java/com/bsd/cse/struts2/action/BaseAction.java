/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.struts2.action;
/*
 * $Id: BaseAction.java,v 1.1 2010/05/07 11:23:39 thanasit Exp $
 *
 * Copyright (R) 2008 Advanced Research Group Co., Ltd.
 * All Rights Reserved.
 */


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
  */
public abstract class BaseAction
        extends ActionSupport
        implements ServletRequestAware, ServletResponseAware,
                   ApplicationAware, ParameterAware, RequestAware,
                   SessionAware
{
    //~ Instance fields ························································

    /**
     * DOCUMENT ME!
     */

    private static final long serialVersionUID = -5422239994150615930L;

    protected static String LOGIN = "login";
    protected static String NO_PERMISSION = "no-permission";

    protected HttpServletRequest request;

    /**
     * DOCUMENT ME!
     */
    protected HttpServletResponse response;

    /**
     * DOCUMENT ME!
     */
    protected Map sessionAttributes;

    /**
     * DOCUMENT ME!
     */
    protected Map applicationAttributes;

    /**
     * DOCUMENT ME!
     */
    protected Map requestParameters;

    /**
     * DOCUMENT ME!
     */
    protected Map requestAttributes;

    //~ Methods ································································

    /**
     * DOCUMENT ME!
     *
     * @param params DOCUMENT ME!
     */
    public void setRequest(final Map params)
    {
        this.requestAttributes = params;
    }

    /**
     * DOCUMENT ME!
     *
     * @param request DOCUMENT ME!
     */
    public void setServletRequest(HttpServletRequest request)
    {
        this.request = request;
    }

    /**
     * DOCUMENT ME!
     *
     * @param response DOCUMENT ME!
     */
    public void setServletResponse(HttpServletResponse response)
    {
        this.response = response;
    }

    /**
     * DOCUMENT ME!
     *
     * @param session DOCUMENT ME!
     */
    public void setSession(final Map session)
    {
        this.sessionAttributes = session;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public ServletContext getServletContext()
    {
        Map map = ActionContext.getContext().getContextMap();

        return (ServletContext) map.get(StrutsStatics.SERVLET_CONTEXT);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws Exception DOCUMENT ME!
     */
    public String index()
            throws Exception
    {
        return SUCCESS;
    }

    public String ping()
        throws Exception
    {
        return NONE;
    }

    /**
     * DOCUMENT ME!
     *
     * @param application DOCUMENT ME!
     */
    public void setApplication(final Map application)
    {
        this.applicationAttributes = application;
    }

    /**
     * DOCUMENT ME!
     *
     * @param params DOCUMENT ME!
     */
    public void setParameters(final Map params)
    {
        this.requestParameters = params;
    }

    public String getLoginName()
    {
       return this.request.getUserPrincipal().getName();
    }
}


