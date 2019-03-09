package cz.siemens.inventory.mapper;

import cz.siemens.inventory.api.gen.model.User;
import cz.siemens.inventory.api.mapper.UserMapper;
import cz.siemens.inventory.entity.LoginUserScd;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserMapperTest {

	private UserMapper cut = new UserMapperImpl();

	@Before
	public void setup() {

	}

	@Test
	public void test_emptyList_mapToInternal() {
		List<LoginUserScd> loginUserScds = cut.mapToInternal(new ArrayList<>());
		assertThat(loginUserScds).isEmpty();
	}

	@Test
	public void test_nullLoginUserScd_mapToInternal() {
		cz.siemens.inventory.api.gen.model.User nullLoginUserScd = null;
		LoginUserScd loginUserScd = cut.mapToInternal(nullLoginUserScd);
		assertThat(loginUserScd).isNull();
	}

	@Test
	public void test_nullLoginUserScd_mapToExternal() {
		LoginUserScd nullLoginUserScd = null;
		User actualUser = cut.mapToExternal(nullLoginUserScd);
		assertThat(actualUser).isNull();
	}

	@Test
	public void test_LoginUserScd_mapToInternal() {
		User expectedUser = getUser(1L, "User 1", "t@c.com");
		LoginUserScd actualLoginUserScd = cut.mapToInternal(expectedUser);
		assertThatLoginUserScdsAreEqual(actualLoginUserScd, expectedUser);
	}

	@Test
	public void test_User_mapToExternal() {
		LoginUserScd expectedLoginUserScd = getLoginUserScd(1L, "User 1", "t@c.com");
		cz.siemens.inventory.api.gen.model.User actualUser = cut.mapToExternal(expectedLoginUserScd);
		assertThatLoginUserScdsAreEqual(actualUser, expectedLoginUserScd);
	}

	@Test
	public void test_UsersList_mapToInternal() {
		List<User> expectedLoginUserScds = new ArrayList<>();
		expectedLoginUserScds.add(getUser(1L, "User 1", "a@b.com"));
		expectedLoginUserScds.add(getUser(2L, "User 2", "c@d.com"));
		List<LoginUserScd> actualCompanyOwners = cut.mapToInternal(expectedLoginUserScds);
		assertThatLoginUserScdsAreEqual(actualCompanyOwners.get(0), expectedLoginUserScds.get(0));
		assertThatLoginUserScdsAreEqual(actualCompanyOwners.get(1), expectedLoginUserScds.get(1));
	}

	private cz.siemens.inventory.api.gen.model.User getUser(Long id, String name, String email) {
		return new User().id(id).firstName(name).lastName(name).email(email)
				.flagAdmin(true).flagBorrow(true).flagInventory(true).flagRead(true).flagWrite(true).flagRevision(true);
	}

	private LoginUserScd getLoginUserScd(Long id, String name, String email) {
		LoginUserScd loginUserScd = new LoginUserScd();
		loginUserScd.setId(id);
		loginUserScd.setFirstName(name);
		loginUserScd.setLastName(name);
		loginUserScd.setEmail(email);
		loginUserScd.setFlagAdmin(true);
		loginUserScd.setFlagBorrow(true);
		loginUserScd.setFlagInventory(true);
		loginUserScd.setFlagRead(true);
		loginUserScd.setFlagWrite(true);
		loginUserScd.setFlagRevision(true);
		return loginUserScd;
	}

	private void assertThatLoginUserScdsAreEqual(LoginUserScd actualLoginUserScd, User expectedLoginUserScd) {
		assertThat(actualLoginUserScd.getId()).isEqualTo(expectedLoginUserScd.getId());
		assertThat(actualLoginUserScd.getFirstName()).isEqualTo(expectedLoginUserScd.getFirstName());
		assertThat(actualLoginUserScd.getLastName()).isEqualTo(expectedLoginUserScd.getLastName());
		assertThat(actualLoginUserScd.getEmail()).isEqualTo(expectedLoginUserScd.getEmail());
		assertThat(actualLoginUserScd.getSuperiorFirstName()).isEqualTo(expectedLoginUserScd.getSuperiorFirstName());
		assertThat(actualLoginUserScd.getSuperiorFirstName()).isEqualTo(expectedLoginUserScd.getSuperiorLastName());
		assertThat(actualLoginUserScd.isFlagAdmin()).isEqualTo(expectedLoginUserScd.isFlagAdmin());
		assertThat(actualLoginUserScd.isFlagBorrow()).isEqualTo(expectedLoginUserScd.isFlagBorrow());
		assertThat(actualLoginUserScd.isFlagInventory()).isEqualTo(expectedLoginUserScd.isFlagInventory());
		assertThat(actualLoginUserScd.isFlagRead()).isEqualTo(expectedLoginUserScd.isFlagRead());
		assertThat(actualLoginUserScd.isFlagWrite()).isEqualTo(expectedLoginUserScd.isFlagWrite());
		assertThat(actualLoginUserScd.isFlagRevision()).isEqualTo(expectedLoginUserScd.isFlagRevision());
	}

	private void assertThatLoginUserScdsAreEqual(User actualUser, LoginUserScd expectedLoginUserScd) {
		assertThat(actualUser.getId()).isEqualTo(expectedLoginUserScd.getId());
		assertThat(actualUser.getFirstName()).isEqualTo(expectedLoginUserScd.getFirstName());
		assertThat(actualUser.getLastName()).isEqualTo(expectedLoginUserScd.getLastName());
		assertThat(actualUser.getEmail()).isEqualTo(expectedLoginUserScd.getEmail());
		assertThat(actualUser.getSuperiorFirstName()).isEqualTo(expectedLoginUserScd.getSuperiorFirstName());
		assertThat(actualUser.getSuperiorFirstName()).isEqualTo(expectedLoginUserScd.getSuperiorLastName());
		assertThat(actualUser.isFlagAdmin()).isEqualTo(expectedLoginUserScd.isFlagAdmin());
		assertThat(actualUser.isFlagBorrow()).isEqualTo(expectedLoginUserScd.isFlagBorrow());
		assertThat(actualUser.isFlagInventory()).isEqualTo(expectedLoginUserScd.isFlagInventory());
		assertThat(actualUser.isFlagRead()).isEqualTo(expectedLoginUserScd.isFlagRead());
		assertThat(actualUser.isFlagWrite()).isEqualTo(expectedLoginUserScd.isFlagWrite());
		assertThat(actualUser.isFlagRevision()).isEqualTo(expectedLoginUserScd.isFlagRevision());
	}
}
