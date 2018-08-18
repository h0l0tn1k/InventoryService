package cz.siemens.inventory.facade.impl;

import cz.siemens.inventory.dao.DeviceDao;
import cz.siemens.inventory.dao.InventoryRecordDao;
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
	private InventoryRecordFacade inventoryRecordFacade;

	@Autowired
	public DeviceFacadeImpl(DeviceDao deviceDao, DeviceMapper deviceMapper, InventoryRecordFacade inventoryRecordFacade) {
		this.deviceDao = deviceDao;
		this.inventoryRecordFacade = inventoryRecordFacade;
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
	public Device createDevice(Device device) {
		//todo: add validation
		device.addDate(OffsetDateTime.now());
		//WORKAROUND: due to poor design I have to save InventoryRecord first
		InventoryRecord inventoryRecord = inventoryRecordFacade.createInventoryRecord(device.getInventoryRecord());
		cz.siemens.inventory.entity.Device deviceToCreate = deviceMapper.mapToInternal(device);
		device.setInventoryRecord(inventoryRecord);

		cz.siemens.inventory.entity.Device createdDevice = deviceDao.save(deviceToCreate);
		return deviceMapper.mapToExternal(createdDevice);
	}

	@Override
	public void deleteDevice(long deviceId) {
		deviceDao.deleteById(deviceId);
	}
}