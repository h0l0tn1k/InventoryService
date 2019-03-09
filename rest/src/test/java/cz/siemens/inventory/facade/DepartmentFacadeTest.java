package cz.siemens.inventory.facade;

import cz.siemens.inventory.api.facade.DepartmentFacade;
import cz.siemens.inventory.dao.DepartmentDao;
import cz.siemens.inventory.entity.Department;
import cz.siemens.inventory.api.mapper.DepartmentMapper;
import cz.siemens.inventory.mapper.DepartmentMapperImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class DepartmentFacadeTest {

	private DepartmentFacade cut;
	private DepartmentDao departmentDao;
	private DepartmentMapper departmentMapper;
	private cz.siemens.inventory.api.gen.model.Department expectedDepartmentExternal = getExternalDepartment(1L,"Department D1");

	@Before
	public void setup() {
		departmentDao = Mockito.mock(DepartmentDao.class);
		departmentMapper = new DepartmentMapperImpl();
		cut = new DepartmentFacadeImpl(departmentDao, departmentMapper);
		Department departmentInternal = new Department();
		departmentInternal.setId(expectedDepartmentExternal.getId());
		departmentInternal.setName(expectedDepartmentExternal.getName());

		doReturn(Optional.of(departmentInternal)).when(departmentDao).findById(1L);
		doReturn(new ArrayList<Department>()).when(departmentDao).findAll();
	}

	@Test
	public void getDepartments_returnsList() {
		List<cz.siemens.inventory.api.gen.model.Department> departments = cut.getDepartments();

		assertThat(departments).isEmpty();
	}

	@Test
	public void getDepartment_byId_returnsOwner() {
		Optional<cz.siemens.inventory.api.gen.model.Department> optionalDepartment = cut.getDepartment(1L);

		assertThat(optionalDepartment.isPresent()).isTrue();
		assertThat(optionalDepartment).isEqualTo(Optional.of(expectedDepartmentExternal));
	}

	@Test
	public void createDepartment_createsOwner() {
		Department expectedDepartment = new Department();
		expectedDepartment.setId(4L);
		expectedDepartment.setName("Company");

		doReturn(expectedDepartment).when(departmentDao).save(expectedDepartment);

		cz.siemens.inventory.api.gen.model.Department createdDepartment = cut.createDepartment(departmentMapper.mapToExternal(expectedDepartment));

		assertThat(createdDepartment).isNotNull();
	}

	@Test
	public void updateDepartment_updatesOwner() {
		Department expectedDepartment = new Department();
		expectedDepartment.setId(4L);
		expectedDepartment.setName("Department 1");

		doReturn(expectedDepartment).when(departmentDao).save(expectedDepartment);

		cz.siemens.inventory.api.gen.model.Department updatedDepartment = cut.updateDepartment(
				departmentMapper.mapToExternal(expectedDepartment));

		verify(departmentDao).save(expectedDepartment);

		assertThat(updatedDepartment).isEqualTo(departmentMapper.mapToExternal(expectedDepartment));
	}

	@Test
	public void deleteDepartment_deletesOwner() {
		Long id = 3L;
		cut.deleteDepartment(id);

		verify(departmentDao).deleteById(id);
	}

	private cz.siemens.inventory.api.gen.model.Department getExternalDepartment(Long id, String name) {
		return new cz.siemens.inventory.api.gen.model.Department().id(id).name(name);
	}
}
