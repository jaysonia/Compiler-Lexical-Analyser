/*
	Author: Jason O'Connell
	Student number: 13710859
*/
package Components;

import java.util.HashMap;

public class TrieNode {
	
	private int id;
	private HashMap<Character, TrieNode> children;
	
	//Constructor
	public TrieNode(){
		id = 0;
		children = new HashMap<Character, TrieNode>();
		return;
	}
	
	public TrieNode addChild(char letter){
		children.put(letter, new TrieNode());
		return children.get(letter);
	}
	
	public TrieNode getChild(char c){
		return children.get(c);
	}
	
	public int getId(){
		return id;
	}
	
	public boolean isChild(Character c){
		return children.containsKey(c);
	}
	
	public void setAccepting(int id){
		this.id = id;
	}
}
