package cz.siemens.inventory.facade.impl;

import cz.siemens.inventory.dao.DeviceTypeDao;
import cz.siemens.inventory.facade.DeviceTypeFacade;
import cz.siemens.inventory.gen.model.DeviceType;
import cz.siemens.inventory.mapper.DeviceTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DeviceTypeFacadeImpl implements DeviceTypeFacade {

	private DeviceTypeDao deviceTypeDao;
	private DeviceTypeMapper deviceTypeMapper;

	@Autowired
	public DeviceTypeFacadeImpl(DeviceTypeDao deviceTypeDao, DeviceTypeMapper deviceTypeMapper) {
		this.deviceTypeDao = deviceTypeDao;
		this.deviceTypeMapper = deviceTypeMapper;
	}

	@Override
	public List<DeviceType> getDeviceTypes() {
		return deviceTypeMapper.mapToExternal(deviceTypeDao.findAll());
	}

	@Override
	public Optional<DeviceType> getDeviceType(long deviceTypeId) {
		return deviceTypeMapper.mapToExternal(deviceTypeDao.findById(deviceTypeId));
	}

	@Override
	public DeviceType createDeviceType(DeviceType deviceType) {
		//todo: add validations
		return deviceTypeMapper.mapToExternal(deviceTypeDao.save(deviceTypeMapper.mapToInternal(deviceType)));
	}

	@Override
	public void deleteDeviceType(long deviceTypeId) {
		deviceTypeDao.deleteById(deviceTypeId);
	}
}
