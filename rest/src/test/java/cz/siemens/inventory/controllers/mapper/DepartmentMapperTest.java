package cz.siemens.inventory.controllers.mapper;

import cz.siemens.inventory.entity.Department;
import cz.siemens.inventory.mapper.DepartmentMapper;
import cz.siemens.inventory.mapper.impl.DepartmentMapperImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DepartmentMapperTest {

	private DepartmentMapper cut = new DepartmentMapperImpl();

	@Before
	public void setup() {

	}

	@Test
	public void test_emptyList_mapToInternal() {
		List<Department> departments = cut.mapToInternal(new ArrayList<>());
		assertThat(departments).isEmpty();
	}

	@Test
	public void test_nullDepartment_mapToInternal() {
		cz.siemens.inventory.gen.model.Department nullDepartment = null;
		Department department = cut.mapToInternal(nullDepartment);
		assertThat(department).isNull();
	}

	@Test
	public void test_Department_mapToInternal() {
		cz.siemens.inventory.gen.model.Department expectedDepartment = getDepartment(1L, "Department 1");
		Department actualDepartment = cut.mapToInternal(expectedDepartment);
		assertThatDepartmentsAreEqual(actualDepartment, expectedDepartment);
	}

	@Test
	public void test_nullDepartment_mapToExternal() {
		Department nullDepartment = null;
		cz.siemens.inventory.gen.model.Department department = cut.mapToExternal(nullDepartment);
		assertThat(department).isNull();
	}

	@Test
	public void test_Department_mapToExternal() {
		Department expectedDepartment = getDepartmentInternal(1L, "Department 1");
		cz.siemens.inventory.gen.model.Department actualDepartment = cut.mapToExternal(expectedDepartment);
		assertThatDepartmentsAreEqual(actualDepartment, expectedDepartment);
	}

	@Test
	public void test_CompanyOwnerList_mapToInternal() {
		List<cz.siemens.inventory.gen.model.Department> expectedDepartments = new ArrayList<>();
		expectedDepartments.add(getDepartment(1L, "Department 1"));
		expectedDepartments.add(getDepartment(2L, "Department 2"));
		List<Department> actualCompanyOwners = cut.mapToInternal(expectedDepartments);
		assertThatDepartmentsAreEqual(actualCompanyOwners.get(0), expectedDepartments.get(0));
		assertThatDepartmentsAreEqual(actualCompanyOwners.get(1), expectedDepartments.get(1));
	}

	private cz.siemens.inventory.gen.model.Department getDepartment(Long id, String name) {
		return new cz.siemens.inventory.gen.model.Department().id(id).name(name);
	}

	private Department getDepartmentInternal(Long id, String name) {
		Department department = new Department();
		department.setId(id);
		department.setName(name);
		return department;
	}

	private void assertThatDepartmentsAreEqual(Department actualDepartment, cz.siemens.inventory.gen.model.Department expectedDepartment) {
		assertThat(actualDepartment.getId()).isEqualTo(expectedDepartment.getId());
		assertThat(actualDepartment.getName()).isEqualTo(expectedDepartment.getName());

	}

	private void assertThatDepartmentsAreEqual(cz.siemens.inventory.gen.model.Department actualDepartment, Department expectedDepartment) {
		assertThat(actualDepartment.getId()).isEqualTo(expectedDepartment.getId());
		assertThat(actualDepartment.getName()).isEqualTo(expectedDepartment.getName());

	}
}
