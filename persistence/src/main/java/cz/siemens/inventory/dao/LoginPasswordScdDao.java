package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginPasswordScdDao extends JpaRepository<UserPassword, String>
{
	@Query("SELECT up FROM UserPassword up where up.email LIKE :email")
	UserPassword getPassword(@Param("email") String email);
}
