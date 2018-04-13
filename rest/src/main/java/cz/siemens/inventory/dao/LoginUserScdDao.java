package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.LoginUserScd;

public abstract class LoginUserScdDao extends GenericDao<LoginUserScd> {

    protected LoginUserScdDao() {
        super(LoginUserScd.class);
    }

    public abstract LoginUserScd authenticate(String email, String password);
}
