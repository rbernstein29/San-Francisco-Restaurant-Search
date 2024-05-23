package project3;

public class RestaurantList extends LinkedList<Restaurant> {
	
	RestaurantList () {}
	
	/**
	 * @param keyword; a restaurant name entered by the user
	 * @param matchingRestaurants; an empty list to which restaurants with names matching to keyword will be added
	 * @return matching restaurants based on names
	 */
	public RestaurantList getMatchingRestaurants(String keyword, RestaurantList matchingRestaurants) { 
		if (keyword == null) {
			return null;
		}
		//traverses the restaurantList 
		for (Restaurant r : this) {
			if (r.name.toLowerCase().contains(keyword.toLowerCase())) {
				matchingRestaurants.add(r); 
			}
		}
		
		//matchingRestaurants.size() == 0 when there are no matching restaurants
		if (matchingRestaurants.size() == 0) {
			System.err.println("No matches found."); 
			return null;
		}
		
		//removes duplicates
		matchingRestaurants.removeDuplicates();
		return matchingRestaurants;
	}
	
	/**
	 * @param keyword; a zipcode entered by the user
	 * @param matchingRestaurants; an empty list to which restaurants with zipcodes matching to keyword will be added
	 * @return matching restaurants based on zipcode
	 */
	public RestaurantList getMatchingZip(String keyword, RestaurantList matchingRestaurants) { 
		if (keyword == null) {
			return null;
		}
		for (Restaurant r : this) {
			if (r.zip.equals(keyword)) {
				matchingRestaurants.add(r); 
				}
		}
		
		//there are no matches
		if (matchingRestaurants.size() == 0) {
			System.err.println("No matches found."); 
			return null;
		}
		
		//removes duplicates
		matchingRestaurants.removeDuplicates();
		return matchingRestaurants;
	}
	
	/**
	 * @return a list without duplicated restaurants
	 * the list is sorted, so if there are duplicates, they will be next to each other
	 */
	public RestaurantList removeDuplicates() {
		this.sort();   // sorts the list so that any duplicates would be consecutive
		int i = 0;     
		while (i < this.size() - 2) {
			if (this.get(i).equals(this.get(i+1))) {   // there is a duplicate
				this.remove(this.get(i));              // removes the first of the two duplicates
			}
			else {
				i++;
			}
		}
		return this;
	}
	
	/**
	 * @return a string of restaurant names separated by a ; 
	 */
	@Override
	public String toString() {
		String restaurants = this.get(0).name; 
		for (Restaurant r : this) {
			restaurants += ("; " + r.name);
		}
		return restaurants;
	}
}


	
