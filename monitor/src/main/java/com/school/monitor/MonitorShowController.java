package com.school.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/show")
public class MonitorShowController {

	@Autowired
	private MonitorShow monitorShow;
	
	@PostMapping("/update")
	public void changeShow(String url) {
		monitorShow.changeWebsite(url);
	}
	
	@PostMapping("/rest")
	public void changeShow() {
		monitorShow.reset();
	}
	
	
	@PostMapping("/login")
	public void loginShow(int monitorId) {
		monitorShow.login(monitorId);		
	}
	
	@PostMapping("/show")
	public void loadShow(int showId) {
		monitorShow.show(showId);		
	}
	
	
	@PostMapping("/loginAndShow")
	public void loginAndShow(int monitorId, int showId) {
		monitorShow.loginAndStartShow(monitorId, showId);	
	}
	
	@PostMapping("/loginAndShowSubstitution")
	public void loginAndShowSubstitution(int monitorId) {
		monitorShow.loginAndShowSubstitution(monitorId);
		
	}
	@PostMapping("/showSubstitution")
	public void ShowSubstitution() {
		monitorShow.showSubstitution();
	}
	


}
