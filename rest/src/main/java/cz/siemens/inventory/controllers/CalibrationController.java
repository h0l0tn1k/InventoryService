package cz.siemens.inventory.controllers;


import cz.siemens.inventory.facade.DeviceCalibrationFacade;
import cz.siemens.inventory.gen.api.CalibrationsApi;
import cz.siemens.inventory.gen.model.DeviceCalibration;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(ApiUris.ROOT_URI)
public class CalibrationController extends BaseController implements CalibrationsApi {

	final static Logger logger = LoggerFactory.getLogger(CalibrationController.class);
	private DeviceCalibrationFacade deviceCalibrationFacade;

	@Autowired
	public CalibrationController(DeviceCalibrationFacade deviceCalibrationFacade) {
		this.deviceCalibrationFacade = deviceCalibrationFacade;
	}

//	@Override
//	public ResponseEntity<DeviceCalibration> createCalibration(@ApiParam(required = true) @Valid @RequestBody DeviceCalibration body) {
//		logger.info("createCalibration({}) request received", body);
//
//		DeviceCalibration deviceCalibration = deviceCalibrationFacade.createDeviceCalibration(body);
//
//		logger.info("createCalibration({}) request finished", deviceCalibration);
//
//		return ResponseEntity.ok(deviceCalibration);
//	}

	@Override
	public ResponseEntity<DeviceCalibration> getCalibration(@ApiParam(required = true) @PathVariable("calibrationId") Long calibrationId) {
		logger.info("getCalibration({}) request received", calibrationId);

		Optional<DeviceCalibration> deviceCalibration = deviceCalibrationFacade.getDeviceCalibration(calibrationId);

		logger.info("getCalibration({}) request finished", calibrationId);

		return returnOptional(deviceCalibration);
	}

	@Override
	public ResponseEntity<DeviceCalibration> updateCalibration(@ApiParam(required = true) @PathVariable("calibrationId") Long calibrationId,
															   @ApiParam(required = true) @Valid @RequestBody DeviceCalibration body) {
		logger.info("updateCalibration({}, {}) request received", calibrationId, body);

		DeviceCalibration deviceCalibration = deviceCalibrationFacade.updateDeviceCalibration(body);

		logger.info("updateCalibration({}, {}) request finished", calibrationId, deviceCalibration);

		return ResponseEntity.ok(deviceCalibration);
	}
}