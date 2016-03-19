/*
	Author: Jason O'Connell
	Student number: 13710859
*/
package Components;

import java.util.Vector;

public class LexToken {
	private String type =null;
	private Vector<Character> attribute = null;
	private String attr= null;
	private int number = -1;
	
	public LexToken(String type,int number){
		this.type = type;
		this.number = number;
	}
	
	public LexToken(String type,Vector<Character> attr){
		attribute = new Vector<Character>();
		this.type = type;
		this.attribute = attr;
	}
	public LexToken(String type,String attr){
		this.type = type;
		this.attr = attr;
	}
	
	public String toString(){
		String value ="";
		if (attribute ==null && attr ==null)
			return "<"+type+"," + number+">";
		else if (attribute ==null && number ==-1)
			return "<"+type+"," + attr+">";
		else{
			for(Character c: attribute){
				value += c;
			}
			return "<"+type+"," + value+">";
		}
	}

}
