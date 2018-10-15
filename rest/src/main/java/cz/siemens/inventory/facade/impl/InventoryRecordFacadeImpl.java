package cz.siemens.inventory.facade.impl;

import cz.siemens.inventory.audit.AuditUtils.AuditUtil;
import cz.siemens.inventory.dao.InventoryRecordDao;
import cz.siemens.inventory.entity.AuditLog;
import cz.siemens.inventory.entity.DeviceInternal;
import cz.siemens.inventory.exception.NotFoundException;
import cz.siemens.inventory.facade.AuditLogFacade;
import cz.siemens.inventory.facade.InventoryRecordFacade;
import cz.siemens.inventory.gen.model.Device;
import cz.siemens.inventory.gen.model.InventoryRecord;
import cz.siemens.inventory.mapper.DeviceMapper;
import cz.siemens.inventory.mapper.InventoryRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class InventoryRecordFacadeImpl implements InventoryRecordFacade {

	private InventoryRecordDao inventoryRecordDao;
	private InventoryRecordMapper inventoryRecordMapper;
	private DeviceMapper deviceMapper;
	private AuditLogFacade auditLogFacade;

	@Autowired
	public InventoryRecordFacadeImpl(InventoryRecordDao inventoryRecordDao,
									 InventoryRecordMapper inventoryRecordMapper,
									 DeviceMapper deviceMapper,
									 AuditLogFacade auditLogFacade) {
		this.inventoryRecordDao = inventoryRecordDao;
		this.inventoryRecordMapper = inventoryRecordMapper;
		this.deviceMapper = deviceMapper;
		this.auditLogFacade = auditLogFacade;
	}

	@Override
	public InventoryRecord createInventoryRecord(InventoryRecord inventoryRecord) {
		return inventoryRecordMapper.mapToExternal(inventoryRecordDao.saveAndFlush(inventoryRecordMapper.mapToInternal(inventoryRecord)));
	}

	@Override
	public InventoryRecord updateInventoryRecord(InventoryRecord inventoryRecord) {
		cz.siemens.inventory.entity.InventoryRecord newInventoryRecord = inventoryRecordMapper.mapToInternal(inventoryRecord);
		Optional<cz.siemens.inventory.entity.InventoryRecord> fromDbOptional = inventoryRecordDao.findById(inventoryRecord.getId());
		if (!fromDbOptional.isPresent()) {
			throw new NotFoundException("Inventory record with id=" + inventoryRecord.getId() + " not found.");
		}
		List<String> inventoryRecordAuditEntries = AuditUtil.getInventoryRecordAuditEntries(fromDbOptional.get(), newInventoryRecord);
		cz.siemens.inventory.entity.InventoryRecord savedInventoryRecord = inventoryRecordDao.save(newInventoryRecord);

		auditLogFacade.saveAuditLogEntries(inventoryRecordAuditEntries, AuditLog.Category.INVENTORY, new DeviceInternal(inventoryRecord.getId()));
		return inventoryRecordMapper.mapToExternal(savedInventoryRecord);
	}

	@Override
	public Optional<InventoryRecord> getInventoryRecord(Long inventoryRecordId) {
		return inventoryRecordMapper.mapToExternal(inventoryRecordDao.findById(inventoryRecordId));
	}

	@Override
	public List<InventoryRecord> getInventoryRecords() {
		return inventoryRecordMapper.mapToExternal(inventoryRecordDao.findAll());
	}

	@Override
	public List<Device> getAllCheckedDevices() {
		return deviceMapper.mapToExternal(inventoryRecordDao.findAllChecked().stream().map(x -> x.getDeviceInventory()).collect(Collectors.toList()));
	}

	@Override
	public List<Device> getAllUncheckedDevices() {
		return deviceMapper.mapToExternal(inventoryRecordDao.findAllUnChecked().stream().map(x -> x.getDeviceInventory()).collect(Collectors.toList()));
	}
}
