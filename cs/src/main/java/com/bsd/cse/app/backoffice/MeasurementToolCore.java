/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.backoffice;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.machine.Machine;
import com.bsd.cse.model.backoffice.machine.MachineModel;
import com.bsd.cse.model.backoffice.machine.MachineModelType;
import com.bsd.cse.model.backoffice.measurement.MeasurementTool;
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
public class MeasurementToolCore {
    private Log LOG = LogFactory.getLog(MeasurementToolCore.class);

    public HashMap<String,Object> save(final MeasurementTool object,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                MeasurementTool objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (MeasurementTool)session.get(MeasurementTool.class, object.getId());
                }
                
                try
                {
                    if(objectDb != null )
                    {

                        objectDb.setBrand(object.getBrand());
                        objectDb.setModel(object.getModel());
                        objectDb.setCodeNo(object.getCodeNo());
                        objectDb.setDescription(object.getDescription());
                        objectDb.setSerialNo(object.getSerialNo());
                        objectDb.setIncomingDate(object.getIncomingDate());
                        objectDb.setCustomerDetail(object.getCustomerDetail());
                        objectDb.setResolution(object.getResolution());
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

    public Boolean checkCanDelete(final MeasurementTool object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                
//                    Criteria criteria = session.createCriteria(MeasurementTool.class);
//                    criteria.add(Restrictions.eq("id",object.getId()));
//                    criteria.setProjection(Projections.count("id"));
//                    Number count = (Number)criteria.uniqueResult();
//                    map.put("results",count.intValue()>0?Boolean.FALSE:Boolean.TRUE);
                map.put("results",Boolean.TRUE);
            }
            
        }.process();

        return (Boolean)map.get("results");
    }

    public HashMap<String,Object> delete(final MeasurementTool object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                MeasurementTool objectDb = null;
                
                if(object != null && object.getId() !=null)
                {
                    objectDb = (MeasurementTool)session.get(MeasurementTool.class, object.getId());
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

    public HashMap<String,Object> getList(final String serialNo,final String codeNo,final Long model) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(MeasurementTool.class);
                Criteria criteriaCount = session.createCriteria(MeasurementTool.class);

                criteria.addOrder(Order.asc("serialNo"));

                Conjunction con = null;
                if(!serialNo.trim().isEmpty())
                {
                    con = new Conjunction();
                    con.add(Restrictions.ilike("serialNo", "%"+serialNo+"%"));
                }

                if(!codeNo.trim().isEmpty())
                {

                    con = (con == null?new Conjunction():con);
                    con.add(Restrictions.ilike("codeNo", "%"+codeNo+"%"));
                }

                if(model != null)
                {
                    con = (con == null?new Conjunction():con);
                    con.add(Restrictions.eq("model.id", model));
                }

                if(con != null)
                {
                    criteria.add(con);
                    criteriaCount.add(con);
                }

                criteriaCount.setProjection(Projections.count("id"));
                Number allRecords = (Number)criteriaCount.uniqueResult();
                List<MeasurementTool> tools = criteria.list();
                if(tools != null && tools.size() > 0)
                {
                    for(MeasurementTool tool: tools)
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


    public List<MeasurementTool> getListFromCheckpoint(final Checkpoint checkpoint) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
//                Criteria criteria = session.createCriteria(Checkpoint.class);
//                criteria.add(Restrictions.eq("process.id",processId));
//                criteria.add(Restrictions.eq("part.id",partId));
//                criteria.add(Restrictions.eq("position",position));
//
//                criteria.setMaxResults(1);
//                Checkpoint checkpoint = (Checkpoint)criteria.uniqueResult();


                Criteria measurementCriteria = session.createCriteria(MeasurementTool.class)
                        .createAlias("model", "model");
                if(checkpoint != null)
                {
                    LOG.info("Checkpoint is not null");
                    measurementCriteria.add(Restrictions.eq("model.type.id",checkpoint.getMeasurementType().getId()));
                }
                else
                {
                    LOG.info("Checkpoint is null");
                }
                measurementCriteria.addOrder(Order.asc("serialNo"));
                List<MeasurementTool> measurementTools = measurementCriteria.list();
                if(measurementTools != null && measurementTools.size() > 0)
                {
                    for(MeasurementTool measurementTool: measurementTools)
                    {
                        measurementTool.toString();
                    }
                }

                LOG.info("measurementTools = "+measurementTools.size());
                map.put("results",measurementTools);


            }
        }.process();
        return (List<MeasurementTool>)map.get("results");
    }

    public List<MeasurementTool> getAllList() throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(MeasurementTool.class);

                criteria.addOrder(Order.asc("serialNo"));

                List<Machine> types = criteria.list();
                if(types != null && types.size() > 0)
                {
                    for(Machine type: types)
                    {
                        type.toString();
                    }
                }
                map.put("results",types);


            }
        }.process();

        return (List<MeasurementTool>)map.get("results");
    }  
}
