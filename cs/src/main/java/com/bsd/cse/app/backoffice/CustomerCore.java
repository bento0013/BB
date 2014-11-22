/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.backoffice;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.customer.Customer;
import com.bsd.cse.model.security.UserInfo;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;


/**
 *
 * @author bento
 */
public class CustomerCore {
    private Log LOG = LogFactory.getLog(CustomerCore.class);

    public HashMap<String,Object> save(final Customer object,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Customer objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (Customer)session.get(Customer.class, object.getId());
                }
                try
                {
                    if(objectDb != null )
                    {

                        objectDb.setAddress(object.getAddress());
                        objectDb.setName(object.getName());
                        objectDb.setSurname(object.getSurname());
                        objectDb.setTel(object.getTel());
                        UserInfo userInfo = new UserInfo();
                        userInfo.setId(userId);
                        objectDb.setUpdatedBy(userInfo);
                        objectDb.setUpdatedDate(new Date());
                        session.saveOrUpdate(objectDb);
                        session.flush();
                    }
                    else
                    {
                        UserInfo userInfo = new UserInfo();
                        userInfo.setId(userId);
                        object.setCreatedBy(userInfo);
                        object.setCreatedDate(new Date());
                        session.save(object);
                        session.flush();
                    }
                }
                catch(Exception e)
                {
                    LOG.error(e.getMessage(),e);
                    throw new Exception(e.toString());
                }

                map.put("results",Boolean.TRUE);
                map.put("resultId",object.getId());
                LOG.info("results = "+(Boolean)map.get("results"));
                LOG.info("resultId = "+(Long)map.get("resultId"));
            }
        }.process();

        return map;
    }

    public Boolean checkCanDelete(final Customer object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                
//                    Criteria criteria = session.createCriteria(Customer.class);
//                    criteria.add(Restrictions.eq("rawType.id",object.getId()));
//                    criteria.setProjection(Projections.count("id"));
//                    Number count = (Number)criteria.uniqueResult();
                    map.put("results",Boolean.TRUE);  
            }
            
        }.process();

        return (Boolean)map.get("results");
    }

    public HashMap<String,Object> delete(final Customer object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Customer objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (Customer)session.get(Customer.class, object.getId());
                }
                
                if(objectDb != null )
                {                    
                    session.delete(objectDb);
                    map.put("results",Boolean.TRUE);
                }
                else
                {
                    map.put("results",Boolean.FALSE);
                }

                
               
                LOG.info("results = "+(Boolean)map.get("results"));
            }
        }.process();

        return map;
    }

    public HashMap<String,Object> getList(final String cusName) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Customer.class);
                Criteria criteriaCount = session.createCriteria(Customer.class);

                criteria.addOrder(Order.asc("name"));

                Conjunction con = null;
                if(!cusName.trim().isEmpty())
                {
                    con = new Conjunction();
                    con.add(Restrictions.ilike("name", "%"+cusName+"%"));
                }

                if(con != null)
                {
                    criteria.add(con);
                    criteriaCount.add(con);
                }

                criteriaCount.setProjection(Projections.count("id"));
                Number allRecords = (Number)criteriaCount.uniqueResult();
                List<Customer> customers = criteria.list();
                if(customers != null && customers.size() > 0)
                {
                    for(Customer customer: customers)
                    {
                        customer.toString();
                    }
                }
                LOG.info("customers = "+customers.size());
                map.put("results",customers);
                map.put("allRecords", new Integer(allRecords.intValue()));

            }
        }.process();

        return map;
    }

    public List<Customer> getAllList() throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Customer.class);

                criteria.addOrder(Order.asc("name"));
                criteria.addOrder(Order.asc("surname"));

                List<Customer> types = criteria.list();
                if(types != null && types.size() > 0)
                {
                    for(Customer type: types)
                    {
                        type.toString();
                    }
                }
                LOG.info("types = "+types.size());
                map.put("results",types);

            }
        }.process();

        return (List<Customer>)map.get("results");
    }
}
