package cz.siemens.inventory.mapper.impl;

import cz.siemens.inventory.entity.Department;
import cz.siemens.inventory.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

@Service
public class DepartmentMapperImpl implements DepartmentMapper {
	@Override
	public Department mapToInternal(cz.siemens.inventory.gen.model.Department object) {
		Department department = new Department();
		department.setId(object.getId());
		department.setName(object.getName());
		return department;
	}

	@Override
	public cz.siemens.inventory.gen.model.Department mapToExternal(Department object) {
		cz.siemens.inventory.gen.model.Department department = new cz.siemens.inventory.gen.model.Department();
		return department.id(object.getId()).name(object.getName());
	}
}
