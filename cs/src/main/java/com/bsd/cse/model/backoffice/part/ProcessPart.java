/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.backoffice.part;

import com.bsd.cse.model.AbstractPojo;
import com.bsd.cse.model.Footprint;
import com.bsd.cse.model.backoffice.machine.MachineModel;

/**
 *
 * @author bento
 */
public class ProcessPart extends Footprint{
    private Long id;
    private static final long serialVersionUID = -1914099380227072382L;    
    private Process process;
    private String imagePath;
    private Part part;
//    private MachineModel machineModel;

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

//    public MachineModel getMachineModel() {
//        return machineModel;
//    }
//
//    public void setMachineModel(MachineModel machineModel) {
//        this.machineModel = machineModel;
//    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }   
        
}
