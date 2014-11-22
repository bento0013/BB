/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.grid.renderer;

import com.bsd.cse.model.dsb.MonitorPartData;
import com.bsd.cse.model.dsb.ProductionProcess;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Vbox;


/**
 *
 * @author bento
 */
public class MonitorPartDataDetailProductionRenderer implements RowRenderer{

    private static Log LOG = LogFactory.getLog(MonitorPartDataDetailProductionRenderer.class);    
    private static DecimalFormat numberFormat = new DecimalFormat("#,##0");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",Locale.US);
    
    public MonitorPartDataDetailProductionRenderer()
    {                
    }

    @Override
    public void render(Row row, Object o) throws Exception {

        if(o instanceof String)
        {
            String data =(String)o;
            Cell cell1 = new Cell();
            Label label = new Label();
            label.setValue(data);
            cell1.appendChild(label);
            cell1.setColspan(4);
            row.appendChild(cell1);
        }
        else
        {
            ProductionProcess productionProcess =(ProductionProcess)o;
            Cell cell1 = new Cell();
            Label label = new Label();
            label.setValue(productionProcess.getProcess().getProcessName());
            cell1.setTooltiptext(dateFormat.format(productionProcess.getLastUpdateDate()));
            cell1.appendChild(label);            
            row.appendChild(cell1);

            Cell cell2 = new Cell();
            Label label2 = new Label();
            label2.setValue(numberFormat.format(productionProcess.getOk()));
            cell2.setTooltiptext(dateFormat.format(productionProcess.getLastUpdateDate()));
            cell2.appendChild(label2);
            row.appendChild(cell2);

            Cell cell3 = new Cell();
            Label label3 = new Label();
            label3.setValue(numberFormat.format(productionProcess.getNg()));
            cell3.setTooltiptext(dateFormat.format(productionProcess.getLastUpdateDate()));
            cell3.appendChild(label3);
            row.appendChild(cell3);

            Cell cell4 = new Cell();
            Label label4 = new Label();
            label4.setValue(numberFormat.format(productionProcess.getWip()));
            cell4.setTooltiptext(dateFormat.format(productionProcess.getLastUpdateDate()));
            cell4.appendChild(label4);
            row.appendChild(cell4);
        }
    }

    
   


}
