package cz.siemens.inventory.api.facade;

import cz.siemens.inventory.api.gen.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectFacade {

	List<Project> getProjects();

	Optional<Project> getProject(long projectId);

	Project createProject(Project project);

	Project updateProject(Project project);

	void deleteProject(long projectId);
}
