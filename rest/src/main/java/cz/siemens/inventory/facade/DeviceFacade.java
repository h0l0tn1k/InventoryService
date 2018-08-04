package cz.siemens.inventory.facade;

import cz.siemens.inventory.gen.model.Device;

import java.util.List;

public interface DeviceFacade {

	List<Device> getDevices();

	Device getDevice(long deviceId);
}
