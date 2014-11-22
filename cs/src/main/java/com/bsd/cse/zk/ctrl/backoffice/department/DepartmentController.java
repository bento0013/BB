/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.backoffice.department;

import com.bsd.cse.app.backoffice.DepartmentCore;
import com.bsd.cse.constant.PagingConstant;
import com.bsd.cse.model.backoffice.department.Department;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.grid.CriteriaObject;
import com.bsd.cse.zk.grid.GridSearchResult;
import com.bsd.cse.zk.grid.HibernateSearchObject;
import com.bsd.cse.zk.listbox.renderer.DepartmentRenderer;
import com.bsd.cse.zk.listbox.wrapper.ListboxListWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
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
public class DepartmentController extends SecurityController{
    private static final long serialVersionUID = -1565787268144841987L;
    private Log LOG = LogFactory.getLog(DepartmentController.class);

    private Window editWin ;
    private Button saveBtn;
    private Toolbarbutton newBtn;
    private Button cancelBtn;
    private Toolbarbutton searchBtn;
    private Textbox idTxt;
    private Textbox departmentSearchNameTxt;
    private Textbox nameTxt;
    private Listbox contentListbox;
    private Paging departmentPg;    
    private Listheader departmentNameClm;
    
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
        CriteriaObject criteriaObject = new CriteriaObject(Department.class);        
        if(!departmentSearchNameTxt.getValue().trim().isEmpty())
        {
            criteriaObject.addCriteria(Restrictions.ilike("name", departmentSearchNameTxt.getValue().trim(),MatchMode.START));
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
//                String depName = departmentSearchNameTxt.getValue();
//                DepartmentCore core = new DepartmentCore();
//                HashMap<String,Object> metaData = core.getList(depName);

                departmentPg.setPageSize(PagingConstant.PAGING_SIZE);
                departmentPg.setDetailed(Boolean.TRUE);
                HibernateSearchObject<Department> searchObject = new HibernateSearchObject<Department>(createCriteria());
                GridSearchResult<Department> result = searchObject.getSearchResult(departmentPg.getActivePage()*departmentPg.getPageSize(), departmentPg.getPageSize());
                if(result != null && result.getCount()>0)
                {
                    contentListbox.setModel(new ListboxListWrapper<Department>(contentListbox, departmentPg, result, searchObject));
                }
                else
                {
                    List<String> noRecords = new ArrayList<String>();
                    noRecords.add("No Record Found.");
                    contentListbox.setModel(new ListboxListWrapper<Department>(contentListbox, departmentPg, new ListModelList(noRecords), searchObject));
                }

                contentListbox.setItemRenderer(new DepartmentRenderer(editWin,idTxt,nameTxt,searchBtn));
            }
        });

        newBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {                
                idTxt.setValue("");
                nameTxt.setValue("");
                editWin.doModal();
            }
        });

        saveBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                HttpUserSession userSession = new HttpUserSession();
                Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
                Department department = new Department();
                String id = idTxt.getValue();
                String name = nameTxt.getValue();
                if(!id.trim().isEmpty() &&!id.equals(""))
                {
                    department.setId(Long.valueOf(id));
                }

                if(name.trim().isEmpty())
                {
                    AlertMessages.alertMessage("backoffice.department.alert.message.8");
                    return;
                }

                department.setName(name);
                DepartmentCore core = new DepartmentCore();
                try
                {
                    HashMap<String,Object> results = core.save(department, userId);
                    Boolean result = (Boolean)results.get("results");
                    if(result != null && result)
                    {
                        editWin.setVisible(false);
                        AlertMessages.successMessage("backoffice.department.alert.message.1");
                        Events.sendEvent(Events.ON_CLICK, searchBtn, null);
                        event.stopPropagation();
                    }
                    else if(result != null && !result)
                    {
                        AlertMessages.failMessage("backoffice.department.alert.message.2");
                        return;
                    }
                }
                catch(Exception e)
                {
                    if(e.toString().indexOf("ConstraintViolationException") != -1)
                    {
                        AlertMessages.failMessage("backoffice.department.alert.message.3");
                    }
                    else
                    {
                        AlertMessages.failMessage("backoffice.default.alert.message.1");
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
        departmentPg = (Paging)getComponent(component, "departmentPg", departmentPg);        
        departmentNameClm = (Listheader)getComponent(component, "departmentNameClm", departmentNameClm);
        editWin = (Window)getComponent(component, "editWin", editWin);
        newBtn = (Toolbarbutton)getComponent(component, "newBtn", newBtn);
        saveBtn = (Button)getComponent(editWin, "saveBtn", saveBtn);
        departmentSearchNameTxt = (Textbox)getComponent(component, "departmentSearchNameTxt", departmentSearchNameTxt);
        cancelBtn = (Button)getComponent(editWin, "cancelBtn", cancelBtn);
        nameTxt  = (Textbox)getComponent(editWin, "nameTxt", nameTxt);
        idTxt  = (Textbox)getComponent(editWin, "idTxt", idTxt);
        contentListbox = (Listbox)getComponent(component, "contentListbox", contentListbox);
        searchBtn = (Toolbarbutton)getComponent(component, "searchBtn", searchBtn);
    }

    private void setSortFieldGrid()
    {
        departmentNameClm.setSortAscending(new FieldComparator("name", false));
        departmentNameClm.setSortDescending(new FieldComparator("name", true));
    }
}
