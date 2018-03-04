package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.CompanyOwner;
import org.springframework.data.repository.CrudRepository;

public interface CompanyDaoGen extends CrudRepository<CompanyOwner, Long> {

}
