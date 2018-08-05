package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.DeviceState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceStateDao extends JpaRepository<DeviceState, Long> {
}
