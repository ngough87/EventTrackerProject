package com.skilldistillery.theomaha.services;

import java.util.List;

import com.skilldistillery.theomaha.entities.FoodType;

public interface FoodTypeService {
	
	List<FoodType> allFoodTypes();
	FoodType  getFoodType(int foodTypeId);
	FoodType create(FoodType foodType);
	FoodType update(int foodTypeId, FoodType foodType);
	boolean deleteById(int foodTypeId);

}
