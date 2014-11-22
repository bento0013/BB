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
public class ControlReportData {
    public static List<ControlData> getData()
    {
        List<ControlData> controlDatas  = new ArrayList<ControlData>();
        ControlData data = new ControlData();
        data.setCustomerName("Customer Data");
        data.setMachineNo("Machine No");
        data.setMeasurementCode("M Code");
        data.setMeasurementName("Measurement Name");
        data.setModelName("Model Name");
        data.setPartName("Part Name");
        data.setPartNo("Part No");
        data.setProcessName("Process Name");
        data.setLsl(new BigDecimal("9.999"));
        data.setRcl(new BigDecimal("9.999"));
        data.setRucl(new BigDecimal("9.999"));
        data.setRlcl(new BigDecimal("9.999"));
        data.setXcl(new BigDecimal("9.999"));
        data.setXlcl(new BigDecimal("9.999"));
        data.setXucl(new BigDecimal("9.999"));
        data.setXbar(new BigDecimal("9.999"));
        data.setRbar(new BigDecimal("9.999"));
        data.setUsl(new BigDecimal("9.999"));
        List<String> periodTimes = new ArrayList<String>();
        List<Date> periodDates = new ArrayList<Date>();
        List<ControlSerieData> samplingDatas = new ArrayList<ControlSerieData>();
        List<ControlSerieData> summaryDatas = new ArrayList<ControlSerieData>();
        List<ControlChartData> chart1Datas = new ArrayList<ControlChartData>();
        List<ControlChartData> chart2Datas = new ArrayList<ControlChartData>();
        for(int index =1;index<=15;index++)
        {
            periodTimes.add(new String("Night"));
            periodDates.add(new Date());
            
        }

//        for(int index =1;index<=5;index++)
//        {
//            ControlSerieData serie = new ControlSerieData();
//            serie.setName(String.valueOf(index));
//            serie.setValue1(new BigDecimal(index));
//            serie.setValue2(new BigDecimal(index));
//            serie.setValue3(new BigDecimal(index));
//            serie.setValue4(new BigDecimal(index));
//            serie.setValue5(new BigDecimal(index));
//            serie.setValue6(new BigDecimal(index));
//            serie.setValue7(new BigDecimal(index));
//            serie.setValue8(new BigDecimal(index));
//            serie.setValue9(new BigDecimal(index));
//            serie.setValue10(new BigDecimal(index));
//            serie.setValue11(new BigDecimal(index));
//            serie.setValue12(new BigDecimal(index));
//            serie.setValue13(new BigDecimal(index));
//            serie.setValue14(new BigDecimal(index));
//            serie.setValue15(new BigDecimal(index));
//            samplingDatas.add(serie);
//        }

        String info[] = {"SUM","X","R","Inspector","Checked","Judgement"};
//        for(int index =1;index<=6;index++)
//        {
//            ControlSerieData serie = new ControlSerieData();
//            serie.setName(String.valueOf(info[index-1]));
//            serie.setValue1(new BigDecimal(index));
//            serie.setValue2(new BigDecimal(index));
//            serie.setValue3(new BigDecimal(index));
//            serie.setValue4(new BigDecimal(index));
//            serie.setValue5(new BigDecimal(index));
//            serie.setValue6(new BigDecimal(index));
//            serie.setValue7(new BigDecimal(index));
//            serie.setValue8(new BigDecimal(index));
//            serie.setValue9(new BigDecimal(index));
//            serie.setValue10(new BigDecimal(index));
//            serie.setValue11(new BigDecimal(index));
//            serie.setValue12(new BigDecimal(index));
//            serie.setValue13(new BigDecimal(index));
//            serie.setValue14(new BigDecimal(index));
//            serie.setValue15(new BigDecimal(index));
//            summaryDatas.add(serie);
//        }

        for(int index =1;index<=15;index++)
        {
            ControlChartData serie = new ControlChartData();
            serie.setName(String.valueOf(index));
            serie.setValue(new BigDecimal(index));
            chart1Datas.add(serie);
            chart2Datas.add(serie);
        }
        
        data.setPeriodTime(periodTimes.toArray(new String[0]));
        data.setPeriodDate(periodDates.toArray(new Date[0]));
        data.setSamplingDatas(samplingDatas);
//        data.setSummaryDatas(summaryDatas);
        data.setChart1Datas(chart1Datas);
        data.setChart2Datas(chart2Datas);

        
        controlDatas.add(data);
        return controlDatas;
    }
}
