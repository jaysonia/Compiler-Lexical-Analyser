package Components;

public class TrieImplemt {
	
	final TrieNode root;
	
	public TrieImplemt(){
		root = new TrieNode();
	}
	
	public int WordCheck(String word){
		if(word.charAt(0) >= 'A' && word.charAt(0) <= 'Z')
			return sTrie(word);
		else
			return dTrie(word);
	}
	
	//static Trie
	private int sTrie(String word){
		TrieNode currentNode;
		char currentLetter;
		boolean found = true;
		
		currentNode = root;
		for(int i=0; (i< word.length()) && found;i++){
			currentLetter = word.charAt(i);
			if(currentNode.isChild(currentLetter)){
				currentNode = currentNode.getChild(currentLetter);
			}
			else
				found = false;
		}
		if(!currentNode.isAccepting())
			return -1;
		
		return currentNode.getId();
	}
	
	//Dynamic Trie
	private int dTrie(String word){
		TrieNode currentNode;
		char currentLetter;
		boolean found = true;
		
		currentNode = root;
		for(int i=0; (i< word.length()) && found;i++){
			currentLetter = word.charAt(i);
			if(currentNode.isChild(currentLetter)){
				currentNode = currentNode.getChild(currentLetter);
			}
			else
				currentNode.addChild(currentLetter);
		}
		currentNode.setAccepting();
		
		return currentNode.getId();
	}

}
