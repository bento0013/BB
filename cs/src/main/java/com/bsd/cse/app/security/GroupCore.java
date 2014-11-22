/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.security;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.security.Group;
import com.bsd.cse.model.security.Role;
import com.bsd.cse.model.security.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author bento
 */
public class GroupCore {
    private static Log LOG = LogFactory.getLog(GroupCore.class);

    public static List<Group> getChildren(final Long groupId,final String filterValue) throws Exception
    {
        final HashMap<String,List<Group>> map = new HashMap<String,List<Group>>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Group.class);
                criteria.add(Restrictions.eq("parent.id",groupId));
                criteria.addOrder(Order.asc("groupName"));
                if(!filterValue.trim().isEmpty())
                {
                    criteria.add(Restrictions.eq("disabled",Boolean.FALSE));
                }
                List<Group> groups =     (List<Group>)criteria.list();
                if(groups != null && groups.size() > 0)
                {
                    for(Group group :groups)
                    {
                        group.getGroupName();
                        session.evict(group);
                        group.setRoles(null);
                        group.toString();
                    }
                }
                session.evict(groups);
                map.put("results", groups);
            }
        }.process();

        return (List<Group>)map.get("results");
    }

    public static List<Group> getChildrenWithPermission(final Long groupId) throws Exception
    {
        final HashMap<String,List<Group>> map = new HashMap<String,List<Group>>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Group.class);
                criteria.add(Restrictions.eq("parent.id",groupId));
                criteria.addOrder(Order.asc("groupName"));
                List<Group> groups =     (List<Group>)criteria.list();
                if(groups != null && groups.size() > 0)
                {
                    for(Group group :groups)
                    {
                        group.getGroupName();
                        group.getRoles().toString();
                        session.evict(group);
                        group.setParent(null);
                        group.toString();
                    }
                }
                session.evict(groups);
                map.put("results", groups);
            }
        }.process();

        return (List<Group>)map.get("results");
    }

    public static HashMap<String,Object> saveGroup(final Group group,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Group groupDb = null;
                if(group != null && group.getId() !=null)
                {
                    groupDb = (Group)session.get(Group.class, group.getId());
                }
                if(groupDb != null )
                {
                    
                    groupDb.setGroupName(group.getGroupName());
                    groupDb.setGroupDescription(group.getGroupDescription());
                    groupDb.setDisabled(group.getDisabled());
                    if(groupDb.getParent() != null)
                    {
                        groupDb.setParent(group.getParent());
                    }
                    groupDb.setRoles(group.getRoles());
                    List<Long> roleIds = new ArrayList<Long>();
                    for(Role role:group.getRoles())
                    {
                        roleIds.add(role.getId());
                    }
                    groupDb.setUpdatedBy(userId);
                    groupDb.setUpdatedDate(new Date());
                    List<Long> groupIds = getGroupBelowMe(groupDb.getId(), session);
                    if(groupIds != null && groupIds.size() > 0)
                    {
                        SQLQuery query = session.createSQLQuery(
                                "delete from cs_role_group where group_id in(:ids) " +
                                (roleIds.size()>0?"and role_id not in (:roles)":"") );

                        query.setParameterList("ids", groupIds);

                        if(roleIds.size() > 0)
                        {
                            query.setParameterList("roles", roleIds);
                        }
                        query.executeUpdate();

                        if(groupDb.getDisabled() != null && groupDb.getDisabled())
                        {
                            SQLQuery disbledquery = session.createSQLQuery(
                                "update cs_group set disabled=:disabled where group_id in(:ids)");
                            disbledquery.setBoolean("disabled", Boolean.TRUE);
                            disbledquery.setParameterList("ids", groupIds);
                            
                            disbledquery.executeUpdate();
                        }
                    }
                    List<Long> userIds = getUserBelowMe(groupDb.getId(), session);
                    if(userIds != null && userIds.size()>0)
                    {
                        SQLQuery userQuery = session.createSQLQuery(
                                "delete from cs_role_user where user_id in(:ids) " +
                                (roleIds.size()>0?"and role_id not in (:roles)":"") );

                        userQuery.setParameterList("ids", userIds);

                        if(roleIds.size() > 0)
                        {
                            userQuery.setParameterList("roles", roleIds);
                        }

                        userQuery.executeUpdate();
                        if(groupDb.getDisabled() != null && groupDb.getDisabled())
                        {
                            SQLQuery disbledquery = session.createSQLQuery(
                                "update cs_user set disabled=:disabled where user_id in(:ids)");
                            disbledquery.setBoolean("disabled", Boolean.TRUE);
                            disbledquery.setParameterList("ids", userIds);
                            disbledquery.executeUpdate();
                        }
                    }

                    
                    session.saveOrUpdate(groupDb);
                }
                else
                {
                    group.setCreatedBy(userId);
                    group.setCreatedDate(new Date());
                    session.save(group);
                }
                
                map.put("results",Boolean.TRUE);
                map.put("groupId",group.getId());
                LOG.info("results = "+(Boolean)map.get("results"));
                LOG.info("groupId = "+(Long)map.get("groupId"));
            }
        }.process();
        
        return map;
    }

    public static List<Long> getGroupBelowMe(Long groupId,Session session)
    {
        LOG.info("++++++++++++++++++++++aaaaaaaaaaaaaaa+++++++++++++++++++++++");
        Criteria criteria = session.createCriteria(Group.class);
        criteria.add(Restrictions.eq("parent.id", groupId));
        criteria.setProjection(Projections.property("id"));
        List<Long> list = (List<Long>)criteria.list();
        if(list != null)
        {
            LOG.info("list = "+list.size());
        }
        else
        {
            LOG.info("list is null");
        }
        List<Long> tmpList= new ArrayList<Long>();
        for(Long qgroupId:list)
        {
            tmpList.addAll(getGroupBelowMe(qgroupId, session));
        }

        if(tmpList != null && tmpList.size() > 0)
        {
            list.addAll(tmpList);
        }
        
        return (List<Long>)list;
    }

    public static List<Long> getUserBelowMe(Long groupId,Session session)
    {
        LOG.info("++++++++++++++++++++ gggggggggggggggg +++++++++++++++++++++++++");
        Criteria criteria = session.createCriteria(Group.class);
        criteria.add(Restrictions.eq("parent.id", groupId));
        criteria.setProjection(Projections.property("id"));
        List<Long> list = (List<Long>)criteria.list();
        if(list != null)
        {
            LOG.info("list = "+list.size());
        }
        else
        {
            LOG.info("list is null");
        }
        List<Long> tmpList= new ArrayList<Long>();
        Criteria usercriteria = session.createCriteria(User.class);
        usercriteria.add(Restrictions.eq("group.id", groupId));
        usercriteria.setProjection(Projections.property("id"));
        List<Long> userList = (List<Long>)usercriteria.list();
        for(Long qgroupId:list)
        {
            tmpList.addAll(getUserBelowMe(qgroupId, session));
        }

        if(userList != null && userList.size() > 0)
        {
            tmpList.addAll(userList);
        }

        return (List<Long>)tmpList;
    }

    public static Group getGroup(final Long groupId) throws Exception
    {
        final HashMap<String,Group> map = new HashMap<String,Group>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Group.class);
                criteria.add(Restrictions.eq("id",groupId));
                Group group = (Group)criteria.uniqueResult();
                if(group != null)
                {                   
                    group.getGroupName();
                    Group parent = group.getParent();
                    if(parent != null)
                    {
                        parent.getGroupName();                        
                        session.evict(parent);
                        parent.setRoles(null);
                        parent.setParent(null);
                    }                    
                    group.getRoles().toString();
                    session.evict(group);                    
                    group.toString();
                }
                
                map.put("results", group);
            }
        }.process();

        return (Group)map.get("results");
    }

    public static Group getGroupWithPermission(final Long groupId) throws Exception
    {
        final HashMap<String,Group> map = new HashMap<String,Group>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Group.class);
                criteria.add(Restrictions.eq("id",groupId));
                Group group = (Group)criteria.uniqueResult();
                if(group != null)
                {
                    group.getGroupName();
                    group.getRoles().toString();
                    session.evict(group);
                    group.setParent(null);
                    group.toString();
                }

                map.put("results", group);
            }
        }.process();

        return (Group)map.get("results");
    }

    public static String getTreeLabel(final Long groupId,final Long userGroupId) throws Exception
    {
        final HashMap<String,String> map = new HashMap<String,String>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Group.class);
                criteria.add(Restrictions.eq("id",groupId));
                Group group = (Group)criteria.uniqueResult();
                StringBuffer str = new StringBuffer(1024);
                if(group != null && !group.getId().equals(userGroupId))
                {

                    if(group.getParent() != null && !group.getParent().getId().equals(userGroupId))
                    {
                        str.append(getGroupLabel(group.getParent().getParent(),userGroupId));
                        str.append("G-"+group.getParent().getId());
                        str.append(",");
                    }
                    else if(group.getParent() != null && group.getParent().getId().equals(userGroupId))
                    {
                        str.append("G-"+group.getParent().getId());
                        str.append(",");
                    }
                    str.append("G-"+group.getId());
                }
                else if(group != null && group.getId().equals(userGroupId))
                {
                    str.append("G-"+group.getId());
                }

                map.put("results", str.toString());
            }
        }.process();

        return (String)map.get("results");
    }

    private static String getGroupLabel(Group group,Long userGroupId)
    {
        StringBuffer str = new StringBuffer(1024);
        if(group != null && !group.getId().equals(userGroupId))
        {
            str.append(getGroupLabel(group.getParent(),userGroupId));
            str.append("G-"+group.getId());
            str.append(",");
            return str.toString();
        }
        else if(group != null && group.getId().equals(userGroupId))
        {
            str.append("G-"+group.getId());
            str.append(",");
            return str.toString();
        }
        return "";
    }
}
