package cz.siemens.inventory.controllers;


import cz.siemens.inventory.facade.DeviceRevisionFacade;
import cz.siemens.inventory.gen.api.ElectricRevisionsApi;
import cz.siemens.inventory.gen.model.DeviceRevision;
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
public class ElectricRevisionController extends BaseController implements ElectricRevisionsApi {

	final static Logger logger = LoggerFactory.getLogger(ElectricRevisionController.class);
	private DeviceRevisionFacade deviceRevisionFacade;

	@Autowired
	public ElectricRevisionController(DeviceRevisionFacade deviceRevisionFacade) {
		this.deviceRevisionFacade = deviceRevisionFacade;
	}

	@Override
	public ResponseEntity<DeviceRevision> createElectricRevision(@ApiParam(required = true) @Valid @RequestBody DeviceRevision body) {
		logger.info("createElectricRevision({}) request received", body);

		DeviceRevision deviceRevision = deviceRevisionFacade.createDeviceRevision(body);

		logger.info("createElectricRevision({}) request finished", deviceRevision);

		return ResponseEntity.ok(deviceRevision);
	}

	@Override
	public ResponseEntity<DeviceRevision> getElectricRevision(@ApiParam(required = true) @PathVariable("revisionId") Long revisionId) {
		logger.info("getElectricRevision({}) request received", revisionId);

		Optional<DeviceRevision> deviceRevision = deviceRevisionFacade.getDeviceRevision(revisionId);

		logger.info("getElectricRevision({}) request finished", revisionId);

		return returnOptional(deviceRevision);
	}

	@Override
	public ResponseEntity<DeviceRevision> updateElectricRevision(@ApiParam(required = true) @PathVariable("revisionId") Long revisionId,
																 @ApiParam(required = true) @Valid @RequestBody DeviceRevision body) {
		logger.info("updateElectricRevision({}, {}) request received", revisionId, body);

		DeviceRevision deviceRevision = deviceRevisionFacade.updateDeviceRevision(body);

		logger.info("updateElectricRevision({}, {}) request finished", revisionId, deviceRevision);

		return ResponseEntity.ok(deviceRevision);
	}
}