/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.backoffice;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.part.Process;
import java.util.HashMap;
import java.util.List;
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
public class ProcessCore {
    private Log LOG = LogFactory.getLog(ProcessCore.class);   

    public List<Process> getAllList() throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Process.class);

                criteria.addOrder(Order.asc("id"));
               
                List<Process> types = criteria.list();
                if(types != null && types.size() > 0)
                {
                    for(Process type: types)
                    {
                        type.toString();
                    }
                }
                map.put("results",types);


            }
        }.process();

        return (List<Process>)map.get("results");
    }

    public List<Process> getList(final Long numberProcess) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Process.class);
                criteria.add(Restrictions.le("id", numberProcess));
                criteria.addOrder(Order.asc("id"));

                List<Process> types = criteria.list();
                if(types != null && types.size() > 0)
                {
                    for(Process type: types)
                    {
                        type.toString();
                    }
                }
                map.put("results",types);


            }
        }.process();

        return (List<Process>)map.get("results");
    }  
}
