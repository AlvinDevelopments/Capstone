import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class TCPServer extends Thread {
	private String input = "";
    private Socket socket;
    Scanner scanner;
    PrintWriter out;
    public TCPServer(String address, String socketNumber) throws Exception {
    	
        try{
        		this.socket = new Socket( InetAddress.getByName(address), Integer.parseInt(socketNumber));
        		System.out.println("test");
        }
        catch(Exception e) {
        		System.out.println("Cannot find Beaglebone device...");
//        		System.exit(0);
        		return;
        }
        out = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("\r\nConnected to: " + socket.getInetAddress() + ": " +socket.getPort());  
    }

    public void run() {
        while (true) {
//            try {
//				out = new PrintWriter(this.socket.getOutputStream(), true);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
        		//System.out.print(input);
        		if(!input.matches("")) {
        			//System.out.print(input);
        			out.print(input);
        		}
            input = "";
            out.flush();
            try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }   
    
    public void sendMessage(int value1, int value2, int value3) {
    		input = Integer.toString(value1)+" "+Integer.toString(value2)+" "+Integer.toString(value3);
    		System.out.println(input);
    }
    
    
}
