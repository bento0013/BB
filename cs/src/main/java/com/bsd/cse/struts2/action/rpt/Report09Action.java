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
import com.bsd.cse.struts2.action.BaseAction;
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
public class Report09Action extends BaseAction    
{
   
    
    protected static Log log = LogFactory.getLog(Report09Action.class);
    private static final long serialVersionUID = -2634117399142812092L;

    public Report09Action()
    {        
    }

    
    public String report()
    {       
        return "report";
    }
       

         
 
}
