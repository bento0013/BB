/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.grid.renderer;

import com.bsd.cse.app.backoffice.MeasurementToolCore;
import com.bsd.cse.app.input.MeasurementPartCore;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.model.input.MeasurementPart;
import com.bsd.cse.model.input.MeasurementCheckpoint;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import java.text.DecimalFormat;
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
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
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
public class MeasurementPartRenderer implements RowRenderer{

    private static Log LOG = LogFactory.getLog(MeasurementPartRenderer.class);    ;
    private Window editWin;
    private Textbox idTxt;
    private Combobox partEditCbox;
    private Combobox measurementToolEditCbox;
    private Combobox timeEditCbox;
    private Combobox processEditCbox;
    private Combobox checkpointEditCbox;
    private Combobox machineEditCbox;
    private Combobox periodEditCbox;
    private Decimalbox valueEditTxt;

    private Datebox lineDateBox;
    private Button searchBtn;
    
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    
    public MeasurementPartRenderer(Window editWin,Textbox idTxt,Datebox lineDateBox,Combobox measurementToolEditCbox,Combobox partEditCbox,Combobox timeEditCbox,Decimalbox valueEditTxt,Combobox processEditCbox,Combobox checkpointEditCbox,Combobox machineEditCbox,Button searchBtn)
    {
        this.editWin = editWin;
        this.valueEditTxt = valueEditTxt;
        this.measurementToolEditCbox = measurementToolEditCbox;
        this.partEditCbox = partEditCbox;
        this.lineDateBox = lineDateBox;
        this.timeEditCbox = timeEditCbox;
        this.processEditCbox = processEditCbox;
        this.checkpointEditCbox = checkpointEditCbox;
        this.machineEditCbox = machineEditCbox;
//        this.periodEditCbox = periodEditCbox;
        this.idTxt = idTxt;
        this.searchBtn = searchBtn;
    }

