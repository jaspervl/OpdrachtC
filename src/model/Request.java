package model;

import java.nio.file.Files;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Parsing class responsible for parsing incoming request(Constructor parameter : string)
 * For now the class will only take a couple of elements like the
 * METHOD USED :  GET/POST/.. 
 * PATH : Path to be used in getting the file
 * HTTP_VERSION : Version of HTTP (?)
 * 
 * @author jasper
 *
 */
public class Request {

	/*
	 * Headers that arent key value pairs
	 */
	private final String PATH;
	private final String REQUEST_METHOD;
	private final String HTTP_VERSION;
	private final HashMap<String,String[]> headers;
	
	public Request(String str){
		headers = new HashMap<>();
		/*
		 * Hack : / Automatic resource allocation /
		 */
		try(Scanner lineScanner = new Scanner(str)){
		REQUEST_METHOD = lineScanner.next();
		PATH = lineScanner.next().substring(1);
		HTTP_VERSION = lineScanner.next();
		while(lineScanner.hasNext())
		headers.put(lineScanner.next(), lineScanner.next().split(","));
		}
		
	}

	
	public String[] getHeader(String name){
		return headers.get(name);
	}
	
	/*
	 * METHODS FOR CONVENIENCE
	 */
	public String getMethod(){
		return REQUEST_METHOD;
	}
	
	public String getPath(){
		return PATH;
	}
	

}
