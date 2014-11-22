/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.rpt;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.machine.Machine;
import com.bsd.cse.model.backoffice.part.Process;
import com.bsd.cse.model.backoffice.part.Checkpoint;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.input.ControlChartGroupingData;
import com.bsd.cse.model.input.MachineTime;
import com.bsd.cse.model.input.MeasurementPart;
import com.bsd.cse.model.input.ProductLine;
import com.bsd.cse.model.input.Time;
import com.bsd.cse.model.report.ControlChartData;
import com.bsd.cse.model.report.ControlData;
import com.bsd.cse.model.report.ControlSerieData;
import com.bsd.cse.model.report.GroupingMachineTime;
import com.bsd.cse.model.report.OEEData;
import com.bsd.cse.model.report.OEEReportData;
import com.bsd.cse.model.report.OperationData;
import com.bsd.cse.model.report.TimePartData;
import com.bsd.cse.model.security.Team;
import com.bsd.cse.model.security.User;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
public class OEEProcessor {    
    private static Log LOG = LogFactory.getLog(OEEProcessor.class);
    private HashMap<Part,HashMap<Process,List<Integer>>> periods = new HashMap<Part,HashMap<Process,List<Integer>>>();
    public List<OEEData> processor(final Date startDate,final Date endDate,final Long machineId,final Long employeeId,final Long teamId) throws Exception
    {
        final HashMap<String,Object> results = new HashMap<String,Object>();
        (new TransactionalProcessor()
        {
            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Part part = null;
                User employee = null;
                Team team = null;
                List<ProductLine> productLines = null;                
                if(employeeId != null)
                {
                    employee = getEmployee(session, employeeId);
                    team = employee.getTeam();
                }

                if(teamId != null)
                {
                    team = getTeam(session, teamId);
                }

//                if(partId != null)
//                {
//                    part = getPart(session, partId);
//                }

                productLines = getProductLine(session, startDate, machineId,employeeId,teamId);
                List<GroupingMachineTime> grouping = groupingMachineTime(productLines);

                List<OEEData> oeeDatas = caculateData(session,  separatePartProcess(productLines),  employee, startDate ,machineId,team,grouping);


                results.put("result",oeeDatas);
            }
            
        }).process();
        return (List<OEEData> )results.get("result");
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

    public Team getTeam(Session session,Long teamId)
    {
        Team team = (Team)session.get(Team.class,teamId);
        return team;
    }

    public HashMap<Part,HashMap<Process,List<MachineTime>>> getMachineTime(Session session,List<GroupingMachineTime> grouping,Date scheduleTime)
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
        criteria.add(Restrictions.eq("scheduleTime", scheduleTime));
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

    public List<ProductLine> getProductLine(Session session,Date requestDate,Long machineId,Long employeeId,Long teamId)
    {

        Criteria criteria = session.createCriteria(ProductLine.class).createAlias("user","user");
        if(machineId != null)
        {
            criteria.add(Restrictions.eq("machine.id", machineId));
        }
        
        criteria.add(Restrictions.eq("requestDate", requestDate));
        
        if(employeeId != null)
        {
            criteria.add(Restrictions.eq("user.id", employeeId));
        }

        if(teamId != null)
        {
            criteria.add(Restrictions.eq("user.team.id", teamId));
        }

        criteria.addOrder(Order.asc("part.id"));
        criteria.addOrder(Order.asc("process.id"));        
        criteria.addOrder(Order.asc("time.id"));        
        List<ProductLine> productLines = criteria.list();
        return productLines;
    }

    public HashMap<Part,HashMap<Process,List<ProductLine>>> separatePartProcess(List<ProductLine> productLines)
    {
        HashMap<Part,HashMap<Process,List<ProductLine>>> results = new HashMap<Part,HashMap<Process,List<ProductLine>>>();
        for(ProductLine productLine : productLines)
        {
//            HashMap<Process,List<Integer>> machineMap = periods.get(productLine.getPart());
//            if(machineMap == null)
//            {
//                machineMap = new HashMap<Machine,List<Integer>>();
//                List<Integer> periodInt = new ArrayList<Integer>();
//                periodInt.add(productLine.getTime().getRound());
//                machineMap.put(productLine.getMachine(),periodInt);
//                periods.put(productLine.getPart(), machineMap);
//            }
//            else
//            {
//                List<Integer> periodInt = machineMap.get(productLine.getMachine());
//                if(periodInt == null)
//                {
//                    periodInt = new ArrayList<Integer>();
//                    periodInt.add(productLine.getTime().getRound());
//                    machineMap.put(productLine.getMachine(),periodInt);
//                }
//                else
//                {
//                    if(!periodInt.contains(productLine.getTime().getRound()))
//                    {
//                        periodInt.add(productLine.getTime().getRound());
//                    }
//                }
//            }
            
            HashMap<Process,List<ProductLine>> processLines = results.get(productLine.getPart());
            
            if(processLines == null)
            {
                processLines = new HashMap<Process,List<ProductLine>>();
                List<ProductLine> processProductLines = new ArrayList<ProductLine>();
                processProductLines.add(productLine);
                processLines.put(productLine.getProcess(),processProductLines );
                results.put(productLine.getPart(), processLines);
            }
            else
            {
                List<ProductLine> processProductLines = processLines.get(productLine.getProcess());
                if(processProductLines == null)
                {
                    processProductLines = new ArrayList<ProductLine>();
                    processProductLines.add(productLine);
                    processLines.put(productLine.getProcess(),processProductLines );
                }
                else
                {
                    processProductLines.add(productLine);
                }
            }
        }
        return results;
    }

