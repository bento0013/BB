/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.grid.renderer;

import com.bsd.cse.app.backoffice.MachineCore;
import com.bsd.cse.app.input.MachineTimeCore;
import com.bsd.cse.app.input.ProductLineCore;
import com.bsd.cse.model.input.ProductLine;
import com.bsd.cse.model.input.ProductLineTime;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.model.backoffice.part.Process;
import com.bsd.cse.model.input.MachineTime;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
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
public class MachineTimeRenderer implements RowRenderer{

    private static Log LOG = LogFactory.getLog(MachineTimeRenderer.class);
    private Window editWin;
    private Textbox idTxt;
    private Datebox lineDateBox;
    private Combobox machineEditCbox;
    private Combobox processEditCbox;
    private Combobox periodEditCbox;
    private Combobox partEditCbox;

    private Longbox aTxt;
    private Longbox bTxt;
    private Longbox cTxt;
    private Longbox dTxt;
    private Longbox eTxt;
    private Longbox f1Txt;
    private Longbox f2Txt;
    private Longbox f3Txt;
    private Longbox hTxt;
    private Longbox iTxt;
    private Longbox jTxt;
    private Longbox k1Txt;
    private Longbox k2Txt;
    private Longbox k3Txt;
    private Longbox mTxt;    
    private Longbox anTxt;
    private Longbox bnTxt;
    private Longbox cnTxt;
    private Longbox dnTxt;
    private Longbox enTxt;
    private Longbox fn1Txt;
    private Longbox fn2Txt;
    private Longbox fn3Txt;
    private Longbox hnTxt;
    private Longbox inTxt;
    private Longbox jnTxt;
    private Longbox kn1Txt;
    private Longbox kn2Txt;
    private Longbox kn3Txt;

