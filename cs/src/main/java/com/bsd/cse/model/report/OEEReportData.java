/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.report;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author bento
 */
public class OEEReportData {
    public static List<OEEData> getData()
    {
        List<OEEData> oeeDatas  = new ArrayList<OEEData>();
        OEEData data = new OEEData();
//        data.setPartDate(new Date());
//        data.setPartName("Part Name");
//        data.setMachineName("MachineName");
//        data.setPartNo("Part No");
//        data.setWorkingHour(24);
////        data.setEmployeeId("11111");
////        data.setEmployeeName("Test");
//        data.setShift("Shift");
//        List<OperationData> operations = new ArrayList<OperationData>();
//        OperationData dataA  = new OperationData();
//        dataA.setName("A");
//
//        dataA.setDescription("Total Available Minutes (In day)");
//        dataA.setOp1(new BigDecimal(0));
//        dataA.setOp2(new BigDecimal(0));
//        dataA.setOp3(new BigDecimal(0));
//        dataA.setOp4(new BigDecimal(0));
//        dataA.setOp5(new BigDecimal(0));
//        dataA.setOp6(new BigDecimal(0));
//        dataA.setOp7(new BigDecimal(0));
//        dataA.setOp8(new BigDecimal(0));
//        dataA.setOp9(new BigDecimal(0));
//        dataA.setOp10(new BigDecimal(0));
//        dataA.setOp11(new BigDecimal(0));
//        dataA.setOp12(new BigDecimal(0));
//        OperationData dataB  = new OperationData();
//        dataB.setName("B");
//        dataB.setDescription("Short Breaks, Minutes");
//        dataB.setOp1(new BigDecimal(0));
//        dataB.setOp2(new BigDecimal(0));
//        dataB.setOp3(new BigDecimal(0));
//        dataB.setOp4(new BigDecimal(0));
//        dataB.setOp5(new BigDecimal(0));
//        dataB.setOp6(new BigDecimal(0));
//        dataB.setOp7(new BigDecimal(0));
//        dataB.setOp8(new BigDecimal(0));
//        dataB.setOp9(new BigDecimal(0));
//        dataB.setOp10(new BigDecimal(0));
//        dataB.setOp11(new BigDecimal(0));
//        dataB.setOp12(new BigDecimal(0));
//        OperationData dataC  = new OperationData();
//        dataC.setName("C");
//        dataC.setDescription("Meal Break, Minutes");
//        dataC.setOp1(new BigDecimal(0));
//        dataC.setOp2(new BigDecimal(0));
//        dataC.setOp3(new BigDecimal(0));
//        dataC.setOp4(new BigDecimal(0));
//        dataC.setOp5(new BigDecimal(0));
//        dataC.setOp6(new BigDecimal(0));
//        dataC.setOp7(new BigDecimal(0));
//        dataC.setOp8(new BigDecimal(0));
//        dataC.setOp9(new BigDecimal(0));
//        dataC.setOp10(new BigDecimal(0));
//        dataC.setOp11(new BigDecimal(0));
//        dataC.setOp12(new BigDecimal(0));
//        OperationData dataH  = new OperationData();
//        dataH.setName("H");
//        dataH.setDescription("Input RM Not Available (min)");
//        dataH.setOp1(new BigDecimal(0));
//        dataH.setOp2(new BigDecimal(0));
//        dataH.setOp3(new BigDecimal(0));
//        dataH.setOp4(new BigDecimal(0));
//        dataH.setOp5(new BigDecimal(0));
//        dataH.setOp6(new BigDecimal(0));
//        dataH.setOp7(new BigDecimal(0));
//        dataH.setOp8(new BigDecimal(0));
//        dataH.setOp9(new BigDecimal(0));
//        dataH.setOp10(new BigDecimal(0));
//        dataH.setOp11(new BigDecimal(0));
//        dataH.setOp12(new BigDecimal(0));
//        OperationData dataG  = new OperationData();
//        dataG.setName("G");
//        dataG.setDescription("Total Planned Down time");
//        dataG.setOp1(new BigDecimal(0));
//        dataG.setOp2(new BigDecimal(0));
//        dataG.setOp3(new BigDecimal(0));
//        dataG.setOp4(new BigDecimal(0));
//        dataG.setOp5(new BigDecimal(0));
//        dataG.setOp6(new BigDecimal(0));
//        dataG.setOp7(new BigDecimal(0));
//        dataG.setOp8(new BigDecimal(0));
//        dataG.setOp9(new BigDecimal(0));
//        dataG.setOp10(new BigDecimal(0));
//        dataG.setOp11(new BigDecimal(0));
//        dataG.setOp12(new BigDecimal(0));
//        OperationData dataL  = new OperationData();
//        dataL.setName("L");
//        dataL.setDescription("Total Unplanned Down time");
//        dataL.setOp1(new BigDecimal(0));
//        dataL.setOp2(new BigDecimal(0));
//        dataL.setOp3(new BigDecimal(0));
//        dataL.setOp4(new BigDecimal(0));
//        dataL.setOp5(new BigDecimal(0));
//        dataL.setOp6(new BigDecimal(0));
//        dataL.setOp7(new BigDecimal(0));
//        dataL.setOp8(new BigDecimal(0));
//        dataL.setOp9(new BigDecimal(0));
//        dataL.setOp10(new BigDecimal(0));
//        dataL.setOp11(new BigDecimal(0));
//        dataL.setOp12(new BigDecimal(0));
//        OperationData dataO  = new OperationData();
//        dataO.setName("O");
//        dataO.setDescription("Ideal Q'ty Parts expected");
//        dataO.setOp1(new BigDecimal(0));
//        dataO.setOp2(new BigDecimal(0));
//        dataO.setOp3(new BigDecimal(0));
//        dataO.setOp4(new BigDecimal(0));
//        dataO.setOp5(new BigDecimal(0));
//        dataO.setOp6(new BigDecimal(0));
//        dataO.setOp7(new BigDecimal(0));
//        dataO.setOp8(new BigDecimal(0));
//        dataO.setOp9(new BigDecimal(0));
//        dataO.setOp10(new BigDecimal(0));
//        dataO.setOp11(new BigDecimal(0));
//        dataO.setOp12(new BigDecimal(0));
//        OperationData dataF  = new OperationData();
//        dataF.setName("F");
//        dataF.setDescription("Job Change Time, Minutes");
//        dataF.setOp1(new BigDecimal(0));
//        dataF.setOp2(new BigDecimal(0));
//        dataF.setOp3(new BigDecimal(0));
//        dataF.setOp4(new BigDecimal(0));
//        dataF.setOp5(new BigDecimal(0));
//        dataF.setOp6(new BigDecimal(0));
//        dataF.setOp7(new BigDecimal(0));
//        dataF.setOp8(new BigDecimal(0));
//        dataF.setOp9(new BigDecimal(0));
//        dataF.setOp10(new BigDecimal(0));
//        dataF.setOp11(new BigDecimal(0));
//        dataF.setOp12(new BigDecimal(0));
//        OperationData dataF1  = new OperationData();
//        dataF1.setName("F");
//        dataF1.setDescription("Not Planned");
//        dataF1.setOp1(new BigDecimal(0));
//        dataF1.setOp2(new BigDecimal(0));
//        dataF1.setOp3(new BigDecimal(0));
//        dataF1.setOp4(new BigDecimal(0));
//        dataF1.setOp5(new BigDecimal(0));
//        dataF1.setOp6(new BigDecimal(0));
//        dataF1.setOp7(new BigDecimal(0));
//        dataF1.setOp8(new BigDecimal(0));
//        dataF1.setOp9(new BigDecimal(0));
//        dataF1.setOp10(new BigDecimal(0));
//        dataF1.setOp11(new BigDecimal(0));
//        dataF1.setOp12(new BigDecimal(0));
//
//
//        List<OperationData> totalParts = new ArrayList<OperationData>();
//        OperationData dataP  = new OperationData();
//        dataP.setName("P");
//        dataP.setDescription("Total Pieces Produced-actual");
//        dataP.setOp1(new BigDecimal(0));
//        dataP.setOp2(new BigDecimal(0));
//        dataP.setOp3(new BigDecimal(0));
//        dataP.setOp4(new BigDecimal(0));
//        dataP.setOp5(new BigDecimal(0));
//        dataP.setOp6(new BigDecimal(0));
//        dataP.setOp7(new BigDecimal(0));
//        dataP.setOp8(new BigDecimal(0));
//        dataP.setOp9(new BigDecimal(0));
//        dataP.setOp10(new BigDecimal(0));
//        dataP.setOp11(new BigDecimal(0));
//        dataP.setOp12(new BigDecimal(0));
//
//        List<OperationData> supportVariables = new ArrayList<OperationData>();
//        OperationData dataR  = new OperationData();
//        dataR.setName("R");
//        dataR.setDescription("Planned Production, Minutes");
//        dataR.setOp1(new BigDecimal(0));
//        dataR.setOp2(new BigDecimal(0));
//        dataR.setOp3(new BigDecimal(0));
//        dataR.setOp4(new BigDecimal(0));
//        dataR.setOp5(new BigDecimal(0));
//        dataR.setOp6(new BigDecimal(0));
//        dataR.setOp7(new BigDecimal(0));
//        dataR.setOp8(new BigDecimal(0));
//        dataR.setOp9(new BigDecimal(0));
//        dataR.setOp10(new BigDecimal(0));
//        dataR.setOp11(new BigDecimal(0));
//        dataR.setOp12(new BigDecimal(0));
//
//        OperationData dataS  = new OperationData();
//        dataS.setName("S");
//        dataS.setDescription("Operation Time, Minutes");
//        dataS.setOp1(new BigDecimal(0));
//        dataS.setOp2(new BigDecimal(0));
//        dataS.setOp3(new BigDecimal(0));
//        dataS.setOp4(new BigDecimal(0));
//        dataS.setOp5(new BigDecimal(0));
//        dataS.setOp6(new BigDecimal(0));
//        dataS.setOp7(new BigDecimal(0));
//        dataS.setOp8(new BigDecimal(0));
//        dataS.setOp9(new BigDecimal(0));
//        dataS.setOp10(new BigDecimal(0));
//        dataS.setOp11(new BigDecimal(0));
//        dataS.setOp12(new BigDecimal(0));
//
//        OperationData dataT  = new OperationData();
//        dataT.setName("T");
//        dataT.setDescription("Good Pieces");
//        dataT.setOp1(new BigDecimal(0));
//        dataT.setOp2(new BigDecimal(0));
//        dataT.setOp3(new BigDecimal(0));
//        dataT.setOp4(new BigDecimal(0));
//        dataT.setOp5(new BigDecimal(0));
//        dataT.setOp6(new BigDecimal(0));
//        dataT.setOp7(new BigDecimal(0));
//        dataT.setOp8(new BigDecimal(0));
//        dataT.setOp9(new BigDecimal(0));
//        dataT.setOp10(new BigDecimal(0));
//        dataT.setOp11(new BigDecimal(0));
//        dataT.setOp12(new BigDecimal(0));
//
//
//        List<OperationData> oeeFactors = new ArrayList<OperationData>();
//        OperationData dataU  = new OperationData();
//        dataU.setName("U");
//        dataU.setDescription("Availability");
//        dataU.setOp1(new BigDecimal(0));
//        dataU.setOp2(new BigDecimal(0));
//        dataU.setOp3(new BigDecimal(0));
//        dataU.setOp4(new BigDecimal(0));
//        dataU.setOp5(new BigDecimal(0));
//        dataU.setOp6(new BigDecimal(0));
//        dataU.setOp7(new BigDecimal(0));
//        dataU.setOp8(new BigDecimal(0));
//        dataU.setOp9(new BigDecimal(0));
//        dataU.setOp10(new BigDecimal(0));
//        dataU.setOp11(new BigDecimal(0));
//        dataU.setOp12(new BigDecimal(0));
//
//        OperationData dataV  = new OperationData();
//        dataV.setName("V");
//        dataV.setDescription("Performance");
//        dataV.setOp1(new BigDecimal(0));
//        dataV.setOp2(new BigDecimal(0));
//        dataV.setOp3(new BigDecimal(0));
//        dataV.setOp4(new BigDecimal(0));
//        dataV.setOp5(new BigDecimal(0));
//        dataV.setOp6(new BigDecimal(0));
//        dataV.setOp7(new BigDecimal(0));
//        dataV.setOp8(new BigDecimal(0));
//        dataV.setOp9(new BigDecimal(0));
//        dataV.setOp10(new BigDecimal(0));
//        dataV.setOp11(new BigDecimal(0));
//        dataV.setOp12(new BigDecimal(0));
//
//        OperationData dataW  = new OperationData();
//        dataW.setName("W");
//        dataW.setDescription("Quality");
//        dataW.setOp1(new BigDecimal(0));
//        dataW.setOp2(new BigDecimal(0));
//        dataW.setOp3(new BigDecimal(0));
//        dataW.setOp4(new BigDecimal(0));
//        dataW.setOp5(new BigDecimal(0));
//        dataW.setOp6(new BigDecimal(0));
//        dataW.setOp7(new BigDecimal(0));
//        dataW.setOp8(new BigDecimal(0));
//        dataW.setOp9(new BigDecimal(0));
//        dataW.setOp10(new BigDecimal(0));
//        dataW.setOp11(new BigDecimal(0));
//        dataW.setOp12(new BigDecimal(0));
//
//        OperationData dataX  = new OperationData();
//        dataX.setName("X");
//        dataX.setDescription("Overall OEE");
//        dataX.setOp1(new BigDecimal(0));
//        dataX.setOp2(new BigDecimal(0));
//        dataX.setOp3(new BigDecimal(0));
//        dataX.setOp4(new BigDecimal(0));
//        dataX.setOp5(new BigDecimal(0));
//        dataX.setOp6(new BigDecimal(0));
//        dataX.setOp7(new BigDecimal(0));
//        dataX.setOp8(new BigDecimal(0));
//        dataX.setOp9(new BigDecimal(0));
//        dataX.setOp10(new BigDecimal(0));
//        dataX.setOp11(new BigDecimal(0));
//        dataX.setOp12(new BigDecimal(0));
//
//        operations.add(dataA);
//        operations.add(dataB);
//        operations.add(dataC);
//        operations.add(dataF);
//        operations.add(dataF1);
//        operations.add(dataG);
//        operations.add(dataH);
//        operations.add(dataL);
//        operations.add(dataO);
//        data.setOperations(operations);
//
//        totalParts.add(dataP);
//        data.setTotalParts(totalParts);
//
//        supportVariables.add(dataR);
//        supportVariables.add(dataS);
//        supportVariables.add(dataT);
//        data.setSupportVariables(supportVariables);
//
//
//        oeeFactors.add(dataU);
//        oeeFactors.add(dataV);
//        oeeFactors.add(dataW);
//        oeeFactors.add(dataX);
//        data.setOeeFactors(oeeFactors);
//
//        List<TimePartData> timeParts = new ArrayList<TimePartData>();
//        TimePartData timePart1  = new TimePartData();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
//        for(int index = 0 ; index< 12;index++)
//        {
//            timePart1  = new TimePartData();
//            timePart1.setName("P");
//            Calendar cal1 = Calendar.getInstance();
//            Calendar cal2 = Calendar.getInstance();
//            cal1.set(Calendar.HOUR_OF_DAY, 8);
//            cal1.set(Calendar.MINUTE,0);
//            cal2.set(Calendar.HOUR_OF_DAY, 10);
//            cal2.set(Calendar.MINUTE,0);
//            cal1.add(Calendar.HOUR_OF_DAY,index*2);
//            cal2.add(Calendar.HOUR_OF_DAY,index*2);
//
//            timePart1.setDescription(MessageFormat.format("{0} - {1}",dateFormat.format(cal1.getTime()),dateFormat.format(cal2.getTime())));
//            timePart1.setOk1(new BigDecimal(200));
//            timePart1.setOk2(new BigDecimal(0));
//            timePart1.setOk3(new BigDecimal(0));
//            timePart1.setOk4(new BigDecimal(0));
//            timePart1.setOk5(new BigDecimal(0));
//            timePart1.setOk6(new BigDecimal(0));
//            timePart1.setOk7(new BigDecimal(0));
//            timePart1.setOk8(new BigDecimal(0));
//            timePart1.setOk9(new BigDecimal(0));
//            timePart1.setOk10(new BigDecimal(0));
//            timePart1.setOk11(new BigDecimal(0));
//            timePart1.setOk12(new BigDecimal(0));
//            timePart1.setNg1(new BigDecimal(200));
//            timePart1.setNg2(new BigDecimal(0));
//            timePart1.setNg3(new BigDecimal(0));
//            timePart1.setNg4(new BigDecimal(0));
//            timePart1.setNg5(new BigDecimal(0));
//            timePart1.setNg6(new BigDecimal(0));
//            timePart1.setNg7(new BigDecimal(0));
//            timePart1.setNg8(new BigDecimal(0));
//            timePart1.setNg9(new BigDecimal(0));
//            timePart1.setNg10(new BigDecimal(0));
//            timePart1.setNg11(new BigDecimal(0));
//            timePart1.setNg12(new BigDecimal(0));
//            timeParts.add(timePart1);
//        }
//        timePart1  = new TimePartData();
//        timePart1.setName("Q");
//
//
//        timePart1.setDescription("Total  Qty in a day");
//        timePart1.setOk1(new BigDecimal(9999));
//        timePart1.setOk2(new BigDecimal(0));
//        timePart1.setOk3(new BigDecimal(0));
//        timePart1.setOk4(new BigDecimal(0));
//        timePart1.setOk5(new BigDecimal(0));
//        timePart1.setOk6(new BigDecimal(0));
//        timePart1.setOk7(new BigDecimal(0));
//        timePart1.setOk8(new BigDecimal(0));
//        timePart1.setOk9(new BigDecimal(0));
//        timePart1.setOk10(new BigDecimal(0));
//        timePart1.setOk11(new BigDecimal(0));
//        timePart1.setOk12(new BigDecimal(0));
//        timePart1.setNg1(new BigDecimal(9999));
//        timePart1.setNg2(new BigDecimal(0));
//        timePart1.setNg3(new BigDecimal(0));
//        timePart1.setNg4(new BigDecimal(0));
//        timePart1.setNg5(new BigDecimal(0));
//        timePart1.setNg6(new BigDecimal(0));
//        timePart1.setNg7(new BigDecimal(0));
//        timePart1.setNg8(new BigDecimal(0));
//        timePart1.setNg9(new BigDecimal(0));
//        timePart1.setNg10(new BigDecimal(0));
//        timePart1.setNg11(new BigDecimal(0));
//        timePart1.setNg12(new BigDecimal(0));
//        timeParts.add(timePart1);
//        data.setTimeParts(timeParts);
         oeeDatas.add(data);
        return oeeDatas;
    }
}
