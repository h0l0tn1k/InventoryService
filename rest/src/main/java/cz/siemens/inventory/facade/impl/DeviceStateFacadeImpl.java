package cz.siemens.inventory.facade.impl;

import cz.siemens.inventory.dao.DeviceStateDao;
import cz.siemens.inventory.facade.DeviceStateFacade;
import cz.siemens.inventory.gen.model.DeviceState;
import cz.siemens.inventory.mapper.DeviceStateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceStateFacadeImpl implements DeviceStateFacade {

	private DeviceStateDao deviceStateDao;
	private DeviceStateMapper deviceStateMapper;

	@Autowired
	public DeviceStateFacadeImpl(DeviceStateDao deviceStateDao, DeviceStateMapper deviceStateMapper) {
		this.deviceStateDao = deviceStateDao;
		this.deviceStateMapper = deviceStateMapper;
	}

	@Override
	public List<DeviceState> getDeviceStates() {
		return deviceStateMapper.mapToExternal(deviceStateDao.findAll());
	}

	@Override
	public Optional<DeviceState> getDeviceState(long deviceStateId) {
		return deviceStateMapper.mapToExternal(deviceStateDao.findById(deviceStateId));
	}

	@Override
	public DeviceState createDeviceState(DeviceState deviceState) {
		//todo: add validations
		return deviceStateMapper.mapToExternal(deviceStateDao.save(deviceStateMapper.mapToInternal(deviceState)));
	}

	@Override
	public void deleteDeviceState(long deviceStateId) {
		deviceStateDao.deleteById(deviceStateId);
	}
}
