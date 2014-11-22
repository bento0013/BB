/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.admrpt;

import com.bsd.cse.common.Configuration;
import com.bsd.cse.model.security.Group;
import com.bsd.cse.common.app.TransactionalProcessor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.vfs.FileObject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jdbc.Work;

/**
 *
 * @author thanasith
 */
public class AdminReportCore {

    private static Log log = LogFactory.getLog(AdminReportCore.class);
    protected static Properties prop;
    protected static String PROPERTIES_FILE = "/template/admin-report-query.properties";

    static
    {
        try {
            log.debug(String.format("Loadind Properties From %1$s",AdminReportCore.class.getName()));
            InputStream input = AdminReportCore.class.getResourceAsStream(PROPERTIES_FILE);
            if(input != null)
            {
                prop = new Properties();
                prop.load(input);
                log.debug(String.format("Load Finish Properties From %1$s",AdminReportCore.class.getName()));
            }
            else
            {
                log.debug(String.format("Loadind Properties From %1$s failed",AdminReportCore.class.getName()));
            }


        } catch (Exception ex) {
            log.debug(String.format("Cannot Load Properties From %1$s",AdminReportCore.class.getName()));
        }
    }

    public static Properties getProp()
    {
        return prop;
    }

    public static void setProp(Properties prop)
    {
        AdminReportCore.prop = prop;
    }    
    

//    public byte[] generateReportString(final HashMap<String,Object> params,final String reportType,final String filename,final Boolean isFirefoxBrowser,Map subreportMap) throws Exception
//    {
//        final HashMap<String,Object> map = new HashMap<String,Object>();
//        params.putAll(addSubreport(subreportMap, reportType));
//        (new TransactionalProcessor()
//        {
//
//            @Override
//            public void process(Session session, Transaction tx) throws Exception {
//                JasperReport jasper = JasperReportManager.getJasperReport(filename,JasperReportType.HTML.equals(reportType)?JasperReportType.HTML:"");
//                ByteArrayOutputStream output = new ByteArrayOutputStream();
//                JasperReportManager.generateReportDBtoStream(jasper, params, session.connection(), reportType, output,isFirefoxBrowser);
//                map.put("result",output.toByteArray());
//            }
//
//        }).process();
//
//        return (byte[])map.get("result");
//    }

//    public InputStream generateReportInputStream(final HashMap<String,Object> params,final String reportType,final String filename,final Boolean isFirefoxBrowser,Map subreportMap) throws Exception
//    {
//        final HashMap<String,Object> map = new HashMap<String,Object>();
//        params.putAll(addSubreport(subreportMap, reportType));
//        (new TransactionalProcessor()
//        {
//
//            @Override
//            public void process(Session session, Transaction tx) throws Exception {
//                JasperReport jasper = JasperReportManager.getJasperReport(filename,JasperReportType.HTML.equals(reportType)?JasperReportType.HTML:"");
//                ByteArrayOutputStream output = new ByteArrayOutputStream();
//                JasperReportManager.generateReportDBtoStream(jasper, params, session.connection(), reportType, output,isFirefoxBrowser);
//                map.put("result",new ByteArrayInputStream(output.toByteArray()));
//            }
//
//        }).process();
//
//        return (InputStream)map.get("result");
//
//    }

    

    public void generateReportFile(final HashMap<String,Object> params,final String reportType,final String filename,final File resultFile,final Boolean isFirefoxBrowser,Map subreportMap) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        params.putAll(addSubreport(subreportMap, reportType));
        (new TransactionalProcessor()
        {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                final JasperReport jasper = JasperReportManager.getJasperReport(filename,JasperReportType.HTML.equals(reportType)?JasperReportType.HTML:"");
                session.doWork(new Work(){
                    @Override
                    public void execute(Connection conn) throws SQLException {
                        try {
                            JasperReportManager.generateReportDBtoFile(jasper, params, conn, reportType, resultFile, isFirefoxBrowser);
                        } catch (JRException ex) {
                            throw new RuntimeException(ex);
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                
//                map.put("result",new ByteArrayInputStream(output.toByteArray()));
            }

        }).process();
        

    }

     public void generateReportDatasourceFile(final HashMap<String,Object> params,final String reportType,final String filename,final File resultFile,final Boolean isFirefoxBrowser,final JRDataSource datasource,Map subreportMap) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        params.putAll(addSubreport(subreportMap, reportType));        
        JasperReport jasper = JasperReportManager.getJasperReport(filename,JasperReportType.HTML.equals(reportType)?JasperReportType.HTML:"");
        JasperReportManager.generateReportDatasourceToFile(jasper, params,datasource, reportType, resultFile,isFirefoxBrowser);
    }

    private Map addSubreport(Map subreportMap,String reportType) throws Exception
    {
        Map jasperMap = new HashMap();
        if(subreportMap != null && subreportMap.size()>0)
        {
            for(String index : (String[])subreportMap.keySet().toArray(new String[0]))
            {
                String filename = (String)subreportMap.get(index);                
                jasperMap.put(index, JasperReportManager.getJasperReport(filename,JasperReportType.HTML.equals(reportType)?JasperReportType.HTML:""));
            }
        }
        return jasperMap;
    }
    
}
