/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.input;

import com.bsd.cse.model.Footprint;
import com.bsd.cse.model.backoffice.machine.Machine;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.backoffice.part.Process;
import java.util.Date;

/**
 *
 * @author bento
 */
public class MachineTime extends Footprint{
    private static final long serialVersionUID = 7925393274989880831L;

    private Long id;
    private Machine machine;
    private Process process;
    private Integer period;
    private Part part;
    private Date scheduleTime;
    private Long apTime;
    private Long bpTime;
    private Long cpTime;
    private Long dpTime;
    private Long epTime;
    private Long fp1Time;
    private Long fp2Time;
    private Long fp3Time;
    private Long hpTime;
    private Long ipTime;
    private Long jpTime;
    private Long kp1Time;
    private Long kp2Time;
    private Long kp3Time;
    private Long mpTime;    

    public Long getApTime() {
        return apTime;
    }

    public void setApTime(Long apTime) {
        this.apTime = apTime;
    }

    public Long getBpTime() {
        return bpTime;
    }

    public void setBpTime(Long bpTime) {
        this.bpTime = bpTime;
    }

    public Long getCpTime() {
        return cpTime;
    }

    public void setCpTime(Long cpTime) {
        this.cpTime = cpTime;
    }

    public Long getDpTime() {
        return dpTime;
    }

    public void setDpTime(Long dpTime) {
        this.dpTime = dpTime;
    }

    public Long getEpTime() {
        return epTime;
    }

    public void setEpTime(Long epTime) {
        this.epTime = epTime;
    }

    public Long getFp1Time() {
        return fp1Time;
    }

    public void setFp1Time(Long fp1Time) {
        this.fp1Time = fp1Time;
    }

    public Long getFp2Time() {
        return fp2Time;
    }

    public void setFp2Time(Long fp2Time) {
        this.fp2Time = fp2Time;
    }

    public Long getFp3Time() {
        return fp3Time;
    }

    public void setFp3Time(Long fp3Time) {
        this.fp3Time = fp3Time;
    }

    public Long getHpTime() {
        return hpTime;
    }

    public void setHpTime(Long hpTime) {
        this.hpTime = hpTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIpTime() {
        return ipTime;
    }

    public void setIpTime(Long ipTime) {
        this.ipTime = ipTime;
    }

    public Long getJpTime() {
        return jpTime;
    }

    public void setJpTime(Long jpTime) {
        this.jpTime = jpTime;
    }

    public Long getKp1Time() {
        return kp1Time;
    }

    public void setKp1Time(Long kp1Time) {
        this.kp1Time = kp1Time;
    }

    public Long getKp2Time() {
        return kp2Time;
    }

    public void setKp2Time(Long kp2Time) {
        this.kp2Time = kp2Time;
    }

    public Long getKp3Time() {
        return kp3Time;
    }

    public void setKp3Time(Long kp3Time) {
        this.kp3Time = kp3Time;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Long getMpTime() {
        return mpTime;
    }

    public void setMpTime(Long mpTime) {
        this.mpTime = mpTime;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
        
    public Date getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Date scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }   
    
}
