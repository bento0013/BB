/*
 * $Id: AdminReportAction.java,v 1.2 2011/08/25 10:27:54 ekalakt Exp $
 *
 * Copyright (C) 2007 Advanced Research Group Co., Ltd. (ARG).
 *
 * All Rights Researved.
 */


package com.bsd.cse.struts2.action.rpt;

import com.bsd.cse.common.Configuration;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * DOCUMENT ME!
 *
 * @author Fuangchai (Big)
 * @version $Revision: 1.2 $
 */
public abstract class AdminReportAction
    extends JrReportAction
{    
    //~ Static fields/initializers ·············································
    protected static Log LOG = LogFactory.getLog(AdminReportAction.class);
        

    public static String reportSize = "";
    private static final long serialVersionUID = 1198628017701609600L;

          
    protected HashMap<String,Object> params = new HashMap();

    protected final String FULL_FORMAT_DATE = "dd/MM/yyyy HH:mm";
    
}