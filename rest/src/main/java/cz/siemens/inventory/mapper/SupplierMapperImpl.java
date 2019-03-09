package cz.siemens.inventory.mapper;

import cz.siemens.inventory.entity.Supplier;
import cz.siemens.inventory.api.mapper.SupplierMapper;
import org.springframework.stereotype.Service;

@Service
public class SupplierMapperImpl implements SupplierMapper {
	@Override
	public Supplier mapToInternal(cz.siemens.inventory.api.gen.model.Supplier object) {
		if (object == null) {
			return null;
		}
		Supplier supplier = new Supplier();
		supplier.setId(object.getId());
		supplier.setName(object.getName());
		return supplier;
	}

	@Override
	public cz.siemens.inventory.api.gen.model.Supplier mapToExternal(Supplier object) {
		if (object == null) {
			return null;
		}
		cz.siemens.inventory.api.gen.model.Supplier supplier = new cz.siemens.inventory.api.gen.model.Supplier();
		return supplier.id(object.getId()).name(object.getName());
	}
}
