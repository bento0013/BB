/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.input;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.machine.Machine;
import com.bsd.cse.model.backoffice.part.Checkpoint;
import com.bsd.cse.model.backoffice.part.Process;
import java.util.List;
import java.util.Date;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.input.FileMeasurementPart;
import com.bsd.cse.model.input.MeasurementPart;
import com.bsd.cse.model.input.MeasurementCheckpoint;
import com.bsd.cse.model.input.MeasurementTime;
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
public class MeasurementPartCore {

    private static Log LOG = LogFactory.getLog(MeasurementPartCore.class);

    public HashMap<String,Object> getMeasurementParts(final Date requestDate,final Part part
            ,final Process process, final Machine machine) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                List<MeasurementCheckpoint> measurementCheckpoints = new ArrayList<MeasurementCheckpoint>();
                HashMap<Long,MeasurementCheckpoint> measurementPartMaps = new HashMap<Long,MeasurementCheckpoint>();
                Criteria criteria = session.createCriteria(MeasurementPart.class);

                LOG.info("part id = "+part.getId());
                LOG.info("requestDate = "+requestDate);
                criteria.add(Restrictions.eq("part.id",part.getId()));
//                criteria.add(Restrictions.eq("period",period));
                criteria.add(Restrictions.eq("process.id",process.getId()));                
//                criteria.add(Restrictions.eq("measurementTime.id",time.getId()));
                criteria.add(Restrictions.eq("machine.id",machine.getId()));
                Calendar requestCal = Calendar.getInstance();
                requestCal.setTime(requestDate);
                requestCal.set(Calendar.HOUR_OF_DAY,0);
                requestCal.set(Calendar.MINUTE,0);
                requestCal.set(Calendar.SECOND,0);
                requestCal.set(Calendar.MILLISECOND,0);
                criteria.add(Restrictions.eq("measurementDate",requestCal.getTime()));
                criteria.addOrder(Order.asc("measurementTime"));
                criteria.addOrder(Order.asc("process.id"));


                List<MeasurementPart> measurementParts = criteria.list();
                if(measurementParts != null && measurementParts.size() > 0)
                {
                    for(MeasurementPart measurementPart: measurementParts)
                    {                        
                        measurementPart.toString();
                    }
                }

               Criteria criteriaTime = session.createCriteria(MeasurementTime.class);
                criteriaTime.addOrder(Order.asc("id"));
                List<MeasurementTime> times = criteriaTime.list();


                

                Criteria criteriaPosition = session.createCriteria(Checkpoint.class)
                        .createAlias("measurementType","measurementType")
                        .createAlias("checkpointType","checkpointType")
                        .createAlias("checkpointUnit","checkpointUnit");
                criteriaPosition.add(Restrictions.eq("part.id", part.getId()));
                criteriaPosition.add(Restrictions.eq("process.id",process.getId()));
                criteriaPosition.addOrder(Order.asc("position"));
                List<Checkpoint> checkpoints = criteriaPosition.list();
                
                for(Checkpoint checkpoint:checkpoints)
                {
                    MeasurementCheckpoint measurementCheckpoint = new MeasurementCheckpoint();
                    measurementCheckpoint.setCheckpoint(checkpoint);
                    List<MeasurementPart> measurementPartLocals = new ArrayList<MeasurementPart>();
                    measurementCheckpoint.setMeasurementParts(measurementPartLocals);
                    measurementCheckpoints.add(measurementCheckpoint);
                    measurementPartMaps.put(checkpoint.getId(),measurementCheckpoint);
                    for(MeasurementTime time : times)
                    {
                        time.toString();
                        MeasurementPart measurementPart  =new MeasurementPart();                        
                        measurementPart.setProcess(process);
                        measurementPart.setPart(part);
//                        measurementPart.setPeriod(period);
                        measurementPart.setCheckpoint(checkpoint);
                        measurementPart.setMeasurementTime(time);
                        measurementPart.setMachine(machine);
                        measurementPart.setMeasurementDate(requestCal.getTime());
                        measurementPartLocals.add(measurementPart);
                    }
                }

                for(MeasurementPart measurementPart : measurementParts)
                {
                    MeasurementCheckpoint measurementCheckpoint = (MeasurementCheckpoint)measurementPartMaps.get(measurementPart.getCheckpoint().getId());
                    measurementCheckpoint.getMeasurementParts().remove(measurementPart.getMeasurementTime().getId().intValue()-1);
                    measurementCheckpoint.getMeasurementParts().add(measurementPart.getMeasurementTime().getId().intValue()-1, measurementPart);
                    LOG.info("measurementPart.getMeasurementTime() Time id = "+measurementPart.getMeasurementTime().getId().intValue()+" size = "+measurementCheckpoint.getMeasurementParts().size());
                }

                map.put("results", measurementCheckpoints);
                
            }
        }.process();

        return map;
    }

    public HashMap<String,Object> save(final MeasurementPart object,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                MeasurementPart objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (MeasurementPart)session.get(MeasurementPart.class, object.getId());
                }

                try
                {
                    if(objectDb != null )
                    {

                        objectDb.setMeasurementValue(object.getMeasurementValue());                        
                        objectDb.setMeasurementTool(object.getMeasurementTool());
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

    public HashMap<String,Object> saveFile(final FileMeasurementPart object,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                FileMeasurementPart objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (FileMeasurementPart)session.get(FileMeasurementPart.class, object.getId());
                }

                try
                {
                    if(objectDb != null )
                    {                       
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

    public HashMap<String,Object> delete(final MeasurementPart object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                MeasurementPart objectDb = null;

                if(object != null && object.getId() !=null)
                {
                    objectDb = (MeasurementPart)session.get(MeasurementPart.class, object.getId());
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

    public HashMap<String,Object> deleteFile(final FileMeasurementPart object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                FileMeasurementPart objectDb = null;

                if(object != null && object.getId() !=null)
                {
                    objectDb = (FileMeasurementPart)session.get(FileMeasurementPart.class, object.getId());
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
