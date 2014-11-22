/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.listbox.renderer;


import com.bsd.cse.app.backoffice.SemiMaterialCore;
import com.bsd.cse.model.backoffice.material.semi.SemiMaterial;
import com.bsd.cse.zk.alertbox.AlertMessages;
import java.util.HashMap;
import org.zkoss.util.ResourceUtil;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Decimalbox;
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
public class SemiMaterialRenderer implements ListitemRenderer{

    private Window win;
    private Textbox idTxt;
    private Textbox nameTxt;
    private Decimalbox sizeTxt;
    private Decimalbox lengthTxt;
    private Longbox minimumStockTxt;
    private Combobox typeCbox;
    private Combobox rawMaterialCbox;
    private Toolbarbutton searchBtn;
   

    
    public SemiMaterialRenderer(Window win,Textbox idTxt,Textbox nameTxt,Decimalbox sizeTxt,Combobox typeCbox,Toolbarbutton searchBtn,Decimalbox lengthTxt,Longbox mininumStockTxt,Combobox rawMaterialCbox)
    {
        this.win = win;
        this.idTxt=idTxt;
        this.sizeTxt=sizeTxt;
        this.nameTxt=nameTxt;
        this.searchBtn = searchBtn;       
        this.lengthTxt = lengthTxt;
        this.minimumStockTxt = mininumStockTxt;
        this.rawMaterialCbox = rawMaterialCbox;
        this.typeCbox = typeCbox;

    }
    @Override
    public void render(Listitem lstm, Object o) throws Exception {
        if(o instanceof String)
        {
            Listcell cell = new Listcell();
            cell.setSpan(7);
            cell.appendChild(new Label((String)o));
            cell.setSclass("cell-align-center");
            cell.setParent(lstm);
        }
        else if(o instanceof SemiMaterial)
        {
            SemiMaterial object = (SemiMaterial)o;
            
            Listcell cell = new Listcell();
            cell.appendChild(new Label(object.getSemiName()));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            cell = new Listcell();
            cell.appendChild(new Label(object.getSemiType().getName()));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");


            cell = new Listcell();
            cell.appendChild(new Label(String.valueOf(object.getSize() != null?String.valueOf(object.getSize()):"")));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            
            cell = new Listcell();
            cell.appendChild(new Label(String.valueOf(object.getLength() != null?String.valueOf(object.getLength()):"")));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            cell = new Listcell();
            cell.appendChild(new Label(String.valueOf(object.getMinimumStock() != null?String.valueOf(object.getMinimumStock()):"")));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            cell = new Listcell();
            if(object.getRawMaterial() != null)
            {
                cell.appendChild(new Label(object.getRawMaterial().getRawName()+"-"+object.getRawMaterial().getRawType().getName()));
            }
            else
            {
                cell.appendChild(new Label(ResourceUtil.getLabel("backoffice.semimaterial.page.label.na")));
            }
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            cell = new Listcell();
            Toolbarbutton editBtn = new Toolbarbutton();
            editBtn.setImage("/images/backoffice/edit.png");
            editBtn.setTooltiptext(ResourceUtil.getLabel("backoffice.semimaterial.page.btn.edit"));
            editBtn.addEventListener(Events.ON_CLICK, new EditObjectEventListener());
            editBtn.setAttribute("object", o);
            editBtn.setParent(cell);

            Toolbarbutton deleteBtn = new Toolbarbutton();
            deleteBtn.setImage("/images/backoffice/delete.png");
            deleteBtn.setTooltiptext(ResourceUtil.getLabel("backoffice.semimaterial.page.btn.delete"));
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
            SemiMaterial object = (SemiMaterial)event.getTarget().getAttribute("object");
            idTxt.setValue(String.valueOf(object.getId()));
            nameTxt.setValue(String.valueOf(object.getSemiName()));
            sizeTxt.setValue(object.getSize());
            lengthTxt.setValue(object.getLength());
            minimumStockTxt.setValue(object.getMinimumStock());
            typeCbox.setValue(object.getSemiType().getName());
            if(object.getRawMaterial() != null)
            {
                rawMaterialCbox.setValue(object.getRawMaterial().getRawName()+"-"+object.getRawMaterial().getRawType().getName());
            }
            else
            {
                rawMaterialCbox.setValue(ResourceUtil.getLabel("backoffice.semimaterial.page.label.na"));                
            }
            win.doModal();
        }

    }

    class DeleteObjectEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {                       
            SemiMaterial object = (SemiMaterial)event.getTarget().getAttribute("object");
            int messageValue = AlertMessages.confirmMessage("backoffice.semimaterial.alert.message.4");
            if(messageValue == 0 || messageValue == AlertMessages.NO)
            {
                return;
            }
            SemiMaterialCore core = new SemiMaterialCore();
            if(!core.checkCanDelete(object))
            {
                AlertMessages.alertMessage("backoffice.semimaterial.alert.message.5");
                return ;
            }
            
            HashMap<String,Object> results = core.delete(object);
            if((Boolean)results.get("results"))
            {
                AlertMessages.alertMessage("backoffice.semimaterial.alert.message.6");
                Events.sendEvent(Events.ON_CLICK,searchBtn, null);
            }
            else
            {
                AlertMessages.alertMessage("backoffice.semimaterial.alert.message.7");
            }
        }

    }

}
