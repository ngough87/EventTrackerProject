package com.skilldistillery.theomaha.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.theomaha.entities.Category;
import com.skilldistillery.theomaha.services.CategoryService;

@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
@RestController
public class CategoryController {

	@Autowired CategoryService catService;
	
	@GetMapping("categories")
	public List<Category> listAllCategories(){
		return catService.allCategorys();
	}

	
	
	@GetMapping("categories/{categoryId}")
	public Category findEvent(@PathVariable Integer categoryId, HttpServletResponse res ){
		Category category =catService.getCategory(categoryId);
		if (category == null) {
			res.setStatus(404);
		}
		return category;
	}
	
	
	@PostMapping("categories")
	public Category create(@RequestBody Category category, HttpServletResponse res, HttpServletRequest req){
		try{
			catService.create(category);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(category.getId());
			res.setHeader("Location",url.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
			category=null;
		}
		  return category;
	}
	
	@PutMapping("categories/{categoryId}")
	public Category update(@PathVariable ("categoryId") Integer categoryId, @RequestBody Category category, HttpServletResponse res){
		
		try {
		category = catService.update(categoryId, category);
		if(category == null) {
			res.setStatus(404);
		}
		}catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			category=null;
		}
		
		return  category;
	}

	@DeleteMapping("categories/{categoryId}")
	public void delete(@PathVariable ("categoryId") Integer categoryId, HttpServletResponse res) {
		
		try {
			if(catService.deleteById(categoryId)) {
				res.setStatus(204);
			}
			else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			res.setStatus(400);
		}
		
		
	}
	
	
	
}
