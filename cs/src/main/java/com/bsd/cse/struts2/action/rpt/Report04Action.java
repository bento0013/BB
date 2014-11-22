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
public class Report04Action
    extends DailyAdminReportAction
{

    private String partId;
    private String processId;
    private String period1;
    private String period2;
    private String machineName;
    private String machine0;
    private String machine1;
    private String machine2;
    private String machine3;
    private String machine4;
    private String machine5;
    private String machine6;
    private String machine7;
    private String machine8;
    private String machine9;
    private String machine10;
    private String machine11;
    private String machine12;
    private String machine13;
    private String machine14;
    private String machine15;
    private String machine16;
    private String machine17;
    private String machine18;
    private String machine19;
    private String time0;
    private String time1;
    private String time2;
    private String time3;
    private String time4;
    private String time5;
    private String time6;
    private String time7;
    private String time8;
    private String time9;



    
    protected static Log log = LogFactory.getLog(Report04Action.class);
    private static final long serialVersionUID = -2634117399142812092L;

    public Report04Action()
    {
        filename = prop.getProperty("admin.rpt.rpt04");
    }

    @Override
    public String report()
    {
        log.info("---------------> report <---------------");        
        addParams("sqlMain",  createQuery(AdminReportCore.getProp().getProperty("admin.rpt.sql.rpt04")));
        addParams("partId","'"+getPartId()+"'");
        addParams("processId","'"+getProcessId()+"'");
        addParams("machineName",getMachineName());
        addParams("machine0","'"+getMachine0()+"'");
        addParams("machine1","'"+getMachine1()+"'");
        addParams("machine2","'"+getMachine2()+"'");
        addParams("machine3","'"+getMachine3()+"'");
        addParams("machine4","'"+getMachine4()+"'");
        addParams("machine5","'"+getMachine5()+"'");
        addParams("machine6","'"+getMachine6()+"'");
        addParams("machine7","'"+getMachine7()+"'");
        addParams("machine8","'"+getMachine8()+"'");
        addParams("machine9","'"+getMachine9()+"'");
        addParams("machine10","'"+getMachine10()+"'");
        addParams("machine11","'"+getMachine11()+"'");
        addParams("machine12","'"+getMachine12()+"'");
        addParams("machine13","'"+getMachine13()+"'");
        addParams("machine14","'"+getMachine14()+"'");
        addParams("machine15","'"+getMachine15()+"'");
        addParams("machine16","'"+getMachine16()+"'");
        addParams("machine17","'"+getMachine17()+"'");
        addParams("machine18","'"+getMachine18()+"'");
        addParams("machine19","'"+getMachine19()+"'");
        addParams("time0",getTime0());
        addParams("time1",getTime1());
        addParams("time2",getTime2());
        addParams("time3",getTime3());
        addParams("time4",getTime4());
        addParams("time5",getTime5());
        addParams("time6",getTime6());
        addParams("time7",getTime7());
        addParams("time8",getTime8());
        addParams("time9",getTime9());
        

//        if(getPeriod1() != null && !getPeriod1().trim().isEmpty())
//        {
//            addParams("period1",getPeriod1());
//        }
//
//        if(getPeriod2() != null && !getPeriod2().trim().isEmpty())
//        {
//            addParams("period2",getPeriod2());
//        }
        
        
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

    public String getMachine10() {
        return machine10;
    }

    public void setMachine10(String machine10) {
        this.machine10 = machine10;
    }

    public String getMachine11() {
        return machine11;
    }

    public void setMachine11(String machine11) {
        this.machine11 = machine11;
    }

    public String getMachine12() {
        return machine12;
    }

    public void setMachine12(String machine12) {
        this.machine12 = machine12;
    }

    public String getMachine13() {
        return machine13;
    }

    public void setMachine13(String machine13) {
        this.machine13 = machine13;
    }

    public String getMachine14() {
        return machine14;
    }

    public void setMachine14(String machine14) {
        this.machine14 = machine14;
    }

    public String getMachine15() {
        return machine15;
    }

    public void setMachine15(String machine15) {
        this.machine15 = machine15;
    }

    public String getMachine16() {
        return machine16;
    }

    public void setMachine16(String machine16) {
        this.machine16 = machine16;
    }

    public String getMachine17() {
        return machine17;
    }

    public void setMachine17(String machine17) {
        this.machine17 = machine17;
    }

    public String getMachine18() {
        return machine18;
    }

    public void setMachine18(String machine18) {
        this.machine18 = machine18;
    }

    public String getMachine19() {
        return machine19;
    }

    public void setMachine19(String machine19) {
        this.machine19 = machine19;
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

    public String getMachine5() {
        return machine5;
    }

    public void setMachine5(String machine5) {
        this.machine5 = machine5;
    }

    public String getMachine6() {
        return machine6;
    }

    public void setMachine6(String machine6) {
        this.machine6 = machine6;
    }

    public String getMachine7() {
        return machine7;
    }

    public void setMachine7(String machine7) {
        this.machine7 = machine7;
    }

    public String getMachine8() {
        return machine8;
    }

    public void setMachine8(String machine8) {
        this.machine8 = machine8;
    }

    public String getMachine9() {
        return machine9;
    }

    public void setMachine9(String machine9) {
        this.machine9 = machine9;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

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

    public String getTime5() {
        return time5;
    }

    public void setTime5(String time5) {
        this.time5 = time5;
    }

    public String getTime6() {
        return time6;
    }

    public void setTime6(String time6) {
        this.time6 = time6;
    }

    public String getTime7() {
        return time7;
    }

    public void setTime7(String time7) {
        this.time7 = time7;
    }

    public String getTime8() {
        return time8;
    }

    public void setTime8(String time8) {
        this.time8 = time8;
    }

    public String getTime9() {
        return time9;
    }

    public void setTime9(String time9) {
        this.time9 = time9;
    }

    public String getTime4() {
        return time4;
    }

    public void setTime4(String time4) {
        this.time4 = time4;
    }
    
}
