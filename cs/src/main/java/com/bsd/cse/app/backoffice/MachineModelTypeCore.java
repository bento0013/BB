/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.backoffice;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.department.Department;
import com.bsd.cse.model.backoffice.machine.MachineModel;
import com.bsd.cse.model.backoffice.machine.MachineModelType;
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
public class MachineModelTypeCore {
    private Log LOG = LogFactory.getLog(MachineModelTypeCore.class);

    public HashMap<String,Object> save(final MachineModelType object,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                MachineModelType objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (MachineModelType)session.get(MachineModelType.class, object.getId());
                }
                try
                {
                    if(objectDb != null )
                    {

                        objectDb.setName(object.getName());
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
                    LOG.error(">>>>>>>>>>>>> "+e.toString());
                    throw new Exception(e.toString());
                }

                map.put("results",Boolean.TRUE);
                map.put("resultId",object.getId());
                LOG.info("results = "+(Boolean)map.get("results"));
                LOG.info("groupId = "+(Long)map.get("departmentId"));
            }
        }.process();

        return map;
    }

    public Boolean checkCanDelete(final MachineModelType object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                
                    Criteria criteria = session.createCriteria(MachineModel.class);
                    criteria.add(Restrictions.eq("type.id",object.getId()));
                    criteria.setProjection(Projections.count("id"));
                    Number count = (Number)criteria.uniqueResult();
                    map.put("results",count.intValue()>0?Boolean.FALSE:Boolean.TRUE);
//                map.put("results", Boolean.TRUE);
            }
            
        }.process();

        return (Boolean)map.get("results");
    }

    public HashMap<String,Object> delete(final MachineModelType object,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                MachineModelType objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (MachineModelType)session.get(MachineModelType.class, object.getId());
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

    public HashMap<String,Object> getList(final String typeName) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(MachineModelType.class);
                Criteria criteriaCount = session.createCriteria(MachineModelType.class);

                criteria.addOrder(Order.asc("name"));

                Conjunction con = null;
                if(!typeName.trim().isEmpty())
                {
                    con = new Conjunction();
                    con.add(Restrictions.ilike("name", "%"+typeName+"%"));
                }

                if(con != null)
                {
                    criteria.add(con);
                    criteriaCount.add(con);
                }

                criteriaCount.setProjection(Projections.count("id"));
                Number allRecords = (Number)criteriaCount.uniqueResult();
                List<MachineModelType> types = criteria.list();
                if(types != null && types.size() > 0)
                {
                    for(MachineModelType type: types)
                    {
                        type.toString();
                    }
                }
                LOG.info("department = "+types.size());
                map.put("results",types);
                map.put("allRecords", new Integer(allRecords.intValue()));

            }
        }.process();

        return map;
    }

    public List<MachineModelType> getAllList() throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(MachineModelType.class);
                Criteria criteriaCount = session.createCriteria(MachineModelType.class);

                criteria.addOrder(Order.asc("name"));                

                criteriaCount.setProjection(Projections.count("id"));
                Number allRecords = (Number)criteriaCount.uniqueResult();
                List<MachineModelType> types = criteria.list();
                if(types != null && types.size() > 0)
                {
                    for(MachineModelType type: types)
                    {
                        type.toString();
                    }
                }
                LOG.info("department = "+types.size());
                map.put("results",types);
                map.put("allRecords", new Integer(allRecords.intValue()));

            }
        }.process();

        return (List<MachineModelType>)map.get("results");
    }  
}
