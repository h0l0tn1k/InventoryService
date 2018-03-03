package cz.siemens.inventory.rest.controllers;

import cz.siemens.inventory.dao.GenericDao;
import cz.siemens.inventory.entity.DeviceType;
import cz.siemens.inventory.rest.ApiUris;
import cz.siemens.inventory.rest.exceptions.ResourceAlreadyExistsException;
import cz.siemens.inventory.rest.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUris.ROOT_URI_DEVICE_TYPE)
public class DeviceTypeController {

    //final static Logger logger = LoggerFactory.getLogger(SupplierController.class);

    @Autowired
    private GenericDao<DeviceType> inventoryRecordDao;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<DeviceType> findAll(){
        return inventoryRecordDao.readAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final DeviceType findById(@PathVariable("id") Long id) throws Exception {
        try {
            return inventoryRecordDao.read(id);
        } catch(Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/create", method= RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void create(@RequestBody DeviceType deviceType) throws Exception {
        try {
            inventoryRecordDao.create(deviceType);
        } catch(Exception ex) {
            throw new ResourceAlreadyExistsException();
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void remove(@PathVariable("id") Long id) throws Exception {
        try {
            inventoryRecordDao.delete(inventoryRecordDao.read(id));
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
}
