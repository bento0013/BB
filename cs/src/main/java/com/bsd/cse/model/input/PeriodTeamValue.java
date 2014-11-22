/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.input;

import com.bsd.cse.model.AbstractPojo;

/**
 *
 * @author bento
 */
public class PeriodTeamValue extends AbstractPojo{
    private static final long serialVersionUID = 5863228427640897586L;
    
    private Integer period;
    private String teamName;

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

     
}
