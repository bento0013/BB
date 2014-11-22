/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.stock;

import com.bsd.cse.app.backoffice.PartCore;
import com.bsd.cse.app.backoffice.SemiMaterialCore;
import com.bsd.cse.app.security.UserCore;
import com.bsd.cse.app.stock.SemiStockCore;
import com.bsd.cse.constant.PagingConstant;
import com.bsd.cse.constant.stock.MaterialTypeConstant;
import com.bsd.cse.constant.stock.StockTypeConstant;
import com.bsd.cse.constant.stock.TransferTypeConstant;
import com.bsd.cse.model.backoffice.material.semi.SemiMaterial;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.security.User;
import com.bsd.cse.model.stock.SemiStockTran;
import com.bsd.cse.model.stock.TransferType;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.grid.CriteriaObject;
import com.bsd.cse.zk.grid.GridSearchResult;
import com.bsd.cse.zk.grid.HibernateSearchObject;
import com.bsd.cse.zk.listbox.renderer.SemiIncomingRenderer;
import com.bsd.cse.zk.listbox.wrapper.ListboxListWrapper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.zkoss.util.ResourceUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.FieldComparator;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

/**
 *
 * @author bento
 */
public class SemiIncomingStockController extends SecurityController{
    private static final long serialVersionUID = -3993123706258278544L;
    private static Log LOG = LogFactory.getLog(SemiIncomingStockController.class);
    private final static Long REQUES_USER_PERMISSION = 1030503L;
    private Window editWin ;        
    private Button saveBtn;
    private Toolbarbutton newBtn;
    private Button cancelBtn;
    private Toolbarbutton searchBtn;   
    
    private Combobox transferTypeCbox;
    private Combobox transferTypeSearchCbox;
    private Combobox semiMaterialSearchCbox;
    private Combobox partCbox;
    private Combobox partSearchCbox;
    private Datebox startDatebox;
    private Datebox endDatebox;
    private Longbox quantityTxt;    
    private Combobox requesterCbox;
    private Label recorderLbl;
    private Label sizeLbl;
    private Label semiMaterialLbl;
    private Listbox contentListbox;                      
    private List<TransferType> transferTypes;
    private List<Part> parts;
    private List<SemiMaterial> semiMaterials;
    private List<User> requesters;
    private Listheader incomingTypeClm;
    private Listheader partClm;
    private Listheader semiMaterialClm;
    private Listheader sizeClm;
    private Listheader quantityClm;
    private Listheader requesterClm;
    private Listheader recorderClm;
    private Listheader createdDateClm;
    private Paging incomingPg;

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

        startDatebox.setValue(new Date());

        endDatebox.setValue(new Date());
        
        transferTypes = TransferTypeConstant.getIncomingTypes(MaterialTypeConstant.SEMI_TYPE_ID);

        PartCore partCore = new PartCore();
        parts = partCore.getAllList();
        ZKCatalogs.setParts(partCbox, parts,"partName-PartNo",null);
        
        SemiMaterialCore semiMaterialCore = new SemiMaterialCore();
        semiMaterials = semiMaterialCore.getAllList();
        
        UserCore userCore = new UserCore();
        requesters = userCore.getAllListWithPermission(REQUES_USER_PERMISSION);
        ZKCatalogs.setUsers(requesterCbox, requesters);
              
        ZKCatalogs.setTransferTypes(transferTypeCbox, transferTypes);
        

        ZKCatalogs.setTransferTypes(transferTypeSearchCbox, transferTypes);
        Comboitem transferTypeSearchItem = new Comboitem();
        transferTypeSearchItem.setLabel("All");
        List<Comboitem> transferTypeSearchItems =  transferTypeSearchCbox.getItems();
        transferTypeSearchItems.add(0, transferTypeSearchItem);
        transferTypeSearchCbox.setSelectedIndex(0);
        
        ZKCatalogs.setParts(partSearchCbox, parts,"partName-partNo",null);
        Comboitem partSearchItem = new Comboitem();
        partSearchItem.setLabel("All");
        List<Comboitem> partSearchItems =  partSearchCbox.getItems();
        partSearchItems.add(0, partSearchItem);
        partSearchCbox.setSelectedIndex(0);
        
