/*
 * Copyright (R) 2008 Advanced Research Group Co., Ltd.
 * All Rights Reserved.
 */


package com.bsd.cse.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
  */
public class JNDIDataSource
{
    //~ Static fields/initializers ·············································

    private static final Log LOG = LogFactory.getLog(JNDIDataSource.class);

    /**
     * DOCUMENT ME!
     */
    public static final String JNDI_CONTEXT = "java:comp/env/jdbc/CSE";
    private static DataSource ds;

    //~ Constructors ···························································

    private JNDIDataSource()
    {
    }

    //~ Methods ································································

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws NamingException DOCUMENT ME!
     */
    public static DataSource get()
            throws NamingException
    {
        return get(JNDI_CONTEXT);
    }

    /**
     * DOCUMENT ME!
     *
     * @param jndiContext DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws NamingException DOCUMENT ME!
     */
    public static DataSource get(final String jndiContext)
            throws NamingException
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("Looking up JNDI Data Source in " + jndiContext + "...");
        }

        Context context = new InitialContext();

        return (DataSource) context.lookup(jndiContext);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws NamingException DOCUMENT ME!
     * @throws SQLException DOCUMENT ME!
     */
    public static Connection getConnection()
            throws NamingException, SQLException
    {
        return getConnection(JNDI_CONTEXT);
    }

    /**
     * DOCUMENT ME!
     *
     * @param jndiContext DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws NamingException DOCUMENT ME!
     * @throws SQLException DOCUMENT ME!
     */
    public static Connection getConnection(final String jndiContext)
            throws NamingException, SQLException
    {
        return get(jndiContext).getConnection();
    }
}
