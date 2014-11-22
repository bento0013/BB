/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.security;

import com.bsd.cse.app.security.GroupCore;
import com.bsd.cse.app.security.TeamCore;
import com.bsd.cse.app.security.UserCore;
import com.bsd.cse.common.Configuration;
import com.bsd.cse.common.HibernateUtil;
import com.bsd.cse.model.security.Group;
import com.bsd.cse.model.security.Role;
import com.bsd.cse.model.security.Team;
import com.bsd.cse.model.security.User;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.alertbox.CsMessagebox;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.listbox.ZKListboxs;
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
import org.zkoss.util.ResourceUtil;
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
import org.zkoss.zul.West;

/**
 *
 * @author bento
 */
public class UserGroupController extends SecurityController{
    private static Log LOG = LogFactory.getLog(UserGroupController.class);
    private static final long serialVersionUID = -4516357098346905567L;
    private Borderlayout ugLayout;
    private West ugWestLayout;
    private Center ugCenterLayout;
    private Panel treePanel;
    private Panel viewPanel;
    private Tree ugTree;
    private UserGroupTreeitem ugItem;
    private Button setGroupBtn;
    private Button setUserBtn;
    private Panel headerPanel;
    private Combobox filterActiveCbox;
    
    
    
    private Grid userGrid;
    private Grid groupGrid;
    private Toolbarbutton addGroupBtn;
    private Toolbarbutton addUserBtn;
    private Button saveGroupBtn;
    private Button saveUserBtn;
    private Button cancelGroupBtn;
    private Button cancelUserBtn;

    private Textbox employeeIdTxt;
    private Textbox empFirstNameTxt;
    private Textbox empLastNameTxt;
    private Combobox teamCbox;
    private Textbox passwordTxt;
    private Textbox confPasswordTxt;
    private Combobox userGroupCbox;
    private Listbox userAvailableRoleListbox;
    private Listbox userSelectedRoleListbox;
    private Checkbox userDisabledChk;
    private Button userSelBtn;
    private Button userAvailBtn;
    private Button uploadImageBtn;
    private Button cancelImageBtn;
    private Image picImage;
    private Checkbox resetPasswdChk;
    private Textbox usernameTxt;
    

    private Textbox groupIdTxt;
    private Textbox groupNameTxt;
    private Textbox groupDescTxt;
    private Combobox parentGroupCbox;
    private Listbox groupAvailableRoleListbox;
    private Listbox groupSelectedRoleListbox;
    private Checkbox groupDisabledChk;
    private Button groupSelBtn;
    private Button groupAvailBtn;
    private Row parentGroupRow;
    private static List<Team> TEAMS;
    private static String IMAGE_PATH;
    private static String PASSWORD_PATTERN;
    private static String USERNAME_PATTERN;
    private static org.zkoss.image.Image DEFAULT_IMAGE;
    private Object tmpData;

    static
    {
        try {
            PASSWORD_PATTERN = "[0-9]{8,}";
            USERNAME_PATTERN = "[A-Z0-9]{3,25}";
            IMAGE_PATH = Configuration.getString("cs.image.folder")+"\\image\\";
            DEFAULT_IMAGE = new AImage(IMAGE_PATH+"no-image.png");
            TEAMS = TeamCore.getAll();
        } catch (IOException ex) {
            LOG.error(ex.getMessage(),ex);
        }
        catch (Exception ex) {
            LOG.error(ex.getMessage(),ex);
        }
    }


    

    @Override
    public void doAfterCompose(Component component) throws Exception
    {

        
        super.doAfterCompose(component);
        createComponent(component);        
        loadCatalogs();
        addEventListener();
        renderInitailTree();                
    }



   

