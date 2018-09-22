package cz.siemens.inventory.facade;

import cz.siemens.inventory.gen.model.User;

import java.util.List;
import java.util.Optional;

public interface UserFacade {

	List<User> getUsers();

	Optional<User> getUser(long userId);
}
