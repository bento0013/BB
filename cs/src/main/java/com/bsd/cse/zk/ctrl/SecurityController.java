/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl;

import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.util.GenericForwardComposer;

/**
 *
 * @author bento
 */
public class SecurityController extends GenericForwardComposer{
    

    private static Log LOG = LogFactory.getLog(SecurityController.class);
    private static final long serialVersionUID = -8949949411483585198L;

     @Override
    public ComponentInfo doBeforeCompose(Page page,Component component,ComponentInfo comInfo){
        ComponentInfo info  = super.doBeforeCompose(page, component, comInfo);        

        return info;
    }

    @Override
    public void doAfterCompose(Component component) throws Exception
    {
        if(Executions.getCurrent().getSession().getAttribute(Attributes.PREFERRED_LOCALE) != null)
        {
            Executions.getCurrent().getSession().setAttribute(Attributes.PREFERRED_LOCALE,Locale.US);
        }
        super.doAfterCompose(component);
    }

    protected Component getComponent(Component component,String comName,Component object)
    {
        if(object != null)
        {
            if(LOG.isDebugEnabled())
            {
                LOG.debug(comName+" = "+object);
            }
            return object;
        }
        if(LOG.isDebugEnabled())
        {
            LOG.debug(comName+" = "+component.getFellowIfAny(comName));
        }
        return component.getFellowIfAny(comName);
    }

    protected String getLocale()
    {
        Object obj = execution.getSession().getAttribute(Attributes.PREFERRED_LOCALE);
        return (obj == null?true:(((Locale)obj).getLanguage().equals("en")))?"en":"lo";
    }    
}
