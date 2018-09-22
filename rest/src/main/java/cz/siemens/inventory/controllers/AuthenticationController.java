package cz.siemens.inventory.controllers;

import cz.siemens.inventory.controllers.exceptions.UnauthorizedException;
import cz.siemens.inventory.dao.LoginUserScdDao;
import cz.siemens.inventory.entity.LoginUserScd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(ApiUris.ROOT_URI)
public class AuthenticationController {

    private LoginUserScdDao userDao;
    final static Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    public AuthenticationController(LoginUserScdDao userDao) {
        this.userDao = userDao;
    }

    //TODO: NOT SAFE, JUST FOR TESTING
    @RequestMapping(value="/login/{email}/{password}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final LoginUserScd findById(@PathVariable("email") String email,
									   @PathVariable("password") String password) throws Exception {
        try {
            return userDao.authenticate(email, password);
        } catch(Exception ex) {
            throw new UnauthorizedException();
        }
    }
}