package cz.siemens.inventory.api.facade;

import cz.siemens.inventory.entity.DeviceInternal;
import cz.siemens.inventory.entity.InventoryServiceAuditLog;

import java.util.List;

public interface AuditLogFacade {

	InventoryServiceAuditLog createAuditLog(InventoryServiceAuditLog inventoryServiceAuditLog);

	List<InventoryServiceAuditLog> createAuditLogs(List<InventoryServiceAuditLog> inventoryServiceAuditLogs);

	void saveAuditLogEntries(List<String> descriptions, InventoryServiceAuditLog.Category category, DeviceInternal device);
}
