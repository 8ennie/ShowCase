package com.school.monitor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/screen")
public class MonitorScreenController {

	@PostMapping("/changeStatus")
	public boolean changeStatus(boolean status) {
		MonitorScreen monitorScreen = new MonitorScreen();
		if(status) {
			 return monitorScreen.turnScreenOn();
		} else {
			return monitorScreen.turnScreenOff();
		}
	}
	
	
}
