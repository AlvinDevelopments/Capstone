import java.io.IOException;

public class Motor extends Thread
{
	private int checkFlag = 0;
	private TCPServer server;
	private int motor1, motor2, motor3;

    public Motor(String address, String socketNumber) throws IOException
    {	
    		initialize(address, socketNumber);
    }
    
    public void initialize(String address, String socketNumber) throws IOException
    {
    		System.out.println("Motor initialized.");
    		try {
				server = new TCPServer(address, socketNumber);
				server.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
	@Override
	public void run() 
	{
		while(true) {
			if(checkDutyCycle())	{		
				 server.sendMessage(motor1, motor2, motor3);
				checkFlag=0;
			}
		}
	}
	
	public boolean checkDutyCycle() {
		if(GUI.thruster_1!=null&&checkFlag==1) {
//			dutyCycle = newGUI.sliders[sliderNumber].getValue();
			return true;
		}
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	

	public void setFlagOn(int value1, int value2, int value3) {
		setMotorValues(value1,value2,value3);
		checkFlag = 1;
		
	}
	
	public void setFlagOff() {
		checkFlag = 0;
	}
	
	public void setMotorValues(int value1, int value2, int value3) {
		motor1 = value1;
		motor2 = value2;
		motor3 = value3;
		
		checkFlag = 1;
	}
}
