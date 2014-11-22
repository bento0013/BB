/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.security;

import com.bsd.cse.model.AbstractPojo;
import java.util.Date;

/**
 *
 * @author bento
 */
public class Function extends AbstractPojo{
    private static final long serialVersionUID = -8436219748475292071L;



    private Long id;
    private String functionName;
    private String functionDescription;
    private String command;
    private Boolean visible;
    private Function parent;
    private Integer orderNo;
    private Date createdDate;
    private Long createdBy;
    private Date updatedDate;
    private Long updatedBy;


    public Function()
    {

    }

    public Function(Long id)
    {
        this.id = id;
    }

     public Function(final Function o)
    {

        this(o.getId());
        this.functionName = o.getFunctionName();
        this.functionDescription = o.getFunctionDescription();
        this.visible = o.getVisible();
        this.orderNo = o.getOrderNo();
        this.command = o.getCommand();                        
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getFunctionDescription() {
        return functionDescription;
    }

    public void setFunctionDescription(String functionDescription) {
        this.functionDescription = functionDescription;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Function getParent() {
        return parent;
    }

    public void setParent(Function parent) {
        this.parent = parent;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }


    @Override
    public boolean equals(final Object that)
    {
        if ((null != that)
            && (null != this.id)
            /* this and that MUST be the same class */
            && this.getClass().isInstance(that)
            && that.getClass().isInstance(this))
        {
            return this.id.equals(((Function)that).id);
        }

        return false;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    @Override
    public int hashCode()
    {
        return (null == id) ? super.hashCode() : this.id.hashCode();
    }
    
}
