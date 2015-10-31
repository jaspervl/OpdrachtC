package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * PROGRAM DESCRIPTION:
 * A simple program to handle incoming requests and send appropiate responses
 * It features :
 * 
 * Capable of retrieving a file
 * Guarding against malicious urls / requests (Further testing required :D)
 * Securing a file with use of .htaccess (although implemented differently)
 *
 * 
 * 
 * There was no real reason to make the server an object since there is no communication between clients.
 * @author jasper
 *
 */
public class Apl {

	private static ServerSocket socket;
	private static final int SERVER_PORT = 8080;
	{
		
	}
	public static void main(String[] args) throws IOException{
		socket = new ServerSocket(SERVER_PORT);
		while(true){
			Socket userSocket = socket.accept();
			ClientThread thread = new ClientThread(userSocket);
			thread.run();
		
		}
		
	}
}
