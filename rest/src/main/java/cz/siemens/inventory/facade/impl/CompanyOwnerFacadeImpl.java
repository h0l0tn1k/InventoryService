package cz.siemens.inventory.facade.impl;

import cz.siemens.inventory.dao.CompanyOwnerDao;
import cz.siemens.inventory.dao.DeviceDao;
import cz.siemens.inventory.facade.CompanyOwnerFacade;
import cz.siemens.inventory.facade.DeviceFacade;
import cz.siemens.inventory.gen.model.CompanyOwner;
import cz.siemens.inventory.gen.model.Device;
import cz.siemens.inventory.mapper.CompanyOwnerMapper;
import cz.siemens.inventory.mapper.DeviceMapper;
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
	public void deleteCompanyOwner(long companyOwnerId) {
		companyOwnerDao.deleteById(companyOwnerId);
	}
}