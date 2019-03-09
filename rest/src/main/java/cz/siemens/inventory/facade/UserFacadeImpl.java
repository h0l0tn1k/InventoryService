package cz.siemens.inventory.facade;

import cz.siemens.inventory.api.facade.UserFacade;
import cz.siemens.inventory.api.gen.model.User;
import cz.siemens.inventory.dao.LoginUserScdDao;
import cz.siemens.inventory.api.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

	private LoginUserScdDao loginUserScdDao;
	private UserMapper userMapper;

	@Autowired
	public UserFacadeImpl(LoginUserScdDao loginUserScdDao, UserMapper userMapper) {
		this.loginUserScdDao = loginUserScdDao;
		this.userMapper = userMapper;
	}

	@Override
	public List<User> getUsers() {
		return userMapper.mapToExternal(loginUserScdDao.findAll());
	}

	@Override
	public Optional<User> getUser(long userId) {
		return userMapper.mapToExternal(loginUserScdDao.findById(userId));
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		return Optional.of(userMapper.mapToExternal(loginUserScdDao.getByEmail(email)));
	}
}
