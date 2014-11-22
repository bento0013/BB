/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.backoffice.part;

import com.bsd.cse.model.Footprint;
import com.bsd.cse.model.backoffice.measurement.MeasurementToolModel;
import com.bsd.cse.model.backoffice.measurement.MeasurementToolType;
import java.math.BigDecimal;

/**
 *
 * @author bento
 */
public class Checkpoint extends Footprint{
    private Long id;
    private static final long serialVersionUID = -9065245881742967646L;
    private Part part;
//    private MeasurementToolModel measurementModel;
    private MeasurementToolType measurementType;
    private Process process;
    private Long position;
    private String checkpointName;
    private BigDecimal minDuration;
    private BigDecimal maxDuration;
    private String imagePath;
    private CheckpointType checkpointType;
    private CheckpointUnit checkpointUnit;
    private Boolean spc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public BigDecimal getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(BigDecimal maxDuration) {
        this.maxDuration = maxDuration;
    }
 
    public BigDecimal getMinDuration() {
        return minDuration;
    }

    public void setMinDuration(BigDecimal minDuration) {
        this.minDuration = minDuration;
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

    public String getCheckpointName() {
        return checkpointName;
    }

    public void setCheckpointName(String checkpointName) {
        this.checkpointName = checkpointName;
    }

    

//    public MeasurementToolModel getMeasurementModel() {
//        return measurementModel;
//    }
//
//    public void setMeasurementModel(MeasurementToolModel measurementModel) {
//        this.measurementModel = measurementModel;
//    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public MeasurementToolType getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(MeasurementToolType measurementType) {
        this.measurementType = measurementType;
    }

    public CheckpointType getCheckpointType() {
        return checkpointType;
    }

    public void setCheckpointType(CheckpointType checkpointType) {
        this.checkpointType = checkpointType;
    }

    public CheckpointUnit getCheckpointUnit() {
        return checkpointUnit;
    }

    public void setCheckpointUnit(CheckpointUnit checkpointUnit) {
        this.checkpointUnit = checkpointUnit;
    }

    public Boolean getSpc() {
        return spc;
    }

    public void setSpc(Boolean spc) {
        this.spc = spc;
    }
    
       
}
