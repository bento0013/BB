/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.menu;

import com.bsd.cse.app.Menu.Item;
import com.bsd.cse.app.Menu.SubMenu;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.ctrl.PublicController;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.util.ResourceUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Menu;

/**
 *
 * @author thanasith
 */
public class MenuController extends PublicController{
    private static final long serialVersionUID = 6378576577835667636L;
    private Menubar menubar;
    private static Log LOG  = LogFactory.getLog(MenuController.class);

    @Override
    public void doAfterCompose(Component component) throws Exception
    {

        super.doAfterCompose(component);
//        HttpServletRequest request = (HttpServletRequest)Executions.getCurrent().getNativeRequest();
       
        if(execution.getUserPrincipal() != null)
        {
            Map<String,List<Object>> map = com.bsd.cse.app.Menu.get(new HttpUserSession(), "");
            List<Object> menus = (List<Object>)map.get("menus");
            LOG.info("menus size = "+menus.size());
            menubar.setVisible(true);

            Menuitem menuitem = new Menuitem();
//                    menuitem.setZclass("z-head-menu");
//            menuitem.setImage();
            menuitem.setHref("/secure/home.html");
            menuitem.setLabel(ResourceUtil.getLabel("header.label.home"));
            menubar.appendChild(menuitem);

            for(Object obj : menus)
            {
                Component addMenu = null;
                if(obj instanceof Item)
                {                    
                    Item item = (Item)obj;

                    if(item.getSubmenu() != null)
                    {
                        Menu menu = new Menu();
    //                    menu.setZclass("z-head-menu");
                        menu.setLabel(ResourceUtil.getLabel(item.getText()));
                        menu.setImage(item.getImage());
                        Menupopup menupopup = new Menupopup();
    //                    menupopup.setZclass("z-head-menu-popup");
                        menu.appendChild(menupopup);
                        createSubMenu(item.getSubmenu(),menupopup);
                        addMenu = menu;
                    }
                    else
                    {
                        menuitem = new Menuitem();
    //                    menuitem.setZclass("z-head-menu");
                        menuitem.setImage(item.getImage());
                        menuitem.setHref(item.getUrl());
                        menuitem.setLabel(ResourceUtil.getLabel(item.getText()));
                        addMenu = menuitem;
                    }

                    if(addMenu != null)
                    {
                        menubar.appendChild(addMenu);
                        addMenu = null;
                    }
                }
            }
                       
        }
        else
        {
            menubar.setVisible(false);
        }
    }

    private void createSubMenu(SubMenu submenu,Menupopup popup)
    {
        for(Item item : submenu.getItemdata())
        {
            Component addMenu = null;
            if(item.getSubmenu() != null)
            {
                Menu menu = new Menu();
//                menu.setZclass("z-head-menu");
                menu.setLabel(ResourceUtil.getLabel(item.getText()));
                Menupopup menupopup = new Menupopup();
//                menupopup.setZclass("z-head-menu-popup");
                menu.appendChild(menupopup);
                createSubMenu(item.getSubmenu(),menupopup);
                addMenu = menu;
            }
            else
            {
                Menuitem menuitem = new Menuitem();
//                menuitem.setZclass("z-head-menu-item");
                menuitem.setHref(item.getUrl());
                menuitem.setLabel(ResourceUtil.getLabel(item.getText()));
                addMenu = menuitem;
            }            
            popup.appendChild(addMenu);
        }
        
    }
}
