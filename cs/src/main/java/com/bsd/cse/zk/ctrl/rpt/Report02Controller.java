/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.rpt;

import com.bsd.cse.app.backoffice.CustomerCore;
import com.bsd.cse.app.backoffice.PartCore;
import com.bsd.cse.model.backoffice.customer.Customer;
import com.bsd.cse.model.backoffice.part.Part;
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
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;

/**
 *
 * @author bento
 */
public class Report02Controller extends SecurityController{
    private static final long serialVersionUID = -4492739773980927088L;
    private Log LOG = LogFactory.getLog(Report02Controller.class);
    private Datebox startDatebox;
    private Datebox endDatebox;
    private Combobox partCbox;
    private Combobox customerCbox;
    private Button searchBtn;
    private Form rptForm;
    private Input requestByInput = new Input();
    private Input startDateStrInput = new Input();
    private Input endDateStrInput = new Input();
    private Input partInput = new Input();
    private Input customerInput = new Input();
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
        setCustomer();
        setForm();
    }

    private void setPart() throws Exception
    {
        Comboitem allItem = new Comboitem();
        allItem.setValue(null);
        allItem.setLabel("All");
        PartCore core = new PartCore();
        List<Part> parts = core.getAllList();
        ZKCatalogs.setParts(partCbox, parts);
        List items = partCbox.getChildren();
        items.add(0,allItem);   
        partCbox.setSelectedIndex(0);
    }

    private void setCustomer() throws Exception
    {
        Comboitem allItem = new Comboitem();
        allItem.setValue(null);
        allItem.setLabel("All");
        CustomerCore core = new CustomerCore();
        List<Customer> customers = core.getAllList();
        ZKCatalogs.setCustomers(customerCbox, customers);
        List items = customerCbox.getChildren();
        items.add(0,allItem);
        customerCbox.setSelectedIndex(0);
    }

    private void getDate()
    {
        Locale locale = (Locale)session.getAttribute(Attributes.PREFERRED_LOCALE);
        Calendar date = Calendar.getInstance(locale);
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.MILLISECOND, 0);
        startDatebox.setValue(date.getTime());
        endDatebox.setValue(date.getTime());
    }
   
   

    public void setForm()
    {        
        startDateInput.setDynamicProperty("name", "startDate");
        endDateInput.setDynamicProperty("name", "endDate");
        startDateStrInput.setDynamicProperty("name", "startDateStr");
        endDateStrInput.setDynamicProperty("name", "endDateStr");
        partInput.setDynamicProperty("name","partId");
        customerInput.setDynamicProperty("name","customerId");
        requestByInput.setDynamicProperty("name", "requestBy");
        startDateStrInput.setVisible(false);
        endDateStrInput.setVisible(false);
        startDateInput.setVisible(false);
        endDateInput.setVisible(false);
        partInput.setVisible(false);
        customerInput.setVisible(false);
        requestByInput.setVisible(false);        
        rptForm.appendChild(startDateStrInput);
        rptForm.appendChild(endDateStrInput);
        rptForm.appendChild(requestByInput);
        rptForm.appendChild(customerInput);
        rptForm.appendChild(partInput);
        rptForm.appendChild(startDateInput);
        rptForm.appendChild(endDateInput);
    }
    

    public void addEventListener()
    {

        searchBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                Locale locale = (Locale)session.getAttribute(Attributes.PREFERRED_LOCALE);
                Date startDate = startDatebox.getValue();
                Date endDate = endDatebox.getValue();
                Part part = (Part)partCbox.getSelectedItem().getValue();
                Customer customer = (Customer)customerCbox.getSelectedItem().getValue();
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
                SimpleDateFormat formatDateStr = new SimpleDateFormat("dd/MM/yyyy",Locale.US);


                if(endDate.before(startDate))
                {
                    AlertMessages.alertMessage("report02.page.alert.message1");
                    return;
                }
                                
                String startDateStr = formatDateStr.format(startDate);
                String endDateStr = formatDateStr.format(endDate);
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
                customerInput.setValue(customer!=null?String.valueOf(customer.getId()):"");

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
