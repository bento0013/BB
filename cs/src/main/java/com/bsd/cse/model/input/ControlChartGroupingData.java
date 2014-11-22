/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.input;

import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author bento
 */
public class ControlChartGroupingData  implements Comparable<ControlChartGroupingData>
{
    private static Log LOG = LogFactory.getLog(ControlChartGroupingData.class);
    private Date measurementDate;
    private Long machineId;
//    private Integer period;
    private Integer numberSampling;

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public Date getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Date measurementDate) {
        this.measurementDate = measurementDate;
    }

    public Integer getNumberSampling() {
        return numberSampling;
    }

    public void setNumberSampling(Integer numberSampling) {
        this.numberSampling = numberSampling;
    }

//    public Integer getPeriod() {
//        return period;
//    }
//
//    public void setPeriod(Integer period) {
//        this.period = period;
//    }


    @Override
    public int compareTo(ControlChartGroupingData o) {
        if(!this.measurementDate.equals(o.getMeasurementDate()))
        {
            return this.measurementDate.compareTo(o.getMeasurementDate());
        }
        else
        {
//            if(!this.period.equals(o.getPeriod()))
//            {
//                return this.period.compareTo(o.getPeriod());
//            }
//            else
//            {
                return this.machineId.compareTo(o.getMachineId());
//            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ControlChartGroupingData other = (ControlChartGroupingData) obj;
        LOG.info("this.measurementDate = "+this.measurementDate);
        LOG.info("other.measurementDate = "+other.measurementDate);
        LOG.info("equals = "+this.measurementDate.equals(other.measurementDate));
        if (this.measurementDate != other.measurementDate && (this.measurementDate == null || !this.measurementDate.equals(other.measurementDate))) {
            return false;
        }
//        if (this.machineId != other.machineId && (this.machineId == null || !this.machineId.equals(other.machineId))) {
//            return false;
//        }
//        if (this.period != other.period && (this.period == null || !this.period.equals(other.period))) {
//            return false;
//        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.measurementDate != null ? this.measurementDate.hashCode() : 0);
        hash = 83 * hash + (this.machineId != null ? this.machineId.hashCode() : 0);
//        hash = 83 * hash + (this.period != null ? this.period.hashCode() : 0);
        return hash;
    }

    
   
}
