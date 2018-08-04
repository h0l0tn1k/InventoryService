package cz.siemens.inventory.facade;

import cz.siemens.inventory.gen.model.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceFacade {

	List<Device> getDevices();

	Optional<Device> getDevice(long deviceId);
}
