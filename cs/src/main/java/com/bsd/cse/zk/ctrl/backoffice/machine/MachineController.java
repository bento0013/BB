/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.backoffice.machine;

import com.bsd.cse.app.backoffice.MachineCore;
import com.bsd.cse.app.backoffice.MachineModelCore;
import com.bsd.cse.app.security.FunctionCore;
import com.bsd.cse.constant.PagingConstant;
import com.bsd.cse.model.backoffice.machine.Machine;
import com.bsd.cse.model.backoffice.machine.MachineModel;
import com.bsd.cse.model.security.Function;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.grid.CriteriaObject;
import com.bsd.cse.zk.grid.GridSearchResult;
import com.bsd.cse.zk.grid.HibernateSearchObject;
import com.bsd.cse.zk.listbox.renderer.MachineRenderer;
import com.bsd.cse.zk.listbox.wrapper.ListboxListWrapper;
import java.io.IOException;
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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
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
public class MachineController extends SecurityController{
    private static final long serialVersionUID = -3993123706258278544L;
    private static Log LOG = LogFactory.getLog(MachineController.class);
    private Window editWin ;
    private Button saveBtn;
    private Toolbarbutton newBtn;
    private Button cancelBtn;
    private Toolbarbutton searchBtn;
    private Textbox nameSearchTxt;
    private Textbox idTxt;   
    private Textbox nameTxt;   
    private Combobox modelCbox;
    private Combobox machineModelCbox;
    private Listbox contentListbox; 
    private Button addModelBtn;
    private List<MachineModel> types = null;
    private Object tmpData;
    private static Function modelTypeFunction;
    private Paging paging;
    private Listheader nameClm;
    private Listheader modelClm;

    static
    {
        try {
            modelTypeFunction = FunctionCore.getFunction(1050303L);                
        } catch (IOException ex) {
            LOG.error(ex.getMessage(),ex);
        }
        catch (Exception ex) {
            LOG.error(ex.getMessage(),ex);
        }
    }

     @Override
    public void doAfterCompose(Component component) throws Exception
    {
        super.doAfterCompose(component);
        createComponent(component);
        addEventListener();
        initialData();
        loadContent();

    }

    public void initialData() throws Exception
    {
        MachineModelCore core = new MachineModelCore();
        types = core.getAllList();
        Comboitem item = new Comboitem();
        item.setLabel("All");
        
        ZKCatalogs.setMachineModels(machineModelCbox, types);
        List<Comboitem> items =  machineModelCbox.getItems();
        items.add(0, item);
        machineModelCbox.setSelectedIndex(0);
        ZKCatalogs.setMachineModels(modelCbox, types);
        
    }

    public void loadContent() throws Exception
    {
        setSortFieldGrid();
        Events.postEvent(Events.ON_CLICK,searchBtn,null);
    }

    private CriteriaObject createCriteria()
    {
        CriteriaObject criteriaObject = new CriteriaObject(Machine.class);
        criteriaObject.addAliasNames("model", "model", CriteriaObject.INNER_JOIN);
        if(!nameSearchTxt.getValue().trim().isEmpty())
        {
            criteriaObject.addCriteria(Restrictions.ilike("name", nameSearchTxt.getValue().trim(),MatchMode.START));
        }
        if(machineModelCbox.getSelectedItem() != null)
        {
            MachineModel model = (MachineModel)(machineModelCbox.getSelectedItem() != null?machineModelCbox.getSelectedItem().getValue():null);
            if(model != null)
            {
                criteriaObject.addCriteria(Restrictions.ilike("model.name", model.getName(),MatchMode.START));
            }
        }
        criteriaObject.setOrderBy("name");
        criteriaObject.setDefaultSort("name");
        criteriaObject.setAscending(Boolean.TRUE);
        return criteriaObject;
    }

    public void addEventListener()
    {
        

        addModelBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                
                execution.sendRedirect(modelTypeFunction.getCommand());
            }
        });
        
        searchBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
