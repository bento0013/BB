/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.struts2.action.input;

import com.bsd.cse.struts2.action.backoffice.*;
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
public class InputAction extends BaseAction{
    private static final long serialVersionUID = -1652350463559843989L;

    public String inputPart()
    {
        return SUCCESS;
    }

    public String inputTime()
    {
        return SUCCESS;
    }

    public String inputMeasurement()
    {
        return SUCCESS;
    }


}
