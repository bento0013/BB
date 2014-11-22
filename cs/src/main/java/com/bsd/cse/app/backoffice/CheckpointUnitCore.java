/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.backoffice;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.part.CheckpointUnit;
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
public class CheckpointUnitCore {
    private Log LOG = LogFactory.getLog(CheckpointUnitCore.class);   

    public List<CheckpointUnit> getAllList() throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(CheckpointUnit.class);

                criteria.addOrder(Order.asc("id"));
               
                List<CheckpointUnit> types = criteria.list();
                if(types != null && types.size() > 0)
                {
                    for(CheckpointUnit type: types)
                    {
                        type.toString();
                    }
                }
                map.put("results",types);


            }
        }.process();

        return (List<CheckpointUnit>)map.get("results");
    }  
}
