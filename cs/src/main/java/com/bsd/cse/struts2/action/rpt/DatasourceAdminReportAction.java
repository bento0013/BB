/*
 * $Id: DailyAdminReportAction.java,v 1.4 2011/08/25 10:27:26 ekalakt Exp $
 *
 * Copyright (C) 2007 Advanced Research Group Co., Ltd. (ARG).
 *
 * All Rights Researved.
 */


package com.bsd.cse.struts2.action.rpt;


import com.bsd.cse.app.admrpt.AdminReportCore;
import com.bsd.cse.app.admrpt.JasperReportType;
import com.bsd.cse.model.security.Group;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.SystemUtils;
import org.zkoss.web.Attributes;

/**
 * DOCUMENT ME!
 *
 * @author <a href="mailto:chalermpongc@ar.co.th">Chalermpong Chaiyawan</a>
 * @version $Revision: 1.4 $
 */
public class DatasourceAdminReportAction
    extends AdminReportAction
{
    protected static Log log = LogFactory.getLog(DatasourceAdminReportAction.class);
    private static final long serialVersionUID = 7156065753887830478L;


    protected String startDate;
    protected String endDate;
    protected String startDateStr;
    protected String endDateStr;
    
    protected String requestBy;

    protected List<Object> datasources = new ArrayList<Object>();

    //~ Methods ································································

    public String report()
    {
        String result= null;
        try
        {
            if(log.isDebugEnabled())
            {
                log.debug("startDate = "+getStartDate());
                log.debug("endDate = "+getEndDate());
                log.debug("getStartDateStr = "+getStartDateStr());
                log.debug("getEndDateStr = "+getEndDateStr());                          
                log.debug("requestBy = "+requestBy);
            }

            if(request.getHeader("User-Agent").toLowerCase().indexOf("firefox".toLowerCase()) != -1)
            {
                isFirefoxBrowser = true;
            }

            createParams();
            AdminReportCore core = new AdminReportCore();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.ENGLISH);
            result = sdf.format(Calendar.getInstance().getTime());
            log.info("file name = "+result);
            result = result + ".pdf";
            log.info("file path = "+result);
            log.info("datasources = "+datasources);
            core.generateReportDatasourceFile(getParams(), JasperReportType.PDF, filename,new File(SystemUtils.getJavaIoTmpDir(),result),isFirefoxBrowser,new JRBeanCollectionDataSource(datasources),subReportMap);
            reportStream = new FileInputStream(new File(SystemUtils.getJavaIoTmpDir(),result));
            return JasperReportType.PDF;

        }
        catch(Exception e)
        {
            log.error(e.getMessage(),e);
            return REPORTERROR;
        }

    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    

    

    




   @Override
    protected void createParams() throws Exception
    {
        addParams("requestBy", getRequestBy());

        if(getStartDate() != null)
        {
            addParams("startDateStr",getStartDateStr());
        }

        if(getEndDate() != null)
        {
            addParams("endDateStr",getEndDateStr());
        }

        if(getStartDate() != null)
        {
            addParams("startDate","'"+getStartDate()+"'");
        }

        if(getEndDate() != null)
        {
            addParams("endDate","'"+getEndDate()+"'");
        }

        

        addParams("requestDate",new SimpleDateFormat(FULL_FORMAT_DATE,Locale.US).format(Calendar.getInstance().getTime()));
        addParams("CS_IMAGE","com/bsd/cse/jreport/img/cs-engineering-img.png");
    }

    
}
