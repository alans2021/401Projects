// CS 401 Fall 2017 Lab 10 Main Program
// Your job is to complete this program so that it runs correctly.
// The Movie class and MovieDB class are idential to those from Lab7. 
// The only difference is that now the main program has a graphical
// interface, and it is written in a more object-oriented way (ex: the
// movies variable is now an instance variable rather than a method
// variable).  I have written the interface, but have left out the code
// for the ActionListener (in the ControlListener class).  You must 
// complete the segments so that the program runs correctly.


import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Lab10
{
	private MovieDB movies;  // MovieDB variable to store movies
	private JFrame theWindow;	// Window
	private JPanel controlPanel, infoPanel;	// Panels for the
					// control buttons and the information
	private JButton listMovies, addMovie, findMovie, quit;
					// JButtons for various actions
	private JTextArea info;	// See JTextArea in Java API
	private Container thePane;
	private ControlListener theListener;

	public Lab10() throws IOException
	{
		movies = new MovieDB(10); // Still making DB only size 10
		loadData();	// Load movies from file

		// Create and initialize the graphical components for the
		// program.  Note carefully how everything is set up.
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(2,2));

		listMovies = new JButton("List Movies");
		addMovie = new JButton("Add Movie");
		findMovie = new JButton("Find Movie");
		quit = new JButton("Quit");

		theListener = new ControlListener();
		listMovies.addActionListener(theListener);
		addMovie.addActionListener(theListener);
		findMovie.addActionListener(theListener);
		quit.addActionListener(theListener);

		controlPanel.add(listMovies);
		controlPanel.add(addMovie);
		controlPanel.add(findMovie);
		controlPanel.add(quit);

		infoPanel = new JPanel();
		infoPanel.setLayout(new FlowLayout());
		info = new JTextArea();
		info.setColumns(40);
		infoPanel.add(info);

		theWindow = new JFrame("Lab 9");
		// Don't quit the program when "X" is clicked -- user
		// must use the Quit button
		theWindow.setDefaultCloseOperation(
				WindowConstants.DO_NOTHING_ON_CLOSE);
		thePane = theWindow.getContentPane();
		thePane.add(controlPanel, BorderLayout.NORTH);
		thePane.add(infoPanel, BorderLayout.SOUTH);
		// pack() to size JFrame as needed
		theWindow.pack();
		theWindow.setVisible(true);
	}

	private class ControlListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton button = (JButton) e.getSource();
			if(button == listMovies){
				String s = movies.toString();
				info.append(s);
				theWindow.pack();
			}
			if(button == addMovie){
				String movie = JOptionPane.showInputDialog("Type in name of movie");
				String director = JOptionPane.showInputDialog("Type in name of director");
				String studio = JOptionPane.showInputDialog("Type in name of studio");
				double gross = Integer.parseInt(JOptionPane.showInputDialog("Type in gross"));
				Movie temp = new Movie(movie, director, studio, gross);
				movies.addMovie(temp);
				info.append(temp.toString());
				theWindow.pack();				
			}
			if(button == findMovie){
				String mov = JOptionPane.showInputDialog("Type in name of movie to find");
				Movie find = movies.findMovie(mov);
				if(find == null){
					info.append("Movie not found" + "\n");
					info.append("");
					theWindow.pack();
				}				
				else{
					info.append(find.toString());
					theWindow.pack();
				}
			}
			if(button == quit){
				int response = JOptionPane.showConfirmDialog(null, "Do you want to quit?");
				if(response == 0){
					saveData();
					System.exit(0);
				}
			}
			// You must fill in this code -- it should handle all
			// 4 of the buttons as specified and demonstrated
		}
	}

	// A "one line" main here just creates a Lab8 object
	public static void main(String [] args) throws IOException
	{
		new Lab10();
	}

	// Instance method to read in data from file into MovieDB
	private void loadData() throws IOException
	{
		// Note the syntax below for creating a Scanner to a file
		Scanner S = new Scanner(new FileInputStream("movieFile.txt"));
		int sz = Integer.parseInt(S.nextLine());
		for (int i = 0; i < sz; i++)
		{
			String t = S.nextLine();
			String d = S.nextLine();
			String st = S.nextLine();
			double g = Double.parseDouble(S.nextLine());
			Movie temp = new Movie(t, d, st, g);
			movies.addMovie(temp);
		}
	}

	// Instance method to save data back to the file.  I have used an
	// exception handler here just to avoid a compilation error.  We will
	// discuss exception handling later.
	private void saveData()
	{
		try
		{
			PrintWriter P = new PrintWriter("movieFile.txt");
			// Note that we are printing the entire DB to the file with
			// one statement.  This is because the toStringFile() method
			// of the MovieDB class calls the toStringFile() method of
			// each Movie within the DB.
			P.print(movies.toStringFile());
			P.close();
		}
		catch (IOException e)
		{}
	}
}
