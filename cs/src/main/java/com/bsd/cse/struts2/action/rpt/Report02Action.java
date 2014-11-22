/*
 * $Id: Dr01Action.java,v 1.1 2011/01/31 12:52:37 thanasit Exp $
 *
 * Copyright (C) 2007 Advanced Research Group Co., Ltd. (ARG).
 *
 * All Rights Researved.
 */


package com.bsd.cse.struts2.action.rpt;

import com.bsd.cse.app.admrpt.AdminReportCore;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * DOCUMENT ME!
 *
 * @author <a href="mailto:chalermpongc@ar.co.th">Chalermpong Chaiyawan</a>
 * @version $Revision: 1.1 $
 */
public class Report02Action
    extends DailyAdminReportAction
{

    private String partId;
    private String customerId;
    
    protected static Log log = LogFactory.getLog(Report02Action.class);
    private static final long serialVersionUID = -2634117399142812092L;

    public Report02Action()
    {
        filename = prop.getProperty("admin.rpt.rpt02");
    }

    @Override
    public String report()
    {
        log.info("---------------> report <---------------");
        createQuery(AdminReportCore.getProp().getProperty("admin.rpt.sql.rpt02"));
        addParams("sqlMain",  createQuery(AdminReportCore.getProp().getProperty("admin.rpt.sql.rpt02")));
        addParams("partId","'"+getPartId()+"'");
        addParams("customerId","'"+getCustomerId()+"'");
//        addParams("headerCSV", AdminReportCore.getProp().getProperty("admin.rpt.header.dr01"));
        return super.report();
    }
    
    private String createQuery(String sqlQuery)
    {
        StringBuffer buf = new StringBuffer(1024);
        buf.append(sqlQuery);
        if(getPartId() != null && !getPartId().isEmpty())
        {
            buf.append(" where data.part_id = $P!{partId}");
        }

        if(getCustomerId() != null && !getCustomerId().isEmpty())
        {
            buf.append((getPartId() != null && !getPartId().isEmpty())?" and ":" where ");
            buf.append(" data.customer_id = $P!{customerId}");
        }
        
        return buf.toString();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    


  

   

    

    
    
 
}
