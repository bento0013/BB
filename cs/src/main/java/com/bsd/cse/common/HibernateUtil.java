/*
 * $Id: HibernateUtil.java,v 1.1 2010/09/23 14:14:30 thanasit Exp $
 *
 * Copyright (C) 2007 Advanced Research Group Co., Ltd. (ARG).
 *
 * All Rights Researved.
 */
package com.bsd.cse.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.io.InputStream;

import java.util.Date;

/**
 * author: fuangchai
 */
public class HibernateUtil {
    //~ Static fields/initializers ·············································

    private static final Log LOG = LogFactory.getLog(HibernateUtil.class);
    private static final SessionFactory sessionFactory;
    /**
     * DOCUMENT ME!
     */
    public static final ThreadLocal session = new ThreadLocal();
    /**
     * DOCUMENT ME!
     */
    public static final ThreadLocal statelessSession = new ThreadLocal();

    static {
        try {
            Configuration cfg =
                    new Configuration() {

                        /**
                         * Overridden to fallback
                         * the loading of <code>resourceName</code> by using
                         * <code>org.hibernate.cfg.Configuration.class</code>
                         * only in case the default loading of
                         * <code>resourceName</code> failed.
                         *
                         * @param resourceName DOCUMENT ME!
                         *
                         * @return DOCUMENT ME!
                         *
                         * @throws MappingException DOCUMENT ME!
                         * @throws MappingNotFoundException DOCUMENT ME!
                         */
                        public Configuration addResource(final String resourceName)
                                throws MappingException {
                            try {
                                return super.addResource(resourceName);
                            } catch (final MappingNotFoundException e) {
                                /*
                                 * If the flow falls through this, it means that hibernate
                                 * cannot load resourceName using the ContextClassLoader
                                 * which is in turn, the resourceName does not reside in
                                 * the [contextPath]/WEB-INF/classes.
                                 */
                                LOG.warn(String.format("The resource named '%1$s' not found or failed to load using the ContextClassLoader, trying the simpler way...",
                                        resourceName));

                                Class c = Configuration.class;
                                InputStream is = c.getResourceAsStream(resourceName);

                                if (null == is) {
                                    throw new MappingNotFoundException("resource",
                                            resourceName);
                                }

                                return addInputStream(is);
                            }
                        }
                    }.configure();

            sessionFactory = cfg.buildSessionFactory();
        } catch (final Throwable ex) {
            LOG.error(ex);

            throw new ExceptionInInitializerError(ex);
        }
    }

