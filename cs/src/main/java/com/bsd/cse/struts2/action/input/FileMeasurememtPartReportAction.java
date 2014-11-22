/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.struts2.action.input;

import com.bsd.cse.app.admrpt.JasperReportType;
import com.bsd.cse.struts2.action.BaseAction;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author bento
 */
public class FileMeasurememtPartReportAction extends BaseAction{
    private static Log log = LogFactory.getLog(FileMeasurememtPartReportAction.class);
    private static final long serialVersionUID = -434189267744817955L;
    private String filePath;
    private String filename;
    private InputStream reportStream;
    public String report()
    {
        String result= null;
        try
        {
            if(log.isDebugEnabled())
            {
                log.debug("filePath = "+getFilePath());
                log.debug("filename = "+getFilename());
            }

            File file =  new File(getFilePath());

            //response.setHeader("content-disposition", "attachment; filename=" + getFilename());            
            reportStream = new FileInputStream(file);
            return JasperReportType.PDF;

        }
        catch(Exception e)
        {
            log.error(e.getMessage(),e);
            return "error";
        }

    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public InputStream getReportStream() {
        return reportStream;
    }

    public void setReportStream(InputStream reportStream) {
        this.reportStream = reportStream;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    

    
}
