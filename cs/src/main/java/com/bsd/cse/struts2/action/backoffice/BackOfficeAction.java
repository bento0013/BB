/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.struts2.action.backoffice;

import com.bsd.cse.struts2.action.security.*;
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
public class BackOfficeAction extends BaseAction{
    private static final long serialVersionUID = -1652350463559843989L;

    public String department()
    {
        LOG.info("Department SUCCESS");
        return SUCCESS;
    }

    public String part()
    {

        LOG.info("Roles SUCCESS");
        return SUCCESS;
    }

    public String ngreason()
    {

        LOG.info("Roles SUCCESS");
        return SUCCESS;
    }

    public String partProcess()
    {

        LOG.info("System SUCCESS");
        return SUCCESS;
    }

    public String machine()
    {

        LOG.info("User SUCCESS");
        return SUCCESS;
    }

    public String machineModel()
    {
        LOG.info("User SUCCESS");
        return SUCCESS;
    }

    public String machineModelType()
    {
        LOG.info("User SUCCESS");
        return SUCCESS;
    }

    public String measurementTool()
    {

        LOG.info("User SUCCESS");
        return SUCCESS;
    }

    public String measurementToolModel()
    {

        LOG.info("measurementToolModel SUCCESS");
        return SUCCESS;
    }

    public String measurementToolType()
    {

        LOG.info("User SUCCESS");
        return SUCCESS;
    }

    public String rawMaterial()
    {

        LOG.info("User SUCCESS");
        return SUCCESS;
    }

    public String rawMaterialType()
    {

        LOG.info("User SUCCESS");
        return SUCCESS;
    }

    public String semiMaterial()
    {

        LOG.info("User SUCCESS");
        return SUCCESS;
    }

    public String semiMaterialType()
    {

        LOG.info("User SUCCESS");
        return SUCCESS;
    }

    public String checkpoint()
    {

        LOG.info("User SUCCESS");
        return SUCCESS;
    }

    public String customer()
    {

        LOG.info("User SUCCESS");
        return SUCCESS;
    }


}
