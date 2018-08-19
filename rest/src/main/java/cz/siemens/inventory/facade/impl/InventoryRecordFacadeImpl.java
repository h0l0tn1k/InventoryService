package cz.siemens.inventory.facade.impl;

import cz.siemens.inventory.dao.InventoryRecordDao;
import cz.siemens.inventory.facade.InventoryRecordFacade;
import cz.siemens.inventory.gen.model.InventoryRecord;
import cz.siemens.inventory.mapper.InventoryRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InventoryRecordFacadeImpl implements InventoryRecordFacade {

	private InventoryRecordDao inventoryRecordDao;
	private InventoryRecordMapper inventoryRecordMapper;

	@Autowired
	public InventoryRecordFacadeImpl(InventoryRecordDao inventoryRecordDao,
									 InventoryRecordMapper inventoryRecordMapper) {
		this.inventoryRecordDao = inventoryRecordDao;
		this.inventoryRecordMapper = inventoryRecordMapper;
	}

	@Override
	public InventoryRecord createInventoryRecord(InventoryRecord inventoryRecord) {
		return inventoryRecordMapper.mapToExternal(inventoryRecordDao.saveAndFlush(inventoryRecordMapper.mapToInternal(inventoryRecord)));
	}

	@Override
	public InventoryRecord updateInventoryRecord(InventoryRecord inventoryRecord) {
		//todo add validation
		return inventoryRecordMapper.mapToExternal(inventoryRecordDao.save(inventoryRecordMapper.mapToInternal(inventoryRecord)));
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
	public List<InventoryRecord> getAllCheckedInventoryRecords() {
		return inventoryRecordMapper.mapToExternal(inventoryRecordDao.findAllChecked());
	}

	@Override
	public List<InventoryRecord> getAllUncheckedInventoryRecords() {
		return inventoryRecordMapper.mapToExternal(inventoryRecordDao.findAllUnChecked());
	}
}
