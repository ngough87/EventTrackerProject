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

	
	
	@GetMapping("events/{eventId}")
	public Event findEvent(@PathVariable Integer eventId, HttpServletResponse res ){
		Event event =eventService.getEvent(eventId);
		if (event == null) {
			res.setStatus(404);
		}
		return event;
	}
	
	
	@PostMapping("events")
	public Event create(@RequestBody Event event, HttpServletResponse res, HttpServletRequest req){
		try{
			eventService.create(event);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(event.getId());
			res.setHeader("Location",url.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
			event=null;
		}
		  return event;
	}
	
	@PutMapping("events/{eventId}")
	public Event update(@PathVariable ("eventId") Integer eventId, @RequestBody Event event, HttpServletResponse res){
		
		try {
		event = eventService.update(eventId, event);
		if(event == null) {
			res.setStatus(404);
		}
		}catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			event=null;
		}
		
		return  event;
	}

	@DeleteMapping("events/{eventId}")
	public void delete(@PathVariable ("eventId") Integer eventId, HttpServletResponse res) {
		
		try {
			if(!eventService.deleteById(eventId)) {
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
