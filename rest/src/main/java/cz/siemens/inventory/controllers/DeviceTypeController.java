package cz.siemens.inventory.controllers;

import cz.siemens.inventory.api.facade.DeviceTypeFacade;
import cz.siemens.inventory.api.gen.DeviceTypesApi;
import cz.siemens.inventory.api.gen.model.DeviceType;
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
public class DeviceTypeController extends BaseController implements DeviceTypesApi {

	final static Logger logger = LoggerFactory.getLogger(DeviceTypeController.class);
	private DeviceTypeFacade deviceTypeFacade;

	@Autowired
	public DeviceTypeController(DeviceTypeFacade deviceTypeFacade) {
		this.deviceTypeFacade = deviceTypeFacade;
	}

	@Override
	public ResponseEntity<List<DeviceType>> getDeviceTypes() {
		logger.info("getDeviceTypes request received");

		List<DeviceType> deviceTypes = deviceTypeFacade.getDeviceTypes();

		logger.info("getDeviceTypes request finished");

		return ResponseEntity.ok(deviceTypes);
	}

	@Override
	public ResponseEntity<DeviceType> getDeviceType(@PathVariable("deviceTypeId") Long deviceTypeId) {
		logger.info("getDeviceType({}) request received", deviceTypeId);

		Optional<DeviceType> deviceType = deviceTypeFacade.getDeviceType(deviceTypeId);

		logger.info("getDeviceType({}) request finished", deviceTypeId);

		return returnOptional(deviceType);
	}

	@Override
	public ResponseEntity<List<DeviceType>> getDeviceTypesByName(@ApiParam(required = true) @PathVariable("deviceTypeName") String deviceTypeName) {
		logger.info("getDeviceTypesByName({}) request received", deviceTypeName);

		List<DeviceType> deviceTypesByName = deviceTypeFacade.getDeviceTypesByName(deviceTypeName);

		logger.info("getDeviceTypesByName({}) request finished", deviceTypeName);

		return ResponseEntity.ok(deviceTypesByName);
	}

	@Override
	public ResponseEntity<DeviceType> createDeviceType(@ApiParam(required = true) @Valid @RequestBody DeviceType body) {
		logger.info("createDeviceType({}) request received", body.toString());

		DeviceType deviceType = deviceTypeFacade.createDeviceType(body);

		logger.info("createDeviceType({}) request finished", deviceType.getId());

		return returnCreatedResponse(deviceType, deviceType.getId().toString());
	}

	@Override
	public ResponseEntity<DeviceType> updateDeviceType(@ApiParam(required = true) @PathVariable("deviceTypeId") Long deviceTypeId,
													   @ApiParam(required = true) @Valid @RequestBody DeviceType body) {
		logger.info("updateDeviceType({}, {}) request received", deviceTypeId, body.toString());

		DeviceType deviceType = deviceTypeFacade.updateDeviceType(body);

		logger.info("updateDeviceType({}, {}) request finished", deviceTypeId, deviceType.getId());

		return ResponseEntity.ok(deviceType);
	}

	@Override
	public ResponseEntity<Void> deleteDeviceType(@PathVariable("deviceTypeId") Long deviceTypeId) {
		logger.info("deleteDeviceType({}) request received", deviceTypeId);

		deviceTypeFacade.deleteDeviceType(deviceTypeId);

		logger.info("deleteDeviceType({}) request finished", deviceTypeId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}