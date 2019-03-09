package cz.siemens.inventory.facade;

import cz.siemens.inventory.api.facade.CompanyOwnerFacade;
import cz.siemens.inventory.api.gen.model.CompanyOwner;
import cz.siemens.inventory.dao.CompanyOwnerDao;
import cz.siemens.inventory.api.mapper.CompanyOwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyOwnerFacadeImpl implements CompanyOwnerFacade {

	private CompanyOwnerMapper companyOwnerMapper;
	private CompanyOwnerDao companyOwnerDao;

	@Autowired
	public CompanyOwnerFacadeImpl(CompanyOwnerDao companyOwnerDao, CompanyOwnerMapper companyOwnerMapper) {
		this.companyOwnerDao = companyOwnerDao;
		this.companyOwnerMapper = companyOwnerMapper;
	}

	@Override
	public List<CompanyOwner> getCompanyOwners() {
		return companyOwnerMapper.mapToExternal(companyOwnerDao.findAll());
	}

	@Override
	public Optional<CompanyOwner> getCompanyOwner(long companyOwnerId) {
		return companyOwnerMapper.mapToExternal(companyOwnerDao.findById(companyOwnerId));
	}

	@Override
	public CompanyOwner createCompanyOwner(CompanyOwner companyOwner) {
		//todo: add validation
		return companyOwnerMapper.mapToExternal(companyOwnerDao.save(companyOwnerMapper.mapToInternal(companyOwner)));
	}

	@Override
	public CompanyOwner updateCompanyOwner(CompanyOwner companyOwner) {
		//todo: add validation
		return companyOwnerMapper.mapToExternal(companyOwnerDao.save(companyOwnerMapper.mapToInternal(companyOwner)));
	}

	@Override
	public void deleteCompanyOwner(long companyOwnerId) {
		companyOwnerDao.deleteById(companyOwnerId);
	}
}