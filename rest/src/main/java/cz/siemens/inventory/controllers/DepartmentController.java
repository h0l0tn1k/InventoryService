package cz.siemens.inventory.controllers;


import cz.siemens.inventory.facade.DepartmentFacade;
import cz.siemens.inventory.gen.api.DepartmentsApi;
import cz.siemens.inventory.gen.model.Department;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiUris.ROOT_URI)
public class DepartmentController extends BaseController implements DepartmentsApi {

    final static Logger logger = LoggerFactory.getLogger(CompanyOwnerController.class);
    private DepartmentFacade departmentFacade;

    @Autowired
    public DepartmentController(DepartmentFacade departmentFacade) {
        this.departmentFacade = departmentFacade;
    }

    @Override
    public ResponseEntity<List<Department>> getDepartments() {
        logger.info("getDepartments request received");

        ResponseEntity<List<Department>> result = ResponseEntity.ok(departmentFacade.getDepartments());

        logger.info("getDepartments request finished");

        return result;
    }

    @Override
    public ResponseEntity<Department> getDepartment(@PathVariable("departmentId") Long departmentId) {
        logger.info("getDepartment({}) request received", departmentId);

        ResponseEntity<Department> result = returnOptional(departmentFacade.getDepartment(departmentId));

        logger.info("getDepartment({}) request finished", departmentId);

        return result;
    }

    @Override
    public ResponseEntity<Department> createDepartment(@ApiParam(required = true) @Valid @RequestBody Department body) {
        logger.info("createDepartment({}) request received", body.toString());

        Department createdDepartment = departmentFacade.createDepartment(body);

        logger.info("createDepartment({}) request finished", createdDepartment.getId());

        return returnCreatedResponse(createdDepartment, createdDepartment.getId().toString());
    }

    @Override
    public ResponseEntity<Department> updateDepartment(@ApiParam(required = true) @PathVariable("departmentId") Long departmentId,
                                                       @ApiParam(required = true) @Valid @RequestBody Department body) {
        logger.info("updateDepartment({}, {}) request received", departmentId, body.toString());

        Department updatedDepartment = departmentFacade.updateDepartment(body);

        logger.info("updateDepartment({}, {}) request finished", departmentId, updatedDepartment.toString());

        return ResponseEntity.ok(updatedDepartment);
    }

    @Override
    public ResponseEntity<Void> deleteDepartment(@ApiParam(required = true) @PathVariable("departmentId") Long departmentId) {
        logger.info("deleteDepartment({}) request received", departmentId);

        departmentFacade.deleteDepartment(departmentId);

        logger.info("deleteDepartment({}) request finished", departmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}