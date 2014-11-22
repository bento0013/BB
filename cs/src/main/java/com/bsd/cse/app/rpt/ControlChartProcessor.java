/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.rpt;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.part.Checkpoint;
import com.bsd.cse.model.input.ControlChartGroupingData;
import com.bsd.cse.model.input.MeasurementPart;
import com.bsd.cse.model.report.ControlChartData;
import com.bsd.cse.model.report.ControlData;
import com.bsd.cse.model.report.ControlSerieData;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;

/**
 *
 * @author bento
 */
public class ControlChartProcessor {
    private static BigDecimal[] A2 = new BigDecimal[]{new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(1.880),new BigDecimal(1.020),new BigDecimal(0.730),new BigDecimal(0.580),new BigDecimal(0.480),new BigDecimal(0.420),new BigDecimal(0.370)};
    private static BigDecimal[] D2 = new BigDecimal[]{new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(1.128),new BigDecimal(1.693),new BigDecimal(2.058),new BigDecimal(2.326),new BigDecimal(2.534),new BigDecimal(2.704),new BigDecimal(2.847)};
    private static BigDecimal[] D3 = new BigDecimal[]{new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(0.080),new BigDecimal(0.140)};
    private static BigDecimal[] D4 = new BigDecimal[]{new BigDecimal(0.0),new BigDecimal(0.0),new BigDecimal(3.270),new BigDecimal(2.570),new BigDecimal(2.280),new BigDecimal(2.110),new BigDecimal(2.000),new BigDecimal(1.920),new BigDecimal(1.860)};
    private static Log LOG = LogFactory.getLog(ControlChartProcessor.class);
    public ControlData processor(final Date startDate,final Date endDate,final Long partId,final Long processId,final Long position) throws Exception
    {
        final HashMap<String,Object> results = new HashMap<String,Object>();
        (new TransactionalProcessor()
        {
            @Override
            public void process(Session session, Transaction tx) throws Exception {                
                List<ControlChartGroupingData> groupDatas =  groupingData(session, startDate, endDate, partId, processId, position);
                HashMap<String,Object> datas = findMinSamplingAndCountSubGroup(groupDatas);
                Integer minSampling = (Integer)datas.get("minSampling");
                Integer numSubGroup = (Integer)datas.get("numSubGroup");
                LOG.info("minSampling = "+minSampling);
                LOG.info("groupDatas size = "+groupDatas.size());
                LOG.info("numSubGroup = "+numSubGroup);
                List<ControlChartGroupingData> groupDataSamplings = (List<ControlChartGroupingData>)datas.get("groupSamplings");
                LOG.info("groupDataSamplings size = "+groupDataSamplings.size());
                List<ControlChartGroupingData> selGroupDataSamplings = new ArrayList<ControlChartGroupingData>();
                samplingSubGroup(numSubGroup, groupDataSamplings, selGroupDataSamplings);
                LOG.info("selGroupDataSamplings size = "+selGroupDataSamplings.size());
                ControlData controlData = samplingData(session, selGroupDataSamplings,startDate, endDate, partId, processId, position,minSampling);
                LOG.info("controlData = "+controlData);
                results.put("result",controlData);                
            }
            
        }).process();
        return (ControlData)results.get("result");
    }

    public void samplingSubGroup(Integer numSubGroup,List<ControlChartGroupingData> groupDataSamplings,List<ControlChartGroupingData> selGroupDataSamplings)
    {        
        if(numSubGroup > 30)
        {
            for(int index = 0 ;index <30;index++)
            {
                Integer randomIndex = new Double(Math.random()*100%groupDataSamplings.size()).intValue();
                ControlChartGroupingData groupSelectData = groupDataSamplings.get(randomIndex);
                selGroupDataSamplings.add(groupSelectData);
                groupDataSamplings.remove(groupSelectData);
            }
        }
        else
        {
            selGroupDataSamplings.addAll(groupDataSamplings);
        }
    }


