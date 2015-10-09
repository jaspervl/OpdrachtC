package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread{
	private Socket socket;
	private String html;
	
	
	public ClientThread(Socket socket,String html){
	this.socket = socket;
	this.html = html;
	
	}
	
	@Override
	public void run(){
		PrintWriter writer;
		try {
			writer = new PrintWriter(socket.getOutputStream());
			writer.print(html);
			writer.flush();
			while(true){
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
