/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bsd.cse.zk.grid;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.zk.grid.GridSearchResult;
import com.bsd.cse.zk.grid.CriteriaObject;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.engine.TypedValue;

/**
 *
 * @author thanasith
 */
public class HibernateSearchObject<E> implements Serializable{
    private static Log LOG = LogFactory.getLog(HibernateSearchObject.class);
    private CriteriaObject criteriaObject ;
    private QueryObject queryObject;    
    private Integer startIndex;
    private Integer pageSize;    
    private Class<E> entityClass;    
    
    public HibernateSearchObject(CriteriaObject _criteriaObject)
    {
        this.entityClass = _criteriaObject.getEntityClass();
        this.criteriaObject = _criteriaObject;
    }
    
    public HibernateSearchObject(QueryObject _queryObject)
    {        
        this.entityClass = _queryObject.getEntityClass();
        this.queryObject = _queryObject;
    }
    
    public GridSearchResult<E> getSearchResult(Integer startIndex,Integer pageSize) throws Exception
    {
        GridSearchResult<E> gridSearchResult = null;
        if(criteriaObject != null)
        {
            gridSearchResult = getCriteriaSearchResult(startIndex,pageSize);
        }
        
        if(queryObject != null)
        {
            gridSearchResult = getQuerySearchResult(startIndex,pageSize);
        }
               
        return gridSearchResult;
    }
    
    private GridSearchResult<E> getCriteriaSearchResult(final Integer startIndex,final Integer pageSize) throws Exception
    {
        final HashMap<String,Object> hashMap = new HashMap<String,Object>();
        (new TransactionalProcessor(LOG)
        {
            @Override
            public void process(Session session, Transaction tx) throws Exception {   
                Criteria criteria = session.createCriteria(entityClass);
                Criteria criteriaCount = session.createCriteria(entityClass);
                                
                Iterator iter = criteriaObject.getAliasNames().keySet().iterator();
                while(iter.hasNext())
                {
                    String key = (String)iter.next();                    
                    criteria = criteria.createAlias(key, (String)criteriaObject.getAliasNames().get(key),(Integer)criteriaObject.getJoinNames().get(key));
                    criteriaCount = criteriaCount.createAlias(key, (String)criteriaObject.getAliasNames().get(key),(Integer)criteriaObject.getJoinNames().get(key));
                }
                criteria.add(criteriaObject.getCriteria());
                criteriaCount.add(criteriaObject.getCriteria());
                
//                Field[] fields = entityClass.getDeclaredFields();                
//                
//                for(Field field : fields)
//                {
//                    LOG.info("field = "+field.getName());
//                }                
                criteriaCount.setProjection(Projections.count(criteriaObject.getDefaultSort()));

                if(criteriaObject.getAscendings().size() == criteriaObject.getOrderBys().size())
                {
                    for(int index = 0 ; index < criteriaObject.getAscendings().size();index++)
                    {

                        if(criteriaObject.getAscendings().get(index))
                        {
                            criteria.addOrder(Order.asc(criteriaObject.getOrderBys().get(index)));
                        }
                        else
                        {
                            criteria.addOrder(Order.desc(criteriaObject.getOrderBys().get(index)));
                        }

                    }
                }
                
                if(criteriaObject.getOrderBy() != null)
                {
                    if(criteriaObject.getAscending())
                    {
                        criteria.addOrder(Order.asc(criteriaObject.getOrderBy()));
                    }
                    else
                    {
                        criteria.addOrder(Order.desc(criteriaObject.getOrderBy()));
                    }
                }
                
                if(!criteriaObject.getOrderBy().equals(criteriaObject.getDefaultSort()) 
                        && criteriaObject.getDefaultSort() != null)
                {                    
                    criteria.addOrder(Order.asc(criteriaObject.getDefaultSort()));                   
                }
                LOG.info("-----------------> criteria startIndex = "+startIndex);
                LOG.info("-----------------> criteria pageSize = "+pageSize);
                criteria.setFirstResult(startIndex);
                criteria.setMaxResults(pageSize);
                
                List<E> list = criteria.list();      
                for(E data : list)
                {
                    data.toString();
                }
                Number count = (Number)criteriaCount.uniqueResult();
                hashMap.put("count",new Integer(count.intValue()));
                hashMap.put("results",list);                                                                                                
            }                       
        }).process();
        GridSearchResult<E> gridSearchResult = new GridSearchResult<E>();
        gridSearchResult.setList((List<E>)hashMap.get("results"));
        gridSearchResult.setCount((Integer)hashMap.get("count"));
        return gridSearchResult;
    }
    
    private GridSearchResult<E> getQuerySearchResult(final Integer startIndex,final Integer pageSize) throws Exception
    {
        final HashMap<String,Object> hashMap = new HashMap<String,Object>();
        (new TransactionalProcessor(LOG)
        {
            @Override
            public void process(Session session, Transaction tx) throws Exception {   
                Query query = session.createQuery(queryObject.getHqlQuery()
                        + (queryObject.getOrderBy() != null?" order by "+queryObject.getOrderBy()+ (queryObject.getAscending()?" asc":" desc"):"") );                
                Query queryCount = session.createQuery("select count(*) "+queryObject.getHqlQuery());
                                                
                Iterator iter = queryObject.getParameters().keySet().iterator();
                while(iter.hasNext())
                {
                    String key = (String)iter.next();
                    query.setParameter(key, queryObject.getParameters().get(key));
                    queryCount.setParameter(key, queryObject.getParameters().get(key));
                }                                      
                
                
                query.setFirstResult(startIndex);
                query.setMaxResults(pageSize);
                
                List<E> list = query.list();

                for(E data : list)
                {
                    data.toString();
                }

                Number count = (Number)queryCount.uniqueResult();
                hashMap.put("count",new Integer(count.intValue()));
                hashMap.put("results",list);              
            }                       
        }).process();
        GridSearchResult<E> gridSearchResult = new GridSearchResult<E>();
        gridSearchResult.setList((List<E>)hashMap.get("results"));
        gridSearchResult.setCount((Integer)hashMap.get("count"));
        return gridSearchResult;
    }   
    
    public void clearSort()
    {
        if(criteriaObject != null)
        {
            criteriaObject.setOrderBy("");
        }
        
        if(queryObject != null)
        {
            queryObject.setOrderBy("");
        }
    }
    
    public void addSort(String orderBy,Boolean directionFlag)
    {                     
        if(criteriaObject != null)
        {
            criteriaObject.setOrderBy(orderBy);
            criteriaObject.setAscending(directionFlag);
        }
        
        if(queryObject != null)
        {
            queryObject.setOrderBy(orderBy);     
            queryObject.setAscending(directionFlag);
        }
    }

              
        
}
