/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.backoffice.material.raw;


import com.bsd.cse.app.backoffice.RawMaterialCore;
import com.bsd.cse.app.backoffice.RawMaterialTypeCore;
import com.bsd.cse.app.security.FunctionCore;
import com.bsd.cse.constant.PagingConstant;
import com.bsd.cse.model.backoffice.material.raw.RawMaterial;
import com.bsd.cse.model.backoffice.material.raw.RawType;
import com.bsd.cse.model.security.Function;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.grid.CriteriaObject;
import com.bsd.cse.zk.grid.GridSearchResult;
import com.bsd.cse.zk.grid.HibernateSearchObject;
import com.bsd.cse.zk.listbox.renderer.RawMaterialRenderer;
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
public class RawMaterialController extends SecurityController{
    private static final long serialVersionUID = -3993123706258278544L;
    private static Log LOG = LogFactory.getLog(RawMaterialController.class);
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
    private Combobox rawTypeCbox;
    private Listbox contentListbox;        
    private Button addTypeBtn;
    private List<RawType> types = null;
    private Object tmpData;
    private static Function modelFunction;
    private Listheader nameClm;
    private Listheader typeClm;
    private Listheader sizeClm;
    private Listheader lengthClm;
    private Listheader miniClm;
    private Paging paging;

