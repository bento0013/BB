/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.rpt;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.backoffice.part.Process;
import com.bsd.cse.model.input.MachineTime;
import com.bsd.cse.model.input.ProductLine;
import com.bsd.cse.model.input.Time;
import com.bsd.cse.model.report.GroupingMachineTime;
import com.bsd.cse.model.report.OEEData;
import com.bsd.cse.model.report.OperationData;
import com.bsd.cse.model.report.PartProcessKey;
import com.bsd.cse.model.report.TimePartData;
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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author bento
 */
public class OEEAppProcessor {
    private static Log LOG = LogFactory.getLog(OEEAppProcessor.class);

    public List<PartProcessKey> groupingData(final Date startDate,final Date endDate,final Long machineId,final Long employeeId,final Long teamId) throws Exception
    {
        final HashMap<String,Object> results = new HashMap<String,Object>();
        (new TransactionalProcessor()
        {
            @Override
            public void process(Session session, Transaction tx) throws Exception {
                StringBuffer strBuff = new StringBuffer(1024);
                strBuff.append("select part.id as partId,process.id as processId from ProductLine ");
                strBuff.append("where ");
                if(machineId != null)
                {
                    strBuff.append(" machine.id = :machineId ");
                    strBuff.append(" and");
                }

                if(teamId != null)
                {
                    strBuff.append(" user.team.id = :teamId ");
                    strBuff.append(" and");
                }

                if(employeeId != null)
                {
                    strBuff.append(" user.id = :employeeId ");
                    strBuff.append(" and");
                }

                if(startDate != null)
                {
                    strBuff.append(" createdDate >= :startDate ");
                    strBuff.append(" and");
                }

                if(endDate != null)
                {
                    strBuff.append(" createdDate < :endDate ");                    
                }
                
                strBuff.append("group by part.id ,process.id ");
                Query query = session.createQuery(strBuff.toString());
                if(machineId != null)
                {
                    query.setLong("machineId",machineId);
                }

                if(teamId != null)
                {
                    query.setLong("teamId",teamId);
                }
                if(employeeId != null)
                {
                    query.setLong("employeeId",employeeId);
                }
                query.setDate("startDate", startDate);
                query.setDate("endDate", endDate);
                query.setResultTransformer(Transformers.aliasToBean(PartProcessKey.class));
                List<PartProcessKey> dataKeys =  query.list();
                results.put("result",dataKeys);
            }


        }).process();
        return (List<PartProcessKey>)results.get("result");
    }

    public Boolean checkMachineTime(final Date startDate,final Date endDate,final Long machineId) throws Exception
    {
        final HashMap<String,Object> results = new HashMap<String,Object>();
        (new TransactionalProcessor()
        {
            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(MachineTime.class);
                criteria.add(Restrictions.ge("scheduleTime",startDate));
                criteria.add(Restrictions.lt("scheduleTime",endDate));
                criteria.add(Restrictions.eq("machine.id",machineId ));
                criteria.setProjection(Projections.count("id"));
                Integer count =  ((Number)criteria.uniqueResult()).intValue();
                results.put("result",new Boolean(count>0));
            }

        }).process();
        