    public void createComponent(Component component)
    {
        ugLayout = (Borderlayout)getComponent(component, "ugLayout", ugLayout);
        ugWestLayout = (West)getComponent(component, "ugWestLayout", ugWestLayout);
        ugCenterLayout = (Center)getComponent(component, "ugCenterLayout", ugCenterLayout);
        treePanel = (Panel)getComponent(ugWestLayout, "treePanel", treePanel);
        viewPanel = (Panel)getComponent(ugCenterLayout, "viewPanel", viewPanel);
        ugTree = (Tree)getComponent(treePanel, "ugTree", ugTree);
        filterActiveCbox = (Combobox)getComponent(treePanel, "filterActiveCbox", filterActiveCbox);
        setGroupBtn = (Button)getComponent(viewPanel, "setGroupBtn", setGroupBtn);
        setUserBtn = (Button)getComponent(viewPanel, "setUserBtn", setUserBtn);
        headerPanel = (Panel)getComponent(ugCenterLayout, "headerPanel", headerPanel);
        
        
        groupGrid = (Grid)getComponent(viewPanel, "groupGrid", groupGrid);
        userGrid = (Grid)getComponent(viewPanel, "userGrid", userGrid);
        addGroupBtn = (Toolbarbutton)getComponent(viewPanel, "addGroupBtn", addGroupBtn);
        addUserBtn = (Toolbarbutton)getComponent(viewPanel, "addUserBtn", addUserBtn);
        saveGroupBtn = (Button)getComponent(viewPanel, "saveGroupBtn", saveGroupBtn);
        saveUserBtn = (Button)getComponent(viewPanel, "saveUserBtn", saveUserBtn);
        cancelGroupBtn = (Button)getComponent(viewPanel, "cancelGroupBtn", cancelGroupBtn);
        cancelUserBtn = (Button)getComponent(viewPanel, "cancelUserBtn", cancelUserBtn);

        usernameTxt = (Textbox)getComponent(viewPanel, "usernameTxt", usernameTxt);
        employeeIdTxt = (Textbox)getComponent(viewPanel, "employeeIdTxt", employeeIdTxt);
        empFirstNameTxt = (Textbox)getComponent(viewPanel, "empFirstNameTxt", empFirstNameTxt);
        empLastNameTxt = (Textbox)getComponent(viewPanel, "empLastNameTxt", empLastNameTxt);;
        teamCbox = (Combobox)getComponent(viewPanel, "teamCbox", teamCbox);
        passwordTxt = (Textbox)getComponent(viewPanel, "passwordTxt", passwordTxt);
        confPasswordTxt = (Textbox)getComponent(viewPanel, "confPasswordTxt", confPasswordTxt);
        userGroupCbox = (Combobox)getComponent(viewPanel, "userGroupCbox", userGroupCbox);
        userAvailableRoleListbox = (Listbox)getComponent(viewPanel, "userAvailableRoleListbox", userAvailableRoleListbox);
        userSelectedRoleListbox = (Listbox)getComponent(viewPanel, "userSelectedRoleListbox", userSelectedRoleListbox);
        userDisabledChk = (Checkbox)getComponent(viewPanel, "userDisabledChk", userDisabledChk);
        userSelBtn = (Button)getComponent(viewPanel, "userSelBtn", userSelBtn);
        userAvailBtn = (Button)getComponent(viewPanel, "userAvailBtn", userAvailBtn);
        picImage = (Image)getComponent(viewPanel, "picImage", picImage);
        uploadImageBtn = (Button)getComponent(viewPanel, "uploadImageBtn", uploadImageBtn);
        cancelImageBtn = (Button)getComponent(viewPanel, "cancelImageBtn", cancelImageBtn);


        groupIdTxt = (Textbox)getComponent(viewPanel, "groupIdTxt", groupIdTxt);
        groupNameTxt = (Textbox)getComponent(viewPanel, "groupNameTxt", groupNameTxt);
        groupDescTxt = (Textbox)getComponent(viewPanel, "groupDescTxt", groupDescTxt);
        parentGroupCbox = (Combobox)getComponent(viewPanel, "parentGroupCbox", parentGroupCbox);
        groupAvailableRoleListbox = (Listbox)getComponent(viewPanel, "groupAvailableRoleListbox", groupAvailableRoleListbox);
        groupSelectedRoleListbox = (Listbox)getComponent(viewPanel, "groupSelectedRoleListbox", groupSelectedRoleListbox);
        groupSelBtn = (Button)getComponent(viewPanel, "groupSelBtn", groupSelBtn);
        groupAvailBtn = (Button)getComponent(viewPanel, "groupAvailBtn", groupAvailBtn);
        groupDisabledChk = (Checkbox)getComponent(viewPanel, "groupDisabledChk", groupDisabledChk);
        parentGroupRow = (Row)getComponent(viewPanel, "parentGroupRow", parentGroupRow);
    }

    private void loadCatalogs() throws Exception
    {
        
    }

    private void setHeaderGroupPanel()
    {
        headerPanel.setTitle(ResourceUtil.getLabel("security.usergroup.page.label.groupdetail"));
    }

    private void setHeaderUserPanel()
    {
        headerPanel.setTitle(ResourceUtil.getLabel("security.usergroup.page.label.userdetail"));
    }

    private void loadGroup(Long groupId) throws Exception
    {
        HttpUserSession userSession = new HttpUserSession();
        Group userGroup = (Group)userSession.getUserInformation().get(Users.GROUP);
        setHeaderGroupPanel();
        Group group = GroupCore.getGroup(groupId);
        if(group != null)
        {
            tmpData = group;
            changeGroupView();
            groupIdTxt.setValue(String.valueOf(group.getId()));
            groupIdTxt.setDisabled(true);
            groupNameTxt.setValue(group.getGroupName());
            groupDescTxt.setValue(group.getGroupDescription());
            groupSelectedRoleListbox.getChildren().clear();
            groupAvailableRoleListbox.getChildren().clear();
            groupDisabledChk.setChecked(group.getDisabled() != null ?group.getDisabled():Boolean.FALSE);
            if(group.getId() == null || !group.getId().equals(1L))
            {
                if(group.getParent() != null && group.getParent().getDisabled() != null && group.getParent().getDisabled())
                {
                    groupDisabledChk.setDisabled(true);
                    parentGroupCbox.setDisabled(true);
                    ZKCatalogs.setSecurityGroups(parentGroupCbox, 1L,group.getId(),Boolean.TRUE);
                }
                else
                {
                    groupDisabledChk.setDisabled(false);
                    parentGroupCbox.setDisabled(false);
                    ZKCatalogs.setSecurityGroups(parentGroupCbox, 1L,group.getId(),Boolean.FALSE);
                }

               
                
//                ZKCatalogs.setSecurityGroups(parentGroupCbox, 1L,group.getId());
                parentGroupRow.setVisible(true);
                if(group.getParent() != null)
                {
                    ZKCatalogs.setSelectedItemGroup(parentGroupCbox, group.getParent());
                }
                else
                {
                    parentGroupCbox.setSelectedIndex(0);
                }

                Group parent = (Group)parentGroupCbox.getSelectedItem().getValue();                
                ZKListboxs.addRoleToListbox(parent.getRoles(), groupAvailableRoleListbox);
                if(group.getRoles() != null)
                {
                    ZKListboxs.removeRoleFromListbox(group.getRoles(), groupAvailableRoleListbox);
                    ZKListboxs.addRoleToListbox(group.getRoles(), groupSelectedRoleListbox);
                }                
                groupAvailBtn.setDisabled(false);
                groupSelBtn.setDisabled(false);

                if(group.getId() != null && group.getId().equals(userGroup.getId()))
                {
                    parentGroupCbox.setDisabled(true);
                    groupAvailBtn.setDisabled(true);
                    groupSelBtn.setDisabled(true);
                    groupDisabledChk.setDisabled(true);
                }
            }
            else
            {
                groupDisabledChk.setDisabled(true);
                parentGroupRow.setVisible(false);
                ZKListboxs.addRoleToListbox(group.getRoles(), groupSelectedRoleListbox);
                groupAvailBtn.setDisabled(true);
                groupSelBtn.setDisabled(true);
            }
            
            
                     
        }

        setShowGroupBtn(true);
        
    }

