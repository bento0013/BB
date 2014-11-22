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
import com.bsd.cse.model.backoffice.machine.Machine;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.backoffice.part.Process;
import com.bsd.cse.model.input.PeriodTeamValue;
import com.bsd.cse.model.input.RamdomMeasurementValue;
import com.bsd.cse.model.report.MeasurementMinMaxTime;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
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
public class Report04Controller extends SecurityController{
    private static final long serialVersionUID = -4492739773980927088L;
    private Log LOG = LogFactory.getLog(Report04Controller.class);
    private Datebox startDatebox;
    private Combobox partCbox;
    private Combobox processCbox;
    private Button searchBtn;
    private Form rptForm;
    private Input requestByInput = new Input();
    private Input startDateStrInput = new Input();    
    private Input partInput = new Input();
    private Input processInput = new Input();
    private Input startDateInput = new Input();
//    private Input period1Input = new Input();
//    private Input period2Input = new Input();
    private Input machineNameInput = new Input();
    private Input machineInput[] = new Input[20];
    private Input timeInput[] = new Input[10];
    
    
    
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
   
   

    public void setForm()
    {        
        startDateInput.setDynamicProperty("name", "startDate");        
        startDateStrInput.setDynamicProperty("name", "startDateStr");        
        partInput.setDynamicProperty("name","partId");
        processInput.setDynamicProperty("name","processId");
        requestByInput.setDynamicProperty("name", "requestBy");
        machineNameInput.setDynamicProperty("name", "machineName");
//        period1Input.setDynamicProperty("name", "period1");
//        period2Input.setDynamicProperty("name", "period2");
//        period1Input.setVisible(false);
//        period2Input.setVisible(false);
        processInput.setVisible(false);
        machineNameInput.setVisible(false);
        requestByInput.setVisible(false);
        startDateStrInput.setVisible(false);
        startDateInput.setVisible(false);
        partInput.setVisible(false);
        for(int index=0;index<20;index++)
        {
            machineInput[index] = new Input();
            machineInput[index].setDynamicProperty("name", "machine"+index);
            machineInput[index].setVisible(false);
            machineInput[index].setValue("0");
            rptForm.appendChild(machineInput[index] );            
        }

        for(int index=0;index<10;index++)
        {
            timeInput[index] = new Input();
            timeInput[index].setDynamicProperty("name", "time"+index);
            timeInput[index].setVisible(false);
            timeInput[index].setValue("");
            rptForm.appendChild(timeInput[index]);
        }
        rptForm.appendChild(startDateStrInput);
        rptForm.appendChild(machineNameInput);
        rptForm.appendChild(requestByInput);
        rptForm.appendChild(processInput);
        rptForm.appendChild(partInput);
        rptForm.appendChild(startDateInput);
//        rptForm.appendChild(period1Input);
//        rptForm.appendChild(period2Input);
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
                HttpUserSession userSession = new HttpUserSession();
                requestByInput.setValue((String)userSession.getUserInformation().get(Users.FULL_NAME));
                partInput.setValue(part!=null?String.valueOf(part.getId()):"");
                processInput.setValue(process!=null?String.valueOf(process.getId()):"");
//                getPeriodEmployee(startDate,part,process);
                getMachineName(startDate,part,process);
                getTime(startDate,part,process);
                Clients.submitForm(rptForm);

            }
        });
                       
    }