        return (Boolean)results.get("result");
    }

    public OEEData processor(final Date startDate,final Date endDate,final Long teamId,final Long employeeId,final Long machineId) throws Exception
    {
        final HashMap<String,Object> results = new HashMap<String,Object>();
        (new TransactionalProcessor()
        {
            @Override
            public void process(Session session, Transaction tx) throws Exception {
                List<ProductLine> productLines = null;
                User employee = null;
               

                productLines = getProductLine(session, startDate,endDate, teamId,employeeId,machineId);
                if(employeeId != null)
                {
                    employee = (User)session.get(User.class,employeeId);
                }
                OEEData oeeData = caculateData(session,  productLines,  startDate,endDate,employee);


                results.put("result",oeeData);
            }

        }).process();
        return (OEEData )results.get("result");
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

//    public HashMap<Date,List<MachineTime>> getMachineTime(Session session,Long machineId,List<Date> scheduleTimes)
//    {
//        HashMap<Date,List<MachineTime>> dateMachineTimes = new HashMap<Date,List<MachineTime>>();
//        Criteria criteria = session.createCriteria(MachineTime.class);
//        criteria.add(Restrictions.eq("machine.id", machineId));
//        criteria.add(Restrictions.in("scheduleTime", scheduleTimes));
//        criteria.addOrder(Order.asc("scheduleTime"));
//        criteria.addOrder(Order.asc("process.id"));
//        List<MachineTime> machineTimes = criteria.list();
//        for(MachineTime machineTime: machineTimes)
//        {
//            List<MachineTime> machineTimesForDate = dateMachineTimes.get(machineTime.getScheduleTime());
//            if(machineTimesForDate == null)
//            {
//                machineTimesForDate = new ArrayList<MachineTime>();
//                dateMachineTimes.put(machineTime.getScheduleTime(), machineTimesForDate);
//            }
//            machineTimesForDate.add(machineTime);
//
//        }
//        return dateMachineTimes;
//    }


    public HashMap<Date,HashMap<Long,List<Integer>>> getPeriods(List<ProductLine> productLines)
    {
        HashMap<Date,HashMap<Long,List<Integer>>> dateProcessPeriods = new HashMap<Date,HashMap<Long,List<Integer>>>();       
        for(ProductLine productLine: productLines)
        {
            List<Integer> periods = null;
            HashMap<Long,List<Integer>> processPeriods = dateProcessPeriods.get(productLine.getRequestDate());            
            if(processPeriods == null)
            {
                processPeriods = new HashMap<Long,List<Integer>>();
                periods = new ArrayList<Integer>();
                processPeriods.put(productLine.getProcess().getId(), periods);
                dateProcessPeriods.put(productLine.getRequestDate(), processPeriods);
            }
            else
            {
                periods = processPeriods.get(productLine.getProcess().getId());
                if(periods == null)
                {
                    periods = new ArrayList<Integer>();
                    processPeriods.put(productLine.getProcess().getId(), periods);
                }
            }
           

            if(!periods.contains(productLine.getTime().getRound()))
            {
                periods.add(productLine.getTime().getRound());
            }
            
        }
        return dateProcessPeriods;
    }

    public HashMap<Part,HashMap<Process,List<ProductLine>>> getPartProcessProductLines(List<ProductLine> productLines)
    {
        HashMap<Part,HashMap<Process,List<ProductLine>>> partProcessProductLines = new HashMap<Part,HashMap<Process,List<ProductLine>>>();
        for(ProductLine productLine: productLines)
        {
            HashMap<Process,List<ProductLine>> processProductLines = partProcessProductLines.get(productLine.getPart());
            if(processProductLines == null)
            {
                processProductLines = new HashMap<Process,List<ProductLine>>();
                partProcessProductLines.put(productLine.getPart(), processProductLines);
            }
            List<ProductLine> productLineMaps = processProductLines.get(productLine.getProcess());
            if(productLineMaps == null)
            {                
                productLineMaps = new ArrayList<ProductLine>();
                processProductLines.put(productLine.getProcess(), productLineMaps);
            }           
            productLineMaps.add(productLine);
        }
        return partProcessProductLines;
    }

    public Integer getColumnCount(List<ProductLine> productLines)
    {
        Integer column = 0 ;
        HashMap<Part,HashMap<Process,List<ProductLine>>> partProcessProductLines = new HashMap<Part,HashMap<Process,List<ProductLine>>>();
        for(ProductLine productLine: productLines)
        {
            HashMap<Process,List<ProductLine>> processProductLines = partProcessProductLines.get(productLine.getPart());
            if(processProductLines == null)
            {
                processProductLines = new HashMap<Process,List<ProductLine>>();
                partProcessProductLines.put(productLine.getPart(), processProductLines);
            }
            List<ProductLine> productLineMaps = processProductLines.get(productLine.getProcess());
            if(productLineMaps == null)
            {
                productLineMaps = new ArrayList<ProductLine>();
                processProductLines.put(productLine.getProcess(), productLineMaps);
                column++;
            }
            productLineMaps.add(productLine);
        }
        return column;
    }

    public List<ProductLine> getProductLine(Session session,Date startDate,Date endDate,Long teamId,Long employeeId,Long machineId)
    {

        Criteria criteria = session.createCriteria(ProductLine.class).createAlias("user", "user");
        if(teamId != null)
        {
            criteria.add(Restrictions.eq("user.team.id", teamId));
        }      
        criteria.add(Restrictions.ge("requestDate", startDate));
        criteria.add(Restrictions.lt("requestDate", endDate));
        if(employeeId != null)
        {
            criteria.add(Restrictions.eq("user.id", employeeId));
        }

        if(machineId != null)
        {
            criteria.add(Restrictions.eq("machine.id", machineId));
        } 
        criteria.addOrder(Order.asc("time.id"));
        criteria.addOrder(Order.asc("process.id"));
        List<ProductLine> productLines = criteria.list();
        return productLines;
    }
    

    public OEEData caculateData(Session session,List<ProductLine> productLineDatas,Date startDate,Date endDate,User employee)
    {            
            
            OEEData oeeData = new OEEData();            
            HashMap<Part,HashMap<Process,List<ProductLine>>> partProcessLines = getPartProcessProductLines(productLineDatas);

            Integer columnCount = getColumnCount(productLineDatas);            
//            HashMap<Date,HashMap<Long,List<Integer>>> dateProcessPeriods = getPeriods(productLineDatas);
//            Date[] dates =  dateLines.keySet().toArray(new Date[0]);
//            List<Long> processes = new ArrayList<Long>();
            
            oeeData.setPart(new String[columnCount]);
            oeeData.setPartId(new Long[columnCount]);
            oeeData.setProcess(new String[columnCount]);
            oeeData.setProcessId(new Long[columnCount]);
            TimePartData[] timePartDatas = getTimeParts(session,columnCount);

            OperationData operationTime[] = new OperationData[]{new OperationData("A","Total Available Minutes (In day)\nเวลาที่มี (นาที) ต่อวัน"),new OperationData("B","Short Breaks, Minutes\nพักเบรค (นาที)")
                                                                ,new OperationData("C","Meal Break, Minutes\nพักทานอาหาร (นาที)"),new OperationData("D","5S Time, Minutes\nเวลาในการทำ 3ส (นาที)")
                                                                ,new OperationData("E","Tool Change Time, Minutes\nเวลาสูญเสียเนื่องจากการเปลี่ยนมีด"),new OperationData("F","Job Change Time, Minutes\nเวลาสูญเสียเนื่องจากเปลี่ยนงาน")
                                                                ,new OperationData("F1","Not Planned\nเวลาสูญเสียเนื่องจากไม่มีแผนผลิต"),new OperationData("F2","Inspection\nเวลาสูญเสียเนื่องจากการตรวจสอบ")
                                                                ,new OperationData("G","Total Planned Down time\nเวสาสูญเสียที่รวมอยู่ในแผน"),new OperationData("H","Input RM Not Available (min)\nเวลาสูญเสียเนื่องจากไม่มี วัตถุดิบ")
                                                                ,new OperationData("I","Operator Not Available\nเวลาสูญเสียเนื่องจากไม่มีพนักงาน"),new OperationData("J","Unplanned Maintenance\nเวลาสูญเสียเนื่องจากซ่อมบำรุง")
                                                                ,new OperationData("K","Machine setting\nเวลาสูญเสียเนื่องจากการตั้งงาน"),new OperationData("K1","No power\nเวลาสูญเสียเนื่องจากไม่มีไฟฟ้า")
                                                                ,new OperationData("K2","Chip cleaning\nเวลาสูญเสียเนื่องจากทิ้งเศษเหล็ก"),new OperationData("L","Total Unplanned Down time\nรวมเวลาสูญเสียทั้งหมด")
                                                                ,new OperationData("M","Cycle time, Seconds\nเวลาในการผลิต/ชิ้น (วินาที)"),new OperationData("O","Ideal Q'ty Parts expected\nจำนวนชิ้นงานที่คาดหวัง")};
            for(OperationData each:operationTime)
            {
                each.setOp(new BigDecimal[columnCount]);
            }
            OperationData supportValue[] = new OperationData[]{new OperationData("R","Planned Production, Minutes\nเวลาที่มีจริงในการวางแผน (นาที)")
                                                            ,new OperationData("S","Operation Time, Minutes\nเวลาที่มีจริงในการผลิต (นาที)")
                                                            ,new OperationData("T","Good Pieces\nชิ้นงานดีที่ผลิตได้")};
            for(OperationData each:supportValue)
            {
                each.setOp(new BigDecimal[columnCount]);
            }
            OperationData oeeFactor[] = new OperationData[]{new OperationData("U","Availability")
                                                            ,new OperationData("V","Performance")
                                                            ,new OperationData("W","Quality")
                                                            ,new OperationData("X","Overall OEE")};
            for(OperationData each:oeeFactor)
            {
                each.setOp(new BigDecimal[columnCount]);
                each.setPartId(new Long[columnCount]);
                each.setProcessId(new Long[columnCount]);
            }
            OperationData totalPart[] = new OperationData[]{new OperationData("P","Total Pieces Produced-actual")};
            for(OperationData each:totalPart)
            {
                each.setOp(new BigDecimal[columnCount]);
            }

            TimePartData timePartDataTotal = new TimePartData();
            timePartDataTotal.setNg(new BigDecimal[columnCount]);
            timePartDataTotal.setOk(new BigDecimal[columnCount]);
            timePartDataTotal.setName("Q");
            timePartDataTotal.setDescription("Total  Qty in a day");
            

            List<GroupingMachineTime> grouping = groupingMachineTime(productLineDatas);

            HashMap<Part,HashMap<Process,List<MachineTime>>> machineTimeDates = getMachineTime(session, grouping,startDate,endDate);

            List<String> checkProcess = new ArrayList<String>();
            List<Integer> checkProcessPeriod = new ArrayList<Integer>();

            Part[] parts = (Part[])partProcessLines.keySet().toArray(new Part[0]);




            for(Part partData : parts)
            {
                HashMap<Process,List<ProductLine>> processProductLines = partProcessLines.get(partData);
                Process[] allProcesses = (Process[])processProductLines.keySet().toArray(new Process[0]);
                List<Process> allProcessList = (List<Process>)Arrays.asList(allProcesses);
                Collections.sort(allProcessList,new Comparator<Process>() {

                    @Override
                    public int compare(Process o1, Process o2) {
                        return o1.getId().compareTo(o2.getId());
                    }
                });
                for(Process process :(List<Process>)allProcessList)
                {

                    if(employee != null)
                    {
                        oeeData.setEmployeeId(employee.getUsername());
                        oeeData.setEmployeeName(employee.getFirstname()+" "+employee.getLastname());
                    }


                    String checkOperation = partData.getId()+"-"+process.getId();                    
                    if(!checkProcess.contains(checkOperation))
                    {
                        checkProcess.add(checkOperation);
                    }

                    Integer operIndex = checkProcess.indexOf(checkOperation);                    
                    oeeData.getPart()[operIndex] = partData.getPartNo();
                    oeeData.getPartId()[operIndex] = partData.getId();
                    oeeData.getProcess()[operIndex] = process.getProcessName();      
                    oeeData.getProcessId()[operIndex] = process.getId();


                    HashMap<Process,List<MachineTime>> processPeriods = machineTimeDates.get(partData);
                    
                    List<MachineTime> machineTimes = null;
                    if(processPeriods != null)
                    {
                        machineTimes = processPeriods.get(process);
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
                                for(int index = checkProcessPeriod.size();index<operIndex && index >=0;index++)
                                {
                                    checkProcessPeriod.add(index, new Integer(0));
                                }
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
                            oeeFactor[0].getPartId()[operIndex] = partData.getId();
                            oeeFactor[0].getProcessId()[operIndex] = process.getId();
                        }
                    }








                    List<ProductLine> productLines = processProductLines.get(process);
    //                List<Long> processes = new ArrayList<Long>();
                    for(ProductLine productLine:productLines)
                    {
    //                    if(!processes.contains(productLine.getProcess().getId()))
    //                    {
    //                        processes.add(productLine.getProcess().getId());
    //                    }

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
                        oeeFactor[1].getPartId()[operIndex] = partData.getId();
                        oeeFactor[1].getProcessId()[operIndex] = process.getId();
                    }
                    else
                    {
                        oeeFactor[1].getOp()[operIndex] = null;
                    }

                    if(totalPart[0].getOp()[operIndex] != null && !(new BigDecimal(0)).equals(totalPart[0].getOp()[operIndex]))
                    {
                        oeeFactor[2].getOp()[operIndex] = supportValue[2].getOp()[operIndex].divide(totalPart[0].getOp()[operIndex],3,RoundingMode.HALF_UP);
                        oeeFactor[2].getPartId()[operIndex] = partData.getId();
                        oeeFactor[2].getProcessId()[operIndex] = process.getId();
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
            }

            List<TimePartData> timePartDataArray = Arrays.asList(timePartDatas);
            ArrayList<TimePartData> timePartDataList = new ArrayList<TimePartData>();
            timePartDataList.addAll(timePartDataArray);
            timePartDataList.add(timePartDataTotal);
            oeeData.setTimeParts(timePartDataList);
            oeeData.setOperations(Arrays.asList(operationTime));
            oeeData.setSupportVariables(Arrays.asList(supportValue));
            oeeData.setOeeFactors(Arrays.asList(oeeFactor));
            oeeData.setTotalParts(Arrays.asList(totalPart));
//        oeeDatas.add(oeeData);

//            for(Date date : dates)
//            {
//                LOG.info(" Date : "+date);
//                List<MachineTime> machineTimes = machineTimeDates.get(date);
//
//                HashMap<Long,List<Integer>> processPeriods = dateProcessPeriods.get(date);
//                LOG.info(" processPeriods : "+(processPeriods != null));
//                if(machineTimes != null)
//                {
//                    LOG.info(" machine Time : "+machineTimes.size());
//                    for(MachineTime machineTime : machineTimes)
//                    {
//                        if(processPeriods != null)
//                        {
//                            LOG.info(" processPeriods size : "+(processPeriods.size()));
//                            List<Integer> periodInt = processPeriods.get(machineTime.getProcess().getId());
//                            if(periodInt == null)
//                            {
//                                continue;
//                            }
//                            for(Integer period : periodInt)
//                            {
//                                if(period.equals(1))
//                                {
//                                    BigDecimal aValue  = (operationTime[0].getOp()[machineTime.getProcess().getId().intValue()-1] == null
//                                            ?new BigDecimal(0):operationTime[0].getOp()[machineTime.getProcess().getId().intValue()-1])
//                                            .add(new BigDecimal(String.valueOf(machineTime.getApTime())));
//                                    operationTime[0].getOp()[machineTime.getProcess().getId().intValue()-1] = aValue;
//
//                                    BigDecimal bValue  = (operationTime[1].getOp()[machineTime.getProcess().getId().intValue()-1] == null
//                                            ?new BigDecimal(0):operationTime[1].getOp()[machineTime.getProcess().getId().intValue()-1])
//                                            .add(new BigDecimal(String.valueOf(machineTime.getBpTime())));
//                                    operationTime[1].getOp()[machineTime.getProcess().getId().intValue()-1] = bValue;
//
//                                    BigDecimal cValue  = (operationTime[2].getOp()[machineTime.getProcess().getId().intValue()-1] == null
//                                            ?new BigDecimal(0):operationTime[2].getOp()[machineTime.getProcess().getId().intValue()-1])
//                                            .add(new BigDecimal(String.valueOf(machineTime.getCpTime())));
//                                    operationTime[2].getOp()[machineTime.getProcess().getId().intValue()-1] = cValue;
//
//                                    BigDecimal dValue  = (operationTime[3].getOp()[machineTime.getProcess().getId().intValue()-1] == null
//                                            ?new BigDecimal(0):operationTime[3].getOp()[machineTime.getProcess().getId().intValue()-1])
//                                            .add(new BigDecimal(String.valueOf(machineTime.getDpTime())));
//                                    operationTime[3].getOp()[machineTime.getProcess().getId().intValue()-1] = dValue;
//
//                                    BigDecimal eValue  = (operationTime[4].getOp()[machineTime.getProcess().getId().intValue()-1] == null
//                                            ?new BigDecimal(0):operationTime[4].getOp()[machineTime.getProcess().getId().intValue()-1])
//                                            .add(new BigDecimal(String.valueOf(machineTime.getEpTime())));
//
//                                    operationTime[4].getOp()[machineTime.getProcess().getId().intValue()-1] = eValue;
//
//                                    BigDecimal f1Value  = (operationTime[5].getOp()[machineTime.getProcess().getId().intValue()-1] == null
//                                            ?new BigDecimal(0):operationTime[5].getOp()[machineTime.getProcess().getId().intValue()-1])
//                                            .add(new BigDecimal(String.valueOf(machineTime.getFp1Time())));
//                                    operationTime[5].getOp()[machineTime.getProcess().getId().intValue()-1] = f1Value;
//
//                                    BigDecimal f2Value  = (operationTime[6].getOp()[machineTime.getProcess().getId().intValue()-1] == null
//                                            ?new BigDecimal(0):operationTime[6].getOp()[machineTime.getProcess().getId().intValue()-1])
//                                            .add(new BigDecimal(String.valueOf(machineTime.getFp2Time())));
//                                    operationTime[6].getOp()[machineTime.getProcess().getId().intValue()-1] = f2Value;
//
//                                    BigDecimal f3Value  = (operationTime[7].getOp()[machineTime.getProcess().getId().intValue()-1] == null
//                                            ?new BigDecimal(0):operationTime[7].getOp()[machineTime.getProcess().getId().intValue()-1])
//                                            .add(new BigDecimal(String.valueOf(machineTime.getFp3Time())));
//                                    operationTime[7].getOp()[machineTime.getProcess().getId().intValue()-1] = f3Value;
//
//                                    BigDecimal gValue = bValue.add(cValue).add(dValue).add(eValue).add(f1Value).add(f2Value).add(f3Value);//G
//                                    operationTime[8].getOp()[machineTime.getProcess().getId().intValue()-1] = gValue;
//
//                                    BigDecimal hValue  = (operationTime[9].getOp()[machineTime.getProcess().getId().intValue()-1] == null
//                                            ?new BigDecimal(0):operationTime[9].getOp()[machineTime.getProcess().getId().intValue()-1])
//                                            .add(new BigDecimal(String.valueOf(machineTime.getHpTime())));
//                                    operationTime[9].getOp()[machineTime.getProcess().getId().intValue()-1] = hValue;
//
//                                    BigDecimal iValue  = (operationTime[10].getOp()[machineTime.getProcess().getId().intValue()-1] == null
//                                            ?new BigDecimal(0):operationTime[10].getOp()[machineTime.getProcess().getId().intValue()-1])
//                                            .add(new BigDecimal(String.valueOf(machineTime.getIpTime())));
//                                    operationTime[10].getOp()[machineTime.getProcess().getId().intValue()-1] = iValue;
//
//                                    BigDecimal jValue  = (operationTime[11].getOp()[machineTime.getProcess().getId().intValue()-1] == null
//                                            ?new BigDecimal(0):operationTime[11].getOp()[machineTime.getProcess().getId().intValue()-1])
//                                            .add(new BigDecimal(String.valueOf(machineTime.getJpTime())));
//                                    operationTime[11].getOp()[machineTime.getProcess().getId().intValue()-1] = jValue;
//
//                                    BigDecimal k1Value  = (operationTime[12].getOp()[machineTime.getProcess().getId().intValue()-1] == null
//                                            ?new BigDecimal(0):operationTime[12].getOp()[machineTime.getProcess().getId().intValue()-1])
//                                            .add(new BigDecimal(String.valueOf(machineTime.getKp1Time())));
//                                    operationTime[12].getOp()[machineTime.getProcess().getId().intValue()-1] = k1Value;
//
//                                    BigDecimal k2Value  = (operationTime[13].getOp()[machineTime.getProcess().getId().intValue()-1] == null
//                                            ?new BigDecimal(0):operationTime[13].getOp()[machineTime.getProcess().getId().intValue()-1])
//                                            .add(new BigDecimal(String.valueOf(machineTime.getKp2Time())));
//                                    operationTime[13].getOp()[machineTime.getProcess().getId().intValue()-1] = k2Value;
//
//                                    BigDecimal k3Value  = (operationTime[14].getOp()[machineTime.getProcess().getId().intValue()-1] == null
//                                            ?new BigDecimal(0):operationTime[14].getOp()[machineTime.getProcess().getId().intValue()-1])
//                                            .add(new BigDecimal(String.valueOf(machineTime.getKp3Time())));
//                                    operationTime[14].getOp()[machineTime.getProcess().getId().intValue()-1] = k3Value;
//
//                                    BigDecimal lValue = hValue.add(iValue).add(jValue).add(k1Value).add(k2Value).add(k3Value);
//                                    operationTime[15].getOp()[machineTime.getProcess().getId().intValue()-1] = lValue;
//
//                                    BigDecimal mValue = new BigDecimal(String.valueOf(machineTime.getMpTime()));
//                                    operationTime[16].getOp()[machineTime.getProcess().getId().intValue()-1] = mValue;
//                                    if(BigDecimal.ZERO.equals(mValue))
//                                    {
//                                        operationTime[17].getOp()[machineTime.getProcess().getId().intValue()-1] = null;
//                                    }
//                                    else
//                                    {
//                                        operationTime[17].getOp()[machineTime.getProcess().getId().intValue()-1] = aValue.subtract(gValue).subtract(lValue).multiply(new BigDecimal(60)).divide(mValue,3,RoundingMode.HALF_UP);
//                                    }
//
//                                    supportValue[0].getOp()[machineTime.getProcess().getId().intValue()-1] = aValue.subtract(gValue);
//                                    supportValue[1].getOp()[machineTime.getProcess().getId().intValue()-1] = aValue.subtract(gValue).subtract(lValue);
//                                    oeeFactor[0].getOp()[machineTime.getProcess().getId().intValue()-1] = aValue.subtract(gValue).subtract(lValue).divide(aValue.subtract(gValue),3,RoundingMode.HALF_UP);
//                                }
////                                else if(period.equals(2))
////                                {
////                                    BigDecimal aValue  = (operationTime[0].getOp()[machineTime.getProcess().getId().intValue()-1] == null
////                                            ?new BigDecimal(0):operationTime[0].getOp()[machineTime.getProcess().getId().intValue()-1])
////                                            .add(new BigDecimal(String.valueOf(machineTime.getApnTime())));
////                                    operationTime[0].getOp()[machineTime.getProcess().getId().intValue()-1] = aValue;
////
////                                    BigDecimal bValue  = (operationTime[1].getOp()[machineTime.getProcess().getId().intValue()-1] == null
////                                            ?new BigDecimal(0):operationTime[1].getOp()[machineTime.getProcess().getId().intValue()-1])
////                                            .add(new BigDecimal(String.valueOf(machineTime.getBpnTime())));
////                                    operationTime[1].getOp()[machineTime.getProcess().getId().intValue()-1] = bValue;
////
////                                    BigDecimal cValue  = (operationTime[2].getOp()[machineTime.getProcess().getId().intValue()-1] == null
////                                            ?new BigDecimal(0):operationTime[2].getOp()[machineTime.getProcess().getId().intValue()-1])
////                                            .add(new BigDecimal(String.valueOf(machineTime.getCpnTime())));
////                                    operationTime[2].getOp()[machineTime.getProcess().getId().intValue()-1] = cValue;
////
////                                    BigDecimal dValue  = (operationTime[3].getOp()[machineTime.getProcess().getId().intValue()-1] == null
////                                            ?new BigDecimal(0):operationTime[3].getOp()[machineTime.getProcess().getId().intValue()-1])
////                                            .add(new BigDecimal(String.valueOf(machineTime.getDpnTime())));
////                                    operationTime[3].getOp()[machineTime.getProcess().getId().intValue()-1] = dValue;
////
////                                    BigDecimal eValue  = (operationTime[4].getOp()[machineTime.getProcess().getId().intValue()-1] == null
////                                            ?new BigDecimal(0):operationTime[4].getOp()[machineTime.getProcess().getId().intValue()-1])
////                                            .add(new BigDecimal(String.valueOf(machineTime.getEpnTime())));
////
////                                    operationTime[4].getOp()[machineTime.getProcess().getId().intValue()-1] = eValue;
////
////                                    BigDecimal f1Value  = (operationTime[5].getOp()[machineTime.getProcess().getId().intValue()-1] == null
////                                            ?new BigDecimal(0):operationTime[5].getOp()[machineTime.getProcess().getId().intValue()-1])
////                                            .add(new BigDecimal(String.valueOf(machineTime.getFpn1Time())));
////                                    operationTime[5].getOp()[machineTime.getProcess().getId().intValue()-1] = f1Value;
////
////                                    BigDecimal f2Value  = (operationTime[6].getOp()[machineTime.getProcess().getId().intValue()-1] == null
////                                            ?new BigDecimal(0):operationTime[6].getOp()[machineTime.getProcess().getId().intValue()-1])
////                                            .add(new BigDecimal(String.valueOf(machineTime.getFpn2Time())));
////                                    operationTime[6].getOp()[machineTime.getProcess().getId().intValue()-1] = f2Value;
////
////                                    BigDecimal f3Value  = (operationTime[7].getOp()[machineTime.getProcess().getId().intValue()-1] == null
////                                            ?new BigDecimal(0):operationTime[7].getOp()[machineTime.getProcess().getId().intValue()-1])
////                                            .add(new BigDecimal(String.valueOf(machineTime.getFpn3Time())));
////                                    operationTime[7].getOp()[machineTime.getProcess().getId().intValue()-1] = f3Value;
////
////                                    BigDecimal gValue = bValue.add(cValue).add(dValue).add(eValue).add(f1Value).add(f2Value).add(f3Value);//G
////                                    operationTime[8].getOp()[machineTime.getProcess().getId().intValue()-1] = gValue;
////
////                                    BigDecimal hValue  = (operationTime[9].getOp()[machineTime.getProcess().getId().intValue()-1] == null
////                                            ?new BigDecimal(0):operationTime[9].getOp()[machineTime.getProcess().getId().intValue()-1])
////                                            .add(new BigDecimal(String.valueOf(machineTime.getHpnTime())));
////                                    operationTime[9].getOp()[machineTime.getProcess().getId().intValue()-1] = hValue;
////
////                                    BigDecimal iValue  = (operationTime[10].getOp()[machineTime.getProcess().getId().intValue()-1] == null
////                                            ?new BigDecimal(0):operationTime[10].getOp()[machineTime.getProcess().getId().intValue()-1])
////                                            .add(new BigDecimal(String.valueOf(machineTime.getIpnTime())));
////                                    operationTime[10].getOp()[machineTime.getProcess().getId().intValue()-1] = iValue;
////
////                                    BigDecimal jValue  = (operationTime[11].getOp()[machineTime.getProcess().getId().intValue()-1] == null
////                                            ?new BigDecimal(0):operationTime[11].getOp()[machineTime.getProcess().getId().intValue()-1])
////                                            .add(new BigDecimal(String.valueOf(machineTime.getJpnTime())));
////                                    operationTime[11].getOp()[machineTime.getProcess().getId().intValue()-1] = jValue;
////
////                                    BigDecimal k1Value  = (operationTime[12].getOp()[machineTime.getProcess().getId().intValue()-1] == null
////                                            ?new BigDecimal(0):operationTime[12].getOp()[machineTime.getProcess().getId().intValue()-1])
////                                            .add(new BigDecimal(String.valueOf(machineTime.getKpn1Time())));
////                                    operationTime[12].getOp()[machineTime.getProcess().getId().intValue()-1] = k1Value;
////
////                                    BigDecimal k2Value  = (operationTime[13].getOp()[machineTime.getProcess().getId().intValue()-1] == null
////                                            ?new BigDecimal(0):operationTime[13].getOp()[machineTime.getProcess().getId().intValue()-1])
////                                            .add(new BigDecimal(String.valueOf(machineTime.getKpn2Time())));
////                                    operationTime[13].getOp()[machineTime.getProcess().getId().intValue()-1] = k2Value;
////
////                                    BigDecimal k3Value  = (operationTime[14].getOp()[machineTime.getProcess().getId().intValue()-1] == null
////                                            ?new BigDecimal(0):operationTime[14].getOp()[machineTime.getProcess().getId().intValue()-1])
////                                            .add(new BigDecimal(String.valueOf(machineTime.getKpn3Time())));
////                                    operationTime[14].getOp()[machineTime.getProcess().getId().intValue()-1] = k3Value;
////
////                                    BigDecimal lValue = hValue.add(iValue).add(jValue).add(k1Value).add(k2Value).add(k3Value);
////                                    operationTime[15].getOp()[machineTime.getProcess().getId().intValue()-1] = lValue;
////
////                                    BigDecimal mValue = new BigDecimal(String.valueOf(machineTime.getMpTime()));
////                                    operationTime[16].getOp()[machineTime.getProcess().getId().intValue()-1] = mValue;
////
////                                    if(BigDecimal.ZERO.equals(mValue))
////                                    {
////                                        operationTime[17].getOp()[machineTime.getProcess().getId().intValue()-1] = null;
////                                    }
////                                    else
////                                    {
////                                        operationTime[17].getOp()[machineTime.getProcess().getId().intValue()-1] = aValue.subtract(gValue).subtract(lValue).multiply(new BigDecimal(60)).divide(mValue,3,RoundingMode.HALF_UP);
////                                    }
////            //                        operationTime[17].getOp()[machineTime.getProcess().getId().intValue()-1] = aValue.subtract(gValue).subtract(lValue).multiply(new BigDecimal(60)).divide(mValue,3,RoundingMode.HALF_UP);
////
////                                    supportValue[0].getOp()[machineTime.getProcess().getId().intValue()-1] = aValue.subtract(gValue);
////                                    supportValue[1].getOp()[machineTime.getProcess().getId().intValue()-1] = aValue.subtract(gValue).subtract(lValue);
////                                    oeeFactor[0].getOp()[machineTime.getProcess().getId().intValue()-1] = aValue.subtract(gValue).subtract(lValue).divide(aValue.subtract(gValue),3,RoundingMode.HALF_UP);
////                                }
//                            }
//                        }
//                    }
//                }
//
//
//
//
//
//                List<ProductLine> productLines = dateLines.get(date);
//                if(productLines != null)
//                {
//                    LOG.info(" ProductLine = : "+productLines.size());
//                    for(ProductLine productLine:productLines)
//                    {
//                        if(!processes.contains(productLine.getProcess().getId()))
//                        {
//                            processes.add(productLine.getProcess().getId());
//                        }
//
//                        timePartDatas[productLine.getTime().getId().intValue()-1].getOk()[productLine.getProcess().getId().intValue()-1] = new BigDecimal(String.valueOf(productLine.getOk())) ;
//                        timePartDatas[productLine.getTime().getId().intValue()-1].getNg()[productLine.getProcess().getId().intValue()-1] = new BigDecimal(String.valueOf(productLine.getNg())) ;
//
//                        totalPart[0].getOp()[productLine.getProcess().getId().intValue()-1] =
//                                (totalPart[0].getOp()[productLine.getProcess().getId().intValue()-1] == null
//                                ?new BigDecimal(0):totalPart[0].getOp()[productLine.getProcess().getId().intValue()-1])
//                                .add(timePartDatas[productLine.getTime().getId().intValue()-1].getOk()[productLine.getProcess().getId().intValue()-1]);
//                        supportValue[2].getOp()[productLine.getProcess().getId().intValue()-1] =
//                                (supportValue[2].getOp()[productLine.getProcess().getId().intValue()-1] == null
//                                ?new BigDecimal(0):supportValue[2].getOp()[productLine.getProcess().getId().intValue()-1])
//                                .add(timePartDatas[productLine.getTime().getId().intValue()-1].getOk()[productLine.getProcess().getId().intValue()-1])
//                                .subtract(timePartDatas[productLine.getTime().getId().intValue()-1].getNg()[productLine.getProcess().getId().intValue()-1]);
//                        timePartDataTotal.getOk()[productLine.getProcess().getId().intValue()-1] =
//                                (timePartDataTotal.getOk()[productLine.getProcess().getId().intValue()-1] == null
//                                ?new BigDecimal(0):timePartDataTotal.getOk()[productLine.getProcess().getId().intValue()-1])
//                                .add(timePartDatas[productLine.getTime().getId().intValue()-1].getOk()[productLine.getProcess().getId().intValue()-1]) ;
//                        timePartDataTotal.getNg()[productLine.getProcess().getId().intValue()-1] =
//                                (timePartDataTotal.getNg()[productLine.getProcess().getId().intValue()-1] == null
//                                ?new BigDecimal(0):timePartDataTotal.getNg()[productLine.getProcess().getId().intValue()-1])
//                                .add(timePartDatas[productLine.getTime().getId().intValue()-1].getNg()[productLine.getProcess().getId().intValue()-1]) ;
//                    }
//                }
//
//            }
//
//            for(Long processId : processes)
//            {
//                if(operationTime[17].getOp()[processId.intValue()-1] != null && !(new BigDecimal(0)).equals(operationTime[17].getOp()[processId.intValue()-1]))
//                {
//                    oeeFactor[1].getOp()[processId.intValue()-1] = totalPart[0].getOp()[processId.intValue()-1].divide(operationTime[17].getOp()[processId.intValue()-1],3,RoundingMode.HALF_UP);
//                }
//                else
//                {
//                    oeeFactor[1].getOp()[processId.intValue()-1] = null;
//                }
//
//                if(totalPart[0].getOp()[processId.intValue()-1] != null && !(new BigDecimal(0)).equals(totalPart[0].getOp()[processId.intValue()-1]))
//                {
//                    oeeFactor[2].getOp()[processId.intValue()-1] = supportValue[2].getOp()[processId.intValue()-1].divide(totalPart[0].getOp()[processId.intValue()-1],3,RoundingMode.HALF_UP);
//                }
//                else
//                {
//                    oeeFactor[2].getOp()[processId.intValue()-1] = null;
//                }
//
//                if( oeeFactor[2].getOp()[processId.intValue()-1] == null || oeeFactor[1].getOp()[processId.intValue()-1] == null || oeeFactor[0].getOp()[processId.intValue()-1] == null)
//                {
//                    oeeFactor[3].getOp()[processId.intValue()-1] = null;
//                }
//                else
//                {
//                    oeeFactor[3].getOp()[processId.intValue()-1] = oeeFactor[0].getOp()[processId.intValue()-1].multiply(oeeFactor[1].getOp()[processId.intValue()-1]).multiply(oeeFactor[2].getOp()[processId.intValue()-1]);
//                }
//            }
//            List<TimePartData> timePartDataArray = Arrays.asList(timePartDatas);
//            ArrayList<TimePartData> timePartDataList = new ArrayList<TimePartData>();
//            timePartDataList.addAll(timePartDataArray);
//            timePartDataList.add(timePartDataTotal);
//            oeeData.setTimeParts(timePartDataList);
//            oeeData.setOperations(Arrays.asList(operationTime));
//            oeeData.setSupportVariables(Arrays.asList(supportValue));
//            oeeData.setOeeFactors(Arrays.asList(oeeFactor));
//            oeeData.setTotalParts(Arrays.asList(totalPart));
            
        
        return oeeData;
    }

    public TimePartData[] getTimeParts(Session session,Integer columnCount)
    {
            Criteria criteria = session.createCriteria(Time.class);
            criteria.addOrder(Order.asc("id"));
            List<Time> times = criteria.list();
            List<TimePartData> timePartDatas = new ArrayList<TimePartData>();
            for(Time time : times)
            {
                TimePartData timePartData = new TimePartData();
                timePartData.setNg(new BigDecimal[columnCount]);
                timePartData.setOk(new BigDecimal[columnCount]);
                timePartData.setName("P");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                timePartData.setDescription(timeFormat.format(time.getBeginTime())+"-"+timeFormat.format(time.getEndTime()));
                timePartDatas.add(timePartData);
            }


            return timePartDatas.toArray(new TimePartData[0]);
    }

    public HashMap<Part,HashMap<Process,List<MachineTime>>> getMachineTime(Session session,List<GroupingMachineTime> grouping,Date startDate,Date endDate)
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
        HashMap<Part,HashMap<Process,List<MachineTime>>> partProcessPeriods = new HashMap<Part,HashMap<Process,List<MachineTime>>>();
        if(machineTimes != null && machineTimes.size() > 0)
        {
            for(MachineTime machineTime : machineTimes)
            {
                HashMap<Process,List<MachineTime>> processPeriods = partProcessPeriods.get(machineTime.getPart());
                if(processPeriods == null)
                {
                    processPeriods = new HashMap<Process,List<MachineTime>>();
                    partProcessPeriods.put(machineTime.getPart(), processPeriods);
                }

                List<MachineTime> machineTimeDatas = processPeriods.get(machineTime.getProcess());
                if(machineTimeDatas == null)
                {
                    machineTimeDatas = new ArrayList<MachineTime>();
                    processPeriods.put(machineTime.getProcess(), machineTimeDatas);
                }

                machineTimeDatas.add(machineTime);
            }
        }

        return partProcessPeriods;
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
        
        return grouping;
    }


}
