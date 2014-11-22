/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.listbox;

import com.bsd.cse.model.security.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

/**
 *
 * @author bento
 */
public class ZKListboxs {
    private static Log LOG = LogFactory.getLog(ZKListboxs.class);
    public static void addRoleToListbox(Set<Role> data,Listbox box)
    {
        for(Role role:data)
        {
            Listitem item = new Listitem(role.getRoleName(), role);
            box.appendChild(item);
        }
    }

    public static void removeRoleFromListbox(Set<Role> selRoles,Listbox src)
    {
        Set<Listitem> deleteItem = new HashSet<Listitem>();
        for(Role role:selRoles)
        {           
            List<Listitem> items = (List<Listitem>)src.getChildren();
            for(Listitem item : items)
            {
                Role roleItem = (Role)item.getValue();
                if(ObjectUtils.equals(roleItem.getId(), role.getId()))
                {
                    deleteItem.add(item);
                    break;
                }
            }            
        }        
        src.getChildren().removeAll(deleteItem);
    }

}
