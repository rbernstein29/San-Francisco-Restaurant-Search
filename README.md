Allows users to search for restaurants in San Francisco, by restaurant ame or by street name, and gives the inspection results for that restaurant.

Each restaurant is an object with name, address, and inspection information. These restaurants are read from a CSV file and inserted into a linked list, ordered alphabetically by
restaurant name. Once the user passes search arguments to the program, a linear search is conducted to find all restaurants in the list which meet the given criterea. These restaurants
are then inserted into another linked list of resultant restaurants. This list is iterated through and each restaurant is printed with the corresponding inspection information. 

This program includes error handling and prevents crashes. It will continuously allow the user to search for restaurants and return results until the user quits. 
