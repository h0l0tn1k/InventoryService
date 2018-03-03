package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.InventoryRecord;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryRecordDaoImpl extends GenericDao<InventoryRecord> {

    public InventoryRecordDaoImpl() {
        super(InventoryRecord.class);
    }
}
