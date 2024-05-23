package project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SFRestaurantData {

	public static void main(String[] args) {
		//reads in a file from the command line
		if (args.length == 0) {
			System.err.println("Usage Error: the program expects file name as an argument.\n");
			System.exit(1);
		}

		File csvFile = new File(args[0]);
		if (!csvFile.exists()) {
			System.err.println("Error: the file " + csvFile.getAbsolutePath() + " does not exist.\n");
			System.exit(1);
		}
		if (!csvFile.canRead()) {
			System.err.println("Error: the file " + csvFile.getAbsolutePath() + " cannot be opened for reading.\n");
			System.exit(1);
		}

		// open the file for reading
		Scanner inCSV = null;

		try {
			inCSV = new Scanner(csvFile);
		} catch (FileNotFoundException e) {
			System.err.println("Error: the file " + csvFile.getAbsolutePath() + " cannot be opened for reading.\n");
			System.exit(1);
		}

		// read the content of the file and save the data in a list of restaurants
		ArrayList<String> restaurantInfo = new ArrayList<String>();
		RestaurantList restaurantsList = new RestaurantList();
		String line = null;
		Scanner parseLine = null;
		while (inCSV.hasNextLine()) {
			try {
				line = inCSV.nextLine();
				parseLine = new Scanner(line);
				parseLine.useDelimiter(", ");
			}

			catch (NoSuchElementException ex) {
				// caused by an incomplete or miss-formatted line in the input file
				System.err.println(line);
				continue;
			}
			try {
				//splits each line from the CSVfile, then assigns variables based on index in newly created array
				ParseFile p = new ParseFile();
				restaurantInfo = p.splitCSVLine(line);
				String name = restaurantInfo.get(1);
				String address = restaurantInfo.get(2);
				String phone = restaurantInfo.get(9);
				String zip = restaurantInfo.get(5);
				String date = restaurantInfo.get(11);
				Date inspectionDate = new Date(date);
				int score = Integer.valueOf(restaurantInfo.get(12));
				String violation = restaurantInfo.get(15);
				String risk = null;
				//checks if risk exists in file based on the size of the array 
				//Prevents OutOfBoundsException when declaring "risk"
				if (restaurantInfo.size() == 17) {
					risk = restaurantInfo.get(16);
				}
				Restaurant restaurant = new Restaurant(name, zip, address, phone);
				Inspection inspection = new Inspection(inspectionDate, score, violation, risk);
				restaurant.addInspection(inspection);
				//compares restaurant objects to find equivalents. Adds inspection from that restaurant to current restaurant
				for (Restaurant r2 : restaurantsList) {
					if (restaurant.equals(r2)) {
						restaurant.addInspection(r2.inspectionList.get(0));
					}
				}
				restaurantsList.add(restaurant);
			}

			catch (IllegalArgumentException ex) {
				// ignore this exception and skip to the next line
			}
		}
		
		/* 
		* Below is the interactive part of the program, where the program prompts the user for
		* a restaurant name or zipcode and returns a list of matches. It repeats this until the user
		* enters "quit"
		*/
		
		Scanner userInput = new Scanner(System.in);
		String userValue = "";
		
		System.out.println("Search the database by matching keywords to restaurant names or zipcodes.");
		System.out.println("\tTo search for matching restaurant names, enter \"name KEYWORD\"");
		System.out.println("\tTo search for matching zipcodes, enter \"zip KEYWORD\"");
		System.out.println("\tTo finish the program, enter \"quit\"\n");

		//repeatedly asks user for input and outputs matching restaurants until user ends the program
		//by entering "quit"
		do {
			System.out.println("Enter your search querey:");
			// get value of from the user
			userValue = userInput.nextLine();
			RestaurantList matchingRestaurants = new RestaurantList();
			if (!userValue.equalsIgnoreCase("quit")) {
				String[] userValues = userValue.split(" ");
				//checks if user entered name/zip and keyword. Prevents OutOfBoundsException
				//prints error if user did not enter name/zip and keyword
				if (userValues.length < 2) {
					System.err.println("This is not a valid querey. Try again");
					continue;
				}
				//sets the firstVal equal to name/zip entered
				//sets the secondVal equal to the keyword
				String firstVal = userValues[0];
				String secondVal = userValues[1];

				if (firstVal.equalsIgnoreCase("name")) {
					restaurantsList.getMatchingRestaurants(secondVal, matchingRestaurants);
					for (Restaurant r : matchingRestaurants) {
						System.out.println(r);
					}
				}

				else if (firstVal.equalsIgnoreCase("zip")) {
					restaurantsList.getMatchingZip(secondVal, matchingRestaurants);
					for (Restaurant r : matchingRestaurants) {
						System.out.println(r);
					}
				}
				//user entered a firstVal different than name or zip
				else {
					System.err.println("This is not a valid querey. Try again.");
				}
			}
		}

		while (!userValue.equalsIgnoreCase("quit"));

		userInput.close();
	}
}
