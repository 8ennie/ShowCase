package com.school.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/screen")
public class MonitorScreenController {

	@Autowired
	private MonitorScreen monitorScreen;
	
	@PostMapping("/changeStatus")
	public boolean changeStatus(boolean status) {
		
		if(status) {
			 return monitorScreen.turnScreenOn();
		} else {
			return monitorScreen.turnScreenOff();
		}
	}
	
	@GetMapping("/status")
	public boolean getStatus() {
		return monitorScreen.getStatus();
	}
	
	
}
