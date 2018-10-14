package cz.siemens.inventory.facade.impl;

import cz.siemens.inventory.dao.ApplianceRevisionDao;
import cz.siemens.inventory.entity.ApplianceRevision;
import cz.siemens.inventory.facade.DeviceRevisionFacade;
import cz.siemens.inventory.gen.model.DeviceRevision;
import cz.siemens.inventory.mapper.DeviceRevisionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class DeviceRevisionFacadeImpl implements DeviceRevisionFacade {

	private DeviceRevisionMapper deviceRevisionMapper;
	private ApplianceRevisionDao applianceRevisionDao;

	@Autowired
	public DeviceRevisionFacadeImpl(ApplianceRevisionDao applianceRevisionDao, DeviceRevisionMapper deviceRevisionMapper) {
		this.applianceRevisionDao = applianceRevisionDao;
		this.deviceRevisionMapper = deviceRevisionMapper;
	}

	@Override
	public Optional<DeviceRevision> getDeviceRevision(long revisionId) {
		return deviceRevisionMapper.mapToExternal(applianceRevisionDao.findById(revisionId));
	}

	@Override
	public DeviceRevision createDeviceRevision(DeviceRevision deviceRevision) {
		//todo add audit log if saved successfully
		ApplianceRevision applianceRevision = deviceRevisionMapper.mapToInternal(deviceRevision);
		return deviceRevisionMapper.mapToExternal(applianceRevisionDao.save(applianceRevision));
	}

	@Override
	public DeviceRevision updateDeviceRevision(DeviceRevision deviceRevision) {
		//todo add audit log if saved successfully
		ApplianceRevision applianceRevision = deviceRevisionMapper.mapToInternal(deviceRevision);
		return deviceRevisionMapper.mapToExternal(applianceRevisionDao.save(applianceRevision));
	}
}
