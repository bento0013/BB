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
import com.bsd.cse.model.input.NgProductLineRecord;
import com.bsd.cse.model.input.ProductLine;
import com.bsd.cse.model.input.Time;
import com.bsd.cse.model.security.UserInfo;
import com.bsd.cse.model.security.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author bento
 */
public class ProductLineCore {

    private static Log LOG = LogFactory.getLog(ProductLineCore.class);

    public HashMap<String,Object> getProductLines(final Date requestDate,final Part part,final Machine machine,final Integer period) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                List<ProductLineTime> productTimes = new ArrayList<ProductLineTime>();
                HashMap<Long,ProductLineTime> productTimeMaps = new HashMap<Long,ProductLineTime>();
                Criteria criteria = session.createCriteria(ProductLine.class).createAlias("time", "time");

                LOG.info("part id = "+part.getId());
                LOG.info("period id = "+period);
//                LOG.info("user id = "+user.getId());
                LOG.info("requestDate = "+requestDate);
                LOG.info("Machine id = "+machine.getId());
                criteria.add(Restrictions.eq("part.id",part.getId()));
                criteria.add(Restrictions.eq("time.round",period));
//                criteria.add(Restrictions.eq("user.id",user.getId()));
                criteria.add(Restrictions.eq("machine.id",machine.getId()));
                criteria.add(Restrictions.le("process.id",part.getNumProcesses()));
                Calendar requestCal = Calendar.getInstance();
                requestCal.setTime(requestDate);
                requestCal.set(Calendar.HOUR_OF_DAY,0);
                requestCal.set(Calendar.MINUTE,0);
                requestCal.set(Calendar.SECOND,0);
                requestCal.set(Calendar.MILLISECOND,0);
                criteria.add(Restrictions.eq("requestDate",requestCal.getTime()));
                criteria.addOrder(Order.asc("time.id"));
                criteria.addOrder(Order.asc("process.id"));

                List<ProductLine> productLines = criteria.list();
                if(productLines != null && productLines.size() > 0)
                {
                    for(ProductLine productLine: productLines)
                    {
                        productLine.getOk();
                        productLine.getUser().getFirstname();
                        User user = productLine.getUser();
                        session.evict(user);
                        session.evict(productLine);
                        user.setGroup(null);
                        user.setRoles(null);
                        productLine.toString();
                    }
                }

                Criteria criteriaTime = session.createCriteria(Time.class);
                criteriaTime.add(Restrictions.eq("round",period));
                criteriaTime.addOrder(Order.asc("id"));
                List<Time> times = criteriaTime.list();

                Criteria criteriaProcess = session.createCriteria(Process.class);               
                criteriaProcess.addOrder(Order.asc("id"));
                List<Process> processes = criteriaProcess.list();

                for(Time time: times)
                {
                    ProductLineTime productTime = new ProductLineTime();
                    productTime.setTime(time);
                    List<ProductLine> productLineLocals = new ArrayList<ProductLine>();
                    productTime.setProductLines(productLineLocals);
                    productTimes.add(productTime);
                    productTimeMaps.put(time.getId(),productTime);
                    for(Process process : processes)
                    {
                        process.toString();
                        ProductLine productLine  =new ProductLine();
                        LOG.info("process id = "+process.getProcessName());
                        productLine.setProcess(process);
                        productLine.setPart(part);
                        productLine.setTime(time);
//                        User user = new User();
//                        productLine.setUser(user);
                        productLine.setMachine(machine);
                        productLine.setRequestDate(requestCal.getTime());
                        productLineLocals.add(productLine);
                    }
                }

                for(ProductLine productLine : productLines)
                {
                    ProductLineTime productTime = (ProductLineTime)productTimeMaps.get(productLine.getTime().getId());
                    productTime.getProductLines().remove(productLine.getProcess().getId().intValue()-1);
                    productTime.getProductLines().add(productLine.getProcess().getId().intValue()-1, productLine);
                    LOG.info("productTime.getProductLines() Time id = "+productTime.getTime().getId()+" size = "+productTime.getProductLines().size());
                }

