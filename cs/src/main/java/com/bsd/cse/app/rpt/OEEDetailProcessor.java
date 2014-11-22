/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.rpt;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.machine.Machine;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.backoffice.part.Process;
import com.bsd.cse.model.input.MachineTime;
import com.bsd.cse.model.input.ProductLine;
import com.bsd.cse.model.input.Time;
import com.bsd.cse.model.report.GroupingMachineTime;
import com.bsd.cse.model.report.OEEData;
import com.bsd.cse.model.report.OEEDetailData;
import com.bsd.cse.model.report.OperationData;
import com.bsd.cse.model.report.OperationDetailData;
import com.bsd.cse.model.report.TimePartData;
import com.bsd.cse.model.report.TimePartDetailData;
import com.bsd.cse.model.security.User;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author bento
 */
public class OEEDetailProcessor {    
    private static Log LOG = LogFactory.getLog(OEEDetailProcessor.class);    
    public OEEDetailData processor(final Date startDate,final Date endDate,final Long partId,final Long employeeId,final Long processId,final Long machineId) throws Exception
    {
        final HashMap<String,Object> results = new HashMap<String,Object>();
        (new TransactionalProcessor()
        {
            @Override
            public void process(Session session, Transaction tx) throws Exception {                
                List<ProductLine> productLines = null;                               

                productLines = getProductLine(session, startDate,endDate, partId,employeeId,processId,machineId);


                OEEDetailData oeeData = caculateData(session,  productLines,machineId, processId ,startDate,endDate);


                results.put("result",oeeData);
            }
            
        }).process();
        return (OEEDetailData)results.get("result");
    }

    public Part getPart(Session session,Long partId)
    {
        Part part = (Part)session.get(Part.class,partId);
        return part;
    }

    public User getEmployee(Session session,Long employeeId)
    {
        User user = (User)session.get(User.class,employeeId);
        return user;
    }

//    public List<MachineTime> getMachineTime(Session session,Long machineId,Date scheduleTime,Long processId)
//    {
//        Criteria criteria = session.createCriteria(MachineTime.class);
//        criteria.add(Restrictions.eq("machine.id", machineId));
//        criteria.add(Restrictions.eq("scheduleTime", scheduleTime));
//        criteria.add(Restrictions.eq("process.id", processId));
//        List<MachineTime> machineTimes = criteria.list();
//        return machineTimes;
//    }

    public List<ProductLine> getProductLine(Session session,Date startDate,Date endDate,Long partId,Long employeeId,Long processId,Long machineId)
    {

        Criteria criteria = session.createCriteria(ProductLine.class);
        if(partId != null)
        {
            criteria.add(Restrictions.eq("part.id", partId));
        }
        
        criteria.add(Restrictions.ge("requestDate", startDate));
        criteria.add(Restrictions.lt("requestDate", endDate));
        if(employeeId != null)
        {
            criteria.add(Restrictions.eq("user.id", employeeId));
        }
        
        if(processId != null)
        {
            criteria.add(Restrictions.eq("process.id", processId));
        }

        if(machineId != null)
        {
            criteria.add(Restrictions.eq("machine.id", machineId));
        }
        
        criteria.addOrder(Order.asc("time.id"));
        criteria.addOrder(Order.asc("requestDate"));
        List<ProductLine> productLines = criteria.list();
        LOG.info("ProductLines size = "+productLines.size());
        return productLines;
    }    

