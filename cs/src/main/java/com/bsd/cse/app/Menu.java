/*
 * Copyright (R) 2008 Advanced Research Group Co., Ltd.
 * All Rights Reserved.
 */


package com.bsd.cse.app;

import com.bsd.cse.common.Configuration;
import com.bsd.cse.common.app.TransactionalProcessor;
import com.bsd.cse.model.AbstractPojo;
import com.bsd.cse.model.security.Function;
import com.bsd.cse.security.Functions;
import com.bsd.cse.security.UserSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
  */
public class Menu
{
    //~ Static fields/initializers ·············································

    private static final Log LOG = LogFactory.getLog(Menu.class);

    /**
     * DOCUMENT ME!
     */
    public static final String EMPTY_COMMAND = "javascript:void(0)";

//    /**
//     * DOCUMENT ME!
//     */
//    protected static String LOGOFF_CMD_PATTERN = Configuration.getString("menu.logoff.cmd.pattern",
//                                                                         "%1$s/logoff.html");
//
//    /**
//     * DOCUMENT ME!
//     */
//    protected static String LOGOFF_TEXT_PATTERN = Configuration.getString("menu.logoff.text.pattern",
//                                                                          "Logoff [%1$s]");
    private static List<Item> cached;

    /**
     * DOCUMENT ME!
     */
    protected static boolean SHOW_NO_PRIVILEDGE_FUNCTIONS = false;/* Configuration
                                                                    .getBoolean("menu.functions.no-priv-items.show",
                                                                                true);*/

    //~ Constructors ···························································

    private Menu()
    {
    }

    //~ Methods ································································

    /**
     * DOCUMENT ME!
     *
     * @param session DOCUMENT ME!
     * @param contextPath DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public static Map<String, List<Object>> get(final UserSession session,
                                                final String contextPath)
    {       
        List<Object> menus = new ArrayList<Object>();
        Map<String, List<Object>> map = new HashMap<String, List<Object>>();
        String ctxpath = contextPath;

        if (StringUtils.isNotBlank(ctxpath))
        {
            if (!ctxpath.endsWith("/"))
            {
                ctxpath += "/";
            }
        }
        else
        {
            ctxpath = EMPTY_COMMAND;
        }

//        menus.add(new Item("Home", ctxpath));       

        try
        {
            if (session != null && session.isValid())
            {
                try
                {
                    List<Item> menu_items = null;

                    if (!SHOW_NO_PRIVILEDGE_FUNCTIONS)
                    {                        
                       menu_items = createMenus(contextPath, session);
                    }
                    else if (null == cached)
                    {
                        synchronized (Menu.class)
                        {
                            if (null == cached)
                            {                                
                                menu_items = (cached = createMenus(contextPath));
                            }
                        }
                    }
                    else
                    {
                        menu_items = cached;
                    }

                    menus.addAll(menu_items);
                }
                finally
                {
                }
//                finally
//                {
//                    menus.add(new Item(String.format(LOGOFF_TEXT_PATTERN,
//                                                     session.getLoginId()),
//                                       String.format(LOGOFF_CMD_PATTERN, ctxpath)));
//                }
            }            
        }
        catch (final Exception e)
        {
            LOG.error(e.getMessage(), e);

            if (LOG.isTraceEnabled())
            {
                e.printStackTrace();
            }
        }

        map.put("menus", menus);

        return map;
    }

    /**
     * DOCUMENT ME!
     *
     * @param contextPath DOCUMENT ME!
     * @param session DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws Exception DOCUMENT ME!
     */
    public static List<Item> createMenus(final String contextPath,
                                         final UserSession session)
            throws Exception
    {
        List<Item> menus = new ArrayList<Item>();
        List<Long> perms =  new ArrayList<Long>();
        perms.addAll((List<Long>) session.getUserInformation().get(UserSession.PERMISSIONS_KEY));                


       

       
        if(perms !=null)
        {
            if (LOG.isDebugEnabled())
            {
                LOG.debug(String.format("Total %1$d function(s) to be added to %2$s's menu.",
                            perms.size(), session.getLoginId()));
            }

        
            for (Long id : perms)
            {             
                Function function = Functions.locate(id);                

                Item item = locateItem(menus, id);
                boolean cascade_create = Boolean.TRUE.equals(function.getVisible());

                if (!cascade_create)
                {
                    function = function.getParent();
                    item = ((null == function) ? null : locateItem(menus, function.getId()));
                }

                if ((null == item) && (null != function)
                    && Boolean.TRUE.equals(function.getVisible()))
                {
                    Function parent_func = function.getParent();

                    item = new Item(function, contextPath, cascade_create);

                    if (null == parent_func  || parent_func.getId().equals(1L))
                    {
                        menus.add(item);
                    }
                    else
                    {
                        /* preparing the parent nodes */
                        while (null != parent_func)
                        {
                            Item parent_item = locateItem(menus, parent_func.getId());

                            if (null == parent_item)
                            {
                                parent_item = new Item(parent_func, contextPath, false);
                            }

                            if (!parent_item.contains(item.functionId))
                            {
                                parent_item.addChild(item);
                            }


                            if ((null != (parent_func = parent_func.getParent()) && parent_func.getId().equals(1L))
                                && !menus.contains(parent_item))
                            {
                                menus.add(parent_item);
                            }

                            item = parent_item;
                        }
                    }
                }
            }
        }

        Collections.sort(menus, new ItemComparator());

        return menus;
    }


