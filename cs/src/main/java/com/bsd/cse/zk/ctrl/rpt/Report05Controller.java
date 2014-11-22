/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.rpt;

import com.bsd.cse.app.backoffice.CustomerCore;
import com.bsd.cse.app.backoffice.PartCore;
import com.bsd.cse.app.backoffice.ProcessCore;
import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.customer.Customer;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.backoffice.part.Process;
import com.bsd.cse.model.input.MeasurementPart;
import com.bsd.cse.model.input.RamdomMeasurementValue;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
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
public class Report05Controller extends SecurityController{
    private static final long serialVersionUID = -4492739773980927088L;
    private Log LOG = LogFactory.getLog(Report05Controller.class);
    private Datebox startDatebox;
    private Combobox partCbox;
    private Combobox processCbox;
    private Button searchBtn;
    private Form rptForm;
    private Input requestByInput = new Input();
    private Input startDateStrInput = new Input();
//    private Input endDateStrInput = new Input();
    private Input partInput = new Input();
    private Input processInput = new Input();
    private Input startDateInput = new Input();
//    private Input endDateInput = new Input();
    private Input timeInput[] = new Input[5];
//    private Input periodInput[] = new Input[5];
    private Input machineInput[] = new Input[5];
    

    
    
    
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
        setForm();
    }

    private void setProcess() throws Exception
    {
        ProcessCore core = new ProcessCore();
        List<Process> processes = core.getAllList();
        ZKCatalogs.setProcesses(processCbox, processes);
        processCbox.setSelectedIndex(0);
    }

    private void setPart() throws Exception
    {
        
        PartCore core = new PartCore();
        List<Part> parts = core.getAllList();
        ZKCatalogs.setParts(partCbox, parts);   
        partCbox.setSelectedIndex(0);
    }

    

    private void getDate()
    {
        Locale locale = (Locale)session.getAttribute(Attributes.PREFERRED_LOCALE);
        Calendar date = Calendar.getInstance(locale);
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.MILLISECOND, 0);
        startDatebox.setValue(date.getTime());
    }

    public void ramdomPosition(final Long partId,final Long processId,final Date measurementDate) throws Exception
    {
        (new TransactionalProcessor() {
            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Query query = session.createQuery("select measurementTime.id as time,machine.id as machineId from MeasurementPart where process.id = :processId and part.id = :partId and measurementDate = :measurementDate group by measurementTime.id,machine.id ");
                query.setLong("processId",processId);
                query.setLong("partId",partId);
                query.setDate("measurementDate",measurementDate);  
                query.setResultTransformer(Transformers.aliasToBean(RamdomMeasurementValue.class));
                List<RamdomMeasurementValue> list = (List<RamdomMeasurementValue>)query.list();
                HashMap<Integer,RamdomMeasurementValue> randomDatas = new HashMap<Integer,RamdomMeasurementValue>();
                LOG.info("result ok");
                

                if(list != null)
                {

                    if(list.size() > 5)
                    {
                        LOG.info("start random");
                        while(randomDatas.size()<5)
                        {
                            Integer randomNumber = (new Double(Math.random()*list.size()*100)).intValue()%list.size();
                            LOG.info("random value = "+randomNumber);
                            randomDatas.put(randomNumber,list.get(randomNumber));
                        }
                        LOG.info("finish random");
                    }
                    
                    if(list.size() <= 5)
                    {
                        for(int index =0;index<list.size();index++)
                        {
                            randomDatas.put(index, list.get(index));
                        }

                        for(int index=list.size();index<5;index++)
                        {
                            RamdomMeasurementValue value = new RamdomMeasurementValue();
                            value.setMachineId(0L);
//                            value.setPeriod(0);
                            value.setTime(0L);
                            randomDatas.put(index, value);
                        }
                    }

                    RamdomMeasurementValue[] randomList = (RamdomMeasurementValue[])randomDatas.values().toArray(new RamdomMeasurementValue[0]);
                    for(int index=0;index<randomList.length;index++)
                    {
                        RamdomMeasurementValue value = randomList[index];
                        timeInput[index].setValue(String.valueOf(value.getTime()));
//                        periodInput[index].setValue(String.valueOf(value.getPeriod()));
                        machineInput[index].setValue(String.valueOf(value.getMachineId()));
                    }

                    

                }

                for(int index=0;index<5;index++)
                {                    
                   LOG.info("timeInput["+index+"] = "+timeInput[index].getValue());
//                   LOG.info("periodInput["+index+"] = "+ periodInput[index].getValue());
                   LOG.info("machineInput["+index+"] = "+ machineInput[index].getValue());
                }

               
                
            }
        }).process();
    }
   
   

    public void setForm()
    {        
        startDateInput.setDynamicProperty("name", "startDate");
//        endDateInput.setDynamicProperty("name", "endDate");
        startDateStrInput.setDynamicProperty("name", "startDateStr");
//        endDateStrInput.setDynamicProperty("name", "endDateStr");
        partInput.setDynamicProperty("name","partId");
        processInput.setDynamicProperty("name","processId");
        requestByInput.setDynamicProperty("name", "requestBy");
        for(int index = 0 ; index < 5 ;index++)
        {
            timeInput[index] = new Input();
            timeInput[index].setDynamicProperty("name", "time"+index);
            timeInput[index].setVisible(false);
            rptForm.appendChild(timeInput[index]);
//            periodInput[index] = new Input();
//            periodInput[index].setDynamicProperty("name", "period"+index);
//            periodInput[index].setVisible(false);
//            rptForm.appendChild(periodInput[index]);
            machineInput[index] = new Input();
            machineInput[index].setDynamicProperty("name", "machine"+index);
            machineInput[index].setVisible(false);
            rptForm.appendChild(machineInput[index]);
        }
        
        startDateStrInput.setVisible(false);
//        endDateStrInput.setVisible(false);
        startDateInput.setVisible(false);
//        endDateInput.setVisible(false);
        partInput.setVisible(false);
        processInput.setVisible(false);
        requestByInput.setVisible(false);
        
        rptForm.appendChild(startDateStrInput);
//        rptForm.appendChild(endDateStrInput);
        rptForm.appendChild(requestByInput);
        rptForm.appendChild(processInput);
        rptForm.appendChild(partInput);
        rptForm.appendChild(startDateInput);
//        rptForm.appendChild(endDateInput);
    }
    

    public void addEventListener()
    {

        searchBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                Locale locale = (Locale)session.getAttribute(Attributes.PREFERRED_LOCALE);
                Date startDate = startDatebox.getValue();
                Part part = (Part)partCbox.getSelectedItem().getValue();

                Process process = (Process)processCbox.getSelectedItem().getValue();
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
                SimpleDateFormat formatDateStr = new SimpleDateFormat("dd/MM/yyyy",Locale.US);
                                
                String startDateStr = formatDateStr.format(startDate);
                startDateStrInput.setValue(startDateStr);
                startDateInput.setValue(formatDate.format(startDate));
//                Calendar endCal = Calendar.getInstance();
//                endCal.add(Calendar.DATE, 1);
//                endDateInput.setValue(formatDate.format(endCal.getTime()));
                HttpUserSession userSession = new HttpUserSession();
                requestByInput.setValue((String)userSession.getUserInformation().get(Users.FULL_NAME));
                partInput.setValue(part!=null?String.valueOf(part.getId()):"");
                processInput.setValue(process!=null?String.valueOf(process.getId()):"");
                ramdomPosition(part.getId(), process.getId(), startDate);

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
