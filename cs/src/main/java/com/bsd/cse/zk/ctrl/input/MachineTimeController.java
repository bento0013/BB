/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.input;

import com.bsd.cse.app.backoffice.MachineCore;
import com.bsd.cse.app.backoffice.PartCore;
import com.bsd.cse.app.backoffice.ProcessCore;
import com.bsd.cse.app.input.MachineTimeCore;
import com.bsd.cse.model.backoffice.machine.Machine;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.input.MachineTime;
import com.bsd.cse.model.security.User;
import com.bsd.cse.model.backoffice.part.Process;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.grid.renderer.MachineTimeRenderer;
import com.bsd.cse.zk.grid.renderer.ProductLineRenderer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

/**
 *
 * @author bento
 */
public class MachineTimeController extends SecurityController{
    private static final long serialVersionUID = 4374154270052271685L;

    private static Log LOG = LogFactory.getLog(MachineTimeController.class);

    private Datebox lineDatebox;
    private Combobox machineCbox;
    private Combobox machineEditCbox;
    private Combobox processEditCbox;
    private Combobox processCbox;
    private Combobox periodEditCbox;
    private Combobox periodCbox;
    private Combobox partEditCbox;
    private Combobox partCbox;
    private Toolbarbutton searchBtn;
    private Grid machineTimeGrid;
    private Window editWin;
    private Textbox idTxt;
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
    private Button cancelBtn;
    private Button saveBtn;
    private Button addBtn;
    private Datebox lineEditDatebox;   

    @Override
    public void doAfterCompose(Component component) throws Exception
    {
        super.doAfterCompose(component);
        createComponent(component);
        addEventListener();
        initialData();
        setPermission();

    }

    public void setPermission()
    {
        addBtn.setVisible(execution.isUserInRole("1020301"));
    }

    public void createComponent(Component component)
    {
        searchBtn = (Toolbarbutton)getComponent(component, "searchBtn", searchBtn);
        addBtn = (Toolbarbutton)getComponent(component, "addBtn", addBtn);
        machineCbox = (Combobox)getComponent(component, "machineCbox", machineCbox);
        processCbox = (Combobox)getComponent(component, "processCbox", processCbox);
        lineDatebox = (Datebox)getComponent(component, "lineDatebox", lineDatebox);
        machineTimeGrid = (Grid)getComponent(component, "machineTimeGrid", machineTimeGrid);
        editWin = (Window)getComponent(component, "editWin", editWin);
        idTxt = (Textbox)getComponent(editWin,"idTxt",idTxt);
        aTxt = (Longbox)getComponent(editWin,"aTxt",aTxt);
        bTxt = (Longbox)getComponent(editWin,"bTxt",bTxt);
        cTxt = (Longbox)getComponent(editWin,"cTxt",cTxt);
        dTxt = (Longbox)getComponent(editWin,"dTxt",dTxt);
        eTxt = (Longbox)getComponent(editWin,"eTxt",eTxt);
        f1Txt = (Longbox)getComponent(editWin,"f1Txt",f1Txt);
        f2Txt = (Longbox)getComponent(editWin,"f2Txt",f2Txt);
        f3Txt = (Longbox)getComponent(editWin,"f3Txt",f3Txt);
        hTxt = (Longbox)getComponent(editWin,"hTxt",hTxt);
        iTxt = (Longbox)getComponent(editWin,"iTxt",iTxt);
        jTxt = (Longbox)getComponent(editWin,"jTxt",jTxt);
        k1Txt = (Longbox)getComponent(editWin,"k1Txt",k1Txt);
        k2Txt = (Longbox)getComponent(editWin,"k2Txt",k2Txt);
        k3Txt = (Longbox)getComponent(editWin,"k3Txt",k3Txt);
        mTxt = (Longbox)getComponent(editWin,"mTxt",mTxt);       
        machineEditCbox = (Combobox)getComponent(editWin,"machineEditCbox",machineEditCbox);
        processEditCbox = (Combobox)getComponent(editWin,"processEditCbox",processEditCbox);
        partEditCbox = (Combobox)getComponent(editWin,"partEditCbox",partEditCbox);
        lineEditDatebox = (Datebox)getComponent(editWin,"lineEditDatebox",lineEditDatebox);
        periodEditCbox = (Combobox)getComponent(editWin,"periodEditCbox",periodEditCbox);
        cancelBtn = (Button)getComponent(editWin,"cancelBtn",cancelBtn);
        saveBtn = (Button)getComponent(editWin,"saveBtn",saveBtn);

    }

