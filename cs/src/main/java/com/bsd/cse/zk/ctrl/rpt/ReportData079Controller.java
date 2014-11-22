/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.rpt;

import com.bsd.cse.app.backoffice.CustomerCore;
import com.bsd.cse.app.backoffice.MachineCore;
import com.bsd.cse.app.backoffice.PartCore;
import com.bsd.cse.app.rpt.OEEAppProcessor;
import com.bsd.cse.app.security.TeamCore;
import com.bsd.cse.app.security.UserCore;
import com.bsd.cse.model.backoffice.customer.Customer;
import com.bsd.cse.model.backoffice.machine.Machine;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.report.OEEData;
import com.bsd.cse.model.report.OperationData;
import com.bsd.cse.model.report.PartProcessKey;
import com.bsd.cse.model.report.PartProcessDateKey;
import com.bsd.cse.model.security.Team;
import com.bsd.cse.model.security.User;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.grid.renderer.OEEFactorRenderer;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.web.Attributes;
import org.zkoss.zhtml.Form;
import org.zkoss.zhtml.Input;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Toolbarbutton;

/**
 *
 * @author bento
 */
public class ReportData079Controller extends SecurityController{
    private static final long serialVersionUID = -4492739773980927088L;
    private Log LOG = LogFactory.getLog(ReportData079Controller.class);
    private Date startDate;
    private Date endDate;
    private Long teamId;
    private Long machineId;
    private Long employeeId;
    private Grid oeeFactorGrid;
    private Include detailInclude ;
    private Label teamLbl;
    private Label machineLbl;
    private Label teamDataLbl;
    private Label machineDataLbl;
    private Label employeeDataLbl;
    private Toolbarbutton searchBtn;
    private Label headerLbl;
    private Label employeeLbl;
    private Columns columns;

    
    
    
     @Override
    public void doAfterCompose(Component component) throws Exception
    {
        super.doAfterCompose(component);
        createComponent(component);
        addEventListener();
        loadContent();

    }

    public void loadContent() throws Exception
    {
        HttpServletRequest request = (HttpServletRequest)Executions.getCurrent().getNativeRequest();
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String machineIdStr = request.getParameter("machineId");
        String teamIdStr = request.getParameter("teamId");
        String employeeIdStr = request.getParameter("employeeId");
        String headerValue = request.getParameter("headerName");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        headerLbl.setValue(headerValue);
        startDate = format.parse(startDateStr);
        endDate = format.parse(endDateStr);
        
        LOG.info("machineIdStr = "+machineIdStr);
        if(machineIdStr != null && !machineIdStr.trim().isEmpty())
        {
            machineId = Long.parseLong(machineIdStr);
            machineLbl.setVisible(true);
            machineDataLbl.setVisible(true);
            Machine machine = MachineCore.getMachine(machineId);
            machineDataLbl.setValue(machine.getName());
        }
        else
        {
            machineLbl.setVisible(false);
            machineDataLbl.setVisible(false);
        }

        LOG.info("teamIdStr = "+teamIdStr);
        if(teamIdStr != null && !teamIdStr.trim().isEmpty())
        {
            teamId = Long.parseLong(teamIdStr);
            teamLbl.setVisible(true);
            teamDataLbl.setVisible(true);
            Team team = TeamCore.getTeam(teamId);
            teamDataLbl.setValue(team.getTeamName());
        }
        else
        {
            teamLbl.setVisible(false);
            teamDataLbl.setVisible(false);
        }
        
        LOG.info("employeeIdStr = "+employeeIdStr);
        if(employeeIdStr != null && !employeeIdStr.trim().isEmpty())
        {
            employeeId = Long.parseLong(employeeIdStr);
            User employee =UserCore.getUser(employeeId);
            employeeDataLbl.setVisible(true);
            employeeDataLbl.setValue(employee.getFirstname()+" "+employee.getLastname());
            employeeLbl.setVisible(true);
        }
        else
        {
            employeeDataLbl.setVisible(false);
        }
        

        OEEAppProcessor processor = new OEEAppProcessor();
        List<PartProcessKey> keys = processor.groupingData(startDate, endDate, machineId, employeeId,teamId);
        List<Long> partIds  = new ArrayList<Long>();
        List<Long> processIds  = new ArrayList<Long>();
        for(PartProcessKey key:keys)
        {
            if(!partIds.contains(key.getPartId()))
            {
                partIds.add(key.getPartId());
            }

            if(!processIds.contains(key.getProcessId()))
            {
                processIds.add(key.getProcessId());
            }
        }

        

        Events.postEvent(Events.ON_CLICK, searchBtn, null);
     
    }
 
      

