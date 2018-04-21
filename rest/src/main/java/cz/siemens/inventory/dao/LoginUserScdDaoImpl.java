package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.LoginUserScd;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class LoginUserScdDaoImpl extends GenericDao<LoginUserScd> {

    protected LoginUserScdDaoImpl() {
        super(LoginUserScd.class);
    }

    public LoginUserScd authenticate(String email, String password) {
        return em.createQuery("SELECT user FROM LoginUserScd user where email=:email and gid=:password", LoginUserScd.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
    }
}
