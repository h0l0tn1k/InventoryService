package cz.siemens.inventory.facade.impl;

import cz.siemens.inventory.dao.LoginUserScdDao;
import cz.siemens.inventory.facade.UserFacade;
import cz.siemens.inventory.gen.model.User;
import cz.siemens.inventory.mapper.UserMapper;
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
}
