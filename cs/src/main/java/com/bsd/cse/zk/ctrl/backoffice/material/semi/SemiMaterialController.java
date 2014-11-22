/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.backoffice.material.semi;


import com.bsd.cse.app.backoffice.RawMaterialCore;
import com.bsd.cse.app.backoffice.SemiMaterialCore;
import com.bsd.cse.app.backoffice.SemiMaterialTypeCore;
import com.bsd.cse.app.security.FunctionCore;
import com.bsd.cse.constant.PagingConstant;
import com.bsd.cse.model.backoffice.material.raw.RawMaterial;
import com.bsd.cse.model.backoffice.material.semi.SemiMaterial;
import com.bsd.cse.model.backoffice.material.semi.SemiType;
import com.bsd.cse.model.security.Function;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.grid.CriteriaObject;
import com.bsd.cse.zk.grid.GridSearchResult;
import com.bsd.cse.zk.grid.HibernateSearchObject;
import com.bsd.cse.zk.listbox.renderer.SemiMaterialRenderer;
import com.bsd.cse.zk.listbox.wrapper.ListboxListWrapper;
import java.io.IOException;
import java.math.BigDecimal;
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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.FieldComparator;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

/**
 *
 * @author bento
 */
public class SemiMaterialController extends SecurityController{
    private static final long serialVersionUID = -3993123706258278544L;
    private static Log LOG = LogFactory.getLog(SemiMaterialController.class);
    private Window editWin ;
    private Button saveBtn;
    private Toolbarbutton newBtn;
    private Button cancelBtn;
    private Toolbarbutton searchBtn;
    private Textbox nameSearchTxt;
    private Textbox idTxt;   
    private Textbox nameTxt;
    private Longbox minimumStockTxt;
    private Decimalbox lengthTxt;
    private Decimalbox sizeTxt;
    private Combobox typeCbox;
    private Combobox semiTypeCbox;

    private Combobox rawMaterialCbox;
    private Combobox rawMaterialEditCbox;
    private Listbox contentListbox;        
    private Button addTypeBtn;
    private Button addRawMaterialBtn;
    private List<SemiType> types = null;
    private List<RawMaterial> rawMaterials = null;
    private Object tmpData;
    private static Function modelFunction;
    private static Function rawModelFunction;
    private Listheader nameClm;
    private Listheader typeClm;
    private Listheader sizeClm;
    private Listheader lengthClm;
    private Listheader miniClm;
    private Listheader rawNameClm;
    private Paging paging;

