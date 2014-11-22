/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.input;

import com.bsd.cse.app.backoffice.MachineCore;
import com.bsd.cse.app.backoffice.NgReasonCore;
import com.bsd.cse.app.backoffice.PartCore;
import com.bsd.cse.app.backoffice.ProcessCore;
import com.bsd.cse.app.input.ProductLineCore;
import com.bsd.cse.app.security.FunctionCore;
import com.bsd.cse.app.security.UserCore;
import com.bsd.cse.model.backoffice.machine.Machine;
import com.bsd.cse.model.backoffice.part.NgReason;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.backoffice.part.Process;
import com.bsd.cse.model.input.NgProductLineRecord;
import com.bsd.cse.model.input.ProductLine;
import com.bsd.cse.model.input.ProductLineTime;
import com.bsd.cse.model.input.Time;
import com.bsd.cse.model.security.Function;
import com.bsd.cse.model.security.User;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.grid.renderer.NgProductLineRenderer;
import com.bsd.cse.zk.grid.renderer.ProductLineRenderer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.util.ResourceUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

/**
 *
 * @author bento
 */
public class ProductLineController extends SecurityController{
    private static final long serialVersionUID = 4374154270052271685L;

    private static Log LOG = LogFactory.getLog(ProductLineController.class);

    private Datebox lineDatebox;
    private Combobox partCbox;
//    private Combobox employeeCbox;
    private Combobox periodCbox;
    private Combobox machineCbox;
    private Combobox processEditCbox;
    private Toolbarbutton searchBtn;
//    private Button addNgReasonBtn;
    private Button addNgReasonListBtn;
    private Grid productLineGrid;
    private Window editWin;
    private Textbox idTxt;
    private Longbox bpTxt;
    private Longbox wpTxt;
    private Longbox okTxt;
    private Longbox ngTxt;
    private Combobox machineEditCbox;
    private Button cancelBtn;
    private Button saveBtn;
    private Combobox periodEditCbox;
    private Combobox ngReasonEditCbox;
//    private Combobox employeeEditCbox;
    private Combobox partEditCbox;
    private Datebox lineEditDatebox;
    private static Function modelTypeFunction;
    private Columns columns;
    private List<Column> columnDatas = new ArrayList<Column>();
    private Grid ngGrid;

    static
    {
        try {
            modelTypeFunction = FunctionCore.getFunction(1050202L);
        } catch (IOException ex) {
            LOG.error(ex.getMessage(),ex);
        }
        catch (Exception ex) {
            LOG.error(ex.getMessage(),ex);
        }
    }

    @Override
    public ComponentInfo doBeforeCompose(Page page,Component component,ComponentInfo comInfo)
    {
        ComponentInfo componentInfo = super.doBeforeCompose(page, component, comInfo);
        
        return componentInfo;
    }

    @Override
    public void doAfterCompose(Component component) throws Exception
    {
        super.doAfterCompose(component);
        createComponent(component);
        addEventListener();
        initialData();
    }

