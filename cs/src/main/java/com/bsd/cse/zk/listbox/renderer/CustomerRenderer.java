/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.listbox.renderer;

import com.bsd.cse.app.backoffice.CustomerCore;
import com.bsd.cse.app.backoffice.DepartmentCore;
import com.bsd.cse.model.backoffice.customer.Customer;
import com.bsd.cse.model.backoffice.department.Department;
import com.bsd.cse.model.security.Setting;
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
public class CustomerRenderer implements ListitemRenderer{

    private Window win;
    private Textbox idTxt;
    private Textbox nameTxt;
    private Textbox surnameTxt;
    private Textbox addressTxt;
    private Textbox telTxt;


    private Toolbarbutton searchBtn;

    
    public CustomerRenderer(Window win,Textbox idTxt,Textbox nameTxt,Textbox surnameTxt,Textbox addressTxt,Textbox telTxt,Toolbarbutton searchBtn)
    {
        this.win = win;
        this.idTxt=idTxt;
        this.nameTxt=nameTxt;
        this.surnameTxt = surnameTxt;
        this.addressTxt = addressTxt;
        this.telTxt = telTxt;
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
        else if(o instanceof Customer)
        {
            Customer obj = (Customer)o;

            Listcell cell = new Listcell();
            cell.appendChild(new Label(obj.getName()));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            cell = new Listcell();
            cell.appendChild(new Label(obj.getSurname()));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            cell = new Listcell();
            cell.appendChild(new Label(obj.getAddress()));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            cell = new Listcell();
            cell.appendChild(new Label(obj.getTel()));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            cell = new Listcell();
            Toolbarbutton editBtn = new Toolbarbutton();
            editBtn.setImage("/images/backoffice/edit.png");
            editBtn.setTooltiptext(ResourceUtil.getLabel("backoffice.customer.page.btn.edit"));
            editBtn.addEventListener(Events.ON_CLICK, new EditCustomerEventListener());
            editBtn.setAttribute("object", o);
            editBtn.setParent(cell);

            Toolbarbutton deleteBtn = new Toolbarbutton();
            deleteBtn.setImage("/images/backoffice/delete.png");
            deleteBtn.setTooltiptext(ResourceUtil.getLabel("backoffice.customer.page.btn.delete"));
            deleteBtn.addEventListener(Events.ON_CLICK, new DeleteCustomerEventListener());
            deleteBtn.setAttribute("object", o);
            deleteBtn.setParent(cell);

            cell.setParent(lstm);
            cell.setSclass("cell-align-left");
        }
        
    }

    class EditCustomerEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            Customer customer = (Customer)event.getTarget().getAttribute("object");
            idTxt.setValue(String.valueOf(customer.getId()));
            nameTxt.setValue(String.valueOf(customer.getName()));
            surnameTxt.setValue(String.valueOf(customer.getSurname()));
            addressTxt.setValue(String.valueOf(customer.getAddress()));
            telTxt.setValue(String.valueOf(customer.getTel()));
            win.doModal();
        }

    }

    class DeleteCustomerEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            HttpUserSession userSession = new HttpUserSession();
            Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
            Customer customer = (Customer)event.getTarget().getAttribute("object");
            int messageValue = AlertMessages.confirmMessage("backoffice.customer.alert.message.4");
            if(messageValue == 0 || messageValue == AlertMessages.NO)
            {
                return;
            }
            CustomerCore core = new CustomerCore();
            if(!core.checkCanDelete(customer))
            {
                AlertMessages.alertMessage("backoffice.customer.alert.message.5");
                return ;
            }
            
            HashMap<String,Object> results = core.delete(customer);
            if((Boolean)results.get("results"))
            {
                AlertMessages.alertMessage("backoffice.customer.alert.message.6");
                Events.sendEvent(Events.ON_CLICK,searchBtn, null);
            }
            else
            {
                AlertMessages.alertMessage("backoffice.customer.alert.message.7");
            }
        }

    }

}
