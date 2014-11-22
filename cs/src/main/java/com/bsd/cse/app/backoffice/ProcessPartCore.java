/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.backoffice;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.machine.MachineModel;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.backoffice.part.Process;
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
public class ProcessPartCore {
    private Log LOG = LogFactory.getLog(ProcessPartCore.class);

    public HashMap<String,Object> save(final ProcessPart object,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                ProcessPart objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    Criteria criteria = session.createCriteria(ProcessPart.class);
                    criteria.add(Restrictions.eq("id", object.getId()));                    
                    criteria.setMaxResults(1);
                    objectDb = (ProcessPart)criteria.uniqueResult();
                }
                try
                {
                    if(objectDb != null )
                    {
                        objectDb.setImagePath(object.getImagePath());
//                        objectDb.setMachineModel(object.getMachineModel());
                        objectDb.setPart(object.getPart());
                        objectDb.setProcess(object.getProcess());
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

    public Boolean checkCanDelete(final ProcessPart object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                
//                    Criteria criteria = session.createCriteria(Part.class);
//                    criteria.add(Restrictions.eq("rawType.id",object.getId()));
//                    criteria.setProjection(Projections.count("id"));
//                    Number count = (Number)criteria.uniqueResult();
//                    map.put("results",count.intValue()>0?Boolean.FALSE:Boolean.TRUE);
                    map.put("results",Boolean.TRUE);
            }
            
        }.process();

        return (Boolean)map.get("results");
    }

    public HashMap<String,Object> delete(final ProcessPart object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                ProcessPart objectDb = null;
                if(object != null && object.getPart() !=null && object.getProcess() !=null)
                {
                    Criteria criteria = session.createCriteria(ProcessPart.class);
                    criteria.add(Restrictions.eq("id", object.getId()));                    
                    criteria.setMaxResults(1);
                    objectDb = (ProcessPart)criteria.uniqueResult();
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

    public HashMap<String,Object> getList(final Part part,final Process process) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(ProcessPart.class);                
                Criteria criteriaCount = session.createCriteria(ProcessPart.class);

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

//                if(machineModel != null)
//                {
//                    con = (con == null?new Conjunction():con);
//                    con.add(Restrictions.eq("machineModel.id", machineModel.getId()));
//                }

                if(con != null)
                {
                    criteria.add(con);
                    criteriaCount.add(con);
                }

                criteriaCount.setProjection(Projections.count("id"));
                Number allRecords = (Number)criteriaCount.uniqueResult();
                List<ProcessPart> tools = criteria.list();
                if(tools != null && tools.size() > 0)
                {
                    for(ProcessPart tool: tools)
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


    public ProcessPart getProcessPart(final Part part,final Process process) throws Exception
    {
        final HashMap<String,ProcessPart> map = new HashMap<String,ProcessPart>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(ProcessPart.class);

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

//                if(machineModel != null)
//                {
//                    con = (con == null?new Conjunction():con);
//                    con.add(Restrictions.eq("machineModel.id", machineModel.getId()));
//                }

                if(con != null)
                {
                    criteria.add(con);                    
                }

                criteria.setMaxResults(1);
                ProcessPart processPart = (ProcessPart)criteria.uniqueResult();
                if(processPart != null )
                {                    
                    processPart.toString();
                }
                                
                map.put("results",processPart);

            }
        }.process();

        return map.get("results");
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
