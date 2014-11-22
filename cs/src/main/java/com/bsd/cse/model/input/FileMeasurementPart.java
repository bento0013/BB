/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.input;

import com.bsd.cse.model.Footprint;
import com.bsd.cse.model.backoffice.machine.Machine;
import com.bsd.cse.model.backoffice.measurement.MeasurementTool;
import com.bsd.cse.model.backoffice.part.Checkpoint;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.backoffice.part.Process;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author bento
 */
public class FileMeasurementPart extends Footprint{
    private static final long serialVersionUID = 498246440999383850L;
    private Date measurementDate;
    private Long id;   
    private Part part;
    private Process process;           
    private Machine machine;
    private String filename;
//    private Integer period;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    } 

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }   
    
    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public Date getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Date measurementDate) {
        this.measurementDate = measurementDate;
    }   

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

//    public Integer getPeriod() {
//        return period;
//    }
//
//    public void setPeriod(Integer period) {
//        this.period = period;
//    }
           
}
