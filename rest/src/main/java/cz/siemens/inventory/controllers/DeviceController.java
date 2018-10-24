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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiUris.ROOT_URI)
public class DeviceController extends BaseController implements DevicesApi {

	final static Logger logger = LoggerFactory.getLogger(DeviceController.class);
	private DeviceFacade deviceFacade;

	@Autowired
	public DeviceController(DeviceFacade deviceFacade) {
		this.deviceFacade = deviceFacade;
	}

	@Override
	public final ResponseEntity<List<Device>> getDevices() {
		logger.info("getDevices request received");

		List<Device> devices = deviceFacade.getDevices();

		logger.info("getDevices request finished");
		return ResponseEntity.ok(devices);
	}

	@Override
	public ResponseEntity<Device> getDevice(@ApiParam(required = true) @PathVariable("deviceId") Long deviceId) {
		logger.info("getDevice({}) request received", deviceId);

		Optional<Device> device = deviceFacade.getDevice(deviceId);

		logger.info("getDevice({}) request finished", deviceId);
		return returnOptional(device);
	}

	@Override
	public ResponseEntity<Device> createDevice(@ApiParam(required = true) @Valid @RequestBody Device body) {
		logger.info("createDevice({}) request received", body);

		Device device = deviceFacade.createDevice(body);

		logger.info("createDevice({}) request finished", device.getId());

		return returnCreatedResponse(device, device.getId().toString());
	}

	@Override
	public ResponseEntity<Void> deleteDevice(@PathVariable("deviceId") Long deviceId) {
		logger.info("deleteDevice({}) request received", deviceId);

		deviceFacade.deleteDevice(deviceId);

		logger.info("deleteDevice({}) request finished", deviceId);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Device> getDeviceWithBarcodeNumber(@ApiParam(required = true) @PathVariable("barcodeNumber") String barcodeNumber) {
		logger.info("getDeviceWithBarcodeNumber({}) request received", barcodeNumber);

		Optional<Device> deviceWithBarcode = deviceFacade.getDeviceByBarcode(barcodeNumber);

		logger.info("getDeviceWithBarcodeNumber({}) request finished", barcodeNumber);

		return returnOptional(deviceWithBarcode);
	}

	@Override
	public ResponseEntity<Device> getDeviceWithSerialNumber(@ApiParam(required = true) @PathVariable("serialNumber") String serialNumber) {
		logger.info("getDeviceWithSerialNumber({}) request received", serialNumber);

		Optional<Device> deviceWithSerialNumber = deviceFacade.getDeviceBySerialNumber(serialNumber);

		logger.info("getDeviceWithSerialNumber({}) request finished", serialNumber);

		return returnOptional(deviceWithSerialNumber);
	}

	@Override
	public ResponseEntity<List<Device>> getDevicesWithSerialNumberLike(@ApiParam(required = true) @PathVariable("serialNumber") String serialNumber) {
		logger.info("getDevicesWithSerialNumberLike({}) request received", serialNumber);

		List<Device> devicesWithSerialNumberLike = deviceFacade.getDevicesBySerialNumberLike(serialNumber);

		logger.info("getDevicesWithSerialNumberLike({}) request finished", serialNumber);

		return ResponseEntity.ok(devicesWithSerialNumberLike);
	}

	@Override
	public ResponseEntity<List<Device>> getDevicesWithSerialOrBarcodeNumberLike(
			@ApiParam(required=true) @PathVariable("serialBarcodeNumber") String serialBarcodeNumber) {
		logger.info("getDevicesWithSerialOrBarcodeNumberLike({}) request received", serialBarcodeNumber);

		List<Device> devicesWithSerialOrBarcodeNumberLike = deviceFacade.getDevicesBySerialOrBarcodeNumberLike(serialBarcodeNumber);

		logger.info("getDevicesWithSerialOrBarcodeNumberLike({}) request finished", serialBarcodeNumber);

		return ResponseEntity.ok(devicesWithSerialOrBarcodeNumberLike);
	}

	@Override
	public ResponseEntity<List<Device>> getDevicesBorrowedBy(@ApiParam(required = true) @PathVariable("userScdId") Long userScdId) {
		logger.info("getDevicesBorrowedBy({}) request received", userScdId);

		List<Device> devicesBorrowedByUser = deviceFacade.getDevicesBorrowedByUser(userScdId);

		logger.info("getDevicesBorrowedBy({}) request finished", userScdId);

		return ResponseEntity.ok(devicesBorrowedByUser);
	}

	@Override
	public ResponseEntity<Device> updateDevice(@ApiParam(required = true) @PathVariable("deviceId") Long deviceId,
											   @ApiParam(required = true) @Valid @RequestBody Device body) {
		logger.info("updateDevice({}, {}) request received", deviceId, body);

		Device device = deviceFacade.updateDevice(body);

		logger.info("updateDevice({}, {}) request finished", deviceId, device);

		return ResponseEntity.ok(device);
	}
}