        ZKCatalogs.setSemiMaterials(semiMaterialSearchCbox, semiMaterials,"rawName-rawType",null);
        Comboitem semiMaterialSearchItem = new Comboitem();
        semiMaterialSearchItem.setLabel("All");
        List<Comboitem> semiMaterialSearchItems =  semiMaterialSearchCbox.getItems();
        semiMaterialSearchItems.add(0, semiMaterialSearchItem);
        semiMaterialSearchCbox.setSelectedIndex(0);

        if( (parts == null || parts.size() == 0))
        {
            newBtn.setVisible(false);
        }
        
    }

    public void loadContent() throws Exception
    {
        setSortFieldGrid();
        Events.postEvent(Events.ON_CLICK,searchBtn,null);
    }

    private CriteriaObject createCriteria()
    {
        CriteriaObject criteriaObject = new CriteriaObject(SemiStockTran.class);
        criteriaObject.addAliasNames("transferType", "transferType",CriteriaObject.LEFT_JOIN);
        criteriaObject.addAliasNames("requester", "requester",CriteriaObject.LEFT_JOIN);
        criteriaObject.addAliasNames("recorder", "recorder",CriteriaObject.LEFT_JOIN);
        criteriaObject.addAliasNames("part", "part",CriteriaObject.LEFT_JOIN);
        criteriaObject.addAliasNames("part.semiMaterial", "semiMaterial",CriteriaObject.LEFT_JOIN);
        criteriaObject.addAliasNames("part.customer", "customer",CriteriaObject.LEFT_JOIN);
        if(partSearchCbox.getSelectedItem() != null)
        {
            Part part = (Part)partSearchCbox.getSelectedItem().getValue();
            if(part != null)
            {
                criteriaObject.addCriteria(Restrictions.eq("part.id", part.getId()));
            }
        }

        if(startDatebox.getValue() != null)
        {
            Calendar startCal = Calendar.getInstance();
            startCal.setTime(startDatebox.getValue());
            startCal.set(Calendar.HOUR_OF_DAY, 0);
            startCal.set(Calendar.MINUTE, 0);
            startCal.set(Calendar.SECOND, 0);
            startCal.set(Calendar.MILLISECOND, 0);
            LOG.info(" start date = "+startCal.getTime());
            criteriaObject.addCriteria(Restrictions.ge("createdDate",startCal.getTime()));
        }

        if(endDatebox.getValue() != null)
        {
            Calendar endCal = Calendar.getInstance();
            endCal.setTime(endDatebox.getValue());
            endCal.add(Calendar.DATE, 1);
            endCal.set(Calendar.HOUR_OF_DAY, 0);
            endCal.set(Calendar.MINUTE, 0);
            endCal.set(Calendar.SECOND, 0);
            endCal.set(Calendar.MILLISECOND, 0);
            criteriaObject.addCriteria(Restrictions.lt("createdDate",endCal.getTime()));
        }

        if(transferTypeSearchCbox.getSelectedItem() != null)
        {
            TransferType transferType = (TransferType)transferTypeSearchCbox.getSelectedItem().getValue();
            if(transferType != null)
            {
                criteriaObject.addCriteria(Restrictions.eq("transferType.id",transferType.getId()));
            }
        }

        if(semiMaterialSearchCbox.getSelectedItem() != null)
        {
            SemiMaterial semiMaterial = (SemiMaterial)semiMaterialSearchCbox.getSelectedItem().getValue();
            if(semiMaterial != null)
            {
                criteriaObject.addCriteria(Restrictions.eq("semiMaterial.id",semiMaterial.getId()));
            }
        }



        criteriaObject.addCriteria(Restrictions.eq("transferType.stockType.id",StockTypeConstant.INCOMING_TYPE_ID));

        criteriaObject.setOrderBy("createdDate");
        criteriaObject.setDefaultSort("createdDate");
        criteriaObject.setAscending(Boolean.FALSE);
        return criteriaObject;
    }

    public void addEventListener()
    {
        

        searchBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
//                Part part = (Part)partSearchCbox.getSelectedItem().getValue();
//                Date startDate  = startDatebox.getValue();
//                Date endDate  = endDatebox.getValue();
//                TransferType transferType = (TransferType)transferTypeSearchCbox.getSelectedItem().getValue();
//                SemiMaterial rawMaterial = (SemiMaterial)semiMaterialSearchCbox.getSelectedItem().getValue();
//                SemiStockCore core = new SemiStockCore();
//                HashMap<String,Object> metaData = core.getSemiIncomingTransactions(startDate, endDate,part, rawMaterial, transferType);
//                if(((Integer)metaData.get("allRecords")).equals(new Integer(0)))
//                {
//                    ArrayList<String> results = new ArrayList<String>();
//                    results.add(ResourceUtil.getLabel("stock.semimaterial.incoming.page.message.norecordfound"));
//                    contentListbox.setModel(new ListModelList((List<String>)results));
//                }
//                else
//                {
//                    contentListbox.setModel(new ListModelList((List<Part>)metaData.get("results")));
//
//                }
//
//                contentListbox.setItemRenderer(new SemiIncomingRenderer());
//
//
                incomingPg.setPageSize(PagingConstant.PAGING_SIZE);
                incomingPg.setDetailed(Boolean.TRUE);
                HibernateSearchObject<SemiStockTran> searchObject = new HibernateSearchObject<SemiStockTran>(createCriteria());
                GridSearchResult<SemiStockTran> result = searchObject.getSearchResult(0, incomingPg.getPageSize());
                if(result != null && result.getCount()>0)
                {
                    contentListbox.setModel(new ListboxListWrapper<SemiStockTran>(contentListbox, incomingPg, result, searchObject));
                }
                else
                {
                    List<String> noRecords = new ArrayList<String>();
                    noRecords.add(ResourceUtil.getLabel("stock.semimaterial.incoming.page.message.norecordfound"));
                    contentListbox.setModel(new ListboxListWrapper<SemiStockTran>(contentListbox, incomingPg, new ListModelList(noRecords), searchObject));
                }

                contentListbox.setItemRenderer(new SemiIncomingRenderer());
            }
        });

        partCbox.addEventListener(Events.ON_CHANGE, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {                                
                Part part = (Part)partCbox.getSelectedItem().getValue();
                semiMaterialLbl.setValue(part.getSemiMaterial().getSemiName()+"-"+part.getSemiMaterial().getSemiType().getName());
                sizeLbl.setValue(String.valueOf(part.getSemiMaterial().getSize()));
               
            }
        });


        newBtn.setVisible(Executions.getCurrent().isUserInRole("1030501"));
        newBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                HttpUserSession userSession = new HttpUserSession();                
                partCbox.setSelectedIndex(0);
                Part part = (Part)partCbox.getSelectedItem().getValue();
                semiMaterialLbl.setValue(part.getSemiMaterial().getSemiName()+"-"+part.getSemiMaterial().getSemiType().getName());
                transferTypeCbox.setSelectedIndex(0);
                requesterCbox.setSelectedIndex(0);
                quantityTxt.setValue(null);
                sizeLbl.setValue(String.valueOf(part.getSemiMaterial().getRawMaterial().getSize()));
                recorderLbl.setValue(userSession.getLoginId());
                editWin.doModal();
            }
        });

        saveBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public synchronized void onEvent(Event event) throws Exception {
                HttpUserSession userSession = new HttpUserSession();
                Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
                SemiStockTran tran = new SemiStockTran();
                
                Long quantity = quantityTxt.getValue();                

                if(quantity == null)
                {
                    AlertMessages.alertMessage("stock.semimaterial.incoming.page.alert.message.4");
                    return;
                }               

                if(partCbox.getSelectedItem() == null)
                {
                    AlertMessages.alertMessage("stock.semimaterial.incoming.page.alert.message.5");
                    return;
                }

                if(requesterCbox.getSelectedItem()  == null)
                {
                    AlertMessages.alertMessage("stock.semimaterial.incoming.page.alert.message.6");
                    return;
                }

                if(transferTypeCbox.getSelectedItem()  == null)
                {
                    AlertMessages.alertMessage("stock.semimaterial.incoming.page.alert.message.7");
                    return;
                }
                
                Part part = (Part)partCbox.getSelectedItem().getValue();
                TransferType transferType = (TransferType)transferTypeCbox.getSelectedItem().getValue();
                User requester = (User)requesterCbox.getSelectedItem().getValue();

                tran.setTransferType(transferType);
                tran.setPart(part);
                tran.setQuantity(quantity);
                tran.setRequester(requester);
                User recorder = new User();
                recorder.setId(userId);
                tran.setRecorder(recorder);
                

                
                SemiStockCore core = new SemiStockCore();
                try
                {

                    HashMap<String,Object> results = core.save(tran, userId);
                    Boolean result = (Boolean)results.get("results");
                    if(result != null && result)
                    {                        
                        editWin.setVisible(false);
                        AlertMessages.successMessage("stock.semimaterial.incoming.page.alert.message.1");
                        Events.sendEvent(Events.ON_CLICK, searchBtn, null);
                        event.stopPropagation();
                    }
                    else if(result != null && !result)
                    {
                        AlertMessages.failMessage("stock.semimaterial.incoming.page.alert.message.2");
                        return;
                    }
                }
                catch(Exception e)
                {
                    LOG.info(e.getMessage(),e);
                    if(e.toString().indexOf("ConstraintViolationException") != -1)
                    {
                        AlertMessages.failMessage("stock.semimaterial.incoming.page.alert.message.3");
                    }
                    else
                    {
                        AlertMessages.failMessage("stock.default.alert.message.1");
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
        transferTypeCbox = (Combobox)getComponent(editWin, "transferTypeCbox", transferTypeCbox);                                              
        partCbox = (Combobox)getComponent(editWin, "partCbox", partCbox);
        quantityTxt  = (Longbox)getComponent(editWin, "quantityTxt", quantityTxt);
        requesterCbox = (Combobox)getComponent(editWin, "requesterCbox", requesterCbox);
        recorderLbl = (Label)getComponent(editWin, "recorderLbl", recorderLbl);
        sizeLbl = (Label)getComponent(editWin, "sizeLbl", sizeLbl);
        semiMaterialLbl = (Label)getComponent(editWin, "semiMaterialLbl", semiMaterialLbl);
        partSearchCbox = (Combobox)getComponent(component, "partSearchCbox", partSearchCbox);
        semiMaterialSearchCbox= (Combobox)getComponent(component, "semiMaterialSearchCbox", semiMaterialSearchCbox);
        transferTypeSearchCbox = (Combobox)getComponent(component, "transferTypeSearchCbox", transferTypeSearchCbox);
        startDatebox = (Datebox)getComponent(component, "startDatebox", startDatebox);
        endDatebox = (Datebox)getComponent(component, "endDatebox", endDatebox);        
        contentListbox = (Listbox)getComponent(component, "contentListbox", contentListbox);
        searchBtn = (Toolbarbutton)getComponent(component, "searchBtn", searchBtn);
        cancelBtn = (Button)getComponent(editWin, "cancelBtn", cancelBtn);
        incomingPg = (Paging)getComponent(component, "incomingPg", incomingPg);
    }

    private void setSortFieldGrid()
    {
        incomingTypeClm.setSortAscending(new FieldComparator("transferType.transferTypeName", false));
        incomingTypeClm.setSortDescending(new FieldComparator("transferType.transferTypeName", true));
        partClm.setSortAscending(new FieldComparator("part.partName", false));
        partClm.setSortDescending(new FieldComparator("part.partName", true));
        semiMaterialClm.setSortAscending(new FieldComparator("semiMaterial.semiName", false));
        semiMaterialClm.setSortDescending(new FieldComparator("semiMaterial.semiName", true));
        sizeClm.setSortAscending(new FieldComparator("semiMaterial.size", false));
        sizeClm.setSortDescending(new FieldComparator("semiMaterial.size", true));
        quantityClm.setSortAscending(new FieldComparator("quantity", false));
        quantityClm.setSortDescending(new FieldComparator("quantity", true));
        requesterClm.setSortAscending(new FieldComparator("requester.username", false));
        requesterClm.setSortDescending(new FieldComparator("requester.username", true));
        recorderClm.setSortAscending(new FieldComparator("recorder.username", false));
        recorderClm.setSortDescending(new FieldComparator("recorder.username", true));
        createdDateClm.setSortAscending(new FieldComparator("createdDate", false));
        createdDateClm.setSortDescending(new FieldComparator("createdDate", true));


    }
}