    private static Item locateItem(final List<Item> menus, final Long id)
    {
        Item item = null;

        for (Item menu : menus)
        {            
            if (id.equals(menu.functionId))
            {
                item = menu;

                break;
            }
            else if (null != (item = menu.locateChild(id)))
            {
                break;
            }
        }

        return item;
    }

    /**
     * DOCUMENT ME!
     *
     * @param contextPath DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws Exception DOCUMENT ME!
     */
    public static List<Item> createMenus(final String contextPath)
            throws Exception
    {
        synchronized (Menu.class)
        {
            final List<Item> MENUS = new ArrayList<Item>();
            (new TransactionalProcessor(LOG)
                {
                    public void process(final Session session,
                                        final Transaction tx)
                            throws Exception
                    {                        
                        for (final Function function : Functions.list())
                        {
                            if ((function.getParent() == null)
                                && Boolean.TRUE.equals(function.getVisible()))
                            {
                                MENUS.add(new Item(function, contextPath));

                                if (LOG.isDebugEnabled())
                                {
                                    LOG.debug(String.format("Function '%1$s' added as a menu item.",
                                                            function.getFunctionName()));
                                }
                            }
                        }

                        Collections.sort(MENUS, new ItemComparator());
                    }
                }).process();

            return MENUS;
        }
    }

    /**
     * DOCUMENT ME!
     */
    public static synchronized void invalidate()
    {
        synchronized (Menu.class)
        {
            Functions.invalidate();

            cached = null;
//            LOGOFF_CMD_PATTERN = Configuration.getString("menu.logoff.cmd.pattern",
//                                                         "%1$s/logoff.html");
//            LOGOFF_TEXT_PATTERN = Configuration.getString("menu.logoff.text.pattern",
//                                                          "Logoff [%1$s]");
            SHOW_NO_PRIVILEDGE_FUNCTIONS = Configuration.getBoolean("menu.functions.no-priv-items.show",
                                                                    true);
        }
    }

    //~ Inner Classes ··························································

