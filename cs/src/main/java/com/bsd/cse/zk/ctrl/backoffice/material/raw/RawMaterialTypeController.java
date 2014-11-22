/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.backoffice.material.raw;

import com.bsd.cse.app.backoffice.RawMaterialTypeCore;
import com.bsd.cse.constant.PagingConstant;
import com.bsd.cse.model.backoffice.material.raw.RawType;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.ctrl.backoffice.machine.*;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.grid.CriteriaObject;
import com.bsd.cse.zk.grid.GridSearchResult;
import com.bsd.cse.zk.grid.HibernateSearchObject;
import com.bsd.cse.zk.listbox.renderer.RawMaterialTypeRenderer;
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
public class RawMaterialTypeController extends SecurityController{
    private static final long serialVersionUID = -3993123706258278544L;
    private Log LOG = LogFactory.getLog(MachineModelTypeController.class);

    private Window editWin ;
    private Button saveBtn;
    private Toolbarbutton newBtn;
    private Button cancelBtn;
    private Toolbarbutton searchBtn;
    private Textbox idTxt;
    private Textbox typeSearchNameTxt;
    private Textbox nameTxt;
    private Textbox descTxt;
    private Listbox contentListbox;
    private Paging paging;
    private Listheader nameClm;
    private Listheader descClm;
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
        CriteriaObject criteriaObject = new CriteriaObject(RawType.class);
        if(!typeSearchNameTxt.getValue().trim().isEmpty())
        {
            criteriaObject.addCriteria(Restrictions.ilike("name", typeSearchNameTxt.getValue().trim(),MatchMode.START));
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
//                String typeName = typeSearchNameTxt.getValue();
//                RawMaterialTypeCore core = new RawMaterialTypeCore();
//                HashMap<String,Object> metaData = core.getList(typeName);
                paging.setPageSize(PagingConstant.PAGING_SIZE);
                paging.setDetailed(Boolean.TRUE);
                HibernateSearchObject<RawType> searchObject = new HibernateSearchObject<RawType>(createCriteria());
                GridSearchResult<RawType> result = searchObject.getSearchResult(paging.getActivePage()*paging.getPageSize(), paging.getPageSize());
                if(result != null && result.getCount()>0)
                {
                    contentListbox.setModel(new ListboxListWrapper<RawType>(contentListbox, paging, result, searchObject));
                }
                else
                {
                    List<String> noRecords = new ArrayList<String>();
                    noRecords.add(ResourceUtil.getLabel("backoffice.rawmaterialtype.message.norecordfound"));
                    contentListbox.setModel(new ListboxListWrapper<RawType>(contentListbox, paging, new ListModelList(noRecords), searchObject));
                }

                contentListbox.setItemRenderer(new RawMaterialTypeRenderer(editWin,idTxt,nameTxt,descTxt,searchBtn));
//                if(((Integer)metaData.get("allRecords")).equals(new Integer(0)))
//                {
//                    ArrayList<String> results = new ArrayList<String>();
//                    results.add(ResourceUtil.getLabel("backoffice.rawmaterialtype.message.norecordfound"));
//                    contentListbox.setModel(new ListModelList((List<String>)results));
//                }
//                else
//                {
//                    contentListbox.setModel(new ListModelList((List<RawType>)metaData.get("results")));
//
//                }
//
//                contentListbox.setItemRenderer(new RawMaterialTypeRenderer(editWin,idTxt,nameTxt,descTxt,searchBtn));
            }
        });

        newBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                idTxt.setValue("");
                nameTxt.setValue("");
                descTxt.setValue("");
                editWin.doModal();
            }
        });

        saveBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                HttpUserSession userSession = new HttpUserSession();
                Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
                RawType rawType = new RawType();
                String id = idTxt.getValue();
                String name = nameTxt.getValue();
                String desc = descTxt.getValue();

                if(name.trim().isEmpty())
                {
                    AlertMessages.alertMessage("backoffice.rawmaterialtype.alert.message.8");
                    return;
                }

                if(!id.trim().isEmpty() &&!id.equals(""))
                {
                    rawType.setId(Long.valueOf(id));
                }



                
                rawType.setName(name);
                rawType.setDescription(desc);
                RawMaterialTypeCore core = new RawMaterialTypeCore();
                try
                {
                    HashMap<String,Object> results = core.save(rawType, userId);
                    Boolean result = (Boolean)results.get("results");
                    if(result != null && result)
                    {
                        editWin.setVisible(false);
                        AlertMessages.successMessage("backoffice.rawmaterialtype.alert.message.1");
                        Events.sendEvent(Events.ON_CLICK, searchBtn, null);
                        event.stopPropagation();
                    }
                    else if(result != null && !result)
                    {
                        AlertMessages.failMessage("backoffice.rawmaterialtype.alert.message.2");
                        return;
                    }
                }
                catch(Exception e)
                {
                    if(e.toString().indexOf("ConstraintViolationException") != -1)
                    {
                        AlertMessages.failMessage("backoffice.rawmaterialtype.alert.message.3");
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
        nameClm = (Listheader)getComponent(component, "nameClm", nameClm);
        descClm = (Listheader)getComponent(component, "descClm", descClm);
        editWin = (Window)getComponent(component, "editWin", editWin);
        newBtn = (Toolbarbutton)getComponent(component, "newBtn", newBtn);
        saveBtn = (Button)getComponent(editWin, "saveBtn", saveBtn);
        typeSearchNameTxt = (Textbox)getComponent(component, "typeSearchNameTxt", typeSearchNameTxt);
        cancelBtn = (Button)getComponent(editWin, "cancelBtn", cancelBtn);
        nameTxt  = (Textbox)getComponent(editWin, "nameTxt", nameTxt);
        descTxt  = (Textbox)getComponent(editWin, "descTxt", descTxt);
        idTxt  = (Textbox)getComponent(editWin, "idTxt", idTxt);
        contentListbox = (Listbox)getComponent(component, "contentListbox", contentListbox);
        searchBtn = (Toolbarbutton)getComponent(component, "searchBtn", searchBtn);
    }

    private void setSortFieldGrid()
    {
        nameClm.setSortAscending(new FieldComparator("name", false));
        nameClm.setSortDescending(new FieldComparator("name", true));
        descClm.setSortAscending(new FieldComparator("description", false));
        descClm.setSortDescending(new FieldComparator("description", true));
    }
}
