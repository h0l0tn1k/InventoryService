package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.LoginUserScd;
import cz.siemens.inventory.entity.UserScd;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UserScdDaoImpl extends GenericDao<UserScd> {

    protected UserScdDaoImpl() {
        super(UserScd.class);
    }

    public UserScd authenticate(String email, String password) {
        return em.createQuery("SELECT user FROM UserScd user where email=:email and gid=:password", UserScd.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
    }

    public UserScd getByScdId(Long scdId) {
        return em.createQuery("SELECT user From UserScd user where scdId=:scdId", UserScd.class).setParameter("scdId", scdId).getSingleResult();
    }
}