    static
    {
        try {
            modelFunction = FunctionCore.getFunction(1050502L);
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
        RawMaterialTypeCore core = new RawMaterialTypeCore();
        types = core.getAllList();
        Comboitem item = new Comboitem();
        item.setLabel("All");
        
        ZKCatalogs.setRawTypes(rawTypeCbox, types);
        List<Comboitem> items =  rawTypeCbox.getItems();
        items.add(0, item);
        rawTypeCbox.setSelectedIndex(0);
        ZKCatalogs.setRawTypes(typeCbox, types);
        
    }

    public void loadContent() throws Exception
    {
        setSortFieldGrid();
        Events.postEvent(Events.ON_CLICK,searchBtn,null);
    }

    private CriteriaObject createCriteria()
    {
        CriteriaObject criteriaObject = new CriteriaObject(RawMaterial.class);
        criteriaObject.addAliasNames("rawType", "rawType", CriteriaObject.INNER_JOIN);       

        if(!nameSearchTxt.getValue().trim().isEmpty())
        {
            criteriaObject.addCriteria(Restrictions.ilike("rawName", nameSearchTxt.getValue().trim(),MatchMode.START));
        }
        
        if(rawTypeCbox.getSelectedItem() != null)
        {
            RawType type = (RawType)(rawTypeCbox.getSelectedItem() != null?rawTypeCbox.getSelectedItem().getValue():null);
            if(type != null)
            {
                criteriaObject.addCriteria(Restrictions.ilike("rawType.name", rawTypeCbox.getName(),MatchMode.START));
            }
        }
        criteriaObject.setOrderBy("rawName");
        criteriaObject.setDefaultSort("rawName");
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
       

        searchBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
//                String name = nameSearchTxt.getValue();
//                RawType type = (RawType)(rawTypeCbox.getSelectedItem() != null?rawTypeCbox.getSelectedItem().getValue():null);
//                RawMaterialCore core = new RawMaterialCore();
//                HashMap<String,Object> metaData = core.getList(name,(type!=null?type.getId():null));
                paging.setPageSize(PagingConstant.PAGING_SIZE);
                paging.setDetailed(Boolean.TRUE);
                HibernateSearchObject<RawMaterial> searchObject = new HibernateSearchObject<RawMaterial>(createCriteria());
                GridSearchResult<RawMaterial> result = searchObject.getSearchResult(paging.getActivePage()*paging.getPageSize(), paging.getPageSize());
                if(result != null && result.getCount()>0)
                {
                    contentListbox.setModel(new ListboxListWrapper<RawMaterial>(contentListbox, paging, result, searchObject));
                }
                else
                {
                    List<String> noRecords = new ArrayList<String>();
                    noRecords.add(ResourceUtil.getLabel("backoffice.rawmaterial.message.norecordfound"));
                    contentListbox.setModel(new ListboxListWrapper<RawMaterial>(contentListbox, paging, new ListModelList(noRecords), searchObject));
                }

                contentListbox.setItemRenderer(new RawMaterialRenderer(editWin,idTxt,nameTxt,sizeTxt,typeCbox,searchBtn,lengthTxt,minimumStockTxt));

//                if(((Integer)metaData.get("allRecords")).equals(new Integer(0)))
//                {
//                    ArrayList<String> results = new ArrayList<String>();
//                    results.add(ResourceUtil.getLabel("backoffice.rawmaterial.message.norecordfound"));
//                    contentListbox.setModel(new ListModelList((List<String>)results));
//                }
//                else
//                {
//                    contentListbox.setModel(new ListModelList((List<RawMaterial>)metaData.get("results")));
//
//                }
//
//                contentListbox.setItemRenderer(new RawMaterialRenderer(editWin,idTxt,nameTxt,sizeTxt,typeCbox,searchBtn,lengthTxt,minimumStockTxt));
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
                editWin.doModal();
            }
        });

        saveBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                HttpUserSession userSession = new HttpUserSession();
                Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
                RawMaterial rawMaterial = new RawMaterial();
                String id = idTxt.getValue();
                String name = nameTxt.getValue();
                BigDecimal size = sizeTxt.getValue();
                BigDecimal length = lengthTxt.getValue();
                Long minimumStock = minimumStockTxt.getValue();

                if(name.trim().isEmpty())
                {
                    AlertMessages.alertMessage("backoffice.rawmaterial.alert.message.8");
                    return;
                }
                
                if(typeCbox.getSelectedItem() == null)
                {
                    AlertMessages.alertMessage("backoffice.rawmaterial.alert.message.9");
                    return;
                }

                if(size == null)
                {
                    AlertMessages.alertMessage("backoffice.rawmaterial.alert.message.10");
                    return;
                }

                if(length == null)
                {
                    AlertMessages.alertMessage("backoffice.rawmaterial.alert.message.11");
                    return;
                }

                if(minimumStock == null)
                {
                    AlertMessages.alertMessage("backoffice.rawmaterial.alert.message.12");
                    return;
                }

                RawType type = (RawType)typeCbox.getSelectedItem().getValue();
                
                if(!id.trim().isEmpty() &&!id.equals(""))
                {
                    rawMaterial.setId(Long.valueOf(id));
                }
                
                rawMaterial.setRawName(name);
                if(rawMaterial.getAmount() == null)
                {
                    rawMaterial.setAmount(0L);
                }
                rawMaterial.setLength(length);
                rawMaterial.setSize(size);
                rawMaterial.setMinimumStock(minimumStock);
                
                if(type != null)
                {
                    rawMaterial.setRawType(type);
                }                

                
                RawMaterialCore core = new RawMaterialCore();
                try
                {

                    HashMap<String,Object> results = core.save(rawMaterial, userId);
                    Boolean result = (Boolean)results.get("results");
                    if(result != null && result)
                    {                                               
                        editWin.setVisible(false);
                        AlertMessages.successMessage("backoffice.rawmaterial.alert.message.1");
                        Events.sendEvent(Events.ON_CLICK, searchBtn, null);
                        event.stopPropagation();
                    }
                    else if(result != null && !result)
                    {
                        AlertMessages.failMessage("backoffice.rawmaterial.alert.message.2");
                        return;
                    }
                }
                catch(Exception e)
                {
                    LOG.info(e.getMessage(),e);
                    if(e.toString().indexOf("ConstraintViolationException") != -1)
                    {
                        AlertMessages.failMessage("backoffice.rawmaterial.alert.message.3");
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
        rawTypeCbox = (Combobox)getComponent(component, "rawTypeCbox", rawTypeCbox);
        cancelBtn = (Button)getComponent(editWin, "cancelBtn", cancelBtn);
        nameTxt  = (Textbox)getComponent(editWin, "nameTxt", nameTxt);
        sizeTxt  = (Decimalbox)getComponent(editWin, "sizeTxt", sizeTxt);
        lengthTxt  = (Decimalbox)getComponent(editWin, "lengthTxt", lengthTxt);
        minimumStockTxt  = (Longbox)getComponent(editWin, "minimumStockTxt", minimumStockTxt);
        typeCbox  = (Combobox)getComponent(editWin, "typeCbox", typeCbox);
        idTxt  = (Textbox)getComponent(editWin, "idTxt", idTxt);
        contentListbox = (Listbox)getComponent(component, "contentListbox", contentListbox);
        searchBtn = (Toolbarbutton)getComponent(component, "searchBtn", searchBtn);
        addTypeBtn= (Button)getComponent(editWin, "addTypeBtn", addTypeBtn);
        nameClm= (Listheader)getComponent(component, "nameClm", nameClm);
        typeClm= (Listheader)getComponent(component, "typeClm", typeClm);
        sizeClm= (Listheader)getComponent(component, "sizeClm", sizeClm);
        lengthClm= (Listheader)getComponent(component, "lengthClm", lengthClm);
        miniClm= (Listheader)getComponent(component, "miniClm", miniClm);
    }

    private void setSortFieldGrid()
    {
        nameClm.setSortAscending(new FieldComparator("rawName", false));
        nameClm.setSortDescending(new FieldComparator("rawName", true));
        typeClm.setSortAscending(new FieldComparator("rawType.name", false));
        typeClm.setSortDescending(new FieldComparator("rawType.name", true));
        sizeClm.setSortAscending(new FieldComparator("size", false));
        sizeClm.setSortDescending(new FieldComparator("size", true));
        lengthClm.setSortAscending(new FieldComparator("length", false));
        lengthClm.setSortDescending(new FieldComparator("length", true));
        miniClm.setSortAscending(new FieldComparator("minimumStock", false));
        miniClm.setSortDescending(new FieldComparator("minimumStock", true));

    }
}
