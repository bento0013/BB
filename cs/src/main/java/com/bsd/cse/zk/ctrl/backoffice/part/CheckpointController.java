/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.backoffice.part;

import com.bsd.cse.app.backoffice.CheckpointCore;
import com.bsd.cse.app.backoffice.CheckpointTypeCore;
import com.bsd.cse.app.backoffice.CheckpointUnitCore;
import com.bsd.cse.app.backoffice.MeasurementToolTypeCore;
import com.bsd.cse.app.backoffice.PartCore;
import com.bsd.cse.app.backoffice.ProcessCore;
import com.bsd.cse.app.security.FunctionCore;
import com.bsd.cse.common.Configuration;
import com.bsd.cse.constant.PagingConstant;
import com.bsd.cse.model.backoffice.measurement.MeasurementToolModel;
import com.bsd.cse.model.backoffice.measurement.MeasurementToolType;
import com.bsd.cse.model.backoffice.part.Checkpoint;
import com.bsd.cse.model.backoffice.part.CheckpointType;
import com.bsd.cse.model.backoffice.part.CheckpointUnit;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.backoffice.part.Process;
import com.bsd.cse.model.security.Function;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.grid.CriteriaObject;
import com.bsd.cse.zk.grid.GridSearchResult;
import com.bsd.cse.zk.grid.HibernateSearchObject;
import com.bsd.cse.zk.listbox.renderer.CheckpointRenderer;
import com.bsd.cse.zk.listbox.wrapper.ListboxListWrapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.FieldComparator;
import org.zkoss.zul.Image;
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
public class CheckpointController extends SecurityController{
    private static final long serialVersionUID = -3993123706258278544L;
    private static Log LOG = LogFactory.getLog(CheckpointController.class);
    private Window editWin ;
    private Window viewWin ;
    private Button closeBtn;
    private Image viewImage;
    private Button saveBtn;
    private Toolbarbutton newBtn;
    private Button cancelBtn;
    private Toolbarbutton searchBtn;
    private Combobox processSearchCbox;
    private Combobox partSearchCbox;
    private Combobox measurementTypeSearchCbox;
    private Combobox processCbox;
    private Combobox partCbox;
    private Combobox measurementTypeCbox;
    private Combobox checkpointTypeCbox;
    private Combobox checkpointUnitCbox;
    private Checkbox spcCheckbox;
    private Textbox checkpointNameTxt;
    private Decimalbox minTxt;
    private Longbox checkpointIdTxt;
    private Decimalbox maxTxt;
    private Longbox positionTxt;
    private Button uploadBtn;
    private Listbox contentListbox;
    private Image picImg;    
    private Button clearUploadBtn;
    private Button addPartBtn;
    private Button addMeasurementModelBtn;
    private static String IMAGE_PATH;    
    private static org.zkoss.image.Image DEFAULT_IMAGE;
    private Object tmpData;
    private List<Part> parts;
    private List<Process> processes;
    private List<CheckpointType> checkpointTypes;
    private List<CheckpointUnit> checkpointUnits;
    private List<MeasurementToolType> measurementTypes;
    private static Function partFunction;
    private static Function measurementModelFunction;
    private Paging paging;
    private Listheader processNameClm;
    private Listheader partNumberClm;
    private Listheader positionClm;
    private Listheader checkpointTypeClm;
    private Listheader checkpointUnitClm;
    private Listheader minClm;
    private Listheader modelClm;
    private Listheader maxClm;

