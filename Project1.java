import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * This class contains the main method plus multiple helper methods that helps drive the main method to produce various outputs
 */
public class Project1 
{
	/**
	 * driver method
	 */
	public static void main(String[] args)
	{
		Scanner scnr = new Scanner(System.in);
		
		//These lines read in the user input by calling the "enterList" method and putting it into an ArrayList
		ArrayList<String> namesArrayList = new ArrayList<String>();
		namesArrayList = enterList(scnr);
		int userOption = enterOptions(scnr);
		
		//the while loop allows continues user inputs until 0 is read
		while(userOption != 0)
		{
			if(userOption == 1)
			{
				orderedList(namesArrayList);
				userOption = enterOptions(scnr);
			}
			else if(userOption == 2)
			{
				fullNames(namesArrayList);
				userOption = enterOptions(scnr);
			}
			else if(userOption == 3)
			{
				singleNames(namesArrayList);
				userOption = enterOptions(scnr);
			}
			else if(userOption == 4)
			{
				nameStatistics(namesArrayList);
				userOption = enterOptions(scnr);
			}
			else if(userOption == 5)
			{
				namesEvenLength(namesArrayList);
				userOption = enterOptions(scnr);
			}
			else if(userOption == 6)
			{
				namesOddLength(namesArrayList);
				userOption = enterOptions(scnr);
			}
			else if(userOption == 7)
			{
				namesNotCapitalized(namesArrayList); 
				userOption = enterOptions(scnr);
			}
			else if(userOption == 8)
			{
				mostFrequentName(namesArrayList);
				userOption = enterOptions(scnr);
			}
			else if(userOption == 9)
			{
				namesArrayList = enterList(scnr);
				userOption = enterOptions(scnr);
			}
			else 
			{
				System.out.print("Incorrect option, please select one from the list above: ");
				userOption = enterOptions(scnr);
			}
			
			
		}
		System.out.println("\n\nProgram End");
		
		scnr.close();
	}

	/**
	 * This method alphabetcally orders the list of names and outputs them for the user when requested
	 */
	public static void orderedList(ArrayList<String> namesArrayList)
	{
		//The collections method and comparator sort the array into alphabetical order
		Collections.sort(namesArrayList, String.CASE_INSENSITIVE_ORDER);
		
		System.out.println("\nOption 1 Output:");
		System.out.println(namesArrayList + "\n");
		
	}
	
	/**
	 * This methods looks through the names list and only prints out the names that have first and last names
	 */
	public static void fullNames(ArrayList<String> namesArrayList)
	{
		Collections.sort(namesArrayList, String.CASE_INSENSITIVE_ORDER);
		
		System.out.println("\nOption 2 Output:");
		for(int i = 0; i < namesArrayList.size(); i++)
		{
			if(namesArrayList.get(i).contains(" "))//If one has a space its a full name and is printed out
			{
				System.out.println(namesArrayList.get(i));
			}
			
		}
		System.out.println(); 
	}
	
	/**
	 * This method looks through the list and only prints single names
	 */
	public static void singleNames(ArrayList<String> namesArrayList) 
	{
		Collections.sort(namesArrayList, String.CASE_INSENSITIVE_ORDER);
	
		System.out.println("\nOption 3 Output:");
		for(int i = 0; i < namesArrayList.size(); i++)
		{
			if(!namesArrayList.get(i).contains(" "))//if one doesnt have a space it is printed
			{
				System.out.println(namesArrayList.get(i));
			}
			
		}
		System.out.println(); 
	}
	
	/**
	 * This method outputs multiple characteristics of the list of names. Such as the number of names, total characters, 
	 * average length, shortest name, longest name, and the population standard deviation of the name lengths. This method 
	 * does not consider the spaces as characters when outputting its result.
	 */
	public static void nameStatistics(ArrayList<String> namesArrayList)
	{
		double namesCount = namesArrayList.size();//initialized into double because these variables are used for other computations.
		System.out.printf("Name Count: %.0f", namesCount);
		System.out.println();

		double totalChar = 0;
		for(String name : namesArrayList)//Counts characters not including spaces
		{
			int length = name.length();
			if(name.contains(" ")) 
			{
				length--;
			}
			
			totalChar += length;
		}
		System.out.printf("Letter Count Total: %.0f", totalChar);
		System.out.println();
		
		double avgLength = totalChar / namesCount;//Calculates average length using the last 2 sectiosn of code in the method
		System.out.printf("Avg Name Length: %.2f", avgLength);
		System.out.println();
		
		//calls the method to find the shortest name in thr array
		findShortestName(namesArrayList);
		
		//calls the method to find the longest name in the array
		findLongestName(namesArrayList);
		
		//calls the method to find the popuylation standatd deviation of the name lengths in the list
		standardDeviation(namesArrayList, avgLength, namesCount); 
	}
	
