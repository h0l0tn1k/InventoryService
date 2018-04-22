package cz.siemens.inventory.rest.controllers;

import cz.siemens.inventory.dao.DeviceDaoImpl;
import cz.siemens.inventory.dao.UserScdDaoImpl;
import cz.siemens.inventory.entity.Device;
import cz.siemens.inventory.entity.LoginUserScd;
import cz.siemens.inventory.entity.UserScd;
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
    private UserScdDaoImpl userScdDao;
    final static Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    public DeviceController(DeviceDaoImpl deviceDao, UserScdDaoImpl userScdDao) {
        this.deviceDao = deviceDao;
        this.userScdDao = userScdDao;
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

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public final void remove(@PathVariable("id") Long id) throws Exception {
        logger.info("remove({id}) called", id);
        try {
            deviceDao.delete(deviceDao.read(id));
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value="/{deviceId}/holder/{holderScdId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Device updateHolderId(@PathVariable("deviceId") Long deviceId, @PathVariable("holderScdId") Long holderScdId) throws Exception {
        logger.info("updateHolderId({deviceId},{holderScdId}) called", deviceId, holderScdId);
        try {
            Device dev = deviceDao.read(deviceId);
            if(holderScdId <= 0) {
                dev.setHolder(null);
            } else {
                UserScd UserScd = userScdDao.getByScdId(holderScdId);
                dev.setHolder(UserScd);
            }
            deviceDao.update(dev);
            return dev;
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value="/borrowed-by/{scdId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<Device> getBorrowedDevicesByScdId(@PathVariable("scdId") Long scdId) {
        logger.info("getBorrowedDevicesByScdId({scdId})", scdId);
        return deviceDao.findDevicesByHolder(scdId);
    }
}
