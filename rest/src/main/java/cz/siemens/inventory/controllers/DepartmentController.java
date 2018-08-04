package cz.siemens.inventory.controllers;

import cz.siemens.inventory.dao.GenericDao;
import cz.siemens.inventory.entity.Department;
import cz.siemens.inventory.controllers.exceptions.ResourceAlreadyExistsException;
import cz.siemens.inventory.controllers.exceptions.ResourceNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUris.ROOT_URI_DEPARTMENT)
public class DepartmentController {

    private GenericDao<Department> departmentDao;
    final static Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    public DepartmentController(GenericDao<Department> departmentDao) {
        this.departmentDao = departmentDao;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<Department> findAll(){
        return departmentDao.readAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Department findById(@PathVariable("id") Long id) throws Exception {
        logger.info("findById({id}) called", id);

        try {
            return departmentDao.read(id);
        }catch(ObjectNotFoundException ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/create", method= RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void create(@RequestBody Department department) throws Exception {
        logger.info("create({department}) called", department.toString());

        try {
            departmentDao.create(department);
        } catch(Exception ex) {
            throw new ResourceAlreadyExistsException();
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void remove(@PathVariable("id") Long id) throws Exception {
        logger.info("remove({id}) called", id);
        try {
            departmentDao.delete(departmentDao.read(id));
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
}
