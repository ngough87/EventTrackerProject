package com.skilldistillery.theomaha.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.theomaha.entities.Rating;
import com.skilldistillery.theomaha.entities.Restaraunt;
import com.skilldistillery.theomaha.repositories.RestarauntRepository;
@Service
public class RestarauntServiceImpl implements RestarauntService {
	
	@Autowired
	RestarauntRepository restRepo;

	@Override
	public List<Restaraunt> allRestaraunts() {
		return restRepo.findAll();
	}

	@Override
	public Restaraunt getRestaraunt(int restarauntId) {
		Restaraunt rest = null;
		Optional<Restaraunt> restOpt = restRepo.findById(restarauntId);
		if (restOpt.isPresent()) {
			rest = restOpt.get();
		}
		return rest;
	}

	@Override
	public Restaraunt create(Restaraunt restaraunt) {
		return restRepo.saveAndFlush(restaraunt);
	}

	@Override
	public Restaraunt update(int restarauntId, Restaraunt restaraunt) {
		Restaraunt restUpdated = getRestaraunt(restarauntId);

		restUpdated.setName(restaraunt.getName());
		restUpdated.setCategory(restaraunt.getCategory());
		restUpdated.setLocation(restaraunt.getLocation());
		restUpdated.setFoodType(restaraunt.getFoodType());
		restUpdated.setRating(restaraunt.getRating());
		restUpdated.setDescription(restaraunt.getDescription());

		return restRepo.save(restUpdated);
	}

	@Override
	public boolean deleteById(int restarauntId) {
		restRepo.deleteById(restarauntId);
		return restRepo.existsById(restarauntId);
	}

}