//                String machineName = nameSearchTxt.getValue();
//                MachineModel model = (MachineModel)(machineModelCbox.getSelectedItem() != null?machineModelCbox.getSelectedItem().getValue():null);
//                MachineCore core = new MachineCore();
//                HashMap<String,Object> metaData = core.getList(machineName,(model!=null?model.getId():null));
                paging.setPageSize(PagingConstant.PAGING_SIZE);
                paging.setDetailed(Boolean.TRUE);
                HibernateSearchObject<Machine> searchObject = new HibernateSearchObject<Machine>(createCriteria());
                GridSearchResult<Machine> result = searchObject.getSearchResult(paging.getActivePage()*paging.getPageSize(), paging.getPageSize());
                if(result != null && result.getCount()>0)
                {
                    contentListbox.setModel(new ListboxListWrapper<Machine>(contentListbox, paging, result, searchObject));
                }
                else
                {
                    List<String> noRecords = new ArrayList<String>();
                    noRecords.add(ResourceUtil.getLabel("backoffice.machine.message.norecordfound"));
                    contentListbox.setModel(new ListboxListWrapper<Machine>(contentListbox, paging, new ListModelList(noRecords), searchObject));
                }

                contentListbox.setItemRenderer(new MachineRenderer(editWin,idTxt,nameTxt,modelCbox,searchBtn));
//                if(((Integer)metaData.get("allRecords")).equals(new Integer(0)))
//                {
//                    ArrayList<String> results = new ArrayList<String>();
//                    results.add(ResourceUtil.getLabel("backoffice.machine.message.norecordfound"));
//                    contentListbox.setModel(new ListModelList((List<String>)results));
//                }
//                else
//                {
//                    contentListbox.setModel(new ListModelList((List<Machine>)metaData.get("results")));
//
//                }
//
//                contentListbox.setItemRenderer(new MachineRenderer(editWin,idTxt,nameTxt,modelCbox,searchBtn));
            }
        });

        newBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {                
                idTxt.setValue("");
                nameTxt.setValue("");                                
                modelCbox.setValue("");
                editWin.doModal();
            }
        });

        saveBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                HttpUserSession userSession = new HttpUserSession();
                Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
                Machine machine = new Machine();
                String id = idTxt.getValue();
                String name = nameTxt.getValue();
                if(name.trim().isEmpty())
                {
                     AlertMessages.alertMessage("backoffice.machine.alert.message.8");
                    return;
                }

                if(modelCbox.getSelectedItem() == null)
                {
                    AlertMessages.alertMessage("backoffice.machine.alert.message.9");
                    return;
                }


                MachineModel modelType = (MachineModel)modelCbox.getSelectedItem().getValue();
                if(!id.trim().isEmpty() &&!id.equals(""))
                {
                    machine.setId(Long.valueOf(id));
                }
                machine.setName(name);
                if(modelType != null)
                {
                    machine.setModel(modelType);
                }            
                
                MachineCore core = new MachineCore();
                try
                {
                    HashMap<String,Object> results = core.save(machine, userId);
                    Boolean result = (Boolean)results.get("results");
                    if(result != null && result)
                    {                                    
                        editWin.setVisible(false);
                        AlertMessages.successMessage("backoffice.machine.alert.message.1");
                        Events.sendEvent(Events.ON_CLICK, searchBtn, null);
                        event.stopPropagation();
                    }
                    else if(result != null && !result)
                    {
                        AlertMessages.failMessage("backoffice.machine.alert.message.2");
                        return;
                    }
                }
                catch(Exception e)
                {
                    if(e.toString().indexOf("ConstraintViolationException") != -1)
                    {
                        AlertMessages.failMessage("backoffice.machine.alert.message.3");
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
        machineModelCbox = (Combobox)getComponent(component, "machineModelCbox", machineModelCbox);
        cancelBtn = (Button)getComponent(editWin, "cancelBtn", cancelBtn);
        nameTxt  = (Textbox)getComponent(editWin, "nameTxt", nameTxt);
        modelCbox  = (Combobox)getComponent(editWin, "modelCbox", modelCbox);        
        idTxt  = (Textbox)getComponent(editWin, "idTxt", idTxt);
        contentListbox = (Listbox)getComponent(component, "contentListbox", contentListbox);
        searchBtn = (Toolbarbutton)getComponent(component, "searchBtn", searchBtn);        
        addModelBtn= (Button)getComponent(editWin, "addModelBtn", addModelBtn);
    }

    private void setSortFieldGrid()
    {
        nameClm.setSortAscending(new FieldComparator("name", false));
        nameClm.setSortDescending(new FieldComparator("name", true));
        modelClm.setSortAscending(new FieldComparator("model.name", false));
        modelClm.setSortDescending(new FieldComparator("model.name", true));
    }
}
