/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.report;

import java.util.Date;
import java.util.List;

/**
 *
 * @author bento
 */
public class OEEDetailData {
    private Date partDate;
    private String partName;
    private String machineName;
    private String partNo;
    private String shift;
    private String employeeId;
    private String employeeName;
    private String processName;
    private Integer workingHour;
    private List<OperationData> operations;
    private List<OperationData> totalParts;
    private List<OperationData> supportVariables;
    private List<OperationData> oeeFactors;
    private List<TimePartData> timeParts;
    private List<String> columnNames;

    public List<OperationData> getOeeFactors() {
        return oeeFactors;
    }

    public void setOeeFactors(List<OperationData> oeeFactors) {
        this.oeeFactors = oeeFactors;
    }

    public List<OperationData> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationData> operations) {
        this.operations = operations;
    }

    public List<OperationData> getSupportVariables() {
        return supportVariables;
    }

    public void setSupportVariables(List<OperationData> supportVariables) {
        this.supportVariables = supportVariables;
    }

    public List<TimePartData> getTimeParts() {
        return timeParts;
    }

    public void setTimeParts(List<TimePartData> timeParts) {
        this.timeParts = timeParts;
    }

    public List<OperationData> getTotalParts() {
        return totalParts;
    }

    public void setTotalParts(List<OperationData> totalParts) {
        this.totalParts = totalParts;
    }   

    public Date getPartDate() {
        return partDate;
    }

    public void setPartDate(Date partDate) {
        this.partDate = partDate;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

   

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Integer getWorkingHour() {
        return workingHour;
    }

    public void setWorkingHour(Integer workingHour) {
        this.workingHour = workingHour;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

  
}
