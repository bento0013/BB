/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.listbox.renderer;

import com.bsd.cse.app.backoffice.MeasurementToolTypeCore;
import com.bsd.cse.model.backoffice.measurement.MeasurementToolType;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import java.util.HashMap;
import org.zkoss.util.ResourceUtil;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
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
public class MeasurementToolTypeRenderer implements ListitemRenderer{

    private Window win;
    private Textbox idTxt;
    private Textbox nameTxt;
    private Textbox descTxt;
    private Toolbarbutton searchBtn;
    
    public MeasurementToolTypeRenderer(Window win,Textbox idTxt,Textbox nameTxt,Textbox descTxt,Toolbarbutton searchBtn)
    {
        this.win = win;
        this.idTxt = idTxt;
        this.nameTxt = nameTxt;
        this.descTxt = descTxt;
        this.searchBtn = searchBtn;

    }
    @Override
    public void render(Listitem lstm, Object o) throws Exception {
        if(o instanceof String)
        {
            Listcell cell = new Listcell();
            cell.setSpan(3);
            cell.appendChild(new Label((String)o));
            cell.setSclass("cell-align-center");
            cell.setParent(lstm);
        }
        else if(o instanceof MeasurementToolType)
        {
            MeasurementToolType object = (MeasurementToolType)o;
            
//            Listcell cell = new Listcell();
//            cell.appendChild(new Label(String.valueOf(object.getId())));
//            cell.setParent(lstm);
//            cell.setSclass("cell-align-left");

            Listcell cell1 = new Listcell();
            cell1.appendChild(new Label(object.getName()));
            cell1.setParent(lstm);
            cell1.setSclass("cell-align-left");

            Listcell cell2 = new Listcell();
            cell2.appendChild(new Label(object.getDescription()));
            cell2.setParent(lstm);
            cell2.setSclass("cell-align-left");

            Listcell cell3 = new Listcell();
            Toolbarbutton editBtn = new Toolbarbutton();
            editBtn.setImage("/images/backoffice/edit.png");
            editBtn.setTooltiptext(ResourceUtil.getLabel("backoffice.measurementtooltype.page.btn.edit"));
            editBtn.addEventListener(Events.ON_CLICK, new EditObjectEventListener());
            editBtn.setAttribute("object", o);
            editBtn.setParent(cell3);

            Toolbarbutton deleteBtn = new Toolbarbutton();
            deleteBtn.setImage("/images/backoffice/delete.png");
            deleteBtn.setTooltiptext(ResourceUtil.getLabel("backoffice.measurementtooltype.page.btn.delete"));
            deleteBtn.addEventListener(Events.ON_CLICK, new DeleteObjectEventListener());
            deleteBtn.setAttribute("object", o);
            deleteBtn.setParent(cell3);

            cell3.setParent(lstm);
            cell3.setSclass("cell-align-left");
        }
        
    }

    class EditObjectEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            MeasurementToolType object = (MeasurementToolType)event.getTarget().getAttribute("object");
            idTxt.setValue(String.valueOf(object.getId()));
            nameTxt.setValue(String.valueOf(object.getName()));
            descTxt.setValue(String.valueOf(object.getDescription()));
            win.doModal();
        }

    }

    class DeleteObjectEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            HttpUserSession userSession = new HttpUserSession();
            Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
            MeasurementToolType object = (MeasurementToolType)event.getTarget().getAttribute("object");
            int messageValue = AlertMessages.confirmMessage("backoffice.measurementtooltype.alert.message.4");
            if(messageValue == 0 || messageValue == AlertMessages.NO)
            {
                return;
            }
            MeasurementToolTypeCore core = new MeasurementToolTypeCore();
            if(!core.checkCanDelete(object))
            {
                AlertMessages.alertMessage("backoffice.measurementtooltype.alert.message.5");
                return ;
            }
            
            HashMap<String,Object> results = core.delete(object);
            if((Boolean)results.get("results"))
            {
                AlertMessages.alertMessage("backoffice.measurementtooltype.alert.message.6");
                Events.sendEvent(Events.ON_CLICK,searchBtn, null);
            }
            else
            {
                AlertMessages.alertMessage("backoffice.measurementtooltype.alert.message.7");
            }
        }

    }

}
