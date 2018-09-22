package cz.siemens.inventory.mapper.impl;

import cz.siemens.inventory.entity.LoginUserScd;
import cz.siemens.inventory.gen.model.User;
import cz.siemens.inventory.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {
	@Override
	public LoginUserScd mapToInternal(User object) {
		if(object == null) {
			return null;
		}
		LoginUserScd loginUserScd = new LoginUserScd();
		loginUserScd.setId(object.getId());
		loginUserScd.setEmail(object.getEmail());
		loginUserScd.setFirstName(object.getFirstName());
		loginUserScd.setLastName(object.getLastName());
		loginUserScd.setSuperiorFirstName(object.getSuperiorFirstName());
		loginUserScd.setSuperiorLastName(object.getSuperiorLastName());
		loginUserScd.setFlagAdmin(object.isFlagAdmin());
		loginUserScd.setFlagBorrow(object.isFlagBorrow());
		loginUserScd.setFlagInventory(object.isFlagInventory());
		loginUserScd.setFlagRead(object.isFlagRead());
		loginUserScd.setFlagWrite(object.isFlagWrite());
		loginUserScd.setFlagRevision(object.isFlagRevision());
		return loginUserScd;
	}

	@Override
	public User mapToExternal(LoginUserScd object) {
		if(object == null) {
			return null;
		}
		return new User().id(object.getId()).email(object.getEmail())
				.firstName(object.getFirstName()).lastName(object.getLastName())
				.superiorFirstName(object.getSuperiorFirstName()).superiorLastName(object.getSuperiorLastName())
				.flagAdmin(object.isFlagAdmin()).flagBorrow(object.isFlagBorrow()).flagInventory(object.isFlagInventory())
				.flagRead(object.isFlagRead()).flagWrite(object.isFlagWrite()).flagRevision(object.isFlagRevision());
	}
}
