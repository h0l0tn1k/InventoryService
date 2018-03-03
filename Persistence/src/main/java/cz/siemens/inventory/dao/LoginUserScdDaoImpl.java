package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.LoginUserScd;
import org.springframework.stereotype.Repository;

@Repository
public class LoginUserScdDaoImpl extends GenericDao<LoginUserScd> {

    public LoginUserScdDaoImpl() {
        super(LoginUserScd.class);
    }
}
