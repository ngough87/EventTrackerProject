package com.skilldistillery.theomaha.services;

import java.util.List;

import com.skilldistillery.theomaha.entities.Restaraunt;

public interface RestarauntService {
	
	
	List<Restaraunt> allRestaraunts();
	Restaraunt  getRestaraunt(int restarauntId);
	Restaraunt create(Restaraunt restaraunt);
	Restaraunt update(int restarauntId, Restaraunt restaraunt);
	boolean deleteById(int restarauntId);


}
