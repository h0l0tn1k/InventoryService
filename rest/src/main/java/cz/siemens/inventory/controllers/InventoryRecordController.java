package cz.siemens.inventory.controllers;

import cz.siemens.inventory.facade.InventoryRecordFacade;
import cz.siemens.inventory.gen.api.InventoryRecordsApi;
import cz.siemens.inventory.gen.model.InventoryRecord;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiUris.ROOT_URI)
public class InventoryRecordController extends BaseController implements InventoryRecordsApi {

	private InventoryRecordFacade inventoryRecordFacade;
	final static Logger logger = LoggerFactory.getLogger(InventoryRecordController.class);

	@Autowired
	public InventoryRecordController(InventoryRecordFacade inventoryRecordFacade) {
		this.inventoryRecordFacade = inventoryRecordFacade;
	}

	public ResponseEntity<InventoryRecord> getInventoryRecord(@ApiParam(required = true) @PathVariable("inventoryRecordId") Long inventoryRecordId) {
		logger.info("getInventoryRecord({}) request received", inventoryRecordId);

		Optional<InventoryRecord> inventoryRecord = inventoryRecordFacade.getInventoryRecord(inventoryRecordId);

		logger.info("getInventoryRecord({}) request finished", inventoryRecordId);

		return returnOptional(inventoryRecord);
	}

	public ResponseEntity<List<InventoryRecord>> getInventoryRecords() {
		logger.info("getInventoryRecords() request received");

		List<InventoryRecord> inventoryRecords = inventoryRecordFacade.getInventoryRecords();

		logger.info("getInventoryRecords() request finished");

		return ResponseEntity.ok(inventoryRecords);
	}

	public ResponseEntity<InventoryRecord> updateInventoryRecord(@ApiParam(required = true) @PathVariable("inventoryRecordId") Long inventoryRecordId,
																 @ApiParam(required = true) @Valid @RequestBody InventoryRecord body) {
		logger.info("updateInventoryRecord({}, {}) request received", inventoryRecordId, body.toString());

		InventoryRecord inventoryRecord = inventoryRecordFacade.updateInventoryRecord(body);

		logger.info("updateInventoryRecord({}) request finished", inventoryRecordId);

		return ResponseEntity.ok(inventoryRecord);
	}
}