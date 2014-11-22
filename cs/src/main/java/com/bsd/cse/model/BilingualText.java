/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model;

import java.io.Serializable;

/**
 *
 * @author bento
 */
public class BilingualText implements Serializable{
    private String en;
    private String lo;

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getLo() {
        return lo;
    }

    public void setLo(String lo) {
        this.lo = lo;
    }    
}
