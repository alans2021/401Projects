package assignment4;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.*;

public class LoginPanel extends JPanel{
	private JLabel toplabel;
	private JLabel bottomlabel;
	private JTextField text;
	private JButton button;
	private String file;
	private Voter person;
	LoginInterface login;
	
	public LoginPanel(String voterfile, LoginInterface L){
		file = voterfile;
		login = L;
		
		toplabel = new JLabel("Please log into the site. If you are not saved in system, type an ID that are all DIGITS");
		toplabel.setFont(new Font("Serif", Font.BOLD, 30));
		
		bottomlabel = new JLabel("Voter ID:");
		bottomlabel.setFont(new Font("Serif", Font.BOLD, 30));
		
		button = new JButton("Submit");
		button.setFont(new Font("Serif", Font.BOLD, 30));
		button.addActionListener(new ButtonListener());
		
		text = new JTextField(10);
		text.setFont(new Font("Serif", Font.BOLD, 30));
		
		setLayout(new BorderLayout()); //JPanel layout will be using borderlayout
		add(toplabel, BorderLayout.NORTH); //components added to north, west, east, or south
		add(bottomlabel, BorderLayout.WEST);
		add(text, BorderLayout.EAST);
		add(button, BorderLayout.SOUTH);
	}
	
	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String id = text.getText();
			person = Voter.getVoter(file, id);
			if(person == null){ //If id inputed not in voter file, adding new voters is done; part of extra credit
				int reply = JOptionPane.showConfirmDialog(LoginPanel.this, "You are not registered. Would you like to be added?");
				if(reply == JOptionPane.YES_OPTION){ //If yes, name entered
					String n = JOptionPane.showInputDialog(LoginPanel.this, "Please enter your name");
					login.setVoter( new Voter(id, n, "false"));
				}
			}
			else{
				if(person.hasVoted()) //If id has voted, show this message
					JOptionPane.showMessageDialog(LoginPanel.this, person.getName() + ", you have already voted!");
				else{
					login.setVoter(person); //LoginInterface reference passed into the constructor is assigned 
					//to LoginInterface instance variable, allowing it to access setVoter method
				}
			}
			
		}
		
	}

}
