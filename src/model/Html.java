package model;

import java.util.ArrayList;

public final class Html {
	private final String HEAD;
	private final String BODY;
	
	public Html(ArrayList<KeyValuePair> head,ArrayList<KeyValuePair> body){
		String strHead = "";
		String strBody = "";
		for(KeyValuePair pair : head){
			strHead += pair.toString();
		}
		for(KeyValuePair pair : body){
			strBody += pair.toString();
		}
		HEAD = "<html><head>" + strHead + "</head>";
		BODY = "<body>" + strBody + "</body></html>";
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "<!DOCTYPE html>" + HEAD + BODY;
	}
}
