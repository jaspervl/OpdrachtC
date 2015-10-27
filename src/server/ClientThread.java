package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import model.Html;

public class ClientThread extends Thread {
	private Socket socket;

	public ClientThread(Socket socket) {
		this.socket = socket;

	}

	@Override
	public void run() {
		Scanner in;

		try {
			in = new Scanner(socket.getInputStream());
			while (socket.isConnected()) {
				while(in.hasNextLine()){
					System.out.println(in.nextLine());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendPage(Html html) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(socket.getOutputStream());
			writer.print(html);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
