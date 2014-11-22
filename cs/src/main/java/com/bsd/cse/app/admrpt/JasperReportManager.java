/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.admrpt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.vfs.FileObject;

/**
 *
 * @author arg
 */
public class JasperReportManager {

   private static Log LOG = LogFactory.getLog(JasperReportManager.class);
   
   private static HashMap<String , JasperReport> cache = new HashMap<String , JasperReport>();   

   private static InputStream getResourceAsStream(String filename)
   {
       InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
       if(in != null)
       {
           if(LOG.isDebugEnabled())
           {
                LOG.debug(String.format(" Load %1$s from Context Class Loader", filename));
           }
           return in;
       }
       
       in = JasperReportManager.class.getResourceAsStream(filename);

       if(in != null)
       {
           if(LOG.isDebugEnabled())
           {
                LOG.debug(String.format(" Load %1$s from %2$s Loader", filename,JasperReportManager.class.getName()));
           }
           return in;
       }

       in = Class.class.getResourceAsStream(filename);

       if(in != null)
       {
           if(LOG.isDebugEnabled())
           {
                LOG.debug(String.format(" Load %1$s ", filename));
           }
           return in;
       }

       LOG.info(String.format("Can not  Load %1$s ", filename));
       

       return null;

   }
    
   public static JasperReport getJasperReport(String filename,String reportType) throws Exception
   {
       try
       {
            JasperReport report = cache.get(reportType+filename);
            if(report == null)
            {
                if(LOG.isDebugEnabled())
                {
                    LOG.debug(String.format("Compile report name : %1$s",filename));
                }
                synchronized(JasperReportManager.class)
                {
                    JasperDesign jasperDesign = JRXmlLoader.load(getResourceAsStream(filename));                    
                    cache.put(filename,JasperCompileManager.compileReport(jasperDesign));
                    jasperDesign.setIgnorePagination(true);          
                    cache.put(JasperReportType.HTML+filename,JasperCompileManager.compileReport(jasperDesign));
                }
            }
            return cache.get(reportType+filename);
       }
       catch(JRException e)
       {
           LOG.error("Error",e);
           throw new Exception(e);
       }
   }

   
   

   public static void generateReportDBtoStream(JasperReport jasperReport,HashMap map,Connection con,String reportType,OutputStream output,Boolean isFirefoxBrowser) throws JRException, Exception
   {
       if(output == null)
       {
           throw new Exception("OutputStream is null");
       }
       JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, con);
       if(JasperReportType.PDF.equals(reportType))
       {
           JasperExportManager.exportReportToPdfStream(jasperPrint,output);
       }
       else if(JasperReportType.HTML.equals(reportType))
       {           
            getHTMLExporter(jasperPrint, output,isFirefoxBrowser).exportReport();
       }
       else if(JasperReportType.CSV.equals(reportType))
       {
            getCSVExporter(jasperPrint, output).exportReport();
       }
       
   }


   public static void generateReportDBtoFile(JasperReport jasperReport,HashMap map,Connection con,String reportType,File file,Boolean isFirefoxBrowser) throws JRException, FileNotFoundException, Exception
   {

       if(file == null)
       {
           throw new Exception("File is null");
       }
       FileOutputStream output = new FileOutputStream(file);       
       
       JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, con);
       if(JasperReportType.PDF.equals(reportType))
       {
           JasperExportManager.exportReportToPdfStream(jasperPrint,output);
       }
       else if(JasperReportType.HTML.equals(reportType))
       {
            getHTMLExporter(jasperPrint, output,isFirefoxBrowser).exportReport();
       }
       else if(JasperReportType.CSV.equals(reportType))
       {
            getCSVExporter(jasperPrint, output).exportReport();
       }
       if(output!=null)
       {
           output.close();
       }

   }
      
   public static void generateReportDatasourceToFile(JasperReport jasperReport,HashMap map,JRDataSource dataSource,String reportType,File file,Boolean isFirefoxBrowser) throws JRException, Exception
   {
       if(file == null)
       {
           throw new Exception("File is null");
       }
       FileOutputStream output = new FileOutputStream(file);
       try
       {
           JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource);
           if(JasperReportType.PDF.equals(reportType))
           {
               JasperExportManager.exportReportToPdfStream(jasperPrint,output);               
           }
           else if(JasperReportType.HTML.equals(reportType))
           {
               getHTMLExporter(jasperPrint, output,isFirefoxBrowser).exportReport();
           }
           else if(JasperReportType.CSV.equals(reportType))
           {
                getCSVExporter(jasperPrint, output).exportReport();
           }
       }
       finally
       {
           if(output != null)
           {
               try
               {
                output.close();
               }catch(Exception e)
               {
                   LOG.warn("Can not close file.");
               }
           }
                      
       }
   }

   public static void generateReportDatasourceToStream(JasperReport jasperReport,HashMap map,JRDataSource dataSource,String reportType,OutputStream output,Boolean isFirefoxBrowser) throws JRException, Exception
   {
       if(output == null)
       {
           throw new Exception("OutputStream is null");
       }
       JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource);
       if(JasperReportType.PDF.equals(reportType))
       {
           JasperExportManager.exportReportToPdfStream(jasperPrint,output);
       }
       else if(JasperReportType.HTML.equals(reportType))
       {
           getHTMLExporter(jasperPrint, output, isFirefoxBrowser).exportReport();
       }
       else if(JasperReportType.CSV.equals(reportType))
       {
            getCSVExporter(jasperPrint, output).exportReport();
       }       
   }

   private static JRExporter getCSVExporter(JasperPrint jasperPrint,OutputStream output)
   {
       JRCsvExporter csvExport = new JRCsvExporter();
       csvExport.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       csvExport.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
       csvExport.setParameter(JRCsvExporterParameter.CHARACTER_ENCODING, "UTF-8");
       csvExport.setParameter(JRCsvExporterParameter.FIELD_DELIMITER, ",");
       csvExport.setParameter(JRCsvExporterParameter.RECORD_DELIMITER, "\n");
       
       return csvExport;
   }

   private static JRExporter getHTMLExporter(JasperPrint jasperPrint,OutputStream output,Boolean isFirefoxBrowser)
   {
       JRHtmlExporter htmlExport = new ARJRHtmlExporter(isFirefoxBrowser);
       htmlExport.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
       htmlExport.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
       htmlExport.setParameter(JRHtmlExporterParameter.CHARACTER_ENCODING, "UTF-8");
       htmlExport.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.FALSE);
       htmlExport.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);      
       htmlExport.setParameter(JRHtmlExporterParameter.IGNORE_PAGE_MARGINS,Boolean.TRUE);

       return htmlExport;

   }



   
   


}
