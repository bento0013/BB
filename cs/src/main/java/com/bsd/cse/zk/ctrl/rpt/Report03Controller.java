/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.rpt;

import com.bsd.cse.app.backoffice.CheckpointCore;
import com.bsd.cse.app.backoffice.CustomerCore;
import com.bsd.cse.app.backoffice.PartCore;
import com.bsd.cse.app.backoffice.ProcessCore;
import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.customer.Customer;
import com.bsd.cse.model.backoffice.part.Checkpoint;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.backoffice.part.Process;
import com.bsd.cse.model.input.ControlChartGroupingData;
import com.bsd.cse.model.input.MeasurementPart;
import com.bsd.cse.model.report.ControlData;
import com.bsd.cse.model.report.ControlSerieData;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import freemarker.template.utility.Collections12;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.zkoss.web.Attributes;
import org.zkoss.zhtml.Form;
import org.zkoss.zhtml.Input;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;

/**
 *
 * @author bento
 */
public class Report03Controller extends SecurityController{
    private static final long serialVersionUID = -4492739773980927088L;
    private Log LOG = LogFactory.getLog(Report03Controller.class);
    private Datebox startDatebox;
    private Datebox endDatebox;
    private Combobox partCbox;
    private Combobox processCbox;
    private Combobox checkpointCbox;
    private Button searchBtn;
    private Form rptForm;
    private Input requestByInput = new Input();
    private Input startDateStrInput = new Input();
    private Input endDateStrInput = new Input();
    private Input partInput = new Input();
    private Input processInput = new Input();
    private Input positionInput = new Input();
    private Input startDateInput = new Input();
    private Input endDateInput = new Input();    
    
    
    
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
        getDate();
        setPart();
        setProcess();
        setCheckPoint((Part)partCbox.getSelectedItem().getValue(), (Process)processCbox.getSelectedItem().getValue());
        setForm();
    }

    private void setPart() throws Exception
    {       
        PartCore core = new PartCore();
        List<Part> parts = core.getAllList();
        ZKCatalogs.setParts(partCbox, parts);                 
        partCbox.setSelectedIndex(0);
    }

    private void setProcess() throws Exception
    {              
        ProcessCore core = new ProcessCore();
        List<Process> processes = core.getAllList();
        ZKCatalogs.setProcesses(processCbox, processes);              
        processCbox.setSelectedIndex(0);
    }

    private void setCheckPoint(Part part, Process process) throws Exception
    {        
        CheckpointCore core = new CheckpointCore();
        List<Checkpoint> checkpoint = core.getListForControlChart(part, process);
        checkpointCbox.getChildren().clear();
        ZKCatalogs.setCheckpoints(checkpointCbox, checkpoint);
        if(checkpoint == null || checkpoint.isEmpty())
        {
            Comboitem item = new Comboitem();
            item.setLabel("");
            checkpointCbox.getChildren().add(item);
        }
        checkpointCbox.setSelectedIndex(0);
    }

    private void getDate()
    {
        Locale locale = (Locale)session.getAttribute(Attributes.PREFERRED_LOCALE);
        Calendar date = Calendar.getInstance(locale);
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.MILLISECOND, 0);
        endDatebox.setValue(date.getTime());
        date.add(Calendar.DATE,-30);
        startDatebox.setValue(date.getTime());
        
    }
   
   

    public void setForm()
    {        
        startDateInput.setDynamicProperty("name", "startDate");
        endDateInput.setDynamicProperty("name", "endDate");
        startDateStrInput.setDynamicProperty("name", "startDateStr");
        endDateStrInput.setDynamicProperty("name", "endDateStr");
        partInput.setDynamicProperty("name","partId");
        processInput.setDynamicProperty("name","processId");
        positionInput.setDynamicProperty("name","position");
        requestByInput.setDynamicProperty("name", "requestBy");
        startDateStrInput.setVisible(false);
        endDateStrInput.setVisible(false);
        startDateInput.setVisible(false);
        endDateInput.setVisible(false);
        partInput.setVisible(false);
        processInput.setVisible(false);
        positionInput.setVisible(false);
        requestByInput.setVisible(false);        
        rptForm.appendChild(startDateStrInput);
        rptForm.appendChild(endDateStrInput);
        rptForm.appendChild(requestByInput);
        rptForm.appendChild(processInput);
        rptForm.appendChild(positionInput);
        rptForm.appendChild(partInput);
        rptForm.appendChild(startDateInput);
        rptForm.appendChild(endDateInput);
    }
    

    public void addEventListener()
    {

        partCbox.addEventListener(Events.ON_CHANGE, new EventListener(){

            @Override
            public void onEvent(Event event) throws Exception {
                setCheckPoint((Part)partCbox.getSelectedItem().getValue(), (Process)processCbox.getSelectedItem().getValue());
            }

        });

        processCbox.addEventListener(Events.ON_CHANGE, new EventListener(){

            @Override
            public void onEvent(Event event) throws Exception {
                setCheckPoint((Part)partCbox.getSelectedItem().getValue(), (Process)processCbox.getSelectedItem().getValue());
            }

        });

        searchBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                Locale locale = (Locale)session.getAttribute(Attributes.PREFERRED_LOCALE);
                Date startDate = startDatebox.getValue();
                Date endDate = endDatebox.getValue();
                Part part = (Part)partCbox.getSelectedItem().getValue();
                Process process = (Process)processCbox.getSelectedItem().getValue();
                Checkpoint checkpoint = (Checkpoint)checkpointCbox.getSelectedItem().getValue();

                if(startDate.after(endDate))
                {
                    AlertMessages.alertMessage("report03.page.alert.message1");
                    return;
                }

                if(checkpoint == null)
                {
                    AlertMessages.alertMessage("report03.page.alert.message2");
                    return;
                }
                


                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
//                SimpleDateFormat formatDateStr = new SimpleDateFormat("dd MMMM yyyy",Locale.US);
                SimpleDateFormat formatDateDateStr = new SimpleDateFormat("dd",Locale.US);
                SimpleDateFormat formatDateMonthStr = new SimpleDateFormat("MMMM",new Locale("th","TH"));
                SimpleDateFormat formatDateYearStr = new SimpleDateFormat("yyyy",Locale.US);
                                
                String startDateStr = formatDateDateStr.format(startDate)+" "+formatDateMonthStr.format(startDate)+" "+formatDateYearStr.format(startDate);
                String endDateStr = formatDateDateStr.format(endDate)+" "+formatDateMonthStr.format(endDate)+" "+formatDateYearStr.format(endDate);
                startDateStrInput.setValue(startDateStr);
                endDateStrInput.setValue(endDateStr);
                startDateInput.setValue(formatDate.format(startDate));
                Calendar endCal = Calendar.getInstance();
                endCal.setTime(endDate);
                endCal.add(Calendar.DATE, 1);
                endDateInput.setValue(formatDate.format(endCal.getTime()));
                HttpUserSession userSession = new HttpUserSession();
                requestByInput.setValue((String)userSession.getUserInformation().get(Users.FULL_NAME));
                partInput.setValue(part!=null?String.valueOf(part.getId()):"");
                processInput.setValue(process!=null?String.valueOf(process.getId()):"");
                positionInput.setValue(checkpoint!=null?String.valueOf(checkpoint.getPosition()):"");
//                samplingData();
//                LOG.info("Can sampling data ");
                Clients.submitForm(rptForm);

            }
        });
                       
    }

    

    public void createComponent(Component component)
    {
               
        searchBtn = (Button)getComponent(component, "searchBtn", searchBtn);
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
