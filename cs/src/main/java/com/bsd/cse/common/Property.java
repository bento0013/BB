/*
 * $Id: Property.java,v 1.1 2010/09/23 14:14:30 thanasit Exp $
 *
 * Copyright (C) 2007 Advanced Research Group Co., Ltd. (ARG).
 *
 * All Rights Researved.
 */


package com.bsd.cse.common;

import java.io.Serializable;

/**
 * DOCUMENT ME!
 *
 * @author <a href="mailto:fuangchaij@ar.co.th">Fuangchai Jumdermvuttiwat</a>
 * @version $Revision: 1.1 $
  */
public class Property
    implements Serializable
{
    //~ Instance fields ························································

    private String key;
    private String value;

    //~ Constructors ···························································

    /**
     * Creates a new Property object.
     */
    public Property()
    {
        super();
    }

    /**
     * Creates a new Property object.
     *
     * @param key DOCUMENT ME!
     * @param value DOCUMENT ME!
     */
    public Property(final String key, final String value)
    {
        super();
        this.key = key;
        this.value = value;
    }

    //~ Methods ································································

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getKey()
    {
        return this.key;
    }

    /**
     * DOCUMENT ME!
     *
     * @param key DOCUMENT ME!
     */
    public void setKey(final String key)
    {
        this.key = key;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getValue()
    {
        return this.value;
    }

    /**
     * DOCUMENT ME!
     *
     * @param value DOCUMENT ME!
     */
    public void setValue(final String value)
    {
        this.value = value;
    }
}
