package cn.infop.dao;

import java.util.Set;

import cn.infop.mode.User;
import cn.infop.tools.JdbcUtils;

public class UserDao extends JdbcUtils<User> {

	private final String table = "(select * from Users) t";

	public UserDao() {
		super(User.class);
		super.multiTable = table;
		super.singleTable = "Users";
	}

	public User findByUsername(String username) {
		String sql = "select * from users where username = ?";
		return getBean(sql, new Object[] { username });
	}

	public Set<String> findRoleNamesByUsername(String username) {
		String sql = "select role_name from user_roles where username = ?";
		return getFielValues(sql, new Object[] { username });
	}

	public Set<String> findPermissionByRolename(String rolename) {
		String sql = "select permission from roles_permissions where role_name = ?";
		return getFielValues(sql, new Object[] { rolename });
	}
}
