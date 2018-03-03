package cz.siemens.inventory.rest.controllers;

import cz.siemens.inventory.dao.GenericDao;
import cz.siemens.inventory.entity.User;
import cz.siemens.inventory.rest.ApiUris;
import cz.siemens.inventory.rest.exceptions.ResourceAlreadyExistsException;
import cz.siemens.inventory.rest.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUris.ROOT_URI_USERS)
public class UserController {

    //final static Logger logger = LoggerFactory.getLogger(SupplierController.class);

    @Autowired
    private GenericDao<User> userDao;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<User> findAll(){
        return userDao.readAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final User findById(@PathVariable("id") Long id) throws Exception {
        try {
            return userDao.read(id);
        } catch(Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/create", method= RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void create(@RequestBody User user) throws Exception {
        try {
            userDao.create(user);
        } catch(Exception ex) {
            throw new ResourceAlreadyExistsException();
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void remove(@PathVariable("id") Long id) throws Exception {
        try {
            userDao.delete(userDao.read(id));
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
}
