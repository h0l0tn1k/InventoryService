package cz.siemens.inventory.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "login_user_scd")
public class LoginUserScd implements Serializable {

	private static final long serialVersionUID = 7421379045143833250L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private long scdId;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	private String gid;

	private String orgcode;

	private String email;

	private Long superiorId;

	private String superiorFirstName;

	private String superiorLastName;

	@NotNull
	private boolean flagRead;

	@NotNull
	private boolean flagWrite;

	@NotNull
	private boolean flagBorrow;

	@NotNull
	private boolean flagInventory;

	@NotNull
	private boolean flagRevision;

	@NotNull
	private boolean flagAdmin;

	public String getName() {
		return firstName +  " " + lastName;
	}
}
