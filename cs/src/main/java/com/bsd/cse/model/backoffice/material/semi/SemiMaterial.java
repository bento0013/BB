/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.backoffice.material.semi;

import com.bsd.cse.model.Footprint;
import com.bsd.cse.model.backoffice.material.raw.RawMaterial;
import java.math.BigDecimal;

/**
 *
 * @author bento
 */
public class SemiMaterial extends Footprint{
    private static final long serialVersionUID = -9105966254505834215L;
    private Long id;
    private String semiName;
    private SemiType semiType;
    private BigDecimal size;
    private Long amount;
    private BigDecimal length;
    private Long minimumStock;
    private RawMaterial rawMaterial;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getSemiName() {
        return semiName;
    }

    public void setSemiName(String semiName) {
        this.semiName = semiName;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(Long minimumStock) {
        this.minimumStock = minimumStock;
    }    
 
    public SemiType getSemiType() {
        return semiType;
    }

    public void setSemiType(SemiType semiType) {
        this.semiType = semiType;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }
    
}