    @Override
    public void render(Row row, Object o) throws Exception {

        if(o instanceof String)
        {
            Cell cell = new Cell();
            cell.setStyle("vertical-align:top");

            Vbox vbox = new Vbox();
            Hbox hbox = new Hbox();
            Label label =new Label();
            label.setValue((String)o) ;
            hbox.appendChild(label);
            vbox.appendChild(hbox);
            cell.appendChild(vbox);
            row.appendChild(cell);cell.setColspan(61);
        }
        else
        {
            HttpUserSession userSession = new HttpUserSession();
            MeasurementCheckpoint measurementCheckpointLine =(MeasurementCheckpoint)o;

            Cell cell = new Cell();
            cell.setStyle("vertical-align:top");
            Vbox vbox = new Vbox();
            Hbox hbox = new Hbox();
            Label label =new Label();
            label.setValue(String.valueOf(measurementCheckpointLine.getCheckpoint().getPosition())) ;
            hbox.appendChild(label);
            vbox.appendChild(hbox);
            cell.appendChild(vbox);
            row.appendChild(cell);

            Cell cell1 = new Cell();
            cell1.setStyle("vertical-align:top");
            Vbox vbox1 = new Vbox();
            vbox1.setAlign("center");
            vbox1.setPack("center");
            Hbox hbox1_1 = new Hbox();
            hbox1_1.setHflex("1");
            hbox1_1.setAlign("center");
            hbox1_1.setPack("center");
            Hbox hbox1_2 = new Hbox();
            hbox1_2.setHflex("1");
            hbox1_2.setAlign("center");
            hbox1_2.setPack("center");
            Hbox hbox1_3 = new Hbox();
            hbox1_3.setHflex("1");
            hbox1_3.setAlign("center");
            hbox1_3.setPack("center");
            Label label1_1 =new Label();
            label1_1.setValue(String.valueOf(measurementCheckpointLine.getCheckpoint().getCheckpointType().getName())) ;
            Label label1_2 =new Label();
            DecimalFormat format = new DecimalFormat("#,##0.000");
            label1_2.setValue((measurementCheckpointLine.getCheckpoint().getMinDuration()!= null?format.format(measurementCheckpointLine.getCheckpoint().getMinDuration()):"")+"-"+(measurementCheckpointLine.getCheckpoint().getMinDuration()!= null?format.format(measurementCheckpointLine.getCheckpoint().getMaxDuration()):"")+" "+String.valueOf(measurementCheckpointLine.getCheckpoint().getCheckpointUnit().getName())) ;
            Label label1_3 =new Label();
            label1_3.setValue(String.valueOf(measurementCheckpointLine.getCheckpoint().getMeasurementType().getName())) ;
            hbox1_1.appendChild(label1_1);
            vbox1.appendChild(hbox1_1);
            hbox1_2.appendChild(label1_2);
            vbox1.appendChild(hbox1_2);
            hbox1_3.appendChild(label1_3);
            vbox1.appendChild(hbox1_3);
            cell1.appendChild(vbox1);
            row.appendChild(cell1);

            for(MeasurementPart measurementPart:measurementCheckpointLine.getMeasurementParts())
            {

                Cell processCell = new Cell();
                Vbox processVbox = new Vbox();
                Hbox processHbox = new Hbox();


                processCell.setStyle("vertical-align:top");

                if(measurementPart.getId() != null)
                {
                    Hbox processHbox2 = new Hbox();
                    Hbox processHbox3 = new Hbox();
                    Hbox processHbox4 = new Hbox();
                    Hbox processHbox5 = new Hbox();
                    Hbox processHbox6 = new Hbox();
                    Hbox processHbox7 = new Hbox();
                    Label process2Label =new Label();
                    Label process3Label =new Label();
                    Label process5Label =new Label();
                    Label process6Label =new Label();
                    Label process7Label =new Label();
                    Label processLabel1 =new Label();

                    processLabel1.setValue(ResourceUtil.getLabel("measurementpart.page.label.detail.value")+measurementPart.getMeasurementValue()!= null?format.format(measurementPart.getMeasurementValue()):"");
                    processHbox.appendChild(processLabel1);
                    processVbox.appendChild(processHbox);

                    process2Label.setValue(ResourceUtil.getLabel("measurementpart.page.label.detail.codeno")+measurementPart.getMeasurementTool().getCodeNo()) ;
                    processHbox2.appendChild(process2Label);
                    processVbox.appendChild(processHbox2);

                    process3Label.setValue(ResourceUtil.getLabel("measurementpart.page.label.detail.createdtime")+dateFormat.format(measurementPart.getCreatedDate())) ;
                    processHbox3.appendChild(process3Label);
                    processVbox.appendChild(processHbox3);

                    process5Label.setValue(ResourceUtil.getLabel("measurementpart.page.label.detail.createdby")+(measurementPart.getCreatedBy() != null?(measurementPart.getCreatedBy().getFirstname()!=null?measurementPart.getCreatedBy().getFirstname():null)+" "+(measurementPart.getCreatedBy().getLastname()!=null?measurementPart.getCreatedBy().getLastname():null):"")) ;
                    processHbox5.appendChild(process5Label);
                    processVbox.appendChild(processHbox5);

                    process6Label.setValue(ResourceUtil.getLabel("measurementpart.page.label.detail.updatedtime")+(measurementPart.getUpdatedDate() != null?dateFormat.format(measurementPart.getUpdatedDate()):"")) ;
                    processHbox6.appendChild(process6Label);
                    processVbox.appendChild(processHbox6);

                    process7Label.setValue(ResourceUtil.getLabel("measurementpart.page.label.detail.updateby")+(measurementPart.getUpdatedBy() != null?(measurementPart.getUpdatedBy().getFirstname()!=null?measurementPart.getUpdatedBy().getFirstname():null)+" "+(measurementPart.getUpdatedBy().getLastname()!=null?measurementPart.getUpdatedBy().getLastname():null):"")) ;
                    processHbox7.appendChild(process7Label);
                    processVbox.appendChild(processHbox7);
                    if(Executions.getCurrent().isUserInRole("1020202"))
                    {
                        Toolbarbutton editButton = new Toolbarbutton();
                        editButton.setImage("/images/input/edit.png");
                        editButton.setAttribute("object", measurementPart);
                        editButton.addEventListener(Events.ON_CLICK, new EditEventListener());
                        processHbox4.appendChild(editButton);
                    }
                    if(Executions.getCurrent().isUserInRole("1020203"))
                    {
                        Toolbarbutton deleteButton = new Toolbarbutton();
                        deleteButton.setImage("/images/input/delete.png");
                        deleteButton.setAttribute("object", measurementPart);
                        deleteButton.addEventListener(Events.ON_CLICK, new DeleteEventListener());
                        processHbox4.appendChild(deleteButton);
                    }

                    if(Executions.getCurrent().isUserInRole("1020202") || Executions.getCurrent().isUserInRole("1020203"))
                    {
                        processVbox.appendChild(processHbox4);
                    }

                }
                else
                {
                    Label processLabel =new Label();
                    processLabel.setValue("No Data") ;
                    processHbox.appendChild(processLabel);
                    processVbox.appendChild(processHbox);
                    Hbox processHbox2 = new Hbox();


                    if(Executions.getCurrent().isUserInRole("1020201"))
                    {
                        Toolbarbutton addButton = new Toolbarbutton();
                        addButton.setImage("/images/input/add.png");
                        addButton.setAttribute("object", measurementPart);
                        processHbox2.appendChild(addButton);
                        addButton.addEventListener(Events.ON_CLICK, new AddEventListener());
                        processVbox.appendChild(processHbox2);
                    }


                }
                processCell.appendChild(processVbox);
                row.appendChild(processCell);
            }
            
            
        }
    }

