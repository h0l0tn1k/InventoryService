package cz.siemens.inventory.facade.impl;

import cz.siemens.inventory.dao.DeviceDao;
import cz.siemens.inventory.dao.InventoryRecordDao;
import cz.siemens.inventory.entity.LoginUserScd;
import cz.siemens.inventory.facade.DeviceFacade;
import cz.siemens.inventory.facade.InventoryRecordFacade;
import cz.siemens.inventory.gen.model.Device;
import cz.siemens.inventory.gen.model.InventoryRecord;
import cz.siemens.inventory.mapper.DeviceMapper;
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

	@Autowired
	public DeviceFacadeImpl(DeviceDao deviceDao, DeviceMapper deviceMapper) {
		this.deviceDao = deviceDao;
		this.deviceMapper = deviceMapper;
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
		return deviceMapper.mapToExternal(deviceDao.save(deviceMapper.mapToInternal(device)));
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
	public List<Device> getDevicesBorrowedByUser(Long userId) {
		return deviceMapper.mapToExternal(deviceDao.getDevicesBorrowedByUser(userId));
	}

	@Override
	public Device createDevice(Device device) {
		//todo: add validation
		cz.siemens.inventory.entity.Device device1 = deviceMapper.mapToInternal(device);
		device1.setAddDate(OffsetDateTime.now());
		return deviceMapper.mapToExternal(deviceDao.save(device1));
	}

	@Override
	public void deleteDevice(long deviceId) {
		deviceDao.deleteById(deviceId);
	}
}