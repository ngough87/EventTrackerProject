package com.skilldistillery.theomaha.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.theomaha.entities.Rating;
import com.skilldistillery.theomaha.entities.Restaurant;
import com.skilldistillery.theomaha.repositories.RestaurantRepository;
@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	RestaurantRepository restRepo;

	@Override
	public List<Restaurant> allRestaurants() {
		return restRepo.findAll();
	}

	@Override
	public Restaurant getRestaraunt(int restaurantId) {
		Restaurant rest = null;
		Optional<Restaurant> restOpt = restRepo.findById(restaurantId);
		if (restOpt.isPresent()) {
			rest = restOpt.get();
		}
		return rest;
	}

	@Override
	public Restaurant create(Restaurant restaurant) {
		return restRepo.saveAndFlush(restaurant);
	}

	@Override
	public Restaurant update(int restaurantId, Restaurant restaurant) {
		Restaurant restUpdated = getRestaraunt(restaurantId);

		restUpdated.setName(restaurant.getName());
		restUpdated.setCategory(restaurant.getCategory());
		restUpdated.setLocation(restaurant.getLocation());
		restUpdated.setFoodType(restaurant.getFoodType());
		restUpdated.setRating(restaurant.getRating());
		restUpdated.setDescription(restaurant.getDescription());

		return restRepo.save(restUpdated);
	}

	@Override
	public boolean deleteById(int restaurantId) {
		restRepo.deleteById(restaurantId);
		return restRepo.existsById(restaurantId);
	}

}