	/**
	 * This is a helper method from "nameStatistics" that allows the program to find the shortest name from the list
	 */
	public static void findShortestName(ArrayList<String>namesArrayList)
	{
		String shortestName = namesArrayList.get(0);//Sets shortest name to the first name in the list
		
		for(String name : namesArrayList)
		{
			int minLength = shortestName.length();
			if(shortestName.contains(" ")) { minLength--; }//this cuts the spaces out of the length before comparing
			
			int nameLength = name.length();
			if( name.contains(" ")){ nameLength--; }//this cuts the spaces out of the lengths before comparing
			
			if(minLength > nameLength)
			{
				shortestName = name;
			}
		}
		System.out.println("Shortest Name: " + shortestName);
	}
	
	
	/**
	 * This is a helper method from "nameStatistics" that allows the program to find the lognest name from the list
	 */
	public static void findLongestName(ArrayList<String>namesArrayList)
	{
		String longestName = namesArrayList.get(0);//sets longest name to the first name in the list
		
		for(String name : namesArrayList)
		{
			int maxLength = longestName.length();
			if(longestName.contains(" ")) { maxLength--; }//cuts spaces out of the length before comparing
			
			int nameLength = name.length();
			if( name.contains(" ")){ nameLength--; }//cuts spaces out of the length before comparing
			
			if(maxLength < nameLength)
			{
				longestName = name;
			}
		}
		System.out.println("Longest Name: " + longestName);
		
	}
	
	
	/**
	 * This is another helper method that helps find the population standard deviation in the "nameStatistics" method
	 */
	public static void standardDeviation(ArrayList<String>namesArrayList, double avgLength, double namesCount)
	{
		//reads the lengths of the elements in the list and puts it into a new array with doubles
		ArrayList<Double> nameLengths = new ArrayList<Double>();
		for(String name: namesArrayList)
		{
			double length = (double)name.length();//
			if(name.contains(" "))
			{
				length--;
			}
			nameLengths.add(length);
		}
		
		//does the first couple steps of finding the population standard deviation
		for(int i = 0; i < nameLengths.size(); i++)
		{
			nameLengths.set(i, Math.pow(nameLengths.get(i) - avgLength, 2));//calculates the square of each element length subtracted by the average length
		}
		
		//Finds the total of the Array List since calcuations above were done to each element
		double total = 0;
		for(double lengths : nameLengths)
		{
			total += lengths;
		}
		
		//finishes off the calculation by square rootign the total divided by the number of names
		double standardDeviation = Math.sqrt(total / namesCount);//
		
		System.out.printf("Population Standard Deviation: %.2f%n", standardDeviation);
		System.out.println();
	}
	
