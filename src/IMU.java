import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class IMU extends Thread {

	private double x;
	private double y;
	private double z;
	private double w;
	private double txtFieldZ, txtFieldSpeed, txtFieldNose, txtFieldRoll, txtFieldIP, txtEulerRoll, txtEulerPitch,
			txtEulerYaw, txtAccX, txtAccY, txtAccZ;

	private ServerSocket socket;
	InputStream in;
	String input;

	public IMU() throws UnknownHostException, IOException {
		this.socket = new ServerSocket(8097);
		initialize();
	}

	public void run() {
		Socket connectionSocket = null;
		while (true) {
			System.out.println("waiting on connection");

			try {
				connectionSocket = socket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedReader inFromClient = null;
			try {
				inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			while (true) {
				try {
					input = inFromClient.readLine();
					if (input == null) {
						break;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				updateIMU(input);
				updateGUI();

				input = null;
				// connectionSocket.getInputStream().flush()
			}
		}

	}

	private void initialize() {
		System.out.println("IMU initialized.\n");
	}

	private void updateIMU(String input) {
		System.out.println(input);
		String[] data = input.split(" ");
		txtFieldZ = Double.parseDouble(data[0]);
		txtFieldSpeed = Double.parseDouble(data[1]);
		txtFieldNose = Double.parseDouble(data[2]);
		txtFieldRoll = Double.parseDouble(data[3]);
		txtFieldIP = Double.parseDouble(data[4]);
		txtEulerRoll = Double.parseDouble(data[5]);
		txtEulerPitch = Double.parseDouble(data[6]);
		txtEulerYaw = Double.parseDouble(data[0]);
		txtAccX = Double.parseDouble(data[0]);
		txtAccY = Double.parseDouble(data[0]);
		txtAccZ = Double.parseDouble(data[0]);

	}

	private void updateGUI() {
		GUI.txtFieldZ.setText(Double.toString(this.txtFieldZ));
		// newGUI.txtFieldSpeed.setText(Double.toString(this.txtFieldSpeed));
		GUI.txtFieldNose.setText(Double.toString(this.txtFieldNose));
		GUI.txtFieldRoll.setText(Double.toString(this.txtFieldRoll));
		// newGUI.txtFieldIP.setText(Double.toString(this.txtFieldIP));
		GUI.txtEulerRoll.setText(Double.toString(this.txtEulerRoll));
		GUI.txtEulerPitch.setText(Double.toString(this.txtEulerPitch));
		GUI.txtEulerYaw.setText(Double.toString(this.txtEulerYaw));
		GUI.txtAccX.setText(Double.toString(this.txtAccX));
		GUI.txtAccY.setText(Double.toString(this.txtAccY));
		GUI.txtAccZ.setText(Double.toString(this.txtAccZ));
	}

}
