package cz.siemens.inventory.facade;

import cz.siemens.inventory.api.facade.ProjectFacade;
import cz.siemens.inventory.api.mapper.ProjectMapper;
import cz.siemens.inventory.dao.ProjectsDao;
import cz.siemens.inventory.entity.Project;
import cz.siemens.inventory.mapper.ProjectMapperImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class ProjectFacadeTest {

	private ProjectFacade cut;
	private ProjectsDao projectDao;
	private ProjectMapper projectMapper;
	private Project projectInternal;

	@Before
	public void setup() {
		projectDao = Mockito.mock(ProjectsDao.class);
		projectMapper = new ProjectMapperImpl();
		cut = new ProjectFacadeImpl(projectDao, projectMapper);
		projectInternal = new Project();
		projectInternal.setId(4L);
		projectInternal.setName("Project");

		doReturn(Optional.of(projectInternal)).when(projectDao).findById(1L);
		doReturn(new ArrayList<Project>()).when(projectDao).findAll();
	}

	@Test
	public void getProjects_returnsList() {
		List<cz.siemens.inventory.api.gen.model.Project> Projects = cut.getProjects();

		assertThat(Projects).isEmpty();
	}

	@Test
	public void getProject_byId_returnsProject() {
		Optional<cz.siemens.inventory.api.gen.model.Project> optionalProject = cut.getProject(1L);

		assertThat(optionalProject.isPresent()).isTrue();
		assertThat(optionalProject).isEqualTo(Optional.of(projectMapper.mapToExternal(projectInternal)));
	}

	@Test
	public void createProject_createsProject() {

		doReturn(projectInternal).when(projectDao).save(projectInternal);

		cz.siemens.inventory.api.gen.model.Project createdProject = cut.createProject(projectMapper.mapToExternal(projectInternal));

		assertThat(createdProject).isNotNull();
	}

	@Test
	public void updateProject_updatesProject() {

		doReturn(projectInternal).when(projectDao).save(projectInternal);

		cz.siemens.inventory.api.gen.model.Project updatedProject = cut.updateProject(
				projectMapper.mapToExternal(projectInternal));

		verify(projectDao).save(projectInternal);

		assertThat(updatedProject).isEqualTo(projectMapper.mapToExternal(projectInternal));
	}

	@Test
	public void deleteProject_deletesProject() {
		Long id = 3L;
		cut.deleteProject(id);

		verify(projectDao).deleteById(id);
	}
}