    static
    {
        try {
            partFunction = FunctionCore.getFunction(1050201L);
            measurementModelFunction = FunctionCore.getFunction(1050403L);
            IMAGE_PATH = Configuration.getString("cs.image.folder")+"\\checkpoint-img\\";
            DEFAULT_IMAGE = new AImage(IMAGE_PATH+"no-image.png");
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
        PartCore partCore = new PartCore();
        parts = partCore.getAllList();
        Comboitem item = new Comboitem();
        item.setLabel("All");
        ZKCatalogs.setParts(partSearchCbox, parts);
        List<Comboitem> items =  partSearchCbox.getItems();
        items.add(0, item);
        partSearchCbox.setSelectedIndex(0);        
        ZKCatalogs.setParts(partCbox, parts);

        MeasurementToolTypeCore measurementTypeCore = new MeasurementToolTypeCore();
        measurementTypes = measurementTypeCore.getAllList();
        ZKCatalogs.setMeasurementModelTypes(measurementTypeSearchCbox, measurementTypes);
        item = new Comboitem();
        item.setLabel("All");
        items =  measurementTypeSearchCbox.getItems();
        items.add(0, item);
        measurementTypeSearchCbox.setSelectedIndex(0);
        ZKCatalogs.setMeasurementModelTypes(measurementTypeCbox, measurementTypes);

        ProcessCore processCore = new ProcessCore();
        processes = processCore.getAllList();
        item = new Comboitem();
        item.setLabel("All");
        ZKCatalogs.setProcesses(processSearchCbox, processes);
        items =  processSearchCbox.getItems();
        items.add(0, item);
        processSearchCbox.setSelectedIndex(0);
        ZKCatalogs.setProcesses(processCbox, processes);

        CheckpointTypeCore checkpointTypeCore = new CheckpointTypeCore();
        checkpointTypes = checkpointTypeCore.getAllList();               
        ZKCatalogs.setCheckpointTypes(checkpointTypeCbox, checkpointTypes);

        CheckpointUnitCore checkpointUnitCore = new CheckpointUnitCore();
        checkpointUnits = checkpointUnitCore.getAllList();
        ZKCatalogs.setCheckpointUnits(checkpointUnitCbox, checkpointUnits);
    }

    public void loadContent() throws Exception
    {
        setSortFieldGrid();
        Events.postEvent(Events.ON_CLICK,searchBtn,null);
    }

    private CriteriaObject createCriteria()
    {
        CriteriaObject criteriaObject = new CriteriaObject(Checkpoint.class);
        criteriaObject.addAliasNames("part", "part", CriteriaObject.INNER_JOIN);
        criteriaObject.addAliasNames("measurementType", "measurementType", CriteriaObject.INNER_JOIN);
        criteriaObject.addAliasNames("process", "process", CriteriaObject.INNER_JOIN);
        criteriaObject.addAliasNames("checkpointType", "checkpointType", CriteriaObject.INNER_JOIN);
        criteriaObject.addAliasNames("checkpointUnit", "checkpointUnit", CriteriaObject.INNER_JOIN);        

        if(measurementTypeSearchCbox.getSelectedItem() != null)
        {
            MeasurementToolModel model = (MeasurementToolModel)(measurementTypeSearchCbox.getSelectedItem() != null?measurementTypeSearchCbox.getSelectedItem().getValue():null);
            if(model != null)
            {
                criteriaObject.addCriteria(Restrictions.ilike("measurementType.name", model.getName(),MatchMode.EXACT));
            }
        }

        if(partSearchCbox.getSelectedItem() != null)
        {
            Part part = (Part)(partSearchCbox.getSelectedItem() != null?partSearchCbox.getSelectedItem().getValue():null);
            if(part != null)
            {
                criteriaObject.addCriteria(Restrictions.ilike("part.partNo", part.getPartNo(),MatchMode.EXACT));
            }
        }

        if(processSearchCbox.getSelectedItem() != null)
        {
            Process process = (Process)(processSearchCbox.getSelectedItem() != null?processSearchCbox.getSelectedItem().getValue():null);
            if(process != null)
            {
                criteriaObject.addCriteria(Restrictions.ilike("process.processName", process.getProcessName(),MatchMode.EXACT));
            }
        }
       
        criteriaObject.setOrderBy("part.partNo");
        criteriaObject.setDefaultSort("part.partNo");
        criteriaObject.setAscending(Boolean.TRUE);
        return criteriaObject;
    }

    public void addEventListener()
    {

        uploadBtn.addEventListener(Events.ON_UPLOAD, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                UploadEvent uEvent = (UploadEvent)event;
                Media media = uEvent.getMedia();
                if(media instanceof org.zkoss.image.Image)
                {
                    picImg.setContent((org.zkoss.image.Image)media);
                }
                else
                {
                    alert("No Image");
                }

            }
        });

        addPartBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                
                execution.sendRedirect(partFunction.getCommand());
            }
        });

        addMeasurementModelBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {

                execution.sendRedirect(measurementModelFunction.getCommand());
            }
        });

        clearUploadBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                Part part = (Part)event.getTarget().getAttribute("object");
                if(part != null && part.getImagePath() != null)
                {
                    org.zkoss.image.Image picImage= new AImage(IMAGE_PATH+part.getImagePath());
                    picImg.setContent(picImage);
                }
                else
                {
                    picImg.setContent(DEFAULT_IMAGE);
                }
            }
        });

        searchBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                MeasurementToolModel model = (MeasurementToolModel)(measurementTypeSearchCbox.getSelectedItem() != null?measurementTypeSearchCbox.getSelectedItem().getValue():null);
                Part part = (Part)(partSearchCbox.getSelectedItem() != null?partSearchCbox.getSelectedItem().getValue():null);
                Process process = (Process)(processSearchCbox.getSelectedItem() != null?processSearchCbox.getSelectedItem().getValue():null);
                CheckpointCore core = new CheckpointCore();
                HashMap<String,Object> metaData = core.getList(part,process,model);
                paging.setPageSize(PagingConstant.PAGING_SIZE);
                paging.setDetailed(Boolean.TRUE);
                HibernateSearchObject<Checkpoint> searchObject = new HibernateSearchObject<Checkpoint>(createCriteria());
                GridSearchResult<Checkpoint> result = searchObject.getSearchResult(paging.getActivePage()*paging.getPageSize(), paging.getPageSize());
                if(result != null && result.getCount()>0)
                {
                    contentListbox.setModel(new ListboxListWrapper<Checkpoint>(contentListbox, paging, result, searchObject));
                }
                else
                {
                    List<String> noRecords = new ArrayList<String>();
                    noRecords.add(ResourceUtil.getLabel("backoffice.checkpoint.message.norecordfound"));
                    contentListbox.setModel(new ListboxListWrapper<Checkpoint>(contentListbox, paging, new ListModelList(noRecords), searchObject));
                }

                contentListbox.setItemRenderer(new CheckpointRenderer(editWin,checkpointIdTxt,partCbox,processCbox,measurementTypeCbox,checkpointTypeCbox,checkpointUnitCbox,minTxt,maxTxt,positionTxt,searchBtn,clearUploadBtn,picImg,checkpointNameTxt,spcCheckbox,viewImage,viewWin));
