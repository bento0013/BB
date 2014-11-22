/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.listbox.renderer;

import com.bsd.cse.model.stock.FgStockTran;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

/**
 *
 * @author bento
 */
public class FgIncomingRenderer implements ListitemRenderer{
              
    private static final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.US);
    
    public FgIncomingRenderer()
    {       
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
        else if(o instanceof FgStockTran)
        {
            FgStockTran object = (FgStockTran)o;
            
            Listcell cell = new Listcell();
            cell.appendChild(new Label(String.valueOf(object.getTransferType().getTransferTypeName())));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            cell = new Listcell();
            cell.appendChild(new Label(String.valueOf(object.getPart().getPartName()+"-"+object.getPart().getPartNo())));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");
              

            cell = new Listcell();
            cell.appendChild(new Label(object.getPart().getCustomer().getName()));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            DecimalFormat numberFormat = new DecimalFormat("#,##0");
            cell = new Listcell();
            cell.appendChild(new Label(String.valueOf(numberFormat.format(object.getQuantity()))));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            

            cell = new Listcell();
            cell.appendChild(new Label(String.valueOf(object.getRequester().getUsername())));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            cell = new Listcell();
            cell.appendChild(new Label(String.valueOf(object.getRecorder().getUsername())));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");

            cell = new Listcell();
            cell.appendChild(new Label(String.valueOf(format.format(object.getCreatedDate()))));
            cell.setParent(lstm);
            cell.setSclass("cell-align-left");
           
        }
        
    }   

}
