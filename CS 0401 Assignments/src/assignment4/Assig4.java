package assignment4;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
//Alan Shen, CS 0401
//Ramirez, Section 1060
public class Assig4 implements VoteInterface, LoginInterface{ //These two interfaces implemented, allowing 
	private JFrame theWindow; //Main Window
	private JLabel text; //Initial Text that shows up before someone logs in
	private JLabel welcome; //Welcome text shown, with voter's name, after person has submitted ID
	private JLabel telltoVote; //Shows up with the ballots, indicating voter to make choices
	private JButton vote; //When clicked, prompts logging in with ID number
	private JButton showBallots; //When clicked, shows the voting choices
	private BallotPanel balpan;
	private LoginPanel logpan;
	private String vfile; //voters.txt stored to this variable
	private String bfile; //ballots stored to this variable 
	private Voter v;
	
	public Assig4(String v, String b){
		vfile = v;
		bfile = b;
		balpan  = new BallotPanel(bfile, Assig4.this); //BallotPanel object made here, means this object only made once
													   //this is used as argument since Assig4 is a LoginInterface object	
		text = new JLabel("Welcome to E-Vote", SwingConstants.CENTER); //This block is initial components seen
		text.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 30));
		vote = new JButton("Click to vote");
		vote.setFont(new Font("Serif", Font.BOLD, 30));
		vote.addActionListener(new ButtonListener());
		
		theWindow = new JFrame("Voting Program");
		theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theWindow.setLayout(new GridLayout(2, 1));
		theWindow.add(text);
		theWindow.add(vote);
		theWindow.pack();
		theWindow.setVisible(true);
	}

	public void setVoter(Voter newV) {
		v = newV; 
		theWindow.remove(logpan); //components removed when setVoter from LoginPanel class called
		theWindow.remove(text);
		
		welcome = new JLabel("Welcome " + newV.getName(), SwingConstants.CENTER);
		welcome.setFont(new Font("Serif", Font.ITALIC, 30));
		showBallots = new JButton("Click to Vote");
		showBallots.setFont(new Font("Serif", Font.BOLD, 30));
		showBallots.addActionListener(new ButtonListener());
		
		theWindow.add(welcome);
		theWindow.add(showBallots);
		theWindow.pack();
	}

	public void voted() { //when this method called, indicates person has voted, saves that to voters.txt, and everything resets
		JOptionPane.showMessageDialog(balpan, v.getName() + " thanks for voting!");
		Voter.saveVoter(vfile, v);
		theWindow.remove(balpan);
		theWindow.remove(telltoVote);
		theWindow.add(text);
		theWindow.add(vote);
		theWindow.pack();
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			
			if(source == vote){ //If source equals the button that prompts login, LoginPanel object called
				logpan = new LoginPanel(vfile, Assig4.this);
				theWindow.remove(vote); //Components removed and added and packed
				theWindow.add(logpan);
				theWindow.pack();
			}
			
			else if(source == showBallots){
				balpan.resetBallots(); //If source equals Button that shows voting choices, BallotPanel resets
				theWindow.remove(welcome); //components removed
				theWindow.remove(showBallots);
				telltoVote = new JLabel(v.getName() + " please make your choices", SwingConstants.CENTER);
				telltoVote.setFont(new Font("Serif", Font.ITALIC, 30));
				
				theWindow.add(telltoVote); //components added,including the JLabel
				theWindow.add(balpan);
				theWindow.pack();
			}
		}
	}
	
	public static void main(String[] args){
		new Assig4("voters.txt", "ballots.txt"); //File names passed to a constructor
	}
	

}
