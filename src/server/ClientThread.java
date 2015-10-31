package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Base64;
import java.util.Scanner;

import model.FileParse;
import model.KeyValuePair;
import model.Request;
import model.Response;

/**
 * A thread that handles all the incoming requests and responses of a client. 
 * 
 * @author jasper
 *
 */
public class ClientThread extends Thread {

	private Socket socket;
	/**
	 * Set the socket we'll be using in the thread
	 * @param socket
	 */
	public ClientThread(Socket socket) {
		this.socket = socket;


	}

	/**
	 * Our run method: This is responsible for handling every individual client. S
	 */
	@Override
	public void run() {
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			while (socket.isConnected()) {
				/*
				 * START READING THE REQUEST
				 * 
				 * Initial empty strings to prevent a null pointer exception from occuring
				 */
				String req = "";
				String inputLine = "";

				while (in != null && (inputLine = in.readLine())!= null && !inputLine.equals("")) {
			
					req += inputLine + "\r\n";
				}
				/*
				 * STOP READING THE REQUEST
				 * 
				 * PARSE THE STRING TO A REQUEST OBJECT
				 */
				Request request = new Request(req);
				Response response;
				/*
				 * RETRIEVE PATH FROM REQUEST EN SEARCH FOR IT WITHIN OUR FILE
				 * SYSTEM
				 */
				File file = new File(request.getPath());
				int statusCode = 200; // Initial state of the status code = 200
				boolean found = false;  // This found actually implies an .htaccess has been found in the surrounding directory
				if (file != null && !file.isFile()) {
					statusCode = 404; // File doesn't exist, give status code 404 not found
				}
				else if(file.getParentFile() != null && file.getParentFile().isDirectory()) {		// Get parent file, check whether its a directory and exists
					for (File f : file.getParentFile().listFiles()) {  // Get all of he surrounding files 
						if (f.getName().equals(".htaccess")) {   // Check whether it equals .htaccess
							String header = request.getHeader("Authorization");
							found = true;
							statusCode = 401;
							if (header != null) {     // Check whether the client has an authorization header
								/*
								 * Decode part
								 */
								String[] header_array = header.split(" "); //   ::    Take the string split it on " "
								String value = header_array[header_array.length -1]; // We only need the last element discard the rest
								value = new String(Base64.getDecoder().decode(value));  // Decode it with base 64
								try(Scanner fileScanner = new Scanner(f)){  // Attach a scanner to the .htaccess file
									found = true;
										while (fileScanner.hasNextLine()) {
												String str = fileScanner.nextLine();
													if (str.contains(value)) {    // Match found
														/*
														 * Person was authorized , removing the false claim and sending him the file
														 */
														found = false;
														statusCode = 200;
												}
										}
								}
								
							}
						}
					}
				}
				if (found) { // IF there was an .htaccess file we'll send a special response to the server
					response = new Response(statusCode,new KeyValuePair("WWW-Authenticate", "basic realm=\"MY AWESOME SITE\""));
				} else {
					FileParse parse = new FileParse(file);  // After all the checks we can finally parse the file and create our response
					response = new Response(statusCode, parse);
				}

				sendPage(response); // Send the response to the client
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Sends the responses 
	 * Calls to string on the method and uses the sockets output stream to send them
	 * 
	 * @param resource   the response
	 */
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