//    public void getPeriodEmployee(final Date startDate,final Part part,final Process process) throws Exception
//    {
//        (new TransactionalProcessor()
//        {
//
//            @Override
//            public void process(Session session, Transaction tx) throws Exception {
//                   SQLQuery query = session.createSQLQuery("select cmp.period period,ct.team_name teamName from cs_measurement_part cmp  "+
//                        " inner join cs_user cu on cmp.created_by = cu.user_id "+
//                        " inner join cs_team ct on cu.team_id = ct.team_id  where cmp.measurement_date = :startDate " +
//                        " and cmp.part_id = :partId and cmp.process_id = :processId and ct.team_name is not null" +
//                        " group by period,team_name");
//                   query.setLong("processId", process.getId());
//                   query.setLong("partId", part.getId());
//                   query.setDate("startDate", startDate);
//                   query.setResultTransformer(Transformers.aliasToBean(PeriodTeamValue.class));
//                   List<PeriodTeamValue> periodDatas  = query.list();
//                   for(PeriodTeamValue value : periodDatas)
//                   {
//                       if(value.getTeamName() != null)
//                       {
//                           if(value.getPeriod().equals(1))
//                           {
//                               period1Input.setValue(value.getTeamName());
//                           }
//                           else
//                           {
//                               period2Input.setValue(value.getTeamName());
//                           }
//                       }
//                   }
//
//            }
//        }).process();
//    }


    public void getMachineName(final Date startDate,final Part part,final Process process) throws Exception
    {
        (new TransactionalProcessor()
        {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                   Query query = session.createQuery(
                           "select measurementTime.id as time ,machine.id as machineId " +
                           "from MeasurementPart where part.id = :partId and process.id = :processId " +
                           "and measurementDate = :startDate group by measurementTime.id,machine.id");
                   query.setLong("processId", process.getId());
                   query.setLong("partId", part.getId());
                   query.setDate("startDate", startDate);
                   query.setResultTransformer(Transformers.aliasToBean(RamdomMeasurementValue.class));
                   List<RamdomMeasurementValue> periodDatas  = query.list();
                   HashMap<Integer,List<RamdomMeasurementValue>> randoms = new HashMap<Integer,List<RamdomMeasurementValue>>();
                   for(RamdomMeasurementValue value : periodDatas)
                   {
                       Integer index1 = 0;
                       Integer index2 = (value.getTime().intValue()-1);
                       Integer index = index1+index2;
                       List<RamdomMeasurementValue> list = randoms.get(index);
                       if(list == null)
                       {
                           list = new ArrayList<RamdomMeasurementValue>();
                           list.add(value);
                           randoms.put(index, list);
                       }
                       else
                       {
                           list.add(value);
                       }
                   }
                   List<Long> machineIds = new ArrayList<Long>();                   
                   for(int index = 0;index<20 ;index++)
                   {
                       List<RamdomMeasurementValue> randomLists = randoms.get(index);
                       if(randomLists != null)
                       {
                           Integer randomIndex = new Double((Math.random()*100*randomLists.size())).intValue()%randomLists.size();
                           RamdomMeasurementValue value = randomLists.get(randomIndex);
                           machineInput[index].setValue(String.valueOf(value.getMachineId()));
                           if(!machineIds.contains(value.getMachineId()))
                           {
                               machineIds.add(value.getMachineId());
                           }
                       }
                   }

                   if(machineIds.size() > 0)
                   {
                       Criteria criteria = session.createCriteria(Machine.class);
                       criteria.add(Restrictions.in("id", machineIds));
                       List<Machine> machines = criteria.list();
                       StringBuffer strBuff= new StringBuffer(1024);
                       for(Machine machine : machines)
                       {
                           strBuff.append(machine.getName());
                           strBuff.append(",");
                       }
                       machineNameInput.setValue(strBuff.substring(0,strBuff.length()-1));
                   }

            }
        }).process();
    }

    public void getTime(final Date startDate,final Part part,final Process process) throws Exception
    {
        (new TransactionalProcessor()
        {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                   SQLQuery query = session.createSQLQuery(
                           "select part_id partId,measurement_date measurementDate,process_id processId," +
                           "    (select to_char(case when min_update is not null then min_update else min_create end" +
                           "        ,'HH24:MI')||' -'||to_char(case when max_update is not null then max_update else max_create end" +
                           "        ,'HH24:MI') time_1 from (select min(created_date) min_create,max(created_date) max_create" +
                           "        ,min(updated_date) min_update,max(updated_date) max_update from cs_measurement_part " +
                           "        where measurement_time = 1 and measurement_date = main_cmp.measurement_date " +
                           "        and part_id = main_cmp.part_id and process_id = main_cmp.process_id and machine_id = :machine0) " +
                           "        time_data) time1," +
                           "    (select to_char(case when min_update is not null then min_update else min_create end" +
                           "        ,'HH24:MI')||' -'||to_char(case when max_update is not null then max_update else max_create end" +
                           "        ,'HH24:MI') time_1 from (select min(created_date) min_create,max(created_date) max_create" +
                           "        ,min(updated_date) min_update,max(updated_date) max_update from cs_measurement_part " +
                           "        where measurement_time = 2 and measurement_date = main_cmp.measurement_date " +
                           "        and part_id = main_cmp.part_id and process_id = main_cmp.process_id and machine_id = :machine1) " +
                           "        time_data) time2," +
                           "    (select to_char(case when min_update is not null then min_update else min_create end" +
                           "        ,'HH24:MI')||' -'||to_char(case when max_update is not null then max_update else max_create end" +
                           "        ,'HH24:MI') time_1 from (select min(created_date) min_create,max(created_date) max_create" +
                           "        ,min(updated_date) min_update,max(updated_date) max_update from cs_measurement_part " +
                           "        where measurement_time = 3 and measurement_date = main_cmp.measurement_date " +
                           "        and part_id = main_cmp.part_id and process_id = main_cmp.process_id and machine_id = :machine2) " +
                           "        time_data) time3," +
                           "    (select to_char(case when min_update is not null then min_update else min_create end" +
                           "        ,'HH24:MI')||' -'||to_char(case when max_update is not null then max_update else max_create end" +
                           "        ,'HH24:MI') time_1  from (select min(created_date) min_create,max(created_date) max_create" +
                           "        ,min(updated_date) min_update,max(updated_date) max_update from cs_measurement_part " +
                           "        where measurement_time = 4 and measurement_date = main_cmp.measurement_date " +
                           "        and part_id = main_cmp.part_id and process_id = main_cmp.process_id and machine_id = :machine3) " +
                           "        time_data) time4 ," +
                           "    (select to_char(case when min_update is not null then min_update else min_create end" +
                           "        ,'HH24:MI')||' -'||to_char(case when max_update is not null then max_update else max_create end" +
                           "        ,'HH24:MI') time_1 from (select min(created_date) min_create,max(created_date) max_create" +
                           "        ,min(updated_date) min_update,max(updated_date) max_update from cs_measurement_part " +
                           "        where measurement_time = 5 and measurement_date = main_cmp.measurement_date " +
                           "        and part_id = main_cmp.part_id and process_id = main_cmp.process_id and machine_id = :machine4) " +
                           "        time_data) time5," +
                           "    (select to_char(case when min_update is not null then min_update else min_create end" +
                           "        ,'HH24:MI')||' -'||to_char(case when max_update is not null then max_update else max_create end" +
                           "        ,'HH24:MI') time_1  from (select min(created_date) min_create,max(created_date) max_create" +
                           "        ,min(updated_date) min_update,max(updated_date) max_update from cs_measurement_part " +
                           "        where measurement_time = 6 and measurement_date = main_cmp.measurement_date " +
                           "        and part_id = main_cmp.part_id and process_id = main_cmp.process_id and machine_id = :machine5) " +
                           "        time_data) time6," +
                           "    (select to_char(case when min_update is not null then min_update else min_create end" +
                           "        ,'HH24:MI')||' -'||to_char(case when max_update is not null then max_update else max_create end" +
                           "        ,'HH24:MI') time_1  from (select min(created_date) min_create,max(created_date) max_create" +
                           "        ,min(updated_date) min_update,max(updated_date) max_update from cs_measurement_part " +
                           "        where measurement_time = 7 and measurement_date = main_cmp.measurement_date " +
                           "        and part_id = main_cmp.part_id and process_id = main_cmp.process_id and machine_id = :machine6) " +
                           "        time_data) time7," +
                           "    (select to_char(case when min_update is not null then min_update else min_create end" +
                           "        ,'HH24:MI')||' -'||to_char(case when max_update is not null then max_update else max_create end" +
                           "        ,'HH24:MI') time_1 from (select min(created_date) min_create,max(created_date) max_create" +
                           "        ,min(updated_date) min_update,max(updated_date) max_update from cs_measurement_part " +
                           "        where measurement_time = 8 and measurement_date = main_cmp.measurement_date " +
                           "        and part_id = main_cmp.part_id and process_id = main_cmp.process_id and machine_id = :machine7) " +
                           "        time_data) time8," +
                           "    (select to_char(case when min_update is not null then min_update else min_create end" +
                           "        ,'HH24:MI')||' -'||to_char(case when max_update is not null then max_update else max_create end" +
                           "        ,'HH24:MI') time_1  from (select min(created_date) min_create,max(created_date) max_create" +
                           "        ,min(updated_date) min_update,max(updated_date) max_update from cs_measurement_part " +
                           "        where measurement_time = 9 and measurement_date = main_cmp.measurement_date " +
                           "        and part_id = main_cmp.part_id and process_id = main_cmp.process_id and machine_id = :machine8) " +
                           "        time_data) time9," +
                           "    (select to_char(case when min_update is not null then min_update else min_create end" +
                           "        ,'HH24:MI')||' -'||to_char(case when max_update is not null then max_update else max_create end" +
                           "        ,'HH24:MI') time_1 from (select min(created_date) min_create,max(created_date) max_create" +
                           "        ,min(updated_date) min_update,max(updated_date) max_update from cs_measurement_part " +
                           "        where measurement_time = 10 and measurement_date = main_cmp.measurement_date " +
                           "        and part_id = main_cmp.part_id and process_id = main_cmp.process_id and machine_id = :machine9) " +
                           "        time_data) time10 " +
                           "    from cs_measurement_part main_cmp where measurement_date = :startDate " +
                           "            and part_id = :partId and process_id = :processId " +
                           "            group by part_id,measurement_date,process_id ");
                   query.addScalar("partId", new LongType());
                   query.addScalar("processId", new LongType());
                   query.addScalar("measurementDate", new DateType());
                   query.addScalar("time1", new StringType());
                   query.addScalar("time2", new StringType());
                   query.addScalar("time3", new StringType());
                   query.addScalar("time4", new StringType());
                   query.addScalar("time5", new StringType());
                   query.addScalar("time6", new StringType());
                   query.addScalar("time7", new StringType());
                   query.addScalar("time8", new StringType());
                   query.addScalar("time9", new StringType());
                   query.addScalar("time10", new StringType());
                   query.setDate("startDate", startDate);
                   query.setLong("partId", part.getId());
                   query.setLong("processId", process.getId());
                   for(int index = 0 ;index < 10 ;index++)
                   {
                       if(machineInput[index].getValue() != null && !machineInput[index].getValue().trim().isEmpty())
                       {
                            query.setLong("machine"+index, new Long(machineInput[index].getValue()));
                       }
                       else
                       {
                           query.setLong("machine"+index, new Long(0));
                       }
                   }                                  


                   query.setResultTransformer(Transformers.aliasToBean(MeasurementMinMaxTime.class));
                   query.setMaxResults(1);
                   MeasurementMinMaxTime time  = (MeasurementMinMaxTime)query.uniqueResult();
                   LOG.info(" time = "+time);
                   if(time != null)
                   {
                       LOG.info(" time = "+time.toString());
                        timeInput[0].setValue(time.getTime1());
                        timeInput[1].setValue(time.getTime2());
                        timeInput[2].setValue(time.getTime3());
                        timeInput[3].setValue(time.getTime4());
                        timeInput[4].setValue(time.getTime5());
                        timeInput[5].setValue(time.getTime6());
                        timeInput[6].setValue(time.getTime7());
                        timeInput[7].setValue(time.getTime8());
                        timeInput[8].setValue(time.getTime9());
                        timeInput[9].setValue(time.getTime10());
                   }
                  

            }
        }).process();
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

        String periodStr = formatPeriodStr.format(periodSearch);

        System.out.println("periodStr = " + periodStr);
        System.out.println("startDateStr = " + startDateStr);        
    }
}
