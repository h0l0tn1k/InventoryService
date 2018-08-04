package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.CompanyOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyOwnerDao extends JpaRepository<CompanyOwner, Long> {
}
