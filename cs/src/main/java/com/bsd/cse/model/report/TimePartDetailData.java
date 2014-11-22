/*
 * To change this template, choose Tools | Templates
 * and oken the template in the editor.
 */

package com.bsd.cse.model.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bento
 */
public class TimePartDetailData {
    private String name;
    private String description;
    private List<BigDecimal> ok = new ArrayList<BigDecimal>();
    private List<BigDecimal> ng = new ArrayList<BigDecimal>();

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

    public List<BigDecimal> getNg() {
        return ng;
    }

    public void setNg(List<BigDecimal> ng) {
        this.ng = ng;
    }

    public List<BigDecimal> getOk() {
        return ok;
    }

    public void setOk(List<BigDecimal> ok) {
        this.ok = ok;
    }

}
