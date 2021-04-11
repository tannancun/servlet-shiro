package cn.infop.mode;

public class User {

	private String username;
	private String password;
	private String credentials;
	private String password_salt;
	private boolean locked;

	public User() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCredentials() {
		return credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public String getPassword_salt() {
		return password_salt;
	}

	public void setPassword_salt(String password_salt) {
		this.password_salt = password_salt;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

//	public void generatePassword() {
//		byte[] passwordSalt = UUID.randomUUID().toString().getBytes();
//		setPasswordSalt(passwordSalt);
//		String passwordHash = new Sha512Hash(this.getPassword(), this.getUsername() + new String(passwordSalt), 99)
//				.toString();
//		this.setPasswordHash(passwordHash);
//	}

}
