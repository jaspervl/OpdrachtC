package model;

/**
 * While we could've used something else as key value pair (like another hashmap),we also wanted
 * to create single instances that held one. 
 * 
 * @author jasper
 *
 */
public final class KeyValuePair {
	private final String KEY;
	private final String VALUE;
	
	
	/**
	 * Simple constructor assigning values
	 * 
	 * @param key 
	 * @param value
	 */
	public KeyValuePair(String key,String value){
		KEY = key;
		VALUE = value;
	}
	
	/**
	 * Returns the key value pair formatted as an http header
	 * @return
	 */
	public String toHttpHeader(){
		return KEY + ":" + VALUE;
	}
	
	/**
	 * Legacy code. It was first used to generate a HTML page
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "<" + KEY + ">" + VALUE + "</" + KEY + ">";
	}
}
