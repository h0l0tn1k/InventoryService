package cz.siemens.inventory.mapper;

import cz.siemens.inventory.entity.Project;
import cz.siemens.inventory.api.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ProjectMapperImpl implements ProjectMapper {

	@Override
	public Project mapToInternal(cz.siemens.inventory.api.gen.model.Project object) {
		if (object == null) {
			return null;
		}
		Project project = new Project();
		project.setId(object.getId());
		project.setName(object.getName());
		return project;
	}

	@Override
	public cz.siemens.inventory.api.gen.model.Project mapToExternal(Project object) {
		if (object == null) {
			return null;
		}
		cz.siemens.inventory.api.gen.model.Project project = new cz.siemens.inventory.api.gen.model.Project();
		return project.id(object.getId()).name(object.getName());
	}
}
