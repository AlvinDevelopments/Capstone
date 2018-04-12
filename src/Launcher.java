import java.awt.EventQueue;

public class Launcher {
	static Controller controller;
	static Motor thrust;
	static Motor servo;
	static IMU imu;
	
	static Controls control;
	static Settings setting;

	static String address = "192.168.7.2";
	static String sPort = "8089";
	static String hPort = "8090";

	public static void main(String[] arguments) throws Exception {

		// Graphic User Interface (GUI) is generated.
		EventQueue.invokeLater(new Runnable() {
			// GUI will be shown until the program is terminated.
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);

					control = new Controls();
					control.setVisible(false);

					setting = new Settings();
					setting.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// The program waits 50 milliseconds while the generated GUI is fully
		// initialized.
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		imu = new IMU();
		imu.start();

	}

	static void launch() throws Exception {

		controller = new Controller();
		controller.start();

		servo = new Motor(address, sPort);
		servo.start();

		thrust = new Motor(address, hPort);
		thrust.start();

//		System.out.println("PWM Controls initialized.\n");

	}

	static void setPorts(String IP, String hhPort, String ssPort) {
		System.out.println("Ports set to ");
		address = IP;
		sPort = ssPort;
		hPort = hhPort;
	}
	
	
}