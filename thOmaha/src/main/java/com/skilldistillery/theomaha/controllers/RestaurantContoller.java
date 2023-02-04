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

import com.skilldistillery.theomaha.entities.Restaurant;
import com.skilldistillery.theomaha.services.RestaurantService;

@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
@RestController
public class RestaurantContoller {
	@Autowired RestaurantService restService;
	
	@GetMapping("restaurants")
	public List<Restaurant> listAllRestaurants(){
		return restService.allRestaurants();
	}

	
	
	@GetMapping("restaurants/{restaurantId}")
	public Restaurant findRestaurants(@PathVariable Integer restaurantId, HttpServletResponse res ){
		Restaurant restaurant =restService.getRestaraunt(restaurantId);
		if (restaurant == null) {
			res.setStatus(404);
		}
		return restaurant;
	}
	
	
	@PostMapping("restaurants")
	public Restaurant create(@RequestBody Restaurant restaurant, HttpServletResponse res, HttpServletRequest req){
		try{
			restService.create(restaurant);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(restaurant.getId());
			res.setHeader("Location",url.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
			restaurant=null;
		}
		  return restaurant;
	}
	
	@PutMapping("restaurants/{restaurantId}")
	public Restaurant update(@PathVariable ("restaurantId") Integer restaurantId, @RequestBody Restaurant restaurant, HttpServletResponse res){
		
		try {
		restaurant = restService.update(restaurantId, restaurant);
		if(restaurant == null) {
			res.setStatus(404);
		}
		}catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			restaurant=null;
		}
		
		return  restaurant;
	}

	@DeleteMapping("restaurants/{restaurantId}")
	public void delete(@PathVariable ("restaurantId") Integer restaurantId, HttpServletResponse res) {
		
		try {
			if(restService.deleteById(restaurantId)) {
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
