package cz.siemens.inventory.rest.controllers;

import cz.siemens.inventory.dao.GenericDao;
import cz.siemens.inventory.dao.InventoryRecordDaoImpl;
import cz.siemens.inventory.entity.InventoryRecord;
import cz.siemens.inventory.rest.ApiUris;
import cz.siemens.inventory.rest.exceptions.ResourceAlreadyExistsException;
import cz.siemens.inventory.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUris.ROOT_URI_INVENTORY_RECORD)
public class InventoryRecordController {

    private InventoryRecordDaoImpl inventoryRecordDao;
    final static Logger logger = LoggerFactory.getLogger(SupplierController.class);

    @Autowired
    public InventoryRecordController(InventoryRecordDaoImpl inventoryRecordDao) {
        this.inventoryRecordDao = inventoryRecordDao;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<InventoryRecord> findAll(){
        logger.info("findAll() called");
        return inventoryRecordDao.readAll();
    }

    @RequestMapping(value="/checked", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<InventoryRecord> findAllChecked(){
        logger.info("findAllChecked() called");
        return inventoryRecordDao.findAllChecked();
    }

    @RequestMapping(value="/unchecked", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<InventoryRecord> findAllUnChecked(){
        logger.info("findAllUnChecked() called");
        return inventoryRecordDao.findAllUnChecked();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final InventoryRecord findById(@PathVariable("id") Long id) throws Exception {
        logger.info("findById({id}) called", id);
        try {
            return inventoryRecordDao.read(id);
        } catch(Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value="/{id}/{checked}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void updateChecked(@PathVariable("id") Long id, @PathVariable("checked") Boolean checked) throws Exception {
        logger.info("updateChecked({id}) called", id);
        try {
            InventoryRecord inventoryRecord = findById(id);
            inventoryRecord.setRegistered(checked);
            inventoryRecordDao.update(inventoryRecord);
        } catch(Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/create", method= RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void create(@RequestBody InventoryRecord inventoryRecord) throws Exception {
        logger.info("create({inventoryRecord}) called", inventoryRecord.toString());
        try {
            inventoryRecordDao.create(inventoryRecord);
        } catch(Exception ex) {
            throw new ResourceAlreadyExistsException();
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void remove(@PathVariable("id") Long id) throws Exception {
        logger.info("remove({id}) called", id);
        try {
            inventoryRecordDao.delete(inventoryRecordDao.read(id));
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
}
