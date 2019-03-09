package cz.siemens.inventory.controllers;

import cz.siemens.inventory.api.facade.DeviceStateFacade;
import cz.siemens.inventory.api.gen.DeviceStatesApi;
import cz.siemens.inventory.api.gen.model.DeviceState;
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
public class DeviceStateController extends BaseController implements DeviceStatesApi {

	private DeviceStateFacade deviceStateFacade;
	final static Logger logger = LoggerFactory.getLogger(DeviceStateController.class);

	@Autowired
	public DeviceStateController(DeviceStateFacade deviceStateFacade) {
		this.deviceStateFacade = deviceStateFacade;
	}

	@Override
	public ResponseEntity<List<DeviceState>> getDeviceStates() {
		logger.info("getDeviceStates request received");

		List<DeviceState> deviceStates = deviceStateFacade.getDeviceStates();

		logger.info("getDeviceStates request finished");

		return ResponseEntity.ok(deviceStates);
	}

	@Override
	public ResponseEntity<DeviceState> getDeviceState(@PathVariable("deviceStateId") Long deviceStateId) {
		logger.info("getDeviceState({}) request received", deviceStateId);

		Optional<DeviceState> deviceState = deviceStateFacade.getDeviceState(deviceStateId);

		logger.info("getDeviceState({}) request finished", deviceStateId);

		return returnOptional(deviceState);
	}

	@Override
	public ResponseEntity<DeviceState> createDeviceState(@ApiParam(required = true) @Valid @RequestBody DeviceState body) {
		logger.info("createDeviceState({}) request received", body.toString());

		DeviceState deviceState = deviceStateFacade.createDeviceState(body);

		logger.info("createDeviceState({}) request finished", deviceState.getId());

		return returnCreatedResponse(deviceState, deviceState.getId().toString());
	}

	@Override
	public ResponseEntity<Void> deleteDeviceState(@ApiParam(value = "DeviceInternal State's id",required=true) @PathVariable("deviceStateId") Long deviceStateId) {
		logger.info("deleteDeviceState({}) request received", deviceStateId);

		deviceStateFacade.deleteDeviceState(deviceStateId);

		logger.info("deleteDeviceState({}) request finished", deviceStateId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}