package com.skilldistillery.theomaha.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.theomaha.entities.Event;
import com.skilldistillery.theomaha.services.EventService;

@RestController
@RequestMapping("api")
public class EventController {
	
	@Autowired EventService eventService;
	
	@GetMapping("events")
	public List<Event> listAllEvents(){
		return eventService.allEvents();
	}

	
	
	@GetMapping("events/{id}")
	public Event findEvent(@PathVariable Integer id, HttpServletResponse res ){
		Event event =eventService.getEvent(id);
		if (event == null) {
			res.setStatus(404);
		}
		return event;
	}
	
	
}
