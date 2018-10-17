package cz.siemens.inventory.service.impl;

import cz.siemens.inventory.dao.LoginPasswordScdDao;
import cz.siemens.inventory.dao.LoginUserScdDao;
import cz.siemens.inventory.entity.LoginUserScd;
import cz.siemens.inventory.entity.UserPassword;
import org.apache.commons.lang3.StringUtils;
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
	private LoginPasswordScdDao loginPasswordScdDao;

	@Autowired
	public UserDetailsServiceImpl(LoginUserScdDao loginUserScdDao, LoginPasswordScdDao loginPasswordScdDao) {
		this.loginUserScdDao = loginUserScdDao;
		this.loginPasswordScdDao = loginPasswordScdDao;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		LoginUserScd user = loginUserScdDao.getByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User with email " + email + " not found");
		}

		UserPassword password = loginPasswordScdDao.getPassword(user.getEmail());
		String passwordValue = "";

		if (StringUtils.isNotBlank(password.getPasswordHash())) {
			passwordValue = password.getPasswordHash();
		} else {
			passwordValue = user.getGid();
		}

		return new User(user.getEmail(), passwordValue, getGrantedAuthorities(user));
	}

	private Collection<GrantedAuthority> getGrantedAuthorities(LoginUserScd user) {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		if (user.isFlagAdmin()) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		if (user.isFlagRead()) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_READ"));
		}
		if (user.isFlagWrite()) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_WRITE"));
		}
		if (user.isFlagBorrow()) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_BORROW"));
		}
		if (user.isFlagInventory()) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_INVENTORY"));
		}
		if (user.isFlagRevision()) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_REVISION"));
		}
		return grantedAuthorities;
	}
}
