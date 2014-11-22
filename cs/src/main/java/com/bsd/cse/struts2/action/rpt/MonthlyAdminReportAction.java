/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import net.sf.jasperreports.engine.JRParameter;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.web.Attributes;

/**
 *
 * @author thanasith
 */
public class MonthlyAdminReportAction extends AdminReportAction{
    protected static Log log = LogFactory.getLog(MonthlyAdminReportAction.class);

    protected String startDate;
    protected String endDate;
    protected String periodStr;
    protected String requestBy;
    
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
                log.debug("periodStr = "+getPeriodStr());

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
            core.generateReportFile(getParams(), JasperReportType.PDF, filename,new File(SystemUtils.getJavaIoTmpDir(),result),isFirefoxBrowser,subReportMap);
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

    public String getPeriodStr() {
        return periodStr;
    }

    public void setPeriodStr(String periodStr) {
        this.periodStr = periodStr;
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

    

    @Override
    protected void createParams() throws Exception
    {        
        addParams("requestBy", getRequestBy());

        if(getStartDate() != null)
        {
            addParams("startDate","'"+getStartDate()+"'");
        }

        if(getEndDate() != null)
        {
            addParams("endDate","'"+getEndDate()+"'");
        }

        if(getPeriodStr() != null)
        {
            addParams("period",getPeriodStr());
        }

        addParams("requestDate",new SimpleDateFormat(FULL_FORMAT_DATE,Locale.US).format(Calendar.getInstance().getTime()));
        addParams("CS_IMAGE","com/bsd/cse/jreport/img/cs-engineering-img.png");
    }
}
