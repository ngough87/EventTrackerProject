package com.skilldistillery.theomaha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.theomaha.entities.FoodType;

public interface FoodTypeRepository extends JpaRepository<FoodType, Integer> {

}
