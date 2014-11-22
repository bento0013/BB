/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.stock;

import com.bsd.cse.app.input.*;
import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.constant.stock.StockTypeConstant;
import com.bsd.cse.model.input.ProductLineTime;
import com.bsd.cse.model.backoffice.part.Process;
import java.util.List;
import java.util.Date;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.input.ProductLine;
import com.bsd.cse.model.input.Time;
import com.bsd.cse.model.security.User;
import com.bsd.cse.model.security.UserInfo;
import com.bsd.cse.model.stock.FgStockTran;
import com.bsd.cse.model.stock.StockType;
import com.bsd.cse.model.stock.TransferType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author bento
 */
public class FgStockCore {

    private static Log LOG = LogFactory.getLog(FgStockCore.class);

    public HashMap<String,Object> getFgIncomingTransactions(final Date startDate,final Date endDate,final Part part,final TransferType transferType) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                
                Criteria criteria = session.createCriteria(FgStockTran.class).createAlias("transferType", "transferType");
                if(startDate != null)
                {
                    criteria.add(Restrictions.ge("createdDate",startDate));
                }
                if(endDate != null)
                {
                    Calendar endCal = Calendar.getInstance();
                    endCal.setTime(endDate);
                    endCal.add(Calendar.DATE, 1);
                    criteria.add(Restrictions.lt("createdDate",endCal.getTime()));
                }

                if(part != null)
                {
                    criteria.add(Restrictions.eq("part.id", part.getId()));
                }
                
                if(transferType != null)
                {
                    criteria.add(Restrictions.eq("transferType.id",transferType.getId()));
                }

                criteria.add(Restrictions.eq("transferType.stockType.id",StockTypeConstant.INCOMING_TYPE_ID));
                criteria.addOrder(Order.desc("createdDate"));

                List<FgStockTran> stockTrans = criteria.list();
                for(FgStockTran tran : stockTrans)
                {
                    tran.toString();
                }

                map.put("results", stockTrans);
                map.put("allRecords", stockTrans.size());
            }
        }.process();

        return map;
    }

    public HashMap<String,Object> getFgOutgoingTransactions(final Date startDate,final Date endDate,final Part part,final TransferType transferType) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {

                Criteria criteria = session.createCriteria(FgStockTran.class).createAlias("transferType", "transferType");
                if(startDate != null)
                {
                    criteria.add(Restrictions.ge("createdDate",startDate));
                }
                if(endDate != null)
                {
                    Calendar endCal = Calendar.getInstance();
                    endCal.setTime(endDate);
                    endCal.add(Calendar.DATE, 1);
                    criteria.add(Restrictions.lt("createdDate",endCal.getTime()));
                }

                if(part != null)
                {
                    criteria.add(Restrictions.eq("part.id", part.getId()));
                }

                if(transferType != null)
                {
                    criteria.add(Restrictions.eq("transferType.id",transferType.getId()));
                }

                criteria.add(Restrictions.eq("transferType.stockType.id",StockTypeConstant.INCOMING_TYPE_ID));
                               
                criteria.addOrder(Order.desc("createdDate"));

                List<FgStockTran> stockTrans = criteria.list();
                for(FgStockTran tran : stockTrans)
                {
                    tran.toString();
                }

                map.put("results", stockTrans);
                map.put("allRecords", stockTrans.size());
            }
        }.process();

        return map;
    }

    public synchronized  HashMap<String,Object>  save(final FgStockTran object,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {                              

                try
                {
                    UserInfo userInfo = new UserInfo();
                    userInfo.setId(userId);
                    object.setCreatedBy(userInfo);
                    object.setCreatedDate(new Date());
                    String queryStr =
                            object.getTransferType().getStockType().getId().equals(StockTypeConstant.OUTGOING_TYPE_ID)
                                ?"update Part set amount=amount-:newItem,updatedDate = :updatedDate,updatedBy.id = :updatedBy  where id = :id"
                                :"update Part set amount=amount+:newItem,updatedDate = :updatedDate,updatedBy.id = :updatedBy  where id = :id";
                    Query query = session.createQuery(queryStr);
                    query.setLong("newItem", object.getQuantity());
                    query.setLong("updatedBy", object.getCreatedBy().getId());
                    query.setTimestamp("updatedDate", object.getCreatedDate());
                    query.setLong("id", object.getPart().getId());
                    query.executeUpdate();
                    Part part = (Part)session.get(Part.class, object.getPart().getId());
                    object.setBalance(part.getAmount());
                    session.evict(part);
                    session.save(object);
                    session.flush();

                    
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
    
}
