/*
 * $Id: Dr01Action.java,v 1.1 2011/01/31 12:52:37 thanasit Exp $
 *
 * Copyright (C) 2007 Advanced Research Group Co., Ltd. (ARG).
 *
 * All Rights Researved.
 */


package com.bsd.cse.struts2.action.rpt;

import com.bsd.cse.app.admrpt.AdminReportCore;
import com.bsd.cse.app.rpt.ControlChartProcessor;
import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.input.ControlChartGroupingData;
import com.bsd.cse.model.input.MeasurementPart;
import com.bsd.cse.model.report.ControlData;
import com.bsd.cse.model.report.ControlSerieData;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.hibernate.type.LongType;

/**
 * DOCUMENT ME!
 *
 * @author <a href="mailto:chalermpongc@ar.co.th">Chalermpong Chaiyawan</a>
 * @version $Revision: 1.1 $
 */
public class Report03Action
    extends DatasourceAdminReportAction
{

    private String partId;
    private String customerId;
    private String processId;
    private String position;
    
    protected static Log log = LogFactory.getLog(Report03Action.class);
    private static final long serialVersionUID = -2634117399142812092L;

    public Report03Action()
    {
        filename = prop.getProperty("admin.rpt.rpt03");
    }

    @Override
    public String report()
    {
        log.info("---------------> report <---------------");
//        createQuery(AdminReportCore.getProp().getProperty("admin.rpt.sql.rpt03"));
//        addParams("sqlMain",  createQuery(AdminReportCore.getProp().getProperty("admin.rpt.sql.rpt03")));
//        addParams("partId","'"+getPartId()+"'");
//        addParams("customerId","'"+getCustomerId()+"'");
        subReportMap.put("chart1Report",prop.getProperty("admin.rpt.rpt03.subreport01"));
        subReportMap.put("chart2Report",prop.getProperty("admin.rpt.rpt03.subreport02"));
        subReportMap.put("samplingReport",prop.getProperty("admin.rpt.rpt03.subreport03"));
        subReportMap.put("summaryReport1",prop.getProperty("admin.rpt.rpt03.subreport04"));
        subReportMap.put("summaryReport2",prop.getProperty("admin.rpt.rpt03.subreport06"));
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date startTime = format.parse(getStartDate());
            Date endTime = format.parse(getEndDate());
            ControlChartProcessor processor = new ControlChartProcessor();
            ControlData controlData = processor.processor(startTime, endTime, new Long(partId), new Long(processId), new Long(position));
            if(controlData != null)
            {
                datasources.add(controlData);
            }
        } catch (Exception ex) {
            Logger.getLogger(Report03Action.class.getName()).log(Level.SEVERE, null, ex);
        }
//        addParams("headerCSV", AdminReportCore.getProp().getProperty("admin.rpt.header.dr01"));
        return super.report();
    }
    
    private String createQuery(String sqlQuery)
    {
        StringBuffer buf = new StringBuffer(1024);
        buf.append(sqlQuery);
        if(getPartId() != null && !getPartId().isEmpty())
        {
            buf.append(" where data.part_id = $P!{partId}");
        }

        if(getCustomerId() != null && !getCustomerId().isEmpty())
        {
            buf.append((getPartId() != null && !getPartId().isEmpty())?" and ":" where ");
            buf.append(" data.customer_id = $P!{customerId}");
        }
        
        return buf.toString();
    }

    public ControlData samplingData() throws Exception
    {
        final HashMap<String,ControlData> results = new HashMap<String,ControlData>();
        (new TransactionalProcessor(LOG)
        {
            @Override
            public void process(Session session, Transaction tx) throws Exception {
                SQLQuery query = session.createSQLQuery("select measurement_date measurementDate,machine_id machineId,count(measurement_part_id) numberSampling from cs_measurement_part where measurement_date >= :startDate and measurement_date < :endDate and part_id = :partId and process_id = :processId and measurement_position = :position group by measurement_date,machine_id,period having count(measurement_part_id) > 2 order by count(measurement_part_id) desc");
                query.setString("startDate", "'"+getStartDate()+"'");
                query.setString("endDate",  "'"+getEndDate()+"'");
                query.setString("partId",  "'"+getPartId()+"'");
                query.setString("processId",  "'"+getProcessId()+"'");
                query.setString("position",  "'"+getPosition()+"'");
                query.addScalar("measurementDate", new org.hibernate.type.TimestampType());
                query.addScalar("machineId", new LongType());
//                query.addScalar("period", new IntegerType());
                query.addScalar("numberSampling", new IntegerType());
                LOG.info("getStartDate = "+getStartDate());
                LOG.info("getEndDate = "+getEndDate());
                LOG.info("getPartId = "+getPartId());
                LOG.info("getProcessId = "+getProcessId());
                LOG.info("getPosition = "+getPosition());

                query.setResultTransformer(Transformers.aliasToBean(ControlChartGroupingData.class));
                List<ControlChartGroupingData> groupDatas =  (List<ControlChartGroupingData>)query.list();
                Integer minSampling = null;
                Integer countSubGroup = 0;
                List<ControlChartGroupingData> groupDataSamplings = new ArrayList<ControlChartGroupingData>();
                for(ControlChartGroupingData groupData:groupDatas)
                {
                    if(minSampling == null)
                    {
                        minSampling = groupData.getNumberSampling();
                    }
                    if(countSubGroup<15)
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

                List<ControlChartGroupingData> selGroupDataSamplings = new ArrayList<ControlChartGroupingData>();
                if(countSubGroup > 15)
                {
                    for(int index = 0 ;index <15;index++)
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

                LOG.info("selGroupDataSamplings.size() = "+selGroupDataSamplings.size());

                if(selGroupDataSamplings != null && selGroupDataSamplings.size() >= 5)
                {

                    Collections.sort(selGroupDataSamplings);

                    Criteria criteria = session.createCriteria(MeasurementPart.class);
                    criteria.add(Restrictions.eq("part.id",18L));
                    criteria.add(Restrictions.eq("process.id",1L));
                    criteria.add(Restrictions.eq("position.id",1L));
                    Calendar startCal = Calendar.getInstance();
                    startCal.set(Calendar.YEAR, 2011);
                    startCal.set(Calendar.MONTH, 10);
                    startCal.set(Calendar.DATE, 26);
                    startCal.set(Calendar.HOUR_OF_DAY, 0);
                    startCal.set(Calendar.MINUTE, 0);
                    startCal.set(Calendar.SECOND, 0);
                    startCal.set(Calendar.MILLISECOND, 0);
                    LOG.info("startDate = "+startCal.getTime());
                    criteria.add(Restrictions.ge("measurementDate",startCal.getTime()));
                    Calendar endCal = Calendar.getInstance();
                    endCal.set(Calendar.YEAR, 2011);
                    endCal.set(Calendar.MONTH, 11);
                    endCal.set(Calendar.DATE, 26);
                    endCal.set(Calendar.HOUR_OF_DAY, 0);
                    endCal.set(Calendar.MINUTE, 0);
                    endCal.set(Calendar.SECOND, 0);
                    endCal.set(Calendar.MILLISECOND, 0);
                    criteria.add(Restrictions.lt("measurementDate",endCal.getTime()));
                    Disjunction disjunction = Restrictions.disjunction();
                    criteria.add(disjunction);
                    for(ControlChartGroupingData groupData : selGroupDataSamplings)
                    {
                        Conjunction conjunction = Restrictions.conjunction();
                        conjunction.add(Restrictions.eq("measurementDate", groupData.getMeasurementDate()));
                        conjunction.add(Restrictions.eq("machine.id", groupData.getMachineId()));
//                        conjunction.add(Restrictions.eq("period", groupData.getPeriod()));
                        disjunction.add(conjunction);
                    }
                    criteria.addOrder(Order.asc("measurementDate"));
//                    criteria.addOrder(Order.asc("period"));
                    criteria.addOrder(Order.asc("machine.id"));
                    HashMap<ControlChartGroupingData,List<BigDecimal>> samplings = new HashMap<ControlChartGroupingData,List<BigDecimal>>();
                    List<MeasurementPart> measurementParts = criteria.list();
                    LOG.info("measurementParts = "+measurementParts.size());
                    for(MeasurementPart measurementPart : measurementParts)
                    {
                        ControlChartGroupingData controlChartGroupingData = new ControlChartGroupingData();
                        controlChartGroupingData.setMachineId(measurementPart.getMachine().getId());
                        controlChartGroupingData.setMeasurementDate(measurementPart.getMeasurementDate());
//                        controlChartGroupingData.setPeriod(measurementPart.getPeriod());
                        LOG.info("contain = "+selGroupDataSamplings.contains(controlChartGroupingData));
                        int index = selGroupDataSamplings.indexOf(controlChartGroupingData);
                        ControlChartGroupingData selGroupDataSampling = selGroupDataSamplings.get(index);
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

                    String periodTime[] = new String[15];
                    Date periodDate[] = new Date[15];
                    ControlSerieData summarySerie = new ControlSerieData();
                    summarySerie.setName("Summary");
                    ControlSerieData xBarSerie = new ControlSerieData();
                    xBarSerie.setName("X Bar");
                    ControlSerieData rSerie = new ControlSerieData();
                    rSerie.setName("R");
                    ControlSerieData inspectorSerie = new ControlSerieData();
                    inspectorSerie.setName("Inspector");
                    ControlSerieData checkedSerie = new ControlSerieData();
                    checkedSerie.setName("Checked");
                    ControlSerieData judgementSerie = new ControlSerieData();
                    judgementSerie.setName("Judgement");
                    ControlSerieData sampleDataSerie1 = new ControlSerieData();
                    ControlSerieData sampleDataSerie2 = new ControlSerieData();
                    ControlSerieData sampleDataSerie3 = new ControlSerieData();
                    ControlSerieData sampleDataSerie4 = new ControlSerieData();
                    ControlSerieData sampleDataSerie5 = new ControlSerieData();
                    for(int index = 0 ;index < selGroupDataSamplings.size(); index++)
                    {
                       ControlChartGroupingData selGroupDataSampling = selGroupDataSamplings.get(index);
                       List<BigDecimal> values =  samplings.get(selGroupDataSampling);
                       if(values != null)
                       {
//                           periodTime[index] = String.valueOf(selGroupDataSampling.getPeriod());
                           periodDate[index] = selGroupDataSampling.getMeasurementDate();
                           sampleDataSerie1.getValue()[index] = values.size()>0?values.get(0):null;
                           sampleDataSerie2.getValue()[index] = values.size()>1?values.get(1):null;
                           sampleDataSerie3.getValue()[index] = values.size()>2?values.get(2):null;
                           sampleDataSerie4.getValue()[index] = values.size()>3?values.get(3):null;
                           sampleDataSerie5.getValue()[index] = values.size()>4?values.get(4):null;
                           summarySerie.getValue()[index] = sampleDataSerie1.getValue()[index]
                                                               .add(sampleDataSerie2.getValue()[index] != null?sampleDataSerie2.getValue()[index]:new BigDecimal(0))
                                                               .add(sampleDataSerie3.getValue()[index] != null?sampleDataSerie3.getValue()[index]:new BigDecimal(0))
                                                               .add(sampleDataSerie4.getValue()[index] != null?sampleDataSerie4.getValue()[index]:new BigDecimal(0))
                                                               .add(sampleDataSerie5.getValue()[index] != null?sampleDataSerie5.getValue()[index]:new BigDecimal(0));
                           xBarSerie.getValue()[index] = summarySerie.getValue()[index].divideToIntegralValue(new BigDecimal(5));
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
                           LOG.info("rSerie.getValue()["+index+"] = "+rSerie.getValue()[index]);
                       }
                    }



                    List<ControlSerieData> samplingDatas = new ArrayList<ControlSerieData>();
                    samplingDatas.add(sampleDataSerie1);
                    samplingDatas.add(sampleDataSerie2);
                    samplingDatas.add(sampleDataSerie3);
                    samplingDatas.add(sampleDataSerie4);
                    samplingDatas.add(sampleDataSerie5);
                    List<ControlSerieData> summaryDatas = new ArrayList<ControlSerieData>();
                    summaryDatas.add(summarySerie);
                    summaryDatas.add(xBarSerie);
                    summaryDatas.add(rSerie);
                    ControlData reportData = new ControlData();
                    reportData.setSamplingDatas(samplingDatas);
                    //reportData.setSummaryDatas(summaryDatas);
                    reportData.setPeriodDate(periodDate);
                    reportData.setPeriodTime(periodTime);
                    reportData.setChart1Datas(null);
                    reportData.setChart2Datas(null);
                    reportData.setCustomerName("Test");
                    reportData.setLsl(new BigDecimal("123"));
                    reportData.setMachineNo("MachineNo");
                    reportData.setMeasurementCode("MeasurementCode");
                    reportData.setMeasurementName("MeasurementName");
                    reportData.setModelName("ModelName");
                    reportData.setPartName("PartName");
                    reportData.setPartNo("PartNo");
                    reportData.setProcessName("ProcessName");
                    reportData.setRbar(new BigDecimal("345"));
                    reportData.setRcl(new BigDecimal("10"));
                    reportData.setRlcl(new BigDecimal("11"));
                    reportData.setRucl(new BigDecimal("14"));
                    reportData.setSpecificControl("SpeciicControl");
                    reportData.setXbar(new BigDecimal("20"));
                    reportData.setXucl(new BigDecimal("20"));
                    reportData.setXcl(new BigDecimal("20"));
                    reportData.setXlcl(new BigDecimal("20"));
                    reportData.setUsl(new BigDecimal("44"));
                    reportData.setLsl(new BigDecimal("44"));


                    results.put("result", reportData);
                }



            }

        }).process();

        return results.get("result");

    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    

    

    


  

   

    

    
    
 
}
