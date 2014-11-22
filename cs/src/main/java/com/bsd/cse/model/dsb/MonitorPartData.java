/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.dsb;

import com.bsd.cse.model.backoffice.part.Part;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author bento
 */
public class MonitorPartData {
    private Part part;

    private BigDecimal store;
    private Date lastStoreUpdateDate;
    private List<ProductionProcess> productions = new ArrayList<ProductionProcess>();
    private BigDecimal storeFinish;
    private Date lastStoreFinishUpdateDate;

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public List<ProductionProcess> getProductions() {
        return productions;
    }

    public void setProductions(List<ProductionProcess> productions) {
        this.productions = productions;
    }

    public BigDecimal getStore() {
        return store;
    }

    public void setStore(BigDecimal store) {
        this.store = store;
    }

    public BigDecimal getStoreFinish() {
        return storeFinish;
    }

    public void setStoreFinish(BigDecimal storeFinish) {
        this.storeFinish = storeFinish;
    }

    public Date getLastStoreFinishUpdateDate() {
        return lastStoreFinishUpdateDate;
    }

    public void setLastStoreFinishUpdateDate(Date lastStoreFinishUpdateDate) {
        this.lastStoreFinishUpdateDate = lastStoreFinishUpdateDate;
    }

    public Date getLastStoreUpdateDate() {
        return lastStoreUpdateDate;
    }

    public void setLastStoreUpdateDate(Date lastStoreUpdateDate) {
        this.lastStoreUpdateDate = lastStoreUpdateDate;
    }

    
   
}
