// CS 0401 Fall 2017
// Lab 11
// You must modify this file so that it works as described in the lab handout.
import java.util.*;
import java.io.*;
public class lab11
{
	public static int[] resize(int[] old){
		int[] temp = new int[old.length * 2];
		System.out.println("Re-sizing array from " + old.length + " to " + temp.length);
		for(int i = 0; i < old.length; i++)
			temp[i] = old[i];
		return temp;
	}
	
	public static void main(String [] args)
	{
		Scanner inScan, fScan = null;
		int [] A = new int[5];
		inScan = new Scanner(System.in);
		System.out.println("Please enter the file to read from: ");
		String fName = inScan.nextLine();
		String nextItem;
		int nextInt = 0;
		int i = 0;
		
		while(true){
			try{
				fScan = new Scanner(new File(fName));
				break;
			}
			catch(IOException e){
				System.out.println("Your file is invalid -- please re-enter");
				fName = inScan.nextLine();
			}
		}
		
		while (fScan.hasNextLine())
		{
			nextItem = fScan.nextLine();
			try{
				nextInt = Integer.parseInt(nextItem);
				A[i] = nextInt;
				i++;
			}
			catch(NumberFormatException e2){
				System.out.println(nextItem + " is not an integer -- ignored");
			}
			catch(ArrayIndexOutOfBoundsException e3){
				A = resize(A);
				A[i] = nextInt;
				i++;
			}
		}

		System.out.println("Here are your " + i + " items:");
		for (int j = 0; j < i; j++)
		{
			System.out.println(A[j] + " ");
		}
	}
}
