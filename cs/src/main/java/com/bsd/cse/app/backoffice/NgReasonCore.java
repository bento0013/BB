/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.backoffice;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.part.NgReason;
import com.bsd.cse.model.input.NgProductLineRecord;
import com.bsd.cse.model.input.ProductLine;
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
public class NgReasonCore {
    private Log LOG = LogFactory.getLog(NgReasonCore.class);

    public HashMap<String,Object> save(final NgReason object,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                
                NgReason objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (NgReason)session.get(NgReason.class, object.getId());
                }
                try
                {
                    if(objectDb != null )
                    {

                        objectDb.setName(object.getName());
                        objectDb.setUpdatedBy(userId);
                        objectDb.setUpdatedDate(new Date());
                        session.saveOrUpdate(objectDb);
                        session.flush();
                    }
                    else
                    {
                        object.setCreatedBy(userId);
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
                map.put("objectId",object.getId());
                LOG.info("results = "+(Boolean)map.get("results"));
                LOG.info("groupId = "+(Long)map.get("objectId"));
            }
        }.process();

        return map;
    }

    public Boolean checkCanDelete(final NgReason object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {

                    Criteria criteria = session.createCriteria(NgProductLineRecord.class);
                    criteria.add(Restrictions.eq("ngReason.id",object.getId()));
                    criteria.setProjection(Projections.count("id"));
                    Number count = (Number)criteria.uniqueResult();

                    LOG.info("count.intValue() = "+count.intValue());   
                    map.put("results",count.intValue()>0?Boolean.FALSE:Boolean.TRUE);
//                    map.put("results",Boolean.TRUE);
            }

        }.process();

        return (Boolean)map.get("results");
    }

    public HashMap<String,Object> delete(final NgReason object,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                NgReason objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (NgReason)session.get(NgReason.class, object.getId());
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

    public HashMap<String,Object> getList(final String depName) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(NgReason.class);
                Criteria criteriaCount = session.createCriteria(NgReason.class);

                criteria.addOrder(Order.asc("name"));

                Conjunction con = null;
                if(!depName.trim().isEmpty())
                {
                    con = new Conjunction();
                    con.add(Restrictions.ilike("name", "%"+depName+"%"));
                }
                
                if(con != null)
                {
                    criteria.add(con);
                    criteriaCount.add(con);
                }

                criteriaCount.setProjection(Projections.count("id"));
                Number allRecords = (Number)criteriaCount.uniqueResult();
                List<NgReason> ngReasons = criteria.list();
                if(ngReasons != null && ngReasons.size() > 0)
                {
                    for(NgReason ngReason: ngReasons)
                    {
                        ngReason.toString();
                    }
                }
                LOG.info("ngReason = "+ngReasons.size());
                map.put("results",ngReasons);
                map.put("allRecords", new Integer(allRecords.intValue()));

            }
        }.process();

        return map;
    }

    public List<NgReason> getAllList() throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(NgReason.class);

                criteria.addOrder(Order.asc("name"));

                List<NgReason> types = criteria.list();
                if(types != null && types.size() > 0)
                {
                    for(NgReason type: types)
                    {
                        type.toString();
                    }
                }
                map.put("results",types);


            }
        }.process();

        return (List<NgReason>)map.get("results");
    }  
}
