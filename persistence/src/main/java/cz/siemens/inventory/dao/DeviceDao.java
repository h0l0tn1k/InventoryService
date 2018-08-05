package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceDao extends JpaRepository<Device, Long> {

	Optional<Device> getDeviceByBarcodeNumber(String barcodeNumber);

	Optional<Device> getDeviceBySerialNumber(String serialNumber);

	List<Device> getDevicesBySerialNumberContainingIgnoreCase(String serialNumber);
}
