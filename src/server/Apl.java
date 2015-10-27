package server;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import model.Html;
import model.HttpResponseHeader;
import model.KeyValuePair;

public class Apl {

	private static ServerSocket socket;
	
	public static void main(String[] args) throws IOException{
		socket = new ServerSocket(8080);
		Html html = new Html(200,"OK",new File("index.html"),new KeyValuePair("Content-Type","text/html"));
		while(true){
			Socket userSocket = socket.accept();
			ClientThread thread = new ClientThread(userSocket);
			thread.run();
		
		}
		
	}
}
