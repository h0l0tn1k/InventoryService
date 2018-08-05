package cz.siemens.inventory.facade.impl;

import cz.siemens.inventory.dao.DeviceDao;
import cz.siemens.inventory.facade.DeviceFacade;
import cz.siemens.inventory.gen.model.Device;
import cz.siemens.inventory.mapper.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
		return deviceMapper.mapToExternal(deviceDao.save(deviceMapper.mapToInternal(device)));
	}

	@Override
	public void deleteDevice(long deviceId) {
		deviceDao.deleteById(deviceId);
	}
}