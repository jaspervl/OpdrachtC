package server;

public final class KeyValuePair {
	private final String KEY;
	private final String VALUE;
	
	public KeyValuePair(String key,String value){
		KEY = key;
		VALUE = value;
	}
	
	public String toHttpHeader(){
		return KEY + ":" + VALUE;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "<" + KEY + ">" + VALUE + "</" + KEY + ">";
	}
}
