/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.grid.renderer;

import com.bsd.cse.app.backoffice.MachineCore;
import com.bsd.cse.app.input.ProductLineCore;
import com.bsd.cse.model.input.ProductLine;
import com.bsd.cse.model.input.ProductLineTime;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.model.backoffice.part.Process;
import com.bsd.cse.model.input.NgProductLineRecord;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.combobox.ZKCatalogs;
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
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;


/**
 *
 * @author bento
 */
public class ProductLineRenderer implements RowRenderer{

    private static Log LOG = LogFactory.getLog(ProductLineRenderer.class);
    private List<Process> processes;
    private Window editWin;
    private Textbox idTxt;
    private Combobox partEditCbox;
//    private Combobox employeeEditCbox;
    private Combobox machineEditCbox;
    private Combobox periodEditCbox;
    private Combobox processEditCbox;
    private Longbox bpTxt;
    private Longbox wpTxt;
    private Longbox okTxt;
    private Longbox ngTxt;
    private Datebox lineDateBox;
    private Grid ngGrid;
    private Button searchBtn;
    
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    
    public ProductLineRenderer(List<Process> processes,Window editWin,Textbox idTxt,Datebox lineDateBox,Combobox machineEditCbox,Combobox partEditCbox,Combobox periodEditCbox,Longbox bpTxt,Longbox wpTxt,Longbox okTxt,Longbox ngTxt,Combobox processEditCbox,Grid ngGrid,Button searchBtn)
    {
        this.processes = processes;
        this.editWin = editWin;
        this.okTxt = okTxt;
        this.ngTxt = ngTxt;
        this.wpTxt = wpTxt;
        this.bpTxt = bpTxt;
//        this.employeeEditCbox = employeeEditCbox;
        this.machineEditCbox = machineEditCbox;
        this.partEditCbox = partEditCbox;
        this.lineDateBox = lineDateBox;
        this.periodEditCbox = periodEditCbox;
        this.processEditCbox = processEditCbox;
        this.ngGrid = ngGrid;
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
            ProductLineTime productTime =(ProductLineTime)o;

            Cell cell = new Cell();
            cell.setStyle("vertical-align:top");
            Vbox vbox = new Vbox();
            Hbox hbox = new Hbox();
            Label label =new Label();
            label.setValue(timeFormat.format(productTime.getTime().getBeginTime())+"-"+timeFormat.format(productTime.getTime().getEndTime())) ;
            hbox.appendChild(label);
            vbox.appendChild(hbox);
            cell.appendChild(vbox);
            row.appendChild(cell);

            for(ProductLine productLine:productTime.getProductLines())
            {

                Cell processCell = new Cell();
                Vbox processVbox = new Vbox();
                Hbox processHbox = new Hbox();


                processCell.setStyle("vertical-align:top");

                if(productLine.getId() != null)
                {
                    Hbox processHbox2 = new Hbox();
                    Hbox processHbox3 = new Hbox();
                    Hbox processHbox4 = new Hbox();
                    Hbox processHbox5 = new Hbox();
                    Hbox processHbox6 = new Hbox();
                    Hbox processHbox7 = new Hbox();
                    Label process2Label =new Label();
                    Label process3Label =new Label();
                    Label processLabel1 =new Label();
                    Label processLabel2 =new Label();
                    Label processLabel3 =new Label();
                    Label processLabel4 =new Label();
                    Label process5Label =new Label();
                    Label process6Label =new Label();
                    Label process7Label =new Label();


                    processLabel1.setValue(ResourceUtil.getLabel("productline.page.label.detail.bp")+productLine.getBp());
                    processLabel2.setValue(ResourceUtil.getLabel("productline.page.label.detail.wp")+productLine.getWp());
                    processLabel3.setValue(ResourceUtil.getLabel("productline.page.label.detail.ok")+productLine.getOk());
                    processLabel4.setValue(ResourceUtil.getLabel("productline.page.label.detail.ng")+productLine.getNg());
                    processHbox.appendChild(processLabel1);
                    processHbox.appendChild(processLabel2);
                    processHbox.appendChild(processLabel3);
                    processHbox.appendChild(processLabel4);
                    processVbox.appendChild(processHbox);

                    process2Label.setValue(ResourceUtil.getLabel("productline.page.label.detail.machine")+productLine.getMachine().getName()) ;
                    processHbox2.appendChild(process2Label);
                    processVbox.appendChild(processHbox2);

                    process3Label.setValue(ResourceUtil.getLabel("productline.page.label.detail.createdtime")+dateFormat.format(productLine.getCreatedDate())) ;
                    processHbox3.appendChild(process3Label);
                    processVbox.appendChild(processHbox3);


                    process5Label.setValue(ResourceUtil.getLabel("productline.page.label.detail.createdby")+(productLine.getCreatedBy() != null?(productLine.getCreatedBy().getFirstname()!=null?productLine.getCreatedBy().getFirstname():null)+" "+(productLine.getCreatedBy().getLastname()!=null?productLine.getCreatedBy().getLastname():null):"")) ;
                    processHbox5.appendChild(process5Label);
                    processVbox.appendChild(processHbox5);

                    process6Label.setValue(ResourceUtil.getLabel("productline.page.label.detail.updatedtime")+(productLine.getUpdatedDate() != null?dateFormat.format(productLine.getUpdatedDate()):"")) ;
                    processHbox6.appendChild(process6Label);
                    processVbox.appendChild(processHbox6);

                    process7Label.setValue(ResourceUtil.getLabel("productline.page.label.detail.updateby")+(productLine.getUpdatedBy() != null?(productLine.getUpdatedBy().getFirstname()!=null?productLine.getUpdatedBy().getFirstname():null)+" "+(productLine.getUpdatedBy().getLastname()!=null?productLine.getUpdatedBy().getLastname():null):"")) ;
                    processHbox7.appendChild(process7Label);
                    processVbox.appendChild(processHbox7);
                    if(Executions.getCurrent().isUserInRole("1020102"))
                    {
                        Toolbarbutton editButton = new Toolbarbutton();
                        editButton.setImage("/images/input/edit.png");
                        editButton.setAttribute("object", productLine);
                        editButton.addEventListener(Events.ON_CLICK, new EditEventListener());
                        processHbox4.appendChild(editButton);
                    }
                    if(Executions.getCurrent().isUserInRole("1020103"))
                    {
                        Toolbarbutton deleteButton = new Toolbarbutton();
                        deleteButton.setImage("/images/input/delete.png");
                        deleteButton.setAttribute("object", productLine);
                        deleteButton.addEventListener(Events.ON_CLICK, new DeleteEventListener());
                        processHbox4.appendChild(deleteButton);
                    }

                    if(Executions.getCurrent().isUserInRole("1020102") || Executions.getCurrent().isUserInRole("1020103"))
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


                    if(Executions.getCurrent().isUserInRole("1020101") && userSession.getUserInformation().get(Users.TEAM_ID) != null)
//                                    
                    {
                        Toolbarbutton addButton = new Toolbarbutton();
                        addButton.setImage("/images/input/add.png");
                        addButton.setAttribute("object", productLine);
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
            ProductLine productLine = (ProductLine)event.getTarget().getAttribute("object");
            LOG.info("product line = "+productLine.getId());    
            idTxt.setValue(String.valueOf(productLine.getId()));
            lineDateBox.setValue(productLine.getRequestDate());
            Comboitem partItem = new Comboitem();
            partItem.setLabel(productLine.getPart().getPartNo());
            partItem.setValue(productLine.getPart());
            partEditCbox.getItems().clear();
            partEditCbox.getItems().add(partItem);
            partEditCbox.setSelectedIndex(0);   
//            Comboitem employeeItem = new Comboitem();
//            employeeItem.setLabel(productLine.getUser().getUsername());
//            employeeItem.setValue(productLine.getUser());
//            employeeEditCbox.getItems().clear();
//            employeeEditCbox.getItems().add(employeeItem);
//            employeeEditCbox.setSelectedIndex(0);
            Comboitem periodItem = new Comboitem();
            periodItem.setLabel(productLine.getTime().getRound().equals(new Integer(1))?"Mid Day":"Mid Night");
            periodItem.setValue(productLine.getTime());
            periodEditCbox.getItems().clear();
            periodEditCbox.getItems().add(periodItem);
            periodEditCbox.setSelectedIndex(0);

            Comboitem processItem = new Comboitem();
            processItem.setLabel(productLine.getProcess().getProcessName());
            processItem.setValue(productLine.getProcess());
            processEditCbox.getItems().clear();
            processEditCbox.getItems().add(processItem);
            processEditCbox.setSelectedIndex(0);

            if(productLine.getProcess().getId() > 1)
            {
                
                ProductLineCore core = new ProductLineCore();
                core.getBpFromBeforeProcess(productLine);
                LOG.info("productLine.getBp() = "+productLine.getBp());
                bpTxt.setValue(productLine.getBp());
//                bpTxt.setDisabled(true);
            }
            else
            {
                bpTxt.setDisabled(false);
                bpTxt.setValue(productLine.getBp());
            }
//            bpTxt.setValue(productLine.getBp());
            wpTxt.setValue(productLine.getWp());
            okTxt.setValue(productLine.getOk());
            ngTxt.setValue(null);
//            MachineCore machineCore = new MachineCore();
//            machineEditCbox.getItems().clear();
//
//            ZKCatalogs.setMachines(machineEditCbox,machineCore.getListFromProcessPart(productLine.getProcess().getId(), productLine.getPart().getId()));
//            machineEditCbox.setValue(productLine.getMachine().getName());
            Comboitem machineItem = new Comboitem();
            machineItem.setLabel(productLine.getMachine().getName());
            machineItem.setValue(productLine.getMachine());
            machineEditCbox.getItems().clear();
            machineEditCbox.getItems().add(machineItem);
            machineEditCbox.setSelectedIndex(0);
            ngGrid.getRows().getChildren().clear();
            ProductLineCore core = new ProductLineCore();
            List<NgProductLineRecord> records = core.getNgReasonList( productLine);
            for(NgProductLineRecord record :records)
            {
                addNgRecord(record);
            }
                
            editWin.doModal();
        }

    }

    class AddEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            ProductLine productLine = (ProductLine)event.getTarget().getAttribute("object");
            idTxt.setValue("");
            lineDateBox.setValue(productLine.getRequestDate());
            Comboitem partItem = new Comboitem();
            partItem.setLabel(productLine.getPart().getPartNo());
            partItem.setValue(productLine.getPart());
            partEditCbox.getItems().clear();
            partEditCbox.getItems().add(partItem);
            partEditCbox.setSelectedIndex(0);
//            Comboitem employeeItem = new Comboitem();
//            employeeItem.setLabel(productLine.getUser().getUsername());
//            employeeItem.setValue(productLine.getUser());
//            employeeEditCbox.getItems().clear();
//            employeeEditCbox.getItems().add(employeeItem);
//            employeeEditCbox.setSelectedIndex(0);
            Comboitem periodItem = new Comboitem();
            periodItem.setLabel(productLine.getTime().getRound().equals(new Integer(1))?"Mid Day":"Mid Night");
            periodItem.setValue(productLine.getTime());
            periodEditCbox.getItems().clear();
            periodEditCbox.getItems().add(periodItem);
            periodEditCbox.setSelectedIndex(0);

            Comboitem processItem = new Comboitem();
            processItem.setLabel(productLine.getProcess().getProcessName());
            processItem.setValue(productLine.getProcess());
            processEditCbox.getItems().clear();
            processEditCbox.getItems().add(processItem);
            processEditCbox.setSelectedIndex(0);

            if(productLine.getProcess().getId() > 1)
            {
                bpTxt.setDisabled(true);
                ProductLineCore core = new ProductLineCore();
                core.getBpFromBeforeProcess(productLine);
                bpTxt.setValue(productLine.getBp());
            }
            else
            {
                bpTxt.setDisabled(false);
                bpTxt.setValue(productLine.getBp());
            }
            
//            wpTxt.setValue(productLine.getWp());
            okTxt.setValue(productLine.getOk());
            ngTxt.setValue(null);
            ngGrid.getRows().getChildren().clear();
//            MachineCore machineCore = new MachineCore();
            
//            ZKCatalogs.setMachines(machineEditCbox,machineCore.getListFromProcessPart(productLine.getProcess().getId(), productLine.getPart().getId()));
            Comboitem machineItem = new Comboitem();
            machineItem.setLabel(productLine.getMachine().getName());
            machineItem.setValue(productLine.getMachine());
            machineEditCbox.getItems().clear();
            machineEditCbox.getItems().add(machineItem);
            machineEditCbox.setSelectedIndex(0);
//            machineEditCbox.
//            machineEditCbox.setValue(productLine.getMachine() != null?productLine.getMachine().getName():"");
            editWin.doModal();
        }

    }

