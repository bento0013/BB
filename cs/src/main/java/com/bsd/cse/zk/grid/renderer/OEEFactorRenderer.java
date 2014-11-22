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
public class OEEFactorRenderer implements RowRenderer{

    private static Log LOG = LogFactory.getLog(OEEFactorRenderer.class);        
        
    private Include detailInclude;
    private Long teamId;
    private Date startDate;
    private Date endDate;
    private Long machineId;
    private Long employeeId;    
    
    public OEEFactorRenderer(Include detailInclude,Long teamId,Date startDate,Date endDate,Long machineId,Long employeeId)
    {
        this.detailInclude = detailInclude;
        this.teamId = teamId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.machineId = machineId;
        this.employeeId = employeeId;        
    }

    @Override
    public void render(Row row, Object o) throws Exception {

        if(o instanceof String)
        {
            Cell cell = new Cell();
            cell.setColspan(13);
            Label label = new Label((String)o);
            cell.appendChild(label);
            row.appendChild(cell);
        }
        else
        if(o instanceof OperationData)
        {
            OperationData data = (OperationData)o;
            BigDecimal conditionValue = new BigDecimal("0");
            if(data.getName().equals("U"))
            {
                conditionValue =  Configuration.getBigDecimal("cs.mininum.oee.availability.value");
            }
            else if(data.getName().equals("V"))
            {
                conditionValue =  Configuration.getBigDecimal("cs.mininum.oee.performance.value");
            }
            else if(data.getName().equals("W"))
            {
                conditionValue =  Configuration.getBigDecimal("cs.mininum.oee.quality.value");
            }

            Cell cell = new Cell();
            Label label = new Label(data.getDescription());
            cell.appendChild(label);
            row.appendChild(cell);
            int index = 0;
            for(BigDecimal op : data.getOp() )
            {
                
                DecimalFormat format  = new DecimalFormat("#,###0.00");
                Cell cell1 = new Cell();
                if(op == null)
                {
                    Label label1 = new Label("");
                    cell1.appendChild(label1);
                }
                else
                if(conditionValue.compareTo(op.multiply(new BigDecimal("100"))) < 0)
                {
                    Label label1 = new Label(format.format(op.multiply(new BigDecimal("100")))+"%");
                    cell1.appendChild(label1);
                }
                else
                {
                    A a = new A(format.format(op.multiply(new BigDecimal("100")))+"%");
                    a.addEventListener(Events.ON_CLICK,new LinkEventListener(data.getPartId()[index] ,data.getProcessId()[index]));
                    cell1.appendChild(a);
                }
                index++;

                row.appendChild(cell1);
            }
        }      
        
    }

    class LinkEventListener implements EventListener
    {
        private Long processId;
        private Long partId;
        public LinkEventListener(Long partId,Long processId)
        {
            this.processId = processId;
            this.partId = partId;
        }
        @Override
        public void onEvent(Event event) throws Exception {

            detailInclude.setSrc(null);
            detailInclude.setDynamicProperty("partId", partId);
            detailInclude.setDynamicProperty("processId", processId);
            if(machineId != null)
            {
                detailInclude.setDynamicProperty("teamId", teamId);
            }
            detailInclude.setDynamicProperty("startDate", startDate);
            detailInclude.setDynamicProperty("endDate", endDate);
            if(machineId != null)
            {
                detailInclude.setDynamicProperty("machineId", machineId);
            }
            if(employeeId != null)
            {
                detailInclude.setDynamicProperty("employeeId", employeeId);
            }
            detailInclude.setSrc("report079_detail.zul");
           
        }


    }

}
    
   



