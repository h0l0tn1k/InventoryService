package cz.siemens.inventory.facade.impl;

import cz.siemens.inventory.dao.InventoryRecordDao;
import cz.siemens.inventory.facade.InventoryRecordFacade;
import cz.siemens.inventory.gen.model.InventoryRecord;
import cz.siemens.inventory.mapper.InventoryRecordMapper;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
