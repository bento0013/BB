/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.input;

import com.bsd.cse.app.backoffice.CheckpointCore;
import com.bsd.cse.app.backoffice.MachineCore;
import com.bsd.cse.app.backoffice.PartCore;
import com.bsd.cse.app.backoffice.ProcessCore;
import com.bsd.cse.app.backoffice.ProcessPartCore;
import com.bsd.cse.app.input.MeasurementPartCore;
import com.bsd.cse.app.input.MeasurementPositionCore;
import com.bsd.cse.app.input.MeasurementTimeCore;
import com.bsd.cse.common.Configuration;
import com.bsd.cse.constant.PagingConstant;
import com.bsd.cse.model.backoffice.machine.Machine;
import com.bsd.cse.model.backoffice.measurement.MeasurementTool;
import com.bsd.cse.model.backoffice.part.Checkpoint;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.backoffice.part.Process;
import com.bsd.cse.model.backoffice.part.ProcessPart;
import com.bsd.cse.model.input.FileMeasurementPart;
import com.bsd.cse.model.input.MeasurementCheckpoint;
import com.bsd.cse.model.input.MeasurementPart;
import com.bsd.cse.model.input.MeasurementTime;
import com.bsd.cse.model.security.UserInfo;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.grid.CriteriaObject;
import com.bsd.cse.zk.grid.GridSearchResult;
import com.bsd.cse.zk.grid.HibernateSearchObject;
import com.bsd.cse.zk.grid.renderer.FileMeasurementPartRenderer;
import com.bsd.cse.zk.grid.renderer.MeasurementPartRenderer;
import com.bsd.cse.zk.grid.wrapper.GridListWrapper;
import com.bsd.cse.zk.media.MediaProcessCore;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.zkoss.image.AImage;
import org.zkoss.util.ResourceUtil;
import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Form;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

/**
 *
 * @author bento
 */
public class MeasurementPartController extends SecurityController{
    private static final long serialVersionUID = 4374154270052271685L;

    private static Log LOG = LogFactory.getLog(MeasurementPartController.class);

    private Datebox lineDatebox;
    private Combobox partCbox;
    private Combobox processEditCbox;
    private Toolbarbutton searchBtn;
    private Grid measurementPartGrid;
    private Grid fileMeasurementPartGrid;
    private Window editWin;
    private Decimalbox valueEditTxt;
    private Combobox measurementToolEditCbox;
    private Button cancelBtn;
    private Button saveBtn;
    private Button uploadBtn;
    private Textbox idTxt;
    private Textbox filenameTxt;
    private Combobox timeEditCbox;
    private Combobox partEditCbox;
    private Datebox lineEditDatebox;
    private Combobox processCbox;
    private Combobox checkpointEditCbox;
//    private Combobox positionCbox;
    private Combobox machineCbox;
    private Combobox machineEditCbox;
    private Form form;
    private Textbox filePathTxt;
    private Textbox fileNameTxt;
//    private Combobox periodCbox;
//    private Combobox periodEditCbox;
//    private Combobox timeCbox;    
    private Image picImg;
    private Paging paging;
    private Media media;
    private Button submitBtn;
    private Part part;
    private Process process;
    private Machine machine;
    private Date lineDate;

    private static String IMAGE_PATH = null;
    private static String FILE_PATH = null;
    private static org.zkoss.image.Image DEFAULT_IMAGE;
    static
    {
        try {
            IMAGE_PATH = Configuration.getString("cs.image.folder")+"\\process-part-img\\";
            FILE_PATH = Configuration.getString("cs.image.folder")+"\\file-measurement-part\\";
            DEFAULT_IMAGE = new AImage(IMAGE_PATH+"no-image.png");
        } catch (IOException ex) {
            LOG.error(ex.getMessage(),ex);
        }
        catch (Exception ex) {
            LOG.error(ex.getMessage(),ex);
        }
    }