    public void createComponent(Component component)
    {
        searchBtn = (Toolbarbutton)getComponent(component, "searchBtn", searchBtn);        
        
        periodCbox = (Combobox)getComponent(component, "periodCbox", periodCbox);
        machineCbox = (Combobox)getComponent(component, "machineCbox", machineCbox);
//        employeeCbox = (Combobox)getComponent(component, "employeeCbox", employeeCbox);
        partCbox = (Combobox)getComponent(component, "partCbox", partCbox);
        lineDatebox = (Datebox)getComponent(component, "lineDatebox", lineDatebox);
        productLineGrid = (Grid)getComponent(component, "productLineGrid", productLineGrid);
        editWin = (Window)getComponent(component, "editWin", editWin);
        idTxt = (Textbox)getComponent(editWin,"idTxt",idTxt);
        bpTxt = (Longbox)getComponent(editWin,"bpTxt",bpTxt);
        wpTxt = (Longbox)getComponent(editWin,"wpTxt",wpTxt);
        okTxt = (Longbox)getComponent(editWin,"okTxt",okTxt);
        ngTxt = (Longbox)getComponent(editWin,"ngTxt",ngTxt);
        ngGrid = (Grid)getComponent(editWin,"ngGrid",ngGrid);
//        addNgReasonBtn = (Button)getComponent(editWin, "addNgReasonBtn", addNgReasonBtn);
        addNgReasonListBtn = (Button)getComponent(editWin, "addNgReasonListBtn", addNgReasonListBtn);
        machineEditCbox = (Combobox)getComponent(editWin,"machineEditCbox",machineEditCbox);
        periodEditCbox = (Combobox)getComponent(editWin,"periodEditCbox",periodEditCbox);
//        employeeEditCbox = (Combobox)getComponent(editWin,"employeeEditCbox",employeeEditCbox);
        processEditCbox = (Combobox)getComponent(editWin,"processEditCbox",processEditCbox);
        ngReasonEditCbox = (Combobox)getComponent(editWin,"ngReasonEditCbox",ngReasonEditCbox);
        partEditCbox = (Combobox)getComponent(editWin,"partEditCbox",partEditCbox);
        lineEditDatebox = (Datebox)getComponent(editWin,"lineEditDatebox",lineEditDatebox);
        cancelBtn = (Button)getComponent(editWin,"cancelBtn",cancelBtn);
        saveBtn = (Button)getComponent(editWin,"saveBtn",saveBtn);

    }

    private void initialData() throws Exception
    {

        HttpUserSession userSession = new HttpUserSession();
        PartCore partCore = new PartCore();
        Comboitem item = new Comboitem();
        item.setLabel("N/A");
        ZKCatalogs.setParts(partCbox, partCore.getAllList());
        List<Comboitem> items =  partCbox.getItems();
        items.add(0, item);
        partCbox.setSelectedIndex(0);

        MachineCore machineCore = new MachineCore();
        Comboitem machineItem = new Comboitem();
        machineItem.setLabel("N/A");
        ZKCatalogs.setMachines(machineCbox, machineCore.getAllList());
        List<Comboitem> machineItems =  machineCbox.getItems();
        machineItems.add(0, machineItem);
        machineCbox.setSelectedIndex(0);

        NgReasonCore ngReasonCore = new NgReasonCore();
        Comboitem ngReasonItem = new Comboitem();
        ngReasonItem.setLabel("N/A");
        ZKCatalogs.setNgReasons(ngReasonEditCbox, ngReasonCore.getAllList());
        List<Comboitem> ngReasonItems =  ngReasonEditCbox.getItems();
        ngReasonItems.add(0, ngReasonItem);
        ngReasonEditCbox.setSelectedIndex(0);

//        UserCore userCore = new UserCore();
//        Comboitem empNaItem = new Comboitem();
//        empNaItem.setLabel("N/A");
//        ZKCatalogs.setUsers(employeeCbox, userCore.getAllList());
//        List<Comboitem> empItems =  employeeCbox.getItems();
//        empItems.add(0, empNaItem);
//        employeeCbox.setSelectedIndex(0);
//        employeeCbox.setValue((String)userSession.getUserInformation().get(Users.LOGINID));

        Comboitem periodNaItem = new Comboitem();
        periodNaItem.setLabel("N/A");
        Comboitem period1Item = new Comboitem();
        period1Item.setValue(new Integer(1));
        period1Item.setLabel("Mid Day");
        Comboitem period2Item = new Comboitem();
        period2Item.setValue(new Integer(2));
        period2Item.setLabel("Mid Night");
        periodCbox.appendChild(periodNaItem);
        periodCbox.appendChild(period1Item);
        periodCbox.appendChild(period2Item);
        
        
        
        periodCbox.setSelectedIndex(0);

        lineDatebox.setValue(new Date());

        ngGrid.setRowRenderer(new NgProductLineRenderer());
        setNoRecordFound();
    }

    public void addEventListener()
    {
        searchBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                loadContent();
            }
        });

