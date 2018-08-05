package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierDao extends JpaRepository<Supplier, Long> {
}
