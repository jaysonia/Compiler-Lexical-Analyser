package Components;

import java.util.Map;

public class TrieNode {
	
	private int id;
	private boolean accepting;
	private Map<Character, TrieNode> children;
	
	//setup root node
	public TrieNode(){
		id=0;
		children =null;
		accepting = false;
		return;
	}
	
	public TrieNode(int id){
		this.id = id;
		accepting = false;
		return;
	}
	
	public TrieNode addChild(char letter){
		children.put(letter, new TrieNode(id++));
		return children.get(letter);
	}
	
	public boolean isAccepting(){
		return accepting;
	}
	
	public int getId(){
		return id;
	}
	
	public boolean isChild(char c){
		if (children.containsKey(c))
			return true;
		else
			return false;
	}
	
	public void setAccepting(){
		accepting = true;
	}
	
	public TrieNode getChild(char c){
		return children.get(c);
	}
}
