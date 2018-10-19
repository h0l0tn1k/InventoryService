package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.InventoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryRecordDao extends JpaRepository<InventoryRecord, Long> {

}
