package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.LoginUserScd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginUserScdDao extends JpaRepository<LoginUserScd, Long> {

	@Query("SELECT user From LoginUserScd user where lower(email)=:email")
	LoginUserScd getByEmail(@Param("email") String email);
}
