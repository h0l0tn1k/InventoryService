package cz.siemens.inventory.rest.controllers;

import cz.siemens.inventory.dao.DeviceDaoImpl;
import cz.siemens.inventory.entity.Device;
import cz.siemens.inventory.rest.ApiUris;
import cz.siemens.inventory.rest.exceptions.ResourceAlreadyExistsException;
import cz.siemens.inventory.rest.exceptions.ResourceNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUris.ROOT_URI_DEVICE)
public class DeviceController {

    private DeviceDaoImpl deviceDao;
    final static Logger logger = LoggerFactory.getLogger(SupplierController.class);

    @Autowired
    public DeviceController(DeviceDaoImpl deviceDao) {
        this.deviceDao = deviceDao;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<Device> findAll(){
        logger.info("findAll() called");
        return deviceDao.readAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Device findById(@PathVariable("id") Long id) throws Exception {
        logger.info("findById({id}) called", id);
        try {
            return deviceDao.read(id);
        }catch(ObjectNotFoundException ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value="/barcode/{barcodeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Device findByBarcodeId(@PathVariable("barcodeId") String barcodeId) throws Exception {
        logger.info("findByBarcodeId({barcodeId}) called", barcodeId);
        try {
            return deviceDao.findDeviceByBarcodeId(barcodeId);
        }catch(ObjectNotFoundException ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value="/serialno/{serialno}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Device findByDeviceSerialNo(@PathVariable("serialno") String serialNo) throws Exception {
        logger.info("findByDeviceSerialNo({serialNo}) called", serialNo);

        try {
            return deviceDao.findDeviceBySerialNo(serialNo);
        }catch(ObjectNotFoundException ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value="/serialno/like/{serialno}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<Device> findDevicesBySerialNo(@PathVariable("serialno") String serialNo) throws Exception {
        logger.info("findDevicesBySerialNo({serialNo}) called", serialNo);
        try {
            return deviceDao.findDevicesBySerialNo(serialNo);
        } catch(Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/create", method= RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void create(@RequestBody Device device) throws Exception {
        logger.info("create({device}) called", device.toString());
        try {
            deviceDao.create(device);
        } catch(Exception ex) {
            throw new ResourceAlreadyExistsException();
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void remove(@PathVariable("id") Long id) throws Exception {
        logger.info("remove({id}) called", id);
        try {
            deviceDao.delete(deviceDao.read(id));
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
}
