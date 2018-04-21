package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.UserPassword;

public class LoginPasswordScdDao extends GenericDao<UserPassword>
{

	public LoginPasswordScdDao()
	{
		super(UserPassword.class);
	}

	public UserPassword getPassword(String username)
	{
		return em.createQuery("SELECT up FROM UserPassword up where up.username LIKE :username", UserPassword.class).setParameter("username", username).getSingleResult();
	}
}
