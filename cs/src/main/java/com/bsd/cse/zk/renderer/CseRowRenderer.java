/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsd.cse.zk.renderer;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Label;
import org.zkoss.zul.Paging;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

/**
 *
 * @author thanasith
 */
public abstract class CseRowRenderer implements RowRenderer{
    protected final static Log LOG = LogFactory.getLog(CseRowRenderer.class);
    protected final static String LEFT_CELL_STYLE = "left-cell-row";
    protected final static String CENTER_CELL_STYLE = "center-cell-row";
    protected final static String RIGHT_CELL_STYLE = "right-cell-row";
    protected final static String LABEL_STYLE = "content-cell";       
    protected Paging paging;
    
    public CseRowRenderer(Paging _paging)
    {        
        this.paging = _paging;
    }
    
    @Override
    public void render(Row row, Object o) throws Exception {
        
        if(paging == null)
        {
            throw new Exception("Required Call Super Constructor!!!");
        }
        
        if(o instanceof String)
        {
            noRecordRender(row,o);
        }
        else
        {
            gridRender(row,o);
        }
        
    }
    
    protected abstract void gridRender(Row row,Object o) throws Exception;
    
    
    private void noRecordRender(Row row,Object o)
    {
        Cell cell = new Cell();
        cell.setSclass(LEFT_CELL_STYLE);        
        cell.setColspan(row.getGrid().getColumns().getChildren().size());        
        Label noRecordLbl = new Label();
        noRecordLbl.setSclass(LABEL_STYLE);
        noRecordLbl.setValue((String)o);
        
        cell.appendChild(noRecordLbl);
        
        row.appendChild(cell);        
    }
    
    protected void appendCellInRow(Row row,Cell cell)
    {
        row.appendChild(cell); 
    }
    
    protected Cell createLabelInCell(String data,String cellStyle)
    {
        Cell cell = new Cell();
        cell.setSclass(cellStyle);
        Label label = new Label();
        label.setSclass(LABEL_STYLE);
        label.setValue(data);
        cell.appendChild(label);
        return cell;
    }
    
    protected Cell createRowNumberInCell(String cellStyle)
    {
        Cell cell = new Cell();
        cell.setSclass(cellStyle);
        Label label = new Label();
        label.setSclass(LABEL_STYLE);        
        cell.appendChild(label);
        return cell;
    }
        
    
    protected Integer getNo(Row row)
    {
        return (paging.getActivePage()*paging.getPageSize())+(row.getGrid().getRows().getChildren().indexOf(row)+1);
    }
    
}
