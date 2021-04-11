package cn.infop.security;

import java.util.UUID;

import org.apache.shiro.crypto.hash.Sha512Hash;

public class Main {

	public static void main(String[] args) {
		String username = "admin";
		String password = "123";
		//String uuid = UUID.randomUUID().toString();
		//System.out.println(uuid);
		//byte[] passwordSalt = uuid.getBytes();
		String passwordHash = new Sha512Hash(password, "9e9bff50-07de-4661-ac1f-a49c975acb0b").toString();
				//new Sha512Hash(password, username + new String(passwordSalt), 99).toString();
		System.out.println("新密码：" + passwordHash);
		System.out.println(org.apache.shiro.crypto.hash.Sha512Hash.ALGORITHM_NAME);
		
		System.out.println(Boolean.getBoolean("fasle"));

	}

}
