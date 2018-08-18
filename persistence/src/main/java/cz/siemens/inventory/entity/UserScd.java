package cz.siemens.inventory.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "login_user_scd")
public class UserScd implements Serializable
{

	private static final long serialVersionUID = 7421379045143833250L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private long scdId;
	private String firstName;
	private String lastName;
	private String gid;
	private String orgcode;
	private String email;
	private long superiorId;
	private String superiorFirstName;
	private String superiorLastName;
	private boolean flagRead;
	private boolean flagWrite;
	private boolean flagBorrow;
	private boolean flagInventory;
	private boolean flagRevision;
	private boolean flagAdmin;
}
