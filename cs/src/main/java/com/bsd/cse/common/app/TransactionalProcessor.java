/*
 * $Id: TransactionalProcessor.java,v 1.2 2011/02/09 08:47:22 thanasit Exp $
 *
 * Copyright (C) 2007 Advanced Research Group Co., Ltd. (ARG).
 *
 * All Rights Researved.
 */


package com.bsd.cse.common.app;

import com.bsd.cse.common.Configuration;

import com.bsd.cse.common.HibernateUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * author: fuangchai
 */
public abstract class TransactionalProcessor
{
    //~ Static fields/initializers ·············································

    private static final ThreadLocal<List<TransactionalProcessor>> CONTEXT =
        new ThreadLocal<List<TransactionalProcessor>>();

    //~ Instance fields ························································

    private Log logger;

    //~ Constructors ···························································

    /**
     * Creates a new TransactionalProcessor object.
     */
    public TransactionalProcessor()
    {
        this(LogFactory.getLog(TransactionalProcessor.class));
    }

    /**
     * Creates a new TransactionalProcessor object.
     *
     * @param logger DOCUMENT ME!
     */
    public TransactionalProcessor(final Log logger)
    {
        logger.getClass(); // check null

        this.logger = logger;

        List<TransactionalProcessor> stack = CONTEXT.get();

        if (null == stack)
        {
            CONTEXT.set(stack = new ArrayList<TransactionalProcessor>());
        }

        stack.add(this);
    }

    //~ Methods ································································

    /**
     * DOCUMENT ME!
     *
     * @throws Exception DOCUMENT ME!
     */
    public final void process()
        throws Exception
    {        
        Session session = HibernateUtil.currentSession();
        /**
         * Session::beginTransaction() can be called twice or more,
         * so no need to check whether this is the ROOT context or not.
         */
        Transaction tx = session.beginTransaction();

        try
        {
            if (logger.isDebugEnabled())
            {
                logger.debug("About to process user transaction.");
            }

            if (CONTEXT.get().size() == 1)
            {
                logger.debug("Current transaction is ROOT context, clean it up before using.");
                session.clear();
            }

            process(session, tx);

            if (logger.isDebugEnabled())
            {
                logger.debug("About to commit transaction.");
            }

            session.flush();

            if (CONTEXT.get().size() == 1)
            {
                HibernateUtil.commit(tx);

                if (logger.isDebugEnabled())
                {
                    logger.debug("Transaction committed successfully.");
                }
            }
            else
            {
                logger.debug("Current transaction is not the ROOT one, commit pending.");
            }
        }
        catch (final Exception e)
        {
            List<TransactionalProcessor> stack = CONTEXT.get();

            logger.error(e.getMessage(), e);

            if (stack.size() == 1)
            {
                HibernateUtil.rollback(tx);
                logger.debug("It's ROOT context, transaction rolled back.");
            }

            if (e instanceof JDBCException)
            {
                JDBCException x = (JDBCException) e;
                String msg = x.getMessage();

                if (StringUtils.isNotBlank(msg))
                {
                    throw new Exception(msg, e);
                }

                throw x.getSQLException();
            }

            throw e;
        }
        finally
        {
            List<TransactionalProcessor> stack = CONTEXT.get();

            if (stack.remove(this))
            {
                if (logger.isDebugEnabled())
                {
                    logger.debug(String.format("Total %1$s left on the stack: %2$d",
                                               getClass().toString(),
                                               stack.size()));
                }
            }
            else
            {
                logger.warn(String.format("%1$s not found on the stack (total: %2$d).",
                                          getClass().toString(),
                                          stack.size()));
            }

            if (stack.size() == 0)
            {
                session.clear();
                HibernateUtil.closeSession();
                logger.debug("It's ROOT context, Hibernate session cleaned up.");
            }
            else
            {
                logger.debug("Not a ROOT context, Hibernate session cleanup ignored.");
            }
        }
    }


   
    /**
     * DOCUMENT ME!
     *
     * @param session DOCUMENT ME!
     * @param tx DOCUMENT ME!
     *
     * @throws Exception DOCUMENT ME!
     */
    public abstract void process(final Session session, final Transaction tx)
        throws Exception;
}
