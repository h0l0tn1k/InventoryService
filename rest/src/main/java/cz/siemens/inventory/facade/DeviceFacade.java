package cz.siemens.inventory.facade;

import cz.siemens.inventory.gen.model.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceFacade {

	List<Device> getDevices();

	Optional<Device> getDevice(long deviceId);

	Device updateDevice(Device device);

	Optional<Device> getDeviceByBarcode(String barcode);

	Optional<Device> getDeviceBySerialNumber(String serialNumber);

	List<Device> getDevicesBySerialNumberLike(String serialNumber);

	List<Device> getDevicesBySerialOrBarcodeNumberLike(String serialOrBarcodeNumber);

	List<Device> getDevicesBorrowedByUser(Long userId);

	Device createDevice(Device device);

	void deleteDevice(long deviceId);
}
