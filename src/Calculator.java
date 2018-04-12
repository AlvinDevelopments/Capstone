
public class Calculator extends Thread {
	public boolean isRunning = true;
	public static double[] eulerAngle = new double[3];
	public static double[] position = new double[6];
	public double accelerationx_last, accelerationy_last, accelerationz_last;
	public double velocityx_last, velocityy_last, velocityz_last;
	public double positionx_last, positiony_last, positionz_last;
	public float timeSec;

	public Calculator() {
		// System.out.println("Hello, Calculator!");
	}

	// public void run()
	// {
	// while(isRunning)
	// {
	// eulerAngle = getAngle(TCPServer.bbData[0], TCPServer.bbData[1],
	// TCPServer.bbData[2], TCPServer.bbData[3]);
	// position = getPosition(TCPServer.bbData[4], TCPServer.bbData[5],
	// TCPServer.bbData[6]);
	// //System.out.println("Euler: " + eulerAngle[0]+" "+eulerAngle[1]+"
	// "+eulerAngle[2] //+ //" Velocity: " + position[0] +
	// /*" " + position[1] + " " + position[2]*///);
	//
	// GUI3.txtEulerRoll.setText(Double.toString(eulerAngle[0]));
	// GUI3.txtEulerPitch.setText(Double.toString(eulerAngle[1]));
	// GUI3.txtEulerYaw.setText(Double.toString(eulerAngle[2]));
	//
	// GUI3.txtAccX.setText(Double.toString(TCPServer.bbData[4]));
	// GUI3.txtAccY.setText(Double.toString(TCPServer.bbData[5]));
	// GUI3.txtAccZ.setText(Double.toString(TCPServer.bbData[6]));
	//
	// }
	// }
	/**
	 * Takes Quaternions and converts to Euler angles
	 * 
	 * @param x
	 *            - x in Quaternion
	 * @param y
	 * @param z
	 * @param w
	 * @return Returns an array of 3 length, where variable 0,1,2 are the x,y,z
	 *         euler anglers
	 */

	public double[] getAngle(double x, double y, double z, double w) {
		double[] result = new double[3];

		// Calculate Angle;
		double sqw = w * w;
		double sqx = x * x;
		double sqy = y * y;
		double sqz = z * z;

		result[0] = Math.atan2(2.0 * (x * y + z * w), (sqx - sqy - sqz + sqw)) * 180 / Math.PI;
		result[1] = Math.asin(-2.0 * (x * z - y * w) / (sqx + sqy + sqz + sqw)) * 180 / Math.PI;
		result[2] = Math.atan2(2.0 * (y * z + x * w), (-sqx - sqy + sqz + sqw)) * 180 / Math.PI;

		return result;
	}

	/**
	 * Takes the linear acceleration for each axis and estimates velocity and
	 * position Note: Error propogates very quickly and is very innacturate
	 * 
	 * @param ax
	 *            - Linear acceleration in the x axis
	 * @param ay
	 *            - "" Y axis
	 * @param az
	 *            - "" z axis
	 * @return An array of 6 length, where variable 0,1,2 are the velocities in the
	 *         x,y,z respectively, and variables 3,4,5 are the positions in x,y,z
	 */
	public double[] getPosition(double ax, double ay, double az) {
		double[] result = new double[6];
		double velocityx_now, velocityy_now, velocityz_now = 0;
		double positionx_now, positiony_now, positionz_now = 0;
		// Calculate Acceleration;
		// like result[0] = ....;
		// like result[1] = ....;
		// float time = TCPServer.timeDiff;
		// timeSec = time / 1000;
		double accx = 0;
		double accy = 0;
		double accz = 0;
		if (ax >= 0.2 || ax <= -0.2) {
			accx = ax;
		}
		if (ay >= 0.2 || ay <= -0.2) {
			accy = ay;
		}
		if (az >= 0.2 || az <= -0.2) {
			accz = az;
		}
		velocityx_now = velocityx_last + (accelerationx_last + ((accx - accelerationx_last) / 2)) * timeSec;
		velocityy_now = velocityy_last + (accelerationy_last + ((accy - accelerationy_last) / 2)) * timeSec;
		velocityz_now = velocityz_last + (accelerationz_last + ((accz - accelerationz_last) / 2)) * timeSec;
		// System.out.println(velocityx_now);
		positionx_now = positionx_last + (velocityx_last + ((velocityx_now - velocityx_last) / 2)) * timeSec;
		positiony_now = positiony_last + (velocityy_last + ((velocityy_now - velocityy_last) / 2)) * timeSec;
		positionz_now = positionz_last + (velocityz_last + ((velocityz_now - velocityz_last) / 2)) * timeSec;

		accelerationx_last = accx;
		accelerationy_last = accy;
		accelerationz_last = accz;

		velocityx_last = velocityx_now;
		velocityy_last = velocityy_now;
		velocityz_last = velocityz_now;

		positionx_last = positionx_now;
		positiony_last = positiony_now;
		positionz_last = positionz_now;

		result[0] = velocityx_now;
		result[1] = velocityy_now;
		result[2] = velocityz_now;

		result[3] = positionx_now;
		result[4] = positiony_now;
		result[5] = positionz_now;

		return result;
	}
}
