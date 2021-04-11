package cn.infop.tools;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class JdbcUtilsSingleton {

	private static Log log = LogFactory.getLog(JdbcUtilsSingleton.class);

	private static JdbcUtilsSingleton instance = null;
	private static DataSource dataSource = null;
	private static Connection connection = null;

	private JdbcUtilsSingleton() {
		Properties properties = new Properties();

		try {
			InputStream inputStream = PropertiesUtils.class.getClassLoader()
					.getResourceAsStream(ConstantUtils.SYS_FILE);
			properties.load(inputStream);
			dataSource = BasicDataSourceFactory.createDataSource(properties);
			log.debug("dataSource initialization success.");
		} catch (Exception e) {
			log.error("dataSource initialization fail {}", e);
			e.printStackTrace();
		}

	}

	public static JdbcUtilsSingleton getInstance() {
		if (instance == null) {
			synchronized (JdbcUtilsSingleton.class) {
				if (instance == null) {
					instance = new JdbcUtilsSingleton();
				}
			}
		}
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public QueryRunner getQueryRunner() {
		return new QueryRunner(dataSource);
	}

	@Override
	protected void finalize() throws Throwable {
		if (connection != null) {
			connection.close();
			log.debug("connection finalize success.");
		}
		dataSource = null;
		log.debug("dataSource finalize success.");
	}

}
