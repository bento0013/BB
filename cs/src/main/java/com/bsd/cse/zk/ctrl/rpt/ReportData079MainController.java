/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.rpt;

import com.bsd.cse.app.backoffice.CustomerCore;
import com.bsd.cse.app.backoffice.PartCore;
import com.bsd.cse.model.backoffice.customer.Customer;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.report.OperationData;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.grid.renderer.OEEFactorRenderer;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.web.Attributes;
import org.zkoss.zhtml.Form;
import org.zkoss.zhtml.Input;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;

/**
 *
 * @author bento
 */
public class ReportData079MainController extends SecurityController{
    private static final long serialVersionUID = -4492739773980927088L;
    private Log LOG = LogFactory.getLog(ReportData079MainController.class);
    private Date startDate;
    private Date endDate;
    private Long partId;
    private Grid oeeFactorGrid;

    
    
    
     @Override
    public void doAfterCompose(Component component) throws Exception
    {
        super.doAfterCompose(component);
        createComponent(component);
        addEventListener();
        loadContent();

    }

    public void loadContent() throws Exception
    {                
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        startDate = (Date)Executions.getCurrent().getArg().get("startDate");
        endDate = (Date)Executions.getCurrent().getArg().get("endDate");
        partId = (Long)Executions.getCurrent().getArg().get("partId");

        LOG.info("partId = "+partId);
        LOG.info("startDate = "+startDate);
        LOG.info("endDate = "+endDate);

//        oeeFactorGrid.setRowRenderer(new OEEFactorRenderer());
        ArrayList<OperationData> ops = new ArrayList<OperationData>();
        
        OperationData data = new OperationData("U","Availability");        
        for(int index = 0;index < 12 ;index++)
        {
            data.getOp()[index] = new BigDecimal(Math.random()*100);
        }
        ops.add(data);

        OperationData data1 = new OperationData("V","Performance");
        for(int index = 0;index < 12 ;index++)
        {
            data1.getOp()[index] = new BigDecimal(Math.random()*100);
        }
        ops.add(data1);

        OperationData data2 = new OperationData("W","Quality");
        for(int index = 0;index < 12 ;index++)
        {
            data2.getOp()[index] = new BigDecimal(Math.random()*100);
        }
        ops.add(data2);

        OperationData data3 = new OperationData("X","Overall OEE");
        for(int index = 0;index < 12 ;index++)
        {
            data3.getOp()[index] = new BigDecimal(Math.random()*100);
        }
        ops.add(data3);
        
        oeeFactorGrid.setModel(new ListModelList(ops));


        getDate();
        setPart();        
    }

    private void setPart() throws Exception
    {
    }

   

    private void getDate()
    {        
    }
      

    public void addEventListener()
    {

                
    }

    public void createComponent(Component component)
    {
                       
    }


    public static void main(String[] args) throws ParseException {
        Locale locale = new Locale("th","TH");
        String month = "01";
        String year = "2554";
        String period = year+month;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM",locale == null?Locale.US:locale);
        SimpleDateFormat formatPeriodStr = new SimpleDateFormat("MMMM yyyy",locale == null?Locale.US:locale);
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
        Date periodSearch = format.parse(period);
        Calendar cal = Calendar.getInstance();
        cal.setTime(periodSearch);
        cal.set(Calendar.DATE,cal.getActualMinimum(Calendar.DATE));
        Date startDate = cal.getTime();
        cal.set(Calendar.DATE,cal.getActualMaximum(Calendar.DATE));
        cal.add(Calendar.DATE,1);
        Date endDate = cal.getTime();
        String startDateStr = formatDate.format(startDate);
        String endDateStr = formatDate.format(endDate);
        String periodStr = formatPeriodStr.format(periodSearch);

        System.out.println("periodStr = " + periodStr);
        System.out.println("startDateStr = " + startDateStr);
        System.out.println("endDateStr = " + endDateStr);
    }
}
