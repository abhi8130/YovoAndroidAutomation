package com.automation.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {

	public static String getDataProperties(String key) throws IOException {
		String propertyValue = null;
		Properties prop = new Properties();

		prop = new Properties();
		FileInputStream ip = null;
		try {
			ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/automation/config/config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prop.load(ip);
		return propertyValue;
	}
}