    /**
     * DOCUMENT ME!
     *
     * @author $author$
     * @version $Revision: 1.1 $
      */
    public static class Item
            extends AbstractPojo
    {
        Long functionId;
        /**
         * DOCUMENT ME!
         */
        Integer order = new Integer(0);

        /**
         * DOCUMENT ME!
         */
        String description;

        /**
         * DOCUMENT ME!
         */
        String text;

        /**
         * DOCUMENT ME!
         */
        String url;


        String image;

        /**
         * DOCUMENT ME!
         */
        SubMenu submenu;

        Item(final String text, final String contextPath)
        {
            LOG.debug("\to function " + this.text + " created.");
            this.text = text;
            this.url = contextPath;
        }

        Item(final Function function, final String contextPath)
        {
            this(function, contextPath, true);
        }
        /**
         * Creates a new Item object.
         *
         * @param function DOCUMENT ME!
         * @param contextPath DOCUMENT ME!
         */
        Item(final Function function, final String contextPath, final boolean cascadeChildren)
        {
            String cmd = function.getCommand();
//            Set<Function> children = function.getChildren();

            this.text = function.getFunctionName();
            this.order = function.getOrderNo();
            this.description = function.getFunctionDescription();
            this.functionId = function.getId();            

            if (StringUtils.isBlank(cmd))
            {
                this.url = EMPTY_COMMAND;
            }
            else if (cmd.startsWith("javascript:"))
            {
                this.url = cmd;
            }
            else if (cmd.startsWith("/"))
            {
                this.url = contextPath + cmd;
            }
            else
            {
                this.url = contextPath + "/" + cmd;
            }

        }

        /**
         * DOCUMENT ME!
         *
         * @return DOCUMENT ME!
         */
        public String getText()
        {
            return this.text;
        }

        /**
         * DOCUMENT ME!
         *
         * @return DOCUMENT ME!
         */
        public String getDescription()
        {
            return this.description;
        }

        /**
         * DOCUMENT ME!
         *
         * @return DOCUMENT ME!
         */
        public String getUrl()
        {
            return this.url;
        }

        /**
         * DOCUMENT ME!
         *
         * @return DOCUMENT ME!
         */
        public SubMenu getSubmenu()
        {
            return this.submenu;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        

        public boolean equals(Object o)
        {
            if (o instanceof Item)
            {
                return (null != this.functionId) && this.functionId.equals(((Item) o).functionId);
            }

            return false;
        }

        public int hashCode()
        {
            return ((null == this.functionId) ? 0 : this.functionId.hashCode());
        }

        public boolean contains(final Long id)
        {
            if (null != this.submenu)
            {
                for (Item item : this.submenu.itemdata)
                {
                    if (((null != item.functionId) && item.functionId.equals(id))
                        || item.contains(id))
                    {
                        return true;
                    }
                }
            }

            return false;
        }

        public Item locateChild(final Long id)
        {
            id.getClass(); // check null

            Item child = null;

            if (null != this.submenu)
            {
                for (Item item : this.submenu.itemdata)
                {
                    if (id.equals(item.functionId))
                    {
                        child = item;

                        break;
                    }
                    else
                    {
                        Item tmp = item.locateChild(id);

                        if (null != tmp)
                        {
                            child = tmp;

                            break;
                        }
                    }
                }
            }

            return child;
        }

        public Item addChild(final Item child)
        {
            if (null == this.submenu)
            {
                this.submenu = new SubMenu(child.functionId.toString());
            }

            return this.submenu.addItem(child);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @author $author$
     * @version $Revision: 1.1 $
      */
    public static class SubMenu
            extends AbstractPojo
    {
        /**
         * DOCUMENT ME!
         */
        List<Item> itemdata = new ArrayList<Item>();

        /**
         * DOCUMENT ME!
         */
        String id;
        String parentId;

        SubMenu(final String parentId)
        {
            this.parentId = parentId;
            this.id = String.format("submenu-%1$s", parentId.toString());
        }

        /**
         * Creates a new SubMenu object.
         *
         * @param parentId DOCUMENT ME!
         * @param functions DOCUMENT ME!
         * @param contextPath DOCUMENT ME!
         */
        SubMenu(final String parentId, final Set<Function> functions,
                final String contextPath)
        {
            this(parentId);

            for (final Function function : functions)
            {
                if (Boolean.TRUE.equals(function.getVisible()))
                {
                    itemdata.add(new Item(function, contextPath));

                    if (LOG.isDebugEnabled())
                    {
                        LOG.debug(String.format("Function named '%1$s' added as a submenu item.",
                                                function.getFunctionName()));
                    }
                }
            }

            Collections.sort(itemdata, new ItemComparator());
        }

        /**
         * DOCUMENT ME!
         *
         * @return DOCUMENT ME!
         */
        public String getId()
        {
            return this.id;
        }

        /**
         * DOCUMENT ME!
         *
         * @return DOCUMENT ME!
         */
        public List<Item> getItemdata()
        {
            return this.itemdata;
        }

        public Item addItem(final Item item)
        {
            this.itemdata.add(item);
            Collections.sort(this.itemdata, new ItemComparator());

            return item;
        }
    }

    private static class ItemComparator
            implements Comparator<Item>
    {
        /**
         * DOCUMENT ME!
         *
         * @param that DOCUMENT ME!
         *
         * @return DOCUMENT ME!
         */
        public boolean equals(final Object that)
        {
            return (this == that);
        }

        /**
         * DOCUMENT ME!
         *
         * @param o1 DOCUMENT ME!
         * @param o2 DOCUMENT ME!
         *
         * @return DOCUMENT ME!
         */
        public int compare(final Item o1, final Item o2)
        {
            return o1.order.compareTo(o2.order);
        }
    }
}
