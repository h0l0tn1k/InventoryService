package cz.siemens.inventory.facade.impl;

import cz.siemens.inventory.audit.AuditUtils.AuditUtil;
import cz.siemens.inventory.dao.ApplianceCalibrationDao;
import cz.siemens.inventory.entity.ApplianceCalibration;
import cz.siemens.inventory.entity.ApplianceRevision;
import cz.siemens.inventory.entity.AuditLog;
import cz.siemens.inventory.entity.DeviceInternal;
import cz.siemens.inventory.exception.NotFoundException;
import cz.siemens.inventory.facade.AuditLogFacade;
import cz.siemens.inventory.facade.DeviceCalibrationFacade;
import cz.siemens.inventory.gen.model.DeviceCalibration;
import cz.siemens.inventory.mapper.DeviceCalibrationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DeviceCalibrationFacadeImpl implements DeviceCalibrationFacade {

	private DeviceCalibrationMapper deviceCalibrationMapper;
	private ApplianceCalibrationDao applianceCalibrationDao;
	private AuditLogFacade auditLogFacade;

	@Autowired
	public DeviceCalibrationFacadeImpl(DeviceCalibrationMapper deviceCalibrationMapper, ApplianceCalibrationDao applianceCalibrationDao,
									   AuditLogFacade auditLogFacade) {
		this.deviceCalibrationMapper = deviceCalibrationMapper;
		this.applianceCalibrationDao = applianceCalibrationDao;
		this.auditLogFacade = auditLogFacade;
	}

	@Override
	public Optional<DeviceCalibration> getDeviceCalibration(long calibrationId) {
		return deviceCalibrationMapper.mapToExternal(applianceCalibrationDao.findById(calibrationId));
	}

	@Override
	public DeviceCalibration updateDeviceCalibration(DeviceCalibration deviceCalibration) {
		Optional<ApplianceCalibration> fromDbOptional = applianceCalibrationDao.findById(deviceCalibration.getId());
		if(!fromDbOptional.isPresent()) {
			throw new NotFoundException("Appliance Calibration with id=" + deviceCalibration.getId() + " not found.");
		}
		ApplianceCalibration newApplianceCalibration = deviceCalibrationMapper.mapToInternal(deviceCalibration);
		List<String> revisionAuditEntries = AuditUtil.getCalibrationAuditEntries(fromDbOptional.get(), newApplianceCalibration);

		ApplianceCalibration createdCalibration = applianceCalibrationDao.save(newApplianceCalibration);

		auditLogFacade.saveAuditLogEntries(revisionAuditEntries, AuditLog.Category.CALIBRATION, new DeviceInternal(createdCalibration.getId()));
		return deviceCalibrationMapper.mapToExternal(createdCalibration);
	}
}