    static
    {
        try {
            modelFunction = FunctionCore.getFunction(1050602L);
            rawModelFunction = FunctionCore.getFunction(1050501L);
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
        SemiMaterialTypeCore core = new SemiMaterialTypeCore();
        types = core.getAllList();
         RawMaterialCore rawCore = new RawMaterialCore();
        rawMaterials = rawCore.getAllList();
        Comboitem item = new Comboitem();
        item.setLabel(ResourceUtil.getLabel("backoffice.semimaterial.page.label.all"));
        Comboitem rawItem = new Comboitem();
        rawItem.setLabel(ResourceUtil.getLabel("backoffice.semimaterial.page.label.all"));
        Comboitem rawEditItem = new Comboitem();
        rawEditItem.setLabel(ResourceUtil.getLabel("backoffice.semimaterial.page.label.na"));
        
        ZKCatalogs.setSemiTypes(semiTypeCbox, types);
        List<Comboitem> items =  semiTypeCbox.getItems();
        items.add(0, item);
        semiTypeCbox.setSelectedIndex(0);
        ZKCatalogs.setSemiTypes(typeCbox, types);

        ZKCatalogs.setRawMaterials(rawMaterialCbox, rawMaterials,"rawName-rawType",null);
        List<Comboitem> rawItems =  rawMaterialCbox.getItems();
        rawItems.add(0, rawItem);
        rawMaterialCbox.setSelectedIndex(0);
        
        ZKCatalogs.setRawMaterials(rawMaterialEditCbox, rawMaterials,"rawName-rawType",null);
        List<Comboitem> rawEditItems =  rawMaterialEditCbox.getItems();
        rawEditItems.add(0, rawEditItem);
        rawMaterialEditCbox.setSelectedIndex(0);
        
    }

    public void loadContent() throws Exception
    {
        setSortFieldGrid();
        Events.postEvent(Events.ON_CLICK,searchBtn,null);
    }

    private CriteriaObject createCriteria()
    {
        CriteriaObject criteriaObject = new CriteriaObject(SemiMaterial.class);
        criteriaObject.addAliasNames("semiType", "semiType", CriteriaObject.INNER_JOIN);
        criteriaObject.addAliasNames("rawMaterial", "rawMaterial", CriteriaObject.LEFT_JOIN);

        if(!nameSearchTxt.getValue().trim().isEmpty())
        {
            criteriaObject.addCriteria(Restrictions.ilike("semiName", nameSearchTxt.getValue().trim(),MatchMode.START));
        }
        if(semiTypeCbox.getSelectedItem() != null)
        {
            SemiType type = (SemiType)(semiTypeCbox.getSelectedItem() != null?semiTypeCbox.getSelectedItem().getValue():null);
            if(type != null)
            {
                criteriaObject.addCriteria(Restrictions.ilike("semiType.name", type.getName(),MatchMode.START));
            }
        }

        if(rawMaterialCbox.getSelectedItem() != null)
        {
            RawMaterial rawMaterial = (RawMaterial)(rawMaterialCbox.getSelectedItem() != null?rawMaterialCbox.getSelectedItem().getValue():null);
            if(rawMaterial != null)
            {
                criteriaObject.addCriteria(Restrictions.ilike("rawMaterial.rawName", rawMaterial.getRawName(),MatchMode.START));
            }
        }
        criteriaObject.setOrderBy("semiName");
        criteriaObject.setDefaultSort("semiName");
        criteriaObject.setAscending(Boolean.TRUE);
        return criteriaObject;
    }

    public void addEventListener()
    {
        

        addTypeBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                
                execution.sendRedirect(modelFunction.getCommand());
            }
        });

        addRawMaterialBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {

                execution.sendRedirect(rawModelFunction.getCommand());
            }
        });
       

        searchBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
//                String name = nameSearchTxt.getValue();
//                SemiType type = (SemiType)(semiTypeCbox.getSelectedItem() != null?semiTypeCbox.getSelectedItem().getValue():null);
//                RawMaterial rawMaterial = (RawMaterial)(rawMaterialCbox.getSelectedItem() != null?rawMaterialCbox.getSelectedItem().getValue():null);
//                SemiMaterialCore core = new SemiMaterialCore();
//                HashMap<String,Object> metaData = core.getList(name,(type!=null?type.getId():null),(rawMaterial!=null?rawMaterial.getId():null));
                paging.setPageSize(PagingConstant.PAGING_SIZE);
                paging.setDetailed(Boolean.TRUE);
                HibernateSearchObject<SemiMaterial> searchObject = new HibernateSearchObject<SemiMaterial>(createCriteria());
                GridSearchResult<SemiMaterial> result = searchObject.getSearchResult(paging.getActivePage()*paging.getPageSize(), paging.getPageSize());
                if(result != null && result.getCount()>0)
                {
                    contentListbox.setModel(new ListboxListWrapper<SemiMaterial>(contentListbox, paging, result, searchObject));
                }
                else
                {
                    List<String> noRecords = new ArrayList<String>();
                    noRecords.add(ResourceUtil.getLabel("backoffice.semimaterial.message.norecordfound"));
                    contentListbox.setModel(new ListboxListWrapper<SemiMaterial>(contentListbox, paging, new ListModelList(noRecords), searchObject));
                }

                contentListbox.setItemRenderer(new SemiMaterialRenderer(editWin,idTxt,nameTxt,sizeTxt,typeCbox,searchBtn,lengthTxt,minimumStockTxt,rawMaterialEditCbox));
