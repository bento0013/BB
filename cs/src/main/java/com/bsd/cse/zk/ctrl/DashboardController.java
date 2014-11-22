/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl;

import com.bsd.cse.app.DashboardCore;
import com.bsd.cse.app.backoffice.PartCore;
import com.bsd.cse.constant.PagingConstant;
import com.bsd.cse.model.backoffice.material.semi.SemiMaterial;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.dsb.DashboardPartSetting;
import com.bsd.cse.model.dsb.MonitorPartData;
import com.bsd.cse.model.dsb.OEEDashboardData;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.grid.CriteriaObject;
import com.bsd.cse.zk.grid.GridSearchResult;
import com.bsd.cse.zk.grid.HibernateSearchObject;
import com.bsd.cse.zk.grid.QueryObject;
import com.bsd.cse.zk.grid.renderer.MonitorPartDataRenderer;
import com.bsd.cse.zk.listbox.renderer.MaterialDashboardRenderer;
import com.bsd.cse.zk.listbox.wrapper.ListboxListWrapper;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import org.zkoss.zul.Cell;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

/**
 *
 * @author bento
 */
public class DashboardController extends SecurityController{
    private static final long serialVersionUID = -1565787268144841987L;
    private Log LOG = LogFactory.getLog(DashboardController.class);

