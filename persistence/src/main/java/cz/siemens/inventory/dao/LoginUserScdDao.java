package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.LoginUserScd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginUserScdDao extends JpaRepository<LoginUserScd, Long> {

	@Query("SELECT user FROM LoginUserScd user where email=:email and gid=:password")
	LoginUserScd authenticate(@Param("email") String email, @Param("password") String password);

	@Query("SELECT user From LoginUserScd user where scdId=:scdId")
	LoginUserScd getByScdId(@Param("scdId") Long scdId);

	@Query("SELECT user From LoginUserScd user where email=:email")
	LoginUserScd getByEmail(@Param("email") String email);
}
