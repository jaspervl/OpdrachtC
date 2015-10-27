package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class Html {
	private final String headers;
	private String content = "";

	public Html(int status, String phrase, File file, KeyValuePair... httpHeaders) {
		try (Scanner sc = new Scanner(file)) {

			while (sc.hasNextLine()) {
				this.content += sc.nextLine() + "\r\n";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File wasn't found derp");
		}
		String str = "HTTP/1.1 " + status + " " + phrase + "\r\n";
		str += "Content-Length : " + content.toString().getBytes().length + "\r\n";
		for (KeyValuePair header : httpHeaders) {
			str += header.toHttpHeader() + "\r\n";
		}

		this.headers = str + "\r\n";

	}

	@Override
	public String toString() {
		return headers + content;
	}
}
