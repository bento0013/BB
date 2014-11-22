/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.input;

import com.bsd.cse.model.Footprint;
import java.util.Date;

/**
 *
 * @author bento
 */
public class MeasurementTime extends Footprint{

    private Long id;
    private Integer timeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTimeName() {
        return timeName;
    }

    public void setTimeName(Integer timeName) {
        this.timeName = timeName;
    }
    
    
}
