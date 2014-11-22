/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.backoffice.measurement;

import com.bsd.cse.model.Footprint;

/**
 *
 * @author bento
 */
public class MeasurementToolModel  extends Footprint{
    private static final long serialVersionUID = 1382038554867018894L;
    private Long id;
    private MeasurementToolType type;
    private String description;
    private String brand;
    private String name;
    private String imagePath;

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MeasurementToolType getType() {
        return type;
    }

    public void setType(MeasurementToolType type) {
        this.type = type;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    

    
    
}
