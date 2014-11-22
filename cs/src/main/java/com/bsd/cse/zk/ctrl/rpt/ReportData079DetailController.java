/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.rpt;

import com.bsd.cse.app.rpt.OEEDetailProcessor;
import com.bsd.cse.model.report.OEEData;
import com.bsd.cse.model.report.OEEDetailData;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.grid.renderer.OEEDetailOperationDataRenderer;
import com.bsd.cse.zk.grid.renderer.OEEDetailTimePartDataRenderer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;

/**
 *
 * @author bento
 */
public class ReportData079DetailController extends SecurityController{
    private static final long serialVersionUID = -4492739773980927088L;
    private Log LOG = LogFactory.getLog(ReportData079DetailController.class);
    private Date startDate;
    private Date endDate;
    private Long partId;
    private Long machineId;
    private Long processId;
    private Long employeeId;
    private Grid operationDataGrid;
    private Grid totalDataGrid;
    private Grid producedGrid;
    private Grid supportGrid;
    private Grid oeeDetailGrid;
    
    private OEEDetailData data ;

    

    @Override
    public ComponentInfo doBeforeCompose(Page page,Component component,ComponentInfo comInfo){
        ComponentInfo info  = super.doBeforeCompose(page, component, comInfo);
        try
        {
            startDate = (Date)Executions.getCurrent().getArg().get("startDate");
            endDate = (Date)Executions.getCurrent().getArg().get("endDate");
            partId = (Long)Executions.getCurrent().getArg().get("partId");
            machineId = (Long)Executions.getCurrent().getArg().get("machineId");
            employeeId = (Long)Executions.getCurrent().getArg().get("employeeId");
            processId = (Long)Executions.getCurrent().getArg().get("processId");
         

            OEEDetailProcessor processor = new OEEDetailProcessor();
            data = processor.processor(startDate, endDate, partId, employeeId, processId, machineId);
            List<String> columnProductedNames = new ArrayList<String>();
            for(String columns: data.getColumnNames())
            {
                columnProductedNames.add("OK");
                columnProductedNames.add("NG");
            }
            page.setAttribute("columnNames",data.getColumnNames() );
            page.setAttribute("columnProductedNames",columnProductedNames );
        }catch(Exception e)
        {
            
        }
        return info;
    }
    
     @Override
    public void doAfterCompose(Component component) throws Exception
    {
        super.doAfterCompose(component);        
        loadContent();

    }

    public void loadContent() throws Exception
    {
//        startDate = (Date)Executions.getCurrent().getArg().get("startDate");
//        endDate = (Date)Executions.getCurrent().getArg().get("endDate");
//        partId = (Long)Executions.getCurrent().getArg().get("partId");
//        machineId = (Long)Executions.getCurrent().getArg().get("machineId");
//        processId = (Long)Executions.getCurrent().getArg().get("processId");
//
//        LOG.info("processId = "+processId);
//        LOG.info("startDate = "+startDate);
//        LOG.info("endDate = "+endDate);
//        LOG.info("machineId = "+machineId);
//        LOG.info("partId = "+partId);

        operationDataGrid.setRowRenderer(new OEEDetailOperationDataRenderer(null,partId,startDate,endDate,machineId));

        operationDataGrid.setModel(new ListModelList(data.getOperations()));

        totalDataGrid.setRowRenderer(new OEEDetailOperationDataRenderer(null,partId,startDate,endDate,machineId));

        totalDataGrid.setModel(new ListModelList(data.getTotalParts()));

        producedGrid.setRowRenderer(new OEEDetailTimePartDataRenderer(null, partId, startDate, endDate, machineId));

        producedGrid.setModel(new ListModelList(data.getTimeParts()));

        supportGrid.setRowRenderer(new OEEDetailOperationDataRenderer(null,partId,startDate,endDate,machineId));

        supportGrid.setModel(new ListModelList(data.getSupportVariables()));

        oeeDetailGrid.setRowRenderer(new OEEDetailOperationDataRenderer(null,partId,startDate,endDate,machineId));

        oeeDetailGrid.setModel(new ListModelList(data.getOeeFactors()));
    }

    
}
