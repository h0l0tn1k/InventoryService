package cz.siemens.inventory.rest.controllers;

import cz.siemens.inventory.dao.GenericDao;
import cz.siemens.inventory.entity.ApplianceRevision;
import cz.siemens.inventory.rest.ApiUris;
import cz.siemens.inventory.rest.exceptions.InvalidParameterException;
import cz.siemens.inventory.rest.exceptions.ResourceAlreadyExistsException;
import cz.siemens.inventory.rest.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUris.ROOT_URI_APPLIANCE_REVISION)
public class ApplianceRevisionController {

    //final static Logger logger = LoggerFactory.getLogger(SupplierController.class);

    @Autowired
    private GenericDao<ApplianceRevision> applianceRevisionDao;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ApplianceRevision> findAll(){
        return applianceRevisionDao.readAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ApplianceRevision findById(@PathVariable("id") Long id) throws Exception {
        try {
            return applianceRevisionDao.read(id);
        } catch(Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/update", method= RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public final ApplianceRevision update(@RequestBody ApplianceRevision applianceRevision) throws Exception {
        try {
            applianceRevisionDao.update(applianceRevision);
            return applianceRevisionDao.read(applianceRevision.getId());
        }catch (Exception ex) {
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(value = "/create", method= RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void create(@RequestBody ApplianceRevision applianceRevision) throws Exception {
        try {
            applianceRevisionDao.create(applianceRevision);
        } catch(Exception ex) {
            throw new ResourceAlreadyExistsException();
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void remove(@PathVariable("id") Long id) throws Exception {
        try {
            applianceRevisionDao.delete(applianceRevisionDao.read(id));
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
}
