/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.security;

import com.bsd.cse.app.security.FunctionCore;
import com.bsd.cse.app.security.GroupCore;
import com.bsd.cse.app.security.RoleCore;
import com.bsd.cse.app.security.TeamCore;
import com.bsd.cse.app.security.UserCore;
import com.bsd.cse.common.HibernateUtil;
import com.bsd.cse.model.security.Function;
import com.bsd.cse.model.security.Group;
import com.bsd.cse.model.security.Role;
import com.bsd.cse.model.security.Team;
import com.bsd.cse.model.security.User;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.listbox.ZKListboxs;
import com.bsd.cse.zk.treeitem.security.FunctionTreeitem;
import com.bsd.cse.zk.treeitem.security.RoleTreeitem;
import com.bsd.cse.zk.treeitem.security.UserGroupTreeitem;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Image;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.West;

/**
 *
 * @author bento
 */
public class RoleController extends SecurityController{
    private static Log LOG = LogFactory.getLog(RoleController.class);
    private static final long serialVersionUID = -4516357098346905567L;
    private Borderlayout roleLayout;
    private West roleWestLayout;
    private Center roleCenterLayout;
    private Panel treePanel;
    private Panel viewPanel;
    private Tree roleTree;
    private Treechildren functionChild;
    private Tree functionTree;
    private RoleTreeitem roleItem;
    private Button setRoleBtn;       
    
    
    
    private Grid roleGrid;    
    private Toolbarbutton addRoleBtn;
    private Button saveRoleBtn;
//    private Button delRoleBtn;
    private Button cancelRoleBtn;
    private Textbox roleIdTxt;
    private Textbox roleNameTxt;
    private Textbox roleDescTxt;

    private Vbox role0;
    private Vbox role1;
   
    private Object tmpData;

    private HashMap<String,FunctionTreeitem> permissionInTrees = new HashMap<String,FunctionTreeitem>();
    private HashMap<String,FunctionTreeitem> rootPermissionInTrees = new HashMap<String,FunctionTreeitem>();
    

    @Override
    public void doAfterCompose(Component component) throws Exception
    {        
        super.doAfterCompose(component);
        createComponent(component);                
        addEventListener();
        renderInitailTree();
        renderPermissions(FunctionCore.getAllFunction());
    }



   

    public void createComponent(Component component)
    {
   
        roleLayout = (Borderlayout)getComponent(component, "roleLayout", roleLayout);
        roleWestLayout = (West)getComponent(component, "roleWestLayout", roleWestLayout);
        roleCenterLayout = (Center)getComponent(component, "roleCenterLayout", roleCenterLayout);
        treePanel = (Panel)getComponent(roleWestLayout, "treePanel", treePanel);
        viewPanel = (Panel)getComponent(roleCenterLayout, "viewPanel", viewPanel);
        roleTree = (Tree)getComponent(treePanel, "roleTree", roleTree);
        setRoleBtn = (Button)getComponent(viewPanel, "setRoleBtn", setRoleBtn);
        functionChild = (Treechildren)getComponent(component,"roleChild", functionChild);
        functionTree = (Tree)getComponent( component , "functionTree", functionTree);
        
        
        roleGrid = (Grid)getComponent(viewPanel, "roleGrid", roleGrid);
        addRoleBtn = (Toolbarbutton)getComponent(treePanel, "addRoleBtn", addRoleBtn);
        saveRoleBtn = (Button)getComponent(viewPanel, "saveRoleBtn", saveRoleBtn);
//        delRoleBtn = (Button)getComponent(viewPanel, "delRoleBtn", delRoleBtn);
        cancelRoleBtn = (Button)getComponent(viewPanel, "cancelRoleBtn", cancelRoleBtn);
        
        roleIdTxt = (Textbox)getComponent(viewPanel, "roleIdTxt", roleIdTxt);
        roleNameTxt = (Textbox)getComponent(viewPanel, "roleNameTxt", roleNameTxt);
        roleDescTxt = (Textbox)getComponent(viewPanel, "roleDescTxt", roleDescTxt);
        role0 = (Vbox)getComponent(viewPanel, "role0", role0);
        role1 = (Vbox)getComponent(viewPanel, "role1", role1);
       
    }
     