//                if(((Integer)metaData.get("allRecords")).equals(new Integer(0)))
//                {
//                    ArrayList<String> results = new ArrayList<String>();
//                    results.add(ResourceUtil.getLabel("backoffice.semimaterial.message.norecordfound"));
//                    contentListbox.setModel(new ListModelList((List<String>)results));
//                }
//                else
//                {
//                    contentListbox.setModel(new ListModelList((List<RawMaterial>)metaData.get("results")));
//
//                }
//
//                contentListbox.setItemRenderer(new SemiMaterialRenderer(editWin,idTxt,nameTxt,sizeTxt,typeCbox,searchBtn,lengthTxt,minimumStockTxt,rawMaterialEditCbox));
            }
        });

        newBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                
                idTxt.setValue("");
                nameTxt.setValue("");
                lengthTxt.setValue((BigDecimal)null);
                minimumStockTxt.setValue(null);
                sizeTxt.setValue((BigDecimal)null);
                typeCbox.setValue("");
                rawMaterialEditCbox.setValue(ResourceUtil.getLabel("backoffice.semimaterial.page.label.na"));
                
                editWin.doModal();                                            
            }
        });

        saveBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                HttpUserSession userSession = new HttpUserSession();
                Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
                SemiMaterial semiMaterial = new SemiMaterial();
                String id = idTxt.getValue();
                String name = nameTxt.getValue();
                BigDecimal size = sizeTxt.getValue();
                BigDecimal length = lengthTxt.getValue();
                Long minimumStock = minimumStockTxt.getValue();

                if(name.trim().isEmpty())
                {
                    AlertMessages.alertMessage("backoffice.semimaterial.alert.message.8");
                    return;
                }

                if(typeCbox.getSelectedItem() == null)
                {
                    AlertMessages.alertMessage("backoffice.semimaterial.alert.message.9");
                    return;
                }

                if(size == null)
                {
                    AlertMessages.alertMessage("backoffice.semimaterial.alert.message.10");
                    return;
                }

                if(length == null)
                {
                    AlertMessages.alertMessage("backoffice.semimaterial.alert.message.11");
                    return;
                }

                if(minimumStock == null)
                {
                    AlertMessages.alertMessage("backoffice.semimaterial.alert.message.12");
                    return;
                }

                

//                if(rawMaterialEditCbox.getSelectedItem() == null)
//                {
//                    AlertMessages.alertMessage("backoffice.semimaterial.alert.message.13");
//                    return;
//                }

                SemiType type = (SemiType)typeCbox.getSelectedItem().getValue();
                RawMaterial rawMaterial = (rawMaterialEditCbox.getSelectedItem() == null?(RawMaterial)null:(RawMaterial)rawMaterialEditCbox.getSelectedItem().getValue());
                
                if(!id.trim().isEmpty() &&!id.equals(""))
                {
                    semiMaterial.setId(Long.valueOf(id));
                }

                
                semiMaterial.setSemiName(name);
                if(semiMaterial.getAmount() == null)
                {
                    semiMaterial.setAmount(0L);
                }
                semiMaterial.setLength(length);
                semiMaterial.setSize(size);
                semiMaterial.setMinimumStock(minimumStock);
                
                if(type != null)
                {
                    semiMaterial.setSemiType(type);
                }

//                if(rawMaterial != null)
//                {
                    semiMaterial.setRawMaterial(rawMaterial);
