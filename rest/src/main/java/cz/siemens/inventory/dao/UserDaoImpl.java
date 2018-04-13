package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UserDaoImpl extends GenericDao<User> {

    public UserDaoImpl() {
        super(User.class);
    }
}
