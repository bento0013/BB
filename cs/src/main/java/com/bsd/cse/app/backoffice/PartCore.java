/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.backoffice;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.part.Checkpoint;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.backoffice.part.ProcessPart;
import com.bsd.cse.model.security.UserInfo;
import com.bsd.cse.model.security.User;
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
public class PartCore {
    private Log LOG = LogFactory.getLog(PartCore.class);

    public HashMap<String,Object> save(final Part object,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Part objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (Part)session.get(Part.class, object.getId());
                }

                try
                {
                    if(objectDb != null )
                    {

                        objectDb.setAmount(object.getAmount());
                        objectDb.setCustomer(object.getCustomer());
                        objectDb.setImagePath(object.getImagePath());
                        objectDb.setMinimumStock(object.getMinimumStock());
                        objectDb.setPartName(object.getPartName());
                        objectDb.setPartNo(object.getPartNo());
                        objectDb.setSemiMaterial(object.getSemiMaterial());
                        objectDb.setNumProcesses(object.getNumProcesses());
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

    public Boolean checkCanDelete(final Part object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                
                    Criteria criteria = session.createCriteria(ProcessPart.class);
                    criteria.add(Restrictions.eq("part.id",object.getId()));
                    criteria.setProjection(Projections.count("part.id"));
                    Number count = (Number)criteria.uniqueResult();
                    map.put("results",count.intValue()>0?Boolean.FALSE:Boolean.TRUE);

                    if((Boolean)map.get("results"))
                    {
                        criteria = session.createCriteria(Checkpoint.class);
                        criteria.add(Restrictions.eq("part.id",object.getId()));
                        criteria.setProjection(Projections.count("part.id"));
                        count = (Number)criteria.uniqueResult();
                        map.put("results",count.intValue()>0?Boolean.FALSE:Boolean.TRUE);
                    }
//                    map.put("results",Boolean.TRUE);
            }
            
        }.process();

        return (Boolean)map.get("results");
    }

    public HashMap<String,Object> delete(final Part object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Part objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (Part)session.get(Part.class, object.getId());
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

    public HashMap<String,Object> getList(final String partNo,final String partName) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Part.class);
                Criteria criteriaCount = session.createCriteria(Part.class);

                criteria.addOrder(Order.asc("partNo"));

                Conjunction con = null;
                if(!partNo.trim().isEmpty())
                {
                    con = new Conjunction();
                    con.add(Restrictions.ilike("partNo", "%"+partNo+"%"));
                }

                if(!partName.trim().isEmpty())
                {

                    con = (con == null?new Conjunction():con);
                    con.add(Restrictions.ilike("partName", "%"+partName+"%"));
                }
               
                if(con != null)
                {
                    criteria.add(con);
                    criteriaCount.add(con);
                }

                criteriaCount.setProjection(Projections.count("id"));
                Number allRecords = (Number)criteriaCount.uniqueResult();
                List<Part> tools = criteria.list();
                if(tools != null && tools.size() > 0)
                {
                    for(Part tool: tools)
                    {
                        tool.toString();
                    }
                }
                LOG.info("results = "+tools.size());
                map.put("results",tools);
                map.put("allRecords", new Integer(allRecords.intValue()));

            }
        }.process();

        return map;
    }

    public List<Part> getList(final List<Long> partIds) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Part.class);
                Criteria criteriaCount = session.createCriteria(Part.class);

                criteria.addOrder(Order.asc("partNo"));

                Conjunction con = null;
                if(partIds != null)
                {
                    con = new Conjunction();
                    con.add(Restrictions.in("id", partIds));
                }
                
                if(con != null)
                {
                    criteria.add(con);
                    criteriaCount.add(con);
                }

                criteriaCount.setProjection(Projections.count("id"));
                Number allRecords = (Number)criteriaCount.uniqueResult();
                List<Part> tools = criteria.list();
                if(tools != null && tools.size() > 0)
                {
                    for(Part tool: tools)
                    {
                        tool.toString();
                    }
                }
                LOG.info("results = "+tools.size());
                map.put("results",tools);
                map.put("allRecords", new Integer(allRecords.intValue()));

            }
        }.process();

        return (List<Part>)map.get("results");
    }

    public List<Part> getAllList() throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Part.class);

                criteria.addOrder(Order.asc("partNo"));
               
                List<Part> types = criteria.list();
                if(types != null && types.size() > 0)
                {
                    for(Part type: types)
                    {
                        type.toString();
                    }
                }
                map.put("results",types);


            }
        }.process();

        return (List<Part>)map.get("results");
    }  
}
