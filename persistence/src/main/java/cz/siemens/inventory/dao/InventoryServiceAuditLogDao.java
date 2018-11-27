package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.InventoryServiceAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryServiceAuditLogDao extends JpaRepository<InventoryServiceAuditLog, Long> {
}
