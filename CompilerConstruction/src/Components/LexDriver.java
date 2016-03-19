/*
	Author: Jason O'Connell
	Student number: 13710859
*/

package Components;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class LexDriver {
	
	private SymbolTable symbolTable = new SymbolTable();
	private Vector<LexToken> tokens = new Vector<LexToken>();
	
	//class constructor
	public LexDriver(){}
	
	//read the file character by character
	public void readFile(String filename){
		int c;
		int state =0;
		try{
			FileInputStream in = new FileInputStream(filename);
			BufferedReader input = new BufferedReader(new InputStreamReader(in));
			c=input.read();	//read in the first character
			while(c!=-1){
					switch(state){
						case 0: 
							state = case0(c);
							break;
						case 1:	//integer
							c = numberCalc(c,input);
							state=0;
							break;
						case 2:	//id
							c = Id(c,input);
							state=0;
							break;
						case 3: //left parenthesis
							tokens.addElement(new LexToken("LPAR",0));
							c=input.read();
							state=0;
							break;
						case 4: //right parenthesis
							tokens.addElement(new LexToken("RPAR",0));
							c=input.read();
							state=0;
							break;
						case 5:	//semicolon
							tokens.addElement(new LexToken("SEMICOLON",0));
							c=input.read();
							state=0;
							break;
						case 6: //space delimiter
							c=input.read();
							state=0;
							break;
						case 7: //String
							c = stringCreator((char)c,input);
							state=0;
							break;
						case 8:	//new line id
							c=input.read();
							state=0;
							break;
						default:	//for unexpected characters
							tokens.addElement(new LexToken("ERROR",0));
							c=input.read();
							state=0;
							break;
					}
			}
			input.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	//method to calculate the value of an integer
	private int numberCalc(int c, BufferedReader input) throws IOException{
		int number = 0;
		while (c>=48 &&c<=57){
			if(number < 6553)
				number = (number*10)+(c-48);
			else if (number == 6553 && (c-48 <=5))
				number = (number*10)+(c-48);
			else{
				number =-1;
				c = input.read();
				break;
			}
			c = input.read();
		}
		if(number == -1)
			tokens.addElement(new LexToken("ERROR",0));
		else
			tokens.addElement(new LexToken("INT",number));
		return c;
	}
	
	//creates a string letter by letter
	private int stringCreator(int  c, BufferedReader input) throws IOException{
		Vector<Character> word = new Vector<Character>();
		c= input.read();
		while (c!='"'){
			if(c=='~'){
				word.add((char)input.read());
			}
			else{
				word.add((char)c);
			}
			c= input.read();
			if(c==-1)
				break;
		}
		if(c ==-1)
			tokens.addElement(new LexToken("ERROR",0));
		else{
			tokens.addElement(new LexToken("STRING",word));
		}
		c= input.read();
		return c;
	}
	
	//checks the base case for the switch and returns an integer
	private int case0(int c){
		if(c>='0' && c<='9')	//integers
			return 1;
		else if((c>='a' && c<='z')||(c>=65 && c<= 90))	//Id's
			return  2;
		else if (c=='(')		//lpar
			return 3;
		else if (c==')')		//rpar
			return 4;
		else if(c==';')			//semicolon
			return 5;
		else if (c==' ')		//space
			return 6;
		else if (c=='"')		//string
			return 7;
		else if (c == 10)		//new line
			return 8;
		else
			return -1;
	}
	
	//checks id and add's a token to the vector
	private int Id(int c, BufferedReader input) throws IOException{
		boolean dynamic = true;
		int value;
		if(c>='A' &&c<='Z'){
			dynamic = false;
			symbolTable.search((char)c, dynamic);
			c = input.read();
		}
		while(c>=97 && c<=122){
			symbolTable.search((char)c, dynamic);
			c = input.read();
		}
		value = symbolTable.Id(dynamic);
		if(value == -1)
			tokens.addElement(new LexToken("ERROR",0));
		else
			tokens.addElement(new LexToken("ID",value));
		return c;
	}
	
	//prints all the tokens
	public void print(){
		for(LexToken current: tokens){
			System.out.println(current);
		}
	}
}