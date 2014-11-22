/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.backoffice;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.machine.Machine;
import com.bsd.cse.model.backoffice.machine.MachineModel;
import com.bsd.cse.model.backoffice.machine.MachineModelType;
import com.bsd.cse.model.backoffice.part.ProcessPart;
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
public class MachineModelCore {
    private Log LOG = LogFactory.getLog(MachineModelCore.class);

    public HashMap<String,Object> save(final MachineModel object,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                MachineModel objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (MachineModel)session.get(MachineModel.class, object.getId());
                }
                
                try
                {
                    if(objectDb != null )
                    {

                        objectDb.setName(object.getName());
                        objectDb.setType(object.getType());
                        objectDb.setImagePath(object.getImagePath());
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

    public Boolean checkCanDelete(final MachineModel object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                
                    Criteria criteria = session.createCriteria(Machine.class);
                    criteria.add(Restrictions.eq("model.id",object.getId()));
                    criteria.setProjection(Projections.count("id"));
                    Number count = (Number)criteria.uniqueResult();
                    map.put("results",count.intValue()>0?Boolean.FALSE:Boolean.TRUE);

//                    if((Boolean)map.get("results"))
//                    {
//                        criteria = session.createCriteria(ProcessPart.class);
//                        criteria.add(Restrictions.eq("machineModel.id",object.getId()));
//                        criteria.setProjection(Projections.count("machineModel.id"));
//                        count = (Number)criteria.uniqueResult();
//                        map.put("results",count.intValue()>0?Boolean.FALSE:Boolean.TRUE);
//                    }
//                    map.put("results",Boolean.TRUE);
            }
            
        }.process();

        return (Boolean)map.get("results");
    }

    public HashMap<String,Object> delete(final MachineModel object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                MachineModel objectDb = null;
                
                if(object != null && object.getId() !=null)
                {
                    objectDb = (MachineModel)session.get(MachineModel.class, object.getId());
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

    public HashMap<String,Object> getList(final String modelName,final Long modelType) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(MachineModel.class);
                Criteria criteriaCount = session.createCriteria(MachineModel.class);

                criteria.addOrder(Order.asc("name"));

                Conjunction con = null;
                if(!modelName.trim().isEmpty())
                {
                    con = new Conjunction();
                    con.add(Restrictions.ilike("name", "%"+modelName+"%"));
                }

                if(modelType != null)
                {
                    con = (con == null?new Conjunction():con);
                    con.add(Restrictions.eq("type.id", modelType));
                }

                if(con != null)
                {
                    criteria.add(con);
                    criteriaCount.add(con);
                }

                criteriaCount.setProjection(Projections.count("id"));
                Number allRecords = (Number)criteriaCount.uniqueResult();
                List<MachineModel> models = criteria.list();
                if(models != null && models.size() > 0)
                {
                    for(MachineModel model: models)
                    {
                        model.toString();
                    }
                }
                LOG.info("results = "+models.size());
                map.put("results",models);
                map.put("allRecords", new Integer(allRecords.intValue()));

            }
        }.process();

        return map;
    }

    public List<MachineModel> getAllList() throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(MachineModel.class);
                Criteria criteriaCount = session.createCriteria(MachineModel.class);

                criteria.addOrder(Order.asc("name"));

                criteriaCount.setProjection(Projections.count("id"));
                Number allRecords = (Number)criteriaCount.uniqueResult();
                List<MachineModel> types = criteria.list();
                if(types != null && types.size() > 0)
                {
                    for(MachineModel type: types)
                    {
                        type.toString();
                    }
                }                
                map.put("results",types);
                map.put("allRecords", new Integer(allRecords.intValue()));

            }
        }.process();

        return (List<MachineModel>)map.get("results");
    }  
}
