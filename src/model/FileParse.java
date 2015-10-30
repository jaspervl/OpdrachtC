package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.activation.MimetypesFileTypeMap;

public class FileParse {

	private final int size;
	private final int status;
	private final String content;
	private final String MIME;

	public FileParse(File file) {
		int initialStatusCode = 200;
		MIME = new MimetypesFileTypeMap().getContentType(file);
		String progress = "";
		try (Scanner sc = new Scanner(file)) {

			while (sc.hasNextLine()) {
				progress += sc.nextLine() + "\r\n";
			}

		} catch (FileNotFoundException e) {
			System.out.println(progress);
			initialStatusCode = 400;
			
		} finally {
			content = progress;
			size = progress.getBytes().length;
			status = initialStatusCode;
		}
	}

	public String getContent() {
		return content;
	}

	public String getMIME() {
		if(!MIME.contains("html")){
			return "text/css";
		}
		return MIME;
	}
	
	public int getStatus(){
		return status;
	}

	public int getSize() {

		return size;
	}
}
