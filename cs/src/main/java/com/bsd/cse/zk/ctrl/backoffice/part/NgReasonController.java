/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.backoffice.part;

import com.bsd.cse.app.backoffice.NgReasonCore;
import com.bsd.cse.constant.PagingConstant;
import com.bsd.cse.model.backoffice.part.NgReason;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.grid.CriteriaObject;
import com.bsd.cse.zk.grid.GridSearchResult;
import com.bsd.cse.zk.grid.HibernateSearchObject;
import com.bsd.cse.zk.listbox.renderer.NgReasonRenderer;
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
import org.zkoss.zul.Column;
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
public class NgReasonController extends SecurityController{
    private static final long serialVersionUID = -1565787268144841987L;
    private Log LOG = LogFactory.getLog(NgReasonController.class);

    private Window editWin ;
    private Button saveBtn;
    private Toolbarbutton newBtn;
    private Button cancelBtn;
    private Toolbarbutton searchBtn;
    private Textbox idTxt;
    private Textbox ngReasonSearchNameTxt;
    private Textbox nameTxt;
    private Listbox contentListbox;
    private Paging ngReasonPg;
    private Listheader ngReasonNameClm;
    
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
        CriteriaObject criteriaObject = new CriteriaObject(NgReason.class);
        if(!ngReasonSearchNameTxt.getValue().trim().isEmpty())
        {
            criteriaObject.addCriteria(Restrictions.ilike("name", ngReasonSearchNameTxt.getValue().trim(),MatchMode.START));
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
//                String depName = ngReasonSearchNameTxt.getValue();
//                NgReasonCore core = new NgReasonCore();
//                HashMap<String,Object> metaData = core.getList(depName);

                ngReasonPg.setPageSize(PagingConstant.PAGING_SIZE);
                ngReasonPg.setDetailed(Boolean.TRUE);
                HibernateSearchObject<NgReason> searchObject = new HibernateSearchObject<NgReason>(createCriteria());
                GridSearchResult<NgReason> result = searchObject.getSearchResult(ngReasonPg.getActivePage()*ngReasonPg.getPageSize(), ngReasonPg.getPageSize());
                if(result != null && result.getCount()>0)
                {
                    contentListbox.setModel(new ListboxListWrapper<NgReason>(contentListbox, ngReasonPg, result, searchObject));
                }
                else
                {
                    List<String> noRecords = new ArrayList<String>();
                    noRecords.add("No Record Found.");
                    contentListbox.setModel(new ListboxListWrapper<NgReason>(contentListbox, ngReasonPg, new ListModelList(noRecords), searchObject));
                }

                contentListbox.setItemRenderer(new NgReasonRenderer(editWin,idTxt,nameTxt,searchBtn));
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
                NgReason ngReason = new NgReason();
                String id = idTxt.getValue();
                String name = nameTxt.getValue();
                if(!id.trim().isEmpty() &&!id.equals(""))
                {
                    ngReason.setId(Long.valueOf(id));
                }

                if(name.trim().isEmpty())
                {
                    AlertMessages.alertMessage("backoffice.ngreason.alert.message.8");
                    return;
                }

                ngReason.setName(name);
                NgReasonCore core = new NgReasonCore();
                try
                {
                    HashMap<String,Object> results = core.save(ngReason, userId);
                    Boolean result = (Boolean)results.get("results");
                    if(result != null && result)
                    {
                        editWin.setVisible(false);
                        AlertMessages.successMessage("backoffice.ngreason.alert.message.1");
                        Events.sendEvent(Events.ON_CLICK, searchBtn, null);
                        event.stopPropagation();
                    }
                    else if(result != null && !result)
                    {
                        AlertMessages.failMessage("backoffice.ngreason.alert.message.2");
                        return;
                    }
                }
                catch(Exception e)
                {
                    if(e.toString().indexOf("ConstraintViolationException") != -1)
                    {
                        AlertMessages.failMessage("backoffice.ngreason.alert.message.3");
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
        ngReasonPg = (Paging)getComponent(component, "ngReasonPg", ngReasonPg);
        ngReasonNameClm = (Listheader)getComponent(component, "ngReasonNameClm", ngReasonNameClm);
        editWin = (Window)getComponent(component, "editWin", editWin);
        newBtn = (Toolbarbutton)getComponent(component, "newBtn", newBtn);
        saveBtn = (Button)getComponent(editWin, "saveBtn", saveBtn);
        ngReasonSearchNameTxt = (Textbox)getComponent(component, "ngReasonSearchNameTxt", ngReasonSearchNameTxt);
        cancelBtn = (Button)getComponent(editWin, "cancelBtn", cancelBtn);
        nameTxt  = (Textbox)getComponent(editWin, "nameTxt", nameTxt);
        idTxt  = (Textbox)getComponent(editWin, "idTxt", idTxt);
        contentListbox = (Listbox)getComponent(component, "contentListbox", contentListbox);
        searchBtn = (Toolbarbutton)getComponent(component, "searchBtn", searchBtn);
    }

    private void setSortFieldGrid()
    {
        ngReasonNameClm.setSortAscending(new FieldComparator("name", false));
        ngReasonNameClm.setSortDescending(new FieldComparator("name", true));
    }
}