    class DeleteEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            HttpUserSession userSession = new HttpUserSession();
            Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
            ProductLine object = (ProductLine)event.getTarget().getAttribute("object");
            int messageValue = AlertMessages.confirmMessage("productline.alert.message.4");
            if(messageValue == 0 || messageValue == AlertMessages.NO)
            {
                return;
            }
            
            ProductLineCore core = new ProductLineCore();

            HashMap<String,Object> results = core.delete(object);
            if((Boolean)results.get("results"))
            {
                AlertMessages.alertMessage("productline.alert.message.6");
                Events.sendEvent(Events.ON_CLICK,searchBtn, null);
            }
            else
            {
                AlertMessages.alertMessage("productline.alert.message.7");
            }
        }

    }

    public void addNgRecord(NgProductLineRecord ngRecord)
    {
        Row row = new Row();
        row.setValue(ngRecord);
        Cell cell = new Cell();
        Label label = new Label(String.valueOf(ngRecord.getNgAmount()));
        cell.appendChild(label);
        row.appendChild(cell);
        row.setValue(ngRecord);


        cell = new Cell();
        label= new Label(String.valueOf(ngRecord.getNgReason().getName()));
        cell.appendChild(label);
        cell.setStyle("vertical-align:middle");
        row.appendChild(cell);

        cell = new Cell();
        Toolbarbutton deleteBtn = new Toolbarbutton();
        deleteBtn.setImage("/images/backoffice/delete.png");
        deleteBtn.setTooltiptext(ResourceUtil.getLabel("backoffice.checkpoint.page.btn.delete"));
        deleteBtn.addEventListener(Events.ON_CLICK, new DeleteObjectEventListener());
        deleteBtn.setAttribute("object", row);
        deleteBtn.setParent(cell);
        cell.setStyle("vertical-align:middle");
        row.appendChild(cell);
        ngGrid.getRows().getChildren().add(row);
        row.setId(String.valueOf(ngGrid.getRows().getChildren().size()));
    }

    class DeleteObjectEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            Row object = (Row)event.getTarget().getAttribute("object");
            Rows rows = (Rows)event.getTarget().getParent().getParent().getParent();
            rows.getChildren().remove(object);
        }

    }
   


}
