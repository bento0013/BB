/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.stock;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.stock.TransferType;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author bento
 */
public class TransferTypeCore {
    private static Log LOG = LogFactory.getLog(TransferTypeCore.class);

    public static List<TransferType> getTranferTypes(final Long stockTypeId,final Long materialType) throws Exception
    {
        final HashMap<String,List<TransferType>> map = new HashMap<String,List<TransferType>>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(TransferType.class);
                criteria.add(Restrictions.eq("stockType.id",stockTypeId));
                criteria.addOrder(Order.asc("transferTypeName"));
                Disjunction disjunction = Restrictions.disjunction();
                disjunction.add(Restrictions.isNull("materialType"));
                if(materialType != null)
                {
                    disjunction.add(Restrictions.eq("materialType", materialType));
                }
                criteria.add(disjunction);
                List<TransferType> transferTypes = (List<TransferType>)criteria.list();
                
                if(transferTypes != null && transferTypes.size() > 0)
                {                    
                    for(TransferType transferType :transferTypes)
                    {
                        transferType.getTransferTypeName();
                        session.evict(transferType);
                        transferType.toString();
                    }
                }

                session.evict(transferTypes);
                map.put("results", transferTypes);
            }
        }.process();

        return (List<TransferType>)map.get("results");
    }

    
}