    private Window editWin ;    
    private Toolbarbutton settingBtn;
    private Button cancelBtn;
    private Button saveBtn;
    private Label allAvailabilityLbl;
    private Label allPerformanceLbl;
    private Label allQualityLbl;
    private Label allOverallLbl;
    private Label aAvailabilityLbl;
    private Label aPerformanceLbl;
    private Label aQualityLbl;
    private Label aOverallLbl;
    private Label bAvailabilityLbl;
    private Label bPerformanceLbl;
    private Label bQualityLbl;
    private Label bOverallLbl;
    private Grid monitorGrid;
    private Listbox materialListbox;
    private Timer timer;
    private Paging materialPaging;
    private Button addPartListBtn;
    private Combobox partEditCbox;
    private Grid settingGrid;
    private final static DecimalFormat numberFormat = new DecimalFormat("#,##0.00");
    private List<Part> partThreads = new ArrayList<Part>();
    
    
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
        Events.postEvent(Events.ON_TIMER,timer,null);
    }

    private CriteriaObject createSemiCriteria()
    {
        CriteriaObject criteriaObject = new CriteriaObject(SemiMaterial.class);        
//        criteriaObject.addCriteria(Restrictions.ltProperty("amount","minimumStock"));
        criteriaObject.setOrderBy("amount - minimumStock");
        criteriaObject.setDefaultSort("semiName");
        criteriaObject.setAscending(Boolean.TRUE);
        return criteriaObject;
    }

    private QueryObject createSemiCriteriaQuery()
    {
        QueryObject criteriaObject = new QueryObject(SemiMaterial.class," from SemiMaterial ");
//        criteriaObject.addCriteria(Restrictions.ltProperty("amount","minimumStock"));
        criteriaObject.setOrderBy("case when amount < minimumStock then 0 else 1 end asc,semiName ");
        criteriaObject.setAscending(Boolean.TRUE);
        return criteriaObject;
    }

    private void loadSettingConfig() throws Exception
    {
        partEditCbox.getChildren().clear();
        PartCore partCore = new PartCore();
        partThreads.clear();
        partThreads.addAll(partCore.getAllList());
        settingGrid.getRows().getChildren().clear();

        DashboardCore dashboardCore = new DashboardCore();
        List<DashboardPartSetting> records = dashboardCore.getPartSetting();
        for(DashboardPartSetting record : records)
        {
            Part part = new Part();
            part.setId(record.getPart().getId());
            partThreads.remove(part);
            Row row = new Row();
            row.setValue(record);
            Cell cell = new Cell();
            Label label = new Label(String.valueOf(record.getPart().getPartName()+"-"+record.getPart().getPartNo()));
            cell.appendChild(label);
            row.appendChild(cell);
            row.setValue(record);

            cell = new Cell();
            Toolbarbutton deleteBtn = new Toolbarbutton();
            deleteBtn.setImage("/images/backoffice/delete.png");
            deleteBtn.setTooltiptext(ResourceUtil.getLabel("dashboard.page.btn.delete"));
            deleteBtn.addEventListener(Events.ON_CLICK, new DeleteObjectEventListener());
            deleteBtn.setAttribute("object", row);
            deleteBtn.setParent(cell);
            cell.setStyle("vertical-align:middle");
            row.appendChild(cell);
            row.setValue(record);
            settingGrid.getRows().getChildren().add(row);
            row.setId(String.valueOf(settingGrid.getRows().getChildren().size()));
//            partEditCbox.setSelectedIndex(0);
        }

        
        Comboitem item = new Comboitem();
        item.setLabel("N/A");        
        ZKCatalogs.setParts(partEditCbox, partThreads,"partName-partNo", new Long(0));
        List<Comboitem> items =  partEditCbox.getItems();
        items.add(0, item);
        partEditCbox.setSelectedIndex(0);

    }

    public void addEventListener()
    {


        settingBtn.setVisible(Executions.getCurrent().isUserInRole("101"));
        timer.setDelay(60000*10);
        timer.addEventListener(Events.ON_TIMER, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                
                materialPaging.setPageSize(PagingConstant.PAGING_SIZE);
                materialPaging.setDetailed(Boolean.TRUE);
                HibernateSearchObject<SemiMaterial> searchObject = new HibernateSearchObject<SemiMaterial>(createSemiCriteriaQuery());
                GridSearchResult<SemiMaterial> result = searchObject.getSearchResult(0, materialPaging.getPageSize());
                if(result != null && result.getCount()>0)
                {
                    materialListbox.setModel(new ListboxListWrapper<SemiMaterial>(materialListbox, materialPaging, result, searchObject));
                }
                else
                {
                    List<String> noRecords = new ArrayList<String>();
                    noRecords.add(ResourceUtil.getLabel("dashboard.message.norecordfound"));
                    materialListbox.setModel(new ListboxListWrapper<SemiMaterial>(materialListbox, materialPaging, new ListModelList(noRecords), searchObject));
                }

                materialListbox.setItemRenderer(new MaterialDashboardRenderer());


                DashboardCore core = new DashboardCore();
                List<MonitorPartData> partDatas = core.getMonitoringPartData();
                if(partDatas != null && partDatas.size()>0)
                {
                    monitorGrid.setModel(new ListModelList(partDatas));
                }
                else
                {
                    List<String> noRecords = new ArrayList<String>();
                    noRecords.add(ResourceUtil.getLabel("dashboard.message.norecordfound"));
                    monitorGrid.setModel(new ListModelList(noRecords));
                }
                

                monitorGrid.setRowRenderer(new MonitorPartDataRenderer());

                List<OEEDashboardData> oeeDatas = core.getOEEDashboardData();
                if(oeeDatas.size() > 0)
                {
                    OEEDashboardData data = oeeDatas.get(0);
                    allAvailabilityLbl.setValue("Availability "+numberFormat.format(data.getAvailability())+"%");
                    allPerformanceLbl.setValue("Performance "+numberFormat.format(data.getPerformance())+"%");
                    allQualityLbl.setValue("Quality "+numberFormat.format(data.getQuality())+"%");
                    allOverallLbl.setValue("Overall "+numberFormat.format(data.getOverall())+"%");
                }   

                if(oeeDatas.size() > 1)
                {
                    OEEDashboardData data = oeeDatas.get(1);
                    aAvailabilityLbl.setValue("Availability "+numberFormat.format(data.getAvailability())+"%");
                    aPerformanceLbl.setValue("Performance "+numberFormat.format(data.getPerformance())+"%");
                    aQualityLbl.setValue("Quality "+numberFormat.format(data.getQuality())+"%");
                    aOverallLbl.setValue("Overall "+numberFormat.format(data.getOverall())+"%");
                }

                if(oeeDatas.size() > 2)
                {
                    OEEDashboardData data = oeeDatas.get(2);
                    bAvailabilityLbl.setValue("Availability "+numberFormat.format(data.getAvailability())+"%");
                    bPerformanceLbl.setValue("Performance "+numberFormat.format(data.getPerformance())+"%");
                    bQualityLbl.setValue("Quality "+numberFormat.format(data.getQuality())+"%");
                    bOverallLbl.setValue("Overall "+numberFormat.format(data.getOverall())+"%");
                }
                

            }
        });
