/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.listbox.renderer;

import com.bsd.cse.app.backoffice.MachineModelCore;
import com.bsd.cse.app.backoffice.MachineModelTypeCore;
import com.bsd.cse.common.Configuration;
import com.bsd.cse.model.backoffice.machine.MachineModel;
import com.bsd.cse.model.backoffice.machine.MachineModelType;
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
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

/**
 *
 * @author bento
 */
public class MachineModelRenderer implements ListitemRenderer{

    private Window win;
    private Textbox idTxt;
    private Textbox nameTxt;
    private Combobox typeCbox;
    private Toolbarbutton searchBtn;
    private Button clearUploadBtn;
    private Image picImg;
    private Window viewWin;
    private Image viewImg;
    private static String IMAGE_PATH;
    private static org.zkoss.image.Image DEFAULT_IMAGE;

    static
    {
        try {
            IMAGE_PATH = Configuration.getString("cs.image.folder")+"\\machine-model-img\\";
            DEFAULT_IMAGE = new AImage(IMAGE_PATH+"no-image.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex) {
             ex.printStackTrace();
        }
    }

    
    public MachineModelRenderer(Window win,Textbox idTxt,Textbox nameTxt,Combobox typeCbox,Toolbarbutton searchBtn,Button clearUploadBtn,Image picImg,Image viewImg,Window viewWin)
    {
        this.win = win;
        this.idTxt=idTxt;
        this.nameTxt=nameTxt;
        this.typeCbox=typeCbox;
        this.searchBtn = searchBtn;
        this.clearUploadBtn = clearUploadBtn;
        this.picImg=picImg;
        this.viewImg = viewImg;
        this.viewWin = viewWin;

    }
    @Override
    public void render(Listitem lstm, Object o) throws Exception {
        if(o instanceof String)
        {
            Listcell cell = new Listcell();
            cell.setSpan(5);
            cell.appendChild(new Label((String)o));
            cell.setSclass("cell-align-center");
            cell.setParent(lstm);
        }
        else if(o instanceof MachineModel)
        {
            MachineModel object = (MachineModel)o;
            
//            Listcell cell = new Listcell();
//            cell.appendChild(new Label(String.valueOf(object.getId())));
//            cell.setParent(lstm);
//            cell.setSclass("cell-align-left");

            Listcell cell1 = new Listcell();
            cell1.appendChild(new Label(object.getName()));
            cell1.setParent(lstm);
            cell1.setSclass("cell-align-left");

            Listcell cell2 = new Listcell();
            cell2.appendChild(new Label(object.getType().getName()));
            cell2.setParent(lstm);
            cell2.setSclass("cell-align-left");

            Listcell cell3 = new Listcell();
            A a = new A(ResourceUtil.getLabel("backoffice.view.page.label.viewimage"));
            a.addEventListener(Events.ON_CLICK, new ViewObjectEventListener());
            a.setAttribute("object", o);
            cell3.appendChild(a);
            cell3.setParent(lstm);
            cell3.setSclass("cell-align-left");

            Listcell cell4 = new Listcell();
            Toolbarbutton editBtn = new Toolbarbutton();
            editBtn.setImage("/images/backoffice/edit.png");
            editBtn.setTooltiptext(ResourceUtil.getLabel("backoffice.machinemodel.page.btn.edit"));
            editBtn.addEventListener(Events.ON_CLICK, new EditObjectEventListener());
            editBtn.setAttribute("object", o);
            editBtn.setParent(cell4);

            Toolbarbutton deleteBtn = new Toolbarbutton();
            deleteBtn.setImage("/images/backoffice/delete.png");
            deleteBtn.setTooltiptext(ResourceUtil.getLabel("backoffice.machinemodel.page.btn.delete"));
            deleteBtn.addEventListener(Events.ON_CLICK, new DeleteObjectEventListener());
            deleteBtn.setAttribute("object", o);
            deleteBtn.setParent(cell4);

            cell4.setParent(lstm);
            cell4.setSclass("cell-align-left");
        }
        
    }

    class ViewObjectEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            MachineModel object = (MachineModel)event.getTarget().getAttribute("object");            
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
            MachineModel object = (MachineModel)event.getTarget().getAttribute("object");
            idTxt.setValue(String.valueOf(object.getId()));
            nameTxt.setValue(String.valueOf(object.getName()));
            typeCbox.setValue(object.getType().getName());
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
            MachineModel object = (MachineModel)event.getTarget().getAttribute("object");
            int messageValue = AlertMessages.confirmMessage("backoffice.machinemodel.alert.message.4");
            if(messageValue == 0 || messageValue == AlertMessages.NO)
            {
                return;
            }
            MachineModelCore core = new MachineModelCore();
            if(!core.checkCanDelete(object))
            {
                AlertMessages.alertMessage("backoffice.machinemodel.alert.message.5");
                return ;
            }
            
            HashMap<String,Object> results = core.delete(object);
            if((Boolean)results.get("results"))
            {
                AlertMessages.alertMessage("backoffice.machinemodel.alert.message.6");
                Events.sendEvent(Events.ON_CLICK,searchBtn, null);
            }
            else
            {
                AlertMessages.alertMessage("backoffice.machinemodel.alert.message.7");
            }
        }

    }

}