    public OEEDetailData caculateData(Session session,List<ProductLine> productLines,Long machineId,Long processId,Date startDate,Date endDate)
    {
        OEEDetailData oeeDetailData = new OEEDetailData();
        List<HashMap<Date,List<Integer>>> requestPeriodDates = new ArrayList<HashMap<Date,List<Integer>>>();
        List<Date> requestDates = new ArrayList<Date>();
        List<String> columnNames = new ArrayList<String>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        HashMap<Date,List<ProductLine>> productLineDates = getProductLines(productLines);
        for(ProductLine productLine : productLines)
        {
            if(!requestDates.contains(productLine.getRequestDate()))
            {
                requestDates.add(productLine.getRequestDate());
//                HashMap<Date,List<Integer>> resultDate = new HashMap<Date,List<Integer>>();
//                List<Integer> periods = new ArrayList<Integer>();
//                periods.add(productLine.getTime().getRound());
//                resultDate.put(productLine.getRequestDate(), periods);
//                requestPeriodDates.add(resultDate);
                columnNames.add(format.format(productLine.getRequestDate()));
//                List<ProductLine> producLineLists = new ArrayList<ProductLine>();
//                producLineLists.add(productLine);
//                dateProductLines.put(productLine.getRequestDate(), producLineLists);
            }
//            else
//            {
//                int index = requestDates.indexOf(productLine.getRequestDate());
//                HashMap<Date,List<Integer>> resultDate = requestPeriodDates.get(index);
//                List<Integer> periods = resultDate.get(productLine.getRequestDate());
//                if(!periods.contains(productLine.getTime().getRound()))
//                {
//                    periods.add(productLine.getTime().getRound());
//                }
//                List<ProductLine> producLineLists = dateProductLines.get(productLine.getRequestDate());
//                producLineLists.add(productLine);
//            }

        }

        oeeDetailData.setColumnNames(columnNames);

        OperationData operationTime[] = new OperationData[]{new OperationData("A","Total Available Minutes (In day)\nเวลาที่มี (นาที) ต่อวัน"),new OperationData("B","Short Breaks, Minutes\nพักเบรค (นาที)")
                                                                ,new OperationData("C","Meal Break, Minutes<br>พักทานอาหาร (นาที)"),new OperationData("D","5S Time, Minutes\nเวลาในการทำ 3ส (นาที)")
                                                                ,new OperationData("E","Tool Change Time, Minutes\nเวลาสูญเสียเนื่องจากการเปลี่ยนมีด"),new OperationData("F","Job Change Time, Minutes\nเวลาสูญเสียเนื่องจากเปลี่ยนงาน")
                                                                ,new OperationData("F1","Not Planned\nเวลาสูญเสียเนื่องจากไม่มีแผนผลิต"),new OperationData("F2","Inspection\nเวลาสูญเสียเนื่องจากการตรวจสอบ")
                                                                ,new OperationData("G","Total Planned Down time\nเวสาสูญเสียที่รวมอยู่ในแผน"),new OperationData("H","Input RM Not Available (min)\nเวลาสูญเสียเนื่องจากไม่มี วัตถุดิบ")
                                                                ,new OperationData("I","Operator Not Available\nเวลาสูญเสียเนื่องจากไม่มีพนักงาน"),new OperationData("J","Unplanned Maintenance\nเวลาสูญเสียเนื่องจากซ่อมบำรุง")
                                                                ,new OperationData("K","Machine setting\nเวลาสูญเสียเนื่องจากการตั้งงาน"),new OperationData("K1","No power\nเวลาสูญเสียเนื่องจากไม่มีไฟฟ้า")
                                                                ,new OperationData("K2","Chip cleaning\nเวลาสูญเสียเนื่องจากทิ้งเศษเหล็ก"),new OperationData("L","Total Unplanned Down time\nรวมเวลาสูญเสียทั้งหมด")
                                                                ,new OperationData("M","Cycle time, Seconds\nเวลาในการผลิต/ชิ้น (วินาที)"),new OperationData("O","Ideal Q'ty Parts expected\nจำนวนชิ้นงานที่คาดหวัง")};
        for(OperationData each:operationTime)
        {
            each.setOp(new BigDecimal[columnNames.size()]);
        }
        OperationData supportValue[] = new OperationData[]{new OperationData("R","Planned Production, Minutes\nเวลาที่มีจริงในการวางแผน (นาที)")
                                                        ,new OperationData("S","Operation Time, Minutes\nเวลาที่มีจริงในการผลิต (นาที)")
                                                        ,new OperationData("T","Good Pieces\nชิ้นงานดีที่ผลิตได้")};
        for(OperationData each:supportValue)
        {
            each.setOp(new BigDecimal[columnNames.size()]);
        }
        OperationData oeeFactor[] = new OperationData[]{new OperationData("U","Availability")
                                                        ,new OperationData("V","Performance")
                                                        ,new OperationData("W","Quality")
                                                        ,new OperationData("X","Overall OEE")};
        for(OperationData each:oeeFactor)
        {
            each.setOp(new BigDecimal[columnNames.size()]);
        }
        OperationData totalPart[] = new OperationData[]{new OperationData("P","Total Pieces Produced-actual")};
        for(OperationData each:totalPart)
        {
            each.setOp(new BigDecimal[columnNames.size()]);
        }

        TimePartData[] timePartDatas = getTimeParts(session,columnNames.size());
        TimePartData timePartDataTotal = new TimePartData();
        timePartDataTotal.setName("Q");
        timePartDataTotal.setDescription("Total Qty in a data");
        timePartDataTotal.setNg(new BigDecimal[columnNames.size()]);
        timePartDataTotal.setOk(new BigDecimal[columnNames.size()]);
        List<GroupingMachineTime> grouping = groupingMachineTime(productLines);

        HashMap<Date,List<MachineTime>> machineTimeDates = getMachineTime(session, grouping,startDate,endDate);
        List<Date> checkProcess = new ArrayList<Date>();
        List<Integer> checkProcessPeriod = new ArrayList<Integer>();

        Date[] dates = (Date[])productLineDates.keySet().toArray(new Date[0]);

        for(Date date : dates)
        {                                  

            Date checkOperation = date;
            LOG.info("checkOperation = "+checkOperation);
            if(!checkProcess.contains(checkOperation))
            {
                checkProcess.add(checkOperation);
            }

            Integer operIndex = checkProcess.indexOf(checkOperation);
            LOG.info("operIndex = "+operIndex);
            List<MachineTime> machineTimes = null;
            if(machineTimeDates != null)
            {
                machineTimes = machineTimeDates.get(date);
            }

            if(machineTimes != null)
            {

                for(MachineTime machineTime : machineTimes)
                {
                    if(checkProcessPeriod.size() > operIndex)
                    {
                        Integer value = checkProcessPeriod.get(operIndex);
                        checkProcessPeriod.remove(operIndex);
                        checkProcessPeriod.add(operIndex, new Integer(value+1));
                    }
                    else
                    {

                        checkProcessPeriod.add(operIndex, new Integer(1));
                    }

                    
                    BigDecimal aValue  = (operationTime[0].getOp()[operIndex] == null
                            ?new BigDecimal(0):operationTime[0].getOp()[operIndex])
                            .add(new BigDecimal(String.valueOf(machineTime.getApTime())));
                    operationTime[0].getOp()[operIndex] = aValue;

                    BigDecimal bValue  = (operationTime[1].getOp()[operIndex] == null
                            ?new BigDecimal(0):operationTime[1].getOp()[operIndex])
                            .add(new BigDecimal(String.valueOf(machineTime.getBpTime())));
                    operationTime[1].getOp()[operIndex] = bValue;

                    BigDecimal cValue  = (operationTime[2].getOp()[operIndex] == null
                            ?new BigDecimal(0):operationTime[2].getOp()[operIndex])
                            .add(new BigDecimal(String.valueOf(machineTime.getCpTime())));
                    operationTime[2].getOp()[operIndex] = cValue;

                    BigDecimal dValue  = (operationTime[3].getOp()[operIndex] == null
                            ?new BigDecimal(0):operationTime[3].getOp()[operIndex])
                            .add(new BigDecimal(String.valueOf(machineTime.getDpTime())));
                    operationTime[3].getOp()[operIndex] = dValue;

                    BigDecimal eValue  = (operationTime[4].getOp()[operIndex] == null
                            ?new BigDecimal(0):operationTime[4].getOp()[operIndex])
                            .add(new BigDecimal(String.valueOf(machineTime.getEpTime())));

                    operationTime[4].getOp()[operIndex] = eValue;

                    BigDecimal f1Value  = (operationTime[5].getOp()[operIndex] == null
                            ?new BigDecimal(0):operationTime[5].getOp()[operIndex])
                            .add(new BigDecimal(String.valueOf(machineTime.getFp1Time())));
                    operationTime[5].getOp()[operIndex] = f1Value;

                    BigDecimal f2Value  = (operationTime[6].getOp()[operIndex] == null
                            ?new BigDecimal(0):operationTime[6].getOp()[operIndex])
                            .add(new BigDecimal(String.valueOf(machineTime.getFp2Time())));
                    operationTime[6].getOp()[operIndex] = f2Value;

                    BigDecimal f3Value  = (operationTime[7].getOp()[operIndex] == null
                            ?new BigDecimal(0):operationTime[7].getOp()[operIndex])
                            .add(new BigDecimal(String.valueOf(machineTime.getFp3Time())));
                    operationTime[7].getOp()[operIndex] = f3Value;

                    BigDecimal gValue = bValue.add(cValue).add(dValue).add(eValue).add(f1Value).add(f2Value).add(f3Value);//G
                    operationTime[8].getOp()[operIndex] = gValue;

                    BigDecimal hValue  = (operationTime[9].getOp()[operIndex] == null
                            ?new BigDecimal(0):operationTime[9].getOp()[operIndex])
                            .add(new BigDecimal(String.valueOf(machineTime.getHpTime())));
                    operationTime[9].getOp()[operIndex] = hValue;

                    BigDecimal iValue  = (operationTime[10].getOp()[operIndex] == null
                            ?new BigDecimal(0):operationTime[10].getOp()[operIndex])
                            .add(new BigDecimal(String.valueOf(machineTime.getIpTime())));
                    operationTime[10].getOp()[operIndex] = iValue;

                    BigDecimal jValue  = (operationTime[11].getOp()[operIndex] == null
                            ?new BigDecimal(0):operationTime[11].getOp()[operIndex])
                            .add(new BigDecimal(String.valueOf(machineTime.getJpTime())));
                    operationTime[11].getOp()[operIndex] = jValue;

                    BigDecimal k1Value  = (operationTime[12].getOp()[operIndex] == null
                            ?new BigDecimal(0):operationTime[12].getOp()[operIndex])
                            .add(new BigDecimal(String.valueOf(machineTime.getKp1Time())));
                    operationTime[12].getOp()[operIndex] = k1Value;

                    BigDecimal k2Value  = (operationTime[13].getOp()[operIndex] == null
                            ?new BigDecimal(0):operationTime[13].getOp()[operIndex])
                            .add(new BigDecimal(String.valueOf(machineTime.getKp2Time())));
                    operationTime[13].getOp()[operIndex] = k2Value;

                    BigDecimal k3Value  = (operationTime[14].getOp()[operIndex] == null
                            ?new BigDecimal(0):operationTime[14].getOp()[operIndex])
                            .add(new BigDecimal(String.valueOf(machineTime.getKp3Time())));
                    operationTime[14].getOp()[operIndex] = k3Value;

                    BigDecimal lValue = hValue.add(iValue).add(jValue).add(k1Value).add(k2Value).add(k3Value);
                    operationTime[15].getOp()[operIndex] = lValue;

                    BigDecimal mValue = new BigDecimal(String.valueOf(machineTime.getMpTime()));
                    operationTime[16].getOp()[operIndex] = mValue;

                    if(BigDecimal.ZERO.equals(mValue))
                    {
                        operationTime[17].getOp()[operIndex] = null;
                    }
                    else
                    {
                        mValue = mValue.divide(new BigDecimal(checkProcessPeriod.get(operIndex)),0,RoundingMode.HALF_UP);
                        operationTime[17].getOp()[operIndex] = aValue.subtract(gValue).subtract(lValue).multiply(new BigDecimal(60)).divide(mValue,3,RoundingMode.HALF_UP);
                    }

                    supportValue[0].getOp()[operIndex] = aValue.subtract(gValue);
                    supportValue[1].getOp()[operIndex] = aValue.subtract(gValue).subtract(lValue);
                    oeeFactor[0].getOp()[operIndex] = aValue.subtract(gValue).subtract(lValue).divide(aValue.subtract(gValue),3,RoundingMode.HALF_UP);
                }
            }








            List<ProductLine> productLineDatas = productLineDates.get(date);
            for(ProductLine productLine:productLineDatas)
            {
                timePartDatas[productLine.getTime().getId().intValue()-1].getOk()[operIndex] = new BigDecimal(String.valueOf(productLine.getOk())) ;
                timePartDatas[productLine.getTime().getId().intValue()-1].getNg()[operIndex] = new BigDecimal(String.valueOf(productLine.getNg())) ;
                totalPart[0].getOp()[operIndex] =
                        (totalPart[0].getOp()[operIndex] == null
                        ?new BigDecimal(0):totalPart[0].getOp()[operIndex])
                        .add(timePartDatas[productLine.getTime().getId().intValue()-1].getOk()[operIndex]);
                supportValue[2].getOp()[operIndex] =
                        (supportValue[2].getOp()[operIndex] == null
                        ?new BigDecimal(0):supportValue[2].getOp()[operIndex])
                        .add(timePartDatas[productLine.getTime().getId().intValue()-1].getOk()[operIndex])
                        .subtract(timePartDatas[productLine.getTime().getId().intValue()-1].getNg()[operIndex]);
                timePartDataTotal.getOk()[operIndex] =
                        (timePartDataTotal.getOk()[operIndex] == null
                        ?new BigDecimal(0):timePartDataTotal.getOk()[operIndex])
                        .add(timePartDatas[productLine.getTime().getId().intValue()-1].getOk()[operIndex]) ;
                timePartDataTotal.getNg()[operIndex] =
                        (timePartDataTotal.getNg()[operIndex] == null
                        ?new BigDecimal(0):timePartDataTotal.getNg()[operIndex])
                        .add(timePartDatas[productLine.getTime().getId().intValue()-1].getNg()[operIndex]) ;
            }


            if(operationTime[17].getOp()[operIndex] != null && !(new BigDecimal(0)).equals(operationTime[17].getOp()[operIndex]))
            {
                oeeFactor[1].getOp()[operIndex] = totalPart[0].getOp()[operIndex].divide(operationTime[17].getOp()[operIndex],3,RoundingMode.HALF_UP);
            }
            else
            {
                oeeFactor[1].getOp()[operIndex] = null;
            }

            if(totalPart[0].getOp()[operIndex] != null && !(new BigDecimal(0)).equals(totalPart[0].getOp()[operIndex]))
            {
                oeeFactor[2].getOp()[operIndex] = supportValue[2].getOp()[operIndex].divide(totalPart[0].getOp()[operIndex],3,RoundingMode.HALF_UP);
            }
            else
            {
                oeeFactor[2].getOp()[operIndex] = null;
            }

            if( oeeFactor[2].getOp()[operIndex] == null || oeeFactor[1].getOp()[operIndex] == null || oeeFactor[0].getOp()[operIndex] == null)
            {
                oeeFactor[3].getOp()[operIndex] = null;
            }
            else
            {
                oeeFactor[3].getOp()[operIndex] = oeeFactor[0].getOp()[operIndex].multiply(oeeFactor[1].getOp()[operIndex]).multiply(oeeFactor[2].getOp()[operIndex]);
            }



              
        }

        List<TimePartData> timePartDataArray = Arrays.asList(timePartDatas);
        ArrayList<TimePartData> timePartDataList = new ArrayList<TimePartData>();
        timePartDataList.addAll(timePartDataArray);
        timePartDataList.add(timePartDataTotal);
        oeeDetailData.setTimeParts(timePartDataList);
        oeeDetailData.setOperations(Arrays.asList(operationTime));
        oeeDetailData.setSupportVariables(Arrays.asList(supportValue));
        oeeDetailData.setOeeFactors(Arrays.asList(oeeFactor));
        oeeDetailData.setTotalParts(Arrays.asList(totalPart));
        
        return oeeDetailData;
    }