//        searchBtn.addEventListener(Events.ON_CLICK, new EventListener() {
//
//            @Override
//            public void onEvent(Event event) throws Exception {
////                String cusName = nameSearchTxt.getValue();
////                CustomerCore core = new CustomerCore();
////                HashMap<String,Object> metaData = core.getList(cusName);
//                paging.setPageSize(PagingConstant.PAGING_SIZE);
//                paging.setDetailed(Boolean.TRUE);
//                HibernateSearchObject<Customer> searchObject = new HibernateSearchObject<Customer>(createCriteria());
//                GridSearchResult<Customer> result = searchObject.getSearchResult(0, paging.getPageSize());
//                if(result != null && result.getCount()>0)
//                {
//                    contentListbox.setModel(new ListboxListWrapper<Customer>(contentListbox, paging, result, searchObject));
//                }
//                else
//                {
//                    List<String> noRecords = new ArrayList<String>();
//                    noRecords.add("backoffice.machine.message.norecordfound");
//                    contentListbox.setModel(new ListboxListWrapper<Customer>(contentListbox, paging, new ListModelList(noRecords), searchObject));
//                }
//
//                contentListbox.setItemRenderer(new CustomerRenderer(editWin,idTxt,nameTxt,surnameTxt,addressTxt,telTxt,searchBtn));
////                if(((Integer)metaData.get("allRecords")).equals(new Integer(0)))
////                {
////                    ArrayList<String> results = new ArrayList<String>();
////                    results.add(ResourceUtil.getLabel("backoffice.customer.message.norecordfound"));
////                    contentListbox.setModel(new ListModelList((List<String>)results));
////                }
////                else
////                {
////                    contentListbox.setModel(new ListModelList((List<Department>)metaData.get("results")));
////
////                }
////
////                contentListbox.setItemRenderer(new CustomerRenderer(editWin,idTxt,nameTxt,surnameTxt,addressTxt,telTxt,searchBtn));
//            }
//        });
//
        settingBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                loadSettingConfig();
                editWin.doModal();
            }
        });

        addPartListBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {

                
                Part part = (Part)partEditCbox.getSelectedItem().getValue();
                
                if(part == null)
                {
                    AlertMessages.alertMessage("dashboard.alert.message.required.1");
                    return ;
                }

                partEditCbox.removeItemAt(partEditCbox.getSelectedIndex());

                partThreads.remove(part);

                DashboardPartSetting record = new DashboardPartSetting();
                record.setPart(part);

                Row row = new Row();
                row.setValue(record);
                Cell cell = new Cell();
                Label label = new Label(String.valueOf(record.getPart().getPartName()+"-"+record.getPart().getPartNo()));
                cell.appendChild(label);
                row.appendChild(cell);
                row.setValue(record);            

                cell = new Cell();
                Toolbarbutton deleteBtn = new Toolbarbutton();
                deleteBtn.setImage("/images/backoffice/delete.png");
                deleteBtn.setTooltiptext(ResourceUtil.getLabel("dashboard.page.btn.delete"));
                deleteBtn.addEventListener(Events.ON_CLICK, new DeleteObjectEventListener());
                deleteBtn.setAttribute("object", row);
                deleteBtn.setParent(cell);
                cell.setStyle("vertical-align:middle");
                row.appendChild(cell);
                settingGrid.getRows().getChildren().add(row);
//                row.setId(String.valueOf(settingGrid.getRows().getChildren().size()));
                row.setValue(record);
                partEditCbox.setSelectedIndex(0);
            }
        });
