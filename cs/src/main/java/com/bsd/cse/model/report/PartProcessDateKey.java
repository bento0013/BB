/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.report;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author bento
 */
public class PartProcessDateKey implements Serializable{
    private static final long serialVersionUID = 7980283543212846170L;
    private Long processId;
    private Date date;
    private Long partId;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PartProcessDateKey other = (PartProcessDateKey) obj;
        if (this.processId != other.processId && (this.processId == null || !this.processId.equals(other.processId))) {
            return false;
        }
        if (this.date != other.date && (this.date == null || !this.date.equals(other.date))) {
            return false;
        }
        if (this.partId != other.partId && (this.partId == null || !this.partId.equals(other.partId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.processId != null ? this.processId.hashCode() : 0);
        hash = 71 * hash + (this.date != null ? this.date.hashCode() : 0);
        hash = 71 * hash + (this.partId != null ? this.partId.hashCode() : 0);
        return hash;
    }

    

    

}
