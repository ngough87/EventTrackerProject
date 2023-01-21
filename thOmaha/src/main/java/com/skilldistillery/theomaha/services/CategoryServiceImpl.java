package com.skilldistillery.theomaha.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.theomaha.entities.Category;
import com.skilldistillery.theomaha.entities.Event;
import com.skilldistillery.theomaha.repositories.CategoryRepository;
@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public List<Category> allCategorys() {
		return categoryRepo.findAll();
	}

	@Override
	public Category getCategory(int categoryId) {
		Category category = null;
		Optional<Category> catOpt = categoryRepo.findById(categoryId);
		if (catOpt.isPresent()) {
			category = catOpt.get();
		}
		return category;
	}

	@Override
	public Category create(Category category) {
		return categoryRepo.saveAndFlush(category);
	}

	@Override
	public Category update(int categoryId, Category category) {
		Category catUpdated = getCategory(categoryId);

		catUpdated.setName(category.getName());

		return categoryRepo.save(catUpdated);
	}

	@Override
	public boolean deleteById(int categoryId) {
		categoryRepo.deleteById(categoryId);
		return categoryRepo.existsById(categoryId);
	}

}
