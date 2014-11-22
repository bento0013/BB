/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.backoffice.machine;

import com.bsd.cse.app.backoffice.MachineModelCore;
import com.bsd.cse.app.backoffice.MachineModelTypeCore;
import com.bsd.cse.app.security.FunctionCore;
import com.bsd.cse.common.Configuration;
import com.bsd.cse.constant.PagingConstant;
import com.bsd.cse.model.backoffice.machine.MachineModel;
import com.bsd.cse.model.backoffice.machine.MachineModelType;
import com.bsd.cse.model.security.Function;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.grid.CriteriaObject;
import com.bsd.cse.zk.grid.GridSearchResult;
import com.bsd.cse.zk.grid.HibernateSearchObject;
import com.bsd.cse.zk.listbox.renderer.MachineModelRenderer;
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
import org.hibernate.Criteria;
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
public class MachineModelController extends SecurityController{
    private static final long serialVersionUID = -3993123706258278544L;
    private static Log LOG = LogFactory.getLog(MachineModelController.class);
    private Window editWin ;
    private Window viewWin;
    private Image viewImage;
    private Button saveBtn;
    private Toolbarbutton newBtn;
    private Button cancelBtn;
    private Button closeBtn;
    private Toolbarbutton searchBtn;
    private Textbox modelNameTxt;   
    private Textbox idTxt;   
    private Textbox nameTxt;   
    private Combobox typeCbox;
    private Button uploadBtn;
    private Combobox modelTypeCbox;
    private Listbox contentListbox;
    private Image picImg;    
    private Button clearUploadBtn;
    private Button addModelBtn;
    private static String IMAGE_PATH;
    private List<MachineModelType> types = null;
    private static org.zkoss.image.Image DEFAULT_IMAGE;
    private Object tmpData;
    private static Function modelTypeFunction;
    private Listheader nameClm;
    private Listheader typeClm;
    private Paging paging;