    private void loadUser(Long userId) throws Exception
    {
        HttpUserSession userSession = new HttpUserSession();
        Group userGroup = (Group)userSession.getUserInformation().get(Users.GROUP);
        setHeaderUserPanel();
        User user = UserCore
                .getUser(userId);
        userGroupCbox.removeAttribute("newUser");
        if(user != null)
        {
            tmpData = user;
            changeUserView();
            usernameTxt.setDisabled(true);
            usernameTxt.setText(user.getUsername());
            employeeIdTxt.setText(user.getId() != null?String.valueOf(user.getId()):"");
            empFirstNameTxt.setText(user.getFirstname());
            empLastNameTxt.setText(user.getLastname());
            resetPasswdChk.setChecked(false);
            resetPasswdChk.setDisabled(false);
            resetPassword(resetPasswdChk.isChecked());
            userSelectedRoleListbox.getChildren().clear();
            userAvailableRoleListbox.getChildren().clear();
            userDisabledChk.setChecked(user.getDisabled() != null ?user.getDisabled():Boolean.FALSE);
            
            if(user.getImage() != null)
            {
                org.zkoss.image.Image userImage= new AImage(IMAGE_PATH+user.getImage());
                picImage.setContent(userImage);
            }
            else
            {
                picImage.setContent(DEFAULT_IMAGE);
            }

            if(user.getTeam() != null)
            {
                ZKCatalogs.setTeams(teamCbox, TEAMS,user.getTeam().getId());
            }
            else
            {
                
                ZKCatalogs.setTeams(teamCbox, TEAMS,0);
            }
            
            
            Group group = user.getGroup();
            if(user.getId() == null || !user.getId().equals(1L))
            {
                Long userLoginId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
                if(group != null && group.getDisabled() != null && group.getDisabled())
                {
                    userDisabledChk.setDisabled(true);
                    userGroupCbox.setDisabled(true);
                    ZKCatalogs.setSecurityGroups(userGroupCbox, userGroup.getId(),0L,Boolean.TRUE);
                }
                else
                {
                    userDisabledChk.setDisabled(false);
                    userGroupCbox.setDisabled(false);
                    ZKCatalogs.setSecurityGroups(userGroupCbox, userGroup.getId(),0L,Boolean.FALSE);
                }                
                if(user.getGroup() != null)
                {
                    ZKCatalogs.setSelectedItemGroup(userGroupCbox, group);
                }
                else
                {
                    userGroupCbox.setSelectedIndex(0);
                }

                Group roleGroup = (Group)userGroupCbox.getSelectedItem().getValue();
                ZKListboxs.addRoleToListbox(roleGroup.getRoles(), userAvailableRoleListbox);
                if(user.getRoles() != null)
                {
                    ZKListboxs.removeRoleFromListbox(user.getRoles(), userAvailableRoleListbox);
                    ZKListboxs.addRoleToListbox(user.getRoles(), userSelectedRoleListbox);
                }
                userAvailBtn.setDisabled(false);
                userSelBtn.setDisabled(false);
                if(user.getId() != null && userLoginId.equals(user.getId()))
                {
                    userGroupCbox.setDisabled(true);
                    userDisabledChk.setDisabled(true);
                    userAvailBtn.setDisabled(true);
                    userSelBtn.setDisabled(true);
                }
            }
            else
            {
                userDisabledChk.setDisabled(true);                
                ZKListboxs.addRoleToListbox(user.getRoles(), userSelectedRoleListbox);
                userAvailBtn.setDisabled(true);
                userSelBtn.setDisabled(true);
                userGroupCbox.setDisabled(true);
                ZKCatalogs.setSecurityGroups(userGroupCbox, userGroup.getId(),0L,Boolean.FALSE);
                if(group != null)
                {
                    ZKCatalogs.setSelectedItemGroup(userGroupCbox, group);
                }
                else
                {
                    userGroupCbox.setSelectedIndex(0);
                }
            }
            
        }
        
        setShowGroupBtn(false);
    }

