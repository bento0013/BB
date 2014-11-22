/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.struts2.action.security;

import com.bsd.cse.common.HibernateUtil;
import com.bsd.cse.model.security.Role;
import com.bsd.cse.struts2.action.BaseAction;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;

/**
 *
 * @author bento
 */
public class SecurityAction extends BaseAction{
    private static final long serialVersionUID = -1652350463559843989L;

    public String role()
    {
        LOG.info("Roles SUCCESS");        
        return SUCCESS;
    }

    public String userGroup()
    {

        LOG.info("Roles SUCCESS");
        return SUCCESS;
    }

    public String systemSetting()
    {

        LOG.info("System SUCCESS");
        return SUCCESS;
    }

    public String userSetting()
    {

        LOG.info("User SUCCESS");
        return SUCCESS;
    }

}
