/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk;

import com.bsd.cse.model.AbstractPojo;
import com.bsd.cse.model.security.Function;
import com.bsd.cse.security.Functions;
import com.bsd.cse.security.UserSession;
import com.bsd.cse.security.Users;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;

/**
 *
 * @author thanasith
 */
public class HttpUserSession extends AbstractPojo implements UserSession
{

    private static final Log LOG = LogFactory.getLog(HttpUserSession.class);

    /**
     * DOCUMENT ME!
     */
    public final static String USER_OBJ = "userObj";
    private static final long serialVersionUID = 279622319501738110L;

    //~ Instance fields ························································

    private final Object LOCK = new Object();
    private HttpSession session;
    private Map<String, Object> creds;
    private HttpServletRequest request;

    //~ Constructors ···························································

    /**
     * Creates a new HttpUserSession object.
     */
    public HttpUserSession()
    {
        super();
        request = (HttpServletRequest)Executions.getCurrent().getNativeRequest();
        session = request.getSession();
        if(!isValid())
        {
            Enumeration enums = session.getAttributeNames();
            while(enums.hasMoreElements())
            {
                Object obj = enums.nextElement();
                if(obj instanceof String)
                {
                    session.removeAttribute((String)obj);
                }
            }

//            LOG.warn("session invalidate");
        }

        if (null == request)
        {
            LOG.warn("No HttpServletRequest found using Struts' ServletActionContext.getRequest(), please verify that the defaultStack has been properly set.");
        }
    }

    /**
     * Creates a new HttpUserSession object.
     *
     * @param request DOCUMENT ME!
     */
    public HttpUserSession(final HttpServletRequest request)
    {
        super();
        this.request = request;
    }

    //~ Methods ································································

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    public boolean isValid()
    {
        return StringUtils.isNotBlank(getLoginId()); //&&  (getLoginId().equals((String)this.getUserInformation().get(Users.LOGINID)));
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    public String getLoginId()
    {       
        
        return request.getUserPrincipal().getName();               
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Map<String, Object> credentials()
    {
        if (null == creds)
        {
            synchronized (LOCK)
            {
                if (null == creds)
                {
                    creds = acquireCredentials();
                }
            }
        }

        return creds;
    }

    private List<Long> acquireFunctionIDs()
    {
        if (null == this.request)
        {
            LOG.warn("Could not retrieve list of function IDs, no reference to HttpServletRequest object.");

            return Collections.EMPTY_LIST;
        }

        List<Long> list = new ArrayList<Long>();

        for (Function function : Functions.list())
        {
            if (request.isUserInRole(function.getId().toString()))
            {
                list.add(function.getId());
            }
        }

        if (LOG.isDebugEnabled())
        {
            LOG.debug(String.format("::acquireFunctionIDs(): total %1$d function(s) found as permissions for user '%2$s'.",
                                    list.size(), request.getUserPrincipal()));
        }

        return list;
    }

    private Map<String, Object> acquireCredentials()
    {
        Map<String, Object> cre = null;
        String loginid = request.getUserPrincipal() == null?"SYSTEM":request.getUserPrincipal()
                                .getName();

        try
        {
            cre = Users.credentials(loginid);
            cre.put(UserSession.PERMISSIONS_KEY, cre.get(Users.PERMISSIONS));
        }
        catch (final Exception e)
        {
            LOG.error(e.getMessage(), e);

            if (LOG.isTraceEnabled())
            {
                e.printStackTrace();
            }

            cre = java.util.Collections.EMPTY_MAP;
        }

        return cre;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Map<String, Object> getUserInformation()
    {
        LOG.debug("## Start User Infomation ##");

        Map<String, Object> cre = (Map<String, Object>) session.getAttribute(USER_OBJ);
       

        if (null == cre )
        {
            LOG.debug("Create User Obj : Type Map");
            cre = credentials();
            session.setAttribute(USER_OBJ, cre);
        }
         

        return cre;
    }

}
