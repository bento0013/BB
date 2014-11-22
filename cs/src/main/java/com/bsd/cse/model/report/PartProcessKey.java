/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.report;

import java.io.Serializable;

/**
 *
 * @author bento
 */
public class PartProcessKey implements Serializable{
    private static final long serialVersionUID = 7980283543212846170L;
    private Long partId;
    private Long processId;

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }   

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    
}
