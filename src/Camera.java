import java.util.Enumeration;
import java.util.Vector;

import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;

public class Camera {
	

	public static CaptureDeviceInfo run() {
	
	 VFWAuto vfwObj = new VFWAuto();

	    Vector devices = CaptureDeviceManager.getDeviceList(null);
	    Enumeration deviceEnum = devices.elements();

//	    System.out.println("Device count : " + devices.size());
//
	    while (deviceEnum.hasMoreElements()) {
	        CaptureDeviceInfo cdi = (CaptureDeviceInfo) deviceEnum.nextElement();
	        System.out.println("Device : " + cdi.getName());
	    }
	
	  devices = CaptureDeviceManager.getDeviceList(null);
	 CaptureDeviceInfo cam = (CaptureDeviceInfo)devices.get(0);
	 System.out.println(devices);
	 
	 	return cam;
	}

}
