/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.security;

import com.bsd.cse.app.backoffice.*;
import com.bsd.cse.common.Configuration;
import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.backoffice.department.Department;
import com.bsd.cse.model.security.Setting;
import com.bsd.cse.model.security.User;
import com.bsd.cse.model.security.UserInfo;
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

/**
 *
 * @author bento
 */
public class SettingCore {
    private Log LOG = LogFactory.getLog(SettingCore.class);

    public HashMap<String,Object> saveSetting(final Setting setting,final Long userId) throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Setting settingDb = null;
                if(setting != null && setting.getId() !=null)
                {
                    settingDb = (Setting)session.get(Setting.class, setting.getId());
                }
                if(settingDb != null )
                {

                    settingDb.setValue(setting.getValue());
                    UserInfo userInfo = new UserInfo();
                    userInfo.setId(userId);
                    settingDb.setUpdatedBy(userInfo);
                    settingDb.setUpdatedDate(new Date());
                    session.saveOrUpdate(settingDb);
                    map.put("results",Boolean.TRUE);                    

                }
                else
                {                   
                    map.put("results",Boolean.FALSE);
                }

            }
        }.process();

        return map;
    }
    
    public HashMap<String,Object> getList() throws Exception
    {
        final HashMap<String,Object> map = new HashMap<String,Object>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Setting.class);
                Criteria criteriaCount = session.createCriteria(Setting.class);

                criteria.addOrder(Order.asc("description"));
                criteriaCount.setProjection(Projections.count("id"));
                Number allRecords = (Number)criteriaCount.uniqueResult();
                List<Setting> settings = criteria.list();
                if(settings != null && settings.size() > 0)
                {
                    for(Setting setting: settings)
                    {
                        setting.toString();
                    }
                }
                LOG.info("settings = "+settings.size());
                map.put("results",settings);
                map.put("allRecords", new Integer(allRecords.intValue()));

            }
        }.process();

        return map;
    }   


}
