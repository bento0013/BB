/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.input;

import com.bsd.cse.model.Footprint;
import com.bsd.cse.model.backoffice.machine.Machine;
import com.bsd.cse.model.backoffice.part.NgReason;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.backoffice.part.Process;
import com.bsd.cse.model.security.User;
import java.util.Date;

/**
 *
 * @author bento
 */
public class ProductLine extends Footprint{
    private static final long serialVersionUID = 498246440999383850L;
    private Long id;
    private Date requestDate;
    private Part part;
    private Process process;    
    private User user;
    private Time time;
    private Machine machine;
    private Long bp;
    private Long wp;
    private Long ok;
    private Long ng;
    private NgReason ngReason;

    public Long getNg() {
        return ng;
    }

    public void setNg(Long ng) {
        this.ng = ng;
    }

    public Long getOk() {
        return ok;
    }

    public void setOk(Long ok) {
        this.ok = ok;
    }
    
    public Long getBp() {
        return bp;
    }

    public void setBp(Long bp) {
        this.bp = bp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Long getWp() {
        return wp;
    }

    public void setWp(Long wp) {
        this.wp = wp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public NgReason getNgReason() {
        return ngReason;
    }

    public void setNgReason(NgReason ngReason) {
        this.ngReason = ngReason;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductLine other = (ProductLine) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    

      

}