    private Button searchBtn;
        
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    
    public MachineTimeRenderer(Window editWin,Textbox idTxt,Datebox lineDateBox,Combobox machineEditCbox,Combobox partEditCbox,Combobox processEditCbox,Combobox periodEditCbox
            ,Longbox aTxt ,Longbox bTxt ,Longbox cTxt ,Longbox dTxt ,Longbox eTxt ,Longbox f1Txt ,Longbox f2Txt
            ,Longbox f3Txt ,Longbox hTxt ,Longbox iTxt ,Longbox jTxt ,Longbox k1Txt ,Longbox k2Txt ,Longbox k3Txt
            ,Longbox mTxt            
            ,Button searchBtn)
    {        
        this.editWin = editWin; 
        this.machineEditCbox = machineEditCbox;
        this.processEditCbox = processEditCbox;
        this.periodEditCbox = periodEditCbox;
        this.partEditCbox = partEditCbox;
        this.lineDateBox = lineDateBox;
        this.idTxt = idTxt;
        this.aTxt = aTxt;
        this.bTxt = bTxt;
        this.cTxt = cTxt;
        this.dTxt = dTxt;
        this.eTxt = eTxt;
        this.f1Txt = f1Txt;
        this.f2Txt = f2Txt;
        this.f3Txt = f3Txt;
        this.hTxt = hTxt;
        this.iTxt = iTxt;
        this.jTxt = jTxt;
        this.k1Txt = k1Txt;
        this.k2Txt = k2Txt;
        this.k3Txt = k3Txt;
        this.mTxt = mTxt;        
        this.searchBtn = searchBtn;
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
            cell1.setColspan(6);
            row.appendChild(cell1);
        }
        else
        {
            MachineTime machineTime =(MachineTime)o;
            Cell cell1 = new Cell();
            Cell cell2 = new Cell();
            Cell cell3 = new Cell();
            Cell cell4 = new Cell();
            Cell cell5 = new Cell();
            Cell cell6 = new Cell();
            Label label5 = new Label();
            Label label4 = new Label();
            Label label3 = new Label();
            Label label2 = new Label();
            Label label = new Label();
            Toolbarbutton buttonEdit = new Toolbarbutton();
            Toolbarbutton buttonDel = new Toolbarbutton();

            label.setValue(dateFormat.format(machineTime.getScheduleTime()));
            cell1.appendChild(label);
            row.appendChild(cell1);

            label2.setValue(machineTime.getMachine().getName());
            cell2.appendChild(label2);
            row.appendChild(cell2);

            label5.setValue(machineTime.getPart().getPartNo());
            cell6.appendChild(label5);
            row.appendChild(cell6);

            label3.setValue(machineTime.getProcess().getProcessName());
            cell3.appendChild(label3);
            row.appendChild(cell3);

            label4.setValue(getPeriod(machineTime.getPeriod()));
            cell5.appendChild(label4);
            row.appendChild(cell5);

            

            if(Executions.getCurrent().isUserInRole("1020302"))
            {
                buttonEdit.setImage("/images/input/edit.png");
                buttonEdit.addEventListener(Events.ON_CLICK, new EditEventListener());
                buttonEdit.setAttribute("object", o);
                cell4.appendChild(buttonEdit);
            }

            if(Executions.getCurrent().isUserInRole("1020303"))
            {
                buttonDel.setImage("/images/input/delete.png");
                buttonDel.addEventListener(Events.ON_CLICK, new DeleteEventListener());
                buttonDel.setAttribute("object", o);
                cell4.appendChild(buttonDel);
            }
            row.appendChild(cell4);
        }
    }

    class EditEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            MachineTime object = (MachineTime)event.getTarget().getAttribute("object");           
            idTxt.setValue(String.valueOf(object.getId()));
            lineDateBox.setValue(object.getScheduleTime());
            lineDateBox.setDisabled(true);
            machineEditCbox.getChildren().clear();
            Comboitem item = new Comboitem();
            item.setValue(object.getMachine());
            item.setLabel(object.getMachine().getName());
            machineEditCbox.getChildren().add(item);
            machineEditCbox.setSelectedIndex(0);

            processEditCbox.getChildren().clear();
            Comboitem processItem = new Comboitem();
            processItem.setValue(object.getProcess());
            processItem.setLabel(object.getProcess().getProcessName());
            processEditCbox.getChildren().add(processItem);
            processEditCbox.setSelectedIndex(0);

            periodEditCbox.getChildren().clear();
            Comboitem periodItem = new Comboitem();
            periodItem.setValue(object.getPeriod());
            periodItem.setLabel(getPeriod(object.getPeriod()));
            periodEditCbox.getChildren().add(periodItem);
            periodEditCbox.setSelectedIndex(0);

            partEditCbox.getChildren().clear();
            Comboitem partItem = new Comboitem();
            partItem.setValue(object.getPart());
            partItem.setLabel(object.getPart().getPartNo());
            partEditCbox.getChildren().add(partItem);
            partEditCbox.setSelectedIndex(0);
            
            aTxt.setValue(object.getApTime());
            bTxt.setValue(object.getBpTime());
            cTxt.setValue(object.getCpTime());
            dTxt.setValue(object.getDpTime());
            eTxt.setValue(object.getEpTime());
            f1Txt.setValue(object.getFp1Time());
            f2Txt.setValue(object.getFp2Time());
            f3Txt.setValue(object.getFp3Time());
            hTxt.setValue(object.getHpTime());
            iTxt.setValue(object.getIpTime());
            jTxt.setValue(object.getJpTime());
            k1Txt.setValue(object.getKp1Time());
            k2Txt.setValue(object.getKp2Time());
            k3Txt.setValue(object.getKp3Time());
            mTxt.setValue(object.getMpTime());            
            editWin.doModal();
        }

    }

    class AddEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            MachineTime object = (MachineTime)event.getTarget().getAttribute("object");
            idTxt.setValue("");
            
            editWin.doModal();
        }

    }

    class DeleteEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            HttpUserSession userSession = new HttpUserSession();
            Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
            MachineTime object = (MachineTime)event.getTarget().getAttribute("object");
            int messageValue = AlertMessages.confirmMessage("machinetime.alert.message.4");
            if(messageValue == 0 || messageValue == AlertMessages.NO)
            {
                return;
            }
            
            MachineTimeCore core = new MachineTimeCore();

            HashMap<String,Object> results = core.delete(object);
            if((Boolean)results.get("results"))
            {
                AlertMessages.alertMessage("machinetime.alert.message.6");
                Events.sendEvent(Events.ON_CLICK,searchBtn, null);
            }
            else
            {
                AlertMessages.alertMessage("machinetime.alert.message.7");
            }
        }

    }

    private String getPeriod(Integer period)
    {
        String periodStr = "Night";
        if(new Integer(1).equals(period))
        {
            periodStr= "Day";
        }       
        return periodStr;
    }
   


}
