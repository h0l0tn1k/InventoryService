package cz.siemens.inventory.rest.controllers;

import cz.siemens.inventory.dao.UserScdDaoImpl;
import cz.siemens.inventory.entity.LoginUserScd;
import cz.siemens.inventory.entity.UserScd;
import cz.siemens.inventory.rest.ApiUris;
import cz.siemens.inventory.rest.exceptions.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(ApiUris.ROOT_URI_LOGIN)
public class AuthenticationController {

    private UserScdDaoImpl userDao;
    final static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    public AuthenticationController(UserScdDaoImpl userDao) {
        this.userDao = userDao;
    }

    //TODO: NOT SAFE, JUST FOR TESTING
    @RequestMapping(value="/{email}/{password}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserScd findById(@PathVariable("email") String email,
                                  @PathVariable("password") String password) throws Exception {
        try {
            return userDao.authenticate(email, password);
        } catch(Exception ex) {
            throw new UnauthorizedException();
        }
    }
}
