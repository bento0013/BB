/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.model.security;

import com.bsd.cse.model.AbstractPojo;

/**
 *
 * @author bento
 */
public class Team extends AbstractPojo{
    private static final long serialVersionUID = 530255097486174737L;
    private Long id;
    private String teamName;

    public Team()
    {
        super();
    }

    public Team(Long id,String teamName)
    {
        super();
        setId(id);
        setTeamName(teamName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }    
}
