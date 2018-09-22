package cz.siemens.inventory.controllers;


import cz.siemens.inventory.facade.UserFacade;
import cz.siemens.inventory.gen.api.UsersApi;
import cz.siemens.inventory.gen.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiUris.ROOT_URI)
public class UserController extends BaseController implements UsersApi {

	final static Logger logger = LoggerFactory.getLogger(UserController.class);
	private UserFacade userFacade;

	@Autowired
	public UserController(UserFacade userFacade) {
		this.userFacade = userFacade;
	}

	@Override
	public ResponseEntity<List<User>> getUsers() {
		logger.info("getUsers request received");

		ResponseEntity<List<User>> users = ResponseEntity.ok(userFacade.getUsers());

		logger.info("getUsers request finished");

		return users;
	}

	@Override
	public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {
		logger.info("getUser({}) request received", userId);

		ResponseEntity<User> result = returnOptional(userFacade.getUser(userId));

		logger.info("getUser({}) request finished", userId);

		return result;
	}
}