    class EditEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            MeasurementPart measurementPart = (MeasurementPart)event.getTarget().getAttribute("object");            
            idTxt.setValue(String.valueOf(measurementPart.getId()));
            lineDateBox.setValue(measurementPart.getMeasurementDate());
            Comboitem partItem = new Comboitem();
            partItem.setLabel(measurementPart.getPart().getPartNo());
            partItem.setValue(measurementPart.getPart());
            partEditCbox.getItems().clear();
            partEditCbox.getItems().add(partItem);
            partEditCbox.setSelectedIndex(0);               
            Comboitem timeItem = new Comboitem();
            timeItem.setLabel(String.valueOf(measurementPart.getMeasurementTime().getTimeName()));
            timeItem.setValue(measurementPart.getMeasurementTime());
            timeEditCbox.getItems().clear();
            timeEditCbox.getItems().add(timeItem);
            timeEditCbox.setSelectedIndex(0);

            Comboitem processItem = new Comboitem();
            processItem.setLabel(measurementPart.getProcess().getProcessName());
            processItem.setValue(measurementPart.getProcess());
            processEditCbox.getItems().clear();
            processEditCbox.getItems().add(processItem);
            processEditCbox.setSelectedIndex(0);

            Comboitem positionItem = new Comboitem();
            positionItem.setLabel(String.valueOf(measurementPart.getCheckpoint().getCheckpointType().getName()));
            positionItem.setValue(measurementPart.getCheckpoint());
            checkpointEditCbox.getItems().clear();
            checkpointEditCbox.getItems().add(positionItem);
            checkpointEditCbox.setSelectedIndex(0);

            Comboitem machineItem = new Comboitem();
            machineItem.setLabel(String.valueOf(measurementPart.getMachine().getName()));
            machineItem.setValue(measurementPart.getMachine());
            machineEditCbox.getItems().clear();
            machineEditCbox.getItems().add(machineItem);
            machineEditCbox.setSelectedIndex(0);
            
//            Comboitem periodItem = new Comboitem();
//            periodItem.setLabel(measurementPart.getPeriod().equals(1)?"Mid Day":"Mid Night");
//            periodItem.setValue(measurementPart.getPeriod());
//            periodEditCbox.getItems().clear();
//            periodEditCbox.getItems().add(periodItem);
//            periodEditCbox.setSelectedIndex(0);
            
