/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.backoffice.machine;

import com.bsd.cse.model.Footprint;

/**
 *
 * @author bento
 */
public class MachineModelType  extends Footprint{
    private static final long serialVersionUID = 1382038554867018894L;
    private Long id;
    private String name;   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }   
    
}
