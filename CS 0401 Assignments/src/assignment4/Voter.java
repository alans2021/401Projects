package assignment4;
import java.io.*;
import java.util.*;

public class Voter {
	private String ID;
	private String name;
	private boolean voted;
	
	public Voter(String s, String n, String b){
		ID = s;
		name = n;
		voted = Boolean.parseBoolean(b);
	}
	
	public static Voter getVoter(String file, String s){
		try{
			File voters = new File(file);
			Scanner sc = new Scanner(voters);
			while(sc.hasNextLine()){
				sc.useDelimiter(":"); //tokens separated by colons
				String id = sc.next();
				String n = sc.next();
				String b = sc.nextLine().substring(1); //substring gets rid of semicolon
				if(id.equals(s)){ //if ID matches argument, new voter object made and returned
					return new Voter(s, n, b);
				}
			}
			return null;
		}
		catch(IOException e){
			System.out.println("File not found");
		}
		return null;
	}
	
	public static void saveVoter(String s, Voter v){
		boolean printed = false; //Variable that determines if Voter v information stored
		try{
			File voters  = new File(s);
			File temp = new File("__temtemp.txt");
			Scanner sc = new Scanner(voters);
			PrintWriter output = new PrintWriter(temp);
			
			while(sc.hasNextLine()){
				sc.useDelimiter(":");
				String str = sc.next(); //temp ID from file
				String n = sc.next(); //temp name from file
				String b = sc.nextLine().substring(1); //temp voted from file, substring gets rid of colon
				String id = v.getId();  //ID information from argument
				if(!str.equals(id)) //If argument (the voter's) id not found in line, info gets printed to temp file like normal
					output.println(str + ":" + n + ":" + b);
				else{ //If voter ID found, this statement executed
					output.println(str + ":" + n + ":" + true); //We know if saveVoter called, true will be printed
					printed = true; //Means voter v info has been printed
				}
			}
			
			if(!printed) //Not printed means it's a new voter
				output.println(v.getId() + ":" + v.getName() + ":" + true );
			output.close();    
			/*sc.close(); deleting and renaming files don't work, so this is just commented out
			voters.delete();
			File newversion = new File("voters.txt");
			temp.renameTo(newversion);*/ 
			
			//Alternate way. However, temp file can't get deleted
			Scanner tempsc = new Scanner(temp); //Make another Scanner that reads from temp file
			PrintWriter output2  = new PrintWriter(voters); //Make printwriter that copies to voters.txt
			while(tempsc.hasNextLine()){
				String line = tempsc.nextLine();
				output2.println(line);
			}
			output2.close();	
			
		}
		catch(IOException e){
			System.out.println("File not found");
		}
	}
	
	public String getName(){
		return name;
	}
	
	public String getId(){
		return ID;
	}
	
	public boolean hasVoted(){
		return voted;
	}
	
	public void vote(){
		voted = true;
	}
	
	public String toString(){
		return new String ("ID: " + ID + " Name: " + name + " Voted? " + voted);
	}

}
