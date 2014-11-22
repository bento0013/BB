/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.backoffice.measurement;

import com.bsd.cse.app.backoffice.MeasurementToolCore;
import com.bsd.cse.app.backoffice.MeasurementToolModelCore;
import com.bsd.cse.app.backoffice.MeasurementToolTypeCore;
import com.bsd.cse.app.security.FunctionCore;
import com.bsd.cse.constant.PagingConstant;
import com.bsd.cse.model.backoffice.machine.MachineModelType;
import com.bsd.cse.model.backoffice.measurement.MeasurementTool;
import com.bsd.cse.model.backoffice.measurement.MeasurementToolModel;
import com.bsd.cse.model.backoffice.measurement.MeasurementToolType;
import com.bsd.cse.model.security.Function;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.grid.CriteriaObject;
import com.bsd.cse.zk.grid.GridSearchResult;
import com.bsd.cse.zk.grid.HibernateSearchObject;
import com.bsd.cse.zk.listbox.renderer.MeasurementModelRenderer;
import com.bsd.cse.zk.listbox.renderer.MeasurementToolRenderer;
import com.bsd.cse.zk.listbox.wrapper.ListboxListWrapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.zkoss.image.AImage;
import org.zkoss.util.ResourceUtil;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.FieldComparator;
import org.zkoss.zul.Image;
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
public class MeasurementToolController extends SecurityController{
    private static final long serialVersionUID = -3993123706258278544L;
    private static Log LOG = LogFactory.getLog(MeasurementToolController.class);
    private Window editWin ;
    private Button saveBtn;
    private Toolbarbutton newBtn;
    private Button cancelBtn;
    private Toolbarbutton searchBtn;
    private Textbox serialNoSearchTxt;
    private Textbox codeNoSearchTxt;
    private Textbox idTxt;   
    private Textbox brandTxt;
    private Textbox resolutionTxt;
    private Textbox incomingDateTxt;
    private Textbox customerDetailTxt;
    private Textbox descTxt;
    private Textbox serialNoTxt;
    private Textbox codeNoTxt;
    private Combobox modelCbox;   
    private Combobox measurementModelCbox;
    private Listbox contentListbox;        
    private Button addModelBtn;    
    private List<MeasurementToolModel> models = null;    
    private Object tmpData;
    private static Function modelFunction;
    private Paging paging;
    private Listheader serialClm;
    private Listheader codenoClm;
    private Listheader modelClm;
    private Listheader brandClm;
    private Listheader descClm;
    private Listheader incomingDateClm;
    private Listheader resolutionClm;
    private Listheader customerClm;

