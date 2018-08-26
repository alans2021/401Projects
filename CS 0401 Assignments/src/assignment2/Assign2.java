package assignment2;
//CS 0401, Section 1060 Professor Ramirez
//Alan Shen
import java.util.*;

public class Assign2 {
	public static void main(String[]args){
		boolean play = true; //First assume that person will play a round
		boolean correct = false; //First assume that the guess is false
		int tries = 3; //Represents number of guesses left for each word
		Scanner input = new Scanner(System.in);
		Scramble game = new Scramble("words.txt");
		System.out.println("Please enter your name");
		String name = input.nextLine();
		System.out.println("Please enter a password. If you've already played, enter the same one.");
		System.out.println("Saved data from previous rounds you've played will be used.");
		String password = input.nextLine(); //Password is used to save individual results for each player
		Results print = new Results("results.txt", name, password);
		String real;
		String guess;
		
		System.out.println("Welcome to the Scrambler");
		System.out.println();
		System.out.println(name + ", you have " + tries + " guess(es) to get the Scramble");
		System.out.println("Don't Fail!");
		
		String word = game.getRealWord();
		
		while(play && word != null){ //Main loop runs when user wants to play and there's still words left
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
				print.won();
			}
			else{
				System.out.println("Round over! You suck " + name);
				System.out.println("The actual word is " + real);
				print.lost();
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
		System.out.println("Here are your total results (including previous games you've played) ");
		print.save();
		System.out.println(print.toString());
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
