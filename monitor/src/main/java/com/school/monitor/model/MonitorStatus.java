package com.school.monitor.model;

import java.time.LocalTime;

public class MonitorStatus {

	private String url;
	private boolean screenStatus;
	private String serverIp;
	private LocalTime sleepTime;
	private LocalTime wakeTime;
	private boolean overwriteScreenTime;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public boolean isScreenStatus() {
		return screenStatus;
	}
	public void setScreenStatus(boolean screenSatus) {
		this.screenStatus = screenSatus;
	}
	
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	
	public LocalTime getSleepTime() {
		return sleepTime;
	}
	public void setSleepTime(LocalTime sleepTime) {
		this.sleepTime = sleepTime;
	}
	
	public LocalTime getWakeTime() {
		return wakeTime;
	}
	public void setWakeTime(LocalTime wakeTime) {
		this.wakeTime = wakeTime;
	}
	public boolean isOverwriteScreenTime() {
		return overwriteScreenTime;
	}
	public void setOverwriteScreenTime(boolean overwriteScreenTime) {
		this.overwriteScreenTime = overwriteScreenTime;
	}
	
	
	
	
	
	
}
