package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Base64;

import model.FileParse;
import model.KeyValuePair;
import model.Request;
import model.Response;

public class ClientThread extends Thread {
	
	
	private Socket socket;

	public ClientThread(Socket socket) {
		this.socket = socket;

	}

	@Override
	public void run() {
		System.out.println("SESSION CREATED");
		BufferedReader in;

		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while (socket.isConnected()) {
				/*
				 * START READING THE REQUEST
				 */
				String req = "";
				String inputLine;
				while (!(inputLine = in.readLine()).equals("")){
				    req += inputLine + "\r\n";
				}
				System.out.println(req);
				/*
				 * STOP READING THE REQUEST
				 * 
				 * PARSE THE STRING TO A REQUEST OBJECT
				 */
				Request request = new Request(req);
				Response response;
				/*
				 * RETRIEVE PATH FROM REQUEST EN SEARCH FOR IT WITHIN OUR FILE SYSTEM
				 */
					File file = new File(request.getPath());
					int statusCode = 200;
					if(file.exists()){
						statusCode = 404;
					}
					boolean found = false;
					for(File f : file.getParentFile().listFiles()){
						System.out.println(f.getName());
						if(f.getName().equals(".htaccess")){
							String header = request.getHeader("Authorization");
							if(header != null){
								String value = header.split(" ")[0];
								System.out.println("VALUE :" + value);
								value = new String(Base64.getDecoder().decode(value));
								System.out.println(value);
							}
							found = true;
							statusCode = 401;
						}
					}
					if(found){
						response = new Response(statusCode,new KeyValuePair("WWW-Authenticate","basic realm=\"MY AWESOME SITE\""));
					}
					else{
						FileParse parse = new FileParse(file);
						response = new Response(parse.getStatus(),parse);
					}
					
					sendPage(response);
			
				
				/*
				 * PARSE THE NEW FILE (Object is for our convenience)
				 */
				
					
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendPage(Response resource) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(socket.getOutputStream());
			writer.print(resource);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
