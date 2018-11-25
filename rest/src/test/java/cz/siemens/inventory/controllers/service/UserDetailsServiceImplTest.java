package cz.siemens.inventory.controllers.service;

import cz.siemens.inventory.dao.LoginPasswordScdDao;
import cz.siemens.inventory.dao.LoginUserScdDao;
import cz.siemens.inventory.entity.LoginUserScd;
import cz.siemens.inventory.entity.UserPassword;
import cz.siemens.inventory.service.impl.UserDetailsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

public class UserDetailsServiceImplTest {

	private UserDetailsService cut;
	private LoginUserScdDao userDao;
	private LoginPasswordScdDao passwordDao;
	private BCryptPasswordEncoder encoder;

	@Before
	public void setup() {
		encoder = new BCryptPasswordEncoder();
		userDao = Mockito.mock(LoginUserScdDao.class);
		passwordDao = Mockito.mock(LoginPasswordScdDao.class);
		cut = new UserDetailsServiceImpl(userDao, passwordDao);
	}

	@Test(expected = UsernameNotFoundException.class)
	public void loadUserByUsername_nonExistingUser() {
		doReturn(null).when(userDao).getByEmail(anyString());
		cut.loadUserByUsername("nonexistinguser");
	}

	@Test
	public void loadUserByUsername_withNonExistingPassword_usesGid() {
		LoginUserScd user = new LoginUserScd();
		user.setFlagRead(true);
		user.setFlagWrite(true);
		user.setFlagRevision(true);
		user.setEmail("test@email.com");
		user.setGid("123");
		doReturn(user).when(userDao).getByEmail(user.getEmail());
		doReturn(Optional.empty()).when(passwordDao).getPassword(user.getEmail());

		UserDetails userDetails = cut.loadUserByUsername(user.getEmail());

		assertThat(userDetails.getUsername()).isEqualTo(user.getEmail());
		assertThat(encoder.matches(user.getGid(), userDetails.getPassword())).isTrue();
		assertThat(userDetails.getAuthorities()).hasSize(3);
//		assertThat(userDetails.getAuthorities()).contains(new SimpleGrantedAuthority(ROLE_READ), new SimpleGrantedAuthority(ROLE_WRITE));
//		assertThat(userDetails.getAuthorities()).contains(new SimpleGrantedAuthority(ROLE_WRITE));
//		assertThat(userDetails.getAuthorities()).contains(new SimpleGrantedAuthority(ROLE_REVISION));
	}

	@Test
	public void loadUserByUsername_withExistingPassword_usesShaPassword() {
		LoginUserScd user = new LoginUserScd();
		user.setFlagRead(true);
		user.setFlagWrite(true);
		user.setFlagRevision(true);
		user.setEmail("test@email.com");
		user.setGid("123");

		UserPassword userPassword = new UserPassword();
		userPassword.setEmail(user.getEmail());
		userPassword.setPasswordHash("hashHash!34262");

		doReturn(user).when(userDao).getByEmail(user.getEmail());
		doReturn(Optional.of(userPassword)).when(passwordDao).getPassword(user.getEmail());

		UserDetails userDetails = cut.loadUserByUsername(user.getEmail());

		assertThat(userDetails.getUsername()).isEqualTo(user.getEmail());
		assertThat(encoder.matches(userPassword.getPasswordHash(), userDetails.getPassword())).isTrue();
		assertThat(userDetails.getAuthorities()).hasSize(3);
//		assertThat(userDetails.getAuthorities()).contains(new SimpleGrantedAuthority(ROLE_READ), new SimpleGrantedAuthority(ROLE_WRITE));
//		assertThat(userDetails.getAuthorities()).contains(new SimpleGrantedAuthority(ROLE_WRITE));
//		assertThat(userDetails.getAuthorities()).contains(new SimpleGrantedAuthority(ROLE_REVISION));
	}
}
