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
public class Report01Action
    extends DailyAdminReportAction
{
    protected static Log log = LogFactory.getLog(Report01Action.class);
    private static final long serialVersionUID = -2634117399142812092L;
    protected String titleStr;
    protected String materialType;

    public Report01Action()
    {
        filename = prop.getProperty("admin.rpt.rpt01");        
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getTitleStr() {
        return titleStr;
    }

    public void setTitleStr(String titleStr) {
        this.titleStr = titleStr;
    }

    @Override
    public String report()
    {
        log.info("---------------> report <---------------");        
        addParams("sqlMain", AdminReportCore.getProp().getProperty("admin.rpt.sql.rpt01."+getMaterialType()));

        if(getTitleStr() != null)
        {
            addParams("titleStr",getTitleStr());
        }
//        addParams("headerCSV", AdminReportCore.getProp().getProperty("admin.rpt.header.dr01"));
        return super.report();
    }
  

   

    

    
    
 
}
