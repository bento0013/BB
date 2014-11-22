/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.grid.renderer;

import com.bsd.cse.app.backoffice.MachineCore;
import com.bsd.cse.app.input.ProductLineCore;
import com.bsd.cse.model.input.ProductLine;
import com.bsd.cse.model.input.ProductLineTime;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.model.backoffice.part.Process;
import com.bsd.cse.model.dsb.DashboardPartSetting;
import com.bsd.cse.model.input.NgProductLineRecord;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.util.ResourceUtil;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;


/**
 *
 * @author bento
 */
public class DashboardSettingRenderer implements RowRenderer{

    private static Log LOG = LogFactory.getLog(DashboardSettingRenderer.class);
   

    
    
    public DashboardSettingRenderer()
    {        
    }

    @Override
    public void render(Row row, Object o) throws Exception {

        if(o instanceof String)
        {
            Cell cell = new Cell();
            cell.setStyle("vertical-align:top");            
            Label label =new Label();
            label.setValue((String)o) ;            
            cell.appendChild(label);
            cell.setColspan(2);
            row.appendChild(cell);
        }
        else
        {
            DashboardPartSetting record = (DashboardPartSetting)o;
            record.setId(new Long(row.getChildren().size()+1));
            Cell cell = new Cell();
            Label label= new Label(String.valueOf(record.getPart().getPartName()+"-"+record.getPart().getPartNo()));
            cell.appendChild(label);
            cell.setStyle("vertical-align:middle");
            row.appendChild(cell);
          

            cell = new Cell();
            Toolbarbutton deleteBtn = new Toolbarbutton();
            deleteBtn.setImage("/images/backoffice/delete.png");
            deleteBtn.setTooltiptext(ResourceUtil.getLabel("dashboard.page.btn.delete"));
            deleteBtn.addEventListener(Events.ON_CLICK, new DeleteObjectEventListener());
            deleteBtn.setAttribute("object", o);
            deleteBtn.setParent(cell);
            cell.setStyle("vertical-align:middle");
            row.appendChild(cell);
        }
    }
    
    class DeleteObjectEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            DashboardPartSetting object = (DashboardPartSetting)event.getTarget().getAttribute("object");
            Grid settingGrid = (Grid)event.getTarget().getParent().getParent().getParent();
            settingGrid.getChildren().remove(object);
        }

    }

   


}
