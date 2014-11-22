/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.grid.renderer;

import com.bsd.cse.model.dsb.MonitorPartData;
import java.util.ArrayList;
import java.util.List;
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
public class MonitorPartDataRenderer implements RowRenderer{

    private static Log LOG = LogFactory.getLog(MonitorPartDataRenderer.class);    
    
    
    public MonitorPartDataRenderer()
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
            Vbox vbox = new Vbox();
            vbox.setHflex("1");
//            vbox.setStyle("border : 1px solid red");

            vbox.appendChild(new Label(partData.getPart().getPartName()+"-"+partData.getPart().getPartNo()));
            Grid grid = new Grid();
            grid.setHflex("1");
            grid.setOddRowSclass("non-odd");
            Columns columns = new Columns();
            Column column1 = new Column(ResourceUtil.getLabel("dashboard.message.label.12"));
            column1.setWidth("200px");
            Column column2 = new Column(ResourceUtil.getLabel("dashboard.message.label.13"));
            column2.setHflex("1");
            Column column3 = new Column(ResourceUtil.getLabel("dashboard.message.label.14"));
            column3.setWidth("200px");
            columns.appendChild(column1);
            columns.appendChild(column2);
            columns.appendChild(column3);
            grid.appendChild(columns);
            List<MonitorPartData> partDatas = new ArrayList<MonitorPartData>();
            partDatas.add(partData);
            grid.setModel(new ListModelList(partDatas));
            grid.setRowRenderer(new MonitorPartDataDetailRenderer());
            vbox.appendChild(grid);
            row.appendChild(vbox);
        }
    }

    
   


}