    private void loadUser() throws Exception
    {
        HttpUserSession userSession = new HttpUserSession();
        Group userGroup = (Group)userSession.getUserInformation().get(Users.GROUP);
        setHeaderUserPanel();
        User user = (User)tmpData;
        if(user != null)
        {
            changeUserView();
            usernameTxt.setText(user.getUsername());
            usernameTxt.setDisabled(true);
            employeeIdTxt.setText(user.getId() != null?String.valueOf(user.getId()):"");
            empFirstNameTxt.setText(user.getFirstname());
            empLastNameTxt.setText(user.getLastname());
            userSelectedRoleListbox.getChildren().clear();
            userAvailableRoleListbox.getChildren().clear();
            resetPasswdChk.setChecked(false);
            resetPasswdChk.setDisabled(false);
            resetPassword(resetPasswdChk.isChecked());
            userDisabledChk.setChecked(user.getDisabled() != null ?user.getDisabled():Boolean.FALSE);
            if(user.getImage() != null)
            {
                org.zkoss.image.Image userImage= new AImage(IMAGE_PATH+user.getImage());
                picImage.setContent(userImage);
            }
            else
            {
                picImage.setContent(DEFAULT_IMAGE);
            }

            if(user.getTeam() != null)
            {
                ZKCatalogs.setTeams(teamCbox, TEAMS,user.getTeam().getId());
            }
            else
            {
                ZKCatalogs.setTeams(teamCbox, TEAMS,0);
            }

            Group group = user.getGroup();
            if(user.getId() == null || !user.getId().equals(1L))
            {                
                Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
                if(user.getId() != null)
                {
                    employeeIdTxt.setDisabled(true);
                }
                
                if(group != null && group.getDisabled() != null && group.getDisabled())
                {
                    userDisabledChk.setDisabled(true);
                    userGroupCbox.setDisabled(true);
                    ZKCatalogs.setSecurityGroups(userGroupCbox, userGroup.getId(),group.getId(),Boolean.TRUE);
                }
                else
                {
                    groupDisabledChk.setDisabled(false);
                    parentGroupCbox.setDisabled(false);
                    LOG.info(userGroupCbox);
                    LOG.info(userGroup);
                    LOG.info(group);
                    ZKCatalogs.setSecurityGroups(userGroupCbox, userGroup.getId(),0L,Boolean.FALSE);
                }
                if(user.getGroup() != null)
                {
                    ZKCatalogs.setSelectedItemGroup(userGroupCbox, group);
                }
                else
                {
                    userGroupCbox.setSelectedIndex(0);
                }

                Group roleGroup = (Group)userGroupCbox.getSelectedItem().getValue();
                ZKListboxs.addRoleToListbox(roleGroup.getRoles(), userAvailableRoleListbox);
                if(user.getRoles() != null)
                {
                    ZKListboxs.removeRoleFromListbox(user.getRoles(), userAvailableRoleListbox);
                    ZKListboxs.addRoleToListbox(user.getRoles(), userSelectedRoleListbox);
                }
                userAvailBtn.setDisabled(false);
                userSelBtn.setDisabled(false);

                if(user.getId() != null && user.getId().equals(userId))
                {
                    userGroupCbox.setDisabled(true);
                    userDisabledChk.setDisabled(true);
                    userAvailBtn.setDisabled(true);
                    userSelBtn.setDisabled(true);
                }
            }
            else
            {
                userDisabledChk.setDisabled(true);
                ZKListboxs.addRoleToListbox(user.getRoles(), userSelectedRoleListbox);
                userAvailBtn.setDisabled(true);
                userSelBtn.setDisabled(true);
            }

        }

       setShowGroupBtn(false);
    }

    private void setShowGroupBtn(Boolean groupBtnFlag)
    {
        saveGroupBtn.setVisible(groupBtnFlag);
        cancelGroupBtn.setVisible(groupBtnFlag);
        saveUserBtn.setVisible(!groupBtnFlag);
        cancelUserBtn.setVisible(!groupBtnFlag);
    }

    private void changeGroupView()
    {
        
            groupGrid.setVisible(true);
            groupGrid.setVflex("1");
            userGrid.setVisible(false);
            userGrid.setVflex(false);
            
    }
    private void changeUserView()
    {
            groupGrid.setVisible(false);
            groupGrid.setVflex(false);
            userGrid.setVisible(true);
            userGrid.setVflex("1");
       
    }

    private void loadGroup() throws Exception
    {
        HttpUserSession userSession = new HttpUserSession();
        Group userGroup = (Group)userSession.getUserInformation().get(Users.GROUP);
        setHeaderGroupPanel();
        Group group = (Group)tmpData;
        parentGroupCbox.removeAttribute("newGroup");
        if(group != null)
        {
            tmpData = group;
            changeGroupView();
            if(group.getId() != null)
            {
                groupIdTxt.setValue(String.valueOf(group.getId()));                
            }
            groupIdTxt.setDisabled(true);
            if(group.getGroupName() != null)
            {
                groupNameTxt.setValue(group.getGroupName());
            }
            else
            {
                groupNameTxt.setValue("");
            }
            if(group.getGroupDescription() != null)
            {
                groupDescTxt.setValue(group.getGroupDescription());
            }
            else
            {
                groupDescTxt.setValue("");
            }
            groupSelectedRoleListbox.getChildren().clear();
            groupAvailableRoleListbox.getChildren().clear();
            groupDisabledChk.setChecked(group.getDisabled() != null ?group.getDisabled():Boolean.FALSE);
            if(group.getId() == null || !group.getId().equals(1L))
            {
                if(group.getParent() != null && group.getParent().getDisabled() != null && group.getParent().getDisabled())
                {
                    groupDisabledChk.setDisabled(true);
                    parentGroupCbox.setDisabled(true);
                    ZKCatalogs.setSecurityGroups(parentGroupCbox, userGroup.getId(),group.getId(),Boolean.TRUE);
                }
                else
                {
                    groupDisabledChk.setDisabled(false);
                    parentGroupCbox.setDisabled(false);
                    ZKCatalogs.setSecurityGroups(parentGroupCbox, userGroup.getId(),group.getId(),Boolean.FALSE);
                }

//                ZKCatalogs.setSecurityGroups(parentGroupCbox, 1L,group.getId());
//                parentGroupRow.setVisible(true);
                parentGroupRow.setVisible(true);
                if(group.getParent() != null)
                {
                    ZKCatalogs.setSelectedItemGroup(parentGroupCbox, group.getParent());
                }
                else
                {
                    parentGroupCbox.setSelectedIndex(0);
                }

                Group parent = (Group)parentGroupCbox.getSelectedItem().getValue();
                ZKListboxs.addRoleToListbox(parent.getRoles(), groupAvailableRoleListbox);
                if(group.getRoles() != null)
                {
                    ZKListboxs.removeRoleFromListbox(group.getRoles(), groupAvailableRoleListbox);
                    ZKListboxs.addRoleToListbox(group.getRoles(), groupSelectedRoleListbox);
                }
                groupAvailBtn.setDisabled(false);
                groupSelBtn.setDisabled(false);

                if(group.getId() != null && group.getId().equals(userGroup.getId()))
                {
                    parentGroupCbox.setDisabled(true);
                    groupAvailBtn.setDisabled(true);
                    groupSelBtn.setDisabled(true);
                    groupDisabledChk.setDisabled(true);
                }
            }
            else
            {
                groupDisabledChk.setDisabled(true);
                parentGroupRow.setVisible(false);
                ZKListboxs.addRoleToListbox(group.getRoles(), groupSelectedRoleListbox);
                groupAvailBtn.setDisabled(true);
                groupSelBtn.setDisabled(true);
            }
        }

        setShowGroupBtn(true);
    }


