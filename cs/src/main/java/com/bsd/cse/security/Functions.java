/*
 * $Id: Functions.java,v 1.1 2010/11/12 15:24:52 thanasit Exp $
 *
 * Copyright (C) 2007 Advanced Research Group Co., Ltd. (ARG).
 *
 * All Rights Researved.
 */


package com.bsd.cse.security;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.common.HibernateUtil;
import com.bsd.cse.model.security.Function;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * DOCUMENT ME!
 *
 * @author <a href="mailto:chalermpongc@ar.co.th">Chalermpong Chaiyawan</a>
 * @version $Revision: 1.1 $
 */
public class Functions
{
    //~ Static fields/initializers ·············································

    private static final Log LOG = LogFactory.getLog(Functions.class);
    private static List<Function> cache;
    private static List<Function> rootNodes;
    private static List<Long> cacheLevel0;
    private static List<Long> cacheLevel1;
    
    //~ Constructors ···························································

    private Functions()
    {
    }

    //~ Methods ································································

    /**
     * DOCUMENT ME!
     */
    public static void invalidate()
    {
        synchronized (Functions.class)
        {
            cache = null;
            rootNodes = null;

            if (LOG.isDebugEnabled())
            {
                LOG.debug(String.format("%1$s's cached invalidated.",
                                        Functions.class.getSimpleName()));
            }
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static List<Function> list()
    {
        if (null == cache)
        {
            synchronized (Functions.class)
            {
                if (null == cache)
                {
                    try
                    {
                        List<Function>[] tmp = load();

                        cache = tmp[0];
                        rootNodes = tmp[1];
                    }
                    catch (final Exception e)
                    {
                        LOG.error(e.getMessage(), e);

                        if (LOG.isTraceEnabled())
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return cache;
    }

    
    /**
     * DOCUMENT ME!
     *
     * @param id DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Function locate(final Long id)
    {
        List<Function> func = list();

        return func.get(func.indexOf(new Function(id)));
    }    

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static List<Function> getRootNodes()
    {
        if (null == rootNodes)
        {
            LOG.debug("No root nodes found, about to try (re)loading...");
            list();
        }

        return rootNodes;
    }

    /**
     * CAUTION: caller of this function must be ensured to close
     * hibernate session!!
     *
     * @return DOCUMENT ME!
     *
     * @throws Exception DOCUMENT ME!
     */
    private static List<Function>[] load()
        throws Exception
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("Loading functions...");
        }

        final List[] RESULT =
            {
                new ArrayList<Function>(),
                new ArrayList<Function>()
            };

        (new TransactionalProcessor(LOG)
            {
                public void process(final Session session, final Transaction tx)
                    throws Exception
                {                    
                    List<Function> cache = (List<Function>) RESULT[0];
                    List<Function> roots = (List<Function>) RESULT[1];
                    List<Function> functions =
                        (List<Function>) HibernateUtil.currentSession()
                                                          .createCriteria(Function.class)
                                                          .setFetchMode("parent",
                                                                            FetchMode.JOIN)
                                                          .list();
                    LOG.debug("size = "+functions.size());

                    for (final Function function : functions)
                    {                        
                        if (LOG.isDebugEnabled())
                        {
                            LOG.debug(String.format("Analyzing function[%1$d, %2$s]...",
                                                    function.getId(),
                                                    function.getFunctionName()));
                        }

                        Function func = null;
                        Function parent = null;

                        if (cache.contains(function))
                        {
                            func = cache.get(cache.indexOf(function));

                            if (LOG.isDebugEnabled())
                            {
                                LOG.debug(String.format("\to Function[%1$d] already exist in the temporary store.",
                                                        func.getId()));
                            }
                        }
                        else
                        {                           
                            cache.add(func = new Function(function));
                            

                            if (LOG.isDebugEnabled())
                            {
                                LOG.debug(String.format("\to Function[%1$d] added to the temporary store.",
                                                        func.getId()));
                            }
                        }

                        if (function.getParent() == null)
                        {
                            if (LOG.isDebugEnabled())
                            {
                                LOG.debug(String.format("\to Function[%1$d] is a root node.",
                                                        func.getId()));
                            }

                            if (!roots.contains(func))
                            {
                                roots.add(func);

                                if (LOG.isDebugEnabled())
                                {
                                    LOG.debug(String.format("\to Function[%1$d] added to the temporary root nodes store.",
                                                            func.getId()));
                                }
                            }
                        }
                        else
                        {
                            if (cache.contains(function.getParent()))
                            {
                                parent = cache.get(cache.indexOf(function.getParent()));

                                if (LOG.isDebugEnabled())
                                {
                                    LOG.debug(String.format("\to Parent[%1$d] retrieved from temporary store.",
                                                            parent.getId()));
                                }
                            }
                            else
                            {
                                cache.add(parent = new Function(function.getParent()));

                                if (LOG.isDebugEnabled())
                                {
                                    LOG.debug(String.format("\to Parent[%1$d] added to temporary store.",
                                                            parent.getId()));
                                }
                            }
                        }

                        if (null != parent)
                        {
                            func.setParent(parent);

                            if (LOG.isDebugEnabled())
                            {
                                LOG.debug(String.format("\to Function[%2$d] has been set as a parent of Function[%1$d].",
                                                        func.getId(),
                                                        parent.getId()));
                            }

//                            if (!parent.getChildren().contains(func))
//                            {
//                                parent.getChildren().add(func);
//
//                                if (LOG.isDebugEnabled())
//                                {
//                                    LOG.debug(String.format("\to Function[%1$d] added as a child of Function[%2$d].",
//                                                            func.getId(),
//                                                            parent.getId()));
//                                }
//                            }
                        }
                    }
                }
            }).process();

        return RESULT;
    }

    /**
     * DOCUMENT ME!
     *
     * @param func DOCUMENT ME!
     * @param ids DOCUMENT ME!
     */
    public static void allChildrenIDs(final Function func, final List<Long> ids)
    {
        if (null == cache)
        {
            list();
        }

        if (cache.contains(func))
        {
            Function f = cache.get(cache.indexOf(func));

            if (!ids.contains(f.getId()))
            {
                ids.add(f.getId());
            }

//            for (final Function child : f.getChildren())
//            {
//                allChildrenIDs(child, ids);
//            }
        }
    }
}
