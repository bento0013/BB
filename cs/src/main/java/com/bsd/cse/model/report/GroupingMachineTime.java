/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.report;

import com.bsd.cse.model.backoffice.machine.Machine;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.backoffice.part.Process;
/**
 *
 * @author bento
 */
public class GroupingMachineTime {
    private Part part;
    private Machine machine;
    private Process process;
    private Integer period;

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GroupingMachineTime other = (GroupingMachineTime) obj;
        if (this.part != other.part && (this.part == null || !this.part.equals(other.part))) {
            return false;
        }
        if (this.machine != other.machine && (this.machine == null || !this.machine.equals(other.machine))) {
            return false;
        }
        if (this.process != other.process && (this.process == null || !this.process.equals(other.process))) {
            return false;
        }
        if (this.period != other.period && (this.period == null || !this.period.equals(other.period))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.part != null ? this.part.hashCode() : 0);
        hash = 71 * hash + (this.machine != null ? this.machine.hashCode() : 0);
        hash = 71 * hash + (this.process != null ? this.process.hashCode() : 0);
        hash = 71 * hash + (this.period != null ? this.period.hashCode() : 0);
        return hash;
    }

    
}