    private HashMap<String,Object> saveGroup() throws Exception
    {
        HttpUserSession userSession = new HttpUserSession();
        Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
        Group group = new Group();
        if(groupIdTxt.getValue().trim().length()>0)
        {
            group.setId(Long.parseLong(groupIdTxt.getValue()));
        }
        group.setGroupName(groupNameTxt.getValue());
        group.setGroupDescription(groupDescTxt.getValue());
        group.setDisabled(groupDisabledChk.isChecked());
        if(parentGroupCbox.getSelectedItem() != null)
        {
            group.setParent((Group)parentGroupCbox.getSelectedItem().getValue());
        }
        Set<Role> roles =new HashSet<Role>();
        for(Object obj : groupSelectedRoleListbox.getChildren())
        {
            Listitem item = (Listitem)obj;
            roles.add((Role)item.getValue());
        }
        if(roles != null && roles.size() > 0)
        {
            group.setRoles(roles);
        }
        return GroupCore.saveGroup(group,userId);
    }

    private Boolean validateGroupData()
    {

        String groupName = groupNameTxt.getValue();
        if(groupName.trim().length() < 1 || groupName.trim().length() > 50)
        {
            return false;
        }
        String groupDesc = groupDescTxt.getValue();
        if(groupDesc.trim().length() < 1 || groupDesc.trim().length() > 50)
        {
            return false;
        }       


        return true;
    }

    private Boolean validateUserData(List<String> alertMessages)
    {

        String username = usernameTxt.getValue();        

        if(!Pattern.matches(USERNAME_PATTERN, username))
        {
            alertMessages.add("Employee ID support only 0-9 and A-Z ,Range length is 3-25 digits");
        }
        
        String empFirstName = empFirstNameTxt.getValue();
        if(empFirstName.trim().length() < 1 || empFirstName.trim().length() > 50)
        {
            alertMessages.add("First Name is required");            
        }

        String empLastName = empLastNameTxt.getValue();
        if(empLastName.trim().length() < 1 || empLastName.trim().length() > 50)
        {
            alertMessages.add("Last Name is required");            
        }

        if(resetPasswdChk.isChecked())
        {
            String password = passwordTxt.getValue();
            String confPassword = confPasswordTxt.getValue();
//            if(password.trim().length() < 1 || password.trim().length() > 50)
//            {
//                alertMessages.add("Password is required");
//                return false;
//            }
//            if(confPassword.trim().length() < 1 || confPassword.trim().length() > 50)
//            {
//                alertMessages.add("Confirm Password is required");
//                return false;
//            }

            if(!password.equals(confPassword))
            {
                alertMessages.add("Password and Confirm Password must be equal.");
                return false;
            }

            if(!password.isEmpty() && !Pattern.matches(PASSWORD_PATTERN, password))
            {
                alertMessages.add("Password support only 0-9 and more than 7 digits");
                return false;
            }
        }

        if(alertMessages != null && !alertMessages.isEmpty())
        {
            return false;
        }

        return true;
    }

    private HashMap<String,Object> saveUser() throws Exception
    {
        HttpUserSession userSession = new HttpUserSession();
        Long loginId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
        User user = new User();
        if(employeeIdTxt.getValue().trim().length()>0)
        {
            String userId = employeeIdTxt.getValue().trim();
            if(userId.length() > 0)
            {
                user.setId(Long.valueOf(userId));
            }
        }
        user.setUsername(usernameTxt.getValue().trim());
        user.setFirstname(empFirstNameTxt.getValue().trim());
        user.setLastname(empLastNameTxt.getValue().trim());
        user.setDisabled(userDisabledChk.isChecked());
        user.setImage(user.getUsername()+".png");
        if(passwordTxt.getValue().length() > 0)
        {
            user.setPassword(passwordTxt.getValue());
        }
        if(userGroupCbox.getSelectedItem() != null)
        {
            user.setGroup((Group)userGroupCbox.getSelectedItem().getValue());
        }
        if(teamCbox.getSelectedIndex() > 0)
        {
            user.setTeam((Team)teamCbox.getSelectedItem().getValue());
        }
        Set<Role> roles =new HashSet<Role>();
        for(Object obj : userSelectedRoleListbox.getChildren())
        {
            Listitem item = (Listitem)obj;
            roles.add((Role)item.getValue());
        }
        if(roles != null && roles.size() > 0)
        {
            user.setRoles(roles);
        }
        return UserCore.saveUser(user,loginId);
    }

    private void resetPassword(Boolean resetFlag)
    {
        resetFlag = resetFlag!=null?resetFlag:false;
        passwordTxt.setDisabled(!resetFlag);
        passwordTxt.setValue("");
        confPasswordTxt.setDisabled(!resetFlag);
        confPasswordTxt.setValue("");
    }

