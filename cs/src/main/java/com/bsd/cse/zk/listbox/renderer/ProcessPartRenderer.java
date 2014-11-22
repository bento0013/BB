/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.listbox.renderer;

import com.bsd.cse.app.backoffice.PartCore;
import com.bsd.cse.app.backoffice.ProcessPartCore;
import com.bsd.cse.common.Configuration;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.backoffice.part.ProcessPart;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import java.io.IOException;
import java.util.HashMap;
import org.zkoss.image.AImage;
import org.zkoss.util.ResourceUtil;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.A;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

/**
 *
 * @author bento
 */
public class ProcessPartRenderer implements ListitemRenderer{

    private Window win;
    private Window viewWin;
    private Image viewImg;
    private Longbox partProcessIdTxt;
    private Combobox partCbox;
    private Combobox processCbox;
//    private Combobox machineModelCbox;
    private Toolbarbutton searchBtn;
    private Button clearUploadBtn;
    private Image picImg;
    private static String IMAGE_PATH;
    private static org.zkoss.image.Image DEFAULT_IMAGE;

    static
    {
        try {
            IMAGE_PATH = Configuration.getString("cs.image.folder")+"\\process-part-img\\";
            DEFAULT_IMAGE = new AImage(IMAGE_PATH+"no-image.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex) {
             ex.printStackTrace();
        }
    }

    
    public ProcessPartRenderer(Window win,Longbox partProcessIdTxt,Combobox partCbox,Combobox processCbox,Toolbarbutton searchBtn,Button clearUploadBtn,Image picImg,Image viewImg,Window viewWin)
    {
        this.partProcessIdTxt = partProcessIdTxt;
        this.win = win;
        this.partCbox = partCbox;
        this.processCbox = processCbox;
//        this.machineModelCbox = machineModelCbox;
        this.searchBtn = searchBtn;
        this.clearUploadBtn = clearUploadBtn;        
        this.picImg=picImg;
        this.viewWin=viewWin;
        this.viewImg=viewImg;

    }
    @Override
    public void render(Listitem lstm, Object o) throws Exception {
        if(o instanceof String)
        {
            Listcell cell = new Listcell();
            cell.setSpan(6);
            cell.appendChild(new Label((String)o));
            cell.setSclass("cell-align-center");
            cell.setParent(lstm);
        }
        else if(o instanceof ProcessPart)
        {
            ProcessPart object = (ProcessPart)o;
            
            Listcell cell = new Listcell();
            cell.appendChild(new Label(String.valueOf(object.getProcess().getProcessName())));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");
        
            cell = new Listcell();
            cell.appendChild(new Label(object.getPart().getPartNo()));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

//            cell = new Listcell();
//            cell.appendChild(new Label(object.getMachineModel().getName()));
//            cell.setParent(lstm);
//            cell.setSclass("cell-align-left");

            cell = new Listcell();
            A a = new A(ResourceUtil.getLabel("backoffice.view.page.label.viewimage"));
            a.addEventListener(Events.ON_CLICK, new ViewObjectEventListener());
            a.setAttribute("object", o);
            cell.appendChild(a);
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            Listcell cell5 = new Listcell();
            Toolbarbutton editBtn = new Toolbarbutton();
            editBtn.setImage("/images/backoffice/edit.png");
            editBtn.setTooltiptext(ResourceUtil.getLabel("backoffice.partprocess.page.btn.edit"));
            editBtn.addEventListener(Events.ON_CLICK, new EditObjectEventListener());
            editBtn.setAttribute("object", o);
            editBtn.setParent(cell5);

            Toolbarbutton deleteBtn = new Toolbarbutton();
            deleteBtn.setImage("/images/backoffice/delete.png");
            deleteBtn.setTooltiptext(ResourceUtil.getLabel("backoffice.partprocess.page.btn.delete"));
            deleteBtn.addEventListener(Events.ON_CLICK, new DeleteObjectEventListener());
            deleteBtn.setAttribute("object", o);
            deleteBtn.setParent(cell5);

            cell5.setParent(lstm);
            cell5.setSclass("cell-align-left");
        }
        
    }

    class ViewObjectEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            ProcessPart object = (ProcessPart)event.getTarget().getAttribute("object");
            if(object != null && object.getImagePath() != null)
            {
                org.zkoss.image.Image objImage= new AImage(IMAGE_PATH+object.getImagePath());
                viewImg.setContent(objImage);
            }
            else
            {
                viewImg.setContent(DEFAULT_IMAGE);
            }

            viewWin.doModal();
        }

    }

    class EditObjectEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            ProcessPart object = (ProcessPart)event.getTarget().getAttribute("object");
            partProcessIdTxt.setValue(object.getId());
            partCbox.setDisabled(true);
            processCbox.setDisabled(true);
            partCbox.setValue(object.getPart().getPartNo());
            processCbox.setValue(object.getProcess().getProcessName());
//            machineModelCbox.setValue(object.getMachineModel().getName());
            
            clearUploadBtn.setAttribute("object", object);
            if(object != null && object.getImagePath() != null)
            {
                org.zkoss.image.Image objImage= new AImage(IMAGE_PATH+object.getImagePath());
                picImg.setContent(objImage);
            }
            else
            {
                picImg.setContent(DEFAULT_IMAGE);
            }

            win.doModal();
        }

    }

    class DeleteObjectEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            HttpUserSession userSession = new HttpUserSession();            
            ProcessPart object = (ProcessPart)event.getTarget().getAttribute("object");
            int messageValue = AlertMessages.confirmMessage("backoffice.partprocess.alert.message.4");
            if(messageValue == 0 || messageValue == AlertMessages.NO)
            {
                return;
            }
            ProcessPartCore core = new ProcessPartCore();
            if(!core.checkCanDelete(object))
            {
                AlertMessages.alertMessage("backoffice.partprocess.alert.message.5");
                return ;
            }
            
            HashMap<String,Object> results = core.delete(object);
            if((Boolean)results.get("results"))
            {
                AlertMessages.alertMessage("backoffice.partprocess.alert.message.6");
                Events.sendEvent(Events.ON_CLICK,searchBtn, null);
            }
            else
            {
                AlertMessages.alertMessage("backoffice.partprocess.alert.message.7");
            }
        }

    }

}
