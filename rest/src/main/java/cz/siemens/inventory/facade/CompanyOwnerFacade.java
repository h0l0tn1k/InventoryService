package cz.siemens.inventory.facade;

import cz.siemens.inventory.gen.model.CompanyOwner;

import java.util.List;
import java.util.Optional;

public interface CompanyOwnerFacade {

	List<CompanyOwner> getCompanyOwners();

	Optional<CompanyOwner> getCompanyOwner(long companyOwnerId);

	CompanyOwner createCompanyOwner(CompanyOwner companyOwner);

	void deleteCompanyOwner(long companyOwnerId);
}
