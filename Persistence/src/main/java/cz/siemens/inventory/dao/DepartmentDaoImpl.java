package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.Department;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class DepartmentDaoImpl extends GenericDao<Department> {

    public DepartmentDaoImpl() {
        super(Department.class);
    }
}
