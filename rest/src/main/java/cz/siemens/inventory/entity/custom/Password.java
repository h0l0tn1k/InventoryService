package cz.siemens.inventory.entity.custom;

import cz.siemens.inventory.dao.LoginPasswordScdDao;
import cz.siemens.inventory.entity.UserPassword;
import cz.siemens.inventory.entity.UserScd;

import java.security.MessageDigest;

public class Password
{
	private String passwordValue;
	private boolean fromScd;

	public Password(UserScd userLogin)
	{
		String userEmail = userLogin.getEmail();

		// Ziskani loginu z tabulky login_user_password
		LoginPasswordScdDao userPasswordDao = new LoginPasswordScdDao();

		UserPassword userPassword = userPasswordDao.getPassword(userEmail);

		// Uzivatel ma jiz nastavene heslo a db tabulka obsahuje jeho hash
		if (userPassword != null && userPassword.getPasswordHash() != null
				&& userPassword.getPasswordHash().length() > 0)
		{
			passwordValue = userPassword.getPasswordHash();
			fromScd = false;
		}
		else // Uzivatel zatim nenastavil heslo a pouziva GID
		{
			passwordValue = userLogin.getGid();
			fromScd = true;
		}
	}

	public boolean isCorrectPassword(String enteredPassword)
	{
		// Uzivatel zatim nenastavil heslo a spravne zadal GID
		if (fromScd && enteredPassword.equals(passwordValue))
			return true;

		// Uzivatel ma jiz nastavene heslo a jeho hash se shoduje s hashi v
		// databazi
		if (!fromScd && Password.getHash(enteredPassword).equals(passwordValue))
			return true;

		// Uzivatel nezadal spravne heslo
		return false;
	}

	public static String getHash(String plainText)
	{
		String resultHash = null;
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(plainText.getBytes());
			byte[] password = md.digest();

			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < password.length; i++)
			{
				hexString.append(Integer.toHexString(0xFF & password[i]));
			}
			resultHash = hexString.toString();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return resultHash;
	}
}
