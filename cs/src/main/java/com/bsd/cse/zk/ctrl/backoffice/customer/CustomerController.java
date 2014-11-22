/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.backoffice.customer;

import com.bsd.cse.app.backoffice.CustomerCore;
import com.bsd.cse.app.backoffice.DepartmentCore;
import com.bsd.cse.constant.PagingConstant;
import com.bsd.cse.model.backoffice.customer.Customer;
import com.bsd.cse.model.backoffice.department.Department;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.grid.CriteriaObject;
import com.bsd.cse.zk.grid.GridSearchResult;
import com.bsd.cse.zk.grid.HibernateSearchObject;
import com.bsd.cse.zk.listbox.renderer.CustomerRenderer;
import com.bsd.cse.zk.listbox.renderer.DepartmentRenderer;
import com.bsd.cse.zk.listbox.wrapper.ListboxListWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.zkoss.util.ResourceUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.FieldComparator;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

/**
 *
 * @author bento
 */
public class CustomerController extends SecurityController{
    private static final long serialVersionUID = -1565787268144841987L;
    private Log LOG = LogFactory.getLog(CustomerController.class);

    private Window editWin ;
    private Button saveBtn;
    private Toolbarbutton newBtn;
    private Button cancelBtn;
    private Toolbarbutton searchBtn;
    private Textbox idTxt;
    private Textbox nameSearchTxt;
    private Textbox nameTxt;
    private Textbox surnameTxt;
    private Textbox addressTxt;
    private Textbox telTxt;
    private Listbox contentListbox;
    private Paging paging;
    private Listheader nameClm;
    private Listheader surnameClm;
    private Listheader addressClm;
    private Listheader telClm;
    
    
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
        setSortFieldGrid();
        Events.postEvent(Events.ON_CLICK,searchBtn,null);
    }

    private CriteriaObject createCriteria()
    {
        CriteriaObject criteriaObject = new CriteriaObject(Customer.class);
        if(!nameSearchTxt.getValue().trim().isEmpty())
        {
            criteriaObject.addCriteria(Restrictions.ilike("name", nameSearchTxt.getValue().trim(),MatchMode.START));
        }
        
        criteriaObject.setOrderBy("name");
        criteriaObject.setDefaultSort("name");
        criteriaObject.setAscending(Boolean.TRUE);
        return criteriaObject;
    }

    public void addEventListener()
    {

        searchBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
//                String cusName = nameSearchTxt.getValue();
//                CustomerCore core = new CustomerCore();
//                HashMap<String,Object> metaData = core.getList(cusName);
                paging.setPageSize(PagingConstant.PAGING_SIZE);
                paging.setDetailed(Boolean.TRUE);
                HibernateSearchObject<Customer> searchObject = new HibernateSearchObject<Customer>(createCriteria());
                GridSearchResult<Customer> result = searchObject.getSearchResult(0, paging.getPageSize());
                if(result != null && result.getCount()>0)
                {
                    contentListbox.setModel(new ListboxListWrapper<Customer>(contentListbox, paging, result, searchObject));
                }
                else
                {
                    List<String> noRecords = new ArrayList<String>();
                    noRecords.add(ResourceUtil.getLabel("backoffice.machine.message.norecordfound"));
                    contentListbox.setModel(new ListboxListWrapper<Customer>(contentListbox, paging, new ListModelList(noRecords), searchObject));
                }

                contentListbox.setItemRenderer(new CustomerRenderer(editWin,idTxt,nameTxt,surnameTxt,addressTxt,telTxt,searchBtn));
//                if(((Integer)metaData.get("allRecords")).equals(new Integer(0)))
//                {
//                    ArrayList<String> results = new ArrayList<String>();
//                    results.add(ResourceUtil.getLabel("backoffice.customer.message.norecordfound"));
//                    contentListbox.setModel(new ListModelList((List<String>)results));
//                }
//                else
//                {
//                    contentListbox.setModel(new ListModelList((List<Department>)metaData.get("results")));
//
//                }
//
//                contentListbox.setItemRenderer(new CustomerRenderer(editWin,idTxt,nameTxt,surnameTxt,addressTxt,telTxt,searchBtn));
            }
        });

        newBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {                
                idTxt.setValue("");
                nameTxt.setValue("");
                surnameTxt.setValue("");
                addressTxt.setValue("");
                telTxt.setValue("");
                editWin.doModal();
            }
        });

        saveBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                HttpUserSession userSession = new HttpUserSession();
                Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
                Customer customer = new Customer();
                String id = idTxt.getValue();
                String name = nameTxt.getValue();
                String address = addressTxt.getValue();
                String tel = telTxt.getValue();
                String surname = surnameTxt.getValue();

                if(name.trim().isEmpty())
                {
                    AlertMessages.alertMessage("backoffice.customer.alert.message.8");
                    return;
                }

