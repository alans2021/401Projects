import java.util.*;
public class Prac2Code2
{
      public static void main(String [] args)
      {
         Scanner input = new Scanner(System.in);
         int number;
         while(true){
        	 System.out.println("Please enter an integer");
        	 String line = input.nextLine();
        	 try{
        		 number = Integer.parseInt(line);
        		 break;
        	 }
        	 catch(Exception e){
        		 System.out.println("Your data was invalid");
        	 }
         }
         System.out.println("You entered " + number);
      }
}

