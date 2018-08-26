import java.util.Scanner;
import java.util.Arrays;


public class Runner {
	public static void main(String[]args){
		double[] fractions;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the max degree of your function in calculating your integral");
		int degree = sc.nextInt();
		int[] regular = new int[degree + 1];
		System.out.println("Does your function have any fractional exponents? Enter 'Yes' if so.");
		if(sc.nextLine().equals("Yes") || sc.nextLine().equals("yes")){
			System.out.println("How many terms?");
			fractions = new double[sc.nextInt() * 2];
			System.out.println("List these out:");
			System.out.println();
			for(int i = 0; i < fractions.length; i++){
				System.out.println("Write the coefficient of a term");
				fractions[i] = sc.nextDouble();
				i++;
				System.out.println("Write the exponent of that same term as a decimal rounded to 2 places");
				fractions[i] = sc.nextDouble();
			}
		}
		for(int i = 0; i < regular.length ; i++){
			System.out.println("Enter the coefficient of your the exponent of " + i);
			regular[i] = sc.nextInt();
		}
		
		
	}

}