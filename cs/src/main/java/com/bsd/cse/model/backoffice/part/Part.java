/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.backoffice.part;

import com.bsd.cse.model.Footprint;
import com.bsd.cse.model.backoffice.customer.Customer;
import com.bsd.cse.model.backoffice.material.semi.SemiMaterial;

/**
 *
 * @author bento
 */
public class Part extends Footprint{
    private static final long serialVersionUID = 7945775652376836937L;
    private Long id;
    private String partNo;
    private String partName;
    private Customer customer;
    private SemiMaterial semiMaterial;
    private Long minimumStock;
    private Long amount;
    private Long numProcesses;
    private String imagePath;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

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

    public Long getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(Long minimumStock) {
        this.minimumStock = minimumStock;
    }

    public Long getNumProcesses() {
        return numProcesses;
    }

    public void setNumProcesses(Long numProcesses) {
        this.numProcesses = numProcesses;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public SemiMaterial getSemiMaterial() {
        return semiMaterial;
    }

    public void setSemiMaterial(SemiMaterial semiMaterial) {
        this.semiMaterial = semiMaterial;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Part other = (Part) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }


    
}
