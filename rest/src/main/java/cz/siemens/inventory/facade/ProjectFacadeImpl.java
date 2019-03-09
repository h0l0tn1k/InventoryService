package cz.siemens.inventory.facade;

import cz.siemens.inventory.api.facade.ProjectFacade;
import cz.siemens.inventory.api.gen.model.Project;
import cz.siemens.inventory.dao.ProjectsDao;
import cz.siemens.inventory.api.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectFacadeImpl implements ProjectFacade {

	private ProjectsDao projectsDao;
	private ProjectMapper projectMapper;

	@Autowired
	public ProjectFacadeImpl(ProjectsDao projectsDao, ProjectMapper projectMapper) {
		this.projectsDao = projectsDao;
		this.projectMapper = projectMapper;
	}

	@Override
	public List<Project> getProjects() {
		return projectMapper.mapToExternal(projectsDao.findAll());
	}

	@Override
	public Optional<Project> getProject(long projectId) {
		return projectMapper.mapToExternal(projectsDao.findById(projectId));
	}

	@Override
	public Project createProject(Project project) {
		//todo: add validations
		return projectMapper.mapToExternal(projectsDao.save(projectMapper.mapToInternal(project)));
	}

	@Override
	public Project updateProject(Project project) {
		//todo: add validations
		return projectMapper.mapToExternal(projectsDao.save(projectMapper.mapToInternal(project)));
	}

	@Override
	public void deleteProject(long projectId) {
		projectsDao.deleteById(projectId);
	}
}
