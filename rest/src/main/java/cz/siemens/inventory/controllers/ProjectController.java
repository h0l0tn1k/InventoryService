package cz.siemens.inventory.controllers;


import cz.siemens.inventory.facade.ProjectFacade;
import cz.siemens.inventory.gen.api.ProjectsApi;
import cz.siemens.inventory.gen.model.Project;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiUris.ROOT_URI)
public class ProjectController extends BaseController implements ProjectsApi {

    final static Logger logger = LoggerFactory.getLogger(ProjectController.class);
    private ProjectFacade projectsFacade;

    @Autowired
    public ProjectController(ProjectFacade projectsFacade) {
        this.projectsFacade = projectsFacade;
    }

    @Override
    public ResponseEntity<List<Project>> getProjects() {
        logger.info("getProjects request received");

        ResponseEntity<List<Project>> result = ResponseEntity.ok(projectsFacade.getProjects());

        logger.info("getProjects request finished");

        return result;
    }

    @Override
    public ResponseEntity<Project> getProject(@PathVariable("projectId") Long projectId) {
        logger.info("getProject({}) request received", projectId);

        ResponseEntity<Project> result = returnOptional(projectsFacade.getProject(projectId));

        logger.info("getProject({}) request finished", projectId);

        return result;
    }

    @Override
    public ResponseEntity<Project> createProject(@ApiParam(required = true) @Valid @RequestBody Project body) {
        logger.info("createProject({}) request received", body.toString());

        Project createdProject = projectsFacade.createProject(body);

        logger.info("createProject({}) request finished", createdProject.getId());

        return returnCreatedResponse(createdProject, createdProject.getId().toString());
    }

    @Override
    public ResponseEntity<Project> updateProject(@ApiParam(required = true) @PathVariable("projectId") Long projectId,
                                                 @ApiParam(required = true) @Valid @RequestBody Project body) {
        logger.info("updateProject({}, {}) request received", projectId, body.toString());

        Project updatedProject = projectsFacade.createProject(body);

        logger.info("updateProject({}, {}) request finished", projectId, updatedProject.toString());

        return ResponseEntity.ok(updatedProject);
    }

    @Override
    public ResponseEntity<Void> deleteProject(@ApiParam(required = true) @PathVariable("projectId") Long projectId) {
        logger.info("deleteProject({}) request received", projectId);

        projectsFacade.deleteProject(projectId);

        logger.info("deleteProject({}) request finished", projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}