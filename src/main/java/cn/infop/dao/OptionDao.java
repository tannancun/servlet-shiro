package cn.infop.dao;

import org.apache.commons.lang3.ObjectUtils;

import cn.infop.mode.Option;
import cn.infop.tools.JdbcUtils;

public class OptionDao extends JdbcUtils<Option> {

	private final String table = "options";

	public OptionDao() {
		super(Option.class);
		super.multiTable = table;
		super.singleTable = "options";
	}

	public void save(String key, String value) {
		if (existKey(key)) {
			update(key, value);
		} else {
			String sql = "insert into options(option_name,option_value) values (?, ?)";
			insert(sql, new Object[] { key, value });
		}
	}

	public boolean existKey(String key) {
		String sql = "select count(*) from options where option_name=?";
		boolean result = false;
		int rowCount = count(sql, new Object[] { key });
		if (rowCount > 0)
			result = true;
		return result;
	}

	public void update(String key, String value) {
		String sql = "update options set option_value = ? where option_name = ?";
		update(sql, new Object[] { value, key });
	}

	public String getValue(String key) {
		String sql = "select * from options where option_name = ?";
		Option option = getBean(sql, new Object[] { key });
		if (ObjectUtils.isNotEmpty(option)) {
			return option.getOption_value();
		} else {
			return "";
		}
	}

}
