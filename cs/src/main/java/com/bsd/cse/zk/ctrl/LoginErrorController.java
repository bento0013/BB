/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl;

import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.util.ResourceUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.A;
import org.zkoss.zul.Label;
import org.zkoss.zul.Toolbarbutton;

/**
 *
 * @author bento
 */
public class LoginErrorController extends SecurityController{
    private static final long serialVersionUID = -9136371353381839991L;
    private static Log LOG  = LogFactory.getLog(LoginErrorController.class);
    private Label errorLog ;
    private Label message;
    private A retry;

    @Override
    public void doAfterCompose(Component component) throws Exception
    {
        super.doAfterCompose(component);
        createComponent(component);

        if(requestScope.get("disabled") != null && (Boolean)requestScope.get("disabled"))
        {
            errorLog.setValue(ResourceUtil.getLabel("default.alert.disabled.label"));
            message.setValue(ResourceUtil.getLabel("default.alert.disabled.message"));
            retry.setVisible(false);
        }
        else
        {
            errorLog.setValue(ResourceUtil.getLabel("default.alert.loginfail.label"));
            message.setValue(ResourceUtil.getLabel("default.alert.loginfail.message"));
            retry.setLabel(ResourceUtil.getLabel("default.alert.loginfail.retry"));
            retry.setVisible(true);
        }

    }

   

    public void createComponent(Component component)
    {
//        homeBtn = (Toolbarbutton)getComponent(component, "homeBtn", homeBtn);

        errorLog = (Label)getComponent(component, "errorLog", errorLog);
        message = (Label)getComponent(component, "message", message);
        retry = (A)getComponent(component, "retry", retry);
    }
   
}