    static
    {
        try {
            modelTypeFunction = FunctionCore.getFunction(1050303L);
            IMAGE_PATH = Configuration.getString("cs.image.folder")+"\\machine-model-img\\";
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
        MachineModelTypeCore core = new MachineModelTypeCore();
        types = core.getAllList();
        Comboitem item = new Comboitem();
        item.setLabel("All");
        
        ZKCatalogs.setMachineModelTypes(modelTypeCbox, types);
        List<Comboitem> items =  modelTypeCbox.getItems();
        items.add(0, item);
        modelTypeCbox.setSelectedIndex(0);
        ZKCatalogs.setMachineModelTypes(typeCbox, types);
        
    }

    public void loadContent() throws Exception
    {
        setSortFieldGrid();
        Events.postEvent(Events.ON_CLICK,searchBtn,null);
    }

    private CriteriaObject createCriteria()
    {
        CriteriaObject criteriaObject = new CriteriaObject(MachineModel.class);
        criteriaObject.addAliasNames("type", "type", CriteriaObject.INNER_JOIN);
        if(!modelNameTxt.getValue().trim().isEmpty())
        {
            criteriaObject.addCriteria(Restrictions.ilike("name", modelNameTxt.getValue().trim(),MatchMode.START));
        }
        if(modelTypeCbox.getSelectedItem() != null)
        {
            MachineModelType modelType = (MachineModelType)(modelTypeCbox.getSelectedItem() != null?modelTypeCbox.getSelectedItem().getValue():null);
            if(modelType != null)
            {
                criteriaObject.addCriteria(Restrictions.ilike("type.name", modelType.getName(),MatchMode.START));
            }
        }
        criteriaObject.setOrderBy("name");
        criteriaObject.setDefaultSort("name");
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

        addModelBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                
                execution.sendRedirect(modelTypeFunction.getCommand());
            }
        });

        clearUploadBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                MachineModel model = (MachineModel)event.getTarget().getAttribute("object");
                if(model != null && model.getImagePath() != null)
                {
                    org.zkoss.image.Image userImage= new AImage(IMAGE_PATH+model.getImagePath());
                    picImg.setContent(userImage);
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
//                String modelName = modelNameTxt.getValue();
//                MachineModelType modelType = (MachineModelType)(modelTypeCbox.getSelectedItem() != null?modelTypeCbox.getSelectedItem().getValue():null);
//                MachineModelCore core = new MachineModelCore();
//                HashMap<String,Object> metaData = core.getList(modelName,(modelType!=null?modelType.getId():null));
                paging.setPageSize(PagingConstant.PAGING_SIZE);
                paging.setDetailed(Boolean.TRUE);
                HibernateSearchObject<MachineModel> searchObject = new HibernateSearchObject<MachineModel>(createCriteria());
                GridSearchResult<MachineModel> result = searchObject.getSearchResult(paging.getActivePage()*paging.getPageSize(), paging.getPageSize());
                if(result != null && result.getCount()>0)
                {
                    contentListbox.setModel(new ListboxListWrapper<MachineModel>(contentListbox, paging, result, searchObject));
                }
                else
                {
                    List<String> noRecords = new ArrayList<String>();
                    noRecords.add(ResourceUtil.getLabel("backoffice.machinemodel.message.norecordfound"));
                    contentListbox.setModel(new ListboxListWrapper<MachineModel>(contentListbox, paging, new ListModelList(noRecords), searchObject));
                }

                contentListbox.setItemRenderer(new MachineModelRenderer(editWin,idTxt,nameTxt,typeCbox,searchBtn,clearUploadBtn,picImg,viewImage,viewWin));
//                if(((Integer)metaData.get("allRecords")).equals(new Integer(0)))
//                {
//                    ArrayList<String> results = new ArrayList<String>();
//                    results.add(ResourceUtil.getLabel("backoffice.machinemodel.message.norecordfound"));
//                    contentListbox.setModel(new ListModelList((List<String>)results));
//                }
//                else
//                {
//                    contentListbox.setModel(new ListModelList((List<MachineModelType>)metaData.get("results")));
//
//                }
//
//                contentListbox.setItemRenderer(new MachineModelRenderer(editWin,idTxt,nameTxt,typeCbox,searchBtn,clearUploadBtn,picImg,viewImage,viewWin));
            }
        });

        newBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                clearUploadBtn.setAttribute("object", null);
                idTxt.setValue("");
                nameTxt.setValue("");                                
                typeCbox.setSelectedIndex(-1);
                picImg.setContent(DEFAULT_IMAGE);
                editWin.doModal();
            }
        });

        saveBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                HttpUserSession userSession = new HttpUserSession();
                Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
                MachineModel machineModel = new MachineModel();
                String id = idTxt.getValue();
                String name = nameTxt.getValue();

                if(name.trim().isEmpty())
                {
                     AlertMessages.alertMessage("backoffice.machinemodel.alert.message.8");
                    return;
                }

                if(typeCbox.getSelectedItem() == null)
                {
                    AlertMessages.alertMessage("backoffice.machinemodel.alert.message.9");
                    return;
                }


                MachineModelType modelType = (MachineModelType)typeCbox.getSelectedItem().getValue();
                if(!id.trim().isEmpty() &&!id.equals(""))
                {
                    machineModel.setId(Long.valueOf(id));
                }
                machineModel.setName(name);
                if(modelType != null)
                {
                    machineModel.setType(modelType);
                }

                if(machineModel.getId() != null)
                {
                    machineModel.setImagePath(machineModel.getId()+".png");
                }

                
                MachineModelCore core = new MachineModelCore();
                try
                {
                    HashMap<String,Object> results = core.save(machineModel, userId);
                    Boolean result = (Boolean)results.get("results");
                    if(result != null && result)
                    {
                        if(machineModel.getImagePath() == null)
                        {                            
                            machineModel.setImagePath(machineModel.getId()+".png");
                            core.save(machineModel, userId);
                        }
                        saveImage(machineModel);
                        editWin.setVisible(false);
                        AlertMessages.successMessage("backoffice.machinemodel.alert.message.1");
                        Events.sendEvent(Events.ON_CLICK, searchBtn, null);
                        event.stopPropagation();
                    }
                    else if(result != null && !result)
                    {
                        AlertMessages.failMessage("backoffice.machinemodel.alert.message.2");
                        return;
                    }
                }
                catch(Exception e)
                {
                    if(e.toString().indexOf("ConstraintViolationException") != -1)
                    {
                        AlertMessages.failMessage("backoffice.machinemodel.alert.message.3");
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

    private void saveImage(MachineModel model) throws Exception
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
        nameClm = (Listheader)getComponent(component, "nameClm", nameClm);
        typeClm = (Listheader)getComponent(component, "typeClm", typeClm);
        paging =  (Paging)getComponent(component, "paging", paging);
        newBtn = (Toolbarbutton)getComponent(component, "newBtn", newBtn);
        saveBtn = (Button)getComponent(editWin, "saveBtn", saveBtn);
        modelTypeCbox = (Combobox)getComponent(component, "modelTypeCbox", modelTypeCbox);
        cancelBtn = (Button)getComponent(editWin, "cancelBtn", cancelBtn);
        nameTxt  = (Textbox)getComponent(editWin, "nameTxt", nameTxt);
        typeCbox  = (Combobox)getComponent(editWin, "typeCbox", typeCbox);
        uploadBtn  = (Button)getComponent(editWin, "uploadBtn", uploadBtn);
        idTxt  = (Textbox)getComponent(editWin, "idTxt", idTxt);
        contentListbox = (Listbox)getComponent(component, "contentListbox", contentListbox);
        searchBtn = (Toolbarbutton)getComponent(component, "searchBtn", searchBtn);
        clearUploadBtn  = (Button)getComponent(editWin, "clearUploadBtn", clearUploadBtn);
        picImg  = (Image)getComponent(editWin, "picImg", picImg);
        addModelBtn= (Button)getComponent(editWin, "addModelBtn", addModelBtn);
        viewWin = (Window)getComponent(component, "viewWin", viewWin);
        viewImage = (Image)getComponent(viewWin,"viewImage",viewImage);
        closeBtn = (Button)getComponent(viewWin,"closeBtn",closeBtn);
    }

    private void setSortFieldGrid()
    {
        nameClm.setSortAscending(new FieldComparator("name", false));
        nameClm.setSortDescending(new FieldComparator("name", true));
        typeClm.setSortAscending(new FieldComparator("type.name", false));
        typeClm.setSortDescending(new FieldComparator("type.name", true));
    }
}