    private List<GroupingMachineTime> groupingMachineTime(List<ProductLine> productLines)
    {
        List<GroupingMachineTime> grouping = new ArrayList<GroupingMachineTime>();
        for(ProductLine productLine : productLines)
        {
            GroupingMachineTime time = new GroupingMachineTime();
            time.setMachine(productLine.getMachine());
            time.setPart(productLine.getPart());
            time.setPeriod(productLine.getTime().getRound());
            time.setProcess(productLine.getProcess());
            if(!grouping.contains(time))
            {
                grouping.add(time);
            }

        }

        LOG.info("grouping = "+grouping.size());
        return grouping;
    }

    public HashMap<Date,List<MachineTime>> getMachineTime(Session session,List<GroupingMachineTime> grouping,Date startDate,Date endDate)
    {
        Criteria criteria = session.createCriteria(MachineTime.class);
        Disjunction disjunction = Restrictions.disjunction();
        for(GroupingMachineTime time : grouping)
        {
            Conjunction con = Restrictions.conjunction();
            con.add(Restrictions.eq("machine.id", time.getMachine().getId()));
            con.add(Restrictions.eq("process.id", time.getProcess().getId()));
            con.add(Restrictions.eq("period", time.getPeriod()));
            con.add(Restrictions.eq("part.id", time.getPart().getId()));
            disjunction.add(con);
        }
        criteria.add(disjunction);
        criteria.add(Restrictions.ge("scheduleTime", startDate));
        criteria.add(Restrictions.lt("scheduleTime", endDate));
        criteria.addOrder(Order.asc("part.id"));
        criteria.addOrder(Order.asc("process.id"));
        List<MachineTime> machineTimes = criteria.list();
        HashMap<Date,List<MachineTime>> machineTimeDates = new HashMap<Date,List<MachineTime>>();
        if(machineTimes != null && machineTimes.size() > 0)
        {
            for(MachineTime machineTime : machineTimes)
            {
                List<MachineTime> machineTimeDatas = machineTimeDates.get(machineTime.getScheduleTime());
                if(machineTimeDatas == null)
                {
                    machineTimeDatas = new ArrayList<MachineTime>();
                    machineTimeDates.put(machineTime.getScheduleTime(), machineTimeDatas);
                }

                machineTimeDatas.add(machineTime);
            }
        }

        return machineTimeDates;
    }

