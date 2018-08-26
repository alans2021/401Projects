package assignment1;
//Alan Shen, Professor Ramirez
//CS 0401, Section 1060

import java.util.*;
import java.lang.Math;
public class Quidditch_Shop {
	public static void main(String[]args){
		System.out.println("Welcome to the North of Westeros! Our warden is Lord Eddard Stark");
		System.out.println("[Hogsmeade Convenience Location]");
		System.out.println("Note 1: We appreciate exact change!");
		System.out.println();
		System.out.println("Note 2: Recall our currency options");
		System.out.println("                        29 Knuts == 1 Sickle");
		System.out.println("                17 Sickles == 1 Galleon == 493 Knuts");
		String password = new String("WINTER IS COMING");
		Scanner input = new Scanner(System.in);
		boolean customer = true;
		
		System.out.println();
		System.out.println("Is there a customer to be waited on? Type 'YES' if so"); //Anything else means no customer to be waited on
		if(! (input.nextLine().toUpperCase().equals("YES")) )
			customer = false;
		
		while(customer){
			boolean discount = false; //We first assume the customer doesn't qualify for discounts
			boolean ordering = true; //We first assume that the customer will be ordering items
			boolean pin = false; //We first assume no pins will be ordered
			int numpins = 0, pins, pinReg = 0; // represents # of pins, true total cost of pins, and total cost of pins if no discount, resp.
				int gryf = 0, huff = 0, raven = 0, slyth = 0; // represents number of pins ordered of each Hogwarts house
			int numquaf = 0, quaffle, quaReg = 0; // represents # of quaffles, total cost of quaffles, ... etc. (See Line 23 comment)
			int numkit = 0, kit, kitReg = 0; // represents # of kits, ... etc. (See Line 23 comment)
			int pinPrice = 20, quafPrice = 145, quafBoxPrice = 638, kitPrice = 986; //represents unit cost in Knuts of pins, quaffles, quaffle box, broomstick kit, resp. 

			System.out.println("What is the secret password for discounts? You get two tries");
			
			for(int i = 0; i < 2; i++){ //Ensures a maximum of only two times that this loop is executed
				String response = input.nextLine().toUpperCase();
				if(!response.equals(password) && i == 0)
					System.out.println("That is not the correct password. You will get one more chance");
				else if(response.equals(password)){
					System.out.println("Welcome House Stark bannerman!");
					System.out.println("You will get special discounts at Winterfell!");
					System.out.println("        Discount on 10 or more House Pins");
					System.out.println("        Discount on Boxes of Quaffles");
					System.out.println("        Discount on Broomstick Service Kits");
					discount = true;
					i++; //doing this ensures the forloop isn't executed again
				}
				else 
					System.out.println("Please enjoy our items at their regular price");
			}
			
			System.out.println("Here is our price list"); // Lines 54 - 64 Shows all the prices
			System.out.println("        House Pins (each)                  " + pinPrice + " Knuts");
			System.out.println("                  Available in Gryffindor, Slytherin, Ravenclaw, and Hufflepuff");
			if(discount){ //pinPrice not changed yet since we still don't know if customer has bought 10 or more
				System.out.println("                  [Only 18 Knuts each if you buy 10 or more]");
				quafBoxPrice = 580;
				kitPrice = 899;
			}
			System.out.println("        Quaffles (box of 5)                " + quafBoxPrice + " Knuts");
			System.out.println("        Quaffles (each)                    " + quafPrice + " Knuts");
			System.out.println("        Broomstick Service Kits (each)     " + kitPrice + " Knuts");
			
			while(ordering == true){ // If ordering is true, this while loop keeps asking the customer what they want
				System.out.println("Please choose an option from 1 through 5. ");
				System.out.println("        1) Update House Pins Order");
				System.out.println("        2) Update Quaffles Order");
				System.out.println("        3) Update Broomstick Kits Order");
				System.out.println("        4) Show price list");
				System.out.println("        5) Check out");
				int response = input.nextInt();
				
				if(response == 4){ //This if statement involves showing the prices
					System.out.println("Here is our price list");
					System.out.println("        House Pins (each)                  " + pinPrice + " Knuts");
					System.out.println("                  Available in Gryffindor, Slytherin, Ravenclaw, and Hufflepuff");
					if(discount)
						System.out.println("                  [Only 18 Knuts each if you buy 10 or more]");
					System.out.println("        Quaffles (box of 5)                " + quafBoxPrice + " Knuts");
					System.out.println("        Quaffles (each)                    " + quafPrice + " Knuts");
					System.out.println("        Broomstick Service Kits (each)     " + kitPrice + " Knuts");
				}
				
				else if(response >= 1 && response <= 3){ //Ordering pins, quaffles, and kits happen here
					System.out.println("Here is your current order");
					if(response == 1)
						pin = true;
					
					while(pin == true){ //This whole while loop deals with ordering pins and the house pins. Line 90 - 156
						System.out.println("    " + numpins + " Team Pins Ordered:");
						System.out.println("         " + gryf + " Gryffindor.");
						System.out.println("         " + huff + " Hufflepuff.");
						System.out.println("         " + raven + " Ravenclaw.");
						System.out.println("         " + slyth + " Slytherin.");
						
						if(discount && numpins >= 10){
							System.out.println("You will pay the discounted price of 18 Knuts for each pin");
							pinPrice = 18;
						}
						else{
							System.out.println("As of right now, you will pay the regular price of 20 Knuts for each pin");
							pinPrice = 20;
						}
						
						System.out.println("For which house do you want to order pins? Enter from 1 to 5");
						System.out.println("        1) Gryffindor");
						System.out.println("        2) Hufflepuff");
						System.out.println("        3) Ravenclaw");
						System.out.println("        4) Slytherin");
						System.out.println("        5) Done with pin order");
						
						int pinoption = input.nextInt(); //Lines 114 - 156: ordering the different house pins.  
						if(pinoption == 1){  
							System.out.println("How many Gryffindor pins would you like?");
							int num = input.nextInt();
							if(num < 0){
								System.out.println("Negative will be assumed as 0");
								num = 0;
							}
							gryf = num;							
						}
						else if(pinoption == 2){
							System.out.println("How many Hufflepuff pins would you like?");
							int num = input.nextInt();
							if(num < 0){
								System.out.println("Negative will be assumed as 0");
								num = 0;
							}
							huff = num;
						}
						else if(pinoption == 3){
							System.out.println("How many Ravenclaw pins would you like?");
							int num = input.nextInt();
							if(num < 0){
								System.out.println("Negative will be assumed as 0");
								num = 0;
							}
							raven = num;	
						}
						else if(pinoption == 4){
							System.out.println("How many Slytherin pins would you like?");
							int num = input.nextInt();
							if(num < 0){
								System.out.println("Negative will be assumed as 0");
								num = 0;
							}
							slyth = num;	
						}
						else if (pinoption == 5)
							pin = false;
						else
							System.out.println("Please choose a number between 1 to 5.");
						numpins = gryf + huff + raven + slyth;
					}
					
					if(response == 2){ //Lines 158 - 172 involve buying quaffles
						System.out.println("    " + numquaf / 5 + " boxes of quaffles ordered");
						System.out.println("    " + numquaf % 5 + " quaffles ordered");
						System.out.println();
						System.out.println("How many quaffles would you like for");
						System.out.println("    " + quafPrice + " Knuts each");
						System.out.println("    " + quafBoxPrice + " Knuts for a box of 5");
						System.out.println("(Just enter the total number of quaffles you would like)");
						int num = input.nextInt();
						if(num < 0){
							System.out.println("Negative will be assumed as 0");
							num = 0;
						}
						numquaf = num;
					}
					
					if(response == 3){ //This if body involves buying broom kits. Lines 174 - 183
						System.out.println("    " + numkit + " kits ordered");
						System.out.println("How many broomstick kits would you like for " + kitPrice + " Knuts each");
						int num = input.nextInt();
						if(num < 0){
							System.out.println("Negative will be assumed as 0");
							num = 0;
						}
						numkit = num;
					}
					
				}
				else if(response == 5) //setting ordering to false breaks the loop. Goes to Line 193 for checking out 
					ordering = false;
				
				else //If integer entered is negative or greater than 5. User enters integer again
					System.out.println("Please enter an integer between 1 to 5");
			}
			
			pins = numpins * pinPrice; //Total cost of pins is calculated
			quaffle = (numquaf / 5 * quafBoxPrice) + (numquaf % 5 * quafPrice); //Total cost of quaffles is calculated. numquaf / 5 is boxes of quaffles while numquaf % 5 is remaining individual ones
			kit = numkit * kitPrice; //Total cost of broomstick kits is calculated
			if(discount){ //Total cost of pins, quaffle, and kit without discount is calculated. If no discount, this is unnecessary
				pinReg = numpins * 20; 
				quaReg = (numquaf / 5 * 638) + (numquaf % 5 * quafPrice); 
				kitReg = numkit * 986; 
			}
			
			System.out.println("Here is your subtotal"); //Lines 202 - 246 are essentially the receipt of the customer's order
			if( numpins > 0){
				System.out.println("\t" + numpins + " House Pins at " + pinPrice + " Knuts each:\t\t" + pins);
				if(gryf > 0)	
					System.out.println("\t\t" + gryf + " Gryffindor");
				if(huff > 0)
					System.out.println("\t\t" + huff + " Hufflepuff");
				if(raven > 0)
					System.out.println("\t\t" + raven + " Ravenclaw");
				if(slyth > 0)
					System.out.println("\t\t" + slyth + " Slytherin");
			}
			if( numquaf / 5 > 0)
				System.out.println("\t" + (numquaf / 5) + " boxes of Quaffles at " + quafBoxPrice + " Knuts each:\t\t" + (numquaf / 5 * quafBoxPrice) );
			if( numquaf % 5 > 0)
				System.out.println("\t" + (numquaf % 5) + " Quaffles at " + quafPrice + " Knuts each:\t\t\t" + (numquaf % 5 * quafPrice) );
			if( numkit > 0 )
				System.out.println("\t" + numkit + " Broomstick Service Kits at " + kitPrice + " Knuts each:\t" + kit);
			
			double totPrice = pins + quaffle + kit;
			double regPrice = pinReg + quaReg + kitReg;
			System.out.println("\t\t\t\t\t\t\t----");
			System.out.println("\tTotal:\t\t\t\t\t\t" + totPrice);
			
			if(discount && totPrice > 0){
				double finalDiscount = ( (int) (totPrice * 10)) / 100.0; //Calculates bonus 10% discount. Also makes sure the trailing decimals aren't written in the receipt. 
				if(totPrice >= 3 * 493){
					totPrice = Math.round(totPrice - finalDiscount);
					System.out.println("\tBonus discount of 10%\t\t\t\t-" + finalDiscount);
					System.out.println("\t\t\t\t\t\t\t----");
					System.out.println("\tNew Total (Nearest Knut):\t\t\t" + totPrice);
				}
				//The rest of this if statement is for extra credit
				System.out.println("Here is the bill without the discount. In addition, the savings for each item and the total savings is listed in Knuts.");
				System.out.println("\t" + numpins + " House Pins:\t\t\t" + pinReg + " Knuts.\tYou saved " + (pinReg - pins));
				System.out.println("\t\t" + gryf + " Gryffindor pins:\t" + 20 * gryf + " Knuts.\tYou saved " + (20 * gryf - pinPrice * gryf));
				System.out.println("\t\t" + huff + " Hufflepuff pins:\t" + 20 * huff + " Knuts.\tYou saved " + (20 * huff - pinPrice * huff));
				System.out.println("\t\t" + raven + " Ravenclaw pins:\t" + 20 * raven + " Knuts.\tYou saved " + (20 * raven - pinPrice * raven));
				System.out.println("\t\t" + slyth + " Slytherin pins:\t" + 20 * slyth + " Knuts.\tYou saved " + (20 * slyth - pinPrice * slyth));
				System.out.println("\t" + (numquaf / 5) + " boxes of Quaffles:\t\t" + (numquaf / 5 * 638) + " Knuts.\tYou saved " + (638 - quafBoxPrice) * (numquaf / 5) );
				System.out.println("\t" + (numquaf % 5) + " Quaffles:\t\t\t" + (numquaf % 5 * 145) + " Knuts.\tYou saved 0"); //Individual Quaffle Prices always the same
				System.out.println("\t" + numkit + " Broomstick Kits:\t\t" + kitReg + " Knuts.\tYou saved " + (kitReg - kit));
				System.out.println("\t\t\t\t\t----\t\t----");
				System.out.println("\tTotal:\t\t\t\t" + regPrice + " Knuts.\tOverall, you saved " + (regPrice - totPrice) );
			}
			
			int payment = 0; //customer has so far paid 0 Knuts
			if(totPrice > 0){ //Ensures payment only when there is something ordered. Lines 249 - 284 deal with paying the cashier
				System.out.println("Please enter payment. You can enter as many times as you like");
				
				while(payment < totPrice){ //User keeps paying until total payment is greater than the price
					System.out.println("    Enter type of currency, where {K, S, G} stnads for Knuts, Sickles, Galleons, resp.");
					String curr = input.next().toUpperCase();
					System.out.println("    Enter amount you want to pay.");
					int amt = input.nextInt();
					if(amt < 0 || !(curr.equals("K") || curr.equals("S") || curr.equals("G")) ) //If amt is negative or the currency is none of the correct letters, user enters again
						System.out.println("Not counted. Please enter in the correct format");
					else{
						if(curr.equals("G")) //Amount get multiplied by 493 to be equivalent to one Galleon
							amt *= 493;
						if(curr.equals("S")) //Amount get multiplied by 29 to be equivalent to one sickle
							amt *= 29;
						payment += amt;		 //Amount paid in one sitting added to overall payment
						System.out.println("    You have added " + amt + " Knuts to the total");
						System.out.println("    You have paid " + payment + " out of " + totPrice + " Knuts");
						if(payment < totPrice)
							System.out.println("    You still owe " + (totPrice - payment) + " Knuts");			
					}
					System.out.println();
				}
				int change = (int) (payment - totPrice); //change in total knuts
				int gal = change / 493; //change in max number of galleons
				int sic = (change - gal * 493) / 29; // change in max number of sickles
				int knut = (change - gal * 493) % 29; //change in remaining knuts
				System.out.println("Thank you! You have overpaid by " + change + " Knuts");
				System.out.println("Here is your change");
				if(gal > 0)
					System.out.println("    " + gal + " Galleons");
				if(sic > 0)
					System.out.println("    " + sic + " Sickles");
				if(knut > 0)
					System.out.println("    " + knut + " Knuts");
			}
			System.out.println("Thank you for shopping here!");
			System.out.println();
			System.out.println("Is there a customer to be waited on? Type 'YES' if so"); 
			String c = input.nextLine(); //This line prevents the previous input from being executed in line 291. 
			String cust = input.nextLine().toUpperCase();
			if(!cust.equals("YES"))
				customer = false;
			else
				customer = true;
		}
		
	}
	
	

}