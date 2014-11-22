/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.listbox.renderer;

import com.bsd.cse.app.backoffice.NgReasonCore;
import com.bsd.cse.model.backoffice.part.NgReason;
import com.bsd.cse.model.security.Setting;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import java.util.HashMap;
import org.zkoss.util.ResourceUtil;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Executions;
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
public class NgReasonRenderer implements ListitemRenderer{

    private Window win;
    private Textbox idTxt;
    private Textbox nameTxt;
    private Toolbarbutton searchBtn;
    
    public NgReasonRenderer(Window win,Textbox idTxt,Textbox nameTxt,Toolbarbutton searchBtn)
    {
        this.win = win;
        this.idTxt=idTxt;
        this.nameTxt=nameTxt;
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
        else if(o instanceof NgReason)
        {            
            NgReason ngReason = (NgReason)o;
            
//            Listcell cell = new Listcell();
//            cell.appendChild(new Label(String.valueOf(ngReason.getId())));
//            cell.setParent(lstm);
//            cell.setSclass("cell-align-left");

            Listcell cell1 = new Listcell();
            cell1.appendChild(new Label(ngReason.getName()));
            cell1.setParent(lstm);
            cell1.setSclass("cell-align-left");

            Listcell cell2 = new Listcell();
            Toolbarbutton editBtn = new Toolbarbutton();
            editBtn.setImage("/images/backoffice/edit.png");
            editBtn.setTooltiptext(ResourceUtil.getLabel("backoffice.ngreason.page.btn.edit"));
            editBtn.addEventListener(Events.ON_CLICK, new EditNgReasonEventListener());
            editBtn.setAttribute("ngReason", o);
            editBtn.setParent(cell2);

            Toolbarbutton deleteBtn = new Toolbarbutton();
            deleteBtn.setTooltiptext("Delete");
            deleteBtn.setImage("/images/backoffice/delete.png");
            deleteBtn.addEventListener(Events.ON_CLICK, new DeleteNgReasonEventListener());
            deleteBtn.setAttribute("ngReason", o);
            deleteBtn.setParent(cell2);

            cell2.setParent(lstm);
            cell2.setSclass("cell-align-left");
        }
        
    }

    class EditNgReasonEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            NgReason ngReason = (NgReason)event.getTarget().getAttribute("ngReason");
            idTxt.setValue(String.valueOf(ngReason.getId()));
            nameTxt.setValue(String.valueOf(ngReason.getName()));
            win.doModal();
        }

    }

    class DeleteNgReasonEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            HttpUserSession userSession = new HttpUserSession();
            Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
            NgReason ngReason = (NgReason)event.getTarget().getAttribute("ngReason");
            int messageValue = AlertMessages.confirmMessage("backoffice.ngreason.alert.message.4");
            if(messageValue == 0 || messageValue == AlertMessages.NO)
            {
                return;
            }
            NgReasonCore core = new NgReasonCore();
            if(!core.checkCanDelete(ngReason))
            {
                AlertMessages.alertMessage("backoffice.ngreason.alert.message.5");
                return ;
            }
            
            HashMap<String,Object> results = core.delete(ngReason, userId);
            if((Boolean)results.get("results"))
            {
                AlertMessages.alertMessage("backoffice.ngreason.alert.message.6");
                Events.sendEvent(Events.ON_CLICK,searchBtn, null);
            }
            else
            {
                AlertMessages.alertMessage("backoffice.ngreason.alert.message.7");
            }
        }

    }

}
