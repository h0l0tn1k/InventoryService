package cz.siemens.inventory.facade;

import cz.siemens.inventory.gen.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentFacade {

	List<Department> getDepartments();

	Optional<Department> getDepartment(long departmentId);

	Department createDepartment(Department department);

	void deleteDepartment(long departmentId);
}