    //~ Methods ································································
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws HibernateException DOCUMENT ME!
     */
    public static Session currentSession()
            throws HibernateException {
        Session s = (Session) session.get();

        if (s == null) {
            session.set(s = sessionFactory.openSession());
        } else if (!s.isOpen()) {
            s = sessionFactory.openSession();
        }

        // This method should be safe to call from anywhere in the same thread
        // and it should guarantee to return the same session with all outstanding
        // transactions.
        //s.clear();
        return s;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws HibernateException DOCUMENT ME!
     */
    public static StatelessSession currentStatelessSession()
            throws HibernateException {
        StatelessSession s = (StatelessSession) statelessSession.get();

        if (null == s) {
            statelessSession.set(s = sessionFactory.openStatelessSession());
        }

        return s;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * DOCUMENT ME!
     *
     * @throws HibernateException DOCUMENT ME!
     */
    public static void closeSession()
            throws HibernateException {
        Session s = (Session) session.get();

        session.set(null);

        if ((s != null) && s.isOpen()) {
            s.close();
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param sql DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Query createQuery(final String sql) {
        return assignKnownParameters(currentSession().createQuery(sql));
    }

    /**
     * DOCUMENT ME!
     *
     * @param tx DOCUMENT ME!
     */
    public static void commit(final Transaction tx) {
        tx.getClass(); // check null

        if (tx.wasCommitted()) {
            LOG.warn("Transaction was already committed [Committed="
                    + tx.wasCommitted() + ", RolledBack=" + tx.wasRolledBack()
                    + "].");
        } else {
            tx.commit();
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param tx DOCUMENT ME!
     */
    public static void rollback(final Transaction tx) {
        tx.getClass(); // check null

        if (tx.wasRolledBack()) {
            LOG.warn("Transaction was already rolled back [Committed="
                    + tx.wasCommitted() + ", RolledBack=" + tx.wasRolledBack()
                    + "].");
        } else {
            tx.rollback();
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param queryName DOCUMENT ME!
     * @param readOnly DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Query getNamedQuery(final String queryName,
            final boolean readOnly) {
        return assignKnownParameters(currentSession().getNamedQuery(queryName).setReadOnly(readOnly));
    }

    /**
     * DOCUMENT ME!
     *
     * @param clazz DOCUMENT ME!
     * @param queryName DOCUMENT ME!
     * @param readOnly DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Query getNamedQuery(final Class clazz,
            final String queryName,
            final boolean readOnly) {
        return getNamedQuery(clazz.getName() + "." + queryName, readOnly);
    }

    /**
     * DOCUMENT ME!
     *
     * @param queryName DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Query getNamedQuery(final String queryName) {
        return getNamedQuery(queryName, false);
    }

    /**
     * DOCUMENT ME!
     *
     * @param data DOCUMENT ME!
     *
     * @throws Exception DOCUMENT ME!
     */
    public static void save(final Object data)
            throws Exception {
        save(data, false);
    }

    /**
     * DOCUMENT ME!
     *
     * @param data DOCUMENT ME!
     * @param evict DOCUMENT ME!
     *
     * @throws Exception DOCUMENT ME!
     */
    public static void save(final Object data, final boolean evict)
            throws Exception {
        data.getClass(); // check null

        Session session = currentSession();

        try {
            session.save(data);

            if (evict) {
                session.flush();
                session.evict(data);
            }
        } catch (final Exception e) {
            session.clear();
            session.evict(data);

            throw e;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param data DOCUMENT ME!
     *
     * @throws Exception DOCUMENT ME!
     */
    public static void update(final Object data)
            throws Exception {
        update(data, false);
    }

    /**
     * DOCUMENT ME!
     *
     * @param data DOCUMENT ME!
     * @param evict DOCUMENT ME!
     *
     * @throws Exception DOCUMENT ME!
     */
    public static void update(final Object data, final boolean evict)
            throws Exception {
        data.getClass(); // check null

        Session session = currentSession();

        try {
            session.update(data);

            if (evict) {
                session.flush();
                session.evict(data);
            }
        } catch (final Exception e) {
            session.clear();
            session.evict(data);

            throw e;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param data DOCUMENT ME!
     *
     * @throws Exception DOCUMENT ME!
     */
    public static void saveOrUpdate(final Object data)
            throws Exception {
        saveOrUpdate(data, false);
    }

    /**
     * DOCUMENT ME!
     *
     * @param data DOCUMENT ME!
     * @param evict DOCUMENT ME!
     *
     * @throws Exception DOCUMENT ME!
     */
    public static void saveOrUpdate(final Object data, final boolean evict)
            throws Exception {
        data.getClass(); // check null

        Session session = currentSession();

        try {
            session.saveOrUpdate(data);

            if (evict) {
                session.flush();
                session.evict(data);
            }
        } catch (final Exception e) {
            session.clear();
            session.evict(data);

            throw e;
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param clazz DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Query createQuery(final Class clazz) {
        return createQuery(clazz, true);
    }

    /**
     * DOCUMENT ME!
     *
     * @param clazz DOCUMENT ME!
     * @param readOnly DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Query createQuery(final Class clazz, final boolean readOnly) {
        return createQuery("FROM " + clazz.getName(), readOnly);
    }

    /**
     * DOCUMENT ME!
     *
     * @param queryString DOCUMENT ME!
     * @param readOnly DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Query createQuery(final String queryString,
            final boolean readOnly) {
        return assignKnownParameters(currentSession().createQuery(queryString).setReadOnly(readOnly));
    }

    /**
     * DOCUMENT ME!
     *
     * @param queryName DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String getNamedQueryString(final String queryName) {
        return currentSession().getNamedQuery(queryName).getQueryString();
    }

    /**
     * DOCUMENT ME!
     *
     * @param clazz DOCUMENT ME!
     * @param queryName DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String getNamedQueryString(final Class clazz,
            final String queryName) {
        return currentSession().getNamedQuery(clazz.getName() + "." + queryName).getQueryString();
    }

    private static Query assignKnownParameters(final Query query) {
        String message = "Found known query parameter '%1$s', set it as %2$s.";

        for (final String param : query.getNamedParameters()) {
            if ("now".equals(param)) {
                Date now = new Date();
                query.setTimestamp(param, now);

                if (LOG.isDebugEnabled()) {
                    LOG.debug(String.format(message, param, now.toString()));
                }
            } else if ("today".equals(param)) {
                Date today = new Date();
                query.setDate(param, today);

                if (LOG.isDebugEnabled()) {
                    LOG.debug(String.format(message, param, today.toString()));
                }
            }
        }

        return query;
    }

    public static StatelessSession newStatelessSession()
            throws HibernateException {
        return sessionFactory.openStatelessSession();
    }

    public static void closeStatelessSession()
            throws HibernateException {
        StatelessSession s = (StatelessSession) statelessSession.get();

        statelessSession.set(null);

        if (null != s) {
            s.close();
        }
    }
}
