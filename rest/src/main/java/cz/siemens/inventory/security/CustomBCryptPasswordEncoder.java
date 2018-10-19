package cz.siemens.inventory.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;

/**
 * This is custom implementation of PasswordEncoder using BCrypt encoder
 * For authentication application uses passwords in SHA-256 format
 * Or Gid field in LoginUserScd table
 */
public class CustomBCryptPasswordEncoder implements PasswordEncoder {

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public CustomBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	private static String getShaHash(String rawPassword) {
		String resultHash = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(rawPassword.getBytes());
			byte[] password = md.digest();
			StringBuilder hexString = new StringBuilder();
			for (byte aPassword : password) {
				hexString.append(Integer.toHexString(0xFF & aPassword));
			}
			resultHash = hexString.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultHash;
	}

	@Override
	public String encode(CharSequence charSequence) {
		return bCryptPasswordEncoder.encode(charSequence);
	}

	/**
	 * Checks whether BCrypt(<code>rawPassword<code>) or BCrypt(Sha256(<code>rawPassword<code>)) matches <code>encodedPassword<code> from database
	 * @param rawPassword     password received from login request
	 * @param encodedPassword could be either BCrypt(password) - user hasn't set password yet, or BCrypt(Sha256(password)) password is set
	 * @return true if passwords match, else otherwise
	 */
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		String encodedToSha = getShaHash(rawPassword.toString());

		boolean matches = bCryptPasswordEncoder.matches(encodedToSha, encodedPassword);
		matches |= bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
		return matches;
	}
}
