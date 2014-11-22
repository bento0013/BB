/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.report;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author bento
 */
public class ControlData {
    private String partName;
    private String partNo;
    private String modelName;
    private String customerName;
    private String processName;
    private String machineNo;
    private String specificControl;
    private String measurementName;
    private String measurementCode;
    private String periodTime[];
    private Date periodDate[];
    private BigDecimal usl;
    private BigDecimal lsl;
    private BigDecimal xcl;
    private BigDecimal xucl;
    private BigDecimal xlcl;
    private BigDecimal rcl;
    private BigDecimal rucl;
    private BigDecimal rlcl;
    private BigDecimal xbar;
    private BigDecimal rbar;
    private BigDecimal cp;
    private BigDecimal cpu;
    private BigDecimal cpl;
    private BigDecimal cpk;
    private BigDecimal holm;
    private List<ControlSerieData> samplingDatas;
    private List<ControlSerieData> summaryDatas1;
    private List<ControlSerieData> summaryDatas2;
    private List<ControlChartData> chart1Datas;
    private List<ControlChartData> chart2Datas;

    public List<ControlChartData> getChart1Datas() {
        return chart1Datas;
    }

    public void setChart1Datas(List<ControlChartData> chart1Datas) {
        this.chart1Datas = chart1Datas;
    }

    public List<ControlChartData> getChart2Datas() {
        return chart2Datas;
    }

    public void setChart2Datas(List<ControlChartData> chart2Datas) {
        this.chart2Datas = chart2Datas;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMachineNo() {
        return machineNo;
    }

    public void setMachineNo(String machineNo) {
        this.machineNo = machineNo;
    }

    public String getMeasurementCode() {
        return measurementCode;
    }

    public void setMeasurementCode(String measurementCode) {
        this.measurementCode = measurementCode;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public void setMeasurementName(String measurementName) {
        this.measurementName = measurementName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public Date[] getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(Date[] periodDate) {
        this.periodDate = periodDate;
    }

    public String[] getPeriodTime() {
        return periodTime;
    }

    public void setPeriodTime(String[] periodTime) {
        this.periodTime = periodTime;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public List<ControlSerieData> getSamplingDatas() {
        return samplingDatas;
    }

    public void setSamplingDatas(List<ControlSerieData> samplingDatas) {
        this.samplingDatas = samplingDatas;
    }

    public String getSpecificControl() {
        return specificControl;
    }

    public void setSpecificControl(String specificControl) {
        this.specificControl = specificControl;
    }

    public List<ControlSerieData> getSummaryDatas1() {
        return summaryDatas1;
    }

    public void setSummaryDatas1(List<ControlSerieData> summaryDatas1) {
        this.summaryDatas1 = summaryDatas1;
    }

    public List<ControlSerieData> getSummaryDatas2() {
        return summaryDatas2;
    }

    public void setSummaryDatas2(List<ControlSerieData> summaryDatas2) {
        this.summaryDatas2 = summaryDatas2;
    }   

    public BigDecimal getLsl() {
        return lsl;
    }

    public void setLsl(BigDecimal lsl) {
        this.lsl = lsl;
    }

    public BigDecimal getRbar() {
        return rbar;
    }

    public void setRbar(BigDecimal rbar) {
        this.rbar = rbar;
    }

    public BigDecimal getRcl() {
        return rcl;
    }

    public void setRcl(BigDecimal rcl) {
        this.rcl = rcl;
    }

    public BigDecimal getRlcl() {
        return rlcl;
    }

    public void setRlcl(BigDecimal rlcl) {
        this.rlcl = rlcl;
    }

    public BigDecimal getRucl() {
        return rucl;
    }

    public void setRucl(BigDecimal rucl) {
        this.rucl = rucl;
    }

    public BigDecimal getUsl() {
        return usl;
    }

    public void setUsl(BigDecimal usl) {
        this.usl = usl;
    }

    public BigDecimal getXbar() {
        return xbar;
    }

    public void setXbar(BigDecimal xbar) {
        this.xbar = xbar;
    }

    public BigDecimal getXcl() {
        return xcl;
    }

    public void setXcl(BigDecimal xcl) {
        this.xcl = xcl;
    }

    public BigDecimal getXlcl() {
        return xlcl;
    }

    public void setXlcl(BigDecimal xlcl) {
        this.xlcl = xlcl;
    }

    public BigDecimal getXucl() {
        return xucl;
    }

    public void setXucl(BigDecimal xucl) {
        this.xucl = xucl;
    }

    public BigDecimal getCp() {
        return cp;
    }

    public void setCp(BigDecimal cp) {
        this.cp = cp;
    }

    public BigDecimal getCpk() {
        return cpk;
    }

    public void setCpk(BigDecimal cpk) {
        this.cpk = cpk;
    }

    public BigDecimal getCpl() {
        return cpl;
    }

    public void setCpl(BigDecimal cpl) {
        this.cpl = cpl;
    }

    public BigDecimal getCpu() {
        return cpu;
    }

    public void setCpu(BigDecimal cpu) {
        this.cpu = cpu;
    }

    public BigDecimal getHolm() {
        return holm;
    }

    public void setHolm(BigDecimal holm) {
        this.holm = holm;
    }

    
   
    
}
