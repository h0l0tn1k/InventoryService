package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.ApplianceRevision;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplianceRevisionDao extends JpaRepository<ApplianceRevision, Long> {
}
