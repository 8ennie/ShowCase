package com.school.monitor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MonitorSettings {

	
	public void setProperty(String key, String value) {
		try {
			Properties prop = new Properties();
			prop.setProperty(key, value);
			prop.store(new FileOutputStream("src/main/resources/monitorSettings.properties"), "");
		} catch (IOException e) {
			System.out.println("Properties could not be loaded");
			e.printStackTrace();
		}
	}
	
	
	public String getProperty(String key) {
		Properties prop = new Properties();
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("monitorSettings.properties")) {
			prop.load(input);
			return (String) prop.get(key);
		} catch (IOException e) {
			System.out.println("Properties could not be loaded");
			e.printStackTrace();
		}
		return null;
	}
	
	public void setServerIp(String serverIp){
		this.setProperty("SERVER_IP", serverIp);
	}
	
	public String getServerIp(){
		return this.getProperty("SERVER_IP");
	}
}