    private void loadRole(Long roleId) throws Exception
    {        
        Role role = RoleCore.getRole(roleId);
        if(role != null)
        {
            tmpData = role;
            roleIdTxt.setValue(String.valueOf(role.getId()));
            roleIdTxt.setDisabled(true);
            roleNameTxt.setValue(role.getRoleName());
            roleDescTxt.setValue(role.getRoleDescription());
            setPermissions(role.getFunctions());
//            delRoleBtn.setVisible(true);
        }       
    }
        
   

    private void loadRole() throws Exception
    {
        Role role = (Role)tmpData;
        if(role != null)
        {            
            roleIdTxt.setValue(String.valueOf(role.getId()));
            roleIdTxt.setDisabled(true);
            roleNameTxt.setValue(role.getRoleName());
            roleDescTxt.setValue(role.getRoleDescription());
            setPermissions(role.getFunctions());
//            delRoleBtn.setVisible(true);
        }
    }


    private HashMap<String,Object> saveRole() throws Exception
    {
        Role role = new Role();
        HttpUserSession userSession = new HttpUserSession();
        Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
        if(roleIdTxt.getValue().trim().length()>0)
        {
            role.setId(Long.parseLong(roleIdTxt.getValue()));
        }
        role.setRoleName(roleNameTxt.getValue());
        role.setRoleDescription(roleDescTxt.getValue());
        Set<Function> functions = new HashSet<Function>();
        getPermissionsForSubmit(functionChild,functions );
        role.setFunctions(functions);
        return RoleCore.saveRole(role, userId);
//        return GroupCore.saveGroup(role,1L);
    }

    private HashMap<String,Object> deleteRole() throws Exception
    {
        Role role = new Role();
        if(roleIdTxt.getValue().trim().length()>0)
        {
            role.setId(Long.parseLong(roleIdTxt.getValue()));
        }        
        return RoleCore.deleteRole(role, 1L);
//        return GroupCore.saveGroup(role,1L);
    }

    private Boolean validateRoleData()
    {

        String roleName = roleNameTxt.getValue();
        if(roleName.trim().length() < 1 || roleName.trim().length() > 50)
        {
            return false;
        }
        String roleDesc = roleDescTxt.getValue();
        if(roleDesc.trim().length() < 1 || roleDesc.trim().length() > 50)
        {
            return false;
        }


        return true;
    }

    private void showRole1()
    {
        role1.setVisible(true);
        role1.setVflex("1");
        role1.setHflex("1");
        role0.setVisible(false);
        role0.setVflex(null);
        role0.setHflex(null);
    }

    private void showRole0()
    {
        role0.setVisible(true);
        role0.setVflex("1");
        role0.setHflex("1");
        role1.setVisible(false);
        role1.setVflex(null);
        role1.setHflex(null);
    }

    
    public void addEventListener()
    {
                    
        saveRoleBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                if(!validateRoleData())
                {
                    AlertMessages.alertMessage("Please input all required field");
                    return ;
                }


                int message = AlertMessages.confirmMessage("Do you want to confirm submit data?");
                if(message == AlertMessages.NO || message == 0)
                {
                    return;
                }


                try
                {
                    HashMap<String,Object> map = saveRole();
                    if((Boolean)map.get("results"))
                    {
                        AlertMessages.successMessage("Save Role is successful.");
                        HashMap<String,Object> treeMap = (HashMap<String,Object>)execution.getDesktop().getAttribute("roleTree");
                        RoleTreeitem item = (RoleTreeitem)treeMap.get(String.valueOf(map.get("roleId")));
                        if(item != null)
                        {
                            item.setLabel(roleNameTxt.getValue());
                            Events.postEvent(Events.ON_CLICK,item,null);
                        }
                        else
                        {
                            item = (RoleTreeitem)treeMap.get(String.valueOf("r-0"));
                            Events.postEvent(Events.ON_CLICK,item,String.valueOf(map.get("roleId")));
                        }
                    }
                    else
                    {
                        AlertMessages.failMessage("Save Role is failed.");
                    }
                }catch(Exception e)
                {                    
                    if(e.getMessage().toLowerCase().indexOf("could not execute jdbc batch update") > -1)
                    {
                        AlertMessages.failMessage("Role is duplicated,Could not be save.");
                    }
                    else
                    {
                        AlertMessages.failMessage("Unexpected Error");
                    }
                }
            }
        });

        

        addRoleBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                clearFunction();
                tmpData = new Role();
                roleIdTxt.setValue("");
                roleNameTxt.setValue("");
                roleDescTxt.setValue("");
