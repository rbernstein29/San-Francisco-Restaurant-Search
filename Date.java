package project3;

public class Date implements Comparable<Date> {
	
	int day;
	int month;
	String yearWithTime;
	int year;
	Date (String date) throws IllegalArgumentException {
		if (date == null){
			throw new IllegalArgumentException("Date is null"); 
		}
		
		this.month = Integer.valueOf(date.split("/")[0]);
		this.day = Integer.valueOf(date.split("/")[1]);
		this.yearWithTime = date.split("/")[2];
		this.year = Integer.valueOf(yearWithTime.split(" ")[0]);
		
		//checks for valid dates based on day, month and year values
		
		//checks that all days are between 1 and 31, all months are between 1 and 12, and all years are between 2000 and 2025
		if (day < 1 || day > 31 || month < 1 || month > 12 || ((year < 0 || year > 25) && (year < 2000 || year > 2025))) {
			throw new IllegalArgumentException("Invalid date entered");
		}
		//checks that months with 30 days are not entered with a day greater than 30
		if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
			throw new IllegalArgumentException("Invalid date entered");
		}
		//checks that Februarys in leap years don't have a day greater than 29
		if ((month == 2 && (year == 2000 || year == 0 || year == 2004 || year == 4 || year == 2008 || year == 8 || year == 2012 || year == 12 || year == 2016 || year == 16 || year == 2020 || year == 20 || year == 2024 || year == 24)) && day > 29) {
			throw new IllegalArgumentException("Invalid date entered");
		}
		//checks that Februarys in non-leap years don't have a day greater than 28
		else if (month == 2 && day > 28) {
			throw new IllegalArgumentException("Invalid date entered");
		}
		
		
	}
	
	Date (int month, int date, int year) throws IllegalArgumentException {
		this.month = month;
		this.day = date;
		this.year = year;
		
		//checks for valid dates based on day, month and year values
		//checks that all days are between 1 and 31, all months are between 1 and 12, and all years are between 2000 and 2025
		if (day < 1 || day > 31 || this.month < 1 || this.month > 12 || ((this.year < 0 || this.year > 25) && (this.year < 2000 || this.year > 2025))) {
			throw new IllegalArgumentException("Invalid date entered");
		}
		//checks that months with 30 days are not entered with a day greater than 30
		if ((this.month == 4 || this.month == 6 || this.month == 9 || this.month == 11) && day > 30) {
			throw new IllegalArgumentException("Invalid date entered");
		}
		//checks that Februarys in leap years don't have a day greater than 29
		if ((this.month == 2 && (this.year == 2000 || this.year == 0 || this.year == 2004 || this.year == 4 || this.year == 2008 || this.year == 8 || this.year == 2012 || this.year == 12 || this.year == 2016 || this.year == 16 || this.year == 2020 || this.year == 20 || this.year == 2024 || this.year == 24)) && day > 29) {
			throw new IllegalArgumentException("Invalid date entered");
		}
		//checks that Februarys in non-leap years don't have a day greater than 28
		else if (this.month == 2 && day > 28) {
			throw new IllegalArgumentException("Invalid date entered");
		}
	}

	/**
	 * Compares two date objects, first by comparing years, then months, then days
	 * @return 1 for greater than if this.year is greater than o.year (meaning this date is greater than o date)
	 *		  -1 for less than if this.year is less than o.year (this date is less than o date)
	 *		   0 otherwise (the 2 date objects are equal)
	 */
	@Override
	public int compareTo(Date o) {
		if (this.year > o.year) {
			return 1;
		}
		if (this.year < o.year) {
			return -1;
		}
		else {
			if (this.month > o.month) {
				return 1;
			}
			if (this.month < o.month) {
				return -1;
			}
			else {
				if (this.day > o.day) {
					return 1;
				}
				if (this.day < o.day) {
					return -1;
				}
				return 0;
			}
		}
	}
	
	/**
	 * @return a string in the format "month/day/year"
	 */
	@Override
	public String toString() {
		if (year <= 9) {
			String dateString = month + "/" + day + "/200" + year;
			return dateString;
		}
		else if (year <= 25) {
			String dateString = month + "/" + day + "/20" + year;
			return dateString;
		}
		else {
			String dateString = month + "/" + day + "/" + year;
			return dateString;
		}
	}
}
