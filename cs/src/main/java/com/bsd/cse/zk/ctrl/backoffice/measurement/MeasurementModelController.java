/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.backoffice.measurement;

import com.bsd.cse.app.backoffice.MeasurementToolModelCore;
import com.bsd.cse.app.backoffice.MeasurementToolTypeCore;
import com.bsd.cse.app.security.FunctionCore;
import com.bsd.cse.common.Configuration;
import com.bsd.cse.constant.PagingConstant;
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
public class MeasurementModelController extends SecurityController{
    private static final long serialVersionUID = -3993123706258278544L;
    private static Log LOG = LogFactory.getLog(MeasurementModelController.class);
    private Window editWin ;
    private Window viewWin;
    private Image viewImage;
    private Button closeBtn;
    private Button saveBtn;
    private Toolbarbutton newBtn;
    private Button cancelBtn;
    private Toolbarbutton searchBtn;
    private Textbox modelNameTxt;   
    private Textbox idTxt;   
    private Textbox nameTxt;
    private Textbox descTxt;
    private Combobox typeCbox;
    private Button uploadBtn;
    private Combobox modelTypeCbox;
    private Listbox contentListbox;
    private Image picImg;    
    private Button clearUploadBtn;
    private Button addModelBtn;
    private static String IMAGE_PATH;
    private List<MeasurementToolType> types = null;
    private static org.zkoss.image.Image DEFAULT_IMAGE;
    private Object tmpData;
    private Paging paging;
    private Listheader nameClm;
    private Listheader typeClm;
    private Listheader descClm;
    private static Function modelTypeFunction;

    static
    {
        try {
            modelTypeFunction = FunctionCore.getFunction(1050403L);
            IMAGE_PATH = Configuration.getString("cs.image.folder")+"\\measurement-tool-model-img\\";
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
        MeasurementToolTypeCore core = new MeasurementToolTypeCore();
        types = core.getAllList();
        Comboitem item = new Comboitem();
        item.setLabel("All");
        
        ZKCatalogs.setMeasurementModelTypes(modelTypeCbox, types);
        List<Comboitem> items =  modelTypeCbox.getItems();
        items.add(0, item);
        modelTypeCbox.setSelectedIndex(0);
        ZKCatalogs.setMeasurementModelTypes(typeCbox, types);
        
    }

    public void loadContent() throws Exception
    {
        setSortFieldGrid();
        Events.postEvent(Events.ON_CLICK,searchBtn,null);
    }

    private CriteriaObject createCriteria()
    {
        CriteriaObject criteriaObject = new CriteriaObject(MeasurementToolModel.class);
        criteriaObject.addAliasNames("type", "type", CriteriaObject.INNER_JOIN);
        if(!modelNameTxt.getValue().trim().isEmpty())
        {
            criteriaObject.addCriteria(Restrictions.ilike("name", modelNameTxt.getValue().trim(),MatchMode.START));
        }
        if(modelTypeCbox.getSelectedItem() != null)
        {
            MeasurementToolType modelType = (MeasurementToolType)(modelTypeCbox.getSelectedItem() != null?modelTypeCbox.getSelectedItem().getValue():null);
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
                MeasurementToolModel model = (MeasurementToolModel)event.getTarget().getAttribute("object");
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
//                MeasurementToolType modelType = (MeasurementToolType)(modelTypeCbox.getSelectedItem() != null?modelTypeCbox.getSelectedItem().getValue():null);
//                MeasurementToolModelCore core = new MeasurementToolModelCore();
//                HashMap<String,Object> metaData = core.getList(modelName,(modelType!=null?modelType.getId():null));
                paging.setPageSize(PagingConstant.PAGING_SIZE);
                paging.setDetailed(Boolean.TRUE);
                HibernateSearchObject<MeasurementToolModel> searchObject = new HibernateSearchObject<MeasurementToolModel>(createCriteria());
                GridSearchResult<MeasurementToolModel> result = searchObject.getSearchResult(paging.getActivePage()*paging.getPageSize(), paging.getPageSize());
                if(result != null && result.getCount()>0)
                {
                    contentListbox.setModel(new ListboxListWrapper<MeasurementToolModel>(contentListbox, paging, result, searchObject));
                }
                else
                {
                    List<String> noRecords = new ArrayList<String>();
                    noRecords.add(ResourceUtil.getLabel("backoffice.measurementmodel.message.norecordfound"));
                    contentListbox.setModel(new ListboxListWrapper<MeasurementToolModel>(contentListbox, paging, new ListModelList(noRecords), searchObject));
                }

                contentListbox.setItemRenderer(new MeasurementModelRenderer(editWin,idTxt,nameTxt,descTxt,typeCbox,searchBtn,clearUploadBtn,picImg,viewImage,viewWin));
//                if(((Integer)metaData.get("allRecords")).equals(new Integer(0)))
//                {
//                    ArrayList<String> results = new ArrayList<String>();
//                    results.add(ResourceUtil.getLabel("backoffice.measurementmodel.message.norecordfound"));
//                    contentListbox.setModel(new ListModelList((List<String>)results));
//                }
//                else
//                {
//                    contentListbox.setModel(new ListModelList((List<MeasurementToolType>)metaData.get("results")));
//
//                }
//
//                contentListbox.setItemRenderer(new MeasurementModelRenderer(editWin,idTxt,nameTxt,descTxt,typeCbox,searchBtn,clearUploadBtn,picImg,viewImage,viewWin));
            }
        });

        newBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                clearUploadBtn.setAttribute("object", null);
                idTxt.setValue("");
                nameTxt.setValue("");
                descTxt.setValue("");
                typeCbox.setValue("");                
                picImg.setContent(DEFAULT_IMAGE);
                editWin.doModal();
            }
        });

        saveBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                HttpUserSession userSession = new HttpUserSession();
                Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
                MeasurementToolModel measurementToolModel = new MeasurementToolModel();
                String id = idTxt.getValue();
                String name = nameTxt.getValue();
                String desc = descTxt.getValue();

                if(name.trim().isEmpty())
                {
                    AlertMessages.alertMessage("backoffice.measurementmodel.alert.message.8");
                    return;
                }
                
                if(typeCbox.getSelectedItem() == null)
                {
                    AlertMessages.alertMessage("backoffice.measurementmodel.alert.message.9");
                    return;
                }

                MeasurementToolType modelType = (MeasurementToolType)typeCbox.getSelectedItem().getValue();
                
                if(!id.trim().isEmpty() &&!id.equals(""))
                {
                    measurementToolModel.setId(Long.valueOf(id));
                }
                measurementToolModel.setName(name);
                measurementToolModel.setDescription(desc);
                if(modelType != null)
                {
                    measurementToolModel.setType(modelType);
                }

                if(measurementToolModel.getId() != null)
                {
                    measurementToolModel.setImagePath(measurementToolModel.getId()+".png");
                }

                
                MeasurementToolModelCore core = new MeasurementToolModelCore();
                try
                {

                    HashMap<String,Object> results = core.save(measurementToolModel, userId);
                    Boolean result = (Boolean)results.get("results");
                    if(result != null && result)
                    {
                        if(measurementToolModel.getImagePath() == null)
                        {                            
                            measurementToolModel.setImagePath(measurementToolModel.getId()+".png");
                            core.save(measurementToolModel, userId);
                        }
                        saveImage(measurementToolModel);
                        editWin.setVisible(false);
                        AlertMessages.successMessage("backoffice.measurementmodel.alert.message.1");
                        Events.sendEvent(Events.ON_CLICK, searchBtn, null);
                        event.stopPropagation();
                    }
                    else if(result != null && !result)
                    {
                        AlertMessages.failMessage("backoffice.measurementmodel.alert.message.2");
                        return;
                    }
                }
                catch(Exception e)
                {
                    LOG.info(e.getMessage(),e);
                    if(e.toString().indexOf("ConstraintViolationException") != -1)
                    {
                        AlertMessages.failMessage("backoffice.measurementmodel.alert.message.3");
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

    private void saveImage(MeasurementToolModel model) throws Exception
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
        modelTypeCbox = (Combobox)getComponent(component, "modelTypeCbox", modelTypeCbox);
        cancelBtn = (Button)getComponent(editWin, "cancelBtn", cancelBtn);
        nameTxt  = (Textbox)getComponent(editWin, "nameTxt", nameTxt);

        descTxt  = (Textbox)getComponent(editWin, "descTxt", descTxt);
        typeCbox  = (Combobox)getComponent(editWin, "typeCbox", typeCbox);
        uploadBtn  = (Button)getComponent(editWin, "uploadBtn", uploadBtn);
        idTxt  = (Textbox)getComponent(editWin, "idTxt", idTxt);
        contentListbox = (Listbox)getComponent(component, "contentListbox", contentListbox);
        searchBtn = (Toolbarbutton)getComponent(component, "searchBtn", searchBtn);
        clearUploadBtn  = (Button)getComponent(editWin, "clearUploadBtn", clearUploadBtn);
        picImg  = (Image)getComponent(editWin, "picImg", picImg);
        addModelBtn= (Button)getComponent(editWin, "addModelBtn", addModelBtn);
    }

    private void setSortFieldGrid()
    {
        nameClm.setSortAscending(new FieldComparator("name", false));
        nameClm.setSortDescending(new FieldComparator("name", true));
        descClm.setSortAscending(new FieldComparator("description", false));
        descClm.setSortDescending(new FieldComparator("description", true));
        typeClm.setSortAscending(new FieldComparator("type.name", false));
        typeClm.setSortDescending(new FieldComparator("type.name", true));
    }
}