    @Override
    public ComponentInfo doBeforeCompose(Page page,Component component,ComponentInfo comInfo)
    {
        ComponentInfo componentInfo = super.doBeforeCompose(page, component, comInfo);
        MeasurementTimeCore timeCore = new MeasurementTimeCore();
        try {
            page.setAttribute("times", timeCore.getAllList());
        } catch (Exception ex) {
            LOG.error(ex.getMessage(),ex);
        }
        return componentInfo;
    }

    @Override
    public void doAfterCompose(Component component) throws Exception
    {
        super.doAfterCompose(component);
        createComponent(component);
        addEventListener();
        initialData();
    }

    public void createComponent(Component component)
    {
        searchBtn = (Toolbarbutton)getComponent(component, "searchBtn", searchBtn);       
        partCbox = (Combobox)getComponent(component, "partCbox", partCbox);
        lineDatebox = (Datebox)getComponent(component, "lineDatebox", lineDatebox);
        measurementPartGrid = (Grid)getComponent(component, "measurementPartGrid", measurementPartGrid);
        editWin = (Window)getComponent(component, "editWin", editWin);
        idTxt = (Textbox)getComponent(editWin,"idTxt",idTxt);
        filePathTxt = (Textbox)getComponent(component,"filePath",filePathTxt);
        fileNameTxt = (Textbox)getComponent(component,"filename",fileNameTxt);
        form = (Form)getComponent(component,"form",form);
        valueEditTxt = (Decimalbox)getComponent(editWin,"valueEditTxt",valueEditTxt);
        measurementToolEditCbox = (Combobox)getComponent(editWin,"measurementToolEditCbox",measurementToolEditCbox);        
        timeEditCbox = (Combobox)getComponent(editWin,"timeEditCbox",timeEditCbox);
//        timeCbox = (Combobox)getComponent(editWin,"timeCbox",timeCbox);
        processEditCbox = (Combobox)getComponent(editWin,"processEditCbox",processEditCbox);
        partEditCbox = (Combobox)getComponent(editWin,"partEditCbox",partEditCbox);
        lineEditDatebox = (Datebox)getComponent(editWin,"lineEditDatebox",lineEditDatebox);
        cancelBtn = (Button)getComponent(editWin,"cancelBtn",cancelBtn);
        saveBtn = (Button)getComponent(editWin,"saveBtn",saveBtn);
        processCbox = (Combobox)getComponent(component,"processCbox",processCbox);
//        periodCbox = (Combobox)getComponent(component,"periodCbox",periodCbox);
//        periodEditCbox= (Combobox)getComponent(editWin,"periodEditCbox",periodEditCbox);
        checkpointEditCbox = (Combobox)getComponent(editWin,"checkpointEditCbox",checkpointEditCbox);
        machineEditCbox = (Combobox)getComponent(editWin,"machineEditCbox",machineEditCbox);
//        positionCbox = (Combobox)getComponent(editWin,"positionCbox",positionCbox);
        machineCbox = (Combobox)getComponent(editWin,"machineCbox",machineCbox);
        filenameTxt = (Textbox)getComponent(component,"filenameTxt",filenameTxt);
        submitBtn  = (Button)getComponent(component,"submitBtn",submitBtn);

    }

