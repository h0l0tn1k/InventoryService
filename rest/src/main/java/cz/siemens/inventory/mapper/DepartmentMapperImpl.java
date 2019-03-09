package cz.siemens.inventory.mapper;

import cz.siemens.inventory.api.mapper.DepartmentMapper;
import cz.siemens.inventory.entity.Department;
import org.springframework.stereotype.Service;

@Service
public class DepartmentMapperImpl implements DepartmentMapper {
	@Override
	public Department mapToInternal(cz.siemens.inventory.api.gen.model.Department object) {
		if (object == null) {
			return null;
		}
		Department department = new Department();
		department.setId(object.getId());
		department.setName(object.getName());
		return department;
	}

	@Override
	public cz.siemens.inventory.api.gen.model.Department mapToExternal(Department object) {
		if (object == null) {
			return null;
		}
		cz.siemens.inventory.api.gen.model.Department department = new cz.siemens.inventory.api.gen.model.Department();
		return department.id(object.getId()).name(object.getName());
	}
}
