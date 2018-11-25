package cz.siemens.inventory.facade;


import cz.siemens.inventory.gen.model.Device;
import cz.siemens.inventory.gen.model.InventoryRecord;

import java.util.List;
import java.util.Optional;

public interface InventoryRecordFacade {

	InventoryRecord updateInventoryRecord(InventoryRecord inventoryRecord);

	Optional<InventoryRecord> getInventoryRecord(Long inventoryRecordId);

	List<InventoryRecord> getInventoryRecords();

}
