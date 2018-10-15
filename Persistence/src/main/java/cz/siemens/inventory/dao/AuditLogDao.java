package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogDao extends JpaRepository<AuditLog, Long> {
}
