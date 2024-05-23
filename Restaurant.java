package project3;

import java.util.ArrayList;

public class Restaurant implements Comparable<Restaurant> {
	public String name;
	public String address;
	public String phoneNumber;
	public String zip;
	public ArrayList<Inspection> inspectionList = new ArrayList<Inspection>();
	
	public Restaurant(String name, String zip) throws IllegalArgumentException {
		this (name, zip, null, null);
		
	}
	
	public Restaurant(String name, String zip, String address, String phone) throws IllegalArgumentException {
		if (name == null || zip.length() != 5) {
			throw new IllegalArgumentException("Invalid entry");
		}
		this.name = name;
		this.zip = zip;
		this.address = address;
		this.phoneNumber = phone;
	}
	
	/**
	 * @param inspect; a single inspection made up of a date, score, violation and risk as declared in the Inspection class
	 * @throws IllegalArgumentException
	 * Adds a single inspection to a restaurant's list of inspections, sorts inspections as they are added based on most recent date
	 */
	public void addInspection(Inspection inspect) throws IllegalArgumentException {
		if (inspect == null) {
			throw new IllegalArgumentException("Invalid inspection entry");
		}
		//sorts inspection objects as they are added to inspectionList based on date (greatest to least - most recent dates start at index 0)
		for (Inspection i : inspectionList) {
			if (i.date.compareTo(inspect.date) == -1 || i.date.equals(inspect.date)) {
				inspectionList.add(inspectionList.indexOf(i), inspect);
				break;
			}
		}
		//if inspection object is greater than all currently in inspectionList, it is entered last
		//only inspection objects that are not currently in inspectionList are added
		if (!inspectionList.contains(inspect)) {
			inspectionList.add(inspect);
		}
	}
	
	/**
	 * @param o; a restaurant to be compared to 
	 * @return true if two restaurants' names and zipcodes are equal, false otherwise
	 */
	public boolean equals(Restaurant o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (!(o instanceof Restaurant)) {
			return false;
		}
		Restaurant other = (Restaurant) o;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		}
		if (zip == null) {
			if (other.zip != null) {
				return false;
			}
		}
		else if (!name.equalsIgnoreCase(other.name)  || !zip.equalsIgnoreCase(other.zip)) {
			return false;
		}
		return true;
	}

	/**
	 * @param o; a restaurant to be compared to
	 * @return -1 for less than if compareTo returns a value less than 0 (this is less than o)
	 *		 	1 for greater than if compareTo returns a value greater than 0 (this is greater than o)
	 *		 	0 otherwise(the 2 restaurant objects are equal)
	 */
	@Override
	public int compareTo(Restaurant o) {
		if (this.name.compareToIgnoreCase(o.name) < 0) {
			return -1;
			}
		
		if (this.name.compareToIgnoreCase(o.name) > 0) {
			return 1;
		}
		else {
			if (this.zip.compareTo(o.zip) < 0) {
				return -1;
			}
			if (this.zip.compareTo(o.zip) > 0) {
				return 1;
			}
			return 0;
		}
	}
	
	/**
	 * @return a string in an organized format with the name, address, 
	 * zipcode, phone number, and two most recent inspection results
	 */
	@Override
	public String toString() {
		String output = name + "\n _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\naddress                       : " + address + "\nzip                           : " + zip + "\nphone           : " + phoneNumber + "\nrecent inspection results: \n\t" + inspectionList.get(0) + "\n";
		//below removes duplicate inspections from the output
		int i = 1;
		while (i < inspectionList.size()) {
			if (!inspectionList.get(0).equals(inspectionList.get(i))) {
				output += "\t" + inspectionList.get(i) + "\n";
				break;
			}
			i++;
		}
		return output;
	}
}
