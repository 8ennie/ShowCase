package com.school.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

@Component
public class MonitorScreen {

	Process p;

	public boolean getStatus() {
		try {
			p = Runtime.getRuntime().exec("sudo tvservice -s");

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String s = null;
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
				return !s.contains("off");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public boolean turnScreenOn() {
		try {
			System.out.println("Monitor Turning On");
			p = Runtime.getRuntime().exec("sudo tvservice -p");
			System.out.println("...");
			p.waitFor();

			p = Runtime.getRuntime().exec("sudo chvt 2");
			System.out.println("...");
			p.waitFor();

			p = Runtime.getRuntime().exec("sudo chvt 7");
			System.out.println("...");
			p.waitFor();

			System.out.println("exit: " + p.exitValue());
			p.destroy();
			return this.getStatus();
		} catch (Exception e) {
			p.destroy();
			System.out.println("The Screen Could not be Turned On!");
			return false;
		}
	}

	public boolean turnScreenOff() {
		try {
			System.out.println("Monitor Turning Off");
			p = Runtime.getRuntime().exec("sudo tvservice -o");
			p.waitFor();
			return this.getStatus();
		} catch (Exception e) {
			p.destroy();
			System.out.println("The Screen Could not be Turned Off!");
			return false;
		}
	}

}
