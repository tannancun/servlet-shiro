package cn.infop.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class PropertiesUtils {

	public static String getValue(String key) {
		InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(ConstantUtils.SYS_FILE);
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(key);
	}

	public static void setValue(String key, String value) {
		String resourceFile = PropertiesUtils.class.getResource("/").getPath() + ConstantUtils.SYS_FILE;
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(resourceFile);
			try {
				prop.load(fis);
				fis.close();
				prop.setProperty(key, value);
				FileOutputStream fos = new FileOutputStream(resourceFile);
				prop.store(fos, "update " + key + " " + new Date());
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