	/**
	 * This method finds all of the names with an even length and outputs them in the console. Spaces are also not considered when
	 * determining if they are even
	 */
	public static void namesEvenLength(ArrayList<String> namesArrayList)
	{
		Collections.sort(namesArrayList, String.CASE_INSENSITIVE_ORDER);

		System.out.print("\nOption 5 Output:\n");
		for(String name : namesArrayList)
		{
			int length = name.length();
			if(name.contains(" "))//finds if any have spaces and subtracts it from their length
			{
				length--;
			}
			if(length % 2 == 0)//calculates their remainder to see if they have even numeber of characters or odd
			{
				System.out.println(name);
			}
		}
		System.out.println(); 
	}

	
	/**
	 * This method finds all of the names with an odd length, similar to last one it does not consider spaces as characters
	 */
	public static void namesOddLength(ArrayList<String> namesArrayList)
	{
		Collections.sort(namesArrayList, String.CASE_INSENSITIVE_ORDER);

		System.out.println("\nOption 6 Output:");
		for(String name : namesArrayList)
		{
			int length = name.length();
			if(name.contains(" "))//subtracts spaces from the length of each name
			{
				length--;
			}
			if(length % 2 != 0)//calcualtes the remainder to see if the names are odd length or even
			{
				System.out.println(name);
			}
		}
		System.out.println(); 
	}

	
	/**
	 * This method outputs all of the uncapitalized names from the list
	 */
	public static void namesNotCapitalized(ArrayList<String> namesArrayList)
	{
		Collections.sort(namesArrayList, String.CASE_INSENSITIVE_ORDER);
		
		System.out.println("\nOption 7 Output:");
		
		
		for(String name : namesArrayList)
		{
			if(name.contains(" "))
			{
				String[] splitName = name.split(" ");//if the name has a space it is split into an Array to look at the first and last name
				//checks if the first name is capatilized
				if(Character.isLowerCase(splitName[0].charAt(0)))
				{
					System.out.println(splitName[0]);
				}
				//checks if the last name is capitilized
				if(Character.isLowerCase(splitName[1].charAt(0)))
				{
					System.out.println(splitName[1]); 
				}
			}
			//if there are no spaces, it checks for the name to see if its capitilized
			else if(Character.isLowerCase(name.charAt(0)))
			{
				System.out.println(name);
			}
		}
		System.out.println();
	}
//WHEN I MAKE THE PSUEDOCODE, MAKE SURE THE FREQUENT NAME METHOD MAKES SENSE(ie. MOVING STUFF AROUND FOR IT TO SEEM LOGICAL)
	
	
	
	/**
	 * This method compares all of the names in the list and finds the most repeted name and outputs it
	 */
	public static void mostFrequentName(ArrayList<String> namesArrayList)
	{		
		//initializeing variables
		int maxCount = 0;
		String frequentName = "";
		
		for(String name1 : namesArrayList)//selects the first element to compare through the rest
		{
			int count = 0;
			
			for(String name2 : namesArrayList)//the pair of for loops allows each and every element to be compared to eachother for frequency
			{
				if(name1.equalsIgnoreCase(name2))// if the names are equal then the count is increased
				{
					count++;
				}
			}
			
			if(count > maxCount)//checks to see if the name comapred had more frewuency then the current highest frequent name
			{
				maxCount = count; //if the name is more frequent then the mostfrequent variable is changed to it 
				frequentName = name1;
			}
		}
		System.out.print("\nOption 8 Output:");
		if(maxCount == 1)//if the max frequency is 1 then it means there was only 1 of every element, they were all unique
		{ 
			System.out.println("\nNo Most Frequent Names"); 
		}
		else 
		{
			System.out.println("\nMost Frequent Name: " + frequentName);
		}
		System.out.println();
	}
		
	
	/**
	 * This is a helper method for the main driver. This method prompts the user for the initial entry and the option 9 re-entry
	 */
	public static ArrayList<String> enterList(Scanner scnr)
	{
		System.out.print("Input: ");
		String namesList = scnr.nextLine();
		
		//Outputs a list of options for ther user to select from
		System.out.println("\nOptions: ");
		System.out.println("1: Display List Ordered");
		System.out.println("2: Display Full Names");
		System.out.println("3: Display Single Names");
		System.out.println("4: Display Name Statistics");
		System.out.println("5: Display Names with Even Length");
		System.out.println("6: Display Names with Odd Length");
		System.out.println("7: Display Names not Capitalized");
		System.out.println("8: Display Most Frequent Name");
		System.out.println("9: Enter new list of Names");
		System.out.println("0: Quit Program\n");
		
		//Reads in the array inputted by the user and puts it into an Array List to be returned to the main method
		String[] namesArray = namesList.split(",");
		ArrayList<String> namesArrayList = new ArrayList<String>(Arrays.asList(namesArray));
		
		return namesArrayList;
	}
	
	
	
	/**
	 * This is a very important method that allows the program to keep running by aksing the user for options until they end the program with "0"
	 */
	public static int enterOptions(Scanner scnr)
	{
		System.out.print("Select an Option: ");
		int userOption = scnr.nextInt(); //reads in the user inputs and returns it to the main method
		scnr.nextLine(); //resolved issue with entering a new list of names by the user
		
		return userOption;
	}

	
	
}

