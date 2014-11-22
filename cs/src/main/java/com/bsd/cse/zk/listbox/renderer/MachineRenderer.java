/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.listbox.renderer;

import com.bsd.cse.app.backoffice.MachineCore;
import com.bsd.cse.app.backoffice.MachineModelCore;
import com.bsd.cse.model.backoffice.machine.Machine;
import com.bsd.cse.model.backoffice.machine.MachineModel;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import java.io.IOException;
import java.util.HashMap;
import org.zkoss.image.AImage;
import org.zkoss.util.ResourceUtil;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
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
public class MachineRenderer implements ListitemRenderer{

    private Window win;
    private Textbox idTxt;
    private Textbox nameTxt;
    private Combobox modelCbox;
    private Toolbarbutton searchBtn;
   

    
    public MachineRenderer(Window win,Textbox idTxt,Textbox nameTxt,Combobox modelCbox,Toolbarbutton searchBtn)
    {
        this.win = win;
        this.idTxt=idTxt;
        this.nameTxt=nameTxt;
        this.modelCbox=modelCbox;
        this.searchBtn = searchBtn;  
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
        else if(o instanceof Machine)
        {
            Machine object = (Machine)o;
            

            Listcell cell1 = new Listcell();
            cell1.appendChild(new Label(object.getName()));
            cell1.setParent(lstm);
            cell1.setSclass("cell-align-left");

            Listcell cell2 = new Listcell();
            cell2.appendChild(new Label(object.getModel().getName()));
            cell2.setParent(lstm);
            cell2.setSclass("cell-align-left");

            Listcell cell4 = new Listcell();
            Toolbarbutton editBtn = new Toolbarbutton();
            editBtn.setImage("/images/backoffice/edit.png");
            editBtn.setTooltiptext(ResourceUtil.getLabel("backoffice.machine.page.btn.edit"));
            editBtn.addEventListener(Events.ON_CLICK, new EditObjectEventListener());
            editBtn.setAttribute("object", o);
            editBtn.setParent(cell4);

            Toolbarbutton deleteBtn = new Toolbarbutton();
            deleteBtn.setImage("/images/backoffice/delete.png");
            deleteBtn.setTooltiptext(ResourceUtil.getLabel("backoffice.machine.page.btn.delete"));
            deleteBtn.addEventListener(Events.ON_CLICK, new DeleteObjectEventListener());
            deleteBtn.setAttribute("object", o);
            deleteBtn.setParent(cell4);

            cell4.setParent(lstm);
            cell4.setSclass("cell-align-left");
        }
        
    }

    class EditObjectEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            Machine object = (Machine)event.getTarget().getAttribute("object");
            idTxt.setValue(String.valueOf(object.getId()));
            nameTxt.setValue(String.valueOf(object.getName()));
            modelCbox.setValue(object.getModel().getName());                      

            win.doModal();
        }

    }

    class DeleteObjectEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {                     
            Machine object = (Machine)event.getTarget().getAttribute("object");
            int messageValue = AlertMessages.confirmMessage("backoffice.machine.alert.message.4");
            if(messageValue == 0 || messageValue == AlertMessages.NO)
            {
                return;
            }
            MachineCore core = new MachineCore();
            if(!core.checkCanDelete(object))
            {
                AlertMessages.alertMessage("backoffice.machine.alert.message.5");
                return ;
            }
            
            HashMap<String,Object> results = core.delete(object);
            if((Boolean)results.get("results"))
            {
                AlertMessages.alertMessage("backoffice.machine.alert.message.6");
                Events.sendEvent(Events.ON_CLICK,searchBtn, null);
            }
            else
            {
                AlertMessages.alertMessage("backoffice.machine.alert.message.7");
            }
        }

    }

}
