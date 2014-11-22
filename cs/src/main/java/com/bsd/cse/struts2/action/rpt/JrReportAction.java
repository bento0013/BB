/*
 * $Id: JrReportAction.java,v 1.1 2011/01/31 12:52:37 thanasit Exp $
 *
 * Copyright (C) 2007 Advanced Research Group Co., Ltd. (ARG).
 *
 * All Rights Researved.
 */


package com.bsd.cse.struts2.action.rpt;

import com.bsd.cse.model.security.Group;
import com.bsd.cse.struts2.action.BaseAction;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * DOCUMENT ME!
 *
 * @author Fuangchai (Big)
 * @version $Revision: 1.1 $
 */
public abstract class JrReportAction
    extends BaseAction
{
    protected static Properties prop;
    private static final long serialVersionUID = 266801156668152829L;
    protected Boolean isFirefoxBrowser = false;
    //~ Static fields/initializers ·············································

    protected static Log LOG = LogFactory.getLog(JrReportAction.class);       



    protected InputStream reportStream;

    protected String filename;

    protected Map subReportMap = new HashMap();
    
    protected String REPORTERROR = "reporterror";

    protected HashMap<String,Object> params = new HashMap();

    protected static String PROPERTIES_FILE = "/template/jr-report.properties";

    static
    {
        try {
            LOG.debug(String.format("Loadind Properties From %1$s",JrReportAction.class.getName()));
            InputStream input = JrReportAction.class.getResourceAsStream(PROPERTIES_FILE);
            if(input != null)
            {
                prop = new Properties();
                prop.load(input);
                LOG.debug(String.format("Load Finish Properties From %1$s",JrReportAction.class.getName()));
            }
            else
            {
                LOG.debug(String.format("Loadind Properties From %1$s failed",JrReportAction.class.getName()));
            }
            
            
        } catch (Exception ex) {
            LOG.debug(String.format("Cannot Load Properties From %1$s",JrReportAction.class.getName()));
        }
    }

    //~ Methods ································································
    protected void addParams(String name,Object value)
    {
        if(LOG.isDebugEnabled())
        {
            LOG.debug("name = "+name+" value = "+value);
        }
        this.params.put(name,value);
    }


    public HashMap<String, Object> getParams() {
        return params;
    }

    public void setParams(HashMap<String, Object> params) {
        this.params = params;
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

    public String index()
    {
        try
        {
        }
        catch(Exception e)
        {
            LOG.error(e.getMessage(),e);
            return ERROR;
        }

        return SUCCESS;
    }

    
    protected abstract void createParams() throws Exception ;   



    


    
    
}