                map.put("results", productTimes);
                map.put("processes", processes);
                
            }
        }.process();

        return map;
    }

    public HashMap<String,Object> save(final ProductLine object,final List<NgProductLineRecord> ngProductRecords,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                ProductLine objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (ProductLine)session.get(ProductLine.class, object.getId());
                }

                try
                {
                    if(objectDb != null )
                    {

                        objectDb.setBp(object.getBp());
                        objectDb.setWp(object.getWp());
                        objectDb.setOk(object.getOk());
                        objectDb.setNg(object.getNg());
                        objectDb.setMachine(object.getMachine());
                        objectDb.setNgReason(object.getNgReason());
                        UserInfo userInfo = new UserInfo();
                        userInfo.setId(userId);
                        objectDb.setUpdatedBy(userInfo);
                        objectDb.setUpdatedDate(new Date());
                        session.saveOrUpdate(objectDb);
                        if(objectDb.getProcess().getId() > 1)
                        {
                            getNextTime(session, objectDb);
                            
                        }
                        session.flush();
                        updateProductLineSeries(session,objectDb);
                        updateNgReasonList(session, ngProductRecords, objectDb);
                    }
                    else
                    {
                        UserInfo userInfo = new UserInfo();
                        userInfo.setId(userId);
                        object.setCreatedBy(userInfo);
                        object.setCreatedDate(new Date());                       
                        if(object.getProcess().getId() > 1)
                        {
                            getNextTime(session, object);                            
                        }
                        session.save(object);
                        session.flush();
                        updateProductLineSeries(session,object);
                        updateNgReasonList(session, ngProductRecords, object);
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

    public HashMap<String,Object> delete(final ProductLine object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                ProductLine objectDb = null;

                if(object != null && object.getId() !=null)
                {
                    objectDb = (ProductLine)session.get(ProductLine.class, object.getId());
                }


                if(objectDb != null )
                {
                    Query query = session.createQuery("Delete from NgProductLineRecord where productLine.id =  :productId");
                    query.setLong("productId", object.getId());
                    query.executeUpdate();   
                    session.delete(objectDb);
                    updateProductLineSeries(session,object);
                    
                    
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

    public void updateProductLineSeries(Session session ,ProductLine object)
    {
        Criteria  criteria = session.createCriteria(ProductLine.class);
        criteria.add(Restrictions.eq("part.id",object.getPart().getId()));
        criteria.add(Restrictions.eq("machine.id",object.getMachine().getId()));
        criteria.add(Restrictions.eq("process.id", object.getProcess().getId()));
        Disjunction disj = Restrictions.disjunction();
        Conjunction con1 = Restrictions.conjunction();
        con1.add(Restrictions.eq("requestDate", object.getRequestDate()));
        con1.add(Restrictions.ge("time.id", object.getTime().getId()));
        disj.add(con1);
        Conjunction con2 = Restrictions.conjunction();
        con2.add(Restrictions.gt("requestDate", object.getRequestDate()));        
        disj.add(con2);
        criteria.add(disj);
        criteria.addOrder(Order.asc("requestDate"));
        criteria.addOrder(Order.asc("time.id"));
        List<ProductLine> productThisAfterProcess = (List<ProductLine>)criteria.list();

        criteria = session.createCriteria(ProductLine.class);
        criteria.add(Restrictions.eq("part.id",object.getPart().getId()));
        criteria.add(Restrictions.eq("machine.id",object.getMachine().getId()));
        criteria.add(Restrictions.eq("process.id", object.getProcess().getId()));
        disj = Restrictions.disjunction();
        con1 = Restrictions.conjunction();
        con1.add(Restrictions.eq("requestDate", object.getRequestDate()));
        con1.add(Restrictions.lt("time.id", object.getTime().getId()));
        disj.add(con1);
        con2 = Restrictions.conjunction();
        con2.add(Restrictions.lt("requestDate", object.getRequestDate()));
        disj.add(con2);
        criteria.add(disj);
        criteria.setMaxResults(1);
        criteria.addOrder(Order.desc("requestDate"));
        criteria.addOrder(Order.desc("time.id"));
        
        ProductLine before = (ProductLine)criteria.uniqueResult();
        
        for(ProductLine currentProductLine:productThisAfterProcess)
        {
            if(before != null)
            {
                currentProductLine.setWp(before.getWp()+currentProductLine.getBp()-currentProductLine.getOk()-currentProductLine.getNg());
            }
            before = currentProductLine;
//            currentProductLine.setUpdatedBy(object.getUpdatedBy());
//            currentProductLine.setUpdatedDate(new Date());
            session.saveOrUpdate(currentProductLine);            
        }
        session.flush();


        criteria = session.createCriteria(ProductLine.class);
        criteria.add(Restrictions.eq("part.id",object.getPart().getId()));
        criteria.add(Restrictions.eq("machine.id",object.getMachine().getId()));
        criteria.add(Restrictions.eq("process.id", object.getProcess().getId()+1L));
        disj = Restrictions.disjunction();
        con1 = Restrictions.conjunction();
        con1.add(Restrictions.eq("requestDate", object.getRequestDate()));
        con1.add(Restrictions.ge("time.id", object.getTime().getId()));
        disj.add(con1);
        con2 = Restrictions.conjunction();
        con2.add(Restrictions.gt("requestDate", object.getRequestDate()));
        disj.add(con2);
        criteria.add(disj);
        criteria.addOrder(Order.asc("requestDate"));
        criteria.addOrder(Order.asc("time.id"));
        criteria.setMaxResults(1);
        ProductLine productNextProcess = (ProductLine)criteria.uniqueResult();

        if(productNextProcess == null)
        {
            return;
        }
        
        criteria = session.createCriteria(ProductLine.class);
        criteria.add(Restrictions.eq("part.id",object.getPart().getId()));
        criteria.add(Restrictions.eq("machine.id",object.getMachine().getId()));
        criteria.add(Restrictions.eq("process.id", object.getProcess().getId()+1L));
        disj = Restrictions.disjunction();
        con1 = Restrictions.conjunction();
        con1.add(Restrictions.eq("requestDate", object.getRequestDate()));
        con1.add(Restrictions.lt("time.id", object.getTime().getId()));
        disj.add(con1);
        con2 = Restrictions.conjunction();
        con2.add(Restrictions.lt("requestDate", object.getRequestDate()));
        disj.add(con2);
        criteria.add(disj);
        criteria.setMaxResults(1);
        criteria.addOrder(Order.desc("requestDate"));
        criteria.addOrder(Order.desc("time.id"));
        
        ProductLine productNextProcessBefore = (ProductLine)criteria.uniqueResult();

        criteria = session.createCriteria(ProductLine.class);
        criteria.add(Restrictions.eq("part.id",object.getPart().getId()));
        criteria.add(Restrictions.eq("machine.id",object.getMachine().getId()));
        criteria.add(Restrictions.eq("process.id", object.getProcess().getId()));
        disj = Restrictions.disjunction();
        
        if(productNextProcessBefore == null
                || !productNextProcessBefore.getRequestDate().equals(productNextProcess.getRequestDate()))
        {
            if(productNextProcessBefore != null)
            {
                con1 = Restrictions.conjunction();

                con1.add(Restrictions.eq("requestDate", productNextProcessBefore.getRequestDate()));
                con1.add(Restrictions.gt("time.id", productNextProcessBefore.getTime().getId()));
                disj.add(con1);
            }
            con2 = Restrictions.conjunction();
            if(productNextProcessBefore != null)
            {
                con2.add(Restrictions.gt("requestDate", productNextProcessBefore.getRequestDate()));
            }
            con2.add(Restrictions.lt("requestDate", productNextProcess.getRequestDate()));
            disj.add(con2);
            Conjunction con3 = Restrictions.conjunction();
            con3.add(Restrictions.eq("requestDate", productNextProcess.getRequestDate()));
            con3.add(Restrictions.le("time.id", productNextProcess.getTime().getId()));
            disj.add(con3);
        }
        else
        {
            con1 = Restrictions.conjunction();
            con1.add(Restrictions.eq("requestDate", productNextProcessBefore.getRequestDate()));
            con1.add(Restrictions.gt("time.id", productNextProcessBefore.getTime().getId()));
            con1.add(Restrictions.le("time.id", productNextProcess.getTime().getId()));
            disj.add(con1);
        }
        criteria.add(disj);
        criteria.addOrder(Order.asc("requestDate"));
        criteria.addOrder(Order.asc("time.id"));        
        List<ProductLine> productThisProcess = (List<ProductLine>)criteria.list();
        Long okValue = 0L;
        for(ProductLine productLine : productThisProcess)
        {
//            LOG.info("productLine.getProcess().getId() = "+productLine.getProcess().getId());
//            LOG.info("productLine.getTime().getId() = "+productLine.getTime().getId());
//            LOG.info("productLine.getRequestDate() = "+productLine.getRequestDate());
//            LOG.info("productLine.getOk() = "+productLine.getOk());
            okValue+=productLine.getOk();
        }

        productNextProcess.setBp(okValue);
        session.saveOrUpdate(productNextProcess);
        session.flush();
        
        
        criteria = session.createCriteria(ProductLine.class);
        criteria.add(Restrictions.eq("part.id",productNextProcess.getPart().getId()));
        criteria.add(Restrictions.eq("machine.id",productNextProcess.getMachine().getId()));
        criteria.add(Restrictions.ge("process.id", productNextProcess.getProcess().getId()));
        disj = Restrictions.disjunction();
        con1 = Restrictions.conjunction();
        con1.add(Restrictions.eq("requestDate", productNextProcess.getRequestDate()));
        con1.add(Restrictions.ge("time.id", productNextProcess.getTime().getId()));
        disj.add(con1);
        con2 = Restrictions.conjunction();
        con2.add(Restrictions.gt("requestDate", productNextProcess.getRequestDate()));
        disj.add(con2);
        criteria.add(disj);
        criteria.addOrder(Order.asc("requestDate"));
        criteria.addOrder(Order.asc("time.id"));
        List<ProductLine> productNextAfterProcess = (List<ProductLine>)criteria.list();

        criteria = session.createCriteria(ProductLine.class);
        criteria.add(Restrictions.eq("part.id",productNextProcess.getPart().getId()));
        criteria.add(Restrictions.eq("machine.id",productNextProcess.getMachine().getId()));
        criteria.add(Restrictions.ge("process.id", productNextProcess.getProcess().getId()));
        disj = Restrictions.disjunction();
        con1 = Restrictions.conjunction();
        con1.add(Restrictions.eq("requestDate", productNextProcess.getRequestDate()));
        con1.add(Restrictions.lt("time.id", productNextProcess.getTime().getId()));
        disj.add(con1);
        con2 = Restrictions.conjunction();
        con2.add(Restrictions.lt("requestDate", productNextProcess.getRequestDate()));
        disj.add(con2);
        criteria.add(disj);
        criteria.setMaxResults(1);
        criteria.addOrder(Order.desc("requestDate"));
        criteria.addOrder(Order.desc("time.id"));
        
        before = (ProductLine)criteria.uniqueResult();
        
        for(ProductLine currentProductLine:productNextAfterProcess)
        {
            if(before != null)
            {
                currentProductLine.setWp(before.getWp()+currentProductLine.getBp()-currentProductLine.getOk()-currentProductLine.getNg());
            }
            before = currentProductLine;
//            currentProductLine.setUpdatedBy(object.getUpdatedBy());
//            currentProductLine.setUpdatedDate(new Date());
            session.saveOrUpdate(currentProductLine);            
        }
        session.flush();
            
    }

    public void getNextTime(Session session,ProductLine currentProductLine)
    {
        Criteria criteria = session.createCriteria(ProductLine.class);
        criteria.add(Restrictions.eq("part.id",currentProductLine.getPart().getId()));
        criteria.add(Restrictions.eq("machine.id",currentProductLine.getMachine().getId()));
        criteria.add(Restrictions.eq("process.id", currentProductLine.getProcess().getId()));
        Disjunction disj = Restrictions.disjunction();
        Conjunction con1 = Restrictions.conjunction();
        con1.add(Restrictions.eq("requestDate", currentProductLine.getRequestDate()));
        con1.add(Restrictions.gt("time.id", currentProductLine.getTime().getId()));
        disj.add(con1);
        Conjunction con2 = Restrictions.conjunction();
        con2.add(Restrictions.gt("requestDate", currentProductLine.getRequestDate()));
        disj.add(con2);
        criteria.add(disj);
        criteria.addOrder(Order.asc("requestDate"));
        criteria.addOrder(Order.asc("time.id"));
        criteria.setMaxResults(1);
        ProductLine productThisAfterProcess = (ProductLine)criteria.uniqueResult();
        if(productThisAfterProcess != null && currentProductLine.getId() == null)
        {
            productThisAfterProcess.setBp(productThisAfterProcess.getBp()-currentProductLine.getBp());
            session.saveOrUpdate(productThisAfterProcess);
        }
        
    }

    public void getBpFromBeforeProcess(final ProductLine currentProductLine) throws Exception
    {
        (new TransactionalProcessor() {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(ProductLine.class);

                criteria.add(Restrictions.eq("part.id",currentProductLine.getPart().getId()));
                criteria.add(Restrictions.eq("machine.id",currentProductLine.getMachine().getId()));
                criteria.add(Restrictions.eq("process.id", currentProductLine.getProcess().getId()));
                Disjunction disj = Restrictions.disjunction();
                Conjunction con1 = Restrictions.conjunction();
                con1.add(Restrictions.eq("requestDate", currentProductLine.getRequestDate()));
                con1.add(Restrictions.lt("time.id", currentProductLine.getTime().getId()));
                disj.add(con1);
                Conjunction con2 = Restrictions.conjunction();
                con2.add(Restrictions.lt("requestDate", currentProductLine.getRequestDate()));
                disj.add(con2);
                criteria.add(disj);
                criteria.setMaxResults(1);
                criteria.addOrder(Order.desc("requestDate"));
                criteria.addOrder(Order.desc("time.id"));

                ProductLine productThisProcessBefore = (ProductLine)criteria.uniqueResult();
                
                criteria = session.createCriteria(ProductLine.class);
                criteria.add(Restrictions.eq("part.id",currentProductLine.getPart().getId()));
                criteria.add(Restrictions.eq("machine.id",currentProductLine.getMachine().getId()));
                criteria.add(Restrictions.eq("process.id", currentProductLine.getProcess().getId()-1));
                disj = Restrictions.disjunction();
                
                con1 = Restrictions.conjunction();
                con1.add(Restrictions.eq("requestDate", currentProductLine.getRequestDate()));
                con1.add(Restrictions.le("time.id", currentProductLine.getTime().getId()));
                disj.add(con1);
                con2 = Restrictions.conjunction();
                con2.add(Restrictions.lt("requestDate", currentProductLine.getRequestDate()));
                disj.add(con2);
                if(productThisProcessBefore != null)
                {
                    Disjunction dis3 = Restrictions.disjunction();
                    Conjunction con3 = Restrictions.conjunction();
                    con3.add(Restrictions.eq("requestDate", productThisProcessBefore.getRequestDate()));
                    con3.add(Restrictions.gt("time.id", productThisProcessBefore.getTime().getId()));
                    dis3.add(con3);
                    dis3.add(Restrictions.gt("requestDate", productThisProcessBefore.getRequestDate()));
                    criteria.add(dis3);
                }
                criteria.add(disj);                
                criteria.addOrder(Order.asc("requestDate"));
                criteria.addOrder(Order.asc("time.id"));
                List<ProductLine> productThisProcess = (List<ProductLine>)criteria.list();
                Long okValue = 0L;
                for(ProductLine productLine : productThisProcess)
                {
                    LOG.info("productLine.getProcess().getId() = "+productLine.getProcess().getId());
                    LOG.info("productLine.getTime().getId() = "+productLine.getTime().getId());
                    LOG.info("productLine.getRequestDate() = "+productLine.getRequestDate());
                    LOG.info("productLine.getOk() = "+productLine.getOk());
                    okValue+=productLine.getOk();
                }
                LOG.info("okValue = "+okValue);
                currentProductLine.setBp(okValue);
            }
        }).process();
        
    }

    public void updateNgReasonList(Session session,List<NgProductLineRecord> records,ProductLine productLine)
    {
        Query query = session.createQuery("DELETE FROM NgProductLineRecord where productLine.id = :productLineId and id not in  (:ids)");
        List<Long> ids = new ArrayList<Long>();
        ids.add(0L);
        
        for(NgProductLineRecord record:records)
        {
            if(record.getId() != null)
            {
                ids.add(record.getId());
            }
        }
        
        query.setLong("productLineId", productLine.getId());
        query.setParameterList("ids", ids);
        query.executeUpdate();
        UserInfo user = productLine.getUpdatedBy();
        Date date = productLine.getUpdatedDate();
        if(user ==null)
        {
            user = productLine.getCreatedBy();
            date = productLine.getCreatedDate();
        }

        for(NgProductLineRecord record :records)
        {
            record.setProductLine(productLine);
            record.setCreatedBy(user);
            record.setCreatedDate(date);
            session.saveOrUpdate(record);
        }
        session.flush();
    }

    public List<NgProductLineRecord> getNgReasonList(final ProductLine productLine) throws Exception
    {
        final HashMap<String,Object> results = new HashMap<String,Object>();
        (new TransactionalProcessor() {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(NgProductLineRecord.class);
                criteria.add(Restrictions.eq("productLine.id", productLine.getId()));
                criteria.addOrder(Order.asc("id"));
                List<NgProductLineRecord> records = (List<NgProductLineRecord>)criteria.list();
                for(NgProductLineRecord record : records)
                {
                    record.toString();
                }

                results.put("result", records);

            }
        }).process();
        return (List<NgProductLineRecord>)results.get("result");
    }
}
