package com.school.monitor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledFuture;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

@Service
public class ScreenTimeScheduler {

	private static Logger LOGGER = LoggerFactory.getLogger(ScreenTimeScheduler.class);

	private ThreadPoolTaskScheduler poolScheduler;

	@Autowired
	private MonitorScreen monitorScreen;

	@Autowired
	private MonitorSettings monitorSettings;

	private ScheduledFuture<?> scheduledSleepTime;

	private ScheduledFuture<?> scheduledWakeTime;

	public ScreenTimeScheduler() {
		poolScheduler = new ThreadPoolTaskScheduler();
		poolScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
		poolScheduler.setPoolSize(1);
		poolScheduler.initialize();		
		
	}

	@PostConstruct
	public void initSchedul() {
		if( monitorSettings.isOverwriteScreenTime()) {
			LocalTime sleepTime = monitorSettings.getSleepTime();
			if (sleepTime != null) {
				scheduleSleepTime(sleepTime);
			}
			LocalTime wakeTime = monitorSettings.getWakeTime();
			if (wakeTime != null) {
				scheduleWakeTime(wakeTime);
			}
		}
	}

	public void scheduleSleepTime(LocalTime sleepTime) {
		if (scheduledSleepTime != null) {
			scheduledSleepTime.cancel(true);
		}
		LocalTime now = LocalTime.now();
		LocalDateTime timeToRun = LocalDateTime.of(LocalDate.now(), sleepTime);
		timeToRun = now.isBefore(sleepTime) ? timeToRun : timeToRun.plus(1, ChronoUnit.DAYS);
		ZoneId zone = ZoneId.of("Europe/Berlin");
		ZoneOffset zoneOffSet = zone.getRules().getOffset(timeToRun);
		Instant whenToRun = timeToRun.toInstant(zoneOffSet);
		scheduledSleepTime = poolScheduler.schedule(() -> turnMonitorOff(whenToRun), whenToRun);
		LOGGER.info("Sleep Time schedule for: " + sleepTime);
	}

	public void scheduleWakeTime(LocalTime wakeTime) {
		if (scheduledWakeTime != null) {
			scheduledWakeTime.cancel(true);
		}
		LocalTime now = LocalTime.now();
		LocalDateTime timeToRun = LocalDateTime.of(LocalDate.now(), wakeTime);
		timeToRun = now.isBefore(wakeTime) ? timeToRun : timeToRun.plus(1, ChronoUnit.DAYS);
		ZoneId zone = ZoneId.of("Europe/Berlin");
		ZoneOffset zoneOffSet = zone.getRules().getOffset(timeToRun);
		Instant whenToRun = timeToRun.toInstant(zoneOffSet);
		scheduledWakeTime = poolScheduler.schedule(() -> turnMonitorOn(whenToRun), whenToRun);
		LOGGER.info("Wake Time schedule for: " + wakeTime);
	}

	public void turnMonitorOff(Instant whenToRun) {
		LOGGER.info("Turning Monitor Off");
		Instant whenToRun2 = whenToRun.plus(1, ChronoUnit.DAYS);
		monitorScreen.turnScreenOff();
		scheduledSleepTime = poolScheduler.schedule(() -> turnMonitorOff(whenToRun2), whenToRun2);
	}

	public void turnMonitorOn(Instant whenToRun) {
		LOGGER.info("Turning Monitor On");
		Instant whenToRun2 = whenToRun.plus(1, ChronoUnit.DAYS);
		monitorScreen.turnScreenOn();
		scheduledWakeTime = poolScheduler.schedule(() -> turnMonitorOn(whenToRun2), whenToRun2);
	}

}
