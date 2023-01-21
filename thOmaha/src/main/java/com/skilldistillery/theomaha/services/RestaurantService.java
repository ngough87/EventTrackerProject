package com.skilldistillery.theomaha.services;

import java.util.List;

import com.skilldistillery.theomaha.entities.Restaurant;

public interface RestaurantService {
	
	
	List<Restaurant> allRestaurants();
	Restaurant  getRestaraunt(int restaurantId);
	Restaurant create(Restaurant restaurant);
	Restaurant update(int restaurantId, Restaurant restaurant);
	boolean deleteById(int restaurantId);


}
