package com.skilldistillery.theomaha.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.theomaha.entities.Restaraunt;
import com.skilldistillery.theomaha.services.RestarauntService;

@RestController
@RequestMapping("api")
public class RestarauntContoller {
	@Autowired RestarauntService restService;
	
	@GetMapping("restaraunts")
	public List<Restaraunt> listAllRestaraunts(){
		return restService.allRestaraunts();
	}

	
	
	@GetMapping("restaraunts/{restarauntId}")
	public Restaraunt findRestaraunts(@PathVariable Integer restarauntId, HttpServletResponse res ){
		Restaraunt restaraunt =restService.getRestaraunt(restarauntId);
		if (restaraunt == null) {
			res.setStatus(404);
		}
		return restaraunt;
	}
	
	
	@PostMapping("restaraunts")
	public Restaraunt create(@RequestBody Restaraunt restaraunt, HttpServletResponse res, HttpServletRequest req){
		try{
			restService.create(restaraunt);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(restaraunt.getId());
			res.setHeader("Location",url.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
			restaraunt=null;
		}
		  return restaraunt;
	}
	
	@PutMapping("restaraunts/{restarauntId}")
	public Restaraunt update(@PathVariable ("restarauntId") Integer restarauntId, @RequestBody Restaraunt restaraunt, HttpServletResponse res){
		
		try {
		restaraunt = restService.update(restarauntId, restaraunt);
		if(restaraunt == null) {
			res.setStatus(404);
		}
		}catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			restaraunt=null;
		}
		
		return  restaraunt;
	}

	@DeleteMapping("restaraunts/{restarauntId}")
	public void delete(@PathVariable ("restarauntId") Integer restarauntId, HttpServletResponse res) {
		
		try {
			if(restService.deleteById(restarauntId)) {
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
