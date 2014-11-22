/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.input;

import com.bsd.cse.model.backoffice.part.Checkpoint;
import java.util.List;

/**
 *
 * @author bento
 */
public class MeasurementCheckpoint {
    private Checkpoint checkpoint;
    private List<MeasurementPart> measurementParts;

    public List<MeasurementPart> getMeasurementParts() {
        return measurementParts;
    }

    public void setMeasurementParts(List<MeasurementPart> measurementParts) {
        this.measurementParts = measurementParts;
    }

    public Checkpoint getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(Checkpoint checkpoint) {
        this.checkpoint = checkpoint;
    }
    
}
