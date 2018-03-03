package cz.siemens.inventory.rest.controllers;

import cz.siemens.inventory.dao.GenericDao;
import cz.siemens.inventory.entity.Device;
import cz.siemens.inventory.rest.ApiUris;
import cz.siemens.inventory.rest.exceptions.ResourceAlreadyExistsException;
import cz.siemens.inventory.rest.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUris.ROOT_URI_DEVICE)
public class DeviceController {

    //final static Logger logger = LoggerFactory.getLogger(SupplierController.class);

    @Autowired
    private GenericDao<Device> deviceDao;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<Device> findAll(){
        return deviceDao.readAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Device findById(@PathVariable("id") Long id) throws Exception {
        try {
            return deviceDao.read(id);
        } catch(Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/create", method= RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void create(@RequestBody Device device) throws Exception {
        try {
            deviceDao.create(device);
        } catch(Exception ex) {
            throw new ResourceAlreadyExistsException();
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void remove(@PathVariable("id") Long id) throws Exception {
        try {
            deviceDao.delete(deviceDao.read(id));
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
}
