package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.LoginUserScd;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class LoginUserScdDaoImpl extends LoginUserScdDao {

    public LoginUserScd authenticate(String email, String password) {
        LoginUserScd user = em.createQuery("SELECT user FROM LoginUserScd user where email=:email", LoginUserScd.class)
                .setParameter("email", email).getSingleResult();
        if(user == null) {
            return null;
        }

        if(user.getGid().equals(password)) {
            return user;
        }

        return null;
    }
}
