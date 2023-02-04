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

import com.skilldistillery.theomaha.entities.Rating;
import com.skilldistillery.theomaha.services.RatingService;

@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
@RestController
public class RatingController {
	
	@Autowired RatingService ratService;
	
	@GetMapping("ratings")
	public List<Rating> listAllRatings(){
		return ratService.allRatings();
	}

	
	
	@GetMapping("ratings/{ratingId}")
	public Rating findRating(@PathVariable Integer ratingId, HttpServletResponse res ){
		Rating rating =ratService.getRating(ratingId);
		if (rating == null) {
			res.setStatus(404);
		}
		return rating;
	}
	
	
	@PostMapping("ratings")
	public Rating create(@RequestBody Rating rating, HttpServletResponse res, HttpServletRequest req){
		try{
			ratService.create(rating);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(rating.getId());
			res.setHeader("Location",url.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
			rating=null;
		}
		  return rating;
	}
	
	@PutMapping("ratings/{ratingId}")
	public Rating update(@PathVariable ("ratingId") Integer ratingId, @RequestBody Rating rating, HttpServletResponse res){
		
		try {
		rating = ratService.update(ratingId, rating);
		if(rating == null) {
			res.setStatus(404);
		}
		}catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			rating=null;
		}
		
		return  rating;
	}

	@DeleteMapping("ratings/{ratingId}")
	public void delete(@PathVariable ("ratingId") Integer ratingId, HttpServletResponse res) {
		
		try {
			if(ratService.deleteById(ratingId)) {
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