    static
    {
        try {
            modelFunction = FunctionCore.getFunction(1050402L);
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
        MeasurementToolModelCore core = new MeasurementToolModelCore();
        models = core.getAllList();
        Comboitem item = new Comboitem();
        item.setLabel("All");
        
        ZKCatalogs.setMeasurementToolModels(measurementModelCbox, models);
        List<Comboitem> items =  measurementModelCbox.getItems();
        items.add(0, item);
        measurementModelCbox.setSelectedIndex(0);
        ZKCatalogs.setMeasurementToolModels(modelCbox, models);
        
    }

    public void loadContent() throws Exception
    {
        setSortFieldGrid();
        Events.postEvent(Events.ON_CLICK,searchBtn,null);
    }

    private CriteriaObject createCriteria()
    {
        CriteriaObject criteriaObject = new CriteriaObject(MeasurementTool.class);
        criteriaObject.addAliasNames("model", "model", CriteriaObject.INNER_JOIN);
        if(!serialNoSearchTxt.getValue().trim().isEmpty())
        {
            criteriaObject.addCriteria(Restrictions.ilike("serialNo", serialNoSearchTxt.getValue().trim(),MatchMode.START));
        }
        if(!codeNoSearchTxt.getValue().trim().isEmpty())
        {
            criteriaObject.addCriteria(Restrictions.ilike("codeNo", codeNoSearchTxt.getValue().trim(),MatchMode.START));
        }
        if(measurementModelCbox.getSelectedItem() != null)
        {
            MeasurementToolModel model = (MeasurementToolModel)(measurementModelCbox.getSelectedItem() != null?measurementModelCbox.getSelectedItem().getValue():null);
            if(model != null)
            {
                criteriaObject.addCriteria(Restrictions.ilike("model.name", model.getName(),MatchMode.START));
            }
        }
        criteriaObject.setOrderBy("serialNo");
        criteriaObject.setDefaultSort("serialNo");
        criteriaObject.setAscending(Boolean.TRUE);
        return criteriaObject;
    }

    public void addEventListener()
    {
        

        addModelBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                
                execution.sendRedirect(modelFunction.getCommand());
            }
        });
       

        searchBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                String serialNo = serialNoSearchTxt.getValue();
                String codeNo = codeNoSearchTxt.getValue();
                MeasurementToolModel model = (MeasurementToolModel)(measurementModelCbox.getSelectedItem() != null?measurementModelCbox.getSelectedItem().getValue():null);
                MeasurementToolCore core = new MeasurementToolCore();
                HashMap<String,Object> metaData = core.getList(serialNo,codeNo,(model!=null?model.getId():null));

                paging.setPageSize(PagingConstant.PAGING_SIZE);
                paging.setDetailed(Boolean.TRUE);
                HibernateSearchObject<MeasurementTool> searchObject = new HibernateSearchObject<MeasurementTool>(createCriteria());
                GridSearchResult<MeasurementTool> result = searchObject.getSearchResult(paging.getActivePage()*paging.getPageSize(), paging.getPageSize());
                if(result != null && result.getCount()>0)
                {
                    contentListbox.setModel(new ListboxListWrapper<MeasurementTool>(contentListbox, paging, result, searchObject));
                }
                else
                {
                    List<String> noRecords = new ArrayList<String>();
                    noRecords.add(ResourceUtil.getLabel("backoffice.measurementtool.message.norecordfound"));
                    contentListbox.setModel(new ListboxListWrapper<MeasurementTool>(contentListbox, paging, new ListModelList(noRecords), searchObject));
                }

                contentListbox.setItemRenderer(new MeasurementToolRenderer(editWin,idTxt,brandTxt,descTxt,modelCbox,searchBtn,serialNoTxt,codeNoTxt,resolutionTxt,incomingDateTxt,customerDetailTxt));
