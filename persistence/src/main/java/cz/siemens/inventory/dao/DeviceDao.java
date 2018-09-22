package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DeviceDao extends JpaRepository<Device, Long> {

	Optional<Device> getDeviceByBarcodeNumber(String barcodeNumber);

	Optional<Device> getDeviceBySerialNumber(String serialNumber);

	List<Device> getDevicesBySerialNumberContainingIgnoreCase(String serialNumber);

	@Query("SELECT dev FROM Device dev WHERE dev.holder.id=:userId")
	List<Device> getDevicesBorrowedByUser(@Param("userId") Long userId);
}
