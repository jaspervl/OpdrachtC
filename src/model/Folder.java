package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Folder<T> {

	private Node<T> first;
	private String rootName;

	public Folder(String directoryName) {
		first = new Node<T>(directoryName);
	}

	public void addFile(String[] parent,String name){
		if(parent == null || parent.length == 0){
		System.out.println("Parent is null or has no items");
		return;
		}
		Node<T> item = first ;
		for(String a : parent){
			if(item == null){
				System.out.println("Invalid parent path");
			}
			item = item.getItem(a);
		}
		
	
	}
	
	
//	public Node<T> contains(String name){
//		if(first.items.isEmpty()){
//			return null;
//		}
//		Stack<Node<T>> stack = new Stack<Node<T>>();
//		stack.push(first);
//		while(!stack.empty()){
//			Node<T> currentNode = stack.pop();
//			if(currentNode.getItem())
//		}
//		
//	}
	
	

	static class Node<T> {
		private HashMap<String,Node<T>> items;

		public Node(String name) {
			this.items = new HashMap<String,Node<T>>();
		}
		
		public Node(String name, HashMap<String,Node<T>> list) {
			this.items = list;
		}
		
		public Node<T> getItem(String item){
			return items.get(item);
		}
	}

}
