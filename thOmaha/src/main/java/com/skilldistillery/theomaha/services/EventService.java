package com.skilldistillery.theomaha.services;

import java.util.List;

import com.skilldistillery.theomaha.entities.Event;

public interface EventService {

	List<Event> allEvents();
	Event  getEvent(int eventId);
	Event create(Event event);
	Event update(int eventId, Event event);
	Event deleteById(int eventId);
	
	
}
