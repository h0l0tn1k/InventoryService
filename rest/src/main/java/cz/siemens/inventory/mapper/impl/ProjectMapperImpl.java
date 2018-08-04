package cz.siemens.inventory.mapper.impl;

import cz.siemens.inventory.entity.Project;
import cz.siemens.inventory.entity.Supplier;
import cz.siemens.inventory.mapper.ProjectMapper;
import cz.siemens.inventory.mapper.SupplierMapper;
import org.springframework.stereotype.Service;

@Service
public class ProjectMapperImpl implements ProjectMapper {

	@Override
	public Project mapToInternal(cz.siemens.inventory.gen.model.Project object) {
		Project project = new Project();
		project.setId(object.getId());
		project.setName(object.getName());
		return project;
	}

	@Override
	public cz.siemens.inventory.gen.model.Project mapToExternal(Project object) {
		cz.siemens.inventory.gen.model.Project project = new cz.siemens.inventory.gen.model.Project();
		return project.id(object.getId()).name(object.getName());
	}
}
