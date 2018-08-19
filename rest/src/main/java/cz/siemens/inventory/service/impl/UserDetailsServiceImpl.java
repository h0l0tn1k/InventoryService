package cz.siemens.inventory.service.impl;

import cz.siemens.inventory.dao.LoginUserScdDao;
import cz.siemens.inventory.entity.LoginUserScd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

	private LoginUserScdDao loginUserScdDao;

	@Autowired
	public UserDetailsServiceImpl(LoginUserScdDao loginUserScdDao) {
		this.loginUserScdDao = loginUserScdDao;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		LoginUserScd user = loginUserScdDao.getByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User with email " + email + " not found");
		}
		return new User(user.getEmail(), user.getGid(), getGrantedAuthorities(user));
	}

	private Collection<GrantedAuthority> getGrantedAuthorities(LoginUserScd user) {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		if (user.isFlagAdmin()) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return grantedAuthorities;
	}
}
