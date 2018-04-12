import com.sun.media.protocol.vfw.VFWCapture;
import java.util.*;

import javax.media.*;

public class VFWAuto {

public VFWAuto() {
    Vector devices = (Vector) CaptureDeviceManager.getDeviceList(null).clone();
    Enumeration e = devices.elements();

    while (e.hasMoreElements()) {
        CaptureDeviceInfo cdi = (CaptureDeviceInfo) e.nextElement();
        String name = cdi.getName();
        if (name.startsWith("vfw:"))
            CaptureDeviceManager.removeDevice(cdi);
    }

    int nDevices = 0;
    for (int i = 0; i < 10; i++) {
        String name = VFWCapture.capGetDriverDescriptionName(i);
        if (name != null && name.length() > 1) {
            System.err.println("Found device " + name);
            System.err.println("Querying device. Please wait...");
            com.sun.media.protocol.vfw.VFWSourceStream.autoDetect(i);
            nDevices++;
        }
    }
}


}