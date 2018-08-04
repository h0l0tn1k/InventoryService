package cz.siemens.inventory.facade.impl;

import cz.siemens.inventory.dao.DeviceDaoImpl;
import cz.siemens.inventory.facade.DeviceFacade;
import cz.siemens.inventory.gen.model.Device;
import cz.siemens.inventory.mapper.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceFacadeImpl implements DeviceFacade {

	private DeviceMapper deviceMapper;
	private DeviceDaoImpl deviceDao;

	@Autowired
	public DeviceFacadeImpl(DeviceDaoImpl deviceDao, DeviceMapper deviceMapper) {
		this.deviceDao = deviceDao;
		this.deviceMapper = deviceMapper;
	}

	@Override
	public List<Device> getDevices() {
		return deviceMapper.mapToExternal(deviceDao.readAll());
	}

	@Override
	public Device getDevice(long deviceId) {
		return deviceMapper.mapToExternal(deviceDao.read(deviceId));
	}
}
