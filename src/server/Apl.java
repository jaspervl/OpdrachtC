package server;

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
		ArrayList<KeyValuePair> header = new ArrayList<>();
		ArrayList<KeyValuePair> body = new ArrayList<>();
		ArrayList<KeyValuePair> httpHeader = new ArrayList<>();
		httpHeader.add(new KeyValuePair("Content-Type","text/html"));
		header.add(new KeyValuePair("title","eerste html pagina"));
		body.add(new KeyValuePair("h1","eerste html pagina"));
		Html html = new Html(header,body);
		httpHeader.add(new KeyValuePair("Content-Length",html.toString().getBytes().length + ""));
		HttpResponseHeader headers = new HttpResponseHeader(200,"OK",httpHeader);
		String htmlString = headers.toString() + html.toString();
		while(true){
			
			Socket userSocket = socket.accept();
			Scanner in = new Scanner(userSocket.getInputStream());
			while(in.hasNextLine()){
				System.out.println(in.nextLine());
			}
			ClientThread thread = new ClientThread(userSocket,htmlString);
			thread.run();
		
		}
		
	}
}
