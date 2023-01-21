package com.skilldistillery.theomaha.services;

import java.util.List;

import com.skilldistillery.theomaha.entities.Category;

public interface CategoryService {
	
	
	List<Category> allCategorys();
	Category  getCategory(int categoryId);
	Category create(Category category);
	Category update(int categoryId, Category category);
	boolean deleteById(int categoryId);

}
