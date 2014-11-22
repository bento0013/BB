/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model;

import com.bsd.cse.model.security.User;
import com.bsd.cse.model.security.UserInfo;
import java.util.Date;

/**
 *
 * @author bento
 */
public class Footprint extends AbstractPojo{
    protected Date createdDate;
    protected UserInfo createdBy;
    protected Date updatedDate;
    protected UserInfo updatedBy;
    

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UserInfo getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserInfo createdBy) {
        this.createdBy = createdBy;
    }

    public UserInfo getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(UserInfo updatedBy) {
        this.updatedBy = updatedBy;
    }
    
    
}
