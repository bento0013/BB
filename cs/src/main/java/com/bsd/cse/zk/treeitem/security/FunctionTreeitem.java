/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.treeitem.security;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;

/**
 *
 * @author bento
 */
public class FunctionTreeitem extends Treeitem {

    private static Log LOG = LogFactory.getLog(FunctionTreeitem.class);
    private FunctionTreeitem parentTreeitem = null;


    public FunctionTreeitem(String label ,String value,FunctionTreeitem parentItem)
    {
        super(label,value);
        this.setId(value);
        this.parentTreeitem = parentItem;
        this.setOpen(Boolean.FALSE);
        renderTree();
        this.createEventTree();

    }

    private void checkAllSubTree(Treechildren treeChildren , Boolean checked)
    {
        if(treeChildren != null)
        {
            List<FunctionTreeitem> items =  (List<FunctionTreeitem>)treeChildren.getChildren();
            if(items != null && items.size() > 0)
            {
                for(FunctionTreeitem childItem : items)
                {

                    Treechildren child = childItem.getTreechildren();
                    if(child != null)
                    {
                        checkAllSubTree(child,checked);

                    }
                    childItem.setSelected(checked);

                }
            }
        }
    }

    public Boolean checkMarkSubTree()
    {
        FunctionTreeitem item = this;
        return checkMarkSubTree(item);

    }

    private Boolean checkMarkSubTree(FunctionTreeitem item)
    {
        Boolean check = Boolean.TRUE;
        if(item != null)
        {

            Treechildren treeChildren = item.getTreechildren();

            if(treeChildren != null && treeChildren.getChildren() != null && treeChildren.getChildren().size() > 0)
            {
                for(FunctionTreeitem childItem : (List<FunctionTreeitem>)treeChildren.getChildren())
                {
                    Boolean localCheck = checkMarkSubTree(childItem);
                    if(localCheck.equals(Boolean.FALSE))
                    {
                        check  = Boolean.FALSE;
                    }

                    if(!localCheck)
                    {
                        item.setSelected(localCheck);
                    }
                }
                item.setSelected(check);
            }
            else
            {
                return item.isSelected();
            }
        }

        return check;

    }

    private void createEventTree()
    {
        this.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                LOG.info("Event ON Click");
               FunctionTreeitem item = (FunctionTreeitem)event.getTarget();
               Tree tree = item.getTree();
               Boolean checked = item.isSelected();
               item.setSelected(checked);
               FunctionTreeitem parentItem = item.getParentTreeitem() ;
               FunctionTreeitem rootItem = parentItem;
               while(parentItem != null)
               {
                   if(parentItem.getParentTreeitem() != null && parentItem.getParentTreeitem() instanceof FunctionTreeitem)
                   {
                       parentItem = parentItem.getParentTreeitem();
                       rootItem = parentItem;
                   }
                   else if(parentItem.getParentTreeitem() != null)
                   {
                       parentItem = parentItem.getParentTreeitem();
                       rootItem = parentItem;
                   }
                   else if(parentItem.getParentTreeitem() == null)
                   {
                       break;
                   }
               }


                if(item.getTreechildren() instanceof  Treechildren)
                {
                    Treechildren child = item.getTreechildren();
                    checkAllSubTree(child,checked);
                }
//
                if(rootItem != null)
                {

                    checkMarkSubTree(rootItem);
                }


            }
        });

    }

    private void renderTree()
    {
        try {
            Treechildren child = null;
            if(parentTreeitem != null)
            {
                if(parentTreeitem.getLastChild() instanceof Treechildren)
                {
                    child = (Treechildren)parentTreeitem.getLastChild();
                    child.appendChild(this);
                }
                else
                {
                    child = new Treechildren();
                    parentTreeitem.appendChild(child);
                    child.appendChild(this);
                }


            }

        } catch (Exception ex) {
            LOG.error("Error",ex);
        }
    }

    public FunctionTreeitem getParentTreeitem() {
        return parentTreeitem;
    }

    public void setParentTreeitem(FunctionTreeitem parentTreeitem) {
        this.parentTreeitem = parentTreeitem;
    }


}