    public HashMap<Date,List<ProductLine>> getProductLines(List<ProductLine> productLines)
    {        
        HashMap<Date,List<ProductLine>> productLineDates = new HashMap<Date,List<ProductLine>>();
        if(productLines != null && productLines.size() > 0)
        {
            for(ProductLine productLine : productLines)
            {
                

                List<ProductLine> productLineDatas = productLineDates.get(productLine.getRequestDate());
                if(productLineDatas == null)
                {
                    productLineDatas = new ArrayList<ProductLine>();
                    productLineDates.put(productLine.getRequestDate(), productLineDatas);
                }

                productLineDatas.add(productLine);
            }
        }

        return productLineDates;
    }

    public TimePartData[] getTimeParts(Session session,Integer columnSize)
    {
            Criteria criteria = session.createCriteria(Time.class);
            criteria.addOrder(Order.asc("id"));
//            criteria.addOrder(Order.asc("beginTime"));
            List<Time> times = criteria.list();
            List<TimePartData> timePartDatas = new ArrayList<TimePartData>();
            for(Time time : times)
            {
                TimePartData timePartData = new TimePartData();
                timePartData.setName("P");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                timePartData.setDescription(timeFormat.format(time.getBeginTime())+"-"+timeFormat.format(time.getEndTime()));
                timePartData.setNg(new BigDecimal[columnSize]);
                timePartData.setOk(new BigDecimal[columnSize]);
                timePartDatas.add(timePartData);
            }
            
           
            
            return timePartDatas.toArray(new TimePartData[0]);
    }    
    
}
