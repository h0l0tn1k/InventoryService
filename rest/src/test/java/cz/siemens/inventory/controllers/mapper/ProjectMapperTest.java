package cz.siemens.inventory.controllers.mapper;

import cz.siemens.inventory.entity.Project;
import cz.siemens.inventory.mapper.ProjectMapper;
import cz.siemens.inventory.mapper.impl.ProjectMapperImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectMapperTest {

	private ProjectMapper cut = new ProjectMapperImpl();

	@Before
	public void setup() {

	}

	@Test
	public void test_emptyList_mapToInternal() {
		List<Project> projects = cut.mapToInternal(new ArrayList<>());
		assertThat(projects).isEmpty();
	}

	@Test
	public void test_nullProject_mapToInternal() {
		cz.siemens.inventory.gen.model.Project nullProject = null;
		Project project = cut.mapToInternal(nullProject);
		assertThat(project).isNull();
	}

	@Test
	public void test_Project_mapToInternal() {
		cz.siemens.inventory.gen.model.Project expectedProject = getProject(1L, "Company owner");
		Project actualProject = cut.mapToInternal(expectedProject);
		assertThatProjectsAreEqual(actualProject, expectedProject);
	}

	@Test
	public void test_nullProject_mapToExternal() {
		Project nullProject = null;
		cz.siemens.inventory.gen.model.Project project = cut.mapToExternal(nullProject);
		assertThat(project).isNull();
	}

	@Test
	public void test_Project_mapToExternal() {
		Project expectedProject = getProjectInternal(1L, "Company owner");
		cz.siemens.inventory.gen.model.Project actualProject = cut.mapToExternal(expectedProject);
		assertThatProjectsAreEqual(actualProject, expectedProject);
	}

	@Test
	public void test_ProjectList_mapToInternal() {
		List<cz.siemens.inventory.gen.model.Project> expectedProjects = new ArrayList<>();
		expectedProjects.add(getProject(1L, "Project 1"));
		expectedProjects.add(getProject(2L, "Project 2"));
		List<Project> actualCompanyOwners = cut.mapToInternal(expectedProjects);
		assertThatProjectsAreEqual(actualCompanyOwners.get(0), expectedProjects.get(0));
		assertThatProjectsAreEqual(actualCompanyOwners.get(1), expectedProjects.get(1));
	}

	private cz.siemens.inventory.gen.model.Project getProject(Long id, String name) {
		return new cz.siemens.inventory.gen.model.Project().id(id).name(name);
	}

	private Project getProjectInternal(Long id, String name) {
		Project project = new Project();
		project.setId(id);
		project.setName(name);
		return project;
	}

	private void assertThatProjectsAreEqual(Project actualProject, cz.siemens.inventory.gen.model.Project expectedProject) {
		assertThat(actualProject.getId()).isEqualTo(expectedProject.getId());
		assertThat(actualProject.getName()).isEqualTo(expectedProject.getName());
	}

	private void assertThatProjectsAreEqual(cz.siemens.inventory.gen.model.Project actualProject, Project expectedProject) {
		assertThat(actualProject.getId()).isEqualTo(expectedProject.getId());
		assertThat(actualProject.getName()).isEqualTo(expectedProject.getName());
	}
}