//        addNgReasonBtn.addEventListener(Events.ON_CLICK, new EventListener() {
//
//            @Override
//            public void onEvent(Event event) throws Exception {
//
//                execution.sendRedirect(modelTypeFunction.getCommand());
//
//            }
//        });

        addNgReasonListBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {

                Long ngValue = ngTxt.getValue();
                NgReason ngReason = (NgReason)ngReasonEditCbox.getSelectedItem().getValue();

                if(ngValue == null)
                {
                    AlertMessages.alertMessage("productline.alert.message.required.8");
                    return ;
                }

                if(ngReason == null)
                {
                    AlertMessages.alertMessage("productline.alert.message.required.12");
                    return ;
                }

                NgProductLineRecord ngRecord = new NgProductLineRecord();
                ngRecord.setProductLine(null);
                ngRecord.setNgAmount(ngValue);
                ngRecord.setNgReason(ngReason);
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
                ngTxt.setValue(null);
                ngReasonEditCbox.setSelectedIndex(0);
            }
        });

        saveBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {

                ProductLine productLine = new ProductLine();
                HttpUserSession userSession = new HttpUserSession();
                Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
                String id = idTxt.getValue();                
                Part part = (Part)partEditCbox.getSelectedItem().getValue();
//                User user = (User)employeeEditCbox.getSelectedItem().getValue();
                Machine machine = (Machine)(machineEditCbox.getSelectedItem() != null?machineEditCbox.getSelectedItem().getValue():null);
                Time time = (Time)periodEditCbox.getSelectedItem().getValue();
                Process process = (Process)processEditCbox.getSelectedItem().getValue();
                
                Long bpValue = bpTxt.getValue();
//                Long wpValue = wpTxt.getValue();
                Long okValue = okTxt.getValue();
                Long ngValue = 0L;

                if(part == null)
                {
                    AlertMessages.alertMessage("productline.alert.message.required.1");
                    return ;
                }

//                if(user == null)
//                {
//                    AlertMessages.alertMessage("productline.alert.message.required.2");
//                    return ;
//                }

                if(machine == null)
                {
                    AlertMessages.alertMessage("productline.alert.message.required.3");
                    return ;
                }

                if(time == null)
                {
                    AlertMessages.alertMessage("productline.alert.message.required.4");
                    return ;
                }

                if(bpValue == null)
                {
                    AlertMessages.alertMessage("productline.alert.message.required.5");
                    return ;
                }

//                if(wpValue == null)
//                {
//                    AlertMessages.alertMessage("productline.alert.message.required.6");
//                    return ;
//                }

                if(okValue == null)
                {
                    AlertMessages.alertMessage("productline.alert.message.required.7");
                    return ;
                }

                

                
                                 
                if(id != null && !id.isEmpty())
                {
                    productLine.setId(new Long(id));                   
                }

                if(!(AlertMessages.confirmMessage("productline.alert.message.required.9") == AlertMessages.YES))
                {
                    return ;
                }

                List<Row> rows = ngGrid.getRows().getChildren();
                List<NgProductLineRecord> records = new ArrayList<NgProductLineRecord>();
                for(Row row : rows)
                {
                    NgProductLineRecord record = (NgProductLineRecord)row.getValue();                    
                    records.add(record);
                    ngValue +=record.getNgAmount();
                }

                
                
                productLine.setMachine(machine);
                productLine.setPart(part);
                User user = new User();
                user.setId(userId);
                productLine.setUser(user);
                productLine.setTime(time);
                productLine.setProcess(process);
//                productLine.setNgReason(ngReason);
                productLine.setBp(bpValue);
                productLine.setWp(bpValue-(okValue+ngValue));
                productLine.setOk(okValue);
                productLine.setNg(ngValue);
                productLine.setRequestDate(lineEditDatebox.getValue());

                

                ProductLineCore core = new ProductLineCore();
                try
                {                    
                    HashMap<String,Object> results = core.save(productLine,records,userId);
                    Boolean result = (Boolean)results.get("results");
                    if(result != null && result)
                    {                        
                        editWin.setVisible(false);
                        AlertMessages.successMessage("productline.alert.message.1");
                        Events.sendEvent(Events.ON_CLICK, searchBtn, null);
                        event.stopPropagation();
                    }
                    else if(result != null && !result)
                    {
                        AlertMessages.failMessage("productline.alert.message.2");
                        return;
                    }
                }
                catch(Exception e)
                {
                    if(e.toString().indexOf("ConstraintViolationException") != -1)
                    {
                        AlertMessages.failMessage("productline.alert.message.3");
                    }
                    else
                    {
                        AlertMessages.failMessage("productline.default.alert.message.1");
                    }
                }


                editWin.setVisible(false);
                event.stopPropagation();
            }
        });

        cancelBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                editWin.setVisible(false);
                event.stopPropagation();

            }
        });
    }

    public void loadContent() throws Exception
    {
        ProductLineCore core = new ProductLineCore();

        if(lineDatebox.getValue() == null)
        {
            AlertMessages.alertMessage("productline.alert.message.required.10");
            setNoRecordFound();
            return ;
        }

        if(partCbox.getSelectedIndex() == 0)
        {
            AlertMessages.alertMessage("productline.alert.message.required.1");
            setNoRecordFound();
            return ;
        }

        if(machineCbox.getSelectedIndex() == 0)
        {
            AlertMessages.alertMessage("productline.alert.message.required.3");
            setNoRecordFound();
            return ;
        }

//        if(employeeCbox.getSelectedIndex() == 0)
//        {
//            AlertMessages.alertMessage("productline.alert.message.required.11");
//            setNoRecordFound();
//            return ;
//        }

        if(periodCbox.getSelectedIndex() == 0)
        {
            AlertMessages.alertMessage("productline.alert.message.required.4");
            setNoRecordFound();
            return ;
        }
        Long numProcesses = ((Part)partCbox.getSelectedItem().getValue()).getNumProcesses();
        if(numProcesses == null)
        {
            numProcesses = 0L;
        }

        columns.getChildren().removeAll(columnDatas);
        List<Column> columnProcesses = new ArrayList<Column>();
        ProcessCore processCore = new ProcessCore();
        Long index = 0L;
        for(Process process : processCore.getAllList())
        {
            if(numProcesses.equals(index) )
            {
                break;
            }
            Column column = new Column();
            column.setLabel(process.getProcessName());
            column.setStyle("min-width:50px");
            columnProcesses.add(column);
            index++;
        }
        columnDatas.addAll(columnProcesses);
        columns.getChildren().addAll(columnProcesses);

        HashMap<String,Object> results = core.getProductLines(lineDatebox.getValue()
                , (Part)partCbox.getSelectedItem().getValue()//, (User)employeeCbox.getSelectedItem().getValue()
                , (Machine)machineCbox.getSelectedItem().getValue(), periodCbox.getSelectedIndex());
        productLineGrid.setModel(new ListModelList((List<ProductLineTime>)results.get("results")));
        productLineGrid.setRowRenderer(new ProductLineRenderer((List<Process>)results.get("processes")
                ,editWin,idTxt,lineEditDatebox
                ,machineEditCbox,partEditCbox,periodEditCbox
                ,bpTxt,wpTxt,okTxt,ngTxt,processEditCbox,ngGrid,searchBtn));


        
    }

    private void setNoRecordFound()
    {
        List<String> norecords = new ArrayList<String>();
        norecords.add(ResourceUtil.getLabel("measurementpart.norecordfound"));
        productLineGrid.setModel(new ListModelList(norecords));
        productLineGrid.setRowRenderer(new ProductLineRenderer((List<Process>)null
                ,editWin,idTxt,lineEditDatebox
                ,machineEditCbox,partEditCbox,periodEditCbox                
                ,bpTxt,wpTxt,okTxt,ngTxt,processEditCbox,ngGrid,searchBtn));

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
