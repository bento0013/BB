/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.input;

import com.bsd.cse.model.AbstractPojo;

/**
 *
 * @author bento
 */
public class RamdomMeasurementValue extends AbstractPojo{
    private static final long serialVersionUID = -5028184018129563296L;
    private Long time;
//    private Integer period;
    private Long machineId;

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

//    public Integer getPeriod() {
//        return period;
//    }
//
//    public void setPeriod(Integer period) {
//        this.period = period;
//    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }      
}
