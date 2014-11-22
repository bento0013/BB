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
import com.bsd.cse.model.security.User;
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
public class MachineCore {
    private static Log LOG = LogFactory.getLog(MachineCore.class);


    public static Machine getMachine(final Long machineId) throws Exception
    {
        final HashMap<String,Machine> map = new HashMap<String,Machine>();
        (new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Machine.class);
                criteria.add(Restrictions.eq("id",machineId));
                criteria.setMaxResults(1);
                Machine machine= (Machine)criteria.uniqueResult();
                if(machine != null )
                {
                    machine.getName();
                }


                map.put("results", machine);
            }
        }).process();

        return (Machine)map.get("results");
    } 

    public HashMap<String,Object> save(final Machine object,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Machine objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (Machine)session.get(Machine.class, object.getId());
                }
                
                try
                {
                    if(objectDb != null )
                    {

                        objectDb.setName(object.getName());
                        objectDb.setModel(object.getModel());
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

    public Boolean checkCanDelete(final Machine object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                
//                    Criteria criteria = session.createCriteria(ProcessPart.class);
//                    criteria.add(Restrictions.eq("id",object.getId()));
//                    criteria.setProjection(Projections.count("id"));
//                    Number count = (Number)criteria.uniqueResult();
//                    map.put("results",count.intValue()>0?Boolean.FALSE:Boolean.TRUE);
                map.put("results",Boolean.TRUE);
            }
            
        }.process();

        return (Boolean)map.get("results");
    }

    public HashMap<String,Object> delete(final Machine object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Machine objectDb = null;
                
                if(object != null && object.getId() !=null)
                {
                    objectDb = (Machine)session.get(Machine.class, object.getId());
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

    public HashMap<String,Object> getList(final String name,final Long modelId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Machine.class);
                Criteria criteriaCount = session.createCriteria(Machine.class);

                criteria.addOrder(Order.asc("name"));

                Conjunction con = null;
                if(!name.trim().isEmpty())
                {
                    con = new Conjunction();
                    con.add(Restrictions.ilike("name", "%"+name+"%"));
                }

                if(modelId != null)
                {
                    con = (con != null?con:new Conjunction());
                    con.add(Restrictions.eq("model.id", modelId));
                }

                if(con != null)
                {
                    criteria.add(con);
                    criteriaCount.add(con);
                }

                criteriaCount.setProjection(Projections.count("id"));
                Number allRecords = (Number)criteriaCount.uniqueResult();
                List<Machine> machines = criteria.list();
                if(machines != null && machines.size() > 0)
                {
                    for(Machine machine: machines)
                    {
                        machine.toString();
                    }
                }
                LOG.info("machines = "+machines.size());
                map.put("results",machines);
                map.put("allRecords", new Integer(allRecords.intValue()));

            }
        }.process();

        return map;
    }

//    public List<Machine> getListFromProcessPart(final Long processId,final Long partId) throws Exception
//    {
//        final HashMap<String,Object> map = new HashMap<String,Object>();
//        new TransactionalProcessor(LOG) {
//
//            @Override
//            public void process(Session session, Transaction tx) throws Exception {
//                Criteria criteria = session.createCriteria(ProcessPart.class);
//                criteria.add(Restrictions.eq("process.id",processId));
//                criteria.add(Restrictions.eq("part.id",partId));
//
//                criteria.setMaxResults(1);
//                ProcessPart processPart = (ProcessPart)criteria.uniqueResult();
//
//
//                Criteria machineCriteria = session.createCriteria(Machine.class);
//                if(processPart != null)
//                {
//                    LOG.info("Process Part is not null");
//                    machineCriteria.add(Restrictions.eq("model.id",processPart.getMachineModel().getId()));
//                }
//                else
//                {
//                    LOG.info("Process Part is null");
//                }
//                machineCriteria.addOrder(Order.asc("name"));
//                List<Machine> machines = machineCriteria.list();
//                if(machines != null && machines.size() > 0)
//                {
//                    for(Machine machine: machines)
//                    {
//                        machine.toString();
//                    }
//                }
//
//                LOG.info("machines = "+machines.size());
//                map.put("results",machines);
//
//
//            }
//        }.process();
//        return (List<Machine>)map.get("results");
//    }

    public List<Machine> getAllList() throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Machine.class);

                criteria.addOrder(Order.asc("name"));

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

        return (List<Machine>)map.get("results");
    }


    public List<Machine> getList(final List<Long> machineIds) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Machine.class);
                Criteria criteriaCount = session.createCriteria(Machine.class);

                criteria.addOrder(Order.asc("name"));

                Conjunction con = null;
                if(machineIds != null)
                {
                    con = new Conjunction();
                    con.add(Restrictions.in("id", machineIds));
                }

                if(con != null)
                {
                    criteria.add(con);
                    criteriaCount.add(con);
                }

                criteriaCount.setProjection(Projections.count("id"));
                Number allRecords = (Number)criteriaCount.uniqueResult();
                List<Machine> tools = criteria.list();
                if(tools != null && tools.size() > 0)
                {
                    for(Machine tool: tools)
                    {
                        tool.toString();
                    }
                }
                LOG.info("results = "+tools.size());
                map.put("results",tools);
                map.put("allRecords", new Integer(allRecords.intValue()));

            }
        }.process();

        return (List<Machine>)map.get("results");
    }
    
}
