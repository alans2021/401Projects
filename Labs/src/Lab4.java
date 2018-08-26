import java.util.Random;
import java.util.*;
public class Lab4 {
	public static void main(String[]args){
		Random num = new Random();
		boolean contin = true;
		Scanner input = new Scanner(System.in);
		while(contin){
			System.out.println("Enter the number of dice rolls you want");
			int roll = input.nextInt();
			rollDice(roll, num);
			System.out.println("Do you want to continue? Say 'YES' if so");
			String resp = input.next().toUpperCase();
			if(!resp.equals("YES"))
				contin = false;
		}
	}
	public static void rollDice(int rolls, Random number){
		double two = 0, three = 0, four = 0, five = 0, six = 0, seven = 0; 
		double eight = 0, nine = 0, ten = 0, eleven = 0, twelve = 0;
	
		for(int i = 0; i < rolls; i++){
			int roll1 = number.nextInt(6) + 1;
			int roll2 = number.nextInt(6) + 1;
			int sum = roll1 + roll2;
			if(sum == 2)
				two++;
			if(sum == 3)
				three++;
			if(sum == 4)
				four++;
			if(sum == 5)
				five++;
			if(sum == 6)
				six++;
			if(sum == 7)
				seven++;
			if(sum == 8)
				eight++;
			if(sum == 9)
				nine++;
			if(sum == 10)
				ten++;
			if(sum == 11)
				eleven++;
			if(sum == 12)
				twelve++;		
		}
		double[] values = {two, three, four, five, six, seven, eight, nine, ten, eleven, twelve};
		for(int i = 0; i < values.length; i++)
			System.out.println("The experimental probability of rolling a " + (i + 2) + " is " + (values[i] / rolls));
	}	
}
