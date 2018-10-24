package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceTypeDao extends JpaRepository<DeviceType, Long> {

	List<DeviceType> getDeviceTypesByObjectTypeNameContaining(String objectTypeName);
}
