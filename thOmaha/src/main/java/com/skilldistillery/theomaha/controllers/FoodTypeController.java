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

import com.skilldistillery.theomaha.entities.FoodType;
import com.skilldistillery.theomaha.services.FoodTypeService;

@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
@RestController
public class FoodTypeController {

	@Autowired FoodTypeService foodTypeService;
	
	@GetMapping("foodTypes")
	public List<FoodType> listAllFoodTypes(){
		return foodTypeService.allFoodTypes();
	}

	
	
	@GetMapping("foodTypes/{foodTypeId}")
	public FoodType findEvent(@PathVariable Integer foodTypeId, HttpServletResponse res ){
		FoodType foodType =foodTypeService.getFoodType(foodTypeId);
		if (foodType == null) {
			res.setStatus(404);
		}
		return foodType;
	}
	
	
	@PostMapping("foodTypes")
	public FoodType create(@RequestBody FoodType foodType, HttpServletResponse res, HttpServletRequest req){
		try{
			foodTypeService.create(foodType);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(foodType.getId());
			res.setHeader("Location",url.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
			foodType=null;
		}
		  return foodType;
	}
	
	@PutMapping("foodTypes/{foodTypeId}")
	public FoodType update(@PathVariable ("foodTypeId") Integer foodTypeId, @RequestBody FoodType foodType, HttpServletResponse res){
		
		try {
		foodType = foodTypeService.update(foodTypeId, foodType);
		if(foodType == null) {
			res.setStatus(404);
		}
		}catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			foodType=null;
		}
		
		return  foodType;
	}

	@DeleteMapping("foodTypes/{foodTypeId}")
	public void deletePost(@PathVariable ("foodTypeId") Integer foodTypeId, HttpServletResponse res) {
		
		try {
			if(foodTypeService.deleteById(foodTypeId)) {
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
