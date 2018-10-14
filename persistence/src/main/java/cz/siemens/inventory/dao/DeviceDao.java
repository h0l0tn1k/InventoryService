package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.DeviceInternal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DeviceDao extends JpaRepository<DeviceInternal, Long> {

	Optional<DeviceInternal> getDeviceByBarcodeNumber(String barcodeNumber);

	Optional<DeviceInternal> getDeviceBySerialNumber(String serialNumber);

	List<DeviceInternal> getDevicesBySerialNumberContainingIgnoreCase(String serialNumber);

	@Query("SELECT dev FROM DeviceInternal dev WHERE dev.holder.id=:userId")
	List<DeviceInternal> getDevicesBorrowedByUser(@Param("userId") Long userId);
}
