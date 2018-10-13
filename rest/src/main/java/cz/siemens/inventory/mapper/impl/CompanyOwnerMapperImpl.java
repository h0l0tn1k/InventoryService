package cz.siemens.inventory.mapper.impl;

import cz.siemens.inventory.entity.CompanyOwner;
import cz.siemens.inventory.mapper.CompanyOwnerMapper;
import org.springframework.stereotype.Service;

@Service
public class CompanyOwnerMapperImpl implements CompanyOwnerMapper {
	@Override
	public CompanyOwner mapToInternal(cz.siemens.inventory.gen.model.CompanyOwner object) {
		if (object == null) {
			return null;
		}
		CompanyOwner companyOwner = new CompanyOwner();
		companyOwner.setId(object.getId());
		companyOwner.setName(object.getName());
		return companyOwner;
	}

	@Override
	public cz.siemens.inventory.gen.model.CompanyOwner mapToExternal(CompanyOwner object) {
		if (object == null) {
			return null;
		}
		cz.siemens.inventory.gen.model.CompanyOwner companyOwner = new cz.siemens.inventory.gen.model.CompanyOwner();
		return companyOwner.id(object.getId()).name(object.getName());
	}
}
