/*
 * To change this template, choose Tools | Templates
 * and oken the template in the editor.
 */

package com.bsd.cse.model.report;

import java.math.BigDecimal;

/**
 *
 * @author bento
 */
public class TimePartData {
    private String name;
    private String description;
    private BigDecimal ok[] = new BigDecimal[12];    
    private BigDecimal ng[] = new BigDecimal[12];    

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

    public BigDecimal[] getNg() {
        return ng;
    }

    public void setNg(BigDecimal[] ng) {
        this.ng = ng;
    }

    public BigDecimal[] getOk() {
        return ok;
    }

    public void setOk(BigDecimal[] ok) {
        this.ok = ok;
    }

    


}
