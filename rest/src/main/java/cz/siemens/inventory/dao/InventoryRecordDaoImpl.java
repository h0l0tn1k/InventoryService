package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.InventoryRecord;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class InventoryRecordDaoImpl extends GenericDao<InventoryRecord> {

    public InventoryRecordDaoImpl() {
        super(InventoryRecord.class);
    }
}
