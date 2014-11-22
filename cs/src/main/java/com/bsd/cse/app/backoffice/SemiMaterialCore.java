/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.backoffice;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.material.raw.RawMaterial;
import com.bsd.cse.model.backoffice.material.semi.SemiMaterial;
import com.bsd.cse.model.backoffice.part.Part;
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
public class SemiMaterialCore {
    private Log LOG = LogFactory.getLog(SemiMaterialCore.class);

    public HashMap<String,Object> save(final SemiMaterial object,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                SemiMaterial objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (SemiMaterial)session.get(SemiMaterial.class, object.getId());
                }

                try
                {
                    if(objectDb != null )
                    {

                        objectDb.setAmount(object.getAmount());
                        objectDb.setSemiType(object.getSemiType());
                        objectDb.setMinimumStock(object.getMinimumStock());
                        objectDb.setSemiName(object.getSemiName());
                        objectDb.setRawMaterial(object.getRawMaterial());
                        objectDb.setLength(object.getLength());
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

     public SemiMaterial getSemiMaterial(final Long rawId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {

                    SemiMaterial semiMaterial = (SemiMaterial)session.get(SemiMaterial.class,rawId);
                    if(semiMaterial != null)
                    {
                        semiMaterial.toString();
                    }
                    map.put("results",semiMaterial);
            }

        }.process();

        return (SemiMaterial)map.get("results");
    }

    public Boolean checkCanDelete(final SemiMaterial object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                
                Criteria criteria = session.createCriteria(Part.class);
                criteria.add(Restrictions.eq("semiMaterial.id",object.getId()));
                criteria.setProjection(Projections.count("id"));
                Number count = (Number)criteria.uniqueResult();
                map.put("results",count.intValue()>0?Boolean.FALSE:Boolean.TRUE);                
            }
            
        }.process();

        return (Boolean)map.get("results");
    }

    public HashMap<String,Object> delete(final SemiMaterial object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                SemiMaterial objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (SemiMaterial)session.get(SemiMaterial.class, object.getId());
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

    public HashMap<String,Object> getList(final String name,final Long type,final Long rawMaterial) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(SemiMaterial.class);
                Criteria criteriaCount = session.createCriteria(SemiMaterial.class);

                criteria.addOrder(Order.asc("semiName"));

                Conjunction con = null;
                if(!name.trim().isEmpty())
                {
                    con = new Conjunction();
                    con.add(Restrictions.ilike("semiName", "%"+name+"%"));
                }


                if(type != null)
                {
                    con = (con == null?new Conjunction():con);
                    con.add(Restrictions.eq("semiType.id", type));
                }

                if(rawMaterial != null)
                {
                    con = (con == null?new Conjunction():con);
                    con.add(Restrictions.eq("rawMaterial.id", rawMaterial));
                }

                if(con != null)
                {
                    criteria.add(con);
                    criteriaCount.add(con);
                }

                criteriaCount.setProjection(Projections.count("id"));
                Number allRecords = (Number)criteriaCount.uniqueResult();
                List<SemiMaterial> semis = criteria.list();
                if(semis != null && semis.size() > 0)
                {
                    for(SemiMaterial semi: semis)
                    {
                        semi.toString();
                    }
                }
                map.put("results",semis);
                map.put("allRecords", new Integer(allRecords.intValue()));

            }
        }.process();

        return map;
    }

    public List<SemiMaterial> getAllList() throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(SemiMaterial.class);

                criteria.addOrder(Order.asc("semiName"));
               
                List<SemiMaterial> types = criteria.list();
                if(types != null && types.size() > 0)
                {
                    for(SemiMaterial type: types)
                    {
                        type.toString();
                    }
                }
                LOG.info("types = "+types.size());
                map.put("results",types);

            }
        }.process();

        return (List<SemiMaterial>)map.get("results");
    }
}
