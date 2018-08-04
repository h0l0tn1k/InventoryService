package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.UserScd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserScdDao extends JpaRepository<UserScd, Long> {

	@Query("SELECT user FROM UserScd user where email=:email and gid=:password")
	UserScd authenticate(@Param("email") String email, @Param("password") String password);

	@Query("SELECT user From UserScd user where scdId=:scdId")
	UserScd getByScdId(@Param("scdId") Long scdId);
}