    public List<ControlChartGroupingData> groupingData(Session session,final Date startDate,final Date endDate,final Long partId,final Long processId,final Long position)
    {
//        SQLQuery query = session.createSQLQuery("select measurement_date measurementDate,machine_id machineId,period period,count(measurement_part_id) numberSampling from cs_measurement_part where measurement_date >= :startDate and measurement_date < :endDate and part_id = :partId and process_id = :processId and measurement_position = :position group by measurement_date,machine_id,period having count(measurement_part_id) > 2 order by count(measurement_part_id) desc");
        SQLQuery query = session.createSQLQuery("select measurement_date measurementDate,count(measurement_part_id) numberSampling " +
                                    " from cs_measurement_part cmp inner join cs_checkpoint cc on cmp.checkpoint_id = cc.checkpoint_id and cc.position_point = :position " +
                                    " where measurement_date >= :startDate and measurement_date < :endDate and cmp.part_id = :partId " +
                                    " and cmp.process_id = :processId  group by measurement_date" +
                                    " having count(measurement_part_id) > 1 order by count(measurement_part_id) desc");
        LOG.info("startDate = "+startDate);
        LOG.info("endDate = "+endDate);
        query.setDate("startDate", startDate);
        query.setDate("endDate", endDate);
        query.setLong("partId", partId);
        query.setLong("processId", processId);
        query.setLong("position", position);
        query.addScalar("measurementDate", new org.hibernate.type.TimestampType());
//        query.addScalar("machineId", new LongType());
//        query.addScalar("period", new IntegerType());
        query.addScalar("numberSampling", new IntegerType());

        query.setResultTransformer(Transformers.aliasToBean(ControlChartGroupingData.class));
        List<ControlChartGroupingData> groupDatas =  (List<ControlChartGroupingData>)query.list();
        if(groupDatas != null)
        {
            LOG.info("groupDatas size = "+groupDatas.size());
        }
        else
        {
            LOG.info("groupDatas size = 0");
        }
        return groupDatas;
    }

    public HashMap<String,Object> findMinSamplingAndCountSubGroup(List<ControlChartGroupingData> groupDatas)
    {
        HashMap<String,Object> results = new HashMap<String,Object>();
        Integer minSampling = null;
        Integer countSubGroup = 0;
        List<ControlChartGroupingData> groupDataSamplings = new ArrayList<ControlChartGroupingData>();
        for(ControlChartGroupingData groupData:groupDatas)
        {
            if(minSampling == null)
            {
                minSampling = groupData.getNumberSampling();
            }
            if(countSubGroup<30)
            {
                minSampling = groupData.getNumberSampling();
            }
            else
            {
                if(!minSampling.equals(groupData.getNumberSampling()))
                {
                    break;
                }
            }
            groupDataSamplings.add(groupData);
            countSubGroup++;
        }
        results.put("minSampling", minSampling);
        results.put("numSubGroup", countSubGroup);
        results.put("groupSamplings", groupDataSamplings);
        return results;
    }

