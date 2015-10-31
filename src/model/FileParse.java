package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Our basic file parsing class. It extracts the contents of a file and its mime type while disgarding the rest
 * 
 * @author jasper
 *
 */
public class FileParse {

	/*
	 * Instance variables (CONTENT : FILE CONTENTS
	 * MIME = TYPE OF FILE
	 */
	private final String content;
	private final String MIME;
	
	/*
	 * Using the extension of files to look up the appropiate mime. If it can't be found we'll try to figure it out
	 * By looking at the request
	 */
	private static final HashMap<String,String> MIMES;
	
	/*
	 * Give the MIME map some initial values
	 * 
	 * (Althought MIME types aren't that important for recent browsers that can
	 * interpret the files type on their own)
	 */
	static{
		MIMES = new HashMap<>();
		MIMES.put("txt", "text/plain");
		MIMES.put("html", "text/html");
		MIMES.put("htm", "text/html");
		MIMES.put("gif", "image/gif ");
		// text/javascript instead of the official application/javascript for compatibility reasons
		MIMES.put("js", "text/javascript");
		MIMES.put("css", "text/css");
	}

	public FileParse(File file) {
		/* were assuming that most paths will not contain any . 
		 * 
		 * Just to save were going to take the last element which has to be the file type
		 */ 
		String[] path = file.getName().split("\\.");
		String temp_mime = path[path.length -1];
		MIME = MIMES.get(temp_mime);
		String progress = "";
		try (Scanner sc = new Scanner(file)) {

			while (sc.hasNextLine()) {
				progress += sc.nextLine() + "\r\n";
			}

		} catch (FileNotFoundException e) {
			System.out.println(progress);

			
		} finally {
			content = progress;
		}
	}

	/**
	 * Gets the string content
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Gets the MIME 
	 * @return
	 */
	public String getMIME() {
		return MIME;
	}
	

	/**
	 * Gets the size of the content (body) 
	 * @return
	 */
	public int getSize() {
		return content.getBytes().length;
	}
}
