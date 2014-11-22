/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.ctrl.security;

import com.bsd.cse.app.security.GroupCore;
import com.bsd.cse.app.security.SettingCore;
import com.bsd.cse.app.security.TeamCore;
import com.bsd.cse.app.security.UserCore;
import com.bsd.cse.common.HibernateUtil;
import com.bsd.cse.common.catalog.LanguageCatalog;
import com.bsd.cse.model.security.Group;
import com.bsd.cse.model.security.Role;
import com.bsd.cse.model.security.Setting;
import com.bsd.cse.model.security.Team;
import com.bsd.cse.model.security.User;
import com.bsd.cse.security.Users;
import com.bsd.cse.zk.HttpUserSession;
import com.bsd.cse.zk.alertbox.AlertMessages;
import com.bsd.cse.zk.combobox.ZKCatalogs;
import com.bsd.cse.zk.ctrl.SecurityController;
import com.bsd.cse.zk.listbox.ZKListboxs;
import com.bsd.cse.zk.listbox.renderer.SettingRenderer;
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
import org.zkoss.zul.ListModelList;
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
import org.zkoss.zul.Window;

/**
 *
 * @author bento
 */
public class SystemSettingController extends SecurityController{
    private static Log LOG = LogFactory.getLog(SystemSettingController.class);         

    private Window editSettingWin ;    
    private Button saveSettingBtn;    
    private Button cancelSettingBtn;
    private Button searchBtn;
    private Textbox idTxt;
    private Textbox nameTxt;
    private Textbox valueTxt;
    private Listbox contentListbox;

    @Override
    public void doAfterCompose(Component component) throws Exception
    {        
        super.doAfterCompose(component);
        createComponent(component);
        initial();
        addEventListener();   
        loadContent();
                       
    }

    public void initial()
    {
        searchBtn = new Button();
//        desktop.setAttribute("editWin", editSettingWin);
    }

    public void loadContent() throws Exception
    {
        Events.postEvent(Events.ON_CLICK,searchBtn,null);        
    }

    public void addEventListener()
    {
              
        searchBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {                                
                SettingCore core = new SettingCore();
                HashMap<String,Object> metaData = core.getList();
                if(((Integer)metaData.get("allRecords")).equals(new Integer(0)))
                {
                    ArrayList<String> results = new ArrayList<String>();
                    results.add("No Record Found");
                    contentListbox.setModel(new ListModelList((List<String>)results));
                }
                else
                {
                    contentListbox.setModel(new ListModelList((List<Setting>)metaData.get("results")));
                    
                }

                contentListbox.setItemRenderer(new SettingRenderer(editSettingWin,idTxt,nameTxt,valueTxt));
            }
        });
        
        saveSettingBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                HttpUserSession userSession = new HttpUserSession();
                Long userId = (Long)userSession.getUserInformation().get(Users.SYSTEMID);
                Setting setting = new Setting();
                String id = idTxt.getValue();
                String value = valueTxt.getValue();
                setting.setId(Long.valueOf(id));
                setting.setValue(value);
                SettingCore core = new SettingCore();
                HashMap<String,Object> results = core.saveSetting(setting, userId);
                if((Boolean)results.get("results"))
                {
                    editSettingWin.setVisible(false);                                       
                    AlertMessages.successMessage("Save Setting is Successful.");
                    Events.sendEvent(Events.ON_CLICK, searchBtn, null);
                    event.stopPropagation();                                         
                }
                else
                {
                    AlertMessages.failMessage("Save Setting is Failed.");
                    return;
                }
            }
        });

        cancelSettingBtn.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {                
                editSettingWin.setVisible(false);
                event.stopPropagation();

            }
        });
    }  

    public void createComponent(Component component)
    {
        editSettingWin = (Window)getComponent(component, "editSettingWin", editSettingWin);        
        saveSettingBtn = (Button)getComponent(editSettingWin, "saveSettingBtn", saveSettingBtn);
        cancelSettingBtn = (Button)getComponent(editSettingWin, "cancelSettingBtn", cancelSettingBtn);
        nameTxt  = (Textbox)getComponent(editSettingWin, "nameTxt", nameTxt);
        idTxt  = (Textbox)getComponent(editSettingWin, "idTxt", idTxt);
        valueTxt  = (Textbox)getComponent(editSettingWin, "valueTxt", valueTxt);
        contentListbox = (Listbox)getComponent(component, "contentListbox", contentListbox);
//        searchBtn = (Button)getComponent(component, "searchBtn", searchBtn);
    }

    
}
