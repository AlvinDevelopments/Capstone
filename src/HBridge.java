//import java.io.IOException;
//
//public class HBridge extends Thread
//{
//	static String fromBeagleBone, toBeagleBone;
//	private static int checkFlag = 0;
//
// 
//    public HBridge() throws IOException
//    {	
//    		initialize();
//    }
//    
//    public void initialize() throws IOException
//    {
//    		//System.out.println("PWM Controls initialized.");
//    		//Launcher.server[sliderNumber].sendMessage("thruster"+" "+sliderNumber+" connected.");
//    }
//    
//	@Override
//	public void run() 
//	{
//		//System.out.println("PWM Controls is active.");
//		while(true) {
//			if(checkDutyCycle())	{
//				 Launcher.hServer.sendMessage(newGUI.thruster_1.getValue(),newGUI.thruster_2.getValue(),newGUI.thruster_3.getValue());
//				checkFlag=0;
//			}
//		}
//	}
//	
//	public boolean checkDutyCycle() {
//		if(newGUI.thruster_1!=null&&checkFlag==1) {
//			return true;
//		}
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}
//	
//	public static void setFlagOn() {
//		checkFlag = 1;
//	}
//	
//	public static void setFlagOff() {
//		checkFlag = 0;
//	}
//}
