/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.report;

import java.math.BigDecimal;

/**
 *
 * @author bento
 */
public class OperationData {
    private String name;
    private String description;
    private BigDecimal op[] = new BigDecimal[12];
    private Long partId[] = null;
    private Long processId[] = null;
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

    public OperationData(String name,String description)
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

    public BigDecimal[] getOp() {
        return op;
    }

    public void setOp(BigDecimal[] op) {
        this.op = op;
    }

    public Long[] getPartId() {
        return partId;
    }

    public void setPartId(Long[] partId) {
        this.partId = partId;
    }

    public Long[] getProcessId() {
        return processId;
    }

    public void setProcessId(Long[] processId) {
        this.processId = processId;
    }       
    
}
