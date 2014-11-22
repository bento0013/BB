/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.backoffice.part;

import com.bsd.cse.app.backoffice.MachineModelCore;
import com.bsd.cse.app.backoffice.PartCore;
import com.bsd.cse.app.backoffice.ProcessCore;
import com.bsd.cse.app.backoffice.ProcessPartCore;
import com.bsd.cse.app.security.FunctionCore;
import com.bsd.cse.common.Configuration;
import com.bsd.cse.model.backoffice.machine.MachineModel;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.backoffice.part.Process;
import com.bsd.cse.model.backoffice.part.ProcessPart;
import com.bsd.cse.model.security.Function;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.listbox.renderer.MeasurementModelRenderer;
import com.bsd.cse.zk.listbox.renderer.PartRenderer;
import com.bsd.cse.zk.listbox.renderer.ProcessPartRenderer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

/**
 *
 * @author bento
 */
public class ProcessPartController extends SecurityController{
    private static final long serialVersionUID = -3993123706258278544L;
    private static Log LOG = LogFactory.getLog(ProcessPartController.class);
    private Window editWin ;
    private Window viewWin ;
    private Image viewImage;
    private Button closeBtn;
    private Button saveBtn;
    private Toolbarbutton newBtn;
    private Button cancelBtn;
    private Toolbarbutton searchBtn;
    private Longbox partProcessIdTxt;
    private Combobox processSearchCbox;
    private Combobox partSearchCbox;
//    private Combobox machineModelSearchCbox;
    private Combobox processCbox;
    private Combobox partCbox;
//    private Combobox machineModelCbox;
    private Button uploadBtn;
    private Listbox contentListbox;
    private Image picImg;    
    private Button clearUploadBtn;
    private Button addPartBtn;
//    private Button addMachineModelBtn;
    private static String IMAGE_PATH;    
    private static org.zkoss.image.Image DEFAULT_IMAGE;
    private Object tmpData;
    private List<Part> parts;
    private List<Process> processes;
//    private List<MachineModel> machineModels;
    private static Function partFunction;
    private static Function machineModelFunction;

    static
    {
        try {
            partFunction = FunctionCore.getFunction(1050201L);
            machineModelFunction = FunctionCore.getFunction(1050302L);
            IMAGE_PATH = Configuration.getString("cs.image.folder")+"\\process-part-img\\";
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

//        MachineModelCore machineModelCore = new MachineModelCore();
//        machineModels = machineModelCore.getAllList();
//        ZKCatalogs.setMachineModels(machineModelSearchCbox, machineModels);
//        item = new Comboitem();
//        item.setLabel("All");
//        items =  machineModelSearchCbox.getItems();
//        items.add(0, item);
//        machineModelSearchCbox.setSelectedIndex(0);
//        ZKCatalogs.setMachineModels(machineModelCbox, machineModels);

        ProcessCore processCore = new ProcessCore();
        processes = processCore.getAllList();
        item = new Comboitem();
        item.setLabel("All");
        ZKCatalogs.setProcesses(processSearchCbox, processes);
        items =  processSearchCbox.getItems();
        items.add(0, item);
        processSearchCbox.setSelectedIndex(0);
        ZKCatalogs.setProcesses(processCbox, processes);
    }

    public void loadContent() throws Exception
    {
        Events.postEvent(Events.ON_CLICK,searchBtn,null);
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

//        addMachineModelBtn.addEventListener(Events.ON_CLICK, new EventListener() {
//
//            @Override
//            public void onEvent(Event event) throws Exception {
//
//                execution.sendRedirect(machineModelFunction.getCommand());
//            }
//        });

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
//                MachineModel model = (MachineModel)(machineModelSearchCbox.getSelectedItem() != null?machineModelSearchCbox.getSelectedItem().getValue():null);
                Part part = (Part)(partSearchCbox.getSelectedItem() != null?partSearchCbox.getSelectedItem().getValue():null);
                Process process = (Process)(processSearchCbox.getSelectedItem() != null?processSearchCbox.getSelectedItem().getValue():null);
                ProcessPartCore core = new ProcessPartCore();
                HashMap<String,Object> metaData = core.getList(part,process);
                if(((Integer)metaData.get("allRecords")).equals(new Integer(0)))
                {
                    ArrayList<String> results = new ArrayList<String>();
                    results.add(ResourceUtil.getLabel("backoffice.partprocess.message.norecordfound"));
                    contentListbox.setModel(new ListModelList((List<String>)results));
                }
                else
                {
                    contentListbox.setModel(new ListModelList((List<Part>)metaData.get("results")));

                }

                contentListbox.setItemRenderer(new ProcessPartRenderer(editWin,partProcessIdTxt,partCbox,processCbox,searchBtn,clearUploadBtn,picImg,viewImage,viewWin));
            }
        });

        newBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                clearUploadBtn.setAttribute("object", null);
                partProcessIdTxt.setValue(null);
                partCbox.setValue("");
                processCbox.setValue("");
                partCbox.setDisabled(false);
                processCbox.setDisabled(false);
