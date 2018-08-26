package assignment2;
import java.io.*;
import java.util.*;

public class Results {
	private File text;
	private Scanner read;
	private int rounds;
	private int wins;
	private int loss;
	
	public Results(String s, String u, String p){ //This constructor for new players
		try{
			text = new File(u + p + ".txt"); //new File named after person's name & password
			if(text.isFile()) //If player file already exists, Scanner reads data from that
				read = new Scanner(text);
			else
				read = new Scanner( new File(s)); //Scanner first reads info from results.txt if new player
			rounds = read.nextInt();
			wins = read.nextInt();
			loss = read.nextInt();
		}
		catch(IOException e){
			System.out.println("File not found");
		}
	}
	
	public void won(){ //For each round won, number of total rounds and wins updated
		wins++;
		rounds++;
	}
	
	public void lost(){ //For each round lost, number of total rounds and losses updated
		loss++;
		rounds++;
	}
	
	public void save(){ //PrintWriter prints these results to specific text file
		try{
			PrintWriter fileOut = new PrintWriter(text);
			fileOut.println(rounds);
			fileOut.println(wins);
			fileOut.println(loss);
			fileOut.close();
		}
		catch(IOException e){
			System.out.println("File not found");
		}
	}
	
	public String toString(){
		StringBuilder res = new StringBuilder("\t Rounds tried: ");
		res.append(rounds + "\n");
		res.append("\t Rounds won: ");
		res.append(wins + "\n");
		res.append("\t Rounds lost: ");
		res.append(loss + "\n");
		return res.toString();
	}

}
