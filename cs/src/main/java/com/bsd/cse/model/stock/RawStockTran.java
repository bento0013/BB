/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.stock;

import com.bsd.cse.model.Footprint;
import com.bsd.cse.model.backoffice.customer.Customer;
import com.bsd.cse.model.backoffice.material.raw.RawMaterial;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.security.User;

/**
 *
 * @author bento
 */
public class RawStockTran extends Footprint{
    private static final long serialVersionUID = 498246440999383850L;
    private Long id;    
    private Part part;    
    private Long quantity;
    private User requester;
    private User recorder;
    private TransferType transferType;
    private Long balance;
   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public User getRecorder() {
        return recorder;
    }

    public void setRecorder(User recorder) {
        this.recorder = recorder;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    } 

    public TransferType getTransferType() {
        return transferType;
    }

    public void setTransferType(TransferType transferType) {
        this.transferType = transferType;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    


}
