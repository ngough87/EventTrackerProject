package com.skilldistillery.theomaha.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.theomaha.entities.EventType;
import com.skilldistillery.theomaha.services.EventTypeService;
@CrossOrigin({"*", "http://localhost/"})
@RequestMapping("api")
@RestController
public class EventTypeController {
	
	
	@Autowired EventTypeService eventTypeService;

	
	@GetMapping("eventTypes")
	public List<EventType> listAllEventTypes(){
		return eventTypeService.allEventTypes();
	}

	
	
	@GetMapping("eventTypes/{eventTypeId}")
	public EventType findEventType(@PathVariable Integer eventTypeId, HttpServletResponse res ){
		EventType eventType =eventTypeService.getEventType(eventTypeId);
		if (eventType == null) {
			res.setStatus(404);
		}
		return eventType;
	}
	
	
	@PostMapping("eventTypes")
	public EventType create(@RequestBody EventType eventType, HttpServletResponse res, HttpServletRequest req){
		try{
			eventTypeService.create(eventType);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(eventType.getId());
			res.setHeader("Location",url.toString());
			
		}catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
			eventType=null;
		}
		  return eventType;
	}
	
	@PutMapping("eventTypes/{eventId}")
	public EventType update(@PathVariable ("eventId") Integer eventTypeId, @RequestBody EventType eventType, HttpServletResponse res){
		
		try {
			eventType = eventTypeService.update(eventTypeId, eventType);
		if(eventType == null) {
			res.setStatus(404);
		}
		}catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			eventType=null;
		}
		
		return  eventType;
	}

	
}