//                if(((Integer)metaData.get("allRecords")).equals(new Integer(0)))
//                {
//                    ArrayList<String> results = new ArrayList<String>();
//                    results.add(ResourceUtil.getLabel("backoffice.checkpoint.message.norecordfound"));
//                    contentListbox.setModel(new ListModelList((List<String>)results));
//                }
//                else
//                {
//                    contentListbox.setModel(new ListModelList((List<Part>)metaData.get("results")));
//
//                }
//
//                contentListbox.setItemRenderer(new CheckpointRenderer(editWin,checkpointIdTxt,partCbox,processCbox,measurementTypeCbox,checkpointTypeCbox,checkpointUnitCbox,minTxt,maxTxt,positionTxt,searchBtn,clearUploadBtn,picImg,viewImage,viewWin));
            }
        });

        newBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                clearUploadBtn.setAttribute("object", null);
                checkpointIdTxt.setValue(null);
                partCbox.setValue("");
                partCbox.setDisabled(false);
                processCbox.setDisabled(false);
                positionTxt.setReadonly(false);
                processCbox.setValue("");
                measurementTypeCbox.setValue("");
                checkpointTypeCbox.setValue("");
                checkpointUnitCbox.setValue("");
                spcCheckbox.setChecked(false);
                checkpointNameTxt.setValue("");
                minTxt.setValue((BigDecimal)null);
                maxTxt.setValue((BigDecimal)null);
                positionTxt.setValue(null);
                picImg.setContent(DEFAULT_IMAGE);
                editWin.doModal();
            }
        });

        saveBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                HttpUserSession userSession = new HttpUserSession();
                Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
                Checkpoint checkpoint = new Checkpoint();

                Long id = checkpointIdTxt.getValue();

                if(id != null)
                {
                    checkpoint.setId(id);
                }

                BigDecimal min = minTxt.getValue();
                BigDecimal max = maxTxt.getValue();
                Long position = positionTxt.getValue();

                

                if(partCbox.getSelectedItem() == null)
                {
                    AlertMessages.alertMessage("backoffice.checkpoint.alert.message.8");
                    return;
                }

                if(processCbox.getSelectedItem() == null)
                {
                    AlertMessages.alertMessage("backoffice.checkpoint.alert.message.9");
                    return;
                }

                if(measurementTypeCbox.getSelectedItem() == null)
                {
                    AlertMessages.alertMessage("backoffice.checkpoint.alert.message.10");
                    return;
                }
               
                if(min == null)
                {
                    AlertMessages.alertMessage("backoffice.checkpoint.alert.message.11");
                    return;
                }

                if(max == null)
                {
                    AlertMessages.alertMessage("backoffice.checkpoint.alert.message.12");
                    return;
                }

                if(position == null)
                {
                    AlertMessages.alertMessage("backoffice.checkpoint.alert.message.13");
                    return;
                }

                if(checkpointTypeCbox.getSelectedItem() == null)
                {
                    AlertMessages.alertMessage("backoffice.checkpoint.alert.message.14");
                    return;
                }

                if(checkpointUnitCbox.getSelectedItem() == null)
                {
                    AlertMessages.alertMessage("backoffice.checkpoint.alert.message.15");
                    return;
                }

                Part part = (Part)partCbox.getSelectedItem().getValue();
                Process process = (Process)processCbox.getSelectedItem().getValue();
                MeasurementToolType measurementType = (MeasurementToolType)measurementTypeCbox.getSelectedItem().getValue();
                CheckpointType checkpointType = (CheckpointType)checkpointTypeCbox.getSelectedItem().getValue();
                CheckpointUnit checkpointUnit = (CheckpointUnit)checkpointUnitCbox.getSelectedItem().getValue();
                
                      
                if(part != null)
                {
                    checkpoint.setPart(part);
                }

                if(process != null)
                {
                    checkpoint.setProcess(process);
                }

                if(measurementType != null)
                {
                    checkpoint.setMeasurementType(measurementType);
                }

                if(checkpointType != null)
                {
                    checkpoint.setCheckpointType(checkpointType);
                }

                if(checkpointUnit != null)
                {
                    checkpoint.setCheckpointUnit(checkpointUnit);
                }


                checkpoint.setPosition(position);
                checkpoint.setMaxDuration(max);
                checkpoint.setMinDuration(min);
                checkpoint.setSpc(spcCheckbox.isChecked());
                checkpoint.setCheckpointName(checkpointNameTxt.getValue());

                if(checkpoint.getId() != null)
                {
                    checkpoint.setImagePath(checkpoint.getId()+".png");
                }

                
                CheckpointCore core = new CheckpointCore();
                try
                {

                    HashMap<String,Object> results = core.save(checkpoint, userId);
                    Boolean result = (Boolean)results.get("results");
                    if(result != null && result)
                    {
                        if(checkpoint.getImagePath() == null)
                        {                            
                            checkpoint.setImagePath(checkpoint.getId()+".png");
                            core.save(checkpoint, userId);
                        }
                        saveImage(checkpoint);
                        editWin.setVisible(false);
                        AlertMessages.successMessage("backoffice.checkpoint.alert.message.1");
                        Events.sendEvent(Events.ON_CLICK, searchBtn, null);
                        event.stopPropagation();
                    }
                    else if(result != null && !result)
                    {
                        AlertMessages.failMessage("backoffice.checkpoint.alert.message.2");
                        return;
                    }
                }
                catch(Exception e)
                {
                    LOG.info(e.getMessage(),e);
                    if(e.toString().indexOf("ConstraintViolationException") != -1)
                    {
                        AlertMessages.failMessage("backoffice.checkpoint.alert.message.3");
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

        closeBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                viewWin.setVisible(false);
                event.stopPropagation();

            }
        });
    }

    private void saveImage(Checkpoint model) throws Exception
    {
        InputStream picStream = null;
        try
        {
            File file = new File(IMAGE_PATH+model.getId()+".png");
            if(file.exists())
            {
                file.mkdirs();
            }
            FileOutputStream fileOutput = new FileOutputStream(file);
            picStream = picImg.getContent().getStreamData();
            int read = 0;
            byte b[] = new byte[256];
            while((read = picStream.read(b)) > 0)
            {
                fileOutput.write(b,0,read);
            }
            fileOutput.flush();
            fileOutput.close();
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            if(picStream != null)
            {
                picStream.close();
            }
        }
    }

    public void createComponent(Component component)
    {
        editWin = (Window)getComponent(component, "editWin", editWin);
        viewWin = (Window)getComponent(component, "viewWin", viewWin);
        closeBtn = (Button)getComponent(viewWin, "closeBtn", closeBtn);
        viewImage  = (Image)getComponent(viewWin, "viewImage", viewImage);
        newBtn = (Toolbarbutton)getComponent(component, "newBtn", newBtn);
        saveBtn = (Button)getComponent(editWin, "saveBtn", saveBtn);      
        cancelBtn = (Button)getComponent(editWin, "cancelBtn", cancelBtn);                        
        uploadBtn  = (Button)getComponent(editWin, "uploadBtn", uploadBtn);
        partSearchCbox  = (Combobox)getComponent(component, "partSearchCbox", partSearchCbox);
        processSearchCbox  = (Combobox)getComponent(component, "processSearchCbox", processSearchCbox);
        measurementTypeSearchCbox  = (Combobox)getComponent(component, "measurementTypeSearchCbox", measurementTypeSearchCbox);
        minTxt = (Decimalbox)getComponent(editWin, "minTxt", minTxt);
        maxTxt = (Decimalbox)getComponent(editWin, "maxTxt", maxTxt);
        checkpointIdTxt = (Longbox)getComponent(editWin, "checkpointIdTxt", checkpointIdTxt);
        positionTxt = (Longbox)getComponent(editWin, "positionTxt", positionTxt);
        partCbox  = (Combobox)getComponent(editWin, "partCbox", partCbox);
        processCbox  = (Combobox)getComponent(editWin, "processCbox", processCbox);
        measurementTypeCbox  = (Combobox)getComponent(editWin, "measurementTypeCbox", measurementTypeCbox);
        checkpointTypeCbox  = (Combobox)getComponent(editWin, "checkpointTypeCbox", checkpointTypeCbox);
        checkpointUnitCbox  = (Combobox)getComponent(editWin, "checkpointUnitCbox", checkpointUnitCbox);
        checkpointNameTxt   = (Textbox)getComponent(editWin, "checkpointNameTxt", checkpointNameTxt);
        spcCheckbox = (Checkbox)getComponent(editWin, "spcCheckbox", spcCheckbox);
        contentListbox = (Listbox)getComponent(component, "contentListbox", contentListbox);
        searchBtn = (Toolbarbutton)getComponent(component, "searchBtn", searchBtn);
        clearUploadBtn  = (Button)getComponent(editWin, "clearUploadBtn", clearUploadBtn);
        picImg  = (Image)getComponent(editWin, "picImg", picImg);
        addPartBtn = (Button)getComponent(editWin, "addPartBtn", addPartBtn);
        addMeasurementModelBtn = (Button)getComponent(editWin, "addMeasurementModelBtn", addMeasurementModelBtn);
    }

    private void setSortFieldGrid()
    {
        processNameClm.setSortAscending(new FieldComparator("process.processName", false));
        processNameClm.setSortDescending(new FieldComparator("process.processName", true));
        partNumberClm.setSortAscending(new FieldComparator("part.partNo", false));
        partNumberClm.setSortDescending(new FieldComparator("part.partNo", true));
        positionClm.setSortAscending(new FieldComparator("position", false));
        positionClm.setSortDescending(new FieldComparator("position", true));
        checkpointTypeClm.setSortAscending(new FieldComparator("checkpointType.name", false));
        checkpointTypeClm.setSortDescending(new FieldComparator("checkpointType.name", true));
        checkpointUnitClm.setSortAscending(new FieldComparator("checkpointUnit.name", false));
        checkpointUnitClm.setSortDescending(new FieldComparator("checkpointUnit.name", true));
        minClm.setSortAscending(new FieldComparator("minDuration", false));
        minClm.setSortDescending(new FieldComparator("minDuration", true));
        modelClm.setSortAscending(new FieldComparator("measurementType.name", false));
        modelClm.setSortDescending(new FieldComparator("measurementType.name", true));
        maxClm.setSortAscending(new FieldComparator("maxDuration", false));
        maxClm.setSortDescending(new FieldComparator("maxDuration", true));
    }

}