    private void saveImage() throws Exception
    {
        InputStream picStream = null;
        try
        {
            File file = new File(IMAGE_PATH+usernameTxt.getValue().trim()+".png");
            if(file.exists())
            {
                file.mkdirs();
            }
            FileOutputStream fileOutput = new FileOutputStream(file);
            picStream = picImage.getContent().getStreamData();
            int read = 0;
            byte b[] = new byte[256];
            while((read = picStream.read(b)) > 0)
            {
                fileOutput.write(b,0,read);
            }
            fileOutput.flush();
            fileOutput.close();            
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            if(picStream != null)
            {
                picStream.close();
            }
        }
    }
   


    public void addEventListener()
    {

        resetPasswdChk.addEventListener(Events.ON_CHECK, new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                resetPassword(resetPasswdChk.isChecked());                                
            }
        });
        
        uploadImageBtn.addEventListener(Events.ON_UPLOAD, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                UploadEvent uEvent = (UploadEvent)event;
                Media media = uEvent.getMedia();
                if(media instanceof org.zkoss.image.Image)
                {
                    picImage.setContent((org.zkoss.image.Image)media);
                }
                else
                {
                    alert("No Image");
                }
                
            }
        });

        cancelImageBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                User user = (User)tmpData;
                if(user.getImage() != null)
                {
                    org.zkoss.image.Image userImage= new AImage(IMAGE_PATH+user.getImage());
                    picImage.setContent(userImage);
                }
                else
                {
                    picImage.setContent(DEFAULT_IMAGE);
                }
            }
        });
        saveGroupBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                HttpUserSession userSession = new HttpUserSession();
                Group userGroup = (Group)userSession.getUserInformation().get(Users.GROUP);
                if(!validateGroupData())
                {
                    AlertMessages.alertMessage("Please input all required field");
                    return ;
                }


                int message = AlertMessages.confirmMessage("Do you want to confirm submit data?");
                if(message == AlertMessages.NO || message == 0)
                {
                    return;
                }
                HashMap<String,Object> map = saveGroup();
                if((Boolean)map.get("results"))
                {
                    AlertMessages.successMessage("Save Group is successful.");
                    String treeLabels = GroupCore.getTreeLabel((Long)map.get("groupId"),userGroup.getId());
                    String ugTree[] = treeLabels.split(",",2);
                    HashMap<String,Object> treeMap = (HashMap<String,Object>)execution.getDesktop().getAttribute("tree");
                    UserGroupTreeitem item = (UserGroupTreeitem)treeMap.get(ugTree[0]);
                    if(((String)item.getValue()).equals("G-"+groupIdTxt.getValue()))
                    {
                        item.setLabel(groupNameTxt.getValue());
                    }
                    Events.postEvent(Events.ON_CLICK,item,ugTree.length > 1?ugTree[1]:null);
                }
                else
                {
                    AlertMessages.failMessage("Save Group is failed.");
                }
            }
        });

         addUserBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {

                HttpUserSession userSession = new HttpUserSession();
                Group userGroup = (Group)userSession.getUserInformation().get(Users.GROUP);
                setHeaderUserPanel();
                
                tmpData = new User();
                changeUserView();
                employeeIdTxt.setValue("");
                usernameTxt.setValue("");
                usernameTxt.setDisabled(false);
                empFirstNameTxt.setValue("");
                empLastNameTxt.setValue("");
                userDisabledChk.setChecked(false);
                userDisabledChk.setDisabled(false);
                resetPasswdChk.setChecked(true);
                resetPasswdChk.setDisabled(true);
                picImage.setContent(DEFAULT_IMAGE);
                resetPassword(resetPasswdChk.isChecked());
                ZKCatalogs.setTeams(teamCbox, TEAMS,0);
                ZKCatalogs.setSecurityGroups(userGroupCbox, userGroup.getId(),0L,false);
                userGroupCbox.setSelectedIndex(0);
                userGroupCbox.setDisabled(false);
                Group group = (Group)userGroupCbox.getSelectedItem().getValue();
                userGroupCbox.setAttribute("newUser",Boolean.TRUE );
                userAvailableRoleListbox.getChildren().clear();
                userSelectedRoleListbox.getChildren().clear();
                ZKListboxs.addRoleToListbox(group.getRoles(), userSelectedRoleListbox);
                userAvailBtn.setDisabled(false);
                userSelBtn.setDisabled(false);
                saveGroupBtn.setVisible(false);
                cancelGroupBtn.setVisible(false);
                saveUserBtn.setVisible(true);
                cancelUserBtn.setVisible(true);

            }
         });

        addGroupBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                HttpUserSession userSession = new HttpUserSession();
                Group userGroup = (Group)userSession.getUserInformation().get(Users.GROUP);
                setHeaderGroupPanel();
                tmpData = new Group();
                changeGroupView();
                groupIdTxt.setValue("");
                groupNameTxt.setValue("");
                groupDescTxt.setValue("");
                groupDisabledChk.setChecked(false);
                groupDisabledChk.setDisabled(false);
