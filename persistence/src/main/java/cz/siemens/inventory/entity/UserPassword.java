package cz.siemens.inventory.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "login_user_password")
public class UserPassword implements Serializable
{
	@Id
	@Column(name = "email")
	private String email;

	@NotNull
	@Column(name = "password")
	private String passwordHash;
}
