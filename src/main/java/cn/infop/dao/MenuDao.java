package cn.infop.dao;

import java.util.ArrayList;
import java.util.List;

import cn.infop.mode.Menu;
import cn.infop.tools.JdbcUtils;

public class MenuDao extends JdbcUtils<Menu> {

	private final String table = "(select * from menus) t";
	private List<Menu> result = new ArrayList<>();

	public MenuDao() {
		super(Menu.class);
		super.multiTable = table;
		super.singleTable = "menus";
	}

	public List<Menu> findAll() {
		String sql = "select * from menus ;";
		return getBeans(sql);
	}

	// -----------------------------------------------------------------
	// 递归查询
	// -----------------------------------------------------------------

	private String addSymbol(int value,String symbol) {
		String s = "";
		for (int i = 0; i < value; i++) {
			s = s.trim();
			s += symbol;
		}
		return s;
	}

	private void findChild(int pid, int level,String symbol) {
		String sql = "SELECT * FROM " + table + " WHERE pid = ? order by name asc;";
		List<Menu> list = getBeans(sql, new Object[] { pid });
		int child_levle = level + 1;
		for (Menu menu : list) {
			menu.setName(addSymbol(level,symbol) + menu.getName());
			result.add(menu);
			findChild(menu.getId(), child_levle,symbol);
		}
	}

	public List<Menu> selectMenuTree(int pid) {
		String symbol = "&nbsp;&nbsp;";
		List<Menu> list = new ArrayList<>();
		String sql = "SELECT * FROM menus WHERE pid = ? order by name asc; ";
		list = getBeans(sql, new Object[] { pid });
		for (Menu menu : list) {
			result.add(menu); // top one, It is zero
			findChild(menu.getId(), 1,symbol);
		}

		return result;
	}

	// -----------------------------------------------------------------
	// end 递归查询
	// -----------------------------------------------------------------

	public void updateLevelName() {
		String symbol = "— ";
		List<Menu> list = new ArrayList<>();
		String sql = "SELECT * FROM menus WHERE pid = ? order by name asc; ";
		list = getBeans(sql, new Object[] { 0 });
		for (Menu menu : list) {
			result.add(menu); // top one, It is zero
			findChild(menu.getId(), 1,symbol);
		}
		
		String sql2 = "update menus set levelname = ?,oid=? where id = ?";
		for (int i = 0; i < result.size(); i++) {
			update(sql2, new Object[] { result.get(i).getName(), i + 1, result.get(i).getId() });
		}
	}
}
