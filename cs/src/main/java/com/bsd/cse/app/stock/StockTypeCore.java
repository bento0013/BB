/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.stock;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.stock.StockType;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
/**
 *
 * @author bento
 */
public class StockTypeCore {
    private static Log LOG = LogFactory.getLog(StockTypeCore.class);

    public static List<StockType> getStockTypes() throws Exception
    {
        final HashMap<String,List<StockType>> map = new HashMap<String,List<StockType>>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(StockType.class);
                criteria.addOrder(Order.asc("stockTypeName"));
                List<StockType> stockTypes = (List<StockType>)criteria.list();
                
                if(stockTypes != null && stockTypes.size() > 0)
                {                    
                    for(StockType stockType :stockTypes)
                    {
                        stockType.getStockTypeName();
                        session.evict(stockType);
                        stockType.toString();
                    }
                }

                session.evict(stockTypes);
                map.put("results", stockTypes);
            }
        }.process();

        return (List<StockType>)map.get("results");
    }    
}
