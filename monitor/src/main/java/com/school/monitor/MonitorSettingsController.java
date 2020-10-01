package com.school.monitor;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/settings")
public class MonitorSettingsController {

	@Autowired
	private MonitorSettings monitorSettings;

	@PostMapping("/serverIp")
	public String changeServerIp(String ip) {
		System.out.println(ip);
		monitorSettings.setServerIp(ip);
		return monitorSettings.getServerIp();
	}

	@PostMapping("/sleepTime")
	public LocalTime setSleepTime(String time) {
		monitorSettings.setSleepTime(LocalTime.parse(time));
		return monitorSettings.getSleepTime();
	}

	@PostMapping("/wakeTime")
	public LocalTime setWakeTime(String time) {
		monitorSettings.setWakeTime(LocalTime.parse(time));
		return monitorSettings.getWakeTime();
	}

	@PostMapping("/overwriteScreenTime")
	public Boolean setWakeTime(boolean overwrite) {
		monitorSettings.setOverwriteScreenTime(overwrite);
		return monitorSettings.isOverwriteScreenTime();
	}
	
	@PostMapping("/onStartResumeLastShow")
	public Boolean setOnStartResumeLastShow(boolean resumeLastShow) {
		monitorSettings.setOnStartResumeLastShow(resumeLastShow);
		return monitorSettings.isOnStartResumeLastShow();
	}
	
	@PostMapping("/startUrl")
	public String setStartUrl(String startUrl) {
		monitorSettings.setStartUrl(startUrl);
		return monitorSettings.getStartUrl();
	}

	@PostMapping("/reboot")
	public boolean reboot() {
		monitorSettings.reboot();
		return true;
	}

}
