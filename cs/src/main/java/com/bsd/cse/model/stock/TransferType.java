/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.stock;

import com.bsd.cse.model.Footprint;


/**
 *
 * @author bento
 */
public class TransferType extends Footprint{
    private static final long serialVersionUID = 498246440999383850L;
    private Long id;    
    private String transferTypeName;
    private StockType stockType;
    private Long materialType;//material Type 1 = Fg,2 = Semi,3 = Raw

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StockType getStockType() {
        return stockType;
    }

    public void setStockType(StockType stockType) {
        this.stockType = stockType;
    }

    public String getTransferTypeName() {
        return transferTypeName;
    }

    public void setTransferTypeName(String transferTypeName) {
        this.transferTypeName = transferTypeName;
    }

    public Long getMaterialType() {
        return materialType;
    }

    public void setMaterialType(Long materialType) {
        this.materialType = materialType;
    }
    

    

}
