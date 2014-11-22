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
public class ControlSerieData {
    private String name;
    private BigDecimal value[] = new BigDecimal[30];
    private String valueStr[] = new String[30];
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal[] getValue() {
        return value;
    }

    public void setValue(BigDecimal value[]) {
        this.value = value;
    }

    public String[] getValueStr() {
        return valueStr;
    }

    public void setValueStr(String[] valueStr) {
        this.valueStr = valueStr;
    }
       
}
