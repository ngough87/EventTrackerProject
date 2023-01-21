package com.skilldistillery.theomaha.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.theomaha.entities.Rating;
import com.skilldistillery.theomaha.repositories.RatingRepository;
@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepo;

	@Override
	public List<Rating> allRatings() {
		return ratingRepo.findAll();
	}

	@Override
	public Rating getRating(int ratingId) {
		Rating rating = null;
		Optional<Rating> ratOpt = ratingRepo.findById(ratingId);
		if (ratOpt.isPresent()) {
			rating = ratOpt.get();
		}
		return rating;
	}

	@Override
	public Rating create(Rating rating) {
		return ratingRepo.saveAndFlush(rating);
	}

	@Override
	public Rating update(int ratingId, Rating rating) {
		Rating ratingUpdated = getRating(ratingId);

		ratingUpdated.setName(rating.getName());

		return ratingRepo.save(ratingUpdated);
	}

	@Override
	public boolean deleteById(int ratingId) {
		ratingRepo.deleteById(ratingId);
		return ratingRepo.existsById(ratingId);
	}

}
