
/**
 * The controller class contains various functions
 *
 */
public class Controller extends Thread{
	
	public Controller(){
		// TODO Auto-generated method stub
	}
	
	public void yawLeft(int degree) {
		// TODO Auto-generated method stub
	}
	
	public void yawRight(int degree) {
		// TODO Auto-generated method stub
	}
	
	public void rollLeft(int degree) {
		// TODO Auto-generated method stub
	}
	
	public void rollRight(int degree) {
		// TODO Auto-generated method stub
	}
	
	public void pitchLeft(int degree) {
		// TODO Auto-generated method stub
	}
	
	public void pitchRight(int degree) {
		// TODO Auto-generated method stub
	}
	
	public void moveForward(int thrust) {
		Launcher.servo.setMotorValues(500, 500, 500);
		Launcher.thrust.setMotorValues(thrust, thrust, thrust);
		GUI.servo_1.setValue(50);
		GUI.servo_2.setValue(50);
		GUI.servo_3.setValue(50);
		GUI.thruster_1.setValue(thrust);
		GUI.thruster_2.setValue(thrust);
		GUI.thruster_3.setValue(thrust);
	}
	
	public void moveBackward(int thrust) {
		Launcher.servo.setMotorValues(0, 0, 0);
		Launcher.thrust.setMotorValues(thrust, thrust, thrust);
		GUI.servo_1.setValue(0);
		GUI.servo_2.setValue(0);
		GUI.servo_3.setValue(0);
		GUI.thruster_1.setValue(thrust);
		GUI.thruster_2.setValue(thrust);
		GUI.thruster_3.setValue(thrust);
	}
	
	
	public void leftThrust(int dutyCycle) {
		// TODO Auto-generated method stub
	}
	
	public void rightThrust(int dutyCycle) {
		// TODO Auto-generated method stub
	}
	
	public void rearThrust(int dutyCycle) {
		// TODO Auto-generated method stub
	}
	
	public void leftTilt(int dutyCycle) {
		// TODO Auto-generated method stub
	}
	
	public void rightTilt(int dutyCycle) {
		// TODO Auto-generated method stub
	}
	
	public void rearTilt(int dutyCycle) {
		// TODO Auto-generated method stub
	}
	
	public void stopThrusters() {
		Launcher.thrust.setMotorValues(50, 50, 50);
	}
	
	
	
	public void sendCommand(String command){
		System.out.println(command);
		String[] commandArray = command.split("\n");
		String[][] commands = new String[commandArray.length][3];
		for( int i = 0; i < commandArray.length;i++) {
			commands[i] = commandArray[i].split(" ");
		}
		
		
		int i = 0;
		
		while(i<commands.length) {
			System.out.println("compare "+i);
			System.out.println(commands[i][0]);
			
			if(commands[i][0].compareTo("forward")==0) {
				moveForward(Integer.valueOf(commands[i][1]));
			}
			else if(commands[i][0].compareTo("backward")==0) {
				moveBackward(Integer.valueOf(commands[i][1]));
			}
			else if(commands[i][0].compareTo("left")==0) {
				if(commands[i][1].compareTo("thrust")==0) {
					GUI.thruster_1.setValue(Integer.valueOf(commands[i][2]));
				}
				else if(commands[i][1].compareTo("tilt")==0) {
					GUI.servo_1.setValue(Integer.valueOf(commands[i][2]));
				}
			}
			else if(commands[i][0].compareTo("rear")==0) {
				if(commands[i][1].compareTo("thrust")==0) {
					GUI.thruster_2.setValue(Integer.valueOf(commands[i][2]));
				}
				else if(commands[i][1].compareTo("tilt")==0) {
					GUI.servo_2.setValue(Integer.valueOf(commands[i][2]));
				}
			}
			else if(commands[i][0].compareTo("right")==0) {
				if(commands[i][1].compareTo("thrust")==0) {
					GUI.thruster_3.setValue(Integer.valueOf(commands[i][2]));
				}
				else if(commands[i][1].compareTo("tilt")==0) {
					GUI.servo_3.setValue(Integer.valueOf(commands[i][2]));
				}
			}
			
			else if(commands[i][0].compareTo("wait")==0) {
				try {
					Thread.sleep(Integer.valueOf(commands[i][1]));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
			i++;			
		}
	}


}
