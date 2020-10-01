package com.school.monitor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MonitorShow {

	private WebDriver driver;

	@Autowired
	private MonitorSettings monitorSettings;

	public MonitorShow() {
		System.setProperty("webdriver.chrome.driver", "/usr/lib/chromium-browser/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--kiosk");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-browser-side-navigation");
		options.addArguments("--disable-notifications");

		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		options.setExperimentalOption("useAutomationExtension", false);
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("useAutomationExtension", false);
		prefs.put("excludeSwitches", new String[] { "enable-automation" });
		options.setExperimentalOption("prefs", prefs);

		driver = new ChromeDriver(options);

	}

	@PostConstruct
	public void postConstructor() {
		if (monitorSettings.isOnStartResumeLastShow()) {
			driver.get(monitorSettings.getCurrentShowUrl());
		} else {
			driver.get(monitorSettings.getStartUrl());
		}
		driver.get(monitorSettings.getStartUrl());
	}

	public void reset() {
		driver.get(monitorSettings.getStartUrl());
	}

	public void changeWebsite(String url) {
		driver.get(url);
	}

	public String getUrl() {
		return driver.getCurrentUrl();
	}

	public void loginAndStartShow(int monitorId, int showId) {
		login(monitorId);
		show(showId);
	}

	public void show(int showId) {
		String showUrl = monitorSettings.getServerIp() + "/photoshow/show/" + showId;
		System.out.println("Starting Show Number: " + showId + " on " + showUrl);
		monitorSettings.setCurrentShowUrl(showUrl);
		driver.get(showUrl);
		System.out.println("Loaded Show");
	}

	public void loginAndShowSubstitution(int monitorId) {
		login(monitorId);
		showSubstitution();
	}

	public void showSubstitution() {
		String showUrl = monitorSettings.getServerIp() + "/photoshow/show/" + "sub";
		System.out.println("Loading Current Substitutions Plan on " + showUrl);
		monitorSettings.setCurrentShowUrl(showUrl);
		driver.get(showUrl);
	}

	public void login(int id) {
		String loginUrl = monitorSettings.getServerIp() + "/auth";
		System.out.println("Loggin in the User: " + "Monitor(" + id + ")" + " on " + loginUrl);
		driver.get(loginUrl);
		WebElement usernameInput = driver.findElement(By.id("username"));
		WebElement passwordInput = driver.findElement(By.id("password"));

		WebElement loginButton = driver.findElement(By.id("login"));

		usernameInput.sendKeys("Monitor(" + id + ")");
		passwordInput.sendKeys("Monitor(" + id + ")");
		loginButton.click();

		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Logged In");
	}

}
