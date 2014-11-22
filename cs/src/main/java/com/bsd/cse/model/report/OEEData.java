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
public class OEEData {
    private Date requestDate;
    private String partName;
    private String machineName;
    private String partNo;
    private String shift;
    private String employeeId;
    private String employeeName;
    private Integer workingHour;
    private Long partId[] = null;
    private Long processId[] = null;
    private String part[] = {"","","","","","","","","","","",""};
    private String process[] = {"","","","","","","","","","","",""};
    private List<OperationData> operations;    
    private List<OperationData> totalParts;
    private List<OperationData> supportVariables;
    private List<OperationData> oeeFactors;
    private List<TimePartData> timeParts;

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

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
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

    public String[] getPart() {
        return part;
    }

    public void setPart(String[] part) {
        this.part = part;
    }

    public String[] getProcess() {
        return process;
    }

    public void setProcess(String[] process) {
        this.process = process;
    }

    public Long[] getPartId() {
        return partId;
    }

    public void setPartId(Long[] partId) {
        this.partId = partId;
    }

    public Long[] getProcessId() {
        return processId;
    }

    public void setProcessId(Long[] processId) {
        this.processId = processId;
    }      

}
