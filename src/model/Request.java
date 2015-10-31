package model;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Parsing class responsible for parsing incoming request(Constructor parameter
 * : string) For now the class will only take a couple of elements like the
 * METHOD USED : GET/POST/.. PATH : Path to be used in getting the file
 * HTTP_VERSION : Version of HTTP (?)
 * 
 * Included a map of the headers in case we want to do something with them.
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
	private final String HTTP_VERSION; // Unused
	private final HashMap<String, String> headers;

	public Request(String str) {
		headers = new HashMap<>();
		/*
		 * Hack : / Automatic resource allocation /
		 */
		try (Scanner requestScanner = new Scanner(str)) {
			String default_method = "GET";
			String default_path = "index.html";
			String default_version = "HTTP/1.1";
			// The first line is special since it contains the most important
			// elements for our response
			if(requestScanner.hasNextLine()) {
				String[] line = requestScanner.nextLine().split(" ");
				default_method = line[0]; // First thing we encounter
														// is the type
				String path = line[1].substring(1); // Second will be
																// the path with
																// /
				if (!path.equals("")) {
					default_path = path;
				}

				default_version = line[2];
			}
			/*
			 * Assign the final values
			 */
			REQUEST_METHOD = default_method;
			PATH = default_path;
			HTTP_VERSION = default_version;
			while (requestScanner.hasNextLine()) {
				String line = requestScanner.nextLine();
				String[] elements = line.split(":");
				if (elements.length > 1) {
					headers.put(elements[0], elements[1]);
				}
			}
		}

	}

	public String getHeader(String name) {
		return headers.get(name);
	}

	public String getAcceptedMime() {
		return "";

	}

	/*
	 * METHODS FOR CONVENIENCE
	 */
	public String getMethod() {
		return REQUEST_METHOD;
	}

	public String getPath() {
		return PATH;
	}

	public String getVersion() {
		return HTTP_VERSION;
	}

}