            valueEditTxt.setValue(measurementPart.getMeasurementValue());         
            MeasurementToolCore measurementToolCore = new MeasurementToolCore();
            measurementToolEditCbox.getItems().clear();
            ZKCatalogs.setMeasurementTools(measurementToolEditCbox,measurementToolCore.getListFromCheckpoint(measurementPart.getCheckpoint()),"codeNo",null);
            measurementToolEditCbox.setValue(measurementPart.getMeasurementTool().getCodeNo());
            editWin.doModal();
        }

    }

    class AddEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            MeasurementPart measurementPart = (MeasurementPart)event.getTarget().getAttribute("object");
            idTxt.setValue("");
            lineDateBox.setValue(measurementPart.getMeasurementDate());
            Comboitem partItem = new Comboitem();
            partItem.setLabel(measurementPart.getPart().getPartNo());
            partItem.setValue(measurementPart.getPart());
            partEditCbox.getItems().clear();
            partEditCbox.getItems().add(partItem);
            partEditCbox.setSelectedIndex(0);
            
            Comboitem timeItem = new Comboitem();
            timeItem.setLabel(String.valueOf(measurementPart.getMeasurementTime().getTimeName()));
            timeItem.setValue(measurementPart.getMeasurementTime());
            timeEditCbox.getItems().clear();
            timeEditCbox.getItems().add(timeItem);
            timeEditCbox.setSelectedIndex(0);

            Comboitem processItem = new Comboitem();
            processItem.setLabel(measurementPart.getProcess().getProcessName());
            processItem.setValue(measurementPart.getProcess());
            processEditCbox.getItems().clear();
            processEditCbox.getItems().add(processItem);
            processEditCbox.setSelectedIndex(0);

            Comboitem positionItem = new Comboitem();
            positionItem.setLabel(String.valueOf(measurementPart.getCheckpoint().getCheckpointType().getName()));
            positionItem.setValue(measurementPart.getCheckpoint());
            checkpointEditCbox.getItems().clear();
            checkpointEditCbox.getItems().add(positionItem);
            checkpointEditCbox.setSelectedIndex(0);

            Comboitem machineItem = new Comboitem();
            machineItem.setLabel(String.valueOf(measurementPart.getMachine().getName()));
            machineItem.setValue(measurementPart.getMachine());
            machineEditCbox.getItems().clear();
            machineEditCbox.getItems().add(machineItem);
            machineEditCbox.setSelectedIndex(0);
            
//            Comboitem periodItem = new Comboitem();
//            periodItem.setLabel(measurementPart.getPeriod().equals(1)?"Mid Day":"Mid Night");
//            periodItem.setValue(measurementPart.getPeriod());
//            periodEditCbox.getItems().clear();
//            periodEditCbox.getItems().add(periodItem);
//            periodEditCbox.setSelectedIndex(0);

            valueEditTxt.setValue(measurementPart.getMeasurementValue());
            MeasurementToolCore measurementToolCore = new MeasurementToolCore();
            measurementToolEditCbox.getItems().clear();
            ZKCatalogs.setMeasurementTools(measurementToolEditCbox,measurementToolCore.getListFromCheckpoint(measurementPart.getCheckpoint()),"codeNo",null);
            measurementToolEditCbox.setValue(measurementPart.getMeasurementTool() != null?measurementPart.getMeasurementTool().getCodeNo():"");
            editWin.doModal();
        }

    }

    class DeleteEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {            
            MeasurementPart object = (MeasurementPart)event.getTarget().getAttribute("object");
            int messageValue = AlertMessages.confirmMessage("measurementpart.alert.message.4");
            if(messageValue == 0 || messageValue == AlertMessages.NO)
            {
                return;
            }
            
            MeasurementPartCore core = new MeasurementPartCore();

            HashMap<String,Object> results = core.delete(object);
            if((Boolean)results.get("results"))
            {
                AlertMessages.alertMessage("measurementpart.alert.message.6");
                Events.sendEvent(Events.ON_CLICK,searchBtn, null);
            }
            else
            {
                AlertMessages.alertMessage("measurementpart.alert.message.7");
            }
        }

    }
   


}
