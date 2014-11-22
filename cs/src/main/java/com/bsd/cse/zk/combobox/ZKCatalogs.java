/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bsd.cse.zk.combobox;

import com.bsd.cse.app.security.GroupCore;
import com.bsd.cse.model.backoffice.customer.Customer;
import com.bsd.cse.model.backoffice.machine.Machine;
import com.bsd.cse.model.backoffice.machine.MachineModel;
import com.bsd.cse.model.backoffice.machine.MachineModelType;
import com.bsd.cse.model.backoffice.material.raw.RawMaterial;
import com.bsd.cse.model.backoffice.material.raw.RawType;
import com.bsd.cse.model.backoffice.material.semi.SemiMaterial;
import com.bsd.cse.model.backoffice.material.semi.SemiType;
import com.bsd.cse.model.backoffice.measurement.MeasurementTool;
import com.bsd.cse.model.backoffice.measurement.MeasurementToolModel;
import com.bsd.cse.model.backoffice.measurement.MeasurementToolType;
import com.bsd.cse.model.backoffice.part.Checkpoint;
import com.bsd.cse.model.backoffice.part.CheckpointType;
import com.bsd.cse.model.backoffice.part.CheckpointUnit;
import com.bsd.cse.model.backoffice.part.NgReason;
import com.bsd.cse.model.backoffice.part.Part;
import com.bsd.cse.model.backoffice.part.Process;
import com.bsd.cse.model.input.MeasurementPosition;
import com.bsd.cse.model.input.MeasurementTime;
import com.bsd.cse.model.security.Group;
import com.bsd.cse.model.security.Team;
import com.bsd.cse.model.security.User;
import com.bsd.cse.model.stock.TransferType;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;

/**
 *
 * @author bento
 */
public class ZKCatalogs {

    private static Log LOG = LogFactory.getLog(ZKCatalogs.class);
    public static void setTeams(final Combobox cbo, final List<Team> teams)
    {
            processTeams(cbo, teams);
    }

    public static void setMachineModelTypes(final Combobox cbo, final List<MachineModelType> types)
    {
            processMachineModelTypes(cbo, types);
    }

    public static void setMachineModelTypes(final Combobox cbo, final List<MachineModelType> types, final Long typeId)
    {
            processMachineModelTypes(cbo, types);
            if (null != typeId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            MachineModelType cat = (MachineModelType) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), typeId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    public static void setMachines(final Combobox cbo, final List<Machine> models)
    {
            processMachines(cbo, models);
    }

    public static void setMachines(final Combobox cbo, final List<Machine> models, final Long modelId)
    {
            processMachines(cbo, models);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            MachineModel cat = (MachineModel) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    private static void processMachines(final Combobox cbo,
			final List<Machine> models)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (Machine model : models)
            {
                    Comboitem item = new Comboitem();
                    item.setLabel(model.getName());
                    LOG.info("Model name = "+model.getName());
                    if(model.getId() != null)
                    {
                        item.setValue(model);
                    }
                    cbo.appendChild(item);
            }
        }
    }


    public static void setNgReasons(final Combobox cbo, final List<NgReason> models)
    {
            processNgReasons(cbo, models);
    }

