package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Apl {

	private static ServerSocket socket;
	
	public static void main(String[] args) throws IOException{
		socket = new ServerSocket(8080);
		while(true){
			Socket userSocket = socket.accept();
			ClientThread thread = new ClientThread(userSocket);
			thread.run();
		
		}
		
	}
}
