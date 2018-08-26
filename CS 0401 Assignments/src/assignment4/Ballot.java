package assignment4;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Ballot extends JPanel{
	private String id; //File name
	private JLabel category; //The category a person is voting for
	private JButton[] choices; //All the buttons/voting choices in that ballot
	private PrintWriter file; //object that prints lines
	private int[] numvotes; //represents number of votes for each button
	private String vote; //stores the current vote until the submit button is clicked
	private boolean clicked; //toggles button clicks

	public Ballot(String line){
		try{
			String[] fields = line.split(":"); //Split the line into arrays separated by colons
			id = fields[0]; //index 0 of fields array is the id number

			category = new JLabel(fields[1], SwingConstants.CENTER); //Index 1 of fields array is the category name, 
			category.setFont(new Font("Serif", Font.BOLD, 30));
			category.setForeground(Color.blue);
					
			String[] names = fields[2].split(","); //Split voting choices into arrays separated by commas
			choices = new JButton[names.length]; //Length of choices[] array is length of names array
			numvotes = new int[names.length]; //numvotes array is also length of names array
			file = new PrintWriter(id + ".txt");
			
			setLayout(new GridLayout(names.length + 1, 1)); //number of rows based on number of buttons plus the JLabel
			add(category);
			for(int i = 0; i < names.length; i++){ //new reference to JButton object made in each index of array
				choices[i] = new JButton(names[i]);
				choices[i].setFont(new Font("Serif", Font.BOLD, 30));
				choices[i].addActionListener(new ButtonListener());
				add(choices[i]);
				file.println(names[i] + ":" + numvotes[i]); //Print out voting choices to the file 
			}
			file.close();
		}
		catch(IOException e){
			System.out.println("File not found");
		}		
	}
	
	public void reset(){ //method for reseting ballots when submit button in BallotPanel is clicked
		vote = new String(""); //vote is nothing so far
		for(int i = 0; i < choices.length; i++)
			choices[i].setForeground(Color.BLACK); //set all button colors to black
	}
	
	public void updateFile(){
		for(int i = 0; i < choices.length; i++){ //first, number of votes that each button has updated
			if(vote.equals(choices[i].getText())) //If the stored string in vote variable same as Jbutton text, vote increments
				numvotes[i]++;
		}
		try{
			File temp = new File("__temmytemptemp.txt");
			file = new PrintWriter(temp); //Make a temporary file
			for(int i = 0; i < choices.length; i++)
				file.println(choices[i].getText() + ":" + numvotes[i]);
			file.close();
			
			//delete and rename methods in File class don't work, so following lines are just an alternate way
			Scanner read = new Scanner(temp); //read from temporary file
			file = new PrintWriter(id + ".txt"); //copy to original file
			while(read.hasNextLine()) 
				file.println(read.nextLine());
			file.close();
		}
		catch(IOException e){
			System.out.println("File not found");
		}
	}
	
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton choice = (JButton) e.getSource();
			if(!clicked){
				vote = new String(choice.getText());
				choice.setForeground(Color.RED);
				clicked = true;
			}
			else{
				if(vote.equals(choice.getText())){
					choice.setForeground(Color.BLACK);
					vote = "";
					clicked = false; //set this toggle to false, since nothing selected
				}
				else{
					choice.setForeground(Color.RED); //set clicked button to color red
					for(int i = 0; i < choices.length; i++){ //find the button that matched the previous choice; set color back
						if(vote.equals(choices[i].getText()))
							choices[i].setForeground(Color.BLACK);
					}
					vote = new String(choice.getText());
				}
			}
		}
	}


}