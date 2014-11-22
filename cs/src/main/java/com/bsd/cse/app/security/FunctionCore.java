/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.security;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.security.Function;
import com.bsd.cse.model.security.Group;
import com.bsd.cse.model.security.Role;
import com.bsd.cse.model.security.User;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.MappingException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.Type;

/**
 *
 * @author bento
 */
public class FunctionCore {
    private static Log LOG = LogFactory.getLog(FunctionCore.class);

    public static Function getFunction(final Long functionId) throws Exception
    {
        final HashMap<String,Function> map = new HashMap<String,Function>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Function.class);
                criteria.add(Restrictions.eq("id",functionId));
                Function function = (Function)criteria.uniqueResult();
                if(function != null)
                {                   
                    function.toString();
                    session.evict(function);
                }
                
                map.put("results", function);
            }
        }.process();

        return (Function)map.get("results");
    }

    public static List<Function> getAllFunction() throws Exception
    {
        final HashMap<String,List<Function>> map = new HashMap<String,List<Function>>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Function.class);
                criteria.addOrder(Order.asc("orderNo"));
                List<Function> functions =     (List<Function>)criteria.list();
                if(functions != null && functions.size() > 0)
                {
                    for(Function function :functions)
                    {
                        function.toString();
                        session.evict(function);                                                
                    }
                }
                session.evict(functions);
                map.put("results", functions);
            }
        }.process();

        return (List<Function>)map.get("results");
    }
    
    public static Role getRole(final Long roleId) throws Exception
    {
        final HashMap<String,Role> map = new HashMap<String,Role>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Role.class);
                criteria.add(Restrictions.eq("id",roleId));
                Role role = (Role)criteria.uniqueResult();
                if(role != null)
                {                   
                    role.toString();
                    session.evict(role);
                }
                
                map.put("results", role);
            }
        }.process();

        return (Role)map.get("results");
    }

    public static Boolean checkRoleAvailable(final Long roleId) throws Exception
    {
        final HashMap<String,Boolean> map = new HashMap<String,Boolean>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                SQLQuery query = session.createSQLQuery("select count(*) from cs_role_user where role_id = :roleId");
                query.setLong("roleId", roleId);                
                Number num = (Number)query.uniqueResult();               
                map.put("results", new Boolean(num.intValue() == 0));
            }
        }.process();

        return (Boolean)map.get("results");
    }

    public static HashMap<String,Object> saveRole(final Role role,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Role roleDb = null;
                if(role != null && role.getId() !=null)
                {
                    roleDb = (Role)session.get(Role.class, role.getId());
                }
                if(roleDb != null )
                {
                    roleDb.setRoleDescription(role.getRoleDescription());
                    roleDb.setRoleName(role.getRoleName());
                    roleDb.setFunctions(role.getFunctions());                                       
                    roleDb.setUpdatedBy(userId);
                    roleDb.setUpdatedDate(new Date());
                    session.saveOrUpdate(roleDb);
                }
                else
                {
                    role.setCreatedBy(userId);
                    role.setCreatedDate(new Date());
                    session.save(role);
                    session.flush();
                    SQLQuery query = session.createSQLQuery("insert into cs_role_group(role_id,group_id) values(:roleId,:groupId)");
                    query.setLong("roleId", role.getId());
                    query.setLong("groupId", 1L);
                    query.executeUpdate();
                    SQLQuery query1 = session.createSQLQuery("insert into cs_role_user(role_id,user_id) values(:roleId,:userId)");
                    query1.setLong("roleId", role.getId());
                    query1.setLong("userId", 1L);
                    query1.executeUpdate();
                }

                map.put("results",Boolean.TRUE);
                map.put("roleId", role.getId());

            }
        }.process();
        
        return map;
    }


    public static HashMap<String,Object> deleteRole(final Role role,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Role roleDb = null;
                if(role != null && role.getId() !=null)
                {
                    roleDb = (Role)session.get(Role.class, role.getId());
                }
                if(roleDb != null )
                {                   
                    session.delete(roleDb);
                }               

                map.put("results",Boolean.TRUE);                

            }
        }.process();

        return map;
    }

    public static String getTreeLabel(final Long userId) throws Exception
    {
        final HashMap<String,String> map = new HashMap<String,String>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(User.class);
                criteria.add(Restrictions.eq("id",userId));
                User user = (User)criteria.uniqueResult();
                StringBuffer str = new StringBuffer(1024);
                if(user != null)
                {
                    
                    if(user.getGroup() != null)
                    {
                        str.append(getGroupLabel(user.getGroup().getParent()));
                        str.append("G-"+user.getGroup().getId());
                        str.append(",");
                    }
                    str.append("U-"+user.getId());
                }

                map.put("results", str.toString());
            }
        }.process();

        return (String)map.get("results");
    }

    private static String getGroupLabel(Group group)
    {
        StringBuffer str = new StringBuffer(1024);
        if(group != null)
        {
            str.append(getGroupLabel(group.getParent()));
            str.append("G-"+group.getId());
            str.append(",");
            return str.toString();
        }
        return "";
    }
}
