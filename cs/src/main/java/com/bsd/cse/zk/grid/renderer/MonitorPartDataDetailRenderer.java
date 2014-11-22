/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.grid.renderer;

import com.bsd.cse.model.dsb.MonitorPartData;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.util.ResourceUtil;
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
public class MonitorPartDataDetailRenderer implements RowRenderer{

    private static Log LOG = LogFactory.getLog(MonitorPartDataDetailRenderer.class);    
    private static DecimalFormat numberFormat = new DecimalFormat("#,##0");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",Locale.US);
    
    public MonitorPartDataDetailRenderer()
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
            cell1.setColspan(3);
            row.appendChild(cell1);
        }
        else
        {
            MonitorPartData partData =(MonitorPartData)o;
            Cell cell1 = new Cell();
            Label label = new Label();
            label.setValue(numberFormat.format(partData.getStore()));
            cell1.setTooltiptext(dateFormat.format(partData.getLastStoreUpdateDate()));
            cell1.appendChild(label);            
            row.appendChild(cell1);
           
            Grid grid = new Grid();
            grid.setHflex("1");
            Columns columns = new Columns();
            Column column1 = new Column(ResourceUtil.getLabel("dashboard.message.label.8"));
            Column column2 = new Column(ResourceUtil.getLabel("dashboard.message.label.9"));
            Column column3 = new Column(ResourceUtil.getLabel("dashboard.message.label.10"));
            Column column4 = new Column(ResourceUtil.getLabel("dashboard.message.label.11"));
            columns.appendChild(column1);
            columns.appendChild(column2);
            columns.appendChild(column3);
            columns.appendChild(column4);
            grid.appendChild(columns);
            grid.setOddRowSclass("non-odd");
            if(partData.getProductions() != null && partData.getProductions().size() > 0)
            {
                grid.setModel(new ListModelList(partData.getProductions()));
            }
            else
            {
                List str = new ArrayList<String>();
                str.add(new String(ResourceUtil.getLabel("dashboard.page.message.norecordfound")));
                grid.setModel(new ListModelList(str));
            }
            
            grid.setRowRenderer(new MonitorPartDataDetailProductionRenderer());            
            row.appendChild(grid);

            Cell cell3 = new Cell();
            Label label3 = new Label();
            label3.setValue(numberFormat.format(partData.getStoreFinish()));
            cell3.setTooltiptext(dateFormat.format(partData.getLastStoreFinishUpdateDate()));
            cell3.appendChild(label3);
            row.appendChild(cell3);
        }
    }

    
   


}
