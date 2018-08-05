/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package cz.siemens.inventory.gen.api;

import cz.siemens.inventory.gen.model.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Api(value = "projects", description = "the projects API")
public interface ProjectsApi {

    Logger log = LoggerFactory.getLogger(ProjectsApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @ApiOperation(value = "Creates new Project", nickname = "createProject", notes = "", response = Project.class, tags={ "Project", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Project created", response = Project.class),
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/projects",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<Project> createProject(@ApiParam(value = "Project object that needs to be created" ,required=true )  @Valid @RequestBody Project body) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"name\" : \"name\",  \"id\" : 0}", Project.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default ProjectsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Deletes an Project", nickname = "deleteProject", notes = "", tags={ "Project", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Project was deleted."),
        @ApiResponse(code = 404, message = "Specified Project does not exist.") })
    @RequestMapping(value = "/projects/{projectId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteProject(@ApiParam(value = "Project's id",required=true) @PathVariable("projectId") Long projectId) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default ProjectsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Gets Project based on projectId", nickname = "getProject", notes = "", response = Project.class, tags={ "Project", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The Project", response = Project.class),
        @ApiResponse(code = 404, message = "Requested Project does not exist.") })
    @RequestMapping(value = "/projects/{projectId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<Project> getProject(@ApiParam(value = "Project's id",required=true) @PathVariable("projectId") Long projectId) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"name\" : \"name\",  \"id\" : 0}", Project.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default ProjectsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    @ApiOperation(value = "Gets all Projects", nickname = "getProjects", notes = "", response = Project.class, responseContainer = "List", tags={ "Project", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "All Projects", response = Project.class, responseContainer = "List") })
    @RequestMapping(value = "/projects",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<Project>> getProjects() {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("[ {  \"name\" : \"name\",  \"id\" : 0}, {  \"name\" : \"name\",  \"id\" : 0} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default ProjectsApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
