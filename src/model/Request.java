package model;

import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
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
	private final HashMap<String,String> headers;
	
	public Request(String str){
		headers = new HashMap<>();
		/*
		 * Hack : / Automatic resource allocation /
		 */
		try(Scanner lineScanner = new Scanner(str)){
		REQUEST_METHOD = lineScanner.next();
		String redirect = lineScanner.next().substring(1);
		if(redirect.equals("")){
			PATH = "index.html";
		}
		else{
			PATH = redirect;
		}
		HTTP_VERSION = lineScanner.next();
		while(lineScanner.hasNextLine()){
		String line = lineScanner.nextLine();
		String[] elements = line.split(" ");
		headers.put(elements[0], line.substring(elements[0].length()));
		}
		for(Map.Entry<String,String> entry: headers.entrySet()){
			System.out.println(entry.getKey() + entry.getValue());
		}
	}
	}

	public String[] generateArray(int offset,String... arr){
		String[] newArr = new String[arr.length - offset];
		for(int i = 0;i < newArr.length;i++){
			newArr[i] = arr[i + offset];
		}
		return newArr;
		
	}
	
	public String getHeader(String name){
		return headers.get(name + ":");
	}
	

	public String getAcceptedMime(){
		return "";
		
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