    public ControlData samplingData(Session session,List<ControlChartGroupingData> selGroupDataSamplings,final Date startDate,final Date endDate,final Long partId,final Long processId,final Long position,final Integer minSampling)
    {
        ControlData reportData = null;
        if(selGroupDataSamplings != null && selGroupDataSamplings.size() >= 5)
        {
            reportData = new ControlData();
            Collections.sort(selGroupDataSamplings);

            Criteria criteria = session.createCriteria(MeasurementPart.class).createAlias("checkpoint", "checkpoint");
            criteria.add(Restrictions.eq("part.id",partId));
            criteria.add(Restrictions.eq("process.id",processId));
            criteria.add(Restrictions.eq("checkpoint.position",position));
            criteria.add(Restrictions.ge("measurementDate",startDate));            
            criteria.add(Restrictions.lt("measurementDate",endDate));
            Disjunction disjunction = Restrictions.disjunction();
            criteria.add(disjunction);
            for(ControlChartGroupingData groupData : selGroupDataSamplings)
            {
                Conjunction conjunction = Restrictions.conjunction();
                conjunction.add(Restrictions.eq("measurementDate", groupData.getMeasurementDate()));
//                conjunction.add(Restrictions.eq("machine.id", groupData.getMachineId()));
//                conjunction.add(Restrictions.eq("period", groupData.getPeriod()));
                disjunction.add(conjunction);
            }
            criteria.addOrder(Order.asc("measurementDate"));
//            criteria.addOrder(Order.asc("period"));
            criteria.addOrder(Order.asc("machine.id"));
            HashMap<ControlChartGroupingData,List<BigDecimal>> samplings = new HashMap<ControlChartGroupingData,List<BigDecimal>>();
            List<MeasurementPart> measurementParts = criteria.list();
            List<String> machineNames = new ArrayList<String>();
            List<String> qualitiers = new ArrayList<String>();

            for(MeasurementPart measurementPart : measurementParts)
            {
                ControlChartGroupingData controlChartGroupingData = new ControlChartGroupingData();
                if(!machineNames.contains(measurementPart.getMachine().getName()))
                {
                    machineNames.add(measurementPart.getMachine().getName());
                }
//                controlChartGroupingData.setMachineId(measurementPart.getMachine().getId());
                controlChartGroupingData.setMeasurementDate(measurementPart.getMeasurementDate());
//                controlChartGroupingData.setPeriod(measurementPart.getPeriod());
                int index = selGroupDataSamplings.indexOf(controlChartGroupingData);
                ControlChartGroupingData selGroupDataSampling = selGroupDataSamplings.get(index);
                try
                {
                    qualitiers.get(index);
                }
                catch(Exception e)
                {
                    String firstname = measurementPart.getCreatedBy().getFirstname() != null?measurementPart.getCreatedBy().getFirstname()+" ":"";
                    String lastname = measurementPart.getCreatedBy().getLastname() != null?measurementPart.getCreatedBy().getLastname():"";
                    qualitiers.add(firstname+lastname);
                }
                
                List<BigDecimal> values = samplings.get(selGroupDataSampling);
                if(values == null)
                {
                    values = new ArrayList<BigDecimal>();
                    values.add(measurementPart.getMeasurementValue());
                    samplings.put(selGroupDataSampling, values);
                }
                else
                {
                    values.add(measurementPart.getMeasurementValue());
                }
            }

            String periodTime[] = new String[30];
            Date periodDate[] = new Date[30];
            ControlSerieData summarySerie = new ControlSerieData();
            summarySerie.setName("Summary");
            ControlSerieData xBarSerie = new ControlSerieData();
            xBarSerie.setName("X Bar");
            ControlSerieData rSerie = new ControlSerieData();
            rSerie.setName("R");
            ControlSerieData inspectorSerie = new ControlSerieData();
            inspectorSerie.setName("Inspector");
            for(int index = 0; index < qualitiers.size() ; index++)
            {
                inspectorSerie.getValueStr()[index]=qualitiers.get(index);
            }
            ControlSerieData checkedSerie = new ControlSerieData();
            checkedSerie.setName("Checked");
            ControlSerieData judgementSerie = new ControlSerieData();
            judgementSerie.setName("Judgement");
            ControlSerieData sampleDataSerie1 = new ControlSerieData();
            sampleDataSerie1.setName(String.valueOf(1));
            ControlSerieData sampleDataSerie2 = new ControlSerieData();
            sampleDataSerie2.setName(String.valueOf(2));
            ControlSerieData sampleDataSerie3 = new ControlSerieData();
            sampleDataSerie3.setName(String.valueOf(3));
            ControlSerieData sampleDataSerie4 = new ControlSerieData();
            sampleDataSerie4.setName(String.valueOf(4));
            ControlSerieData sampleDataSerie5 = new ControlSerieData();
            sampleDataSerie5.setName(String.valueOf(5));
            for(int index = 0 ;index < selGroupDataSamplings.size(); index++)
            {
               ControlChartGroupingData selGroupDataSampling = selGroupDataSamplings.get(index);
               List<BigDecimal> values =  randomSampling(samplings.get(selGroupDataSampling),minSampling);
               if(values != null)
               {
//                   periodTime[index] = selGroupDataSampling.getPeriod().equals(1)?"Day":"Night";
                   periodDate[index] = selGroupDataSampling.getMeasurementDate();
                   sampleDataSerie1.getValue()[index] = (values.size()>0 && 0<minSampling)?values.get(0):null;
                   sampleDataSerie2.getValue()[index] = (values.size()>1 && 1<minSampling)?values.get(1):null;
                   sampleDataSerie3.getValue()[index] = (values.size()>2 && 2<minSampling)?values.get(2):null;
                   sampleDataSerie4.getValue()[index] = (values.size()>3 && 3<minSampling)?values.get(3):null;
                   sampleDataSerie5.getValue()[index] = (values.size()>4 && 4<minSampling)?values.get(4):null;
                   summarySerie.getValue()[index] = sampleDataSerie1.getValue()[index]
                                                       .add(sampleDataSerie2.getValue()[index] != null?sampleDataSerie2.getValue()[index]:new BigDecimal(0))
                                                       .add(sampleDataSerie3.getValue()[index] != null?sampleDataSerie3.getValue()[index]:new BigDecimal(0))
                                                       .add(sampleDataSerie4.getValue()[index] != null?sampleDataSerie4.getValue()[index]:new BigDecimal(0))
                                                       .add(sampleDataSerie5.getValue()[index] != null?sampleDataSerie5.getValue()[index]:new BigDecimal(0));
                   xBarSerie.getValue()[index] = summarySerie.getValue()[index].divide(new BigDecimal(minSampling),10, RoundingMode.HALF_UP);
                   BigDecimal minValue = sampleDataSerie1.getValue()[index];
                   BigDecimal maxValue = sampleDataSerie1.getValue()[index];

                   if(sampleDataSerie2.getValue()[index] != null)
                   {
                       minValue = sampleDataSerie2.getValue()[index].compareTo(minValue) < 0?sampleDataSerie2.getValue()[index]:minValue;
                       maxValue = sampleDataSerie2.getValue()[index].compareTo(maxValue) > 0?sampleDataSerie2.getValue()[index]:maxValue;
                   }

                   if(sampleDataSerie3.getValue()[index] != null)
                   {
                       minValue = sampleDataSerie3.getValue()[index].compareTo(minValue) < 0?sampleDataSerie3.getValue()[index]:minValue;
                       maxValue = sampleDataSerie3.getValue()[index].compareTo(maxValue) > 0?sampleDataSerie3.getValue()[index]:maxValue;
                   }

                   if(sampleDataSerie4.getValue()[index] != null)
                   {
                       minValue = sampleDataSerie4.getValue()[index].compareTo(minValue) < 0?sampleDataSerie4.getValue()[index]:minValue;
                       maxValue = sampleDataSerie4.getValue()[index].compareTo(maxValue) > 0?sampleDataSerie4.getValue()[index]:maxValue;
                   }

                   if(sampleDataSerie5.getValue()[index] != null)
                   {
                       minValue = sampleDataSerie5.getValue()[index].compareTo(minValue) < 0?sampleDataSerie5.getValue()[index]:minValue;
                       maxValue = sampleDataSerie5.getValue()[index].compareTo(maxValue) > 0?sampleDataSerie5.getValue()[index]:maxValue;
                   }

                   rSerie.getValue()[index] = maxValue.subtract(minValue);                   
               }
            }



            List<ControlSerieData> samplingDatas = new ArrayList<ControlSerieData>();
            samplingDatas.add(sampleDataSerie1);
            samplingDatas.add(sampleDataSerie2);
            samplingDatas.add(sampleDataSerie3);
            samplingDatas.add(sampleDataSerie4);
            samplingDatas.add(sampleDataSerie5);
            List<ControlSerieData> summaryDatas1 = new ArrayList<ControlSerieData>();
            summaryDatas1.add(summarySerie);
            summaryDatas1.add(xBarSerie);
            summaryDatas1.add(rSerie);
            List<ControlSerieData> summaryDatas2 = new ArrayList<ControlSerieData>();
            summaryDatas2.add(inspectorSerie);
            summaryDatas2.add(checkedSerie);
            summaryDatas2.add(judgementSerie);
            
            reportData.setSamplingDatas(samplingDatas);
            reportData.setSummaryDatas1(summaryDatas1);
            reportData.setSummaryDatas2(summaryDatas2);
            reportData.setPeriodDate(periodDate);
            reportData.setPeriodTime(periodTime);

            BigDecimal rBar = new BigDecimal(0);
            BigDecimal rBarCount = new BigDecimal(0);
            for(int index = 0 ; index<rSerie.getValue().length;index++)
            {
               
                if(rSerie.getValue()[index] != null)
                {
                    rBar = rBar.add(rSerie.getValue()[index]);
                    rBarCount = rBarCount.add(new BigDecimal(1));
                }
            }

            Criteria checkpointCriteria = session.createCriteria(Checkpoint.class);
            checkpointCriteria.add(Restrictions.eq("part.id", partId));
            checkpointCriteria.add(Restrictions.eq("process.id", processId));
            checkpointCriteria.add(Restrictions.eq("position", position));
            checkpointCriteria.setMaxResults(1);
            Checkpoint checkpoint = (Checkpoint)checkpointCriteria.uniqueResult();

            rBar = rBar.divide(rBarCount,10, RoundingMode.HALF_UP);

            List<ControlChartData> rChartControlChartDatas = new ArrayList<ControlChartData>();
            for(int index = 0 ; index<rSerie.getValue().length;index++)
            {
                ControlChartData controlChartData = new ControlChartData();
                controlChartData.setName(String.valueOf(index+1));
                controlChartData.setValue(rSerie.getValue()[index]);
                controlChartData.setValue1(rBar);
                controlChartData.setValue2(D4[minSampling].multiply(rBar));
                controlChartData.setValue3(D3[minSampling].multiply(rBar));
                rChartControlChartDatas.add(controlChartData);                
            }


            BigDecimal xDBar = new BigDecimal(0);
            BigDecimal xDBarCount = new BigDecimal(0);
            List<ControlChartData> xChartControlChartDatas = new ArrayList<ControlChartData>();
            for(int index = 0 ; index<xBarSerie.getValue().length;index++)
            {                
                if(xBarSerie.getValue()[index] != null)
                {
                    xDBar = xDBar.add(xBarSerie.getValue()[index]);
                    xDBarCount = xDBarCount.add(new BigDecimal(1));
                }
            }
            xDBar = xDBar.divide(xDBarCount,10, RoundingMode.HALF_UP);
            for(int index = 0 ; index<xBarSerie.getValue().length;index++)
            {
                ControlChartData controlChartData = new ControlChartData();
                controlChartData.setName(String.valueOf(index+1));
                controlChartData.setValue(xBarSerie.getValue()[index]);
                controlChartData.setValue1(xDBar);
                controlChartData.setValue2(xDBar.add(rBar.multiply(A2[minSampling])));
                controlChartData.setValue3(xDBar.subtract(rBar.multiply(A2[minSampling])));
                controlChartData.setValue4(checkpoint.getMaxDuration());
                controlChartData.setValue5(checkpoint.getMinDuration());
                xChartControlChartDatas.add(controlChartData);                
            }
            
            

            if(checkpoint != null)
            {
                reportData.setCustomerName(checkpoint.getPart().getCustomer().getName());
                StringBuilder machineNameBuf = new StringBuilder(1024);
                for(String machineName:machineNames)
                {
                    machineNameBuf.append(machineName);
                    machineNameBuf.append(",");
                }
                reportData.setMachineNo(machineNameBuf.substring(0,machineNameBuf.length()-1));
                reportData.setMeasurementCode(null);
                reportData.setMeasurementName(null);
                reportData.setModelName(null);
                reportData.setPartName(checkpoint.getPart().getPartName());
                reportData.setPartNo(checkpoint.getPart().getPartNo());
                reportData.setProcessName(checkpoint.getProcess().getProcessName());
                reportData.setSpecificControl("("+checkpoint.getMinDuration().setScale(3)+"-"+checkpoint.getMaxDuration().setScale(3)+") "+checkpoint.getCheckpointUnit().getName());
                reportData.setChart1Datas(xChartControlChartDatas);
                reportData.setChart2Datas(rChartControlChartDatas);
            }

            reportData.setRbar(rBar);
            reportData.setRcl(rBar);
            reportData.setRlcl(D3[minSampling].multiply(rBar));
            reportData.setRucl(D4[minSampling].multiply(rBar));
            
            reportData.setXbar(xDBar);
            reportData.setXucl(xDBar.add(rBar.multiply(A2[minSampling])));
            reportData.setXcl(xDBar);
            reportData.setXlcl(xDBar.subtract(rBar.multiply(A2[minSampling])));
            reportData.setUsl(checkpoint.getMaxDuration());
            reportData.setLsl(checkpoint.getMinDuration());
            reportData.setHolm(rBar.divide(D2[minSampling],10,RoundingMode.HALF_UP));
            reportData.setCp(reportData.getUsl().subtract(reportData.getLsl())
                    .divide(reportData.getHolm().multiply(new BigDecimal(6)),10,RoundingMode.HALF_UP));
            reportData.setCpu(reportData.getUsl().subtract(reportData.getXbar())
                    .divide(reportData.getHolm().multiply(new BigDecimal(3)),10,RoundingMode.HALF_UP));
            reportData.setCpl(reportData.getXbar().subtract(reportData.getLsl())
                    .divide(reportData.getHolm().multiply(new BigDecimal(3)),10,RoundingMode.HALF_UP));
            reportData.setCpk((reportData.getCpu().compareTo(reportData.getCpl()) > 0)
                    ? reportData.getCpl():reportData.getCpu());
            


            return reportData;
        }
        else
        {
            return null;
        }
    }

    public List<BigDecimal> randomSampling(List<BigDecimal> sampling,int minSampling)
    {
        List<BigDecimal> values = new ArrayList<BigDecimal>();
        for(int index = 0;index < minSampling;index++)
        {
            int random = new Double(Math.random()*100%sampling.size()).intValue();
            BigDecimal data = sampling.get(random);
            sampling.remove(data);
            values.add(data);
        }
        return values;
    }


    
}
