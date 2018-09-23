package cz.siemens.inventory.facade.impl;

import cz.siemens.inventory.dao.ApplianceCalibrationDao;
import cz.siemens.inventory.entity.ApplianceCalibration;
import cz.siemens.inventory.facade.DeviceCalibrationFacade;
import cz.siemens.inventory.gen.model.DeviceCalibration;
import cz.siemens.inventory.mapper.DeviceCalibrationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class DeviceCalibrationFacadeImpl implements DeviceCalibrationFacade {

	private DeviceCalibrationMapper deviceCalibrationMapper;
	private ApplianceCalibrationDao applianceCalibrationDao;

	@Autowired
	public DeviceCalibrationFacadeImpl(DeviceCalibrationMapper deviceCalibrationMapper, ApplianceCalibrationDao applianceCalibrationDao) {
		this.deviceCalibrationMapper = deviceCalibrationMapper;
		this.applianceCalibrationDao = applianceCalibrationDao;
	}

	@Override
	public Optional<DeviceCalibration> getDeviceCalibration(long calibrationId) {
		return deviceCalibrationMapper.mapToExternal(applianceCalibrationDao.findById(calibrationId));
	}

	@Override
	public DeviceCalibration createDeviceCalibration(DeviceCalibration deviceCalibration) {
		ApplianceCalibration calibration = deviceCalibrationMapper.mapToInternal(deviceCalibration);

		return deviceCalibrationMapper.mapToExternal(applianceCalibrationDao.save(calibration));
	}
}
