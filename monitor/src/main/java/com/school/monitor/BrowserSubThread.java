package com.school.monitor;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class BrowserSubThread implements Runnable {

	private Process p;
	private Thread worker;
	private final AtomicBoolean running = new AtomicBoolean(false);
	private final AtomicBoolean stopped = new AtomicBoolean(false);
	private String url;
	
	
	public BrowserSubThread(String url) {
		this.url = url;
	}
	
	public void start() {
		worker = new Thread(this);
		worker.start();
	}

	public void stop() {
		running.set(false);
	}
	
	public void interrupt() {
        running.set(false);
        worker.interrupt();
    }
	
	boolean isRunning() {
        return running.get();
    }
 
    boolean isStopped() {
        return stopped.get();
    }

	public void run() {
		running.set(true);
		stopped.set(false);
		while (running.get()) {
			try {
				System.out.println("Loading Site: /usr/bin/chromium-browser --incognito --kiosk " + url);
				p = Runtime.getRuntime().exec("sudo -u pi /usr/bin/chromium-browser --incognito --kiosk " + url);
				p.waitFor();
				System.out.println("Loaded Site");
				p.destroy();
				return;
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				p.destroy();
				System.out.println("Cant Load Show");
				return;
			} catch (IOException e) {
				p.destroy();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		stopped.set(true);
		return;
	}

}
