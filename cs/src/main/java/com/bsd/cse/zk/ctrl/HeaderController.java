/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl;

import com.bsd.cse.common.Configuration;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.image.AImage;
import org.zkoss.util.ResourceUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Toolbarbutton;

/**
 *
 * @author bento
 */
public class HeaderController extends SecurityController{
    private static final long serialVersionUID = -9136371353381839991L;
    private static Log LOG  = LogFactory.getLog(HeaderController.class);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");
//    private Toolbarbutton homeBtn;
    private Toolbarbutton loginBtn;
    private Toolbarbutton logoutBtn;
    private Label logininfo ;
    private Label logintime ;
    private Label loginLbl;
    private Label loginTimeLbl;
    private Image loginImg;
    private static String IMAGE_PATH;
    private static org.zkoss.image.Image DEFAULT_IMAGE;

    static
    {
        try {            
            IMAGE_PATH = Configuration.getString("cs.image.folder")+"\\image\\";
            DEFAULT_IMAGE = new AImage(IMAGE_PATH+"no-image.png");            
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
        createEventListener();
        loginvalidate();

    }

    public void loginvalidate() throws IOException
    {
        if(execution.getUserPrincipal() != null)
        {
//            homeBtn.setVisible(true);
            logoutBtn.setVisible(true);
            loginBtn.setVisible(false);
            logininfo.setVisible(true);
            logintime.setVisible(true);
            HttpUserSession userSession = new HttpUserSession();
            Date loginTime = (Date)userSession.getUserInformation().get(Users.LAST_LOGIN_TIME);                     
            logininfo.setValue((String)userSession.getUserInformation().get(Users.FULL_NAME));
            loginImg.setVisible(true);
            org.zkoss.image.Image userImage = new AImage(IMAGE_PATH+userSession.getLoginId()+".png");
            loginImg.setContent(userImage);
            logintime.setValue(dateFormat.format(loginTime));
        }
        else
        {
//            homeBtn.setVisible(false);
            logoutBtn.setVisible(false);
            logininfo.setVisible(false);
            logintime.setVisible(false);
            loginImg.setVisible(false);
            loginLbl.setVisible(false);
            loginTimeLbl.setVisible(false);
            loginBtn.setVisible(true);
        }
    }

    public void createComponent(Component component)
    {
//        homeBtn = (Toolbarbutton)getComponent(component, "homeBtn", homeBtn);
        loginBtn = (Toolbarbutton)getComponent(component, "loginBtn", loginBtn);
        logoutBtn = (Toolbarbutton)getComponent(component, "logoutBtn", logoutBtn);
        logininfo = (Label)getComponent(component, "logininfo", logininfo);
        logintime = (Label)getComponent(component, "logintime", logintime);
    }

    public void createEventListener()
    {
//        homeBtn.addEventListener(Events.ON_CLICK,new EventListener() {
//
//            @Override
//            public void onEvent(Event event) throws Exception {
//                LOG.info("home click");
//                execution.sendRedirect("/secure/home.html");
//            }
//        });

        loginBtn.addEventListener(Events.ON_CLICK,new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                LOG.info("home click");
                execution.sendRedirect("/secure/home.html");
            }
        });

        logoutBtn.addEventListener(Events.ON_CLICK,new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                LOG.info("logout click");
                execution.sendRedirect("/public/logout.html");
            }
        });
    }
}
