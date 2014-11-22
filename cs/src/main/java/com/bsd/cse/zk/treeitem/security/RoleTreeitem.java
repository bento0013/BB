/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.treeitem.security;

import com.bsd.cse.app.security.GroupCore;
import com.bsd.cse.app.security.RoleCore;
import com.bsd.cse.app.security.UserCore;
import com.bsd.cse.common.HibernateUtil;
import com.bsd.cse.model.security.Group;
import com.bsd.cse.model.security.Role;
import com.bsd.cse.model.security.User;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;

/**
 *
 * @author bento
 */
public class RoleTreeitem extends Treeitem{
    private static Log LOG = LogFactory.getLog(RoleTreeitem.class);
    private static final long serialVersionUID = -4398860828767351467L;  
    private Button setRoleBtn;
    private Long roleId;
    private String roleStrId;
    private RoleTreeitem parentItem;

    private final static String userImage = "/images/security/role.png";

    public RoleTreeitem(String label, Long roleId,String roleStrId,RoleTreeitem parentItem,Button setRoleBtn)
    {
        super(label, roleId);
        this.roleId = roleId;
        this.roleStrId = roleStrId;
        this.setId(this.roleStrId);
        this.setImage(userImage);        
        this.createEventTree();              
        this.setRoleBtn = setRoleBtn;
        HashMap<String,Object> treeMap = (HashMap<String,Object>)Executions.getCurrent().getDesktop().getAttribute("roleTree");
        if(treeMap == null)
        {
            treeMap = new HashMap<String,Object>();
            Executions.getCurrent().getDesktop().setAttribute("roleTree",treeMap);
        }

        treeMap.put(this.getId(), this);
    }

    private void createEventTree()
    {
        this.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                LOG.info("Event ON Click");
                renderPanel(event);
            }
        });

        this.addEventListener(Events.ON_OPEN, new EventListener()
        {
            @Override
            public void onEvent(Event event) throws Exception {
                LOG.info("Event ON OPEN");
                renderPanel(event);
            }

        });
    }

    private void renderPanel(Event event)
    {
        
        LOG.info("Event Name = "+event.getName());

        String data = (String)event.getData();
        if(event.getName().equalsIgnoreCase(Events.ON_OPEN))
        {
            if(!this.isOpen())
            {
                return;
            }
        }
        
        this.setSelected(Boolean.TRUE);        
        this.setOpen(Boolean.TRUE);
        
        if(this.roleId != null && this.roleId.equals(0L) && data == null)
        {
            renderTree();
            Events.postEvent(Events.ON_CLICK, this.setRoleBtn, this.roleId);
        }

        if(this.roleId != null && !this.roleId.equals(0L) && data == null)
        {            
            Events.postEvent(Events.ON_CLICK, this.setRoleBtn, this.roleId);
        }  

        
        if(data != null)
        {            
            
            renderTree();            
            HashMap<String,Object> treeMap = (HashMap<String,Object>)Executions.getCurrent().getDesktop().getAttribute("roleTree");
            RoleTreeitem item = (RoleTreeitem)treeMap.get(data);
            

            Events.postEvent(Events.ON_CLICK, item,null);
        }

    }

    private void renderTree()
    {
        try {
            List<Role> roles = RoleCore.getAllRoleTree();

            Treechildren child = null;
            if(this.getLastChild() instanceof Treechildren)
            {
                child = (Treechildren)this.getLastChild();
                child.getChildren().clear();
            }
            else
            {
                if((roles != null && roles.size() > 0)
                         )
                {
                    child = new Treechildren();
                    this.appendChild(child);
                }
            }

            for(Role role : roles)
            {
                LOG.info("Role ID = "+role.getId());
                RoleTreeitem rItem = new RoleTreeitem(
                role.getRoleName(),
                role.getId(),"r-"+role.getId(),this,this.setRoleBtn);
                child.appendChild(rItem);
            }

        } catch (Exception ex) {
            LOG.error("Error",ex);
        }
    }
}