    private void initialData() throws Exception
    {
        
        CheckpointCore checkpointCore = new CheckpointCore();
        Comboitem item = new Comboitem();
        item.setLabel("N/A");
        ZKCatalogs.setParts(partCbox, checkpointCore.getAllPartList());
        List<Comboitem> items =  partCbox.getItems();
        items.add(0, item);
        partCbox.setSelectedIndex(0);
                
        ProcessCore processCore = new ProcessCore();
        Comboitem processItem = new Comboitem();
        processItem.setLabel("N/A");
        ZKCatalogs.setProcesses(processCbox, processCore.getAllList());
        List<Comboitem> processItems =  processCbox.getItems();
        processItems.add(0, processItem);
        processCbox.setSelectedIndex(0);

        MachineCore machineCore = new MachineCore();
        Comboitem machineItem = new Comboitem();
        machineItem.setLabel("N/A");
        ZKCatalogs.setMachines(machineCbox, machineCore.getAllList());
        List<Comboitem> machineItems =  machineCbox.getItems();
        machineItems.add(0, machineItem);
        machineCbox.setSelectedIndex(0);

//        MeasurementTimeCore timeCore = new MeasurementTimeCore();
//        Comboitem timeItem = new Comboitem();
//        timeItem.setLabel("N/A");
//        ZKCatalogs.setTimes(timeCbox, timeCore.getAllList());
//        List<Comboitem> positionItems =  timeCbox.getItems();
//        positionItems.add(0, timeItem);
//        timeCbox.setSelectedIndex(0);

//        Comboitem periodNaItem = new Comboitem();
//        periodNaItem.setLabel("N/A");
//        Comboitem period1Item = new Comboitem();
//        period1Item.setValue(new Integer(1));
//        period1Item.setLabel("Mid Day");
//        Comboitem period2Item = new Comboitem();
//        period2Item.setValue(new Integer(2));
//        period2Item.setLabel("Mid Night");
//        periodCbox.appendChild(periodNaItem);
//        periodCbox.appendChild(period1Item);
//        periodCbox.appendChild(period2Item);

        picImg.setContent(DEFAULT_IMAGE);

//        periodCbox.setSelectedIndex(0);

        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        lineDatebox.setValue(currentDate.getTime());
        setNoRecordFound();
        disabledUploadFile(true);
    }