//                delRoleBtn.setVisible(false);
                showRole1();
            }
        });


        cancelRoleBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                int message = AlertMessages.confirmMessage("Do you want to cancel change data?");
                if(message == AlertMessages.NO || message == 0)
                {
                    return;
                }
                loadRole();
            }
        });

//        delRoleBtn.addEventListener(Events.ON_CLICK, new EventListener() {
//
//            @Override
//            public void onEvent(Event event) throws Exception {
//                int message = AlertMessages.confirmMessage("Are you confirm to delete this information?");
//                if(message == AlertMessages.NO || message == 0)
//                {
//                    return;
//                }
//
//                if(!RoleCore.checkRoleAvailable(Long.valueOf(roleIdTxt.getValue())))
//                {
//                    AlertMessages.alertMessage("Role is not available,Could not be delete.");
//                    return;
//                }
//
//                HashMap<String,Object> map = deleteRole();
//                if((Boolean)map.get("results"))
//                {
//                    AlertMessages.alertMessage("Delete Role is successful.");
//                    HashMap<String,Object> treeMap = (HashMap<String,Object>)execution.getDesktop().getAttribute("roleTree");
//                    RoleTreeitem item = (RoleTreeitem)treeMap.get("0");
//                    Events.postEvent(Events.ON_CLICK,item,null);
//                }
//                else
//                {
//                    AlertMessages.alertMessage("Save Role is failed.");
//                }
//            }
//        });



        setRoleBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                Long roleId = (Long)event.getData();
                LOG.info("setRoleBtn >>>>>>>>>>>>>> roleId = "+roleId);
                if(roleId != null && !roleId.equals(0L))
                {                                       
                    loadRole(roleId);
                    showRole1();
                }
                else
                {
                    showRole0();
                }
            }
        });


    }

    public void renderInitailTree() throws Exception
    {
        if(roleTree != null)
        {
//            HttpUserSession userSession = new HttpUserSession();
            Group userGroup = (Group)HibernateUtil.currentSession().get(Group.class, new Long(1L));

            roleItem = new RoleTreeitem("Role Management",new Long(0L),"r-0",null,setRoleBtn);
//            ugItem.setImage(groupImage);
            Treechildren child = null;
            if(roleTree.getLastChild() instanceof Treechildren)
            {
                child = (Treechildren)roleTree.getLastChild();
                child.getChildren().clear();
            }
            else
            {
                if(userGroup != null)
                {
                    child = new Treechildren();
                    roleTree.appendChild(child);
                }
            }
            child.appendChild(roleItem);
            Events.postEvent(Events.ON_CLICK, roleItem, null);
        }
    }

    private void clearFunction()
    {
        for(Object permission : permissionInTrees.values())
        {
            ((FunctionTreeitem)permission).setSelected(false);
        }
    }

    private void setPermissions(Set<Function> roleFunctions)
    {
        clearFunction();
        if(roleFunctions != null )
        {
            for(Function function : roleFunctions)
            {
                FunctionTreeitem userItem = (FunctionTreeitem)permissionInTrees.get(""+function.getId());
                if(userItem != null)
                {
                    userItem.setSelected(Boolean.TRUE);
                }
            }

            if(rootPermissionInTrees != null && rootPermissionInTrees.size() > 0)
            {
                for(FunctionTreeitem item : rootPermissionInTrees.values())
                {
                    item.checkMarkSubTree();
                }
            }
        }
    }

    private void renderPermissions(List<Function> functions)
    {
        functionChild.getChildren().clear();
        Treeitem noitem = new Treeitem();
        noitem.setSelected(Boolean.TRUE);
        functionChild.getChildren().add(noitem);
        permissionInTrees = new HashMap<String,FunctionTreeitem>();
        rootPermissionInTrees = new HashMap<String,FunctionTreeitem>();
        if(functions != null && functions.size() > 0)
        {
            for(Function function : functions)
            {
                showGroupPermissions(permissionInTrees,function,rootPermissionInTrees);
            }                       
        }
    }

    private FunctionTreeitem showGroupPermissions(HashMap<String,FunctionTreeitem> groupPermissionInTrees,Function function,HashMap<String,FunctionTreeitem> rootPermissionInTrees)
    {
        FunctionTreeitem item = null;
        if(groupPermissionInTrees.containsKey(""+function.getId()))
        {
            item =  groupPermissionInTrees.get(""+function.getId());
        } else if(function.getParent() != null && groupPermissionInTrees.containsKey(""+function.getParent().getId()))
        {
            FunctionTreeitem parentItem = groupPermissionInTrees.get(""+function.getParent().getId());            
            item = new FunctionTreeitem(function.getFunctionName(),""+function.getId(),parentItem);
            item.setOpen(true);
            groupPermissionInTrees.put(""+function.getId(), item);
        }
        else if(function.getParent() != null)
        {
            FunctionTreeitem parentItem = showGroupPermissions(groupPermissionInTrees,function.getParent(),rootPermissionInTrees);
            item = new FunctionTreeitem(function.getFunctionName(),""+function.getId(),parentItem);
            item.setOpen(true);
            groupPermissionInTrees.put(""+function.getId(), item);
        }
        else
        {
            item = new FunctionTreeitem(function.getFunctionName(),""+function.getId(),null);
            item.setOpen(true);
            groupPermissionInTrees.put(""+function.getId(), item);
            rootPermissionInTrees.put(""+function.getId(), item);
            functionChild.appendChild(item);
        }

        return item;
    }


    private void getPermissionsForSubmit(Treechildren treechildren,Set<Function> permissions) throws Exception
    {
        if(treechildren != null && treechildren.getChildren() != null && treechildren.getChildren().size() > 0)
        {
            List<Treeitem> groupFunctions  = (List<Treeitem>)treechildren.getChildren();
            if(groupFunctions != null && groupFunctions.size() > 0)
            {
                for(Treeitem function : groupFunctions)
                {
                    getPermissions(function,permissions);
                }
            }
        }
    }

    private void getPermissions(Treeitem item,Set<Function> permissions) throws Exception
    {
        if(item instanceof FunctionTreeitem)
        {
            FunctionTreeitem functionItem = (FunctionTreeitem)item;
            if(functionItem.getTreechildren() != null
                    && functionItem.getTreechildren().getChildren() != null
                    && functionItem.getTreechildren().getChildren().size() > 0)
            {
                getPermissionsForSubmit(functionItem.getTreechildren(),permissions);
            }
            else
            {
                if(functionItem.getTreechildren() == null
                        || functionItem.getTreechildren().getChildren() == null
                        || functionItem.getTreechildren().getChildren().size() == 0)
                {

                    if(functionItem.getValue() != null && functionItem.isSelected())
                    {
                        try
                        {
                            Function function = (Function)HibernateUtil.currentSession()
                                    .get(Function.class, Long.parseLong((String)functionItem.getValue()));
                            function.toString();
                            HibernateUtil.currentSession().evict(function);
                            permissions.add(function);
                            
                        }
                        catch(Exception e)
                        {
                            throw e;
                        }
                    }
                }
            }
        }

    }  
}
