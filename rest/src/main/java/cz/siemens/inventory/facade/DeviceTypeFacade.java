package cz.siemens.inventory.facade;

import cz.siemens.inventory.gen.model.DeviceType;

import java.util.List;
import java.util.Optional;

public interface DeviceTypeFacade {

	List<DeviceType> getDeviceTypes();

	Optional<DeviceType> getDeviceType(long deviceTypeId);

	List<DeviceType> getDeviceTypesByName(String deviceTypeName);

	DeviceType createDeviceType(DeviceType deviceType);

	DeviceType updateDeviceType(DeviceType deviceType);

	void deleteDeviceType(long deviceTypeId);
}
