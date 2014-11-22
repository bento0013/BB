/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.backoffice;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.material.raw.RawMaterial;
import com.bsd.cse.model.backoffice.material.raw.RawType;
import com.bsd.cse.model.backoffice.material.semi.SemiMaterial;
import com.bsd.cse.model.security.UserInfo;
import java.math.BigDecimal;
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
public class RawMaterialCore {
    private Log LOG = LogFactory.getLog(RawMaterialCore.class);

    public HashMap<String,Object> save(final RawMaterial object,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                RawMaterial objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (RawMaterial)session.get(RawMaterial.class, object.getId());
                }
                try
                {
                    if(objectDb != null )
                    {

                        objectDb.setAmount(object.getAmount());
                        objectDb.setMinimumStock(object.getMinimumStock());
                        objectDb.setLength(object.getLength());
                        objectDb.setRawType(object.getRawType());
                        objectDb.setRawName(object.getRawName());
                        objectDb.setSize(object.getSize());
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

    public RawMaterial getRawMaterial(final Long rawId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {

                    RawMaterial rawMaterial = (RawMaterial)session.get(RawMaterial.class,rawId);
                    if(rawMaterial != null)
                    {
                        rawMaterial.toString();
                    }
                    map.put("results",rawMaterial);
            }

        }.process();

        return (RawMaterial)map.get("results");
    }

    public Boolean checkCanDelete(final RawMaterial object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                
                    Criteria criteria = session.createCriteria(SemiMaterial.class);
                    criteria.add(Restrictions.eq("rawMaterial.id",object.getId()));
                    criteria.setProjection(Projections.count("id"));
                    Number count = (Number)criteria.uniqueResult();
                    map.put("results",count.intValue()>0?Boolean.FALSE:Boolean.TRUE);                
            }
            
        }.process();

        return (Boolean)map.get("results");
    }

    public HashMap<String,Object> delete(final RawMaterial object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                RawMaterial objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (RawMaterial)session.get(RawMaterial.class, object.getId());
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

    public HashMap<String,Object> getList(final String name,final Long type) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(RawMaterial.class);
                Criteria criteriaCount = session.createCriteria(RawMaterial.class);

                criteria.addOrder(Order.asc("rawName"));

                Conjunction con = null;
                if(!name.trim().isEmpty())
                {
                    con = new Conjunction();
                    con.add(Restrictions.ilike("rawName", "%"+name+"%"));
                }
             

                if(type != null)
                {
                    con = (con == null?new Conjunction():con);
                    con.add(Restrictions.eq("rawType.id", type));
                }

                if(con != null)
                {
                    criteria.add(con);
                    criteriaCount.add(con);
                }

                criteriaCount.setProjection(Projections.count("id"));
                Number allRecords = (Number)criteriaCount.uniqueResult();
                List<RawMaterial> raws = criteria.list();
                if(raws != null && raws.size() > 0)
                {
                    for(RawMaterial raw: raws)
                    {
                        raw.toString();
                    }
                }         
                map.put("results",raws);
                map.put("allRecords", new Integer(allRecords.intValue()));

            }
        }.process();

        return map;
    }

    public List<RawMaterial> getAllList() throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(RawMaterial.class);
                Criteria criteriaCount = session.createCriteria(RawMaterial.class);

                criteria.addOrder(Order.asc("rawName"));

                criteriaCount.setProjection(Projections.count("id"));
                Number allRecords = (Number)criteriaCount.uniqueResult();
                List<RawMaterial> types = criteria.list();
                if(types != null && types.size() > 0)
                {
                    for(RawMaterial type: types)
                    {
                        type.toString();
                    }
                }
                LOG.info("types = "+types.size());
                map.put("results",types);
                map.put("allRecords", new Integer(allRecords.intValue()));

            }
        }.process();

        return (List<RawMaterial>)map.get("results");
    }
}