    public List<OEEData> caculateData(Session session,HashMap<Part,HashMap<Process,List<ProductLine>>> partLines,User employee,Date requestDate,Long machineId,Team team,List<GroupingMachineTime> grouping)
    {
        List<OEEData> oeeDatas = new ArrayList<OEEData>();
        Part[] parts = (Part[])partLines.keySet().toArray(new Part[0]);
        Machine machine = null;

        OEEData oeeData = new OEEData();
        
        if(machineId != null)
        {
            machine = (Machine)session.get(Machine.class,machineId);
            oeeData.setMachineName(machine.getName());
        }

        oeeData.setRequestDate(requestDate);
        oeeData.setShift(team.getTeamName());
        
        oeeData.setWorkingHour(24);
                
        OperationData operationTime[] = new OperationData[]{new OperationData("A","Total Available Minutes (In day)\nเวลาที่มี (นาที) ต่อวัน"),new OperationData("B","Short Breaks, Minutes\nพักเบรค (นาที)")
                                                        ,new OperationData("C","Meal Break, Minutes\nพักทานอาหาร (นาที)"),new OperationData("D","5S Time, Minutes\nเวลาในการทำ 3ส (นาที)")
                                                        ,new OperationData("E","Tool Change Time, Minutes\nเวลาสูญเสียเนื่องจากการเปลี่ยนมีด"),new OperationData("F","Job Change Time, Minutes\nเวลาสูญเสียเนื่องจากเปลี่ยนงาน")
                                                        ,new OperationData("F1","Not Planned\nเวลาสูญเสียเนื่องจากไม่มีแผนผลิต"),new OperationData("F2","Inspection\nเวลาสูญเสียเนื่องจากการตรวจสอบ")
                                                        ,new OperationData("G","Total Planned Down time\nเวสาสูญเสียที่รวมอยู่ในแผน"),new OperationData("H","Input RM Not Available (min)\nเวลาสูญเสียเนื่องจากไม่มี วัตถุดิบ")
                                                        ,new OperationData("I","Operator Not Available\nเวลาสูญเสียเนื่องจากไม่มีพนักงาน"),new OperationData("J","Unplanned Maintenance\nเวลาสูญเสียเนื่องจากซ่อมบำรุง")
                                                        ,new OperationData("K","Machine setting\nเวลาสูญเสียเนื่องจากการตั้งงาน"),new OperationData("K1","No power\nเวลาสูญเสียเนื่องจากไม่มีไฟฟ้า")
                                                        ,new OperationData("K2","Chip cleaning\nเวลาสูญเสียเนื่องจากทิ้งเศษเหล็ก"),new OperationData("L","Total Unplanned Down time\nรวมเวลาสูญเสียทั้งหมด")
                                                        ,new OperationData("M","Cycle time, Seconds\nเวลาในการผลิต/ชิ้น (วินาที)"),new OperationData("O","Ideal Q'ty Parts expected\nจำนวนชิ้นงานที่คาดหวัง")};
        OperationData supportValue[] = new OperationData[]{new OperationData("R","Planned Production, Minutes\nเวลาที่มีจริงในการวางแผน (นาที)")
                                                        ,new OperationData("S","Operation Time, Minutes\nเวลาที่มีจริงในการผลิต (นาที)")
                                                        ,new OperationData("T","Good Pieces\nชิ้นงานดีที่ผลิตได้")};

        OperationData oeeFactor[] = new OperationData[]{new OperationData("U","Availability")
                                                        ,new OperationData("V","Performance")
                                                        ,new OperationData("W","Quality")
                                                        ,new OperationData("X","Overall OEE")};
        OperationData totalPart[] = new OperationData[]{new OperationData("P","Total Pieces Produced-actual")};

        TimePartData timePartDataTotal = new TimePartData();
        timePartDataTotal.setName("Q");
        timePartDataTotal.setDescription("Total  Qty in a day");

        TimePartData[] timePartDatas = getTimeParts(session);

        HashMap<Part,HashMap<Process,List<MachineTime>>> partProcessPeriods = getMachineTime(session, grouping, requestDate);
        List<String> checkProcess = new ArrayList<String>();
        List<Integer> checkProcessPeriod = new ArrayList<Integer>();

        

        
        
        for(Part partData : parts)
        {
            HashMap<Process,List<ProductLine>> processProductLines = partLines.get(partData);
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
                LOG.info("checkOperation = "+checkOperation);
                if(!checkProcess.contains(checkOperation))
                {
                    checkProcess.add(checkOperation);
                }

                Integer operIndex = checkProcess.indexOf(checkOperation);
                LOG.info("operIndex = "+operIndex);
                oeeData.getPart()[operIndex] = partData.getPartNo();
                oeeData.getProcess()[operIndex] = process.getProcessName();
                LOG.info("oeeData.getPart()["+operIndex+"] = "+oeeData.getPart()[operIndex]);
                LOG.info("oeeData.getProcess()["+operIndex+"] = "+oeeData.getProcess()[operIndex]);

                HashMap<Process,List<MachineTime>> processPeriods = partProcessPeriods.get(partData);

                LOG.info("processPeriods = "+processPeriods);
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
        oeeDatas.add(oeeData);
        
        return oeeDatas;
    }

    public TimePartData[] getTimeParts(Session session)
    {
            Criteria criteria = session.createCriteria(Time.class);
            criteria.addOrder(Order.asc("id"));
            List<Time> times = criteria.list();
            List<TimePartData> timePartDatas = new ArrayList<TimePartData>();
            for(Time time : times)
            {
                TimePartData timePartData = new TimePartData();
                timePartData.setName("P");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                timePartData.setDescription(timeFormat.format(time.getBeginTime())+"-"+timeFormat.format(time.getEndTime()));
                timePartDatas.add(timePartData);
            }
           
            
            return timePartDatas.toArray(new TimePartData[0]);
    }

    


    
}
