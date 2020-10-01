package com.school.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.monitor.model.MonitorStatus;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/status")
public class MonitorStatusController {

	@Autowired
	private MonitorScreen monitorScreen;
	
	@Autowired
	private MonitorShow monitorShow;
	
	@Autowired
	private MonitorSettings monitorSettings;
	
	@GetMapping()
	public MonitorStatus generalState() {
		MonitorStatus monitorStatus = new MonitorStatus();
		monitorStatus.setScreenStatus (monitorScreen.getStatus());
		monitorStatus.setUrl(monitorShow.getUrl());
		monitorStatus.setServerIp(monitorSettings.getServerIp());
		monitorStatus.setSleepTime(monitorSettings.getSleepTime());
		monitorStatus.setWakeTime(monitorSettings.getWakeTime());
		monitorStatus.setOverwriteScreenTime(monitorStatus.isOverwriteScreenTime());
		return monitorStatus;
	}
	
	
}
