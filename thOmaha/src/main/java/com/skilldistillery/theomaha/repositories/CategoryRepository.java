package com.skilldistillery.theomaha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.theomaha.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
