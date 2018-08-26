package assignment4;
import java.awt.*;
import java.util.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.*;

public class BallotPanel extends JPanel{
	private Ballot[] ballots;
	private File file;
	private VoteInterface ballot;
	private Scanner input;
	private JButton[][] choices;
	private JButton castvote;
	
	public BallotPanel(String b, VoteInterface V){
		try{
			file = new File(b);
			ballot = V;
			input = new Scanner(file); //ballots.txt or ballots2.txt gets read in to file
			ballots = new Ballot[Integer.parseInt(input.nextLine())]; //First integer in the read file is the length of ballot array 
			for(int i = 0; i < ballots.length; i++)
				ballots[i] = new Ballot(input.nextLine()); //each index in ballot array points to a new ballot object 

			castvote = new JButton("Cast your vote");
			castvote.setFont(new Font("Serif", Font.BOLD, 30));
			castvote.addActionListener(new ButtonListener());
			
			
			setLayout(new GridLayout(1, ballots.length + 1));
			for(int i = 0; i < ballots.length; i++)
				add(ballots[i]); //each ballot added to BallotPanel 
			add(castvote);
		}
		catch(IOException e){
			System.out.println("File not found");
		}
	}
	
	public void resetBallots(){ //Each ballot in Ballot[] array gets reset
		for(int i = 0; i < ballots.length; i++)
			ballots[i].reset();
	}
	
	
	private class ButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			int reply = JOptionPane.showConfirmDialog(BallotPanel.this, "Confirm Vote?"); //JOptionPane shows up
			if(reply == JOptionPane.YES_OPTION){ //If yes, all ballots get updated
				for(int i = 0; i < ballots.length; i++)
					ballots[i].updateFile();
				ballot.voted(); //Goes to VoteInterface object and calls voted method
			}
		}
	}
	

}
