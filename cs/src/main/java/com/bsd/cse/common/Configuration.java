/*
 * Copyright (R) 2008 Advanced Research Group Co., Ltd.
 * All Rights Reserved.
 */


package com.bsd.cse.common;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.commons.configuration.DatabaseConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.net.URL;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.2 $
  */
public class Configuration
{
    //~ Static fields/initializers ·············································

    private static final Log LOG = LogFactory.getLog(Configuration.class);
    private static org.apache.commons.configuration.Configuration config;
    private static DatabaseConfiguration dbConfig;

    //~ Constructors ···························································

    private Configuration()
    {
    }

    //~ Methods ································································

    /**
     * FIXME:  This still be unsafe way to invalidate current instance of configuration
     * even we do synchronizing it.
     */
    public static synchronized void invalidate()
    {
        config = null;
    }

    private static org.apache.commons.configuration.Configuration get()
    {
        if (null == config)
        {
            synchronized (Configuration.class)
            {
                if (null == config)
                {
                    LOG.info("Initializing configuration...");

                    try
                    {
                        dbConfig = new DatabaseConfiguration(
                                    JNDIDataSource.get(),
                                    "cs_sys_para_config",
                                    "name",
                                    "value");
                        CompositeConfiguration cc = new CompositeConfiguration();

                        cc.addConfiguration(new SystemConfiguration());
                        cc.addConfiguration(dbConfig);

                        config = cc;
                        LOG.info("Configuration initialized.");
                    }
                    catch (Exception e)
                    {
                        LOG.error(e.getMessage(), e);

                        throw new RuntimeException(e);
                    }
                }
            }
        }

        return config;
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     * @param value DOCUMENT ME!
     */
    public static void addProperty(String key, Object value)
    {
        get().addProperty(key, value);
    }

    /**
     * DOCUMENT ME!
     */
    public static void clear()
    {
        get().clear();
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     */
    public static void clearProperty(String key)
    {
        get().clearProperty(key);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static boolean containsKey(String key)
    {
        return get().containsKey(key);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static BigDecimal getBigDecimal(String key)
    {
        return get().getBigDecimal(key);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     * @param defaultValue DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static BigDecimal getBigDecimal(String key, BigDecimal defaultValue)
    {
        return get().getBigDecimal(key, defaultValue);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static BigInteger getBigInteger(String key)
    {
        return get().getBigInteger(key);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     * @param defaultValue DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static BigInteger getBigInteger(String key, BigInteger defaultValue)
    {
        return get().getBigInteger(key, defaultValue);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static boolean getBoolean(String key)
    {
        return get().getBoolean(key);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     * @param defaultValue DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static boolean getBoolean(String key, boolean defaultValue)
    {
        return get().getBoolean(key, defaultValue);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     * @param defaultValue DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Boolean getBoolean(String key, Boolean defaultValue)
    {
        return get().getBoolean(key, defaultValue);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static byte getByte(String key)
    {
        return get().getByte(key);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     * @param defaultValue DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static byte getByte(String key, byte defaultValue)
    {
        return get().getByte(key, defaultValue);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     * @param defaultValue DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Byte getByte(String key, Byte defaultValue)
    {
        return get().getByte(key, defaultValue);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static double getDouble(String key)
    {
        return get().getDouble(key);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     * @param defaultValue DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static double getDouble(String key, double defaultValue)
    {
        return get().getDouble(key, defaultValue);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     * @param defaultValue DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Double getDouble(String key, Double defaultValue)
    {
        return get().getDouble(key, defaultValue);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static float getFloat(String key)
    {
        return get().getFloat(key);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     * @param defaultValue DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static float getFloat(String key, float defaultValue)
    {
        return get().getFloat(key, defaultValue);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     * @param defaultValue DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Float getFloat(String key, Float defaultValue)
    {
        return get().getFloat(key, defaultValue);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static int getInt(String key)
    {
        return get().getInt(key);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     * @param defaultValue DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static int getInt(String key, int defaultValue)
    {
        return get().getInt(key, defaultValue);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     * @param defaultValue DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Integer getInteger(String key, Integer defaultValue)
    {
        return get().getInteger(key, defaultValue);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Iterator getKeys()
    {
        return get().getKeys();
    }

    /**
     * DOCUMENT ME!
     *
     * @param prefix DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Iterator getKeys(String prefix)
    {
        return get().getKeys(prefix);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static List getList(String key)
    {
        return get().getList(key);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     * @param defaultValue DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static List getList(String key, List defaultValue)
    {
        return get().getList(key, defaultValue);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static long getLong(String key)
    {
        return get().getLong(key);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     * @param defaultValue DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static long getLong(String key, long defaultValue)
    {
        return get().getLong(key, defaultValue);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     * @param defaultValue DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Long getLong(String key, Long defaultValue)
    {
        return get().getLong(key, defaultValue);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Properties getProperties(String key)
    {
        return get().getProperties(key);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Object getProperty(String key)
    {
        return get().getProperty(key);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static short getShort(String key)
    {
        return get().getShort(key);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     * @param defaultValue DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static short getShort(String key, short defaultValue)
    {
        return get().getShort(key, defaultValue);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     * @param defaultValue DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Short getShort(String key, Short defaultValue)
    {
        return get().getShort(key, defaultValue);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String getString(String key)
    {
        return get().getString(key);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     * @param defaultValue DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String getString(String key, String defaultValue)
    {
        return get().getString(key, defaultValue);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static String[] getStringArray(String key)
    {
        return get().getStringArray(key);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static boolean isEmpty()
    {
        return get().isEmpty();
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     * @param value DOCUMENT ME!
     */
    public static void setProperty(String key, Object value)
    {
        //dbConfig.setProperty(key, value); // works, but we lost descr field!
        final String SQL = "UPDATE sys_parameter_config SET value = ? WHERE name = ?";

        Connection con = null;
        PreparedStatement stm = null;

        try
        {
            con = getDataSource().getConnection();
            stm = con.prepareStatement(SQL);

            stm.setString(1, (String) value);
            stm.setString(2, key);

            if (0 == stm.executeUpdate())
            {
                throw new RuntimeException(String.format("Property '%1$s' could not be found!", key));
            }
        }
        catch (SQLException e)
        {
            LOG.error(e.getMessage(), e);
        }
        finally
        {
            if (null != stm)
            {
                try
                {
                    stm.close();
                }
                catch (SQLException ignored)
                {
                }
            }

            if (null != con)
            {
                try
                {
                    con.close();
                }
                catch (SQLException ignored)
                {
                }
            }
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param prefix DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static org.apache.commons.configuration.Configuration subset(String prefix)
    {
        return get().subset(prefix);
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Property[] getPropertyArray(final String key)
    {
        String[] props = getStringArray(key);
        Property[] result = new Property[props.length];

        for (int index = 0; index < props.length; ++index)
        {
            String[] p = props[index].split("=");

            result[index] = new Property(p[0], p[1]);
        }

        return result;
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static javax.sql.DataSource getDataSource()
    {
        return dbConfig.getDatasource();
    }
}