//
        saveBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                HttpUserSession userSession = new HttpUserSession();
                Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
                if(!(AlertMessages.confirmMessage("dashboard.alert.message.required.2") == AlertMessages.YES))
                {
                    return ;
                }

                List<Row> rows = settingGrid.getRows().getChildren();
                List<DashboardPartSetting> records = new ArrayList<DashboardPartSetting>();
                for(Row row : rows)
                {
                    DashboardPartSetting record = (DashboardPartSetting)row.getValue();
                    records.add(record);                    
                }

                DashboardCore core = new DashboardCore();
                try
                {
                    HashMap<String,Object> results = core.saveSetting(records, userId);
                    Boolean result = (Boolean)results.get("results");
                    if(result != null && result)
                    {
                        editWin.setVisible(false);
                        AlertMessages.successMessage("dashboard.alert.message.1");
                        Events.sendEvent(Events.ON_TIMER,timer , null);
                        event.stopPropagation();
                    }
                    else if(result != null && !result)
                    {
                        AlertMessages.failMessage("dashboard.alert.message.2");
                        return;
                    }
                }
                catch(Exception e)
                {
                    if(e.toString().indexOf("ConstraintViolationException") != -1)
                    {
                        AlertMessages.failMessage("dashboard.alert.message.3");
                    }
                    else
                    {
                        AlertMessages.failMessage("dashboard.alert.message.4");
                    }
                }
            }
        });
//
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
        settingBtn = (Toolbarbutton)getComponent(component, "settingBtn", settingBtn);
        saveBtn = (Button)getComponent(editWin, "saveBtn", saveBtn);
        settingGrid = (Grid)getComponent(editWin, "settingGrid", settingGrid);
        cancelBtn = (Button)getComponent(editWin, "cancelBtn", cancelBtn);
        partEditCbox  = (Combobox)getComponent(editWin, "partEditCbox", partEditCbox);
        addPartListBtn  = (Button)getComponent(editWin, "addPartListBtn", addPartListBtn);
//        surnameTxt  = (Textbox)getComponent(editWin, "surnameTxt", surnameTxt);
//        addressTxt  = (Textbox)getComponent(editWin, "addressTxt", addressTxt);
//        telTxt  = (Textbox)getComponent(editWin, "telTxt", telTxt);
//        contentListbox = (Listbox)getComponent(component, "contentListbox", contentListbox);
//        searchBtn = (Toolbarbutton)getComponent(component, "searchBtn", searchBtn);
    }

    private void setSortFieldGrid()
    {
//        nameClm.setSortAscending(new FieldComparator("name", false));
//        nameClm.setSortDescending(new FieldComparator("name", true));
//        surnameClm.setSortAscending(new FieldComparator("surname", false));
//        surnameClm.setSortDescending(new FieldComparator("surname", true));
//        addressClm.setSortAscending(new FieldComparator("address", false));
//        addressClm.setSortDescending(new FieldComparator("address", true));
//        telClm.setSortAscending(new FieldComparator("tel", false));
//        telClm.setSortDescending(new FieldComparator("tel", true));
    }

    class DeleteObjectEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            Row object = (Row)event.getTarget().getAttribute("object");
            Rows rows = (Rows)event.getTarget().getParent().getParent().getParent();
            Part dashPart = ((DashboardPartSetting)object.getValue()).getPart();
            Part part = new Part();
            part.setId(dashPart.getId());
            part.setPartName(dashPart.getPartName());
            part.setPartNo(dashPart.getPartNo());
            partThreads.add(part);
            partEditCbox.getChildren().clear();
            ZKCatalogs.setParts(partEditCbox, partThreads,"partName-partNo",new Long(0));
            Comboitem item = new Comboitem();
            item.setLabel("N/A");
            List<Comboitem> items =  partEditCbox.getItems();
            items.add(0, item);
            partEditCbox.setSelectedIndex(0);
            rows.getChildren().remove(object);
            
        }

    }
}
