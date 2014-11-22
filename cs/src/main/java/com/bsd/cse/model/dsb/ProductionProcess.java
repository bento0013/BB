/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.dsb;
import com.bsd.cse.model.backoffice.part.Process;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author bento
 */
public class ProductionProcess {
    private Process process;
    private BigDecimal ok = new BigDecimal(0);
    private BigDecimal ng = new BigDecimal(0);
    private BigDecimal wip = new BigDecimal(0);
    private Date lastUpdateDate;

    public BigDecimal getNg() {
        return ng;
    }

    public void setNg(BigDecimal ng) {
        this.ng = ng;
    }

    public BigDecimal getOk() {
        return ok;
    }

    public void setOk(BigDecimal ok) {
        this.ok = ok;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public BigDecimal getWip() {
        return wip;
    }

    public void setWip(BigDecimal wip) {
        this.wip = wip;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductionProcess other = (ProductionProcess) obj;
        if (this.process != other.process && (this.process == null || !this.process.equals(other.process))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.process != null ? this.process.hashCode() : 0);
        return hash;
    }
    
    
}
