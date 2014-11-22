/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.backoffice.material.raw;

import com.bsd.cse.model.Footprint;

/**
 *
 * @author bento
 */
public class RawType extends Footprint{
    private static final long serialVersionUID = -88426532524738970L;
    private Long id;
    private String name;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
       
}
