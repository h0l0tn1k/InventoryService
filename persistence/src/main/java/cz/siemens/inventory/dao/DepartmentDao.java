package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentDao extends JpaRepository<Department, Long> {
}
