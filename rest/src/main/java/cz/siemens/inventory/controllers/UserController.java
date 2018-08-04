package cz.siemens.inventory.controllers;

import cz.siemens.inventory.dao.GenericDao;
import cz.siemens.inventory.entity.User;
import cz.siemens.inventory.controllers.exceptions.ResourceAlreadyExistsException;
import cz.siemens.inventory.controllers.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUris.ROOT_URI_USERS)
public class UserController {

    private GenericDao<User> userDao;
    final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(GenericDao<User> userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<User> findAll(){
        logger.info("findAll()");
        return userDao.readAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final User findById(@PathVariable("id") Long id) throws Exception {
        logger.info("findById({id})", id);
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
        logger.info("create({user})", user.toString());
        try {
            userDao.create(user);
        } catch(Exception ex) {
            throw new ResourceAlreadyExistsException();
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void remove(@PathVariable("id") Long id) throws Exception {
        logger.info("remove({id})", id);
        try {
            userDao.delete(userDao.read(id));
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
}
