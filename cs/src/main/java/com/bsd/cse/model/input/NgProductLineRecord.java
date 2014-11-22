/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.input;

import com.bsd.cse.model.Footprint;
import com.bsd.cse.model.backoffice.part.NgReason;

/**
 *
 * @author bento
 */
public class NgProductLineRecord extends Footprint{
    private static final long serialVersionUID = 6842509961065428549L;
    private Long id;
    private Long recordId;
    private ProductLine productLine;
    private Long ngAmount;
    private NgReason ngReason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNgAmount() {
        return ngAmount;
    }

    public void setNgAmount(Long ngAmount) {
        this.ngAmount = ngAmount;
    }

    public NgReason getNgReason() {
        return ngReason;
    }

    public void setNgReason(NgReason ngReason) {
        this.ngReason = ngReason;
    }

    public ProductLine getProductLine() {
        return productLine;
    }

    public void setProductLine(ProductLine productLine) {
        this.productLine = productLine;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NgProductLineRecord other = (NgProductLineRecord) obj;
        if (this.recordId != other.recordId && (this.recordId == null || !this.recordId.equals(other.recordId))) {
            return false;
        }
        if (this.productLine != other.productLine && (this.productLine == null || !this.productLine.equals(other.productLine))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.recordId != null ? this.recordId.hashCode() : 0);
        hash = 29 * hash + (this.productLine != null ? this.productLine.hashCode() : 0);
        return hash;
    }

    
    
}
