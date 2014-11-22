/*
 * $Id: Users.java,v 1.2 2011/01/05 02:56:23 thanasit Exp $
 *
 * Copyright (C) 2007 Advanced Research Group Co., Ltd. (ARG).
 *
 * All Rights Researved.
 */


package com.bsd.cse.security;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.common.Configuration;
import com.bsd.cse.common.HibernateUtil;

//import com.arg.cbs.model.Setting;
import com.bsd.cse.model.security.Function;
import com.bsd.cse.model.security.Group;
import com.bsd.cse.model.security.Role;
import com.bsd.cse.model.security.User;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * DOCUMENT ME!
 *
 * @author <a href="mailto:chalermpongc@ar.co.th">Chalermpong Chaiyawan</a>
 * @version $Revision: 1.2 $
 */
public class Users
{
    //~ Static fields/initializers ·············································

    private static final Log LOG = LogFactory.getLog(Users.class);

    /**
     * DOCUMENT ME!
     */
    public static final String LAST_LOGIN_TIME = "LastLoginTime";

    /**
     * DOCUMENT ME!
     */
    public static final String DELETED = "Deleted";

    public static final String DISABLED = "Disabeld";

    /**
     * DOCUMENT ME!
     */
    public static final String PERMISSIONS = "Permissions";

    /**
     * DOCUMENT ME!
     */
    public static final String SYSADMIN = "SysAdmin";

    /**
     * DOCUMENT ME!
     */
    public static final String FULL_NAME = "FullName";

    /**
     * DOCUMENT ME!
     */
    public static final String GROUP = "Group";
    public static final String MAINGROUP = "MainGroup";

    public static final String  SYSTEMID = "SystemId";
    public static final String  LOCALE = "Locale";

    public static final String  PASSWORD_EXPIRED_DATE = "PasswordExpiredDate";
    public static final String  CHANGE_PASSWORD_NEXT_LOGON = "ChangePasswordNextLogon";

    public static final String  ACCESS_PERMIT = "accessPermit";

    public static final String  MAX_PASSWORD_DURATION = "MaxPasswordDuration";

    public static final String  SESSION_ID = "uuid";

    public static final String  IP_ADDRESS = "ipAddress";
    
    public static final String  LOGINID = "loginId";
    public static final String  SESSION_TIMEOUT = "session_timeout";
    public static final String  LOGON_TIME = "loginTime";
    public static final String  TEAM_ID = "teamID";

    //~ Constructors ···························································

    private Users()
    {
    }

    //~ Methods ································································

    /**
     * DOCUMENT ME!
     *
     * @param loginId DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws Exception DOCUMENT ME!
     */

    public static Map<String, Object> credentials(final String loginId)
        throws Exception
    {
        if(LOG.isDebugEnabled())
        {
            LOG.debug("---------------------> credentials <------------------------------");
        }
        final Map<String, Object> CRE = new HashMap<String, Object>();

        (new TransactionalProcessor(LOG)
            {
                public void process(final Session session, final Transaction tx)
                    throws Exception
                {
                    User user = locate(loginId, session);
                    List<Long> permission_ids = permissions(user);

//                   ACCESS_PERMIT
                    CRE.put(LAST_LOGIN_TIME, new Date());
//                    CRE.put(STATUS,
//                            Boolean.TRUE.equals(user.getStatus())
//                            ? Boolean.TRUE : Boolean.FALSE);
                    CRE.put(LOGINID,loginId);
                    CRE.put(DISABLED,user.getDisabled());                    
                    CRE.put(PERMISSIONS, permission_ids);
                    CRE.put(TEAM_ID,user.getTeam());
                    CRE.put(FULL_NAME, (user.getFirstname()!=null?user.getFirstname():null)+" "+(user.getLastname()!=null?user.getLastname():null));                   
                    Group group = user.getGroup();
                    if(group != null)
                    {
//                        group.getRoles().toString(); //Get Object before evict
//                        session.evict(group);
//                        group.toString(); //Get Object Info
                        CRE.put(GROUP, group);
                    }                                      
                    CRE.put(SYSTEMID, user.getId());

                }
            }).process();

        return CRE;
    }

    
    private static List<Long> permissions(final User user)
        throws Exception
    {
        List<Long> permission_ids = new ArrayList<Long>();

        for(Role role : user.getRoles())
        {
            for (final Function permission : role.getFunctions())
            {
                if(LOG.isDebugEnabled())
                {
                    LOG.debug("permission = "+permission.getFunctionName());
                }
                Functions.allChildrenIDs(permission, permission_ids);
            }
        }

        return permission_ids;
    }

    private static User locate(final String loginId, final Session session)
        throws Exception
    {
        User user =
            (User) session.createCriteria(User.class)
                              .setFetchMode("roles", FetchMode.JOIN)
                              .add(Restrictions.eq("username", loginId))
                              .uniqueResult();

        if (null == user)
        {
            throw new Exception(String.format("User '%1$s' not found.", loginId));
        }
        user.toString();
        session.evict(user);        

        return user;
    }

    public static Map<String, Object> loadUser(final String loginId)
        throws Exception
    {
        final Map<String, Object> hash = new HashMap<String, Object>();
        (new TransactionalProcessor(LOG)
            {
                public void process(final Session session, final Transaction tx)
                    throws Exception
                {
                    User user = (User) session.createCriteria(User.class)
                                          .add(Restrictions.eq("loginId", loginId))
                                          .uniqueResult();

                    if (null == user)
                    {
                        user = null;
                    }else
                    {
                        session.evict(user);
                    }
                    hash.put("user", user);
                }
        }).process();

        return hash;
    }

    public static Map<String, Object> loadUser(final Long id)
        throws Exception
    {
        final Map<String, Object> hash = new HashMap<String, Object>();
        (new TransactionalProcessor(LOG)
            {
                public void process(final Session session, final Transaction tx)
                    throws Exception
                {
                    if(null == id)
                    {
                        hash.put("user", null);
                    }
                    else
                    {
                        User user = (User) session.createCriteria(User.class)
                                              .add(Restrictions.eq("id", id))
                                              .uniqueResult();

                        if (null == user)
                        {
                            user = null;
                        }else
                        {
                            session.evict(user);
                        }
                        hash.put("user", user);
                    }
                }
        }).process();

        return hash;
    }
       
}
