package cz.siemens.inventory.facade;

import cz.siemens.inventory.gen.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectFacade {

	List<Project> getProjects();

	Optional<Project> getProject(long projectId);

	Project createProject(Project project);

	void deleteProject(long projectId);
}
