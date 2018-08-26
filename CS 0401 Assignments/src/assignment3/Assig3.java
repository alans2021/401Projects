package assignment3;
//CS 0401, Section 1060 Professor Ramirez
//Alan Shen
import java.util.*;

public class Assig3 {
	public static void main(String[]args){
		boolean play = false; //First assume that person doesn't play a round
		boolean correct = false; //First assume that the guess is false
		boolean newplay; //determines if it's a new player or not
		int tries = 3; //Represents number of guesses left for each word
		Scanner input = new Scanner(System.in);
		Scramble2 game = new Scramble2("words.txt");
		String real;
		String guess;
		PlayerList people = new PlayerList("players.txt");
		Player pers;
		int currround; //current rounds played for each game
		int currwin; //current rounds won
		int currloss; //current rounds lost

		System.out.println("Welcome to the Scrambler");
		System.out.println("Please enter your name. Hit 'Enter' key to quit");
		String name = input.nextLine();
		pers = people.getPlayer(name); //get player from the list.
		while(!name.equals("")){
			newplay = false; //first assume not new player
			currround = 0;
			currwin = 0;
			currloss = 0;
			while(pers == null){ //If none found, proceed with this statement
				System.out.println("You are not in our player list");
				System.out.println("Do you wish to add it? Press 'Y' if so. If not, press anything else");
				if(input.nextLine().toUpperCase().equals("Y")){
					System.out.println("Welcome " + name);
					System.out.println("Please make a password. Then press enter.");
					String pass = input.nextLine();
					pers = new Player(name, pass); //new player made and added to current player list
					people.addPlayer(pers);
					play = true;
					newplay = true;
				}
				else{
					play = false;
					System.out.println("Please enter your name. Hit 'Enter' key to quit");
					name = input.nextLine();
					pers = people.getPlayer(name);
				}
			}
			if(pers != null && !newplay){ //For returning players
				System.out.println("Please enter your password");
				String pass = input.nextLine();
				while(!pass.equals(pers.getPassword())){
					System.out.println("That's not correct. Try again");
					pass = input.nextLine();
				}
				System.out.println("Welcome back " + name);
				play = true;
			}
			
			System.out.println();
			System.out.println(name + ", you have " + tries + " guess(es) to get the Scramble");
			System.out.println("Don't Fail!");
			String word = game.getRealWord();

			while(play && word != null){ //Loop runs when individual wants to play and there's still words left
				real = word.toUpperCase();
				while(tries > 0 && !correct){ //Loop runs if number of tries left is greater than zero and it's not correct
					System.out.println("Scramble: " + game.getScrambledWord().toUpperCase());
					System.out.print("Your Guess: ");
					guess = input.nextLine().toUpperCase();
					if(guess.equals(real))
						correct = true; //If word guessed correctly, correct is true and while loop is broken out of
					else{
						System.out.println("Sorry " + name + ", that guess is WRONG!");
						System.out.println("Here are the letters you got correct");
						printLetters(real, guess); //Go to a method that shows which letters were correct
						tries--;
						System.out.println(name + ", you have " + tries + " guess(es) remaining");
					}
				}

				if(correct){ //If guessed correctly, this statement printed
					System.out.println("Great job " + name + "! You actually didn't mess up!");
					pers.won();
					currround++;
					currwin++;
				}
				else{
					System.out.println("Round over! You suck " + name);
					System.out.println("The actual word is " + real);
					pers.lost();
					currround++;
					currloss++;
				}
				word = game.getRealWord();
				if(word != null){//Statement runs if still words remaining
					System.out.println("Would you like to play another round? (Y/N)");
					String resp = input.nextLine().toUpperCase();
					if(resp.equals("Y")){ //Everything resets if user says 'Y' 
						correct = false;
						tries = 3;
						System.out.println();
						System.out.println(name + ", you have " + tries + " guess(es) to get the Scramble");
						System.out.println("Don't Fail!");
					}
					else
						play = false;
				}
				else //No words remaining means the variable word will equal null, so that means loop does not keep running
					play = false;
			}
			
			System.out.println("Thanks for playing " + name + "!");
			System.out.println(name + ", Here are your results for today's game.");
			System.out.println("\tRounds played: " + currround);
			System.out.println("\tWins: " + currwin);
			System.out.println("\tLosses: " + currloss);
			System.out.println("Here are your cumulative stats");
			System.out.println(pers.toString()); //Cumulative stats shown

			game.reset(); //Scramble object resets
			System.out.println("Please enter your name. Hit 'Enter' key to quit");
			name = input.nextLine();
			pers = people.getPlayer(name); //get player from the list.
			tries = 3;
			correct = false;
		}
		people.saveList(); //Results of all players saved to players.txt
		people.updateStats();
		System.out.println("Game over");
		System.out.println("Here are the player stats");
		System.out.println(people.toString());
	}
	
	public static void printLetters(String rea, String gues){
		StringBuilder letters = new StringBuilder();
		
		for(int i = 0; i < rea.length(); i++){
			if(gues.length() > i && gues.charAt(i) == rea.charAt(i)) //If the guess at an index matches the letter of the real word, 
				//it will be printed. Also, the condition that the guessword's length is greater than the index must be fulfilled
				letters.append(gues.charAt(i));
			else
				letters.append("_"); //If guess at an index doesn't match, print out "_"
		}
		System.out.println(letters);
	}

}
