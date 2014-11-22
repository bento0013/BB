/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.struts2.action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author bento
 */
public class MainAction extends BaseAction{
    private static final long serialVersionUID = -6560112522316091294L;
    private static Log log = LogFactory.getLog(MainAction.class);    
    

    public String prelogin() throws IOException
    {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> prelogin");
        if(request.getSession().getAttribute("prelogin") != null)
        {
        
            return request.getUserPrincipal() == null?LOGIN:SUCCESS;
        }
        else
        {
            request.getSession().setAttribute("prelogin",Boolean.TRUE);
            return SUCCESS;
        }
        
    }

    public String permission()
    {
        return NO_PERMISSION;
    }

    public String home()
    {
        return SUCCESS;
    }

    public String logout()
    {

        HttpSession session = request.getSession();
        session.invalidate();
        return SUCCESS;
    }

    public String disabled()
    {

        HttpSession session = request.getSession();
        if(session.getAttribute("disabled") != null)
        {
            request.setAttribute("disabled", session.getAttribute("disabled"));       
            session.invalidate();
            return SUCCESS;
        }
        
        return LOGIN;
        
        
    }
}
