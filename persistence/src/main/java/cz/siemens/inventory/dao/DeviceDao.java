package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceDao extends JpaRepository<Device, Long> {
}
