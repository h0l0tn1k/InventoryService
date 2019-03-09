package cz.siemens.inventory.api.facade;

import cz.siemens.inventory.api.gen.model.CompanyOwner;

import java.util.List;
import java.util.Optional;

public interface CompanyOwnerFacade {

	List<CompanyOwner> getCompanyOwners();

	Optional<CompanyOwner> getCompanyOwner(long companyOwnerId);

	CompanyOwner createCompanyOwner(CompanyOwner companyOwner);

	CompanyOwner updateCompanyOwner(CompanyOwner companyOwner);

	void deleteCompanyOwner(long companyOwnerId);
}
