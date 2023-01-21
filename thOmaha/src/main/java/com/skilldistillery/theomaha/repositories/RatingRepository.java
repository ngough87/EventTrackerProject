package com.skilldistillery.theomaha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.theomaha.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