    private void initialData() throws Exception
    {


        PartCore partCore = new PartCore();
        Comboitem partItem = new Comboitem();
        partItem.setLabel("N/A");
        ZKCatalogs.setParts(partCbox, partCore.getAllList());
        List<Comboitem> partItems =  partCbox.getItems();
        partItems.add(0, partItem);
        partCbox.setSelectedIndex(0);
        
        MachineCore machineCore = new MachineCore();
        Comboitem item = new Comboitem();
        item.setLabel("N/A");
        ZKCatalogs.setMachines(machineCbox, machineCore.getAllList());
        List<Comboitem> items =  machineCbox.getItems();
        items.add(0, item);
        machineCbox.setSelectedIndex(0);

        ProcessCore processCore = new ProcessCore();
        Comboitem processItem = new Comboitem();
        processItem.setLabel("N/A");
//        ZKCatalogs.setProcesses(processCbox, processCore.getAllList());
        List<Comboitem> processItems =  processCbox.getItems();
        processItems.add(0, processItem);
        processCbox.setSelectedIndex(0);

        
        
        Comboitem period0Item = new Comboitem();
        period0Item.setLabel("N/A");
        Comboitem period1Item = new Comboitem();
        period1Item.setLabel("Day");
        period1Item.setValue(new Integer(1));
        Comboitem period2Item = new Comboitem();
        period2Item.setLabel("Night");
        period2Item.setValue(new Integer(2));
        List<Comboitem> periodItems =  periodCbox.getItems();
        periodItems.add(0, period0Item);
        periodItems.add(1, period1Item);
        periodItems.add(2, period2Item);
        periodCbox.setSelectedIndex(0);
        
        lineDatebox.setValue(new Date());

        showNoRecordFound();
    }

