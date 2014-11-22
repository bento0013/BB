/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.backoffice.part;

import com.bsd.cse.app.backoffice.CustomerCore;
import com.bsd.cse.app.backoffice.PartCore;
import com.bsd.cse.app.backoffice.SemiMaterialCore;
import com.bsd.cse.app.security.FunctionCore;
import com.bsd.cse.common.Configuration;
import com.bsd.cse.constant.PagingConstant;
import com.bsd.cse.model.backoffice.customer.Customer;
import com.bsd.cse.model.backoffice.material.semi.SemiMaterial;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.security.Function;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.grid.CriteriaObject;
import com.bsd.cse.zk.grid.GridSearchResult;
import com.bsd.cse.zk.grid.HibernateSearchObject;
import com.bsd.cse.zk.listbox.renderer.PartRenderer;
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
public class PartController extends SecurityController{
    private static final long serialVersionUID = -3993123706258278544L;
    private static Log LOG = LogFactory.getLog(PartController.class);
    private Window editWin ;
    private Window viewWin ;
    private Image viewImage;
    private Button closeBtn;
    private Button saveBtn;
    private Toolbarbutton newBtn;
    private Button cancelBtn;
    private Toolbarbutton searchBtn;
    private Textbox idTxt;
    private Textbox partNoSearchTxt;
    private Textbox partNameSearchTxt;
    private Textbox partNoTxt;
    private Textbox partNameTxt;
    private Longbox numProcessesTxt;
    private Longbox minimumStockTxt;              
    private Button uploadBtn;
    private Combobox semiMaterialCbox;
    private Combobox customerCbox;
    private Listbox contentListbox;
    private Image picImg;    
    private Button clearUploadBtn;
    private Button addCusBtn;
    private Button addSemiBtn;
    private static String IMAGE_PATH;    
    private static org.zkoss.image.Image DEFAULT_IMAGE;
    private Object tmpData;
    private List<Customer> customers;
    private List<SemiMaterial> semiMaterials;
    private static Function customerFunction;
    private static Function semiMaterialFunction;
    private Paging paging;
    private Listheader partNoClm;
    private Listheader partNameClm;
    private Listheader numProcessesClm;
    private Listheader customerNameClm;
    private Listheader semiNameClm;
    private Listheader minimumClm;