//                parentGroupRow.setVisible(true);
                parentGroupRow.setVisible(true);
                ZKCatalogs.setSecurityGroups(parentGroupCbox, userGroup.getId(),0L,false);
                parentGroupCbox.setSelectedIndex(0);
                Group group = (Group)parentGroupCbox.getSelectedItem().getValue();
                parentGroupCbox.setAttribute("newGroup",Boolean.TRUE );
                groupAvailableRoleListbox.getChildren().clear();
                groupSelectedRoleListbox.getChildren().clear();                
                ZKListboxs.addRoleToListbox(group.getRoles(), groupSelectedRoleListbox);
                groupAvailBtn.setDisabled(false);
                groupSelBtn.setDisabled(false);
                saveGroupBtn.setVisible(true);
                saveUserBtn.setVisible(false);
                cancelGroupBtn.setVisible(true);
                cancelUserBtn.setVisible(false);
            }
        });

        parentGroupCbox.addEventListener(Events.ON_CHANGE, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                Combobox parentGroup = (Combobox)event.getTarget();
                List<Object> removeSelectedRoles = new ArrayList<Object>();
                Set<Role> removeAvailRoles = new HashSet<Role>();
                if(parentGroup.getSelectedItem()!=null)
                {
                    Group changeGroup = (Group)parentGroup.getSelectedItem().getValue();
                    groupAvailableRoleListbox.getChildren().clear();
                    Boolean newGroupFlag = (Boolean)event.getTarget().getAttribute("newGroup");
                    if(newGroupFlag != null)
                    {
                        groupSelectedRoleListbox.getChildren().clear();
                        ZKListboxs.addRoleToListbox(changeGroup.getRoles(), groupSelectedRoleListbox);
                    }
                    else if(groupSelectedRoleListbox.getChildren() != null && groupSelectedRoleListbox.getChildren().size() > 0 && changeGroup.getRoles() != null && changeGroup.getRoles().size() > 0)
                    {                        
                        ZKListboxs.addRoleToListbox(changeGroup.getRoles(), groupAvailableRoleListbox );
                        for(Object obj :groupSelectedRoleListbox.getChildren())
                        {
                            Role role = (Role)((Listitem)obj).getValue();
                            if(changeGroup.getRoles() != null && changeGroup.getRoles().size() > 0)
                            {
                                Boolean foundRole = false;
                                for(Role changeRole:changeGroup.getRoles())
                                {                                    
                                    if(ObjectUtils.equals(role.getId(), changeRole.getId()))
                                    {                                       
                                        foundRole = true;
                                        break;
                                    }
                                }

                                if(!foundRole)
                                {
                                    removeSelectedRoles.add(obj);
                                }
                                else
                                {
                                    removeAvailRoles.add(role);
                                }
                            }
                        }
                        if(removeSelectedRoles.size() > 0)
                        {
                            groupSelectedRoleListbox.getChildren().removeAll(removeSelectedRoles);
                        }

                        if(removeAvailRoles.size() > 0)
                        {
                            ZKListboxs.removeRoleFromListbox(removeAvailRoles, groupAvailableRoleListbox);
                        }
                        

                    }
                    else
                    {                        
                        ZKListboxs.addRoleToListbox(changeGroup.getRoles(), groupAvailableRoleListbox );
                        groupSelectedRoleListbox.getChildren().clear();
                    }
                                   
                }
            }
        });


        userGroupCbox.addEventListener(Events.ON_CHANGE, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                Combobox userGroup = (Combobox)event.getTarget();
                List<Object> removeSelectedRoles = new ArrayList<Object>();
                Set<Role> removeAvailRoles = new HashSet<Role>();
                if(userGroup.getSelectedItem()!=null)
                {
                    Group changeGroup = (Group)userGroup.getSelectedItem().getValue();
                    userAvailableRoleListbox.getChildren().clear();
                    Boolean newUserFlag = (Boolean)event.getTarget().getAttribute("newUser");
                    if(newUserFlag != null)
                    {
                        userSelectedRoleListbox.getChildren().clear();
                        ZKListboxs.addRoleToListbox(changeGroup.getRoles(), userSelectedRoleListbox);
                    }
                    else if(userSelectedRoleListbox.getChildren() != null && userSelectedRoleListbox.getChildren().size() > 0 && changeGroup.getRoles() != null && changeGroup.getRoles().size() > 0)
                    {
                        ZKListboxs.addRoleToListbox(changeGroup.getRoles(), userAvailableRoleListbox);
                        for(Object obj :userSelectedRoleListbox.getChildren())
                        {
                            Role role = (Role)((Listitem)obj).getValue();
                            if(changeGroup.getRoles() != null && changeGroup.getRoles().size() > 0)
                            {
                                Boolean foundRole = false;
                                for(Role changeRole:changeGroup.getRoles())
                                {                                    
                                    if(ObjectUtils.equals(role.getId(), changeRole.getId()))
                                    {                                        
                                        foundRole = true;
                                        break;
                                    }
                                }

                                if(!foundRole)
                                {                                    
                                    removeSelectedRoles.add(obj);
                                }
                                else
                                {
                                    removeAvailRoles.add(role);
                                }
                            }
                        }
                        if(removeSelectedRoles.size() > 0)
                        {
                            userSelectedRoleListbox.getChildren().removeAll(removeSelectedRoles);
                        }

                        if(removeAvailRoles.size() > 0)
                        {
                            ZKListboxs.removeRoleFromListbox(removeAvailRoles, userAvailableRoleListbox);
                        }


                    }
                    else
                    {
                        ZKListboxs.addRoleToListbox(changeGroup.getRoles(), userAvailableRoleListbox);
                        userSelectedRoleListbox.getChildren().clear();
                    }

                }
            }
        });

        cancelGroupBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                int message = AlertMessages.confirmMessage("Do you want to cancel change data?");
                if(message == AlertMessages.NO || message == 0)
                {
                    return;
                }
                loadGroup();
            }
        });

        saveUserBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {

                HttpUserSession userSession = new HttpUserSession();
                Group userGroup = (Group)userSession.getUserInformation().get(Users.GROUP);
                List<String> alertMessages = new ArrayList();
                if(!validateUserData(alertMessages))
                {
                    StringBuffer messages = new StringBuffer(1024);

                    for(String message : alertMessages)
                    {
                        messages.append(message+"\n");
                    }
                    
                    AlertMessages.alertMessage(messages.toString());
                    return ;
                }


                int message = AlertMessages.confirmMessage("Do you want to confirm submit data?");
                if(message == AlertMessages.NO || message == 0)
                {
                    return;
                }
                try
                {
                    HashMap<String,Object> map = saveUser();
                    if((Boolean)map.get("results"))
                    {
                        saveImage();
                        AlertMessages.successMessage("Save User is successful.");
                        String treeLabels = UserCore.getTreeLabel((Long)map.get("userId"),userGroup.getId());
                        String ugTree[] = treeLabels.split(",",2);
                        HashMap<String,Object> treeMap = (HashMap<String,Object>)execution.getDesktop().getAttribute("tree");
                        UserGroupTreeitem item = (UserGroupTreeitem)treeMap.get(ugTree[0]);                    
                        Events.postEvent(Events.ON_CLICK,item,ugTree[1]);
                    }
                    else
                    {
                        AlertMessages.failMessage("Save User is failed.");
                    }
                }catch(Exception e)
                {
                    LOG.info(e.getMessage(),e);
                    if(e.getMessage().toLowerCase().indexOf("could not execute jdbc batch update") > -1)
                    {
                        AlertMessages.failMessage("Could not create duplicate user.");
                    }
                    else
                    {
                        AlertMessages.failMessage("Unexpected Error");
                    }
                }
            }
        });

        cancelUserBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                int message = AlertMessages.confirmMessage("Do you want to cancel change data?");
                if(message == AlertMessages.NO || message == 0)
                {
                    return;
                }
                loadUser();
            }

        });

        userSelBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                Set<Listitem> items = (Set<Listitem>)userAvailableRoleListbox.getSelectedItems();
                HashSet<Listitem> itemSelecteds = new HashSet<Listitem>();
                if(items.size() > 0)
                {                    
                    for(Listitem item :items)
                    {
                        itemSelecteds.add(item);
                    }

                    userAvailableRoleListbox.getChildren().removeAll(items);

                    for(Listitem item : itemSelecteds)
                    {
                        userSelectedRoleListbox.appendChild(item);
                    }
                    
                }
                else
                {
                    AlertMessages.alertMessage("Please select at least 1 row.");
                }
            }
        });

         userAvailBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                Set<Listitem> items = (Set<Listitem>)userSelectedRoleListbox.getSelectedItems();
                HashSet<Listitem> itemSelecteds = new HashSet<Listitem>();
                if(items.size() > 0)
                {
                    for(Listitem item :items)
                    {                                                
                        itemSelecteds.add(item);
                    }

                    userSelectedRoleListbox.getChildren().removeAll(items);

                    for(Listitem item : itemSelecteds)
                    {
                        userAvailableRoleListbox.appendChild(item);
                    }

                }
                else
                {
                    AlertMessages.alertMessage("Please select at least 1 row.");
                }
            }
        });

         groupSelBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {

                Set<Listitem> items = (Set<Listitem>)groupAvailableRoleListbox.getSelectedItems();
                HashSet<Listitem> itemSelecteds = new HashSet<Listitem>();
                if(items.size() > 0)
                {                    
                    for(Listitem item :items)
                    {
                        itemSelecteds.add(item);
                    }

                    groupAvailableRoleListbox.getChildren().removeAll(items);

                    for(Listitem item : itemSelecteds)
                    {
                        groupSelectedRoleListbox.appendChild(item);
                    }
                }
                else
                {
                    AlertMessages.alertMessage("Please select at least 1 row.");
                }
                    
            }
        });

         groupAvailBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                Set<Listitem> items = (Set<Listitem>)groupSelectedRoleListbox.getSelectedItems();
                HashSet<Listitem> itemSelecteds = new HashSet<Listitem>();
                if(items.size() > 0)
                {                    
                    for(Listitem item :items)
                    {
                        itemSelecteds.add(item);
                    }

                    groupSelectedRoleListbox.getChildren().removeAll(items);

                    for(Listitem item : itemSelecteds)
                    {
                        groupAvailableRoleListbox.appendChild(item);
                    }
                }
                else
                {
                    AlertMessages.alertMessage("Please select at least 1 row.");
                }
            }
        });

        setGroupBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                Long groupId = (Long)event.getData();
                loadGroup(groupId);
            }
        });

        filterActiveCbox.addEventListener(Events.ON_CHANGE, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
               renderInitailTree();
            }
        });

        setUserBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                Long userId = (Long)event.getData();
                loadUser(userId);
            }
        });
    }

    public void renderInitailTree() throws Exception
    {
        if(ugTree != null)
        {
            HttpUserSession userSession = new HttpUserSession();
            Group userGroup = (Group)userSession.getUserInformation().get(Users.GROUP);
//            Group userGroup = (Group)HibernateUtil.currentSession().get(Group.class, new Long(1L));


            ugItem = new UserGroupTreeitem(
                    userGroup.getGroupName(),
                    String.valueOf("G-"+userGroup.getId()),
                    userGroup.getId(),null,null,setGroupBtn,setUserBtn,filterActiveCbox
                    );
//            ugItem.setImage(groupImage);
            Treechildren child = null;
            if(ugTree.getLastChild() instanceof Treechildren)
            {
                child = (Treechildren)ugTree.getLastChild();
                child.getChildren().clear();
            }
            else
            {
                if(userGroup != null)
                {
                    child = new Treechildren();
                    ugTree.appendChild(child);
                }
            }
            child.appendChild(ugItem);
            Events.postEvent(Events.ON_CLICK, ugItem, null);
        }
    }
}
