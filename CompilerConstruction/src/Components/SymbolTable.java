/*
	Author: Jason O'Connell
	Student number: 13710859
*/
package Components;

public class SymbolTable {
	private static int id;
	final TrieNode root;
	private final String[] RESERVED = {"Private","Public","Protected","Static","Primary","Integer","Exception","Try"};
	private static TrieNode currentNode;
	
	public SymbolTable(){
		root = new TrieNode();
		id=0;
		//implement the list of static Symbols
		for(int i=0;i< RESERVED.length;i++){
			stringProcess(RESERVED[i]);
		}
	}
	
	//set this as private as it is only used to add the static ID's
	private int stringProcess(String word){
		currentNode = root;
		
		for(int i=0;i< word.length();i++){
			search(word.charAt(i), true);
		}
		return Id(true);
	}
	
	public void search(char c, boolean dynamic){
		if(currentNode != null){
			if(currentNode.isChild(c)){
				currentNode = currentNode.getChild(c);
			}
			else{
				if(dynamic == true){
					currentNode.addChild(c);
					currentNode = currentNode.getChild(c);
				}
				else{
					//if no child exists set the current node to null
					currentNode = null;
				}
			}
		}
		else
			return;
	}
	
	//sets the id of the current node if it is dynamic
	public int Id(boolean dynamic){
		int returnid;
		if(currentNode == null ||(currentNode.getId() == 0 && dynamic == false)){
			currentNode = root;
			return -1;
		}
		else if(currentNode.getId() == 0 && dynamic == true){
			id++;
			currentNode.setAccepting(id);
		}
		returnid = currentNode.getId();
		currentNode = root;
		
		return returnid;
	}
}
