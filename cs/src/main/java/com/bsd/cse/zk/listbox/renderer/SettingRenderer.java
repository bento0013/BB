/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.listbox.renderer;

import com.bsd.cse.model.security.Setting;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author bento
 */
public class SettingRenderer implements ListitemRenderer{

    private Window win;
    private Textbox idTxt;
    private Textbox nameTxt;
    private Textbox valueTxt;
    public SettingRenderer(Window win,Textbox idTxt,Textbox nameTxt,Textbox valueTxt)
    {
        this.win = win;
        this.idTxt=idTxt;
        this.nameTxt=nameTxt;
        this.valueTxt=valueTxt;
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
        else if(o instanceof Setting)
        {
            Setting setting = (Setting)o;
            
            Listcell cell = new Listcell();
            cell.appendChild(new Label(setting.getDescription()));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            Listcell cell1 = new Listcell();
            cell1.appendChild(new Label(setting.getValue()));
            cell1.setParent(lstm);
            cell1.setSclass("cell-align-left");

            Listcell cell2 = new Listcell();
            Button editBtn = new Button();
            editBtn.setLabel("Edit");
            editBtn.addEventListener(Events.ON_CLICK, new SettingEventListener());
            editBtn.setAttribute("setting", o);
            editBtn.setParent(cell2);

            cell2.setParent(lstm);
            cell2.setSclass("cell-align-left");
        }
        
    }

    class SettingEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            Setting setting = (Setting)event.getTarget().getAttribute("setting");
            idTxt.setValue(String.valueOf(setting.getId()));
            nameTxt.setValue(String.valueOf(setting.getDescription()));
            valueTxt.setValue(String.valueOf(setting.getValue()));
            win.doModal();
        }

    }

}
