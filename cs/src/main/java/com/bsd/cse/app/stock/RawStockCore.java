/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.stock;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.constant.stock.StockTypeConstant;
import com.bsd.cse.model.backoffice.material.raw.RawMaterial;
import com.bsd.cse.model.backoffice.material.semi.SemiMaterial;
import java.util.List;
import java.util.Date;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.security.User;
import com.bsd.cse.model.security.UserInfo;
import com.bsd.cse.model.stock.RawStockTran;
import com.bsd.cse.model.stock.TransferType;
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
public class RawStockCore {

    private static Log LOG = LogFactory.getLog(RawStockCore.class);

    public HashMap<String,Object> getRawIncomingTransactions(final Date startDate,final Date endDate,final Part part,final RawMaterial rawMaterial,final TransferType transferType) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                
                Criteria criteria = session.createCriteria(RawStockTran.class)
                        .createAlias("part", "part")
                        .createAlias("part.semiMaterial", "semiMaterial")
                        .createAlias("semiMaterial.rawMaterial", "rawMaterial")
                        .createAlias("transferType", "transferType");
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

                if(rawMaterial != null)
                {
                    criteria.add(Restrictions.eq("rawMaterial.id", rawMaterial.getId()));
                }
                
                if(transferType != null)
                {
                    criteria.add(Restrictions.eq("transferType.id",transferType.getId()));
                }

                criteria.add(Restrictions.eq("transferType.stockType.id",StockTypeConstant.INCOMING_TYPE_ID));
                criteria.addOrder(Order.desc("createdDate"));

                List<RawStockTran> stockTrans = criteria.list();
                for(RawStockTran tran : stockTrans)
                {
                    tran.toString();
                }

                map.put("results", stockTrans);
                map.put("allRecords", stockTrans.size());
            }
        }.process();

        return map;
    }

    public HashMap<String,Object> getRawOutgoingTransactions(final Date startDate,final Date endDate,final Part part,final RawMaterial rawMaterial,final TransferType transferType) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {

                Criteria criteria = session.createCriteria(RawStockTran.class)
                        .createAlias("part", "part")
                        .createAlias("part.semiMaterial", "semiMaterial")
                        .createAlias("semiMaterial.rawMaterial", "rawMaterial")
                        .createAlias("transferType", "transferType");
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

                if(rawMaterial != null)
                {
                    criteria.add(Restrictions.eq("rawMaterial.id", rawMaterial.getId()));
                }

                if(transferType != null)
                {
                    criteria.add(Restrictions.eq("transferType.id",transferType.getId()));
                }

                criteria.add(Restrictions.eq("transferType.stockType.id",StockTypeConstant.OUTGOING_TYPE_ID));
                criteria.addOrder(Order.desc("createdDate"));

                List<RawStockTran> stockTrans = criteria.list();
                for(RawStockTran tran : stockTrans)
                {
                    tran.toString();
                }

                map.put("results", stockTrans);
                map.put("allRecords", stockTrans.size());
            }
        }.process();

        return map;
    }

    public synchronized  HashMap<String,Object>  save(final RawStockTran object,final Long userId) throws Exception
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
                                ?"update RawMaterial set amount=amount-:newItem,updatedDate = :updatedDate,updatedBy.id = :updatedBy  where id = :id"
                                :"update RawMaterial set amount=amount+:newItem,updatedDate = :updatedDate,updatedBy.id = :updatedBy  where id = :id";
                    Query query = session.createQuery(queryStr);
                    query.setLong("newItem", object.getQuantity());
                    query.setLong("updatedBy", object.getCreatedBy().getId());
                    query.setTimestamp("updatedDate", object.getCreatedDate());
                    query.setLong("id", object.getPart().getSemiMaterial().getRawMaterial().getId());
                    query.executeUpdate();
                    RawMaterial rawMaterial = (RawMaterial)session.get(RawMaterial.class, object.getPart().getSemiMaterial().getRawMaterial().getId());
                    object.setBalance(rawMaterial.getAmount());
                    session.evict(rawMaterial);
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
