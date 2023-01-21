package com.skilldistillery.theomaha.services;

import java.util.List;

import com.skilldistillery.theomaha.entities.Rating;

public interface RatingService {
	
	List<Rating> allRatings();
	Rating  getRating(int ratingId);
	Rating create(Rating rating);
	Rating update(int ratingId, Rating rating);
	boolean deleteById(int ratingId);


}
