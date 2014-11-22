/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.security;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.trans.TransactionLog;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author bento
 */
public class TransactionLogs {

    public static void logs(final Long functionId,final Long userId) throws Exception
    {
        new TransactionalProcessor()
        {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                TransactionLog log = new TransactionLog();
                log.setCreatedby(userId);
                log.setCreatedDate(new Date());
                log.setFunctionId(functionId);
                session.save(log);
            }

        }.process();
    }
   
}
