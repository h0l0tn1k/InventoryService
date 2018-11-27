package cz.siemens.inventory.facade.impl;

import cz.siemens.inventory.audit.AuditUtils.AuditUtil;
import cz.siemens.inventory.dao.ApplianceRevisionDao;
import cz.siemens.inventory.entity.ApplianceRevision;
import cz.siemens.inventory.entity.InventoryServiceAuditLog;
import cz.siemens.inventory.entity.DeviceInternal;
import cz.siemens.inventory.exception.NotFoundException;
import cz.siemens.inventory.facade.AuditLogFacade;
import cz.siemens.inventory.facade.DeviceRevisionFacade;
import cz.siemens.inventory.gen.model.DeviceRevision;
import cz.siemens.inventory.mapper.DeviceRevisionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DeviceRevisionFacadeImpl implements DeviceRevisionFacade {

	private DeviceRevisionMapper deviceRevisionMapper;
	private ApplianceRevisionDao applianceRevisionDao;
	private AuditLogFacade auditLogFacade;

	@Autowired
	public DeviceRevisionFacadeImpl(ApplianceRevisionDao applianceRevisionDao, DeviceRevisionMapper deviceRevisionMapper,
									AuditLogFacade auditLogFacade) {
		this.applianceRevisionDao = applianceRevisionDao;
		this.deviceRevisionMapper = deviceRevisionMapper;
		this.auditLogFacade = auditLogFacade;
	}

	@Override
	public Optional<DeviceRevision> getDeviceRevision(long revisionId) {
		return deviceRevisionMapper.mapToExternal(applianceRevisionDao.findById(revisionId));
	}

	@Override
	public DeviceRevision updateDeviceRevision(DeviceRevision deviceRevision) {
		Optional<ApplianceRevision> fromDbOptional = applianceRevisionDao.findById(deviceRevision.getId());
		if(!fromDbOptional.isPresent()) {
			throw new NotFoundException("Electric revision with id=" + deviceRevision.getId() + " not found.");
		}
		ApplianceRevision newApplianceRevision = deviceRevisionMapper.mapToInternal(deviceRevision);
		List<String> revisionAuditEntries = AuditUtil.getRevisionAuditEntries(fromDbOptional.get(), newApplianceRevision);

		ApplianceRevision createdRevision = applianceRevisionDao.save(newApplianceRevision);

		auditLogFacade.saveAuditLogEntries(revisionAuditEntries, InventoryServiceAuditLog.Category.REVISION, new DeviceInternal(createdRevision.getId()));
		return deviceRevisionMapper.mapToExternal(createdRevision);
	}
}
