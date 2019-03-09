package cz.siemens.inventory.api.facade;

import cz.siemens.inventory.api.gen.model.User;

import java.util.List;
import java.util.Optional;

public interface UserFacade {

	List<User> getUsers();

	Optional<User> getUser(long userId);

	Optional<User> getUserByEmail(String email);
}
