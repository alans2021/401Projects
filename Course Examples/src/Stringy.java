// CS 0401 Fall 2017
// Demonstration of some methods in the Character class, and of the split()
// method in the String class.  JOptionPane is used for I/O
import java.util.*;
import javax.swing.*;
public class Stringy
{
	public static void main(String [] args)
	{
		while (true)
		{
			String line	= JOptionPane.showInputDialog(null, "Enter a line (empty to quit)");
			if (line == null || line.equals(""))
				break;
			else
			{
				// Split the String into substrings using one or more blank spaces as the
				// delimiter.  Experiment with this by trying other delimiters.
				String [] words = line.split("[ ]+");
				StringBuilder [] newWords = new StringBuilder[words.length];
				for (int i = 0; i < words.length; i++)
				{
					newWords[i] = new StringBuilder();
					for (int j = 0; j < words[i].length(); j++)
					{
						char c = words[i].charAt(j);
						if (Character.isLetter(c))
						{
							c = Character.toUpperCase(c);
							newWords[i].append(c);
						}
					}
				}
				JOptionPane.showMessageDialog(null, "Click ok for your words:");
				for (StringBuilder s: newWords)
					JOptionPane.showMessageDialog(null, s.toString());
			}
		}
	}
}