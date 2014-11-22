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
public class Report05Action
    extends DailyAdminReportAction
{

    private String partId;
    private String processId;
    private String time0;
    private String time1;
    private String time2;
    private String time3;
    private String time4;
//    private String period0;
//    private String period1;
//    private String period2;
//    private String period3;
//    private String period4;
    private String machine0;
    private String machine1;
    private String machine2;
    private String machine3;
    private String machine4;
    
    protected static Log log = LogFactory.getLog(Report05Action.class);
    private static final long serialVersionUID = -2634117399142812092L;

    public Report05Action()
    {
        filename = prop.getProperty("admin.rpt.rpt05");
    }

    @Override
    public String report()
    {
        log.info("---------------> report <---------------");
//        createQuery(AdminReportCore.getProp().getProperty("admin.rpt.sql.rpt05"));
        addParams("sqlMain",  createQuery(AdminReportCore.getProp().getProperty("admin.rpt.sql.rpt05")));
        addParams("partId","'"+getPartId()+"'");

        addParams("processId","'"+getProcessId()+"'");
        addParams("imagepath","'D:\\\\sandbox\\\\process-part-img\\\\'");
        addParams("time0","'"+getTime0()+"'");
        addParams("time1","'"+getTime1()+"'");
        addParams("time2","'"+getTime2()+"'");
        addParams("time3","'"+getTime3()+"'");
        addParams("time4","'"+getTime4()+"'");
//        addParams("period0","'"+getPeriod0()+"'");
//        addParams("period1","'"+getPeriod1()+"'");
//        addParams("period2","'"+getPeriod2()+"'");
//        addParams("period3","'"+getPeriod3()+"'");
//        addParams("period4","'"+getPeriod4()+"'");
        addParams("machine0","'"+getMachine0()+"'");
        addParams("machine1","'"+getMachine1()+"'");
        addParams("machine2","'"+getMachine2()+"'");
        addParams("machine3","'"+getMachine3()+"'");
        addParams("machine4","'"+getMachine4()+"'");

//        addParams("headerCSV", AdminReportCore.getProp().getProperty("admin.rpt.header.dr01"));
        return super.report();
    }
    
    private String createQuery(String sqlQuery)
    {
        StringBuffer buf = new StringBuffer(1024);
        buf.append(sqlQuery);
//        if(getPartId() != null && !getPartId().isEmpty())
//        {
//            buf.append(" where data.part_id = $P!{partId}");
//        }
//
//        if(getProcessId() != null && !getProcessId().isEmpty())
//        {
//            buf.append((getPartId() != null && !getPartId().isEmpty())?" and ":" where ");
//            buf.append(" data.customer_id = $P!{processId}");
//        }
        
        return buf.toString();
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }   

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getMachine0() {
        return machine0;
    }

    public void setMachine0(String machine0) {
        this.machine0 = machine0;
    }

    public String getMachine1() {
        return machine1;
    }

    public void setMachine1(String machine1) {
        this.machine1 = machine1;
    }

    public String getMachine2() {
        return machine2;
    }

    public void setMachine2(String machine2) {
        this.machine2 = machine2;
    }

    public String getMachine3() {
        return machine3;
    }

    public void setMachine3(String machine3) {
        this.machine3 = machine3;
    }

    public String getMachine4() {
        return machine4;
    }

    public void setMachine4(String machine4) {
        this.machine4 = machine4;
    }

//    public String getPeriod0() {
//        return period0;
//    }
//
//    public void setPeriod0(String period0) {
//        this.period0 = period0;
//    }
//
//    public String getPeriod1() {
//        return period1;
//    }
//
//    public void setPeriod1(String period1) {
//        this.period1 = period1;
//    }
//
//    public String getPeriod2() {
//        return period2;
//    }
//
//    public void setPeriod2(String period2) {
//        this.period2 = period2;
//    }
//
//    public String getPeriod3() {
//        return period3;
//    }
//
//    public void setPeriod3(String period3) {
//        this.period3 = period3;
//    }
//
//    public String getPeriod4() {
//        return period4;
//    }
//
//    public void setPeriod4(String period4) {
//        this.period4 = period4;
//    }

    public String getTime0() {
        return time0;
    }

    public void setTime0(String time0) {
        this.time0 = time0;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getTime3() {
        return time3;
    }

    public void setTime3(String time3) {
        this.time3 = time3;
    }

    public String getTime4() {
        return time4;
    }

    public void setTime4(String time4) {
        this.time4 = time4;
    }
 
}
