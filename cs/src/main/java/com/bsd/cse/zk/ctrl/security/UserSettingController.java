/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.security;

import com.bsd.cse.app.security.GroupCore;
import com.bsd.cse.app.security.TeamCore;
import com.bsd.cse.app.security.UserCore;
import com.bsd.cse.common.HibernateUtil;
import com.bsd.cse.common.catalog.LanguageCatalog;
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
import org.zkoss.web.Attributes;
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
import org.zkoss.zul.Radio;
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
public class UserSettingController extends SecurityController{
    private static Log LOG = LogFactory.getLog(UserSettingController.class);     
    private static String PASSWORD_PATTERN;
    private Button changeLanguageBtn;
    private Button cancelChangeLanguageBtn;
    private Button changePasswordBtn;
    private Button cancelChangePasswordBtn;
    private Textbox oldPasswordTxt;
    private Textbox newPasswordTxt;
    private Textbox confirmPasswordTxt;
    private Radio engChk;
    private Radio thaiChk;
    private User user;

    static
    {
        try {
            PASSWORD_PATTERN = "[0-9]{8,}";                        
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
        loadContent();
        addEventListener();                  
    }

    public void loadContent() throws Exception
    {
        HttpUserSession userSession = new HttpUserSession();
        user = UserCore.getUser((Long)userSession.getUserInformation().get(Users.SYSTEMID));
        if(user.getLanguage() != null && user.getLanguage().equals("T"))
        {
            thaiChk.setChecked(true);
        }
        else
        {
            engChk.setChecked(true);
        }

    }

    public void addEventListener()
    {
        changeLanguageBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                if(thaiChk.isChecked())
                {
                    user.setLanguage(thaiChk.getValue());
                    session.setAttribute(Attributes.PREFERRED_LOCALE,LanguageCatalog.getLocale(thaiChk.getValue()) );                    
                }
                else
                {
                    user.setLanguage(engChk.getValue());
                    session.setAttribute(Attributes.PREFERRED_LOCALE,LanguageCatalog.getLocale(engChk.getValue()) );
                }
                LOG.info("Locale = "+session.getAttribute(Attributes.PREFERRED_LOCALE));
                user.setPassword(null);
                HashMap<String,Object> results = UserCore.saveUser(user, user.getId());
                if((Boolean)results.get("results"))
                {                    
                    AlertMessages.successMessage("Change Language is success");
                    
                }
                execution.sendRedirect("/secure/security/us.html");

            }
        });

        cancelChangeLanguageBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                loadContent();
            }
        });

        changePasswordBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {

                try
                {
                    String oldPassword = oldPasswordTxt.getText();
                    String newPassword = newPasswordTxt.getText();
                    String confirmPassword = confirmPasswordTxt.getText();
                    if(oldPassword.isEmpty())
                    {
                        AlertMessages.alertMessage("Old password is required");
                        return ;
                    }

                    if(newPassword.isEmpty())
                    {
                        AlertMessages.alertMessage("New password is required");
                        return ;
                    }

                    if(confirmPassword.isEmpty())
                    {
                        AlertMessages.alertMessage("Confirm password is required");
                        return ;
                    }

                    if(!newPassword.isEmpty() &&
                        !confirmPassword.isEmpty() &&
                        !newPassword.equals(confirmPassword))
                    {
                        AlertMessages.alertMessage("New password and Confirm must be equals.");
                        return ;
                    }

                    if(!UserCore.encPassword(oldPassword).equals(user.getPassword()))
                    {
                        AlertMessages.alertMessage("Old password is incorrect.");
                        return ;
                    }

                    if(!Pattern.matches(PASSWORD_PATTERN, newPassword))
                    {
                        AlertMessages.alertMessage("New password is must be Number 8 digits or more");
                        return ;
                    }
                    
                    user.setPassword(newPassword);
                    HashMap<String,Object> results = UserCore.saveUser(user, user.getId());
                    if((Boolean)results.get("results"))
                    {
                        clearPassword();
                        AlertMessages.successMessage("Change Password is success");
                        return ;
                    }
                }
                catch(Exception e)
                {
                    AlertMessages.successMessage("Change Password is failed");
                    return ;
                }
                
            }
        });

        cancelChangePasswordBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                clearPassword();
                
            }
        });
        
    }


    private void clearPassword()
    {
        oldPasswordTxt.setValue("");
        newPasswordTxt.setValue("");
        confirmPasswordTxt.setValue("");
    }




   

    public void createComponent(Component component)
    {
        changeLanguageBtn  = (Button)getComponent(component, "changeLanguageBtn", changeLanguageBtn);
        cancelChangeLanguageBtn  = (Button)getComponent(component, "cancelChangeLanguageBtn", cancelChangeLanguageBtn);
        changePasswordBtn  = (Button)getComponent(component, "changePasswordBtn", changePasswordBtn);
        cancelChangePasswordBtn  = (Button)getComponent(component, "cancelChangePasswordBtn", cancelChangePasswordBtn);
        oldPasswordTxt  = (Textbox)getComponent(component, "oldPasswordTxt", oldPasswordTxt);
        newPasswordTxt  = (Textbox)getComponent(component, "newPasswordTxt", newPasswordTxt);
        confirmPasswordTxt  = (Textbox)getComponent(component, "confirmPasswordTxt", confirmPasswordTxt);
        engChk  = (Radio)getComponent(component, "engChk", engChk);
        thaiChk  = (Radio)getComponent(component, "thaiChk", thaiChk);
    }

    
}
