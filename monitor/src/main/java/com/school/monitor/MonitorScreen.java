package com.school.monitor;

public class MonitorScreen {

	Process p;
	
	public boolean turnScreenOn() {
		try {
			System.out.println ("Monitor Turning On");
            p = Runtime.getRuntime().exec("sudo tvservice -p");
            System.out.println ("...");
            p.waitFor();
            
            p = Runtime.getRuntime().exec("sudo chvt 2");
            System.out.println ("...");
            p.waitFor();
            
            p = Runtime.getRuntime().exec("sudo chvt 7");
            System.out.println ("...");
            p.waitFor();
            
            System.out.println ("exit: " + p.exitValue());
            p.destroy();
            return true;
		} catch (Exception e) {
			p.destroy();
			System.out.println ("The Screen Could not be Turned On!");
			return false;
		}
	}
	
	
	public boolean turnScreenOff() {
		try {
			System.out.println ("Monitor Turning Off");
	        p = Runtime.getRuntime().exec("sudo tvservice -o");
	        p.waitFor();
	        return true;
		} catch (Exception e) {
			p.destroy();
			System.out.println ("The Screen Could not be Turned Off!");
			return false;
		}
	}
	
	
}
