/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.dsb;

import java.math.BigDecimal;

/**
 *
 * @author bento
 */
public class OEEDashboardData {
    private BigDecimal availability;
    private BigDecimal performance;
    private BigDecimal quality;
    private BigDecimal overall;

    public BigDecimal getAvailability() {
        return availability;
    }

    public void setAvailability(BigDecimal availability) {
        this.availability = availability;
    }

    public BigDecimal getOverall() {
        return overall;
    }

    public void setOverall(BigDecimal overall) {
        this.overall = overall;
    }

    public BigDecimal getPerformance() {
        return performance;
    }

    public void setPerformance(BigDecimal performance) {
        this.performance = performance;
    }

    public BigDecimal getQuality() {
        return quality;
    }

    public void setQuality(BigDecimal quality) {
        this.quality = quality;
    }
    
}
