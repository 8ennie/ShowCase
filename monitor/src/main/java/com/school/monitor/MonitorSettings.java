package com.school.monitor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MonitorSettings {

	@Autowired
	ScreenTimeScheduler screenTimeScheduler;

	private void setProperty(String key, String value) {
		Properties prop = new Properties();
		try (InputStream input = new FileInputStream("/home/pi/Desktop/Controller/monitorSettings.properties")) {
			prop.load(input);
			prop.setProperty(key, value);
			prop.store(new FileOutputStream("/home/pi/Desktop/Controller/monitorSettings.properties"), null);
		} catch (IOException e) {
			System.out.println("Properties could not be loaded");
			e.printStackTrace();
		}
	}

	private String getProperty(String key) {
		Properties prop = new Properties();
		try (InputStream input = new FileInputStream("/home/pi/Desktop/Controller/monitorSettings.properties")) {
			prop.load(input);
			return (String) prop.get(key);
		} catch (IOException e) {
			System.out.println("Properties could not be loaded");
			e.printStackTrace();
		}
		return null;
	}

	public void setServerIp(String serverIp) {
		this.setProperty("SERVER_IP", serverIp);
	}

	public String getServerIp() {
		return this.getProperty("SERVER_IP");
	}

	public void setSleepTime(LocalTime sleepTime) {
		this.setProperty("SLEEP_TIME", sleepTime.toString());
		screenTimeScheduler.scheduleSleepTime(sleepTime);
	}

	public LocalTime getSleepTime() {
		String sleepTime = this.getProperty("SLEEP_TIME");
		if (sleepTime != null) {
			return LocalTime.parse(sleepTime);
		}
		return null;
	}

	public void setWakeTime(LocalTime wakeTime) {
		this.setProperty("WAKE_TIME", wakeTime.toString());
		screenTimeScheduler.scheduleWakeTime(wakeTime);
	}

	public LocalTime getWakeTime() {
		String wakeTime = this.getProperty("WAKE_TIME");
		if (wakeTime != null) {
			return LocalTime.parse(wakeTime);
		}
		return null;
	}

	public boolean isOverwriteScreenTime() {
		String overwrite = this.getProperty("OVERWRITE_SCREEN_TIME");
		if (overwrite != null) {
			return overwrite == "true" ? true : false;
		}
		return false;
	}

	public void setOverwriteScreenTime(boolean overwrite) {
		this.setProperty("OVERWRITE_SCREEN_TIME", String.valueOf(overwrite));
	}
	
	public void setStartUrl(String serverIp) {
		this.setProperty("START_URL", serverIp);
	}

	public String getStartUrl() {
		return this.getProperty("START_URL");
	}
	
	public void setCurrentShowUrl(String serverIp) {
		this.setProperty("CURRENT_SHOW_URL", serverIp);
	}

	public String getCurrentShowUrl() {
		return this.getProperty("CURRENT_SHOW_URL");
	}

	public boolean isOnStartResumeLastShow() {
		String resumeLastShow = this.getProperty("ON_START_RESUME_LAST_SHOW");
		if (resumeLastShow != null) {
			return resumeLastShow == "true" ? true : false;
		}
		return false;
	}

	public void setOnStartResumeLastShow(boolean resumeLastShow) {
		this.setProperty("ON_START_RESUME_LAST_SHOW", String.valueOf(resumeLastShow));
	}
	
	public void reboot() {
		try {
			System.out.println("Raspberry Rebooting");
			Process p = Runtime.getRuntime().exec("sudo reboot");
			p.waitFor();
			p.destroy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
