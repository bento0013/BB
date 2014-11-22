/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.app.security;

import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.security.Group;
import com.bsd.cse.model.security.Role;
import com.bsd.cse.model.security.Team;
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
public class TeamCore {
    private static Log LOG = LogFactory.getLog(TeamCore.class);

    public static List<Team> getAll() throws Exception
    {
        final HashMap<String,List<Team>> map = new HashMap<String,List<Team>>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Team.class);
                criteria.addOrder(Order.asc("teamName"));
                List<Team> teams = (List<Team>)criteria.list();
                if(teams != null && teams.size() > 0)
                {
                    
                    for(Team team :teams)
                    {
                        team.getTeamName();
                        session.evict(team);
                        team.toString();                        
                    }
                }


                session.evict(teams);
                Team team = new Team(0L,"N/A");
                teams.add(0, team);
                map.put("results", teams);
            }
        }.process();

        return (List<Team>)map.get("results");
    }

    public static Team getTeam(final Long teamId) throws Exception
    {
        final HashMap<String,Team> map = new HashMap<String,Team>();
        new TransactionalProcessor(LOG) {

            @Override
            public void process(Session session, Transaction tx) throws Exception {
                Criteria criteria = session.createCriteria(Team.class);
                criteria.add(Restrictions.eq("id",teamId));                
                criteria.setMaxResults(1);
                Team team= (Team)criteria.uniqueResult();
                if(team != null )
                {
                    team.getTeamName();                  
                }

             
                map.put("results", team);
            }
        }.process();

        return (Team)map.get("results");
    }  
}