    public static void setNgReasons(final Combobox cbo, final List<NgReason> models, final Long modelId)
    {
            processNgReasons(cbo, models);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            NgReason cat = (NgReason) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    private static void processNgReasons(final Combobox cbo,
			final List<NgReason> models)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (NgReason model : models)
            {
                    Comboitem item = new Comboitem();
                    item.setLabel(model.getName());
                    LOG.info("Model name = "+model.getName());
                    if(model.getId() != null)
                    {
                        item.setValue(model);
                    }
                    cbo.appendChild(item);
            }
        }
    }


    public static void setMachineModels(final Combobox cbo, final List<MachineModel> models)
    {
            processMachineModels(cbo, models);
    }

    public static void setMachineModels(final Combobox cbo, final List<MachineModel> models, final Long modelId)
    {
            processMachineModels(cbo, models);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            MachineModel cat = (MachineModel) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    private static void processMachineModels(final Combobox cbo,
			final List<MachineModel> models)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (MachineModel model : models)
            {
                    Comboitem item = new Comboitem();
                    item.setLabel(model.getName());
                    if(model.getId() != null)
                    {
                        item.setValue(model);
                    }
                    cbo.appendChild(item);
            }
        }
    }

    public static void setProcesses(final Combobox cbo, final List<Process> models)
    {
            processProcesses(cbo, models);
    }

    public static void setProcesses(final Combobox cbo, final List<Process> models, final Long modelId)
    {
            processProcesses(cbo, models);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            Process cat = (Process) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    

    private static void processProcesses(final Combobox cbo,
			final List<Process> models)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (Process process : models)
            {
                    Comboitem item = new Comboitem();
                    item.setLabel(process.getProcessName());
                    if(process.getId() != null)
                    {
                        item.setValue(process);
                    }
                    cbo.appendChild(item);
            }
        }
    }

    public static void setCheckpointTypes(final Combobox cbo, final List<CheckpointType> models)
    {
            processCheckpointTypes(cbo, models);
    }

    public static void setCheckpointTypes(final Combobox cbo, final List<CheckpointType> models, final Long modelId)
    {
            processCheckpointTypes(cbo, models);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            CheckpointType cat = (CheckpointType) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    private static void processCheckpointTypes(final Combobox cbo,
			final List<CheckpointType> models)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (CheckpointType checkpointType : models)
            {
                    Comboitem item = new Comboitem();
                    item.setLabel(checkpointType.getName());
                    if(checkpointType.getId() != null)
                    {
                        item.setValue(checkpointType);
                    }
                    cbo.appendChild(item);
            }
        }
    }

    public static void setCheckpointUnits(final Combobox cbo, final List<CheckpointUnit> models)
    {
            processCheckpointUnits(cbo, models);
    }

    public static void setCheckpointUnits(final Combobox cbo, final List<CheckpointUnit> models, final Long modelId)
    {
            processCheckpointUnits(cbo, models);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            CheckpointUnit cat = (CheckpointUnit) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    private static void processCheckpointUnits(final Combobox cbo,
			final List<CheckpointUnit> models)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (CheckpointUnit checkpointUnit : models)
            {
                    Comboitem item = new Comboitem();
                    item.setLabel(checkpointUnit.getName());
                    if(checkpointUnit.getId() != null)
                    {
                        item.setValue(checkpointUnit);
                    }
                    cbo.appendChild(item);
            }
        }
    }


    public static void setCheckpoints(final Combobox cbo, final List<Checkpoint> models)
    {
            processCheckpoints(cbo, models);
    }

    public static void setCheckpoints(final Combobox cbo, final List<Checkpoint> models, final Long modelId)
    {
            processCheckpoints(cbo, models);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            Checkpoint cat = (Checkpoint) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    private static void processCheckpoints(final Combobox cbo,
			final List<Checkpoint> models)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (Checkpoint checkpoint : models)
            {
                    Comboitem item = new Comboitem();
                    item.setLabel("Position #"+String.valueOf(checkpoint.getPosition()));
                    if(checkpoint.getId() != null)
                    {
                        item.setValue(checkpoint);
                    }
                    cbo.appendChild(item);
            }
        }
    }

    public static void setPositions(final Combobox cbo, final List<MeasurementPosition> models)
    {
            processPositions(cbo, models);
    }

    public static void setPositions(final Combobox cbo, final List<MeasurementPosition> models, final Long modelId)
    {
            processPositions(cbo, models);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            MeasurementPosition cat = (MeasurementPosition) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    private static void processPositions(final Combobox cbo,
			final List<MeasurementPosition> models)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (MeasurementPosition position : models)
            {
                    Comboitem item = new Comboitem();
                    item.setLabel(position.getPositionName());
                    if(position.getId() != null)
                    {
                        item.setValue(position);
                    }
                    cbo.appendChild(item);
            }
        }
    }


    public static void setTimes(final Combobox cbo, final List<MeasurementTime> models)
    {
            processTimes(cbo, models);
    }

    public static void setTimes(final Combobox cbo, final List<MeasurementTime> models, final Long modelId)
    {
            processTimes(cbo, models);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            MeasurementPosition cat = (MeasurementPosition) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    private static void processTimes(final Combobox cbo,
			final List<MeasurementTime> models)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (MeasurementTime time : models)
            {
                    Comboitem item = new Comboitem();
                    item.setLabel(String.valueOf(time.getTimeName()));
                    if(time.getId() != null)
                    {
                        item.setValue(time);
                    }
                    cbo.appendChild(item);
            }
        }
    }
    
    public static void setParts(final Combobox cbo, final List<Part> models)
    {
            processParts(cbo, models);
    }

    public static void setParts(final Combobox cbo, final List<Part> models, final Long modelId)
    {
            processParts(cbo, models);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            Part cat = (Part) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    public static void setParts(final Combobox cbo, final List<Part> models,final String showType, final Long modelId)
    {
            processParts(cbo, models,showType);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            Part cat = (Part) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    private static void processParts(final Combobox cbo,
			final List<Part> models)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (Part process : models)
            {
                    Comboitem item = new Comboitem();
                    item.setLabel(process.getPartNo());
                    if(process.getId() != null)
                    {
                        item.setValue(process);
                    }
                    cbo.appendChild(item);
            }
        }
    }

    private static void processParts(final Combobox cbo,
			final List<Part> models,final String showType)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (Part process : models)
            {
                    Comboitem item = new Comboitem();
                    if(showType!= null && showType.equals("partName-partNo"))
                    {
                        item.setLabel(process.getPartName()+"-"+process.getPartNo());
                    }
                    else
                    {
                        item.setLabel(process.getPartNo());
                    }
                    if(process.getId() != null)
                    {
                        item.setValue(process);
                    }
                    cbo.appendChild(item);
            }
        }
    }

    public static void setUsers(final Combobox cbo, final List<User> models)
    {
            processUsers(cbo, models);
    }

    public static void setUsers(final Combobox cbo, final List<User> models, final Long modelId)
    {
            processUsers(cbo, models);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            Process cat = (Process) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    private static void processUsers(final Combobox cbo,
			final List<User> models)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (User user : models)
            {
                    Comboitem item = new Comboitem();
                    item.setLabel(user.getFirstname()+" "+user.getLastname());
                    if(user.getId() != null)
                    {
                        item.setValue(user);
                    }
                    cbo.appendChild(item);
            }
        }
    }





    public static void setMeasurementModelTypes(final Combobox cbo, final List<MeasurementToolType> types)
    {
            processMeasurementToolTypes(cbo, types);
    }

    public static void setMeasurementModelTypes(final Combobox cbo, final List<MeasurementToolType> types, final Long typeId)
    {
            processMeasurementToolTypes(cbo, types);
            if (null != typeId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            MeasurementToolType cat = (MeasurementToolType) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), typeId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    private static void processMeasurementToolTypes(final Combobox cbo,
			final List<MeasurementToolType> types)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (MeasurementToolType type : types)
            {
                    Comboitem item = new Comboitem();
                    item.setLabel(type.getName());
                    if(type.getId() != null)
                    {
                        item.setValue(type);
                    }
                    cbo.appendChild(item);
            }
        }
    }

    public static void setMeasurementToolModels(final Combobox cbo, final List<MeasurementToolModel> models)
    {
            processMeasurementToolModels(cbo, models);
    }

    public static void setMeasurementToolModels(final Combobox cbo, final List<MeasurementToolModel> models, final Long modelId)
    {
            processMeasurementToolModels(cbo, models);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            MeasurementToolModel cat = (MeasurementToolModel) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    private static void processMeasurementToolModels(final Combobox cbo,
			final List<MeasurementToolModel> models)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (MeasurementToolModel model : models)
            {
                    Comboitem item = new Comboitem();
                    item.setLabel(model.getName());
                    if(model.getId() != null)
                    {
                        item.setValue(model);
                    }
                    cbo.appendChild(item);
            }
        }
    }

    public static void setMeasurementTools(final Combobox cbo, final List<MeasurementTool> models)
    {
            processMeasurementTools(cbo, models,null);
    }

    public static void setMeasurementTools(final Combobox cbo, final List<MeasurementTool> models,String showType, final Long modelId)
    {
            processMeasurementTools(cbo, models,showType);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            MeasurementTool cat = (MeasurementTool) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    public static void setMeasurementTools(final Combobox cbo, final List<MeasurementTool> models, final Long modelId)
    {
            setMeasurementTools(cbo, models,null,modelId);
    }

    private static void processMeasurementTools(final Combobox cbo,
			final List<MeasurementTool> models,String showType)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (MeasurementTool model : models)
            {
                    Comboitem item = new Comboitem();
                    LOG.info("ShowType = "+showType);
                    if(showType != null && showType.equals("codeNo"))
                    {
                        item.setLabel(model.getCodeNo());
                    }
                    else
                    {
                        item.setLabel(model.getSerialNo());
                    }
                    
                    if(model.getId() != null)
                    {
                        item.setValue(model);
                    }
                    cbo.appendChild(item);
            }
        }
    }

    public static void setSemiMaterials(final Combobox cbo, final List<SemiMaterial> models)
    {
            processSemiMaterials(cbo, models);
    }

    public static void setSemiMaterials(final Combobox cbo, final List<SemiMaterial> models, final Long modelId)
    {
            processSemiMaterials(cbo, models);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            SemiMaterial cat = (SemiMaterial) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    public static void setSemiMaterials(final Combobox cbo, final List<SemiMaterial> models,final String showType, final Long modelId)
    {
            processSemiMaterials(cbo, models,showType);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            SemiMaterial cat = (SemiMaterial) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }



    private static void processSemiMaterials(final Combobox cbo,
			final List<SemiMaterial> models,final String showType)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (SemiMaterial process : models)
            {
                    Comboitem item = new Comboitem();
                    if(showType!= null && showType.equals("semiName-semiType"))
                    {
                        item.setLabel(process.getSemiName()+"-"+process.getSemiType().getName());
                    }
                    else
                    {
                        item.setLabel(process.getSemiName());
                    }
                    if(process.getId() != null)
                    {
                        item.setValue(process);
                    }
                    cbo.appendChild(item);
            }
        }
    }

    private static void processSemiMaterials(final Combobox cbo,
			final List<SemiMaterial> models)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (SemiMaterial model : models)
            {
                    Comboitem item = new Comboitem();
                    item.setLabel(model.getSemiName());
                    if(model.getId() != null)
                    {
                        item.setValue(model);
                    }
                    cbo.appendChild(item);
            }
        }
    }

    public static void setCustomers(final Combobox cbo, final List<Customer> models)
    {
            processCustomers(cbo, models);
    }

    public static void setCustomers(final Combobox cbo, final List<Customer> models, final Long modelId)
    {
            processCustomers(cbo, models);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            Customer cat = (Customer) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    private static void processCustomers(final Combobox cbo,
			final List<Customer> models)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (Customer model : models)
            {
                    Comboitem item = new Comboitem();
                    item.setLabel(model.getName());
                    if(model.getId() != null)
                    {
                        item.setValue(model);
                    }
                    cbo.appendChild(item);
            }
        }
    }

    public static void setTransferTypes(final Combobox cbo, final List<TransferType> models)
    {
            processTransferTypes(cbo, models);
    }

    public static void setTransferTypes(final Combobox cbo, final List<TransferType> models, final Long modelId)
    {
            processTransferTypes(cbo, models);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            Customer cat = (Customer) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    private static void processTransferTypes(final Combobox cbo,
			final List<TransferType> models)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (TransferType model : models)
            {
                    Comboitem item = new Comboitem();
                    item.setLabel(model.getTransferTypeName());
                    if(model.getId() != null)
                    {
                        item.setValue(model);
                    }
                    cbo.appendChild(item);
            }
        }
    }


    public static void setRawMaterials(final Combobox cbo, final List<RawMaterial> models)
    {
            processRawMaterials(cbo, models);
    }

    public static void setRawMaterials(final Combobox cbo, final List<RawMaterial> models, final Long modelId)
    {
            processRawMaterials(cbo, models);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            RawType cat = (RawType) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }
    
     public static void setRawMaterials(final Combobox cbo, final List<RawMaterial> models,final String showType, final Long modelId)
    {
            processRawMaterials(cbo, models,showType);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            RawMaterial cat = (RawMaterial) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    

    private static void processRawMaterials(final Combobox cbo,
			final List<RawMaterial> models,final String showType)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (RawMaterial process : models)
            {
                    Comboitem item = new Comboitem();
                    if(showType!= null && showType.equals("rawName-rawType"))
                    {
                        item.setLabel(process.getRawName()+"-"+process.getRawType().getName());
                    }
                    else
                    {
                        item.setLabel(process.getRawName());
                    }
                    if(process.getId() != null)
                    {
                        item.setValue(process);
                    }
                    cbo.appendChild(item);
            }
        }
    }



    private static void processRawMaterials(final Combobox cbo,
			final List<RawMaterial> models)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (RawMaterial model : models)
            {
                    Comboitem item = new Comboitem();
                    item.setLabel(model.getRawName());
                    if(model.getId() != null)
                    {
                        item.setValue(model);
                    }
                    cbo.appendChild(item);
            }
        }
    }




    public static void setSemiTypes(final Combobox cbo, final List<SemiType> models)
    {
            processSemiTypes(cbo, models);
    }

    public static void setSemiTypes(final Combobox cbo, final List<SemiType> models, final Long modelId)
    {
            processSemiTypes(cbo, models);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            SemiType cat = (SemiType) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    private static void processSemiTypes(final Combobox cbo,
			final List<SemiType> models)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (SemiType model : models)
            {
                    Comboitem item = new Comboitem();
                    item.setLabel(model.getName());
                    if(model.getId() != null)
                    {
                        item.setValue(model);
                    }
                    cbo.appendChild(item);
            }
        }
    }


    public static void setRawTypes(final Combobox cbo, final List<RawType> models)
    {
            processRawTypes(cbo, models);
    }

    public static void setRawTypes(final Combobox cbo, final List<RawType> models, final Long modelId)
    {
            processRawTypes(cbo, models);
            if (null != modelId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            RawType cat = (RawType) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), modelId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    private static void processRawTypes(final Combobox cbo,
			final List<RawType> models)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (RawType model : models)
            {
                    Comboitem item = new Comboitem();
                    item.setLabel(model.getName());
                    if(model.getId() != null)
                    {
                        item.setValue(model);
                    }
                    cbo.appendChild(item);
            }
        }
    }

    public static void setTeams(final Combobox cbo, final List<Team> teams, final Long teamId)
    {
            processTeams(cbo, teams);
            if (null != teamId)
            {
                    List currencyCbos = cbo.getItems();
                    for (Object obj : currencyCbos)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            Team cat = (Team) cbi.getValue();

                            if (ObjectUtils.equals(cat.getId(), teamId))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    public static void setTeams(final Combobox cbo, final List<Team> teams, final int selectedIndex)
    {
            processTeams(cbo, teams);
            if (0 <= selectedIndex)
            {
                    cbo.setSelectedIndex(selectedIndex);
            }
    }

    private static void processTeams(final Combobox cbo,
			final List<Team> teams)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (Team team : teams)
            {
                    Comboitem item = new Comboitem();
                    item.setLabel(team.getTeamName());
                    if(team.getId() != null)
                    {
                        item.setValue(team);
                    }
                    cbo.appendChild(item);
            }
        }
    }

    private static void processMachineModelTypes(final Combobox cbo,
			final List<MachineModelType> types)
    {
        if(cbo.getItems() == null || cbo.getItems().isEmpty())
        {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (MachineModelType type : types)
            {
                    Comboitem item = new Comboitem();
                    item.setLabel(type.getName());
                    if(type.getId() != null)
                    {
                        item.setValue(type);
                    }
                    cbo.appendChild(item);
            }
        }
    }

    public static void setSecurityGroups(final Combobox cbo,final Long groupId,final Long exceptionGroupId,Boolean parentDisabled) throws Exception
    {
        clear(cbo);
        cbo.setReadonly(true);
        cbo.setRows(1);        
        if(parentDisabled != null && parentDisabled)
        {
            processDisabledGroups(cbo, groupId, exceptionGroupId, 0);
            
        }
        else
        {
           processGroups(cbo,groupId,exceptionGroupId,0);
        }
    }

    public static void setGroups(final Combobox cbo, final List groups, final int selectedIndex)
    {
            processGroups(cbo, groups);
            if (0 <= selectedIndex)
            {
                    cbo.setSelectedIndex(selectedIndex);
            }
    }

    public static void setSelectedItemGroup(final Combobox cbo,
			final Group group)
    {
            if (null != cbo && null != group)
            {
                    List items = cbo.getItems();
                    for (Object obj : items)
                    {
                            Comboitem cbi = (Comboitem) obj;
                            Group data = (Group) cbi.getValue();

                            if (ObjectUtils.equals(data.getId(), group.getId()))
                            {
                                    cbo.setSelectedItem(cbi);
                                    return;
                            }
                    }
            }
    }

    private static void processGroups(final Combobox cbo,Long groupId,Long exceptionGroupId,int level) throws Exception
    {
        Group mainGroup = GroupCore.getGroupWithPermission(groupId);
        if(mainGroup != null && (mainGroup.getDisabled() == null || !mainGroup.getDisabled()))
        {
            Comboitem item = new Comboitem();
            StringBuffer buffer =new StringBuffer(20);
            for(int index = 0 ;index<level;index++)
            {
                buffer.append("  ");
            }            
            item.setLabel(buffer.toString()+mainGroup.getGroupName());
            item.setValue(mainGroup);
            cbo.appendChild(item);
            List<Group> groups = GroupCore.getChildrenWithPermission(groupId);
            if(groups != null && groups.size() > 0)
            {
                for (Group group : groups)
                {   
                    if(!group.getId().equals(exceptionGroupId))
                    {
                        processGroups(cbo, group.getId(),exceptionGroupId,level+1);
                    }
                }
            }
        }
    }

    private static void processDisabledGroups(final Combobox cbo,Long groupId,Long exceptionGroupId,int level) throws Exception
    {
        Group mainGroup = GroupCore.getGroupWithPermission(groupId);
        if(mainGroup != null)
        {
            Comboitem item = new Comboitem();
            StringBuffer buffer =new StringBuffer(20);
            for(int index = 0 ;index<level;index++)
            {
                buffer.append("  ");
            }            
            item.setLabel(buffer.toString()+mainGroup.getGroupName());
            item.setValue(mainGroup);
            cbo.appendChild(item);
            List<Group> groups = GroupCore.getChildrenWithPermission(groupId);
            if(groups != null && groups.size() > 0)
            {
                for (Group group : groups)
                {
                    if(!group.getId().equals(exceptionGroupId))
                    {
                        processDisabledGroups(cbo, group.getId(),exceptionGroupId,level+1);
                    }
                }
            }
        }
    }

    public static void cloneComboboxChilds(Combobox src,Combobox dest)
    {
        List<Comboitem> items = (List<Comboitem>)src.getChildren();
        for(Comboitem item : items)
        {
            dest.appendChild(item);            
        }
    }

    private static void processGroups(final Combobox cbo, final List groups)
    {
            clear(cbo);
            cbo.setReadonly(true);
            cbo.setRows(1);

            for (Object obj : groups)
            {
                    Group group = (Group) obj;
                    Comboitem item = new Comboitem();
                    item.setLabel(group.getGroupName());
                    item.setValue(group);
                    cbo.appendChild(item);
            }
    }

    private static void clear(Combobox cbo)
    {
            if (null != cbo.getItems())
            {
                    cbo.getItems().clear();
            }
    }

}
