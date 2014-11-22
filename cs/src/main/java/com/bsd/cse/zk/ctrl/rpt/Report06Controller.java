/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.rpt;

import com.bsd.cse.app.backoffice.CustomerCore;
import com.bsd.cse.app.backoffice.MachineCore;
import com.bsd.cse.app.backoffice.PartCore;
import com.bsd.cse.app.security.TeamCore;
import com.bsd.cse.model.backoffice.customer.Customer;
import com.bsd.cse.model.backoffice.machine.Machine;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.security.Team;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.zkoss.zul.Datebox;

/**
 *
 * @author bento
 */
public class Report06Controller extends SecurityController{
    private static final long serialVersionUID = -4492739773980927088L;
    private Log LOG = LogFactory.getLog(Report06Controller.class);
    private Datebox datebox;
    private Combobox machineCbox;
    private Combobox teamCbox;
    private Button searchBtn;
    private Form rptForm;
    private Input requestByInput = new Input();    
    private Input machineInput = new Input();
    private Input teamInput = new Input();
    private Input customerInput = new Input();
    private Input startDateInput = new Input();    
    
    
    
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
        setMachine();
        setTeam();
        setForm();
    }

    private void setMachine() throws Exception
    {
//        Comboitem allItem = new Comboitem();
//        allItem.setValue(null);
//        allItem.setLabel("All");
        teamCbox.getChildren().clear();
        MachineCore core = new MachineCore();
        List<Machine> machines = core.getAllList();
        ZKCatalogs.setMachines(machineCbox, machines);
//        List items = partCbox.getChildren();
//        items.add(0,allItem);
        machineCbox.setSelectedIndex(0);
    }

    private void setTeam() throws Exception
    {
//        Comboitem allItem = new Comboitem();
//        allItem.setValue(null);
//        allItem.setLabel("All");
//        TeamCore core = new TeamCore();
        teamCbox.getChildren().clear();
        List<Team> teams = TeamCore.getAll();
        teams.remove(0);
        ZKCatalogs.setTeams(teamCbox, teams);
//        List items = partCbox.getChildren();
//        items.add(0,allItem);
        teamCbox.setSelectedIndex(0);
    }

  

    private void getDate()
    {
        Locale locale = (Locale)session.getAttribute(Attributes.PREFERRED_LOCALE);
        Calendar date = Calendar.getInstance(locale);
//        date.add(Calendar.DATE,-1);
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.MILLISECOND, 0);
        datebox.setValue(date.getTime());
    }
   
   

    public void setForm()
    {        
        startDateInput.setDynamicProperty("name", "startDate");        
        machineInput.setDynamicProperty("name","machineId");
        teamInput.setDynamicProperty("name","teamId");
        customerInput.setDynamicProperty("name","customerId");
        requestByInput.setDynamicProperty("name", "requestBy");        
        startDateInput.setVisible(false);        
        machineInput.setVisible(false);
        teamInput.setVisible(false);
        customerInput.setVisible(false);
        requestByInput.setVisible(false);                
        rptForm.appendChild(requestByInput);
        rptForm.appendChild(customerInput);
        rptForm.appendChild(machineInput);
        rptForm.appendChild(teamInput);
        rptForm.appendChild(startDateInput);        
    }
    

    public void addEventListener()
    {

        searchBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                Locale locale = (Locale)session.getAttribute(Attributes.PREFERRED_LOCALE);
                Date startDate = datebox.getValue();
                Date currentDate = new Date();
                if(!currentDate.after(startDate))
                {
                    AlertMessages.alertMessage("Date is not no future.");
                    return ;
                }


                Machine machine = (Machine)machineCbox.getSelectedItem().getValue();
                Team team = (Team)teamCbox.getSelectedItem().getValue();
//                if(part == null)
//                {
//                    AlertMessages.alertMessage("Part Number is required.");
//                    return ;
//                }
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd",Locale.US);                
                                        
                startDateInput.setValue(formatDate.format(startDate));               
                HttpUserSession userSession = new HttpUserSession();
                requestByInput.setValue((String)userSession.getUserInformation().get(Users.FULL_NAME));
                machineInput.setValue(machine!=null?String.valueOf(machine.getId()):"");
                teamInput.setValue(team!=null?String.valueOf(team.getId()):"");

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
