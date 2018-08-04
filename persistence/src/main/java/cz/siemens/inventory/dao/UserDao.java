package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
}
