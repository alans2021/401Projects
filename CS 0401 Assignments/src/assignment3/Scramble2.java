package assignment3;
import java.io.*;
import java.util.*;

public class Scramble2 {
	
	private File text;
	private boolean scrambled; //This variable is true when word in text file currently being read has been scrambled
	private String word; //Represents real word
	private Scanner read;
	private String scramble; //Represents scrambled word
	private ArrayList<String> list = new ArrayList<String>();
	private int index; //Represents max index that can be read from the ArrayList
	
	public Scramble2(String s){
		try{
			text = new File(s);
			read = new Scanner(text);
			while(read.hasNextLine()) //As long as scanner detects there are words in the file, loop runs
				list.add(read.nextLine()); //Next word added to the arraylist
			index = list.size();
		}
		catch(IOException e){
			System.out.println("File not found");
		}
	}
	
	public String getRealWord(){
		if(index < 1){ //word equals null when all words read, therefore max index is less than 1
			word = null;
			scrambled = false;
		}
		
		else if(scrambled || (!scrambled && word == null) ){ //If getScrambledWord() has been called, 
			//or if the first line has not been read yet, word will be set equal to what the Scanner reads
			Random r = new Random();
			int loc = r.nextInt(index); //Random number generated between 0 and index(exclusive) 
			word = list.get(loc);
			list.add(word); //selected word goes to end of list, so it can't be read again
			list.remove(loc); //the word that is read gets removed from original location of list
			scrambled = false; //Since word is now set, scrambled should be set to false
			index--; //max index decrements
			
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
	
	public void reset(){
		index = list.size(); //Index being set to the size means all the words can be read from ArrayList
		scrambled = false;
		word = null;
	}
	

}
