package cz.siemens.inventory.mapper;

import cz.siemens.inventory.api.mapper.CompanyOwnerMapper;
import cz.siemens.inventory.entity.CompanyOwner;
import org.springframework.stereotype.Service;

@Service
public class CompanyOwnerMapperImpl implements CompanyOwnerMapper {
	@Override
	public CompanyOwner mapToInternal(cz.siemens.inventory.api.gen.model.CompanyOwner object) {
		if (object == null) {
			return null;
		}
		CompanyOwner companyOwner = new CompanyOwner();
		companyOwner.setId(object.getId());
		companyOwner.setName(object.getName());
		return companyOwner;
	}

	@Override
	public cz.siemens.inventory.api.gen.model.CompanyOwner mapToExternal(CompanyOwner object) {
		if (object == null) {
			return null;
		}
		cz.siemens.inventory.api.gen.model.CompanyOwner companyOwner = new cz.siemens.inventory.api.gen.model.CompanyOwner();
		return companyOwner.id(object.getId()).name(object.getName());
	}
}
