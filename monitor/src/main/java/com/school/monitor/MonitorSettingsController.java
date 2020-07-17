package com.school.monitor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/settings")
public class MonitorSettingsController {

	@PostMapping("/setServerIP")
	public boolean changeStatus(String serverIp) {
		MonitorSettings monitorSettings = new MonitorSettings();
		monitorSettings.setServerIp(serverIp);
		return true;
	}
	
	
}
