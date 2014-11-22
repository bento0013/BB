/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.security;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.security.Group;
import com.bsd.cse.model.security.User;
import com.bsd.util.DigestUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author bento
 */
public class UserCore {
    private static Log LOG = LogFactory.getLog(UserCore.class);

    private static String DIGEST_PASSWORD = "SHA-256";
    private static String CHARSET = "UTF-8";

    public static List<User> getUsersInGroup(final Long groupId,final String filterValue) throws Exception
    {
        final HashMap<String,List<User>> map = new HashMap<String,List<User>>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(User.class);
                criteria.add(Restrictions.eq("group.id",groupId));
                if(!filterValue.trim().isEmpty())
                {
                    criteria.add(Restrictions.eq("disabled",Boolean.FALSE));
                }
                List<User> users =     (List<User>)criteria.list();
                if(users != null && users.size() > 0)
                {
                    for(User user :users)
                    {                        
                        Group group = user.getGroup();
                        group.getGroupName();
                        session.evict(group);
                        group.setRoles(null);
                        group.toString();
                        session.evict(user);
                        user.setRoles(null);
                        user.toString();
                    }
                }
                session.evict(users);
                map.put("results", users);
            }
        }.process();

        return (List<User>)map.get("results");
    }

    public static User getUser(final Long userId) throws Exception
    {
        final HashMap<String,User> map = new HashMap<String,User>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(User.class);
                criteria.add(Restrictions.eq("id",userId));
                User user = (User)criteria.uniqueResult();
                if(user != null)
                {                   
                    Group group = user.getGroup();
                        group.getGroupName();
                        session.evict(group);
                        group.setRoles(null);
                        group.toString();
                        user.getRoles().toString();
                        session.evict(user);                        
                        user.toString();
                }
                
                map.put("results", user);
            }
        }.process();

        return (User)map.get("results");
    }

    public static User getUser(final String username) throws Exception
    {
        final HashMap<String,User> map = new HashMap<String,User>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(User.class);
                criteria.add(Restrictions.eq("username",username));
                User user = (User)criteria.uniqueResult();
                if(user != null)
                {
                    Group group = user.getGroup();
                        group.getGroupName();
                        session.evict(group);
                        group.setRoles(null);
                        group.toString();
                        user.getRoles().toString();
                        session.evict(user);
                        user.toString();
                }

                map.put("results", user);
            }
        }.process();

        return (User)map.get("results");
    }

    public static String encPassword(String password)
    {
       return DigestUtil.hashPassword(DIGEST_PASSWORD, password, CHARSET);
    }

    public static HashMap<String,Object> saveUser(final User user,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                User userDb = null;
                if(user != null && user.getId() !=null)
                {
                    userDb = (User)session.get(User.class, user.getId());
                }
                if(userDb != null )
                {

                    userDb.setUsername(user.getUsername().toUpperCase());
                    userDb.setFirstname(user.getFirstname());
                    userDb.setLastname(user.getLastname());
                    userDb.setImage(user.getImage());
                    userDb.setLanguage(user.getLanguage());
                    if(user.getPassword() != null)
                    {                        
                        userDb.setPassword(DigestUtil.hashPassword(DIGEST_PASSWORD, user.getPassword(), CHARSET));
                    }
                    else
                    {
                        userDb.setPassword(DigestUtil.hashPassword(DIGEST_PASSWORD, "", CHARSET));
                    }
                    userDb.setDisabled(user.getDisabled());
                    if(userDb.getGroup() != null)
                    {
                        userDb.setGroup(user.getGroup());
                    }

                    userDb.setTeam(user.getTeam());


                    userDb.setRoles(user.getRoles());                    
                    userDb.setUpdatedBy(userId);
                    userDb.setUpdatedDate(new Date());                    


                    session.saveOrUpdate(userDb);
                }
                else
                {
                    if(user.getPassword() != null)
                    {
                        user.setPassword(DigestUtil.hashPassword(DIGEST_PASSWORD, user.getPassword(), CHARSET));
                    }
                    user.setCreatedBy(userId);
                    user.setCreatedDate(new Date());
                    session.save(user);
                }

                map.put("results",Boolean.TRUE);
                map.put("userId", user.getId());

            }
        }.process();
        
        return map;
    }

    public static String getTreeLabel(final Long userId,final Long userGroupId) throws Exception
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
                    
                    if(user.getGroup() != null && !user.getGroup().getId().equals(userGroupId))
                    {
                        str.append(getGroupLabel(user.getGroup().getParent(),userGroupId));
                        str.append("G-"+user.getGroup().getId());
                        str.append(",");
                    }
                    else if(user.getGroup() != null && user.getGroup().getId().equals(userGroupId))
                    {
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

    private static String getGroupLabel(Group group,Long userGroupId)
    {
        StringBuffer str = new StringBuffer(1024);
        if(group != null && !userGroupId.equals(group.getId()))
        {
            str.append(getGroupLabel(group.getParent(),userGroupId));
            str.append("G-"+group.getId());
            str.append(",");
            return str.toString();
        }
        else if(group != null && userGroupId.equals(group.getId()))
        {
            str.append("G-"+group.getId());
            str.append(",");
            return str.toString();
        }
        return "";
    }

    public List<User> getAllList() throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(User.class);

                criteria.add(Restrictions.eq("disabled", Boolean.FALSE));
                criteria.addOrder(Order.asc("username"));

                List<User> users = criteria.list();
                if(users != null && users.size() > 0)
                {
                    for(User user: users)
                    {
                        user.getFirstname();
                        session.evict(user);
                        user.setGroup(null);
                        user.setRoles(null);
                        user.toString();
                    }
                }
                map.put("results",users);


            }
        }.process();

        return (List<User>)map.get("results");
    }

    public List<User> getAllListWithPermission(final Long permission) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(User.class);
                criteria.createAlias("roles", "roles");
                criteria.createAlias("roles.functions", "functions");
                criteria.add(Restrictions.eq("functions.id", permission));
                criteria.add(Restrictions.eq("disabled", Boolean.FALSE));
                criteria.addOrder(Order.asc("username"));
                criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

                List<User> users = criteria.list();
                if(users != null && users.size() > 0)
                {
                    for(User user: users)
                    {
                        user.getFirstname();
                        session.evict(user);
                        user.setGroup(null);
                        user.setRoles(null);
                        user.toString();
                    }
                }
                map.put("results",users);


            }
        }.process();

        return (List<User>)map.get("results");
    }  
}
