package cz.siemens.inventory.facade;

import cz.siemens.inventory.api.facade.AuditLogFacade;
import cz.siemens.inventory.api.facade.DeviceCalibrationFacade;
import cz.siemens.inventory.api.gen.model.DeviceCalibration;
import cz.siemens.inventory.audit.AuditUtils.AuditUtil;
import cz.siemens.inventory.dao.ApplianceCalibrationDao;
import cz.siemens.inventory.entity.ApplianceCalibration;
import cz.siemens.inventory.entity.InventoryServiceAuditLog;
import cz.siemens.inventory.entity.DeviceInternal;
import cz.siemens.inventory.exception.NotFoundException;
import cz.siemens.inventory.api.mapper.DeviceCalibrationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

		auditLogFacade.saveAuditLogEntries(revisionAuditEntries, InventoryServiceAuditLog.Category.CALIBRATION, new DeviceInternal(createdCalibration.getId()));
		return deviceCalibrationMapper.mapToExternal(createdCalibration);
	}
}
