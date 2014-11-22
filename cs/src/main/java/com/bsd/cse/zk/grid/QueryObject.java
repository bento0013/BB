/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsd.cse.zk.grid;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author thanasith
 */
public class QueryObject implements Serializable{
    
    private Class entityClass;
    private Boolean ascending = Boolean.TRUE;
    private String orderBy;
    private String hqlQuery;
    private HashMap<String,Object> parameters = new HashMap<String,Object>();
    
    public QueryObject(Class _entityClass,String _hqlQuery)
    {
        this.hqlQuery = _hqlQuery;
        this.entityClass = _entityClass;
    }
            
    

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getHqlQuery() {
        return hqlQuery;
    }

    public HashMap<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Boolean getAscending() {
        return ascending;
    }

    public void setAscending(Boolean ascending) {
        this.ascending = ascending;
    }

    public Class getEntityClass() {
        return entityClass;
    }
    
        
    
}
