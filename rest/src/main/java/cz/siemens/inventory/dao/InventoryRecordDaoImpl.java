package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.InventoryRecord;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class InventoryRecordDaoImpl extends GenericDao<InventoryRecord> {

    public InventoryRecordDaoImpl() {
        super(InventoryRecord.class);
    }

    public List<InventoryRecord> findAllChecked() {
        return em.createQuery("SELECT ir FROM InventoryRecord ir WHERE ir.registered = TRUE", InventoryRecord.class)
                .getResultList();
    }

    public List<InventoryRecord> findAllUnChecked() {
        return em.createQuery("SELECT ir FROM InventoryRecord ir WHERE ir.registered = FALSE", InventoryRecord.class)
                .getResultList();
    }
}
