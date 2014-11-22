/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.backoffice;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.department.Department;
import com.bsd.cse.model.security.User;
import com.bsd.cse.zk.alertbox.AlertMessages;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author bento
 */
public class DepartmentCore {
    private Log LOG = LogFactory.getLog(DepartmentCore.class);

    public HashMap<String,Object> save(final Department object,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                
                Department objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (Department)session.get(Department.class, object.getId());
                }
                try
                {
                    if(objectDb != null )
                    {

                        objectDb.setName(object.getName());
                        objectDb.setUpdatedBy(userId);
                        objectDb.setUpdatedDate(new Date());
                        session.saveOrUpdate(objectDb);
                        session.flush();
                    }
                    else
                    {
                        object.setCreatedBy(userId);
                        object.setCreatedDate(new Date());
                        session.save(object);
                        session.flush();
                    }
                }
                catch(Exception e)
                {
                    LOG.error(e.getMessage(),e);
                    throw new Exception(e.toString());
                }
                

                map.put("results",Boolean.TRUE);
                map.put("objectId",object.getId());
                LOG.info("results = "+(Boolean)map.get("results"));
                LOG.info("groupId = "+(Long)map.get("objectId"));
            }
        }.process();

        return map;
    }

    public Boolean checkCanDelete(final Department object) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {

//                    Criteria criteria = session.createCriteria(User.class);
//                    criteria.add(Restrictions.eq("model.id",object.getId()));
//                    criteria.setProjection(Projections.count("id"));
//                    Number count = (Number)criteria.uniqueResult();
//                    map.put("results",count.intValue()>0?Boolean.FALSE:Boolean.TRUE);
                    map.put("results",Boolean.TRUE);
            }

        }.process();

        return (Boolean)map.get("results");
    }

    public HashMap<String,Object> delete(final Department object,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Department objectDb = null;
                if(object != null && object.getId() !=null)
                {
                    objectDb = (Department)session.get(Department.class, object.getId());
                }
                
                if(objectDb != null )
                {                    
                    session.delete(objectDb);
                    map.put("results",Boolean.TRUE);
                }
                else
                {
                    map.put("results",Boolean.FALSE);
                }

                
               
                LOG.info("results = "+(Boolean)map.get("results"));
            }
        }.process();

        return map;
    }

    public HashMap<String,Object> getList(final String depName) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Department.class);
                Criteria criteriaCount = session.createCriteria(Department.class);

                criteria.addOrder(Order.asc("name"));

                Conjunction con = null;
                if(!depName.trim().isEmpty())
                {
                    con = new Conjunction();
                    con.add(Restrictions.ilike("name", "%"+depName+"%"));
                }
                
                if(con != null)
                {
                    criteria.add(con);
                    criteriaCount.add(con);
                }

                criteriaCount.setProjection(Projections.count("id"));
                Number allRecords = (Number)criteriaCount.uniqueResult();
                List<Department> departments = criteria.list();
                if(departments != null && departments.size() > 0)
                {
                    for(Department department: departments)
                    {
                        department.toString();
                    }
                }
                LOG.info("department = "+departments.size());
                map.put("results",departments);
                map.put("allRecords", new Integer(allRecords.intValue()));

            }
        }.process();

        return map;
    }   
}
