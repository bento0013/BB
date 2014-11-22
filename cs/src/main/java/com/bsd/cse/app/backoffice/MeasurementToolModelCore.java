/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.backoffice;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.measurement.MeasurementTool;
import com.bsd.cse.model.backoffice.measurement.MeasurementToolModel;
import com.bsd.cse.model.backoffice.part.Checkpoint;
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
public class MeasurementToolModelCore {
    private Log LOG = LogFactory.getLog(MeasurementToolModelCore.class);

    public HashMap<String,Object> save(final MeasurementToolModel object,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                MeasurementToolModel objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (MeasurementToolModel)session.get(MeasurementToolModel.class, object.getId());
                }
                
                try
                {
                    if(objectDb != null )
                    {

                        objectDb.setName(object.getName());
                        objectDb.setType(object.getType());
                        objectDb.setBrand(object.getBrand());
                        objectDb.setDescription(object.getDescription());
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

    public Boolean checkCanDelete(final MeasurementToolModel object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                
                    Criteria criteria = session.createCriteria(MeasurementTool.class);
                    criteria.add(Restrictions.eq("model.id",object.getId()));
                    criteria.setProjection(Projections.count("model.id"));
                    Number count = (Number)criteria.uniqueResult();
                    map.put("results",count.intValue()>0?Boolean.FALSE:Boolean.TRUE);                    
//                    if((Boolean)map.get("results"))
//                    {
//                        criteria = session.createCriteria(Checkpoint.class);
//                        criteria.add(Restrictions.eq("measurementModel.id",object.getId()));
//                        criteria.setProjection(Projections.count("measurementModel.id"));
//                        count = (Number)criteria.uniqueResult();
//                        map.put("results",count.intValue()>0?Boolean.FALSE:Boolean.TRUE);
//                    }
            }
            
        }.process();

        return (Boolean)map.get("results");
    }

    public HashMap<String,Object> delete(final MeasurementToolModel object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                MeasurementToolModel objectDb = null;
                
                if(object != null && object.getId() !=null)
                {
                    objectDb = (MeasurementToolModel)session.get(MeasurementToolModel.class, object.getId());
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
                Criteria criteria = session.createCriteria(MeasurementToolModel.class);
                Criteria criteriaCount = session.createCriteria(MeasurementToolModel.class);

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
                List<MeasurementToolModel> models = criteria.list();
                if(models != null && models.size() > 0)
                {
                    for(MeasurementToolModel model: models)
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

    public List<MeasurementToolModel> getAllList() throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(MeasurementToolModel.class);
                Criteria criteriaCount = session.createCriteria(MeasurementToolModel.class);

                criteria.addOrder(Order.asc("name"));

                criteriaCount.setProjection(Projections.count("id"));
                Number allRecords = (Number)criteriaCount.uniqueResult();
                List<MeasurementToolModel> types = criteria.list();
                if(types != null && types.size() > 0)
                {
                    for(MeasurementToolModel type: types)
                    {
                        type.toString();
                    }
                }
                LOG.info("types = "+types.size());
                map.put("results",types);
                map.put("allRecords", new Integer(allRecords.intValue()));

            }
        }.process();

        return (List<MeasurementToolModel>)map.get("results");
    }
}