//                if(surname.trim().isEmpty())
//                {
//                    AlertMessages.alertMessage("backoffice.customer.alert.message.9");
//                    return;
//                }

                if(!id.trim().isEmpty() &&!id.equals(""))
                {
                    customer.setId(Long.valueOf(id));
                }

                customer.setName(name);
                customer.setSurname(surname);
                customer.setAddress(address);
                customer.setTel(tel);

                CustomerCore core = new CustomerCore();
                try
                {
                    HashMap<String,Object> results = core.save(customer, userId);
                    Boolean result = (Boolean)results.get("results");
                    if(result != null && result)
                    {
                        editWin.setVisible(false);
                        AlertMessages.successMessage("backoffice.customer.alert.message.1");
                        Events.sendEvent(Events.ON_CLICK, searchBtn, null);
                        event.stopPropagation();
                    }
                    else if(result != null && !result)
                    {
                        AlertMessages.failMessage("backoffice.customer.alert.message.2");
                        return;
                    }
                }
                catch(Exception e)
                {
                    if(e.toString().indexOf("ConstraintViolationException") != -1)
                    {
                        AlertMessages.failMessage("backoffice.customer.alert.message.3");
                    }
                    else
                    {
                        AlertMessages.failMessage("Unexpect Error,Please contact Administrator.");
                    }
                }
            }
        });

        cancelBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                editWin.setVisible(false);
                event.stopPropagation();

            }
        });
    }

    public void createComponent(Component component)
    {
        editWin = (Window)getComponent(component, "editWin", editWin);
        newBtn = (Toolbarbutton)getComponent(component, "newBtn", newBtn);
        saveBtn = (Button)getComponent(editWin, "saveBtn", saveBtn);
        nameSearchTxt = (Textbox)getComponent(component, "nameSearchTxt", nameSearchTxt);
        cancelBtn = (Button)getComponent(editWin, "cancelBtn", cancelBtn);
        idTxt  = (Textbox)getComponent(editWin, "idTxt", idTxt);
        nameTxt  = (Textbox)getComponent(editWin, "nameTxt", nameTxt);
        surnameTxt  = (Textbox)getComponent(editWin, "surnameTxt", surnameTxt);
        addressTxt  = (Textbox)getComponent(editWin, "addressTxt", addressTxt);
        telTxt  = (Textbox)getComponent(editWin, "telTxt", telTxt);
        contentListbox = (Listbox)getComponent(component, "contentListbox", contentListbox);
        searchBtn = (Toolbarbutton)getComponent(component, "searchBtn", searchBtn);
    }

    private void setSortFieldGrid()
    {
        nameClm.setSortAscending(new FieldComparator("name", false));
        nameClm.setSortDescending(new FieldComparator("name", true));
        surnameClm.setSortAscending(new FieldComparator("surname", false));
        surnameClm.setSortDescending(new FieldComparator("surname", true));
        addressClm.setSortAscending(new FieldComparator("address", false));
        addressClm.setSortDescending(new FieldComparator("address", true));
        telClm.setSortAscending(new FieldComparator("tel", false));
        telClm.setSortDescending(new FieldComparator("tel", true));
    }
}
