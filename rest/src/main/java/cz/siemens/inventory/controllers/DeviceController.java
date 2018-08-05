package cz.siemens.inventory.controllers;

import cz.siemens.inventory.facade.DeviceFacade;
import cz.siemens.inventory.gen.api.DevicesApi;
import cz.siemens.inventory.gen.model.Device;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(ApiUris.ROOT_URI)
public class DeviceController extends BaseController implements DevicesApi {

	private DeviceFacade deviceFacade;
	final static Logger logger = LoggerFactory.getLogger(DeviceController.class);

	@Autowired
	public DeviceController(DeviceFacade deviceFacade) {
		this.deviceFacade = deviceFacade;
	}

	@Override
	public final ResponseEntity<List<Device>> getDevices() {
		logger.info("getDevices() called");
		return ResponseEntity.ok(deviceFacade.getDevices());
	}

	@Override
	public ResponseEntity<Device> getDevice(@ApiParam(required = true) @PathVariable("deviceId") Long deviceId) {
		logger.info("getDevice({deviceId}) called", deviceId);
		return returnOptional(deviceFacade.getDevice(deviceId));
	}

	@Override
	public ResponseEntity<Device> createDevice(@ApiParam(required = true) @Valid @RequestBody Device body) {
		logger.info("createDevice({device}) called", body.toString());
		return ResponseEntity.ok(body);
	}

	@Override
	public ResponseEntity<Void> deleteDevice(@ApiParam(required = true) @PathVariable("deviceId") Long deviceId) {
		logger.info("deleteDevice({deviceId}) called", deviceId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Device> getDeviceWithBarcodeNumber(@ApiParam(required = true) @PathVariable("barcodeNumber") String barcodeNumber) {
		return ResponseEntity.ok(new Device());
	}

	@Override
	public ResponseEntity<Device> getDeviceWithSerialNumber(@ApiParam(required = true) @PathVariable("serialNumber") String serialNumber) {
		return ResponseEntity.ok(new Device());
	}

	@Override
	public ResponseEntity<List<Device>> getDevicesWithSerialNumberLike(@ApiParam(required = true) @PathVariable("serialNumber") String serialNumber) {
		return ResponseEntity.ok(new ArrayList<>());
	}

	@Override
	public ResponseEntity<List<Device>> getDevicesBorrowedBy(@ApiParam(required = true) @PathVariable("userScdId") Long userScdId) {
		return ResponseEntity.ok(new ArrayList<>());
	}

	@Override
	public ResponseEntity<Device> updateDeviceHolder(@ApiParam(required = true) @PathVariable("deviceId") Long deviceId,
													 @ApiParam(required = true) @PathVariable("holderScdId") Long holderScdId) {
		return ResponseEntity.ok(new Device());
	}
}
