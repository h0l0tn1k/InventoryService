package cz.siemens.inventory.controllers;


import cz.siemens.inventory.facade.UserFacade;
import cz.siemens.inventory.gen.api.UsersApi;
import cz.siemens.inventory.gen.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
	public ResponseEntity<User> getCurrentUser() {
		logger.info("getCurrentUser() request received");

		String usersEmail = SecurityContextHolder.getContext().getAuthentication().getName();

		Optional<User> userByEmail = userFacade.getUserByEmail(usersEmail);

		logger.info("getCurrentUser() request finished");

		return returnOptional(userByEmail);
	}

	@Override
	public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {
		logger.info("getUser({}) request received", userId);

		ResponseEntity<User> result = returnOptional(userFacade.getUser(userId));

		logger.info("getUser({}) request finished", userId);

		return result;
	}
}