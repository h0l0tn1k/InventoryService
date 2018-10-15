package cz.siemens.inventory.facade;

import cz.siemens.inventory.entity.AuditLog;
import cz.siemens.inventory.entity.DeviceInternal;
import cz.siemens.inventory.gen.model.DeviceType;

import java.util.List;
import java.util.Optional;

public interface AuditLogFacade {

	AuditLog createAuditLog(AuditLog auditLog);

	List<AuditLog> createAuditLogs(List<AuditLog> auditLogs);

	void saveAuditLogEntries(List<String> descriptions, AuditLog.Category category, DeviceInternal device);
}
