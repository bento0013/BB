/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app;

import com.bsd.cse.app.rpt.OEEAppProcessor;
import com.bsd.cse.app.security.TeamCore;
import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.backoffice.part.Process;
import com.bsd.cse.model.dsb.DashboardPartSetting;
import com.bsd.cse.model.dsb.MonitorPartData;
import com.bsd.cse.model.dsb.OEEDashboardData;
import com.bsd.cse.model.dsb.ProductionProcess;
import com.bsd.cse.model.input.ProductLine;
import com.bsd.cse.model.report.OEEData;
import com.bsd.cse.model.report.OperationData;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author bento
 */
public class DashboardCore {
    private static Log LOG = LogFactory.getLog(DashboardCore.class);
    public List<MonitorPartData> getMonitoringPartData() throws Exception
    {
        final HashMap<String,List<MonitorPartData>> results = new HashMap<String,List<MonitorPartData>>();
        (new TransactionalProcessor()
        {
            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(DashboardPartSetting.class);
                List<DashboardPartSetting> list = (List<DashboardPartSetting>)criteria.list();
                HashMap<Long,MonitorPartData> monitorPartDatas = new HashMap<Long,MonitorPartData>();

                List<Long> partIds = new ArrayList<Long>();
                for(DashboardPartSetting setting : list)
                {
                    partIds.add(setting.getPart().getId());
                }

                if(partIds.size() == 0)
                {
                    results.put("results", null);
                    return;
                }
                criteria = session.createCriteria(Part.class);
                criteria.add(Restrictions.in("id", partIds));
                List<Part> partDatas = (List<Part>)criteria.list();

                for(Part part : partDatas)
                {
                    MonitorPartData monitorPartData = monitorPartDatas.get(part.getId());
                    if(monitorPartData == null)
                    {
                        monitorPartData = new MonitorPartData();
                        monitorPartDatas.put(part.getId(),monitorPartData);
                    }
                    monitorPartData.setPart(part);
                    monitorPartData.setStore(new BigDecimal(part.getSemiMaterial().getAmount()));
                    monitorPartData.setLastStoreUpdateDate(part.getSemiMaterial().getUpdatedDate()!= null?part.getSemiMaterial().getUpdatedDate():part.getSemiMaterial().getCreatedDate());
                    monitorPartData.setStoreFinish(new BigDecimal(part.getAmount()));
                    monitorPartData.setLastStoreFinishUpdateDate(part.getUpdatedDate()!= null?part.getUpdatedDate():part.getCreatedDate());
                }

                criteria = session.createCriteria(ProductLine.class);
                criteria.add(Restrictions.in("part.id", partIds));

                Date currentDate = new Date();

                Calendar currentDateCal = Calendar.getInstance();
                currentDateCal.set(Calendar.HOUR_OF_DAY, 8);
                currentDateCal.set(Calendar.MINUTE, 0);
                currentDateCal.set(Calendar.SECOND, 0);
                currentDateCal.set(Calendar.MILLISECOND, 0);
                if(currentDate.compareTo(currentDateCal.getTime()) > 0)
                {
                    Calendar requestDateTime = Calendar.getInstance();
                    requestDateTime.set(Calendar.HOUR_OF_DAY,0);
                    requestDateTime.set(Calendar.MINUTE,0);
                    requestDateTime.set(Calendar.SECOND,0);
                    requestDateTime.set(Calendar.MILLISECOND,0);
                    criteria.add(Restrictions.eq("requestDate", requestDateTime.getTime()));
                }
                else
                {
                    Calendar requestDateTime = Calendar.getInstance();
                    requestDateTime.set(Calendar.HOUR_OF_DAY,0);
                    requestDateTime.set(Calendar.MINUTE,0);
                    requestDateTime.set(Calendar.SECOND,0);
                    requestDateTime.set(Calendar.MILLISECOND,0);
                    requestDateTime.add(Calendar.DATE, -1);
                    criteria.add(Restrictions.eq("requestDate", requestDateTime.getTime()));
                }
                criteria.addOrder(Order.asc("process.id"));
                criteria.addOrder(Order.asc("time.id"));

                HashMap<Long,HashMap<Long,HashMap<Long,Long>>> parts = new HashMap<Long,HashMap<Long,HashMap<Long,Long>>>();


                List<ProductLine> productLines = (List<ProductLine>)criteria.list();
                
                for(ProductLine productLine : productLines)
                {
                    MonitorPartData monitorPartData = monitorPartDatas.get(productLine.getPart().getId());
                    if(monitorPartData == null)
                    {
                        monitorPartData = new MonitorPartData();
                        monitorPartDatas.put(productLine.getPart().getId(),monitorPartData);
                    }
                    monitorPartData.setPart(productLine.getPart());                    
                    monitorPartData.setStore(new BigDecimal(productLine.getPart().getSemiMaterial().getAmount()));
                    monitorPartData.setLastStoreUpdateDate(productLine.getPart().getSemiMaterial().getUpdatedDate()!= null?productLine.getPart().getSemiMaterial().getUpdatedDate():productLine.getPart().getSemiMaterial().getCreatedDate());
                    monitorPartData.setStoreFinish(new BigDecimal(productLine.getPart().getAmount()));
                    monitorPartData.setLastStoreFinishUpdateDate(productLine.getPart().getUpdatedDate()!= null?productLine.getPart().getUpdatedDate():productLine.getPart().getCreatedDate());
                    
                    
                    List<ProductionProcess> productionProcesses = monitorPartData.getProductions();
                    ProductionProcess productionProcess = new ProductionProcess();
                    productionProcess.setProcess(productLine.getProcess());
                    int index = productionProcesses.indexOf(productionProcess);
                    if(index > -1)
                    {
                       productionProcess = productionProcesses.get(index);
                    }
                    else
                    {
                        productionProcesses.add(productionProcess);
                    }

                    productionProcess.setOk(productionProcess.getOk().add(new BigDecimal(productLine.getOk())));
                    productionProcess.setNg(productionProcess.getNg().add(new BigDecimal(productLine.getNg())));
                    if(productLine.getUpdatedDate() != null)
                    {
                        if(productionProcess.getLastUpdateDate() != null)
                        {
                            if(productionProcess.getLastUpdateDate().before(productLine.getUpdatedDate()))
                            {
                                productionProcess.setLastUpdateDate(productLine.getUpdatedDate());
                            }
                        }
                        else
                        {
                            productionProcess.setLastUpdateDate(productLine.getUpdatedDate());
                        }

                    }
                    else
                    {
                        if(productionProcess.getLastUpdateDate() != null)
                        {
                            if(productionProcess.getLastUpdateDate().before(productLine.getCreatedDate()))
                            {
                                productionProcess.setLastUpdateDate(productLine.getCreatedDate());
                            }
                        }
                        else
                        {
                            productionProcess.setLastUpdateDate(productLine.getCreatedDate());
                        }
                    }                    
                    productionProcess.setProcess(productLine.getProcess());
                    productionProcess.getProcess().getProcessName();

                    HashMap<Long,HashMap<Long,Long>> processes = parts.get(productLine.getPart().getId());
                    if(processes == null)
                    {
                        processes = new HashMap<Long,HashMap<Long,Long>>();
                        parts.put(productLine.getPart().getId(),processes);
                    }
                    HashMap<Long,Long> machineIds = processes.get(productLine.getProcess().getId());
                    if(machineIds == null)
                    {                        
                        machineIds = new HashMap<Long,Long>();
                        processes.put(productLine.getProcess().getId(),machineIds);
                    }

                    Long time = machineIds.get(productLine.getMachine().getId());                    

                    if(time == null)
                    {
                        machineIds.put(productLine.getMachine().getId(), productLine.getTime().getId());
                    }
                    else
                    {
                        if(time.compareTo(productLine.getTime().getId()) < 0)
                        {
                            machineIds.put(productLine.getMachine().getId(), productLine.getTime().getId());
                        }
                    }
                    

                }

                for(ProductLine productLine : productLines)
                {
                    MonitorPartData monitorPartData = monitorPartDatas.get(productLine.getPart().getId());
                    if(monitorPartData == null)
                    {
                        monitorPartData = new MonitorPartData();
                        monitorPartDatas.put(productLine.getPart().getId(),monitorPartData);
                    }
                   
                    List<ProductionProcess> productionProcesses = monitorPartData.getProductions();
                    ProductionProcess productionProcess = new ProductionProcess();
                    productionProcess.setProcess(productLine.getProcess());
                    int index = productionProcesses.indexOf(productionProcess);
                    if(index > -1)
                    {
                       productionProcess = productionProcesses.get(index);
                    }
                    else
                    {
                        productionProcesses.add(productionProcess);
                    }
                    HashMap<Long,HashMap<Long,Long>> processes = parts.get(productLine.getPart().getId());

                    HashMap<Long,Long> machineIds = processes.get(productLine.getProcess().getId());
                    
                    Long timeId = machineIds.get(productLine.getMachine().getId());
                    
                    if(timeId.equals(productLine.getTime().getId()))
                    {
                        LOG.info("productLine.getProcess().getId() = "+productLine.getProcess().getId()+ " = "+productLine.getWp());
                        productionProcess.setWip(productionProcess.getWip().add(new BigDecimal(productLine.getWp())));
                    }

                }




                results.put("result", Arrays.asList(monitorPartDatas.values().toArray(new MonitorPartData[0])) );
            }
        }).process();
        return results.get("result");
    }

    public List<OEEDashboardData> getOEEDashboardData() throws Exception
    {
        List<OEEDashboardData> oeeDatas = new ArrayList<OEEDashboardData>();

        OEEAppProcessor processor = new OEEAppProcessor();
        Calendar startDateTime = Calendar.getInstance();
        startDateTime.set(Calendar.DATE, 1);
        startDateTime.set(Calendar.HOUR_OF_DAY, 0);
        startDateTime.set(Calendar.MINUTE, 0);
        startDateTime.set(Calendar.SECOND, 0);
        startDateTime.set(Calendar.MILLISECOND, 0);

        Calendar endDateTime = Calendar.getInstance();
        endDateTime.set(Calendar.DATE, endDateTime.getActualMaximum(Calendar.DATE));
        endDateTime.set(Calendar.HOUR_OF_DAY, 0);
        endDateTime.set(Calendar.MINUTE, 0);
        endDateTime.set(Calendar.SECOND, 0);
        endDateTime.set(Calendar.MILLISECOND, 0);
       
        OEEData oeeDataAppA = processor.processor(startDateTime.getTime(), endDateTime.getTime(), 1L, null, null);
        OEEDashboardData oeeDataA = new OEEDashboardData();
        if(oeeDataAppA.getOeeFactors().size() > 0)
        {
            OperationData availabilityData =  oeeDataAppA.getOeeFactors().get(0);
            BigDecimal sumData = new BigDecimal(0);
            int countNum = 0;
            for(BigDecimal dataValue : availabilityData.getOp())
            {
                if(dataValue != null)
                {

                    countNum++;
                    sumData = sumData.add(dataValue);
                }
            }
            if(countNum > 0)
            {                
                sumData = sumData.multiply(new BigDecimal(100)).divide(new BigDecimal(countNum), 2, RoundingMode.HALF_UP);
            }
            oeeDataA.setAvailability(sumData);            
        }

        if(oeeDataAppA.getOeeFactors().size() > 1)
        {
            OperationData performanceData =  oeeDataAppA.getOeeFactors().get(1);
//            OperationData availabilityData =  oeeDataAppA.getOeeFactors().get(0);
            BigDecimal sumData = new BigDecimal(0);
            int countNum = 0;
            for(BigDecimal dataValue : performanceData.getOp())
            {
                if(dataValue != null)
                {
                    countNum++;
                    sumData = sumData.add(dataValue);
                }
            }
            if(countNum > 0)
            {                
                sumData = sumData.multiply(new BigDecimal(100)).divide(new BigDecimal(countNum), 2, RoundingMode.HALF_UP);
            }
            oeeDataA.setPerformance(sumData);
        }

        if(oeeDataAppA.getOeeFactors().size() > 2)
        {
            OperationData qualityData =  oeeDataAppA.getOeeFactors().get(2);
            BigDecimal sumData = new BigDecimal(0);
            int countNum = 0;
            for(BigDecimal dataValue : qualityData.getOp())
            {
                if(dataValue != null)
                {                   
                    countNum++;
                    sumData = sumData.add(dataValue);
                }
            }
            if(countNum > 0)
            {               
                sumData = sumData.multiply(new BigDecimal(100)).divide(new BigDecimal(countNum), 2, RoundingMode.HALF_UP);
            }
            oeeDataA.setQuality(sumData);
        }

         if(oeeDataAppA.getOeeFactors().size() > 3)
        {
            OperationData overallData =  oeeDataAppA.getOeeFactors().get(3);
            BigDecimal sumData = new BigDecimal(0);
            int countNum = 0;
            for(BigDecimal dataValue : overallData.getOp())
            {
                if(dataValue != null)
                {
                    countNum++;
                    sumData = sumData.add(dataValue);
                }
            }
            if(countNum > 0)
            {                
                sumData = sumData.multiply(new BigDecimal(100)).divide(new BigDecimal(countNum), 2, RoundingMode.HALF_UP);
            }
            oeeDataA.setOverall(sumData);
        }

        OEEData oeeDataAppB = processor.processor(startDateTime.getTime(), endDateTime.getTime(), 2L, null, null);
        OEEDashboardData oeeDataB = new OEEDashboardData();
        if(oeeDataAppB.getOeeFactors().size() > 0)
        {
            OperationData availabilityData =  oeeDataAppB.getOeeFactors().get(0);
            BigDecimal sumData = new BigDecimal(0);
            int countNum = 0;
            for(BigDecimal dataValue : availabilityData.getOp())
            {
                if(dataValue != null)
                {
                    countNum++;
                    sumData = sumData.add(dataValue);
                }
            }
            if(countNum > 0)
            {              
                sumData = sumData.multiply(new BigDecimal(100)).divide(new BigDecimal(countNum), 2, RoundingMode.HALF_UP);
            }
            oeeDataB.setAvailability(sumData);
        }

        if(oeeDataAppB.getOeeFactors().size() > 1)
        {
            OperationData performanceData =  oeeDataAppB.getOeeFactors().get(1);
//            OperationData availabilityData =  oeeDataAppA.getOeeFactors().get(0);
            BigDecimal sumData = new BigDecimal(0);
            int countNum = 0;
            for(BigDecimal dataValue : performanceData.getOp())
            {
                if(dataValue != null)
                {
                    countNum++;
                    sumData = sumData.add(dataValue);
                }
            }
            if(countNum > 0)
            {                
                sumData = sumData.multiply(new BigDecimal(100)).divide(new BigDecimal(countNum), 2, RoundingMode.HALF_UP);
            }
            oeeDataB.setPerformance(sumData);
        }

        if(oeeDataAppB.getOeeFactors().size() > 2)
        {
            OperationData qualityData =  oeeDataAppB.getOeeFactors().get(2);
            BigDecimal sumData = new BigDecimal(0);
            int countNum = 0;
            for(BigDecimal dataValue : qualityData.getOp())
            {
                if(dataValue != null)
                {
                    countNum++;
                    sumData = sumData.add(dataValue);
                }
            }
            if(countNum > 0)
            {                
                sumData = sumData.multiply(new BigDecimal(100)).divide(new BigDecimal(countNum), 2, RoundingMode.HALF_UP);
            }
            oeeDataB.setQuality(sumData);
        }

         if(oeeDataAppB.getOeeFactors().size() > 3)
        {
            OperationData overallData =  oeeDataAppB.getOeeFactors().get(3);
            BigDecimal sumData = new BigDecimal(0);
            int countNum = 0;
            for(BigDecimal dataValue : overallData.getOp())
            {
                if(dataValue != null)
                {
                    countNum++;
                    sumData = sumData.add(dataValue);
                }
            }
            if(countNum > 0)
            {                
                sumData = sumData.multiply(new BigDecimal(100)).divide(new BigDecimal(countNum), 2, RoundingMode.HALF_UP);
            }
            oeeDataB.setOverall(sumData);
        }

        OEEDashboardData oeeDataAll = new OEEDashboardData();        
        oeeDataAll.setAvailability(oeeDataA.getAvailability().add(oeeDataB.getAvailability()).divide(new BigDecimal(2),2,RoundingMode.HALF_UP));
        oeeDataAll.setPerformance(oeeDataA.getPerformance().add(oeeDataB.getPerformance()).divide(new BigDecimal(2),2,RoundingMode.HALF_UP));
        oeeDataAll.setQuality(oeeDataA.getQuality().add(oeeDataB.getQuality()).divide(new BigDecimal(2),2,RoundingMode.HALF_UP));
        oeeDataAll.setOverall(oeeDataA.getOverall().add(oeeDataB.getOverall()).divide(new BigDecimal(2),2,RoundingMode.HALF_UP));
       
        oeeDatas.add(oeeDataAll);
        oeeDatas.add(oeeDataA);
        oeeDatas.add(oeeDataB);
        return oeeDatas;
    }

    public HashMap<String,Object> saveSetting(final List<DashboardPartSetting> partSettings,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {

                Query query = session.createQuery("Delete from DashboardPartSetting");
                query.executeUpdate();               

                for(DashboardPartSetting partSetting : partSettings)
                {
                    session.save(partSetting);
                }

                session.flush();

                map.put("results",Boolean.TRUE);                
                LOG.info("results = "+(Boolean)map.get("results"));                
            }
        }.process();

        return map;
    }

    public List<DashboardPartSetting> getPartSetting() throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {

                Criteria criteria = session.createCriteria(DashboardPartSetting.class);
                List<DashboardPartSetting> partSettings = criteria.list();

                for(DashboardPartSetting partSetting : partSettings)
                {
                    partSetting.getPart().getPartName();
                }
                
                map.put("results",partSettings);                
            }
        }.process();

        return (List<DashboardPartSetting>)map.get("results");
    }
}