//                } 

                
                SemiMaterialCore core = new SemiMaterialCore();
                try
                {

                    HashMap<String,Object> results = core.save(semiMaterial, userId);
                    Boolean result = (Boolean)results.get("results");
                    if(result != null && result)
                    {                                               
                        editWin.setVisible(false);
                        AlertMessages.successMessage("backoffice.semimaterial.alert.message.1");
                        Events.sendEvent(Events.ON_CLICK, searchBtn, null);
                        event.stopPropagation();
                    }
                    else if(result != null && !result)
                    {
                        AlertMessages.failMessage("backoffice.semimaterial.alert.message.2");
                        return;
                    }
                }
                catch(Exception e)
                {
                    LOG.info(e.getMessage(),e);
                    if(e.toString().indexOf("ConstraintViolationException") != -1)
                    {
                        AlertMessages.failMessage("backoffice.semimaterial.alert.message.3");
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
        editWin = (Window)getComponent(component, "editWin", editWin);
        newBtn = (Toolbarbutton)getComponent(component, "newBtn", newBtn);
        saveBtn = (Button)getComponent(editWin, "saveBtn", saveBtn);
        nameSearchTxt  = (Textbox)getComponent(component, "nameSearchTxt", nameSearchTxt);
        semiTypeCbox = (Combobox)getComponent(component, "semiTypeCbox", semiTypeCbox);
        rawMaterialCbox = (Combobox)getComponent(component, "rawMaterialCbox", rawMaterialCbox);
        cancelBtn = (Button)getComponent(editWin, "cancelBtn", cancelBtn);
        nameTxt  = (Textbox)getComponent(editWin, "nameTxt", nameTxt);
        sizeTxt  = (Decimalbox)getComponent(editWin, "sizeTxt", sizeTxt);
        lengthTxt  = (Decimalbox)getComponent(editWin, "lengthTxt", lengthTxt);
        minimumStockTxt  = (Longbox)getComponent(editWin, "minimumStockTxt", minimumStockTxt);
        typeCbox  = (Combobox)getComponent(editWin, "typeCbox", typeCbox);
        rawMaterialEditCbox  = (Combobox)getComponent(editWin, "rawMaterialEditCbox", rawMaterialEditCbox);
        idTxt  = (Textbox)getComponent(editWin, "idTxt", idTxt);
        contentListbox = (Listbox)getComponent(component, "contentListbox", contentListbox);
        searchBtn = (Toolbarbutton)getComponent(component, "searchBtn", searchBtn);
        addTypeBtn= (Button)getComponent(editWin, "addTypeBtn", addTypeBtn);
        addRawMaterialBtn= (Button)getComponent(editWin, "addRawMaterialBtn", addRawMaterialBtn);
        nameClm= (Listheader)getComponent(component, "nameClm", nameClm);
        typeClm= (Listheader)getComponent(component, "typeClm", typeClm);
        sizeClm= (Listheader)getComponent(component, "sizeClm", sizeClm);
        lengthClm= (Listheader)getComponent(component, "lengthClm", lengthClm);
        miniClm= (Listheader)getComponent(component, "miniClm", miniClm);
        rawNameClm= (Listheader)getComponent(component, "rawNameClm", rawNameClm);
    }

    private void setSortFieldGrid()
    {
        nameClm.setSortAscending(new FieldComparator("semiName", false));
        nameClm.setSortDescending(new FieldComparator("semiName", true));
        typeClm.setSortAscending(new FieldComparator("semiType.name", false));
        typeClm.setSortDescending(new FieldComparator("semiType.name", true));
        sizeClm.setSortAscending(new FieldComparator("size", false));
        sizeClm.setSortDescending(new FieldComparator("size", true));
        lengthClm.setSortAscending(new FieldComparator("length", false));
        lengthClm.setSortDescending(new FieldComparator("length", true));
        miniClm.setSortAscending(new FieldComparator("minimumStock", false));
        miniClm.setSortDescending(new FieldComparator("minimumStock", true));
        rawNameClm.setSortAscending(new FieldComparator("rawMaterial.rawName", false));
        rawNameClm.setSortDescending(new FieldComparator("rawMaterial.rawName", true));
    }
}
