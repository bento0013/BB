/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsd.cse.zk.grid;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;

/**
 *
 * @author thanasith
 */
public class CriteriaObject implements Serializable{
    private String orderBy;
    private List<String> orderBys = new ArrayList<String>();
    private List<Boolean> ascendings = new ArrayList<Boolean>();
    private Class entityClass;
    private Conjunction criteria = new Conjunction();
    private Boolean ascending = Boolean.TRUE;
    private HashMap<String,String> aliasNames = new HashMap<String,String>();
    private HashMap<String,Integer> joinNames = new HashMap<String,Integer>();
    private String defaultSort;  
    public static Integer FULL_JOIN = Criteria.FULL_JOIN;
    public static Integer INNER_JOIN = Criteria.INNER_JOIN;
    public static Integer LEFT_JOIN = Criteria.LEFT_JOIN;
    

    public CriteriaObject(Class entityClass)
    {
        this.entityClass = entityClass;
    }
    
    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
    
    public void addCriteria(Criterion condition)
    {
        criteria.add(condition);
    }

    public Conjunction getCriteria() {
        return criteria;
    }   

    public Class getEntityClass() {
        return entityClass;
    }
    
    public HashMap<String, String> getAliasNames() {
        return aliasNames;
    }

    public void addAliasNames(String name,String alias,Integer joinOption) {
        aliasNames.put(name, alias);
        joinNames.put(name,joinOption);
    }

    public Boolean getAscending() {
        return ascending;
    }

    public void setAscending(Boolean ascending) {
        this.ascending = ascending;
    }

    public String getDefaultSort() {
        return defaultSort;
    }

    public void setDefaultSort(String defaultSort) {
        this.defaultSort = defaultSort;
    }

    public HashMap<String, Integer> getJoinNames() {
        return joinNames;
    }

    public List<Boolean> getAscendings() {
        return ascendings;
    }

    public void setAscendings(List<Boolean> ascendings) {
        this.ascendings = ascendings;
    }

    public List<String> getOrderBys() {
        return orderBys;
    }

    public void setOrderBys(List<String> orderBys) {
        this.orderBys = orderBys;
    }
                                        
}
