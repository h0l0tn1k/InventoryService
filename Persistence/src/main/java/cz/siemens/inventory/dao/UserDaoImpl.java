package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericDao<User> {

    public UserDaoImpl() {
        super(User.class);
    }
}