//                if(((Integer)metaData.get("allRecords")).equals(new Integer(0)))
//                {
//                    ArrayList<String> results = new ArrayList<String>();
//                    results.add(ResourceUtil.getLabel("backoffice.measurementtool.message.norecordfound"));
//                    contentListbox.setModel(new ListModelList((List<String>)results));
//                }
//                else
//                {
//                    contentListbox.setModel(new ListModelList((List<MeasurementToolType>)metaData.get("results")));
//
//                }
//
//                contentListbox.setItemRenderer(new MeasurementToolRenderer(editWin,idTxt,brandTxt,descTxt,modelCbox,searchBtn,serialNoTxt,codeNoTxt,resolutionTxt,incomingDateTxt,customerDetailTxt));
            }
        });

        newBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {                
                idTxt.setValue("");
                brandTxt.setValue("");
                descTxt.setValue("");
                serialNoTxt.setValue("");
                codeNoTxt.setValue("");
                modelCbox.setValue("");
                resolutionTxt.setValue("");
                incomingDateTxt.setValue("");
                customerDetailTxt.setValue("");
                editWin.doModal();
            }
        });

        saveBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                HttpUserSession userSession = new HttpUserSession();
                Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
                MeasurementTool measurementTool = new MeasurementTool();
                String id = idTxt.getValue();
                String brand = brandTxt.getValue();
                String desc = descTxt.getValue();
                String serialNo = serialNoTxt.getValue();
                String codeNo = codeNoTxt.getValue();
                String resolution = resolutionTxt.getValue();
                String customerDetail = incomingDateTxt.getValue();
                String incomingDate = customerDetailTxt.getValue();

                if(serialNo.trim().isEmpty())
                {
                    AlertMessages.alertMessage("backoffice.measurementtool.alert.message.8");
                    return;
                }

                if(codeNo.trim().isEmpty())
                {
                    AlertMessages.alertMessage("backoffice.measurementtool.alert.message.9");
                    return;
                }

                if(modelCbox.getSelectedItem() == null)
                {
                    AlertMessages.alertMessage("backoffice.measurementtool.alert.message.10");
                    return;
                }

                MeasurementToolModel model = (MeasurementToolModel)modelCbox.getSelectedItem().getValue();
                
                if(!id.trim().isEmpty() &&!id.equals(""))
                {
                    measurementTool.setId(Long.valueOf(id));
                }
                
                measurementTool.setBrand(brand);
                measurementTool.setDescription(desc);
                measurementTool.setCodeNo(codeNo);
                measurementTool.setResolution(resolution);
                measurementTool.setIncomingDate(incomingDate);
                measurementTool.setCustomerDetail(customerDetail);
                measurementTool.setSerialNo(serialNo);
                
                if(model != null)
                {
                    measurementTool.setModel(model);
                }                

                
                MeasurementToolCore core = new MeasurementToolCore();
                try
                {

                    HashMap<String,Object> results = core.save(measurementTool, userId);
                    Boolean result = (Boolean)results.get("results");
                    if(result != null && result)
                    {                                               
                        editWin.setVisible(false);
                        AlertMessages.successMessage("backoffice.measurementtool.alert.message.1");
                        Events.sendEvent(Events.ON_CLICK, searchBtn, null);
                        event.stopPropagation();
                    }
                    else if(result != null && !result)
                    {
                        AlertMessages.failMessage("backoffice.measurementtool.alert.message.2");
                        return;
                    }
                }
                catch(Exception e)
                {
                    LOG.info(e.getMessage(),e);
                    if(e.toString().indexOf("ConstraintViolationException") != -1)
                    {
                        AlertMessages.failMessage("backoffice.measurementtool.alert.message.3");
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
        measurementModelCbox = (Combobox)getComponent(component, "measurementModelCbox", measurementModelCbox);
        cancelBtn = (Button)getComponent(editWin, "cancelBtn", cancelBtn);
        brandTxt  = (Textbox)getComponent(editWin, "brandTxt", brandTxt);
        serialNoTxt  = (Textbox)getComponent(editWin, "serialNoTxt", serialNoTxt);
        resolutionTxt  = (Textbox)getComponent(editWin, "resolutionTxt", resolutionTxt);
        customerDetailTxt  = (Textbox)getComponent(editWin, "customerDetailTxt", customerDetailTxt);
        incomingDateTxt  = (Textbox)getComponent(editWin, "incomingDateTxt", incomingDateTxt);
        codeNoTxt  = (Textbox)getComponent(editWin, "codeNoTxt", codeNoTxt);
        descTxt  = (Textbox)getComponent(editWin, "descTxt", descTxt);
        modelCbox  = (Combobox)getComponent(editWin, "modelCbox", modelCbox);
        idTxt  = (Textbox)getComponent(editWin, "idTxt", idTxt);
        contentListbox = (Listbox)getComponent(component, "contentListbox", contentListbox);
        searchBtn = (Toolbarbutton)getComponent(component, "searchBtn", searchBtn);
        addModelBtn= (Button)getComponent(editWin, "addModelBtn", addModelBtn);
    }

    private void setSortFieldGrid()
    {
        serialClm.setSortAscending(new FieldComparator("serialNo", false));
        serialClm.setSortDescending(new FieldComparator("serialNo", true));
        codenoClm.setSortAscending(new FieldComparator("codeNo", false));
        codenoClm.setSortDescending(new FieldComparator("codeNo", true));
        brandClm.setSortAscending(new FieldComparator("brand", false));
        brandClm.setSortDescending(new FieldComparator("brand", true));
        descClm.setSortAscending(new FieldComparator("description", false));
        descClm.setSortDescending(new FieldComparator("description", true));
        incomingDateClm.setSortAscending(new FieldComparator("incomingDate", false));
        incomingDateClm.setSortDescending(new FieldComparator("incomingDate", true));
        customerClm.setSortAscending(new FieldComparator("customerDetail", false));
        customerClm.setSortDescending(new FieldComparator("customerDetail", true));
        modelClm.setSortAscending(new FieldComparator("model.name", false));
        modelClm.setSortDescending(new FieldComparator("model.name", true));
        resolutionClm.setSortAscending(new FieldComparator("resolution", false));
        resolutionClm.setSortDescending(new FieldComparator("resolution", true));
    }
}
