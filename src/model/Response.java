package model;

import java.io.File;
import java.util.HashMap;

public final class Response {

	private static final HashMap<String, String> HTTP_PROTOCOL = new HashMap<>();
	
	/*
	 * Declaring finals ensure immutability
	 */
	private final String HTTP_HEADERS;
	private final String BODY;

	/*
	 * Assign the status codes of the protocol
	 */
	static {
		HTTP_PROTOCOL.put("100", "Continue");
		HTTP_PROTOCOL.put("101", "Switching Protocols");
		HTTP_PROTOCOL.put("200", "OK");
		HTTP_PROTOCOL.put("201", "Created");
		HTTP_PROTOCOL.put("202", "Accepted");
		HTTP_PROTOCOL.put("203", "Non-Authoritative Information");
		HTTP_PROTOCOL.put("204", "No Content");
		HTTP_PROTOCOL.put("205", "Reset Content");
		HTTP_PROTOCOL.put("206", "Partial Content");
		HTTP_PROTOCOL.put("300", "Multiple Choices");
		HTTP_PROTOCOL.put("301", "Moved Permanently");
		HTTP_PROTOCOL.put("302", "Found");
		HTTP_PROTOCOL.put("303", "See Other");
		HTTP_PROTOCOL.put("304", "Not Modified");
		HTTP_PROTOCOL.put("305", "Use Proxy");
		HTTP_PROTOCOL.put("306", "(Unused)");
		HTTP_PROTOCOL.put("307", "Temporary Redirect");
		HTTP_PROTOCOL.put("400", "Bad A");
		HTTP_PROTOCOL.put("401", "Unauthorized");
		HTTP_PROTOCOL.put("402", "Payment Required");
		HTTP_PROTOCOL.put("403", "Forbidden");
		HTTP_PROTOCOL.put("404", "Not Found");
		HTTP_PROTOCOL.put("405", "Method Not Allowed");
		HTTP_PROTOCOL.put("406", "Not Acceptable");
		HTTP_PROTOCOL.put("407", "Proxy Authentication Required");
		HTTP_PROTOCOL.put("408", "Request Timeout");
		HTTP_PROTOCOL.put("409", "Conflict");
		HTTP_PROTOCOL.put("410", "Gone");
		HTTP_PROTOCOL.put("411", "Length Required");
		HTTP_PROTOCOL.put("412", "Precondition Failed");
		HTTP_PROTOCOL.put("413", "Request Entity Too Large");
		HTTP_PROTOCOL.put("414", "Request-URI Too Long");
		HTTP_PROTOCOL.put("415", "Unsupported Media Type");
		HTTP_PROTOCOL.put("416", "Requested Range Not Satisfiable");
		HTTP_PROTOCOL.put("417", "Expectation Failed");
		HTTP_PROTOCOL.put("500", "Internal Server Error");
		HTTP_PROTOCOL.put("501", "Not Implemented");
		HTTP_PROTOCOL.put("502", "Bad Gateway");
		HTTP_PROTOCOL.put("503", "Service Unavailable");
		HTTP_PROTOCOL.put("504", "Gateway Timeout");
		HTTP_PROTOCOL.put("505", "HTTP Version Not Supported");

	}

	public Response(int status, FileParse file, KeyValuePair... httpHeaders) {
		String statusCode = status + "";
		String str = "HTTP/1.1 " + statusCode + " " + HTTP_PROTOCOL.get(statusCode) + "\r\n";
		str += "Content-Length: " + file.getSize() + "\r\n";
		str += "Content-Type: " + file.getMIME() + "\r\n";
		this.HTTP_HEADERS = str + addingHeaders(httpHeaders);
		BODY = file.getContent();
		System.out.println(this.toString());
	}

	/**
	 * Constuctor for a response that doesnt have a body
	 * @param status
	 * @param httpHeaders
	 */
	public Response(int status, KeyValuePair... httpHeaders) {
		String statusCode = status + "";
		String str = "HTTP/1.1 " + statusCode + " " + HTTP_PROTOCOL.get(statusCode) + "\r\n";
		str += "Content-Length: " + 0 + "\r\n";
		this.HTTP_HEADERS = str + addingHeaders(httpHeaders);
		BODY = ""; // Empty
	}

	private String addingHeaders(KeyValuePair... httpHeaders) {
		String str = "";
		for (KeyValuePair header : httpHeaders) {
			str += header.toHttpHeader() + "\r\n";
		}

		return str + "\r\n";
	}

	@Override
	public String toString() {
		return HTTP_HEADERS + BODY;
	}
	


}
