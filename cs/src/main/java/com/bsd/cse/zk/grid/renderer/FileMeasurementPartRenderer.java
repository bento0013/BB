/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.grid.renderer;

import com.bsd.cse.app.input.MeasurementPartCore;
import com.bsd.cse.common.Configuration;
import com.bsd.cse.model.input.FileMeasurementPart;
import com.bsd.cse.zk.alertbox.AlertMessages;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zhtml.Form;
import org.zkoss.zhtml.Input;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.A;
import org.zkoss.zul.Button;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Toolbarbutton;


/**
 *
 * @author bento
 */
public class FileMeasurementPartRenderer implements RowRenderer{
    private static Log LOG = LogFactory.getLog(FileMeasurementPartRenderer.class);
    private static String FILE_PATH = null;
    private Button searchBtn;
    private Form form;
    private Textbox filePathTxt;
    private Textbox fileNameTxt;
    private static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm",Locale.US);
    static
    {
        try {

            FILE_PATH = Configuration.getString("cs.image.folder")+"\\file-measurement-part\\";
        } catch (Exception ex) {
            LOG.error(ex.getMessage(),ex);
        }        
    }


    public FileMeasurementPartRenderer(Button searchBtn,Form form,Textbox filePathTxt,Textbox fileNameTxt)
    {
        this.searchBtn = searchBtn;
        this.form = form;
        this.fileNameTxt = fileNameTxt;
        this.filePathTxt = filePathTxt;
    }

    @Override
    public void render(Row row, Object o) throws Exception {
        if(o instanceof String)
        {
            Cell cell = new Cell();
            
            cell.appendChild(new Label((String)o));
            cell.setSclass("cell-align-center");
//            cell.setParent(lstm);
            cell.setColspan(4);
            row.appendChild(cell);
        }
        else if(o instanceof FileMeasurementPart)
        {
            FileMeasurementPart fileMeasurementPart = (FileMeasurementPart)o;

            Cell cell = new Cell();
            A a = new A(fileMeasurementPart.getFilename());
            cell.appendChild(a);
            a.setAttribute("object", o);
            a.addEventListener(Events.ON_CLICK, new DownloadFileEventListener());
            cell.setSclass("cell-align-left");
            row.appendChild(cell);

            
            Label label = new Label(fileMeasurementPart.getCreatedBy().getUsername());
            cell = new Cell();
            cell.appendChild(label);
            cell.setSclass("cell-align-left");
            row.appendChild(cell);

            label = new Label(format.format(fileMeasurementPart.getCreatedDate()));
            cell = new Cell();
            cell.appendChild(label);
            cell.setSclass("cell-align-left");
            row.appendChild(cell);

            cell = new Cell();
            Toolbarbutton deleteButton = new Toolbarbutton();
            deleteButton.setImage("/images/input/delete.png");
            deleteButton.setAttribute("object", o);
            deleteButton.addEventListener(Events.ON_CLICK, new DeleteEventListener());
            cell.appendChild(deleteButton);
            cell.setSclass("cell-align-center");
            row.appendChild(cell);

            


        }
    }

    class DownloadFileEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            LOG.info("================ DOWNLOAD FILE ===================");
            FileMeasurementPart object = (FileMeasurementPart)event.getTarget().getAttribute("object");

                        
            form.setDynamicProperty("action","file-measurement.html");
            form.setDynamicProperty("method", "post");
            form.setDynamicProperty("target", "_self");
            String fileExtension = "";
            if(object.getFilename().indexOf(".") != -1)
            {
                fileExtension = object.getFilename().substring(object.getFilename().lastIndexOf("."));
            }                                                                    
            filePathTxt.setValue(String.valueOf(FILE_PATH+object.getId()+fileExtension));                    
            fileNameTxt.setValue(object.getFilename());
            Clients.submitForm(form);
        }

    }


    class DeleteEventListener implements EventListener
    {

        @Override
        public void onEvent(Event event) throws Exception {
            FileMeasurementPart object = (FileMeasurementPart)event.getTarget().getAttribute("object");
            int messageValue = AlertMessages.confirmMessage("measurementpart.alert.message.4");
            if(messageValue == 0 || messageValue == AlertMessages.NO)
            {
                return;
            }

            MeasurementPartCore core = new MeasurementPartCore();

            HashMap<String,Object> results = core.deleteFile(object);
            if((Boolean)results.get("results"))
            {
                AlertMessages.alertMessage("measurementpart.alert.message.file.6");
                String fileExtension = "";
                if(object.getFilename().indexOf(".") != -1)
                {
                    fileExtension = object.getFilename().substring(object.getFilename().indexOf("."));
                }
                LOG.info("file Delete  = "+FILE_PATH+object.getId()+fileExtension);
                
                File fileDelete = new File(FILE_PATH+object.getId()+fileExtension);
                
                if(fileDelete.exists())
                {
                    fileDelete.delete();
                }

                Events.sendEvent(Events.ON_CLICK,searchBtn, null);
            }
            else
            {
                AlertMessages.alertMessage("measurementpart.alert.message.7");
            }
        }

    }

}
