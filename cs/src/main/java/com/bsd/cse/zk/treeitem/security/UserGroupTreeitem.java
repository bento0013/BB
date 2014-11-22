/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.treeitem.security;

import com.bsd.cse.app.security.GroupCore;
import com.bsd.cse.app.security.UserCore;
import com.bsd.cse.common.HibernateUtil;
import com.bsd.cse.model.security.Group;
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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;

/**
 *
 * @author bento
 */
public class UserGroupTreeitem extends Treeitem{
    private static Log LOG = LogFactory.getLog(UserGroupTreeitem.class);
    private static final long serialVersionUID = -4398860828767351467L;
    private Long userId;
    private Long groupId;
    private UserGroupTreeitem parentItem;
    private UserGroupTreeitem owner;
    private Button setUserBtn;
    private Button setGroupBtn;
    private Combobox filterActiveCbox;

    private final static String userImage = "/images/security/user.png";
    private final static String groupImage = "/images/security/group.png";
    private final static String userDisImage = "/images/security/user_disabled.png";
    private final static String groupDisImage = "/images/security/group_disabled.png";

    public UserGroupTreeitem(String label,String value, Long groupId, Long userId,UserGroupTreeitem parentItem,Button setGroupBtn,Button setUserBtn,Combobox filterActiveCbox)
    {
        super(label, value);
        this.userId = userId;
        this.groupId = groupId;
        this.setId(value);
        this.setImage(userId==null?groupImage:userImage);
        this.parentItem = parentItem;
        this.setOpen(Boolean.FALSE);
        this.createEventTree();
        this.owner = this;
        this.setGroupBtn = setGroupBtn;
        this.setUserBtn = setUserBtn;
        this.filterActiveCbox = filterActiveCbox;
        HashMap<String,Object> treeMap = (HashMap<String,Object>)Executions.getCurrent().getDesktop().getAttribute("tree");
        if(treeMap == null)
        {
            treeMap = new HashMap<String,Object>();
            Executions.getCurrent().getDesktop().setAttribute("tree",treeMap);
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
        Executions.getCurrent().getDesktop().setAttribute("userGroupCurrentItem", this);
        this.setOpen(Boolean.TRUE);
        if(this.userId == null && this.groupId != null && data == null)
        {
            renderTree();
            Events.postEvent(Events.ON_CLICK, this.setGroupBtn, this.groupId);
        }



        if(this.userId != null && data == null)
        {
            Events.postEvent(Events.ON_CLICK, this.setUserBtn, this.userId);
//            if(getRefreshList() == null || getRefreshList().isEmpty())
//            {
//                renderDetail();
//            }
        }

        
        if(data != null)
        {
            String[] ugpath = data.split(",", 2);
            LOG.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ugpath[0] = "+ugpath[0]);
            if(ugpath.length > 1)
            {
                LOG.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ugpath[1] = "+ugpath[1]);
            }
            renderTree();
            HashMap<String,Object> treeMap = (HashMap<String,Object>)Executions.getCurrent().getDesktop().getAttribute("tree");
            UserGroupTreeitem item = (UserGroupTreeitem)treeMap.get(ugpath[0]);

            Events.postEvent(Events.ON_CLICK, item, ugpath.length > 1?ugpath[1]:null);
        }



    }

    private void renderTree()
    {
        UserGroupTreeitem refreshItem = null;
        try {
//            Boolean viewAllType = (Boolean)Executions.getCurrent()etDesktop().getAttribute("viewAllType");
            List<Group> groups = GroupCore.getChildren(groupId,(String)filterActiveCbox.getSelectedItem().getValue());
            List<User> users = UserCore.getUsersInGroup(groupId,(String)filterActiveCbox.getSelectedItem().getValue());
            Treechildren child = null;
            if(this.getLastChild() instanceof Treechildren)
            {
                child = (Treechildren)this.getLastChild();
                child.getChildren().clear();
            }
            else
            {
                if((groups != null && groups.size() > 0)
                        || users != null && users.size() > 0 )
                {
                    child = new Treechildren();
                    this.appendChild(child);
                }
            }

//            if(refreshList != null)
//            {
//                LOG.info("refreshList = "+refreshList.isEmpty());
//            }
//            else
//            {
//                LOG.info("refreshList = null");
//            }

//            String refreshKey = (refreshList != null && !refreshList.isEmpty())?refreshList.pop():null;
//            LOG.info("refreshKey = "+refreshKey);
//            HttpUserSession userSession = new HttpUserSession();
//            Long userSessionId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);


            for(Group group : groups)
            {
                UserGroupTreeitem gItem = new UserGroupTreeitem(group.getGroupName(),
                    "G-"+String.valueOf(group.getId()),
                    group.getId(),null,this,this.setGroupBtn,this.setUserBtn,this.filterActiveCbox
                    );

//                if(refreshKey != null && refreshKey.equals((String)gItem.getValue()))
//                {
//                    refreshItem = gItem;
//                    refreshItem.setRefreshList(refreshList);
//                    this.refreshList = null;
//                }

                if((group.getDisabled() != null && group.getDisabled()))
                {
                    gItem.setImage(groupDisImage);
                }
                else
                {
                    gItem.setImage(groupImage);
                }
                child.appendChild(gItem);
            }

            for(User user : users)
            {
                LOG.info("USER ID = "+user.getId());
//                if((userSessionId.equals(UserCore.ADMIN_SYSTEM_ID) && userSessionId.equals(user.getId())) || !user.getId().equals(UserCore.ADMIN_SYSTEM_ID)  )
//                {
                    UserGroupTreeitem uItem = new UserGroupTreeitem(
                        user.getUsername()+(user.getFirstname() != null?"-"+user.getFirstname():"")+(user.getLastname() != null?" "+user.getLastname():""),
                        "U-"+String.valueOf(user.getId()),
                        this.groupId,user.getId(),this,this.setGroupBtn,this.setUserBtn,this.filterActiveCbox
                        );

//                    if(refreshKey != null && refreshKey.equals((String)uItem.getValue()))
//                    {
//                        refreshItem = uItem;
//                        refreshItem.setRefreshList(refreshList);
//                        this.refreshList = null;
//                    }

                    if((user.getDisabled() != null && user.getDisabled()))
                    {
                        uItem.setImage(userDisImage);
                    }
                    else
                    {
                        uItem.setImage(userImage);
                    }
                    child.appendChild(uItem);
//                }
            }

//            if(refreshItem == null)
//            {
//                LOG.info("userItem id = "+this.getId());
//                this.refreshList = null;
//                renderDetail();
//            }
//            else
//            {
//                LOG.info("refreshItem id = "+refreshItem.getId());
//                Events.postEvent(Events.ON_CLICK,refreshItem,null);
//            }




        } catch (Exception ex) {
            LOG.error("Error",ex);
        }
    }
}
