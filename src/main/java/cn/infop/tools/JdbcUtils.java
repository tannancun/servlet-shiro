package cn.infop.tools;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import cn.infop.datatables.DataTablesRequest;
import cn.infop.datatables.DataTablesResponse;

@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class JdbcUtils<T> {

	private static final Logger log = Logger.getLogger(JdbcUtils.class);

	private final Class<T> entityClass;

	protected String singleTable;
	protected String multiTable;

	public JdbcUtils(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public int save(String[] fields, Object[] params) throws SQLException {
		log.info("RReady to insert data");
		if (ObjectUtils.isEmpty(fields))
			throw new SQLException("Table field cannot be empty");
		if (ObjectUtils.isEmpty(params))
			throw new SQLException("Table field value cannot be empty");
		if (fields.length != params.length)
			throw new SQLException("The number of table fields does not match the number of parameters");

		String key = "";
		String symbol = "";
		for (int i = 0; i < fields.length; i++) {
			if (i > 0) {
				key += "," + fields[i];
				symbol += "," + "?";
			} else {
				key = fields[i];
				symbol += "?";
			}
		}

		String sql = "insert into " + singleTable + "(" + key + ") values (" + symbol + ")";
		return insert(sql, params);
	}

	/**
	 * 
	 * @param fields
	 * @param params
	 * @param ConditionalLocation
	 * @return
	 * @throws SQLException
	 */
	public int update(String[] fields, Object[] params, int ConditionalLocation) throws SQLException {
		log.info("Ready to modify data");
		if (ObjectUtils.isEmpty(fields))
			throw new SQLException("Table field cannot be empty");
		if (ObjectUtils.isEmpty(params))
			throw new SQLException("Table field value cannot be empty");
		if (fields.length != params.length)
			throw new SQLException("The number of table fields does not match the number of parameters");
		if (ConditionalLocation >= fields.length || ConditionalLocation >= params.length)
			throw new SQLException("Unable to define modification conditions");

		String KV = "";
		for (int i = 0; i < ConditionalLocation; i++) {
			if (i > 0) {
				KV += "," + fields[i] + "=?";
			} else {
				KV = fields[i] + "=?";
			}
		}

		String where = "";
		for (int i = ConditionalLocation - 1; i < fields.length; i++) {
			if (i > ConditionalLocation) {
				where += " and " + fields[i] + "=?";
			} else {
				where = fields[i] + "=?";
			}
		}

		String sql = "update " + singleTable + " set " + KV + " WHERE " + where + "";
		return this.update(sql, params);
	}

	public void deleteById(int id) {
		String sql = "delete from " + singleTable + " where id=?;";
		update(sql, new Object[] { id });
	}

	public T findOneById(int id) {
		String sql = "select * from " + multiTable + " where id=?;";
		return getBean(sql, new Object[] { id });
	}

	private List<T> getEntitiesForMySQL(String sql, String singleOrder, Integer start, Integer length) {
		sql = sql + singleOrder + " limit " + start + " , " + length;
		return getBeans(sql);
	}

	private String getWhere(String value, String[] searchFields) {
		String result = "";
		for (int i = 0; i < searchFields.length; i++) {
			if (i > 0)
				result += " or " + searchFields[i] + " like " + value;
			else
				result += " " + searchFields[i] + " like " + value;
		}
		return result;
	}

	public DataTablesResponse<T> findAll(DataTablesRequest dtr, String[] searchFields) {
		String table = this.multiTable;
		if (StringUtils.isEmpty(table))
			table = this.singleTable;
		if (StringUtils.isEmpty(table))
			log.error("Table name cannot be empty");

		log.info("Handling requests from Datatables");
		String sql_sum = "select count(*) sum from " + table;
		String sql_query = "select * from " + table;

		String column = dtr.getColumnsSearchKey();
		String global = "'%" + dtr.getGlobalSearchField() + "%'";

		List<T> list = new ArrayList<>();
		DataTablesResponse<T> pd = new DataTablesResponse<T>();
		int records = count(sql_sum);
		int filters = 0;

		if (column == null) {
			if (StringUtils.isEmpty(dtr.getGlobalSearchField())) {
				list = getEntitiesForMySQL(sql_query, dtr.getSingleOrder(), dtr.getStart(), dtr.getLength());
				filters = count(sql_sum);
			} else {
				String like = " where " + getWhere(global, searchFields);
				filters = count(sql_sum + like);
				list = getEntitiesForMySQL(sql_query + like, dtr.getSingleOrder(), dtr.getStart(), dtr.getLength());
			}
		} else {
			if (StringUtils.isEmpty(dtr.getGlobalSearchField())) {
				String like = " where " + column;
				filters = count(sql_sum + like);
				list = getEntitiesForMySQL(sql_query + like, dtr.getSingleOrder(), dtr.getStart(), dtr.getLength());
			} else {
				String like = " where " + column + " and(" + getWhere(global, searchFields) + ")";
				filters = count(sql_sum + like);
				list = getEntitiesForMySQL(sql_query + like, dtr.getSingleOrder(), dtr.getStart(), dtr.getLength());
			}
		}

		pd.setRecordsFiltered(filters);
		pd.setRecordsTotal(records);
		pd.setDraw(dtr.getDraw());
		pd.setData(list);
		return pd;
	}

	public List<T> FindAllRecordsOfSomeFields(Object... fields) {
		if (ObjectUtils.isNotEmpty(fields)) {
			String sql = "SELECT";
			for (int i = 0; i < fields.length; i++) {
				if (i > 0)
					sql += " , " + fields[i];
				else
					sql += " " + fields[i];
			}
			sql += " FROM " + singleTable;
			return getBeans(sql);
		} else
			return null;
	}

	public T getBean(String sql) {
		log.debug("query: " + sql);
		try {
			QueryRunner runner = JdbcUtilsSingleton.getInstance().getQueryRunner();
			return (T) runner.query(sql, new BeanHandler(entityClass));
		} catch (Exception ex) {
			log.error(ex);
		}
		return null;
	}

	public T getBean(String sql, Object... params) {
		log.debug("query: " + sql);
		try {
			QueryRunner runner = JdbcUtilsSingleton.getInstance().getQueryRunner();
			return (T) runner.query(sql, new BeanHandler(entityClass), params);
		} catch (Exception ex) {
			log.error(ex);
		}
		return null;
	}

	public List<T> getBeans(String sql) {
		log.debug("query: " + sql);
		try {
			QueryRunner runner = JdbcUtilsSingleton.getInstance().getQueryRunner();
			return (List) runner.query(sql, new BeanListHandler(entityClass));
		} catch (Exception ex) {
			log.error(ex);
		}
		return null;
	}

	public List<T> getBeans(String sql, Object... params) {
		log.debug("query: " + sql);
		try {
			QueryRunner runner = JdbcUtilsSingleton.getInstance().getQueryRunner();
			return (List) runner.query(sql, new BeanListHandler(entityClass), params);
		} catch (Exception ex) {
			log.error(ex);
		}
		return null;
	}

	public List<T> getOneFielValue(String sql) {
		log.debug("query: " + sql);
		try {
			QueryRunner runner = JdbcUtilsSingleton.getInstance().getQueryRunner();
			return (List<T>) runner.query(sql, new ColumnListHandler<String>());
		} catch (Exception ex) {
			log.error(ex);
		}
		return null;
	}

	public List<T> getOneFielValue(String sql, Object[] params) {
		log.debug("query: " + sql);
		try {
			QueryRunner runner = JdbcUtilsSingleton.getInstance().getQueryRunner();
			return (List<T>) runner.query(sql, new ColumnListHandler<String>(), params);
		} catch (Exception ex) {
			log.error(ex);
		}
		return null;
	}

	public Set<String> getFielValues(String sql, Object[] params) {
		log.debug("query: " + sql);
		Set<String> set = new LinkedHashSet<String>();
		List<String> list = new ArrayList<>();
		try {
			QueryRunner runner = JdbcUtilsSingleton.getInstance().getQueryRunner();
			list = runner.query(sql, new ColumnListHandler<String>(), params);
			for (String s : list) {
				set.add(s);
			}
			return set;
		} catch (Exception ex) {
			log.error(ex);
		}
		return null;
	}

	public String getOne(String sql) {
		log.debug("queryOne: " + sql);
		String result = "";
		try {
			QueryRunner runner = JdbcUtilsSingleton.getInstance().getQueryRunner();
			Object value = runner.query(sql, new ScalarHandler());
			if (ObjectUtils.allNotNull(value)) {
				result = value.toString();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String getOne(String sql, Object[] params) {
		log.debug("queryOne: " + sql);
		String result = "";
		try {
			QueryRunner runner = JdbcUtilsSingleton.getInstance().getQueryRunner();
			Object value = runner.query(sql, new ScalarHandler(), params);
			if (ObjectUtils.allNotNull(value)) {
				result = value.toString();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据SQL和条件进行统计
	 * 
	 * @param sql
	 * @param params 条件
	 * @return
	 */
	public int count(String sql, Object[] params) {
		log.debug("count: " + sql);
		try {
			QueryRunner runner = JdbcUtilsSingleton.getInstance().getQueryRunner();
			Object value = runner.query(sql, new ScalarHandler(), params);
			return Integer.parseInt(value.toString());
		} catch (Exception ex) {
			log.error(ex);
		}
		return 0;
	}

	public int count(String sql) {
		log.debug("count: " + sql);
		try {
			QueryRunner runner = JdbcUtilsSingleton.getInstance().getQueryRunner();
			Object value = runner.query(sql, new ScalarHandler());
			return Integer.parseInt(value.toString());
		} catch (Exception ex) {
			log.error(ex);
		}
		return 0;
	}

	public int update(String sql, Object[] params) {
		log.debug("update: " + sql);
		int id = 0;
		try {
			QueryRunner runner = JdbcUtilsSingleton.getInstance().getQueryRunner();
			id = runner.update(sql, params);
		} catch (Exception ex) {
			log.error(ex);
		}
		return id;
	}

	public int insert(String sql, Object[] params) {
		log.debug("insert: " + sql);
		int id = 0;
		QueryRunner runner = JdbcUtilsSingleton.getInstance().getQueryRunner();
		ScalarHandler<Long> rsh = new ScalarHandler<Long>();
		try {
			Long ID = runner.insert(sql, rsh, params);
			id = Integer.parseInt(Long.toString(ID));
		} catch (SQLException ex) {
			log.error(ex);
		}

		return id;
	}

	public void insertWithoutReturnId(String sql, Object[] params) {
		log.debug("insert: " + sql);
		ScalarHandler<String> rsh = new ScalarHandler<String>();
		QueryRunner runner = JdbcUtilsSingleton.getInstance().getQueryRunner();
		try {
			runner.insert(sql, rsh, params);
		} catch (SQLException e) {
			log.error(e);
		}
	}

	public int delete(String sql, Object[] params) {
		log.debug("delete: " + sql);
		int id = 0;
		try {
			QueryRunner runner = JdbcUtilsSingleton.getInstance().getQueryRunner();
			id = runner.update(sql, params);
		} catch (Exception ex) {
			log.error(ex);
		}
		return id;
	}

	public int batch(String sql, Object[][] params) {
		log.debug("batch: " + sql);
		int rows = 0;
		QueryRunner runner = JdbcUtilsSingleton.getInstance().getQueryRunner();

		try {
			rows = runner.batch(sql, params).length;
		} catch (SQLException e) {
			log.error(e);
		}

		return rows;
	}

	public InputStream getInputStream(String sql, Object[] params, String fieldName) {
		QueryRunner runner = JdbcUtilsSingleton.getInstance().getQueryRunner();
		InputStream in = null;
		try (Connection connection = JdbcUtilsSingleton.getInstance().getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			runner.fillStatement(statement, params);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				in = rs.getBinaryStream(fieldName);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return in;
	}
}