    public void addEventListener()
    {
        
//        employeeCbox.addEventListener(Events.ON_CHANGE,new EventListener() {
//
//            @Override
//            public void onEvent(Event event) throws Exception {
//                Events.postEvent(Events.ON_CLICK, searchBtn, null);
//            }
//        });
//
//        partCbox.addEventListener(Events.ON_CHANGE,new EventListener() {
//
//            @Override
//            public void onEvent(Event event) throws Exception {
//                Events.postEvent(Events.ON_CLICK, searchBtn, null);
//            }
//        });
//
//        machineCbox.addEventListener(Events.ON_CHANGE,new EventListener() {
//
//            @Override
//            public void onEvent(Event event) throws Exception {
//                Events.postEvent(Events.ON_CLICK, searchBtn, null);
//            }
//        });
        
        searchBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
//                Part part = (Part)partCbox.getSelectedItem().getValue();
//                Machine machine = (Machine)machineCbox.getSelectedItem().getValue();
//                User user = null;
//                if(employeeCbox.isVisible())
//                {
//                    user = (User)employeeCbox.getSelectedItem().getValue();
//                }
//                Long userId = user!= null?user.getId():null;

                OEEAppProcessor processor = new OEEAppProcessor();
                oeeFactorGrid.setRowRenderer(new OEEFactorRenderer(detailInclude,teamId,startDate,endDate,machineId,employeeId));
                
                ArrayList<Object> ops = new ArrayList<Object>();

//                if(part == null || machine == null)
//                {
//                    String data = "No Found Data";
//                    ops.add(data);
//                    oeeFactorGrid.setModel(new ListModelList(ops));
//                    detailInclude.setSrc(null);
//                    return;
//                }
                                
                OEEData oeeData = processor.processor(startDate, endDate, teamId, employeeId, machineId);

                if(oeeData != null && oeeData.getOeeFactors() != null && oeeData.getOeeFactors().size() > 0)
                {
                    for(int index = 0 ;index < oeeData.getPart().length;index++)
                    {
                        List allColumn = columns.getChildren();
                        Column column = new Column();
                        column.setWidth("80px");
                        Label label1 = new Label(oeeData.getPart()[index]);
                        label1.setStyle("float:left;clear:left");
                        Label label2 = new Label(oeeData.getProcess()[index]);
                        label2.setStyle("float:left;clear:left");
                        Div div = new Div();
                        div.appendChild(label1);
                        div.appendChild(label2);
                        div.setStyle("background:none;text-align:center");
                        column.getChildren().add(div);                        
                        allColumn.add(column);
                    }
                    oeeFactorGrid.setModel(new ListModelList(oeeData.getOeeFactors()));
                    detailInclude.setSrc(null);
                }
                else
                {
                    String data = "No Found Data";
                    ops.add(data);
                    oeeFactorGrid.setModel(new ListModelList(ops));
                    detailInclude.setSrc(null);
                }
            }
        });
                
    }

    public void createComponent(Component component)
    {
                       
    }


    public static void main(String[] args) throws ParseException {
        Locale locale = new Locale("th","TH");
        String month = "01";
        String year = "2554";
        String period = year+month;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM",locale == null?Locale.US:locale);
        SimpleDateFormat formatPeriodStr = new SimpleDateFormat("MMMM yyyy",locale == null?Locale.US:locale);
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
        Date periodSearch = format.parse(period);
        Calendar cal = Calendar.getInstance();
        cal.setTime(periodSearch);
        cal.set(Calendar.DATE,cal.getActualMinimum(Calendar.DATE));
        Date startDate = cal.getTime();
        cal.set(Calendar.DATE,cal.getActualMaximum(Calendar.DATE));
        cal.add(Calendar.DATE,1);
        Date endDate = cal.getTime();
        String startDateStr = formatDate.format(startDate);
        String endDateStr = formatDate.format(endDate);
        String periodStr = formatPeriodStr.format(periodSearch);

        System.out.println("periodStr = " + periodStr);
        System.out.println("startDateStr = " + startDateStr);
        System.out.println("endDateStr = " + endDateStr);
    }
}
