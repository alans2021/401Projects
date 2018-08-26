package assignment2;
import java.io.*;
import java.util.*;

public class Scramble {
	
	private File text;
	private boolean scrambled; //This variable is true when word in text file currently being read has been scrambled
	private String word; //Represents real word
	private Scanner read;
	private String scramble; //Represents scrambled word
	
	public Scramble(String s){
		try{
			text = new File(s);
			read = new Scanner(text);
		}
		catch(IOException e){
			System.out.println("File not found");
		}
	}
	
	public String getRealWord(){
		if(!read.hasNextLine()){ //word equals null at end of text file
			word = null;
			scrambled = false;
		}
		
		else if(scrambled || (!scrambled && word == null) ){ //If getScrambledWord() has been called, 
			//or if the first line has not been read yet, word will be set equal to what the Scanner reads  
			word = read.nextLine();
			scrambled = false; //Since word is now set to next word, scrambled should be set to false
		}
		return word;		
	}
	
	public String getScrambledWord(){
		if(scrambled) //If word already scrambled, same scrambled word returned
			return scramble;
		if(word == null) //If word is not anything yet, return null
			return null;
		Random index = new Random();
		StringBuilder s = new StringBuilder();
		StringBuilder inter = new StringBuilder(word); //Intermediate variable of real word
		int len = inter.length();
		while(len > 0){
			int ind = index.nextInt(len); //random index generated from 0 (inclusive) to len (exclusive)
			s.append(inter.charAt(ind)); //append character of index that is randomly generated
			inter.deleteCharAt(ind); //character at index removed from intermediate since that has already been used for the scramble
			len--; 
		}
		scramble = s.toString();
		scrambled = true;
		return scramble;
	}
	
	
	

}
