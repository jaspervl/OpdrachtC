package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
		while(true){
			Socket userSocket = socket.accept();
			PrintWriter writer = new PrintWriter(userSocket.getOutputStream());
			Html html = new Html(header,body);
			httpHeader.add(new KeyValuePair("Content-Length",html.toString().getBytes().length + ""));
			HttpResponseHeader headers = new HttpResponseHeader(200,"OK",httpHeader);
			writer.print(headers.toString() + html.toString() );
			writer.flush();
		}
		
	}
}