    static
    {
        try {
            customerFunction = FunctionCore.getFunction(10508L);
            semiMaterialFunction = FunctionCore.getFunction(1050601L);
            IMAGE_PATH = Configuration.getString("cs.image.folder")+"\\part-img\\";
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
        CustomerCore cusCore = new CustomerCore();
        customers = cusCore.getAllList();

        ZKCatalogs.setCustomers(customerCbox, customers);

        SemiMaterialCore semiCore = new SemiMaterialCore();
        semiMaterials = semiCore.getAllList();

        ZKCatalogs.setSemiMaterials(semiMaterialCbox, semiMaterials);
    }

    public void loadContent() throws Exception
    {
        setSortFieldGrid();
        Events.postEvent(Events.ON_CLICK,searchBtn,null);
    }

    private CriteriaObject createCriteria()
    {
        CriteriaObject criteriaObject = new CriteriaObject(Part.class);
        criteriaObject.addAliasNames("customer", "customer", CriteriaObject.INNER_JOIN);
        criteriaObject.addAliasNames("semiMaterial", "semiMaterial", CriteriaObject.INNER_JOIN);
        if(!partNameSearchTxt.getValue().trim().isEmpty())
        {
            criteriaObject.addCriteria(Restrictions.ilike("partName", partNameSearchTxt.getValue().trim(),MatchMode.START));
        }

        if(!partNoSearchTxt.getValue().trim().isEmpty())
        {
            criteriaObject.addCriteria(Restrictions.ilike("partNo", partNoSearchTxt.getValue().trim(),MatchMode.START));
        }

        criteriaObject.setOrderBy("partNo");
        criteriaObject.setDefaultSort("partNo");
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

        addCusBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                
                execution.sendRedirect(customerFunction.getCommand());
            }
        });

        addSemiBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {

                execution.sendRedirect(semiMaterialFunction.getCommand());
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
                String partNo = partNoSearchTxt.getValue();
                String partName = partNameSearchTxt.getValue();
                PartCore core = new PartCore();
                HashMap<String,Object> metaData = core.getList(partNo,partName);
                paging.setPageSize(PagingConstant.PAGING_SIZE);
                paging.setDetailed(Boolean.TRUE);
                HibernateSearchObject<Part> searchObject = new HibernateSearchObject<Part>(createCriteria());
                GridSearchResult<Part> result = searchObject.getSearchResult(paging.getActivePage()*paging.getPageSize(), paging.getPageSize());
                if(result != null && result.getCount()>0)
                {
                    contentListbox.setModel(new ListboxListWrapper<Part>(contentListbox, paging, result, searchObject));
                }
                else
                {
                    List<String> noRecords = new ArrayList<String>();
                    noRecords.add(ResourceUtil.getLabel("backoffice.machine.message.norecordfound"));
                    contentListbox.setModel(new ListboxListWrapper<Part>(contentListbox, paging, new ListModelList(noRecords), searchObject));
                }

                contentListbox.setItemRenderer(new PartRenderer(editWin,idTxt,partNoTxt,partNameTxt,customerCbox,semiMaterialCbox,minimumStockTxt,numProcessesTxt,searchBtn,clearUploadBtn,picImg,viewImage,viewWin));
//                if(((Integer)metaData.get("allRecords")).equals(new Integer(0)))
//                {
//                    ArrayList<String> results = new ArrayList<String>();
//                    results.add(ResourceUtil.getLabel("backoffice.part.message.norecordfound"));
//                    contentListbox.setModel(new ListModelList((List<String>)results));
//                }
//                else
//                {
//                    contentListbox.setModel(new ListModelList((List<Part>)metaData.get("results")));
//
//                }
//
//                contentListbox.setItemRenderer(new PartRenderer(editWin,idTxt,partNoTxt,partNameTxt,customerCbox,semiMaterialCbox,minimumStockTxt,searchBtn,clearUploadBtn,picImg,viewImage,viewWin));
            }
        });

        newBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                clearUploadBtn.setAttribute("object", null);
                idTxt.setValue("");
                partNoTxt.setValue("");
                partNameTxt.setValue("");
                customerCbox.setValue("");
                semiMaterialCbox.setValue("");
                minimumStockTxt.setValue(null);
                picImg.setContent(DEFAULT_IMAGE);
                editWin.doModal();
            }
        });

        saveBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                HttpUserSession userSession = new HttpUserSession();
                Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
                Part part = new Part();
                String id = idTxt.getValue();
                String partNo = partNoTxt.getValue();
                String partName = partNameTxt.getValue();
                Long minimumStock = minimumStockTxt.getValue();
                Long numProcesses = numProcessesTxt.getValue();

                if(partNo.trim().isEmpty())
                {
                    AlertMessages.alertMessage("backoffice.part.alert.message.8");
                    return;
                }

                if(partName.trim().isEmpty())
                {
                    AlertMessages.alertMessage("backoffice.part.alert.message.9");
                    return;
                }

                if(customerCbox.getSelectedItem() == null)
                {
                    AlertMessages.alertMessage("backoffice.part.alert.message.10");
                    return;
                }

                if(semiMaterialCbox.getSelectedItem() == null)
                {
                    AlertMessages.alertMessage("backoffice.part.alert.message.11");
                    return;
                }

                if(minimumStock == null)
                {
                    AlertMessages.alertMessage("backoffice.part.alert.message.12");
                    return;
                }

                if(numProcesses == null)
                {
                    AlertMessages.alertMessage("backoffice.part.alert.message.13");
                    return;
                }  

                Customer customer = (Customer)customerCbox.getSelectedItem().getValue();
                SemiMaterial semiMaterial = (SemiMaterial)semiMaterialCbox.getSelectedItem().getValue();
                
                if(!id.trim().isEmpty() &&!id.equals(""))
                {
                    part.setId(Long.valueOf(id));
                }
                part.setPartNo(partNo);
                part.setPartName(partName);
                part.setNumProcesses(numProcesses);
                if(part.getAmount() == null)
                {
                    part.setAmount(0L);
                }
                part.setMinimumStock(minimumStock);
                if(customer != null)
                {
                    part.setCustomer(customer);
                }

                if(semiMaterial != null)
                {
                    part.setSemiMaterial(semiMaterial);
                }

                if(part.getId() != null)
                {
                    part.setImagePath(part.getId()+".png");
                }

                
                PartCore core = new PartCore();
                try
                {

                    HashMap<String,Object> results = core.save(part, userId);
                    Boolean result = (Boolean)results.get("results");
                    if(result != null && result)
                    {
                        if(part.getImagePath() == null)
                        {                            
                            part.setImagePath(part.getId()+".png");
                            core.save(part, userId);
                        }
                        saveImage(part);
                        editWin.setVisible(false);
                        AlertMessages.successMessage("backoffice.part.alert.message.1");
                        Events.sendEvent(Events.ON_CLICK, searchBtn, null);
                        event.stopPropagation();
                    }
                    else if(result != null && !result)
                    {
                        AlertMessages.failMessage("backoffice.part.alert.message.2");
                        return;
                    }
                }
                catch(Exception e)
                {
                    LOG.info(e.getMessage(),e);
                    if(e.toString().indexOf("ConstraintViolationException") != -1)
                    {
                        AlertMessages.failMessage("backoffice.part.alert.message.3");
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

    private void saveImage(Part model) throws Exception
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
        partNoSearchTxt = (Textbox)getComponent(component, "partNoSearchTxt", partNoSearchTxt);
        partNameSearchTxt = (Textbox)getComponent(component, "partNameSearchTxt", partNameSearchTxt);        
        cancelBtn = (Button)getComponent(editWin, "cancelBtn", cancelBtn);                        
        uploadBtn  = (Button)getComponent(editWin, "uploadBtn", uploadBtn);
        idTxt  = (Textbox)getComponent(editWin, "idTxt", idTxt);
        partNoTxt  = (Textbox)getComponent(editWin, "partNoTxt", partNoTxt);
        partNameTxt  = (Textbox)getComponent(editWin, "partNameTxt", partNameTxt);
        customerCbox  = (Combobox)getComponent(editWin, "customerCbox", customerCbox);
        semiMaterialCbox  = (Combobox)getComponent(editWin, "semiMaterialCbox", semiMaterialCbox);
        minimumStockTxt  = (Longbox)getComponent(editWin, "minimumStockTxt", minimumStockTxt);
        numProcessesTxt  = (Longbox)getComponent(editWin, "numProcessesTxt", numProcessesTxt);
        contentListbox = (Listbox)getComponent(component, "contentListbox", contentListbox);
        searchBtn = (Toolbarbutton)getComponent(component, "searchBtn", searchBtn);
        clearUploadBtn  = (Button)getComponent(editWin, "clearUploadBtn", clearUploadBtn);
        picImg  = (Image)getComponent(editWin, "picImg", picImg);
        addCusBtn= (Button)getComponent(editWin, "addCusBtn", addCusBtn);
        addSemiBtn= (Button)getComponent(editWin, "addSemiBtn", addSemiBtn);
        viewWin = (Window)getComponent(component, "viewWin", viewWin);
        closeBtn = (Button)getComponent(viewWin, "closeBtn", closeBtn);
        viewImage  = (Image)getComponent(viewWin, "viewImage", viewImage);
    }

    private void setSortFieldGrid()
    {
        partNoClm.setSortAscending(new FieldComparator("partNo", false));
        partNoClm.setSortDescending(new FieldComparator("partNo", true));
        partNameClm.setSortAscending(new FieldComparator("partName", false));
        partNameClm.setSortDescending(new FieldComparator("partName", true));
        customerNameClm.setSortAscending(new FieldComparator("customer.name", false));
        customerNameClm.setSortDescending(new FieldComparator("customer.name", true));
        semiNameClm.setSortAscending(new FieldComparator("semiMaterial.semiName", false));
        semiNameClm.setSortDescending(new FieldComparator("semiMaterial.semiName", true));
        minimumClm.setSortAscending(new FieldComparator("minimumStock", false));
        minimumClm.setSortDescending(new FieldComparator("minimumStock", true));
        numProcessesClm.setSortAscending(new FieldComparator("numProcesses", false));
        numProcessesClm.setSortDescending(new FieldComparator("numProcesses", true));
        
    }
}
