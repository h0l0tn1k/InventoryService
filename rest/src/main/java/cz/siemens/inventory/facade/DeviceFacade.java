package cz.siemens.inventory.facade;

import cz.siemens.inventory.gen.model.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceFacade {

	List<Device> getDevices();

	Optional<Device> getDevice(long deviceId);

	Optional<Device> getDeviceByBarcode(String barcode);

	Optional<Device> getDeviceBySerialNumber(String serialNumber);

	List<Device> getDevicesBySerialNumberLike(String serialNumber);

	Device createDevice(Device device);

	void deleteDevice(long deviceId);
}
