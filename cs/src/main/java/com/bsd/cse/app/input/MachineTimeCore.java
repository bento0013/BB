/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.input;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.machine.Machine;
import com.bsd.cse.model.input.ProductLineTime;
import com.bsd.cse.model.backoffice.part.Process;
import java.util.List;
import java.util.Date;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.input.MachineTime;
import com.bsd.cse.model.input.ProductLine;
import com.bsd.cse.model.input.Time;
import com.bsd.cse.model.security.User;
import com.bsd.cse.model.security.User;
import com.bsd.cse.model.security.UserInfo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author bento
 */
public class MachineTimeCore {

    private static Log LOG = LogFactory.getLog(MachineTimeCore.class);

    public HashMap<String,Object> getMachineTimes(final Date requestDate,final Machine machine, final Part part, final Process process, final Integer period) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {                                
                Criteria criteria = session.createCriteria(MachineTime.class).createAlias("machine", "machine").createAlias("process", "process");
               
                Calendar requestCal = Calendar.getInstance();
                requestCal.setTime(requestDate);
                requestCal.set(Calendar.HOUR_OF_DAY,0);
                requestCal.set(Calendar.MINUTE,0);
                requestCal.set(Calendar.SECOND,0);
                requestCal.set(Calendar.MILLISECOND,0);
                criteria.add(Restrictions.eq("scheduleTime",requestCal.getTime()));
                if(machine != null)
                {
                    criteria.add(Restrictions.eq("machine.id",machine.getId()));
                }

                if(part != null)
                {
                    criteria.add(Restrictions.eq("part.id",part.getId()));
                }

                if(process != null)
                {
                    criteria.add(Restrictions.eq("process.id",process.getId()));
                }

                 if(period != null)
                {
                    criteria.add(Restrictions.eq("period",period));
                }
                
                criteria.addOrder(Order.asc("process.id"));
                criteria.addOrder(Order.asc("machine.name"));

                List<MachineTime> machineTimes = criteria.list();
                if(machineTimes != null && machineTimes.size() > 0)
                {
                    for(MachineTime machineTime: machineTimes)
                    {                        
                        machineTime.toString();
                    }
                }

                map.put("results", machineTimes);
                
            }
        }.process();

        return map;
    }

    public HashMap<String,Object> save(final MachineTime object,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                MachineTime objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (MachineTime)session.get(MachineTime.class, object.getId());
                }

                try
                {
                    if(objectDb != null )
                    {

                        objectDb.setApTime(object.getApTime());
                        objectDb.setBpTime(object.getBpTime());
                        objectDb.setCpTime(object.getCpTime());
                        objectDb.setDpTime(object.getDpTime());
                        objectDb.setEpTime(object.getEpTime());
                        objectDb.setFp1Time(object.getFp1Time());
                        objectDb.setFp2Time(object.getFp2Time());
                        objectDb.setFp3Time(object.getFp3Time());
                        objectDb.setHpTime(object.getHpTime());
                        objectDb.setIpTime(object.getIpTime());
                        objectDb.setJpTime(object.getJpTime());
                        objectDb.setKp1Time(object.getKp1Time());
                        objectDb.setKp2Time(object.getKp2Time());
                        objectDb.setKp3Time(object.getKp3Time());
                        objectDb.setMpTime(object.getMpTime());
                        objectDb.setPeriod(object.getPeriod());
                        objectDb.setPart(object.getPart());
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

    public HashMap<String,Object> delete(final MachineTime object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                MachineTime objectDb = null;

                if(object != null && object.getId() !=null)
                {
                    objectDb = (MachineTime)session.get(MachineTime.class, object.getId());
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
}
