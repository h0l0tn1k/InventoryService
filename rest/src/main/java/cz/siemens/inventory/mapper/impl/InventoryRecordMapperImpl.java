package cz.siemens.inventory.mapper.impl;

import cz.siemens.inventory.entity.InventoryRecord;
import cz.siemens.inventory.entity.custom.InventoryState;
import cz.siemens.inventory.mapper.InventoryRecordMapper;
import org.springframework.stereotype.Service;

@Service
public class InventoryRecordMapperImpl implements InventoryRecordMapper {
	@Override
	public InventoryRecord mapToInternal(cz.siemens.inventory.gen.model.InventoryRecord object) {
		if(object == null) {
			return null;
		}
		InventoryRecord inventoryRecord = new InventoryRecord();
		inventoryRecord.setId(object.getId());
		inventoryRecord.setRegistered(InventoryState.fromValue(object.getInventoryState().toString()));
		inventoryRecord.setComment(object.getComment());
		return inventoryRecord;
	}

	@Override
	public cz.siemens.inventory.gen.model.InventoryRecord mapToExternal(InventoryRecord object) {
		if(object == null) {
			return null;
		}
		cz.siemens.inventory.gen.model.InventoryRecord inventoryRecord = new cz.siemens.inventory.gen.model.InventoryRecord();
		return inventoryRecord.id(object.getId())
				.inventoryState(cz.siemens.inventory.gen.model.InventoryState.fromValue(object.getRegistered().toString()))
				.comment(object.getComment());
	}
}
