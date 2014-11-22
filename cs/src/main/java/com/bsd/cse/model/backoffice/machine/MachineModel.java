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
public class MachineModel extends Footprint{
    private static final long serialVersionUID = -8258522045599156504L;
    private Long id;
    private String name;
    private MachineModelType type;
    private String imagePath;    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MachineModelType getType() {
        return type;
    }

    public void setType(MachineModelType type) {
        this.type = type;
    }      
    
}
