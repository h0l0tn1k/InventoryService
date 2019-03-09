package cz.siemens.inventory.facade;

import cz.siemens.inventory.api.facade.AuditLogFacade;
import cz.siemens.inventory.api.facade.DeviceFacade;
import cz.siemens.inventory.api.gen.model.Device;
import cz.siemens.inventory.audit.AuditUtils.AuditUtil;
import cz.siemens.inventory.dao.DeviceDao;
import cz.siemens.inventory.entity.*;
import cz.siemens.inventory.entity.custom.InventoryState;
import cz.siemens.inventory.exception.BadRequestException;
import cz.siemens.inventory.exception.ConflictException;
import cz.siemens.inventory.exception.NotFoundException;
import cz.siemens.inventory.api.mapper.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DeviceFacadeImpl implements DeviceFacade {

	private DeviceMapper deviceMapper;
	private DeviceDao deviceDao;
	private AuditLogFacade auditLogFacade;

	@Autowired
	public DeviceFacadeImpl(DeviceDao deviceDao, DeviceMapper deviceMapper, AuditLogFacade auditLogFacade) {
		this.deviceDao = deviceDao;
		this.deviceMapper = deviceMapper;
		this.auditLogFacade = auditLogFacade;
	}

	@Override
	public List<Device> getDevices() {
		return deviceMapper.mapToExternal(deviceDao.findAll());
	}

	@Override
	public Optional<Device> getDevice(long deviceId) {
		return deviceMapper.mapToExternal(deviceDao.findById(deviceId));
	}

	@Override
	public Device updateDevice(Device device) {
		DeviceInternal newDevice = deviceMapper.mapToInternal(device);
		Optional<DeviceInternal> fromDbOptional = deviceDao.findById(newDevice.getId());
		if (!fromDbOptional.isPresent()) {
			throw new NotFoundException("Device with id=" + newDevice.getId() + " not found.");
		}

		checkBarcodeIsUnique(newDevice);
		checkSerialNumberIsUnique(newDevice);

		List<String> deviceAuditEntries = AuditUtil.getDeviceAuditEntries(fromDbOptional.get(), newDevice);
		DeviceInternal savedDevice = deviceDao.save(newDevice);
		auditLogFacade.saveAuditLogEntries(deviceAuditEntries, InventoryServiceAuditLog.Category.GENERAL, savedDevice);

		return deviceMapper.mapToExternal(savedDevice);
	}

	@Override
	public Optional<Device> getDeviceByBarcode(String barcode) {
		return deviceMapper.mapToExternal(deviceDao.getDeviceByBarcodeNumber(barcode));
	}

	@Override
	public Optional<Device> getDeviceBySerialNumber(String serialNumber) {
		return deviceMapper.mapToExternal(deviceDao.getDeviceBySerialNumber(serialNumber));
	}

	@Override
	public List<Device> getDevicesBySerialNumberLike(String serialNumber) {
		return deviceMapper.mapToExternal(deviceDao.getDevicesBySerialNumberContainingIgnoreCase(serialNumber));
	}

	@Override
	public List<Device> getDevicesBySerialOrBarcodeNumberLike(String serialOrBarcodeNumber) {
		return deviceMapper.mapToExternal(deviceDao.getDevicesByQrCodeOrSerialNumber(serialOrBarcodeNumber));
	}

	@Override
	public List<Device> getDevicesBorrowedByUser(Long userId) {
		return deviceMapper.mapToExternal(deviceDao.getDevicesBorrowedByUser(userId));
	}

	@Override
	public Device createDevice(Device device) {
		DeviceInternal deviceInternal = deviceMapper.mapToInternal(device);
		deviceInternal.setId(null);
		deviceInternal.setInventoryRecord(getNewInventoryRecord());
		deviceInternal.setDeviceCalibration(getNewApplianceCalibration());
		deviceInternal.setLastRevision(getNewApplianceRevision());

		validate(deviceInternal);

		List<String> deviceAuditEntries = AuditUtil.getDeviceAuditEntries(null, deviceInternal);
		DeviceInternal createdDevice = deviceDao.save(deviceInternal);
		auditLogFacade.saveAuditLogEntries(deviceAuditEntries, InventoryServiceAuditLog.Category.GENERAL, createdDevice);

		return deviceMapper.mapToExternal(createdDevice);
	}

	@Override
	public void deleteDevice(long deviceId) {
		deviceDao.deleteById(deviceId);
	}

	private InventoryRecord getNewInventoryRecord() {
		return new InventoryRecord(null, InventoryState.False, "", null);
	}

	private ApplianceRevision getNewApplianceRevision() {
		return new ApplianceRevision(null, 0, null, null);
	}

	private ApplianceCalibration getNewApplianceCalibration() {
		return new ApplianceCalibration(null, 0, null, null);
	}

	public void validate(DeviceInternal deviceInternal) {
		checkAddDate(deviceInternal);
		checkSerialNumberIsNotEmpty(deviceInternal);
		checkSerialNumberIsUnique(deviceInternal);
		checkBarcodeIsUnique(deviceInternal);
	}

	private void checkAddDate(DeviceInternal deviceInternal) {
		if (deviceInternal.getAddDate() == null) {
			deviceInternal.setAddDate(OffsetDateTime.now());
		} else if (deviceInternal.getAddDate().isAfter(OffsetDateTime.now())) {
			throw new BadRequestException("Add date cannot be in the future");
		}
	}

	private void checkBarcodeIsUnique(DeviceInternal deviceInternal) {
		Optional<DeviceInternal> deviceByBarcodeNumber = deviceDao.getDeviceByBarcodeNumber(deviceInternal.getBarcodeNumber());
		if (deviceByBarcodeNumber.isPresent()) {
			if (!deviceByBarcodeNumber.get().getId().equals(deviceInternal.getId())) {
				throw new ConflictException("Device with barcode number " + deviceInternal.getBarcodeNumber() + " already exists.");
			}
		}
	}

	private void checkSerialNumberIsNotEmpty(DeviceInternal deviceInternal) {
		if (deviceInternal.getSerialNumber().trim().isEmpty()) {
			throw new BadRequestException("Device cannot have empty serial number.");
		}
	}

	private void checkSerialNumberIsUnique(DeviceInternal deviceInternal) {
		Optional<DeviceInternal> deviceBySerialNumber = deviceDao.getDeviceBySerialNumber(deviceInternal.getSerialNumber());
		if (deviceBySerialNumber.isPresent()) {
			if (!deviceBySerialNumber.get().getId().equals(deviceInternal.getId())) {
				throw new ConflictException("Device with serial number " + deviceInternal.getSerialNumber() + " already exists.");
			}
		}
	}
}