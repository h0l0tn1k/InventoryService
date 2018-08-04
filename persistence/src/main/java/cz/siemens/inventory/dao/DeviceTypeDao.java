package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceTypeDao extends JpaRepository<DeviceType, Long> {
}