    public void addEventListener()
    {
        searchBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                loadContent();
            }
        });

        partEditCbox.addEventListener(Events.ON_CHANGE, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {

                Part part = (Part)partEditCbox.getSelectedItem().getValue();
                Comboitem processItem = new Comboitem();
                processItem.setLabel("");
                List<Comboitem> processItems =  processEditCbox.getItems();
                processItems.clear();
                if(part != null)
                {
                    ProcessCore processCore = new ProcessCore();
                    ZKCatalogs.setProcesses(processEditCbox, processCore.getList(part.getNumProcesses()));

                }
                processItems.add(0, processItem);
                processEditCbox.setSelectedIndex(0);
            }
        });

        partCbox.addEventListener(Events.ON_CHANGE, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {

                Part part = (Part)partCbox.getSelectedItem().getValue();
                Comboitem processItem = new Comboitem();
                processItem.setLabel("N/A");
                List<Comboitem> processItems =  processCbox.getItems();
                processItems.clear();
                if(part != null)
                {
                    ProcessCore processCore = new ProcessCore();                   
                    ZKCatalogs.setProcesses(processCbox, processCore.getList(part.getNumProcesses()));
                    
                }
                processItems.add(0, processItem);
                processCbox.setSelectedIndex(0);
            }
        });

        addBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                Calendar  nowCal = Calendar.getInstance();
                nowCal.set(Calendar.HOUR_OF_DAY, 0);
                nowCal.set(Calendar.MINUTE, 0);
                nowCal.set(Calendar.SECOND, 0);
                nowCal.set(Calendar.MILLISECOND, 0);
                lineEditDatebox.setValue(nowCal.getTime());
                lineEditDatebox.setDisabled(false);

                partEditCbox.getChildren().clear();
                PartCore partCore = new PartCore();
                Comboitem partItem = new Comboitem();
                partItem.setLabel("");
                ZKCatalogs.setParts(partEditCbox, partCore.getAllList());
                List<Comboitem> partItems =  partEditCbox.getItems();
                partItems.add(0, partItem);
                partEditCbox.setSelectedIndex(0);

                machineEditCbox.getChildren().clear();                
                MachineCore machineCore = new MachineCore();
                Comboitem item = new Comboitem();
                item.setLabel("");
                ZKCatalogs.setMachines(machineEditCbox, machineCore.getAllList());                
                List<Comboitem> items =  machineEditCbox.getItems();
                items.add(0, item);
                machineEditCbox.setSelectedIndex(0);

                processEditCbox.getChildren().clear();
                ProcessCore processCore = new ProcessCore();
                Comboitem processItem = new Comboitem();
                processItem.setLabel("");
                ZKCatalogs.setProcesses(processEditCbox, processCore.getAllList());
                List<Comboitem> processItems =  processEditCbox.getItems();
                processItems.add(0, processItem);
                processEditCbox.setSelectedIndex(0);

                periodEditCbox.getChildren().clear();
                Comboitem period0Item = new Comboitem();
                period0Item.setLabel("");
                Comboitem period1Item = new Comboitem();
                period1Item.setLabel("Day");
                period1Item.setValue(new Integer(1));
                Comboitem period2Item = new Comboitem();
                period2Item.setLabel("Night");
                period2Item.setValue(new Integer(2));
                List<Comboitem> periodItems =  periodEditCbox.getItems();
                periodItems.add(0, period0Item);
                periodItems.add(1, period1Item);
                periodItems.add(2, period2Item);
                periodEditCbox.setSelectedIndex(0);
                
                aTxt.setValue(720L);
                bTxt.setValue(40L);
                cTxt.setValue(40L);
                dTxt.setValue(15L);
                eTxt.setValue(0L);
                f1Txt.setValue(0L);
                f2Txt.setValue(0L);
                f3Txt.setValue(0L);
                hTxt.setValue(0L);
                iTxt.setValue(0L);
                jTxt.setValue(0L);
                k1Txt.setValue(0L);
                k2Txt.setValue(0L);
                k3Txt.setValue(8L);
                mTxt.setValue(0L);                
                editWin.doModal();
            }
        });

        saveBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {

                MachineTime machineTime = new MachineTime();
                HttpUserSession userSession = new HttpUserSession();
                Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
                String id = idTxt.getValue();
                Machine machine = (Machine)(machineEditCbox.getSelectedItem() != null?machineEditCbox.getSelectedItem().getValue():null);
                Part part = (Part)(partEditCbox.getSelectedItem() != null?partEditCbox.getSelectedItem().getValue():null);
                Process process = (Process)(processEditCbox.getSelectedItem() != null?processEditCbox.getSelectedItem().getValue():null);
                Integer period = (Integer)(periodEditCbox.getSelectedItem() != null?periodEditCbox.getSelectedItem().getValue():null);
                Date scheduleDate =lineEditDatebox.getValue();
                Long aValue = aTxt.getValue();
                Long bValue = bTxt.getValue();
                Long cValue = cTxt.getValue();
                Long dValue = dTxt.getValue();
                Long eValue = eTxt.getValue();
                Long f1Value = f1Txt.getValue();
                Long f2Value = f2Txt.getValue();
                Long f3Value = f3Txt.getValue();
                Long hValue = hTxt.getValue();
                Long iValue = iTxt.getValue();
                Long jValue = jTxt.getValue();
                Long k1Value = k1Txt.getValue();
                Long k2Value = k2Txt.getValue();
                Long k3Value = k3Txt.getValue();
                Long mValue = mTxt.getValue();                


//
                if(scheduleDate == null)
                {
                    AlertMessages.alertMessage("machinetime.alert.message.required.1");
                    return ;
                }
                
                if(machine == null)
                {
                    AlertMessages.alertMessage("machinetime.alert.message.required.2");
                    return ;
                }

                if(part == null)
                {
                    AlertMessages.alertMessage("machinetime.alert.message.required.21");
                    return ;
                }
                
                if(process == null)
                {
                    AlertMessages.alertMessage("machinetime.alert.message.required.19");
                    return ;
                }

                if(period == null)
                {
                    AlertMessages.alertMessage("machinetime.alert.message.required.20");
                    return ;
                }

                if(aValue == null)
                {
                    AlertMessages.alertMessage("machinetime.alert.message.required.3");
                    return ;
                }
//
                if(bValue == null)
                {
                    AlertMessages.alertMessage("machinetime.alert.message.required.4");
                    return ;
                }

                if(cValue == null)
                {
                    AlertMessages.alertMessage("machinetime.alert.message.required.5");
                    return ;
                }

                if(dValue == null)
                {
                    AlertMessages.alertMessage("machinetime.alert.message.required.6");
                    return ;
                }

                if(eValue == null)
                {
                    AlertMessages.alertMessage("machinetime.alert.message.required.18");
                    return ;
                }

                if(f1Value == null)
                {
                    AlertMessages.alertMessage("machinetime.alert.message.required.7");
                    return ;
                }

                if(f2Value == null)
                {
                    AlertMessages.alertMessage("machinetime.alert.message.required.8");
                    return ;
                }

                if(f3Value == null)
                {
                    AlertMessages.alertMessage("machinetime.alert.message.required.9");
                    return ;
                }

                if(hValue == null)
                {
                    AlertMessages.alertMessage("machinetime.alert.message.required.10");
                    return ;
                }

                if(iValue == null)
                {
                    AlertMessages.alertMessage("machinetime.alert.message.required.11");
                    return ;
                }

                if(jValue == null)
                {
                    AlertMessages.alertMessage("machinetime.alert.message.required.12");
                    return ;
                }

                if(k1Value == null)
                {
                    AlertMessages.alertMessage("machinetime.alert.message.required.13");
                    return ;
                }

                if(k2Value == null)
                {
                    AlertMessages.alertMessage("machinetime.alert.message.required.14");
                    return ;
                }

                if(k3Value == null)
                {
                    AlertMessages.alertMessage("machinetime.alert.message.required.15");
                    return ;
                }

                if(mValue == null)
                {
                    AlertMessages.alertMessage("machinetime.alert.message.required.16");
                    return ;
                }          
                
//
                                            

                if(id != null && !id.isEmpty())
                {
                    machineTime.setId(new Long(id));
                }

                machineTime.setMachine(machine);
                machineTime.setProcess(process);
                machineTime.setApTime(aValue);
                machineTime.setBpTime(bValue);
                machineTime.setCpTime(cValue);
                machineTime.setDpTime(dValue);
                machineTime.setEpTime(eValue);
                machineTime.setFp1Time(f1Value);
                machineTime.setFp2Time(f2Value);
                machineTime.setFp3Time(f3Value);
                machineTime.setHpTime(hValue);
                machineTime.setIpTime(iValue);
                machineTime.setJpTime(jValue);
                machineTime.setKp1Time(k1Value);
                machineTime.setKp2Time(k2Value);
                machineTime.setKp3Time(k3Value);
                machineTime.setMpTime(mValue);
                machineTime.setPeriod(period);
                machineTime.setPart(part);
                                        


                machineTime.setScheduleTime(scheduleDate);
//
                MachineTimeCore core = new MachineTimeCore();
                try
                {
                    HashMap<String,Object> results = core.save(machineTime, userId);
                    Boolean result = (Boolean)results.get("results");
                    if(result != null && result)
                    {
                        editWin.setVisible(false);
                        AlertMessages.successMessage("machinetime.alert.message.1");
                        Events.sendEvent(Events.ON_CLICK, searchBtn, null);
                        event.stopPropagation();
                    }
                    else if(result != null && !result)
                    {
                        AlertMessages.failMessage("machinetime.alert.message.2");
                        return;
                    }
                }
                catch(Exception e)
                {
                    if(e.toString().indexOf("ConstraintViolationException") != -1)
                    {
                        AlertMessages.failMessage("machinetime.alert.message.3");
                    }
                    else
                    {
                        AlertMessages.failMessage("machinetime.default.alert.message.1");
                    }
                }

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
        MachineTimeCore core = new MachineTimeCore();

        if(lineDatebox.getValue() == null)
        {
            AlertMessages.alertMessage("Request Date is required.");
            showNoRecordFound();
            return ;
        }

//        if(machineCbox.getSelectedIndex() == 0)
//        {
//            AlertMessages.alertMessage("Machine is required.");
//            showNoRecordFound();
//            return ;
//        }

       

        HashMap<String,Object> results = core.getMachineTimes(lineDatebox.getValue()
                , (Machine)machineCbox.getSelectedItem().getValue()
                , (Part)partCbox.getSelectedItem().getValue()
                , (Process)processCbox.getSelectedItem().getValue()
                , (Integer)periodCbox.getSelectedItem().getValue());
        List<MachineTime> detail = (List<MachineTime>)results.get("results");
        if(detail ==null || detail.isEmpty())
        {
            showNoRecordFound();
        }
        else
        {
            machineTimeGrid.setModel(new ListModelList(detail));
            machineTimeGrid.setRowRenderer(new MachineTimeRenderer(
                editWin,idTxt,lineEditDatebox
                ,machineEditCbox,partEditCbox,processEditCbox,periodEditCbox,aTxt,bTxt,cTxt
                ,dTxt,eTxt,f1Txt,f2Txt,f3Txt,hTxt,iTxt,jTxt,k1Txt
                ,k2Txt,k3Txt,mTxt
                ,searchBtn));
        }
        
        


        
    }

    private void showNoRecordFound()
    {
        List<String> nodata = new ArrayList<String>();
        nodata.add(new String(ResourceUtil.getLabel("machinetime.message.norecordfound")));
        machineTimeGrid.setModel(new ListModelList(nodata));
        machineTimeGrid.setRowRenderer(new MachineTimeRenderer(
                editWin,idTxt,lineEditDatebox
                ,machineEditCbox,partEditCbox,processEditCbox,periodEditCbox,aTxt,bTxt,cTxt
                ,dTxt,eTxt,f1Txt,f2Txt,f3Txt,hTxt,iTxt,jTxt,k1Txt
                ,k2Txt,k3Txt,mTxt
                ,searchBtn));
    }



}
