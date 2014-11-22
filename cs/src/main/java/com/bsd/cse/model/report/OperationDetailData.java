/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bento
 */
public class OperationDetailData {
    private String name;
    private String description;
    private List<BigDecimal> op = new ArrayList<BigDecimal>();
//    private BigDecimal op2;
//    private BigDecimal op3;
//    private BigDecimal op4;
//    private BigDecimal op5;
//    private BigDecimal op6;
//    private BigDecimal op7;
//    private BigDecimal op8;
//    private BigDecimal op9;
//    private BigDecimal op10;
//    private BigDecimal op11;
//    private BigDecimal op12;

    public OperationDetailData(String name,String description)
    {
        this.name = name;
        this.description = description;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BigDecimal> getOp() {
        return op;
    }

    public void setOp(List<BigDecimal> op) {
        this.op = op;
    }

    

    

    
}
