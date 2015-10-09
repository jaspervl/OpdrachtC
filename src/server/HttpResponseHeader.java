package server;

import java.util.ArrayList;

public class HttpResponseHeader {
	
	private final String headerString;
	
	public HttpResponseHeader(int status,String phrase,ArrayList<KeyValuePair> headers){
		String str = "HTTP/1.1 " + status + " " + phrase + "\r\n";
		for (KeyValuePair header : headers) {
			str += header.toHttpHeader() + "\r\n";
		}
		headerString = str + "\r\n";
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return headerString;
	}

}
