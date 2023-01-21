package com.skilldistillery.theomaha.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.theomaha.entities.FoodType;
import com.skilldistillery.theomaha.entities.FoodType;
import com.skilldistillery.theomaha.entities.FoodType;
import com.skilldistillery.theomaha.repositories.FoodTypeRepository;
@Service
public class FoodTypeServiceImpl implements FoodTypeService {
	
	@Autowired
	FoodTypeRepository foodTypeRepo;

	@Override
	public List<FoodType> allFoodTypes() {
		return foodTypeRepo.findAll();
	}

	@Override
	public FoodType getFoodType(int foodTypeId) {
		FoodType foodType = null;
		Optional<FoodType> foodOpt = foodTypeRepo.findById(foodTypeId);
		if (foodOpt.isPresent()) {
			foodType = foodOpt.get();
		}
		return foodType;
	}

	@Override
	public FoodType create(FoodType foodType) {
		return foodTypeRepo.saveAndFlush(foodType);
	}

	@Override
	public FoodType update(int foodTypeId, FoodType foodType) {
		FoodType foodTypeUpdated = getFoodType(foodTypeId);

		foodTypeUpdated.setName(foodType.getName());

		return foodTypeRepo.save(foodTypeUpdated);
	}

	@Override
	public boolean deleteById(int foodTypeId) {
		foodTypeRepo.deleteById(foodTypeId);
		return foodTypeRepo.existsById(foodTypeId);
	}
	}