//                machineModelCbox.setValue("");
                picImg.setContent(DEFAULT_IMAGE);
                editWin.doModal();
            }
        });

        saveBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                HttpUserSession userSession = new HttpUserSession();
                Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
                ProcessPart processPart = new ProcessPart();
              
                Long id = partProcessIdTxt.getValue();

                if(id != null)
                {
                    processPart.setId(id);
                }
                
                if(partCbox.getSelectedItem() == null)
                {
                    AlertMessages.alertMessage("backoffice.partprocess.alert.message.8");
                    return;
                }

                if(processCbox.getSelectedItem() == null)
                {
                    AlertMessages.alertMessage("backoffice.partprocess.alert.message.9");
                    return;
                }

//                if(machineModelCbox.getSelectedItem() == null)
//                {
//                    AlertMessages.alertMessage("backoffice.partprocess.alert.message.10");
//                    return;
//                }

                Part part = (Part)partCbox.getSelectedItem().getValue();
                Process process = (Process)processCbox.getSelectedItem().getValue();
//                MachineModel machineModel = (MachineModel)machineModelCbox.getSelectedItem().getValue();
                
                      
                if(part != null)
                {
                    processPart.setPart(part);
                }

                if(process != null)
                {
                    processPart.setProcess(process);
                }

//                if(machineModel != null)
//                {
//                    processPart.setMachineModel(machineModel);
//                }

                if(processPart.getId() != null)
                {
                    processPart.setImagePath(processPart.getId()+".png");
                }

                
                ProcessPartCore core = new ProcessPartCore();
                try
                {

                    HashMap<String,Object> results = core.save(processPart, userId);
                    Boolean result = (Boolean)results.get("results");                    
                    if(result != null && result)
                    {
                        if(processPart.getImagePath() == null)
                        {                            
                            processPart.setImagePath(processPart.getId()+".png");
                            core.save(processPart, userId);
                        }
                        saveImage(processPart);
                        editWin.setVisible(false);
                        AlertMessages.successMessage("backoffice.partprocess.alert.message.1");
                        Events.sendEvent(Events.ON_CLICK, searchBtn, null);
                        event.stopPropagation();
                    }
                    else if(result != null && !result)
                    {
                        AlertMessages.failMessage("backoffice.partprocess.alert.message.2");
                        return;
                    }
                }
                catch(Exception e)
                {
                    LOG.info(e.getMessage(),e);
                    if(e.toString().indexOf("ConstraintViolationException") != -1)
                    {
                        AlertMessages.failMessage("backoffice.partprocess.alert.message.3");
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

    private void saveImage(ProcessPart model) throws Exception
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
        newBtn = (Toolbarbutton)getComponent(component, "newBtn", newBtn);
        saveBtn = (Button)getComponent(editWin, "saveBtn", saveBtn);      
        cancelBtn = (Button)getComponent(editWin, "cancelBtn", cancelBtn);                        
        uploadBtn  = (Button)getComponent(editWin, "uploadBtn", uploadBtn);
        partSearchCbox  = (Combobox)getComponent(component, "partSearchCbox", partSearchCbox);
        processSearchCbox  = (Combobox)getComponent(component, "processSearchCbox", processSearchCbox);
//        machineModelSearchCbox  = (Combobox)getComponent(component, "machineModelSearchCbox", machineModelSearchCbox);
        partCbox  = (Combobox)getComponent(editWin, "partCbox", partCbox);
        processCbox  = (Combobox)getComponent(editWin, "processCbox", processCbox);
//        machineModelCbox  = (Combobox)getComponent(editWin, "machineModelCbox", machineModelCbox);
        partProcessIdTxt = (Longbox)getComponent(editWin, "partProcessIdTxt", partProcessIdTxt);
        contentListbox = (Listbox)getComponent(component, "contentListbox", contentListbox);
        searchBtn = (Toolbarbutton)getComponent(component, "searchBtn", searchBtn);
        clearUploadBtn  = (Button)getComponent(editWin, "clearUploadBtn", clearUploadBtn);
        picImg  = (Image)getComponent(editWin, "picImg", picImg);
        addPartBtn = (Button)getComponent(editWin, "addPartBtn", addPartBtn);
//        addMachineModelBtn = (Button)getComponent(editWin, "addMachineModelBtn", addMachineModelBtn);
        viewWin = (Window)getComponent(component, "viewWin", viewWin);
        closeBtn = (Button)getComponent(viewWin, "closeBtn", closeBtn);
        viewImage  = (Image)getComponent(viewWin, "viewImage", viewImage);
    }
}
