/*
 * $Id: Dr01Action.java,v 1.1 2011/01/31 12:52:37 thanasit Exp $
 *
 * Copyright (C) 2007 Advanced Research Group Co., Ltd. (ARG).
 *
 * All Rights Researved.
 */


package com.bsd.cse.struts2.action.rpt;

import com.bsd.cse.app.admrpt.AdminReportCore;
import com.bsd.cse.app.rpt.ControlChartProcessor;
import com.bsd.cse.app.rpt.OEEProcessor;
import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.input.ControlChartGroupingData;
import com.bsd.cse.model.input.MeasurementPart;
import com.bsd.cse.model.report.ControlData;
import com.bsd.cse.model.report.ControlSerieData;
import com.bsd.cse.model.report.OEEData;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;

/**
 * DOCUMENT ME!
 *
 * @author <a href="mailto:chalermpongc@ar.co.th">Chalermpong Chaiyawan</a>
 * @version $Revision: 1.1 $
 */
public class Report06Action
    extends DatasourceAdminReportAction
{


    private String machineId;
    private String teamId;
    private String employeeId;
    
    protected static Log log = LogFactory.getLog(Report06Action.class);
    private static final long serialVersionUID = -2634117399142812092L;

    public Report06Action()
    {
        filename = prop.getProperty("admin.rpt.rpt06");
    }

    @Override
    public String report()
    {
        log.info("---------------> report <---------------");
//        createQuery(AdminReportCore.getProp().getProperty("admin.rpt.sql.rpt03"));
//        addParams("sqlMain",  createQuery(AdminReportCore.getProp().getProperty("admin.rpt.sql.rpt03")));
        addParams("machineId","'"+getMachineId()+"'");
        addParams("teamId","'"+getTeamId()+"'");
        subReportMap.put("subReport1",prop.getProperty("admin.rpt.rpt06.subreport01"));
        subReportMap.put("subReport2",prop.getProperty("admin.rpt.rpt06.subreport02"));
        subReportMap.put("subReport3",prop.getProperty("admin.rpt.rpt06.subreport03"));
        subReportMap.put("subReport4",prop.getProperty("admin.rpt.rpt06.subreport04"));
        subReportMap.put("subReport5",prop.getProperty("admin.rpt.rpt06.subreport05"));
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date startTime = format.parse(getStartDate());
//            Date endTime = format.parse(getEndDate());
            OEEProcessor processor = new OEEProcessor();
//            LOG.info("partId = "+partId);
            List<OEEData> oeeDatas = processor.processor(startTime, null, Long.valueOf(machineId),null,Long.valueOf(teamId));
            for(OEEData oeeData : oeeDatas)
            {
                datasources.add(oeeData);
            }
        } catch (Exception ex) {
            Logger.getLogger(Report06Action.class.getName()).log(Level.SEVERE, null, ex);
        }
//        addParams("headerCSV", AdminReportCore.getProp().getProperty("admin.rpt.header.dr01"));
        return super.report();
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
       

       
    

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }        
 
}