    public void addEventListener()
    {
        searchBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                
                loadContent();
                
            }
        });

        uploadBtn.addEventListener(Events.ON_UPLOAD, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                UploadEvent uEvent = (UploadEvent)event;
                media = uEvent.getMedia();
                filenameTxt.setValue(media.getName());
                LOG.info("format : "+media.getFormat());
                LOG.info("name : "+media.getName());
                LOG.info("Content : "+media.getContentType());
                try
                {
                    LOG.info("StringData : "+media.getStringData());
                }
                catch(Exception e)
                {
                    LOG.warn(e.getMessage(),e);
                }

                try
                {
                    LOG.info("ByteData : "+media.getByteData());
                }
                catch(Exception e)
                {
                    LOG.warn(e.getMessage(),e);
                }

                try
                {
                    LOG.info("ReaderData : "+media.getReaderData());
                }
                catch(Exception e)
                {
                    LOG.warn(e.getMessage(),e);
                }

                try
                {
                    LOG.info("StreamData : "+media.getStreamData());
                }
                catch(Exception e)
                {
                    LOG.warn(e.getMessage(),e);
                }
                
                
                
            }
        });

        submitBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {                                
                FileMeasurementPart fileMeasurementPart = new FileMeasurementPart();
                HttpUserSession userSession = new HttpUserSession();
                Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
                fileMeasurementPart.setFilename(media.getName());
                MeasurementPartCore core = new MeasurementPartCore();
                fileMeasurementPart.setPart(part);
                fileMeasurementPart.setProcess(process);
                fileMeasurementPart.setMachine(machine);
                Calendar measurementDate = Calendar.getInstance();
                measurementDate.setTime(lineDate);
                measurementDate.set(Calendar.HOUR_OF_DAY, 0);
                measurementDate.set(Calendar.MINUTE, 0);
                measurementDate.set(Calendar.SECOND, 0);
                measurementDate.set(Calendar.MILLISECOND, 0);
                fileMeasurementPart.setMeasurementDate(measurementDate.getTime());
                UserInfo userInfo = new UserInfo();
                userInfo.setId(userId);
                fileMeasurementPart.setCreatedBy(userInfo);
                fileMeasurementPart.setCreatedDate(new Date());
                core.saveFile(fileMeasurementPart, userId);
                saveFile(fileMeasurementPart);
                filenameTxt.setValue("");
                searchFile();
            }
        });

        saveBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {

                MeasurementPart measurementPart = new MeasurementPart();
                HttpUserSession userSession = new HttpUserSession();
                Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
                String id = idTxt.getValue();                
                Part part = (Part)partEditCbox.getSelectedItem().getValue();                
                MeasurementTool measurementTool = (MeasurementTool)(measurementToolEditCbox.getSelectedItem() != null?measurementToolEditCbox.getSelectedItem().getValue():null);
                MeasurementTime time = (MeasurementTime)timeEditCbox.getSelectedItem().getValue();
                Process process = (Process)processEditCbox.getSelectedItem().getValue();
//                Integer period = (Integer)periodEditCbox.getSelectedItem().getValue();
                Checkpoint checkpoint = (Checkpoint)checkpointEditCbox.getSelectedItem().getValue();
                Machine machine = (Machine)machineEditCbox.getSelectedItem().getValue();
                BigDecimal value = valueEditTxt.getValue();
                

                if(part == null)
                {
                    AlertMessages.alertMessage("measurementpart.alert.message.required.2");
                    return ;
                }

                if(process == null)
                {
                    AlertMessages.alertMessage("measurementpart.alert.message.required.3");
                    return ;
                }

                if(checkpoint == null)
                {
                    AlertMessages.alertMessage("measurementpart.alert.message.required.7");
                    return ;
                }
              
                if(measurementTool == null)
                {
                    AlertMessages.alertMessage("measurementpart.alert.message.required.6");
                    return ;
                }

                if(machine == null)
                {
                    AlertMessages.alertMessage("measurementpart.alert.message.required.8");
                    return ;
                }

                if(time == null)
                {
                    AlertMessages.alertMessage("measurementpart.alert.message.required.4");
                    return ;
                }

                if(value == null)
                {
                    AlertMessages.alertMessage("measurementpart.alert.message.required.5");
                    return ;
                }

//                if(period == null)
//                {
//                    AlertMessages.alertMessage("measurementpart.alert.message.required.9");
//                    return ;
//                }


                
                 
                if(id != null && !id.isEmpty())
                {
                    measurementPart.setId(new Long(id));
                }
                
                
                measurementPart.setMeasurementTool(measurementTool);
                measurementPart.setPart(part);
                measurementPart.setCheckpoint(checkpoint);
                measurementPart.setMeasurementTime(time);
                measurementPart.setProcess(process);
                measurementPart.setMeasurementValue(value);
                measurementPart.setMachine(machine);
//                measurementPart.setPeriod(period);


                measurementPart.setMeasurementDate(lineEditDatebox.getValue());

                MeasurementPartCore core = new MeasurementPartCore();
                try
                {                    
                    HashMap<String,Object> results = core.save(measurementPart, userId);
                    Boolean result = (Boolean)results.get("results");
                    if(result != null && result)
                    {                        
                        editWin.setVisible(false);
                        AlertMessages.successMessage("measurementpart.alert.message.1");
                        Events.sendEvent(Events.ON_CLICK, searchBtn, null);
                        event.stopPropagation();
                    }
                    else if(result != null && !result)
                    {
                        AlertMessages.failMessage("measurementpart.alert.message.2");
                        return;
                    }
                }
                catch(Exception e)
                {
                    if(e.toString().indexOf("ConstraintViolationException") != -1)
                    {
                        AlertMessages.failMessage("measurementpart.alert.message.3");
                    }
                    else
                    {
                        AlertMessages.failMessage("measurementpart.default.alert.message.1");
                    }
                }


                editWin.setVisible(false);
                event.stopPropagation();
            }
        });

        cancelBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                editWin.setVisible(false);
                event.stopPropagation();

            }
        });

         partCbox.addEventListener(Events.ON_CHANGE, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {

                Part part = (Part)partCbox.getSelectedItem().getValue();
                Comboitem processItem = new Comboitem();
                processItem.setLabel("N/A");
                List<Comboitem> processItems =  processCbox.getItems();
                processItems.clear();
                if(part != null)
                {
                    ProcessCore processCore = new ProcessCore();
                    ZKCatalogs.setProcesses(processCbox, processCore.getList(part.getNumProcesses()));

                }
                processItems.add(0, processItem);
                processCbox.setSelectedIndex(0);
            }
        });
    }

    public void loadContent() throws Exception
    {
        MeasurementPartCore core = new MeasurementPartCore();
        ProcessPartCore processPartCore = new ProcessPartCore();
        disabledUploadFile(true);

        if(lineDatebox.getValue() == null)
        {
            AlertMessages.alertMessage("measurementpart.alert.message.required.1");
            setNoRecordFound();
            return ;
        }

        if(partCbox.getSelectedIndex() == 0)
        {
            AlertMessages.alertMessage("measurementpart.alert.message.required.2");
            setNoRecordFound();
            return ;
        }

        if(processCbox.getSelectedIndex() == 0)
        {
            AlertMessages.alertMessage("measurementpart.alert.message.required.3");
            setNoRecordFound();
            return ;
        }

//        if(timeCbox.getSelectedIndex() == 0)
//        {
//            AlertMessages.alertMessage("measurementpart.alert.message.required.7");
//            setNoRecordFound();
//            return ;
//        }

        if(machineCbox.getSelectedIndex() == 0)
        {
            AlertMessages.alertMessage("measurementpart.alert.message.required.8");
            setNoRecordFound();
            return ;
        }

//        if(periodCbox.getSelectedIndex() == 0)
//        {
//            AlertMessages.alertMessage("measurementpart.alert.message.required.9");
//            setNoRecordFound();
//            return ;
//        }
        disabledUploadFile(false);

        lineDate = lineDatebox.getValue();
        part = (Part)partCbox.getSelectedItem().getValue();
        machine = (Machine)machineCbox.getSelectedItem().getValue();
        process = (Process)processCbox.getSelectedItem().getValue();
      

        HashMap<String,Object> results = core.getMeasurementParts(lineDatebox.getValue()
                , (Part)partCbox.getSelectedItem().getValue(), (Process)processCbox.getSelectedItem().getValue()
                ,  (Machine)machineCbox.getSelectedItem().getValue());
        List<MeasurementCheckpoint> parts = (List<MeasurementCheckpoint>)results.get("results");
        
        if(parts != null && parts.size() > 0)
        {

            ProcessPart processPart = processPartCore.getProcessPart((Part)partCbox.getSelectedItem().getValue(), (Process)processCbox.getSelectedItem().getValue());
            if(processPart != null && processPart.getImagePath() != null)
            {
                org.zkoss.image.Image picImage= new AImage(IMAGE_PATH+processPart.getImagePath());
                picImg.setContent(picImage);
            }
            measurementPartGrid.setModel(new ListModelList((List<MeasurementCheckpoint>)parts));
            measurementPartGrid.setRowRenderer(new MeasurementPartRenderer(
                    editWin,idTxt,lineEditDatebox
                    ,measurementToolEditCbox,partEditCbox,timeEditCbox
                    ,valueEditTxt,processEditCbox,checkpointEditCbox,machineEditCbox,searchBtn));
        }
        else
        {
            setNoRecordFound();
        }

        searchFile();
        
    }

    private void setNoRecordFound()
    {
        List<String> norecords = new ArrayList<String>();
        norecords.add(ResourceUtil.getLabel("measurementpart.norecordfound"));
        measurementPartGrid.setModel(new ListModelList(norecords));
        measurementPartGrid.setRowRenderer(new MeasurementPartRenderer(
                    editWin,idTxt,lineEditDatebox
                    ,measurementToolEditCbox,partEditCbox,timeEditCbox
                    ,valueEditTxt,processEditCbox,checkpointEditCbox,machineEditCbox,searchBtn));
        picImg.setContent(DEFAULT_IMAGE);
        paging.setPageSize(PagingConstant.PAGING_SIZE);
        paging.setDetailed(Boolean.TRUE);
        List<String> noRecords = new ArrayList<String>();
        noRecords.add(ResourceUtil.getLabel("backoffice.machine.message.norecordfound"));
        fileMeasurementPartGrid.setModel(new GridListWrapper<FileMeasurementPart>(fileMeasurementPartGrid, paging, new ListModelList(noRecords), null));
        fileMeasurementPartGrid.setRowRenderer(new FileMeasurementPartRenderer(searchBtn,form,filePathTxt,fileNameTxt));
    }

    private CriteriaObject createCriteria()
    {
        CriteriaObject criteriaObject = new CriteriaObject(FileMeasurementPart.class);
        criteriaObject.addAliasNames("part", "part", CriteriaObject.INNER_JOIN);
        criteriaObject.addAliasNames("process", "process", CriteriaObject.INNER_JOIN);        
        criteriaObject.addAliasNames("machine", "machine", CriteriaObject.INNER_JOIN);

        if(partCbox.getSelectedIndex() != 0)
        {            
            Part part = (Part)partCbox.getSelectedItem().getValue();
            if(part != null)
            {
                criteriaObject.addCriteria(Restrictions.eq("part.id",part.getId()));
            }            
        }
        else
        {
            AlertMessages.alertMessage("measurementpart.alert.message.required.2");
            return null;
        }

        if(processCbox.getSelectedIndex() != 0)
        {
            com.bsd.cse.model.backoffice.part.Process process = (com.bsd.cse.model.backoffice.part.Process)processCbox.getSelectedItem().getValue();
            if(process != null)
            {
                criteriaObject.addCriteria(Restrictions.eq("process.id",process.getId()));
            }
        }
        else
        {
            AlertMessages.alertMessage("measurementpart.alert.message.required.3");
            return null;
        }

        if(machineCbox.getSelectedIndex() != 0)
        {
            Machine machine = (Machine)machineCbox.getSelectedItem().getValue();
            if(machine != null)
            {
                criteriaObject.addCriteria(Restrictions.eq("machine.id",machine.getId()));
            }
        }
        else
        {
            AlertMessages.alertMessage("measurementpart.alert.message.required.3");
            return null;
        }
                

        if(lineDatebox.getValue() != null)
        {
            criteriaObject.addCriteria(Restrictions.eq("measurementDate",lineDatebox.getValue()));
        }

        
     
       

        criteriaObject.setOrderBy("filename");
        criteriaObject.setDefaultSort("filename");
        criteriaObject.setAscending(Boolean.TRUE);
        return criteriaObject;
    }

    private void searchFile() throws Exception
    {
        paging.setPageSize(PagingConstant.PAGING_SIZE);
        paging.setDetailed(Boolean.TRUE);
        HibernateSearchObject<FileMeasurementPart> searchObject = new HibernateSearchObject<FileMeasurementPart>(createCriteria());
        GridSearchResult<FileMeasurementPart> result = searchObject.getSearchResult(paging.getActivePage()*paging.getPageSize(), paging.getPageSize());
        if(result != null && result.getCount()>0)
        {
            fileMeasurementPartGrid.setModel(new GridListWrapper<FileMeasurementPart>(fileMeasurementPartGrid, paging, result, searchObject));
        }
        else
        {
            List<String> noRecords = new ArrayList<String>();
            noRecords.add(ResourceUtil.getLabel("backoffice.machine.message.norecordfound"));
            fileMeasurementPartGrid.setModel(new GridListWrapper<FileMeasurementPart>(fileMeasurementPartGrid, paging, new ListModelList(noRecords), searchObject));
        }


        fileMeasurementPartGrid.setRowRenderer(new FileMeasurementPartRenderer(searchBtn,form,filePathTxt,fileNameTxt));
    }

    private void saveFile(FileMeasurementPart fileMeasurementPart) throws Exception
    {
        InputStream picStream = null;
        try
        {            
            MediaProcessCore.saveData(media, FILE_PATH+fileMeasurementPart.getId()+media.getName().substring(media.getName().lastIndexOf(".")));
        }
        catch(Exception e)
        {
            throw e;
        }        
    }

    private void disabledUploadFile(Boolean disableFlag)
    {
        uploadBtn.setDisabled(disableFlag);
        submitBtn.setDisabled(disableFlag);

    }
}
