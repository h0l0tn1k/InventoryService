package cz.siemens.inventory.facade;


import cz.siemens.inventory.gen.model.Device;
import cz.siemens.inventory.gen.model.InventoryRecord;

import java.util.List;
import java.util.Optional;

public interface InventoryRecordFacade {

	InventoryRecord createInventoryRecord(InventoryRecord inventoryRecord);

	InventoryRecord updateInventoryRecord(InventoryRecord inventoryRecord);

	Optional<InventoryRecord> getInventoryRecord(Long inventoryRecordId);

	List<InventoryRecord> getInventoryRecords();

	List<Device> getAllCheckedDevices();

	List<Device> getAllUncheckedDevices();

}
