package com.school.monitor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javafx.application.Platform;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/show")
public class MonitorShowController {

	@PostMapping("/update")
	public void changeShow(String url) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				MonitorBrowserApplication.changeWebsite(url);
			}
		});
	}

}
