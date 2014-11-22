/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.grid.renderer;

import com.bsd.cse.app.backoffice.MachineCore;
import com.bsd.cse.app.input.ProductLineCore;
import com.bsd.cse.common.Configuration;
import com.bsd.cse.model.input.ProductLine;
import com.bsd.cse.model.input.ProductLineTime;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.model.backoffice.part.Process;
import com.bsd.cse.model.report.OperationData;
import com.bsd.cse.model.report.TimePartData;
import com.bsd.cse.model.report.TimePartDetailData;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.A;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Include;
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
public class OEEDetailTimePartDataRenderer implements RowRenderer{

    private static Log LOG = LogFactory.getLog(OEEDetailTimePartDataRenderer.class);        
        
    private Include detailInclude;
    private Long partId;
    private Date startDate;
    private Date endDate;
    private Long machineId;
    
    public OEEDetailTimePartDataRenderer(Include detailInclude,Long partId,Date startDate,Date endDate,Long machineId)
    {
        this.detailInclude = detailInclude;
        this.partId = partId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.machineId = machineId;
    }

    @Override
    public void render(Row row, Object o) throws Exception {
        TimePartData data = (TimePartData)o;
        
        Cell cell = new Cell();
        String name = data.getName();
        if(data.getName() != null && data.getName().equalsIgnoreCase("p"))
        {
            name = "";
        }
        Label label = new Label(name);        
        cell.appendChild(label);
        row.appendChild(cell);

//        cell = new Cell();
        Div div =new Div();
        String[] infos = data.getDescription().split("\n");
        for(String info:infos)
        {
            label = new Label(info);
            label.setStyle("float:left;clear:left");
            div.appendChild(label);
        }                               
        cell = new Cell();
        cell.appendChild(div);
        row.appendChild(cell);

        for(int index = 0 ;index < data.getNg().length;index++ )
        {            
            DecimalFormat format  = new DecimalFormat("#,##0");
            Cell cell1 = new Cell();
            
            Label label1 = new Label(data.getOk()[index] != null?format.format(data.getOk()[index]):"");
            cell1.appendChild(label1);            
            row.appendChild(cell1);

            cell1 = new Cell();
            label1 = new Label(data.getNg()[index]!=null?format.format(data.getNg()[index]):"");
            cell1.appendChild(label1);
            row.appendChild(cell1);
        }      
        
    }
  

}
    
   



