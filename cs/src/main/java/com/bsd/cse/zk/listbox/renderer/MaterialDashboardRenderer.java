/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.listbox.renderer;

import com.bsd.cse.model.backoffice.material.semi.SemiMaterial;
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
public class MaterialDashboardRenderer implements ListitemRenderer{
    
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",Locale.US);
    private static DecimalFormat numberFormat = new DecimalFormat("#,##0");
    
    public MaterialDashboardRenderer()
    {       
    }

    @Override
    public void render(Listitem lstm, Object o) throws Exception {
        if(o instanceof String)
        {
            Listcell cell = new Listcell();
            cell.setSpan(2);
            cell.appendChild(new Label((String)o));
            cell.setSclass("cell-align-center");
            cell.setParent(lstm);
        }
        else if(o instanceof SemiMaterial)
        {
            SemiMaterial object = (SemiMaterial)o;



            Listcell cell = new Listcell();
            cell.appendChild(new Label(String.valueOf(object.getSemiName())));
            cell.setTooltiptext(dateFormat.format(object.getUpdatedDate()!=null?object.getUpdatedDate():object.getCreatedDate()));
            cell.setParent(lstm);
            cell.setSclass(object.getMinimumStock()>object.getAmount()?"font_bold_red":"");
        
            cell = new Listcell();
            cell.appendChild(new Label(numberFormat.format(object.getAmount())));
            cell.setParent(lstm);
            cell.setTooltiptext(dateFormat.format(object.getUpdatedDate()!=null?object.getUpdatedDate():object.getCreatedDate()));
            cell.setSclass(object.getMinimumStock()>object.getAmount()?"font_bold_red":"");
            
        }
        
    }

    

}
