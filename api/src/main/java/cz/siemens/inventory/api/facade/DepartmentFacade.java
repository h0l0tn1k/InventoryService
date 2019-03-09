package cz.siemens.inventory.api.facade;

import cz.siemens.inventory.api.gen.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentFacade {

	List<Department> getDepartments();

	Optional<Department> getDepartment(long departmentId);

	Department createDepartment(Department department);

	Department updateDepartment(Department department);

	void deleteDepartment(long departmentId);
}
