package cz.siemens.inventory.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "login_user_password")
public class UserPassword implements Serializable
{

	@Id
	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String passwordHash;

	public UserPassword()
	{
	}

	public UserPassword(String email, String passwordHash)
	{
		this.email = email;
		this.passwordHash = passwordHash;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPasswordHash()
	{
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash)
	{
		this.passwordHash = passwordHash;
	}
}
