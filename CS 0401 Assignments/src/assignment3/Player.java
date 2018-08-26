package assignment3;

public class Player {
	private String name;
	private int wins;
	private int loss;
	private int rounds;
	private String password;
	
	public Player(String s){ //Constructor for new player in PlayerListTest
		name = s;
		password = "";
	}
	
	public Player(String s, String p){//Constructor for new player in game
		name = s;
		password = p;
	}
	
	public Player(String s, int r, int w, int l, String p){ //Constructor for returning player
		name = s;
		rounds = r;
		wins = w;
		loss = l;
		password = p;
	}
	
	public void won(){
		wins++;
		rounds++;
	}
	
	public void lost(){
		loss++;
		rounds++;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPassword(){
		return password;
	}
	
	public int getWins(){
		return wins;
	}
	
	public int getLoss(){
		return loss;
	}
	
	public int getRounds(){
		return rounds;
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder("\tName: " + name + "\n");
		s.append("\tTotal Rounds Played: " + rounds + "\n");
		s.append("\tTotal Rounds Won: " + wins + "\n");
		s.append("\tTotal Rounds Lost: " + loss + "\n");
		return s.toString();
	}

}
