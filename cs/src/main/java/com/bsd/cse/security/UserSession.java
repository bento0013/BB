/*
 * $Id: UserSession.java,v 1.1 2010/09/23 14:43:49 thanasit Exp $
 *
 * Copyright (C) 2007 Advanced Research Group Co., Ltd. (ARG).
 *
 * All Rights Researved.
 */


package com.bsd.cse.security;

import java.util.Map;

/**
 * DOCUMENT ME!
 *
 * @author <a href="mailto:chalermpongc@ar.co.th">Chalermpong Chaiyawan</a>
 * @version $Revision: 1.1 $
  */
public interface UserSession
{
    //~ Methods ································································

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean isValid();

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getLoginId();

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Map<String, ?> credentials();

    public Map<String, ?> getUserInformation();

    public static final String PERMISSIONS_KEY = (UserSession.class.getName() + ".permissions");
}
