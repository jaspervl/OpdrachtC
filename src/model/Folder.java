package model;

import java.util.HashMap;

public class Folder<T> {

	private String rootName;
	private Node<T> first;

	public Folder(String directoryName) {
		first = new Node<T>();
	}

	public void addFolderFile(String[] parent,String folderName){
		Node<T> parentDirectory = getDirectory(parent);
		if(parentDirectory == null){
			System.out.println("No parent directory found : addFile ");
			return;
		}
		parentDirectory.addFolder(folderName);
	}
	
	public void addFile(T resource){
		
	}
	
	/**
	 * Iterates through an array of strings (the path to the parent folder
	 * 
	 * @param parent
	 * @return
	 */
	public Node<T> getDirectory(String[] parent){
		if(parent == null || parent.length == 0){
		System.out.println("Parent is null or has no items");
		return null;
		}
		Node<T> item = first ;
		for(String a : parent){
			if(item == null){
				System.out.println("Invalid parent path");
			}
			item = item.getFolder(a);
		}
		return item;
		
		
	
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
	
	
	/**
	 * Every node contains two maps  :
	 * 
	 * One map holds the directories (which contains multiple nodes of its own and those..etc etc)
	 * 
	 * Another map holds the files current available in the map (resource).
	 * @author jasper
	 *
	 * @param <T> (Resource type)
	 */
	static class Node<T> {
		private HashMap<String,Node<T>> subDir;
		private HashMap<String,T> files;

		public Node() {
			this.subDir = new HashMap<String,Node<T>>();
			files = new HashMap<String,T>();
		}
		
		
		public Node<T> getFolder(String item){
			return subDir.get(item);
		}
		
		/**
		 * Gets a resource of type T out of 
		 * @param item
		 * @return
		 */
		public T getFile(String item){
			return files.get(item);
		}
		
		public void addFolder(String name){
			subDir.put(name, new Node<T>());
		}
		
		public void addFile(String name,T resource){
			files.put(name, resource);
		}
	}

}
