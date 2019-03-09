package cz.siemens.inventory.facade;

import cz.siemens.inventory.api.facade.DepartmentFacade;
import cz.siemens.inventory.api.gen.model.Department;
import cz.siemens.inventory.dao.DepartmentDao;
import cz.siemens.inventory.api.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepartmentFacadeImpl implements DepartmentFacade {

	private DepartmentDao departmentDao;
	private DepartmentMapper departmentMapper;

	@Autowired
	public DepartmentFacadeImpl(DepartmentDao departmentDao, DepartmentMapper departmentMapper) {
		this.departmentDao = departmentDao;
		this.departmentMapper = departmentMapper;
	}

	@Override
	public List<Department> getDepartments() {
		return departmentMapper.mapToExternal(departmentDao.findAll());
	}

	@Override
	public Optional<Department> getDepartment(long departmentId) {
		return departmentMapper.mapToExternal(departmentDao.findById(departmentId));
	}

	@Override
	public Department createDepartment(Department department) {
		//todo: add validatians
		return departmentMapper.mapToExternal(departmentDao.save(departmentMapper.mapToInternal(department)));
	}

	@Override
	public Department updateDepartment(Department department) {
		//todo: add validatians
		return departmentMapper.mapToExternal(departmentDao.save(departmentMapper.mapToInternal(department)));
	}

	@Override
	public void deleteDepartment(long departmentId) {
		departmentDao.deleteById(departmentId);
	}
}
