/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.backoffice;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.measurement.MeasurementToolModel;
import com.bsd.cse.model.backoffice.part.Checkpoint;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.backoffice.part.Process;
import com.bsd.cse.model.security.UserInfo;
import freemarker.template.utility.Collections12;
import java.util.Collections;
import java.util.Comparator;
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
public class CheckpointCore {
    private Log LOG = LogFactory.getLog(CheckpointCore.class);

    public HashMap<String,Object> save(final Checkpoint object,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Checkpoint objectDb = null;
                if(object != null )
                {
                    Criteria criteria = session.createCriteria(Checkpoint.class);
                    criteria.add(Restrictions.eq("id", object.getId()));
                    criteria.setMaxResults(1);
                    objectDb = (Checkpoint)criteria.uniqueResult();
                }

                try
                {
                    if(objectDb != null )
                    {
                        objectDb.setPart(object.getPart());
                        objectDb.setProcess(object.getProcess());
                        objectDb.setPosition(object.getPosition());
//                        objectDb.setMeasurementModel(object.getMeasurementModel());
                        objectDb.setCheckpointType(object.getCheckpointType());
                        objectDb.setCheckpointUnit(object.getCheckpointUnit());
                        objectDb.setImagePath(object.getImagePath());
                        objectDb.setMaxDuration(object.getMaxDuration());
                        objectDb.setMinDuration(object.getMinDuration());
                        objectDb.setCheckpointName(object.getCheckpointName());
                        objectDb.setSpc(object.getSpc());
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

    public Boolean checkCanDelete(final Checkpoint object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                
//                    Criteria criteria = session.createCriteria(Checkpoint.class);
//                    criteria.add(Restrictions.eq("rawType.id",object.getId()));
//                    criteria.setProjection(Projections.count("id"));
//                    Number count = (Number)criteria.uniqueResult();
//                    map.put("results",count.intValue()>0?Boolean.FALSE:Boolean.TRUE);
                map.put("results",Boolean.TRUE);
            }
            
        }.process();

        return (Boolean)map.get("results");
    }

    public HashMap<String,Object> delete(final Checkpoint object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Checkpoint objectDb = null;
                if(object != null)
                {
                    Criteria criteria = session.createCriteria(Checkpoint.class);
                    criteria.add(Restrictions.eq("id", object.getId()));                    
                    criteria.setMaxResults(1);
                    objectDb = (Checkpoint)criteria.uniqueResult();
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

    public HashMap<String,Object> getList(final Part part,final Process process,final MeasurementToolModel measurementModel) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Checkpoint.class);
                Criteria criteriaCount = session.createCriteria(Checkpoint.class);

                criteria.addOrder(Order.asc("process.id"));

                Conjunction con = null;
                if(part != null)
                {
                    con = new Conjunction();
                    con.add(Restrictions.eq("part.id", part.getId()));
                }

                if(process != null)
                {
                    con = (con == null?new Conjunction():con);
                    con.add(Restrictions.eq("process.id", process.getId()));
                }

                if(measurementModel != null)
                {
                    con = (con == null?new Conjunction():con);
                    con.add(Restrictions.eq("measurementModel.id", measurementModel.getId()));
                }

                if(con != null)
                {
                    criteria.add(con);
                    criteriaCount.add(con);
                }

                criteriaCount.setProjection(Projections.count("part.id"));
                Number allRecords = (Number)criteriaCount.uniqueResult();
                List<Checkpoint> tools = criteria.list();
                if(tools != null && tools.size() > 0)
                {
                    for(Checkpoint tool: tools)
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

    public List<Part> getAllPartList() throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Checkpoint.class);

                criteria.setProjection(Projections.distinct(Projections.property("part")));

                criteria.addOrder(Order.asc("part.id"));
                List<Part> tools = criteria.list();
                if(tools != null && tools.size() > 0)
                {
                    for(Part tool: tools)
                    {
                        tool.toString();
                    }
                }

                Collections.sort(tools,new Comparator<Part>() {

                    @Override
                    public int compare(Part o1, Part o2) {
                        if(o1.getPartNo().equalsIgnoreCase(o2.getPartNo()))
                        {
                            return o1.getPartName().compareTo(o2.getPartName());
                        }
                        return o1.getPartNo().compareTo(o2.getPartNo());
                    }
                });
                LOG.info("results = "+tools.size());
                map.put("results",tools);                

            }
        }.process();

        return (List<Part>)map.get("results");
    }

    public List<Checkpoint> getListForControlChart(final Part part,final Process process) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Checkpoint.class);                

                criteria.addOrder(Order.asc("process.id"));
                criteria.addOrder(Order.asc("position"));

                Conjunction con = null;
                if(part != null)
                {
                    con = new Conjunction();
                    con.add(Restrictions.eq("part.id", part.getId()));
                }

                if(process != null)
                {
                    con = (con == null?new Conjunction():con);
                    con.add(Restrictions.eq("process.id", process.getId()));
                }

                con.add(Restrictions.eq("spc", Boolean.TRUE));
               

                if(con != null)
                {
                    criteria.add(con);
                }

                                
                List<Checkpoint> tools = criteria.list();
                if(tools != null && tools.size() > 0)
                {
                    for(Checkpoint tool: tools)
                    {
                        tool.toString();
                    }
                }
                LOG.info("results = "+tools.size());
                map.put("results",tools);                

            }
        }.process();

        return (List<Checkpoint>)map.get("results");
    }
}
