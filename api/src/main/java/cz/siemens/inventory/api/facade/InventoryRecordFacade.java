package cz.siemens.inventory.api.facade;

import cz.siemens.inventory.api.gen.model.InventoryRecord;

import java.util.List;
import java.util.Optional;

public interface InventoryRecordFacade {

	InventoryRecord updateInventoryRecord(InventoryRecord inventoryRecord);

	Optional<InventoryRecord> getInventoryRecord(Long inventoryRecordId);

	List<InventoryRecord> getInventoryRecords();

}
