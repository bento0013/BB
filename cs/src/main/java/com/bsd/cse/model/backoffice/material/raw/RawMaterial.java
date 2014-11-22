/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.backoffice.material.raw;

import com.bsd.cse.model.Footprint;
import java.math.BigDecimal;

/**
 *
 * @author bento
 */
public class RawMaterial extends Footprint{
    private static final long serialVersionUID = -6904027321494878503L;
    private Long id;
    private String rawName;
    private RawType rawType;    
    private Long amount;
    private BigDecimal length;
    private Long minimumStock;
    private BigDecimal size;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    public RawType getRawType() {
        return rawType;
    }

    public void setRawType(RawType rawType) {
        this.rawType = rawType;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

}
