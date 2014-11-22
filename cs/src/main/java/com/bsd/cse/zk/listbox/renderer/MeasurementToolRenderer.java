/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.listbox.renderer;


import com.bsd.cse.app.backoffice.MeasurementToolCore;
import com.bsd.cse.app.backoffice.MeasurementToolModelCore;
import com.bsd.cse.model.backoffice.measurement.MeasurementTool;
import com.bsd.cse.model.backoffice.measurement.MeasurementToolModel;
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
public class MeasurementToolRenderer implements ListitemRenderer{

    private Window win;
    private Textbox idTxt;
    private Textbox brandTxt;
    private Textbox descTxt;
    private Textbox serialNoTxt;
    private Textbox codeNoTxt;
    private Combobox modelCbox;
    private Textbox resolutionTxt;
    private Textbox customerDetailTxt;
    private Textbox incomingDateTxt;
    private Toolbarbutton searchBtn;
   

    
    public MeasurementToolRenderer(Window win,Textbox idTxt,Textbox brandTxt,Textbox descTxt,Combobox modelCbox,Toolbarbutton searchBtn,Textbox serialNoTxt,Textbox codeNoTxt,Textbox resolutionTxt,Textbox incomingDateTxt,Textbox customerDetailTxt)
    {
        this.win = win;
        this.idTxt=idTxt;
        this.brandTxt=brandTxt;
        this.modelCbox=modelCbox;
        this.searchBtn = searchBtn;       
        this.descTxt = descTxt;
        this.serialNoTxt = serialNoTxt;
        this.codeNoTxt = codeNoTxt;
        this.resolutionTxt = resolutionTxt;
        this.customerDetailTxt = customerDetailTxt;
        this.incomingDateTxt = incomingDateTxt;

    }
    @Override
    public void render(Listitem lstm, Object o) throws Exception {
        if(o instanceof String)
        {
            Listcell cell = new Listcell();
            cell.setSpan(9);
            cell.appendChild(new Label((String)o));
            cell.setSclass("cell-align-center");
            cell.setParent(lstm);
        }
        else if(o instanceof MeasurementTool)
        {
            MeasurementTool object = (MeasurementTool)o;                      
            
            Listcell cell = new Listcell();
            cell.appendChild(new Label(object.getSerialNo()));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            cell = new Listcell();
            cell.appendChild(new Label(object.getCodeNo()));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            cell = new Listcell();
            cell.appendChild(new Label(object.getModel().getName()));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            cell = new Listcell();
            cell.appendChild(new Label(object.getBrand()));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            cell = new Listcell();
            cell.appendChild(new Label(object.getDescription()));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            cell = new Listcell();
            cell.appendChild(new Label(object.getResolution()));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            cell = new Listcell();
            cell.appendChild(new Label(object.getIncomingDate()));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            cell = new Listcell();
            cell.appendChild(new Label(object.getCustomerDetail()));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");                                

            cell = new Listcell();
            Toolbarbutton editBtn = new Toolbarbutton();
            editBtn.setImage("/images/backoffice/edit.png");
            editBtn.setTooltiptext(ResourceUtil.getLabel("backoffice.measurementtool.page.btn.edit"));
            editBtn.addEventListener(Events.ON_CLICK, new EditObjectEventListener());
            editBtn.setAttribute("object", o);
            editBtn.setParent(cell);

            Toolbarbutton deleteBtn = new Toolbarbutton();
            deleteBtn.setImage("/images/backoffice/delete.png");
            deleteBtn.setTooltiptext(ResourceUtil.getLabel("backoffice.measurementtool.page.btn.delete"));
            deleteBtn.addEventListener(Events.ON_CLICK, new DeleteObjectEventListener());
            deleteBtn.setAttribute("object", o);
            deleteBtn.setParent(cell);

            cell.setParent(lstm);
            cell.setSclass("cell-align-left");
        }
        
    }

    class EditObjectEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            MeasurementTool object = (MeasurementTool)event.getTarget().getAttribute("object");
            idTxt.setValue(String.valueOf(object.getId()));
            brandTxt.setValue(String.valueOf(object.getBrand()));
            descTxt.setValue(String.valueOf(object.getDescription()));
            serialNoTxt.setValue(String.valueOf(object.getSerialNo()));
            codeNoTxt.setValue(String.valueOf(object.getCodeNo()));
            incomingDateTxt.setValue(String.valueOf(object.getIncomingDate()));
            customerDetailTxt.setValue(String.valueOf(object.getCustomerDetail()));
            resolutionTxt.setValue(String.valueOf(object.getResolution()));
            modelCbox.setValue(object.getModel().getName());                       
            win.doModal();
        }

    }

    class DeleteObjectEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            HttpUserSession userSession = new HttpUserSession();            
            MeasurementTool object = (MeasurementTool)event.getTarget().getAttribute("object");
            int messageValue = AlertMessages.confirmMessage("backoffice.measurementtool.alert.message.4");
            if(messageValue == 0 || messageValue == AlertMessages.NO)
            {
                return;
            }
            MeasurementToolCore core = new MeasurementToolCore();
            if(!core.checkCanDelete(object))
            {
                AlertMessages.alertMessage("backoffice.measurementtool.alert.message.5");
                return ;
            }
            
            HashMap<String,Object> results = core.delete(object);
            if((Boolean)results.get("results"))
            {
                AlertMessages.alertMessage("backoffice.measurementtool.alert.message.6");
                Events.sendEvent(Events.ON_CLICK,searchBtn, null);
            }
            else
            {
                AlertMessages.alertMessage("backoffice.measurementtool.alert.message.7");
            }
        }

    }

}
