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
public class StockType extends Footprint{
    private static final long serialVersionUID = 498246440999383850L;
    private Long id;    
    private String stockTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockTypeName() {
        return stockTypeName;
    }

    public void setStockTypeName(String stockTypeName) {
        this.stockTypeName = stockTypeName;
    }  

}
